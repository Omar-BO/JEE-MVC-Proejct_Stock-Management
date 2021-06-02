package com.InventoryManagement.Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.InventoryManagement.DAO.CategoryDAO;
import com.InventoryManagement.DAO.ProductDAO;
import com.InventoryManagement.DAO.ProviderDAO;
import com.InventoryManagement.Beans.Product;

@WebServlet("/product")
public class productServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	
	public void init() {
		productDAO = new ProductDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String page = request.getParameter("page");
		try {
			if (page==null){
				listProduct(request, response);
			}
			else {
			switch (page) {
			case "new":
				showNewForm(request, response);
				break;
			case "insert":
				insertProduct(request, response);
				break;
			case "delete":
				deleteProduct(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateProduct(request, response);
				break;
			default:
				listProduct(request, response);
				break;
			}
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//List<Product> listProduct = productDAO.selectAllPRODUCTS();
		List<Map<String, String>> listProduct = productDAO.selectAllProductS();
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO category = new CategoryDAO();
		List<Map<String, String>> maListe = category.selectAllCategory();
		request.setAttribute("listCategory", maListe);
		ProviderDAO provider = new ProviderDAO();
		List<Map<String, String>> maListe2 = provider.selectAllProvider();
		request.setAttribute("listProvider", maListe2);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		CategoryDAO category = new CategoryDAO();
		List<Map<String, String>> maListe = category.selectAllCategory();
		request.setAttribute("listCategory", maListe);
		ProviderDAO provider = new ProviderDAO();
		List<Map<String, String>> maListe2 = provider.selectAllProvider();
		request.setAttribute("listProvider", maListe2);
		int id = Integer.parseInt(request.getParameter("id"));
		Product existingProduct = productDAO.selectproduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		request.setAttribute("product", existingProduct);
		dispatcher.forward(request, response);

	}

	private void insertProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String label 		= request.getParameter("label");
		String description 	= request.getParameter("description");
		int quantity 		= Integer.parseInt(request.getParameter("quantity"));
		String price		= request.getParameter("price");
		String VAT 			= request.getParameter("vat");
		int idCategory 		= Integer.parseInt(request.getParameter("category"));
		int idProvider 		= Integer.parseInt(request.getParameter("provider"));
		Product newProduct 	= new Product(label,description,quantity,price,VAT,idCategory,idProvider);
		productDAO.insertproduct(newProduct);
		response.sendRedirect("product");
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id 				= Integer.parseInt(request.getParameter("id"));
		String label 		= request.getParameter("label");
		String description 	= request.getParameter("description");
		int quantity 		= Integer.parseInt(request.getParameter("quantity"));
		String price		= request.getParameter("price");
		String VAT 			= request.getParameter("vat");
		int idCategory 		= Integer.parseInt(request.getParameter("category"));
		int idProvider 		= Integer.parseInt(request.getParameter("provider"));

		Product book = new Product(id, label,description,quantity,price,VAT,idCategory,idProvider);
		productDAO.updateproduct(book);
		response.sendRedirect("product");
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		productDAO.deleteproduct(id);
		response.sendRedirect("product");

	}

}
