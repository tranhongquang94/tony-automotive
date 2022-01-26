package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

import DAO.CarsDAO;
import DAO.OrderDAO;
import DAO.Order_detailsDAO;
import DAO.UsersDAO;
import model.Car;
import model.CartDTO;
import model.Order;
import model.Users;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CarsDAO carsDAO = new CarsDAO();
    UsersDAO usersDAO = new UsersDAO();
    OrderDAO orderDAO = new OrderDAO();
    Order_detailsDAO order_detailsDAO = new Order_detailsDAO();

    public CartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String carId = request.getParameter("carId");
		String action = request.getParameter("action");
		
		//Get cart from session
		HttpSession session = request.getSession();
		CartDTO cart = (CartDTO) session.getAttribute("cart");
		
		if (action != null) {
			if (action.equals("view")) {
				showCartDetails(request, response);				
			} else if (action.equals("remove")) {
				removeCarById(request, response, carId, cart, session);
			} else {
				try {
					checkoutCart(request, response, cart, session);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return;
		};
		
		try {
			Car car = carsDAO.getCarById(Integer.parseInt(carId));
			boolean isExist = cart.getCars().contains(car);
			System.out.print(car);
			if (!isExist) {
				cart.getCars().add(car);
			}
			//Update cart to session
			session.setAttribute("cart", cart);
			
			response.sendRedirect("CarsListServlet?carId=" + carId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void showCartDetails (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("cart-detail.jsp");
		rd.forward(request, response);
	}
	
	private void removeCarById(HttpServletRequest request, HttpServletResponse response, String carId, CartDTO cart, HttpSession session) throws IOException {
		Car car = carsDAO.getCarById(Integer.parseInt(carId));
		
		// Create an iterator object to loop through cart list.
		Iterator<Car> itr = cart.getCars().iterator();
		
		// True when cart have at least 1 element left
		while(itr.hasNext()) {
			if (itr.next().getId() == Integer.parseInt(carId)) {
				itr.remove();
			}
		}
		
		//Update cart to session
		session.setAttribute("cart", cart);	
		response.sendRedirect("CartServlet?action=view");
	}
	

	private void checkoutCart(HttpServletRequest request, HttpServletResponse response, CartDTO cart, HttpSession session) throws SQLException, IOException {	
		//Get userId
		String email = (String) session.getAttribute("email");
		int userId = usersDAO.getUsersByEmail(email).getId();
		
		//Get the order time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
		LocalDateTime now = LocalDateTime.now();
		String orderTime = dtf.format(now);
		
		//Create order
		int newOrderId = orderDAO.insertOrder(userId, orderTime);
		
		//Get carId and create order details
		if (newOrderId != 0) {
			Iterator<Car> itr = cart.getCars().iterator();
			while(itr.hasNext()) {
				int carId = itr.next().getId();
				int result = order_detailsDAO.insertOrderDetails(newOrderId, carId, 1);
			};
			
			//Empty cart
			session.setAttribute("cart", new CartDTO(new HashSet<>()));
			
			response.sendRedirect("CarsListServlet");
		} else {
			response.sendError(400, "There is an error in creating order");
		}
		
	}
}
