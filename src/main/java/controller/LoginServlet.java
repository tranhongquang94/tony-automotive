package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UsersDAO;
import model.CartDTO;
import model.Users;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UsersDAO usersDAO = new UsersDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		String email = (String) session.getAttribute("email");
		
		if (email == null || action == null) {
			response.sendRedirect("login.jsp");
		} else {
			switch (action) {
			case "login": 
				response.sendRedirect("CarsListServlet");
				break;
			case "logout":
				logout(request,response); 
				break;
			default:
				response.sendRedirect("CarsListServlet");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String action = request.getParameter("action");
		
		if (action.equals("login")) {
			login(request, response, email, password);
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response, String email, String password) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			Users users = usersDAO.getUsersByEmailAndPassword(email, password);
			if (users != null) {
				session.setAttribute("email", email);
				session.setAttribute("cart", new CartDTO(new HashSet<>()));
				
				response.sendRedirect("CarsListServlet");
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	};
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("email");
		response.sendRedirect("CarsListServlet");
	}

}
