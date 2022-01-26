package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CarsDAO;
import DAO.CategoryDAO;
import model.Car;
import model.Category;
import utils.ArrayUtil;

/**
 * Servlet implementation class CarsListServlet
 */
@WebServlet("/CarsListServlet")
public class CarsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CarsDAO carsDAO = new CarsDAO();
    CategoryDAO categoriesDAO = new CategoryDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarsListServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String categoryId = request.getParameter("categoryId");
		String carId = request.getParameter("carId");
		String page = request.getParameter("page");
		
		//Get number of pages and prepare array data to send to jsp
		int pageNum = carsDAO.getNumOfPages();
		List<Integer> pageArray = ArrayUtil.getPageArray(pageNum);
		
		try {
			List<Car> cars = null;
			List<Car> mostOrderedCars = carsDAO.getMostOrderedCars();
			List<Category> categories = categoriesDAO.getCategories();
			
			if (categoryId == null && page == null) {
				cars = carsDAO.getListOfCars();
			} else if (categoryId != null && page == null) {
				cars = carsDAO.getCarsByCategoryId(Integer.parseInt(categoryId));
			} else {
				cars = carsDAO.getCarByPages(Integer.parseInt(page));
			}
			
			if (carId != null) {
				 Car car = carsDAO.getCarById(Integer.parseInt(carId));
				 displayCarById(request, response, car);
				 return;
			}
			
			//attach data to request;
			request.setAttribute("cars", cars);
			request.setAttribute("categories", categories);
			request.setAttribute("pageArray", pageArray);
			request.setAttribute("mostOrderedCars", mostOrderedCars);
			
			//send data to jsp
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * Display Single Car on single car page
	 */
	private void displayCarById(HttpServletRequest request, HttpServletResponse response, Car car) {
		try {
			request.setAttribute("car", car);
			RequestDispatcher rd = request.getRequestDispatcher("car-detail.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
