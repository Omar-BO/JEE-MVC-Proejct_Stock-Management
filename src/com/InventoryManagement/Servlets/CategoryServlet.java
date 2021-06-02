package com.InventoryManagement.Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.InventoryManagement.DAO.CategoryDAO;
import com.InventoryManagement.Beans.Category;
/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private CategoryDAO categoryDAO;
	
	public void init() {
		categoryDAO = new CategoryDAO();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		try {
			if (page==null){
				listCategory(request, response);
			}
			else {
			switch (page) {
			case "new":
				showNewForm(request, response);
				break;
			case "insert":
				insertCategory(request, response);
				break;
			case "delete":
				deleteCategory(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateCategory(request, response);
				break;
			default:
				listCategory(request, response);
				break;
			}
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Category> listCategory = categoryDAO.selectAllCategorys();
		request.setAttribute("listCategory", listCategory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("category-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Category existingCategory = categoryDAO.selectCategory(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
		request.setAttribute("category", existingCategory);
		dispatcher.forward(request, response);

	}

	private void insertCategory(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String label = request.getParameter("label");
		String description = request.getParameter("description");
		Category newCategory 	= new Category(label, description);
		categoryDAO.insertCategory(newCategory);
		response.sendRedirect("category");
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id 			= Integer.parseInt(request.getParameter("id"));
		String label = request.getParameter("label");
		String description = request.getParameter("description");
		Category category = new Category(id, label, description);
		categoryDAO.updateCategory(category);
		response.sendRedirect("category");
	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		categoryDAO.deleteCategory(id);
		response.sendRedirect("category");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
