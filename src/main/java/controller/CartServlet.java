package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
import model.Order_details;
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
		Car car = null;
		
		if (action == null) {
			action = "";
		}
		
		if (carId != null) {
			car = carsDAO.getCarById(Integer.parseInt(carId));
		}
		switch (action) {
			case "view" : 
				showCartDetails(request, response);
				break;
			case "add":
				addCarToCart(request, response, car, cart, session);
				break;
			case "remove":
				removeCar(request, response, car, cart, session);
				break;
			case "reduceNum":
				changeCarNumById(request, response, car, cart, session, "reduce");
				break;
			case "addNum":
				changeCarNumById(request, response, car, cart, session, "add");
				break;
			case "checkout":
				//Checkout cart
				try {
					checkoutCart(request, response, cart, session);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
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
	
	private void addCarToCart(HttpServletRequest request, HttpServletResponse response, Car car, CartDTO cart, HttpSession session) throws IOException {
		// Add car to cart
		try {
			boolean isExist = cart.getCars().containsKey(car);
			
			if (!isExist) {
				cart.getCars().put(car, 1);
			} else {
				cart.getCars().put(car, cart.getCars().get(car) + 1);
			}
			//Update cart to session
			session.setAttribute("cart", cart);
			
			response.sendRedirect("CarsListServlet");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void removeCar(HttpServletRequest request, HttpServletResponse response, Car car, CartDTO cart, HttpSession session) throws IOException {
		cart.getCars().remove(car);
		
		
//		Update cart to session
		session.setAttribute("cart", cart);	
		response.sendRedirect("CartServlet?action=view");
	}
	
	private void changeCarNumById(HttpServletRequest request, HttpServletResponse response, Car car, CartDTO cart,
			HttpSession session, String string) throws IOException {
		//Get number of specific car in cart
		int frequency = cart.getCars().get(car);
		if (string.equals("add")) {
			cart.getCars().put(car, frequency + 1);
		} else {
			if (frequency == 1) {
				// Remove the car ? 
				session.setAttribute("showModal", true);
			} else {
				cart.getCars().put(car, frequency - 1);	
			}
		}
		session.setAttribute("cart", cart);
		response.sendRedirect("CartServlet?action=view");
	}

	private void checkoutCart(HttpServletRequest request, HttpServletResponse response, CartDTO cart, HttpSession session) throws SQLException, IOException {	
//		//Get userId
//		String email = (String) session.getAttribute("email");
//		int userId = usersDAO.getUsersByEmail(email).getId();
//		
//		//Get the order time
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
//		LocalDateTime now = LocalDateTime.now();
//		String orderTime = dtf.format(now);
//		
//		//Create new order object;
//		Order order = new Order();
//		order.setUserId(userId);
//		order.setOrderTime(orderTime);
//		
//		// Create new order details obj and list
//		List<Order_details> orderDetailsList = new ArrayList<Order_details>();
//		
//		//Iterate through car in cart and add carId to orderDetailsList
//		for (Car car : cart.getCars()) {
//			int carId = car.getId();
//			orderDetailsList.add(new Order_details(carId));
//		}
//		
//		order_detailsDAO.insert(order, orderDetailsList);
//		//Empty cart
//		cart.setCars(new ArrayList<Car>());
//		session.setAttribute("cart", cart);
//			
//		response.sendRedirect("CarsListServlet");
	}
}
