package controller;

import java.io.IOException;
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
 * Servlet implementation class LoginBOServlet
 */
@WebServlet("/LoginBOServlet")
public class LoginBOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UsersDAO usersDAO = new UsersDAO(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginBOServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("bo/admin/loginBO.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		switch(action) {
		case "login":
			login(request, response, email, password);
			break;
		case "logout":
			logout(request, response);
			break;
		default:
			response.sendRedirect("bo/admin/loginBO.jsp");
			break;
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response, String email, String password) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Users users = usersDAO.getUsersByEmailAndPassword(email, password);
			if (users != null) {
				session.setAttribute("email", email);
				session.setAttribute("role", "admin");
				response.sendRedirect("CarsListBOServlet");
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
		session.removeAttribute("role");
		response.sendRedirect("LoginBOServlet");
	}

}
