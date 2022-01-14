package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		String username = (String) session.getAttribute("username");
		
		if (username == null || action == null) {
			response.sendRedirect("login.jsp");
		} else {
			switch (action) {
			case "login": 
				response.sendRedirect("index.jsp");
				break;
			case "logout":
				logout(request,response);
				break;
			default:
				response.sendRedirect("index.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String action = request.getParameter("action");
		
		if (action.equals("login")) {
			login(request, response, username, password);
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response, String username, String password) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (username.equals("admin") && password.equals("admin")) {
			session.setAttribute("username", username);
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}
	};
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		response.sendRedirect("index.jsp");
	}

}
