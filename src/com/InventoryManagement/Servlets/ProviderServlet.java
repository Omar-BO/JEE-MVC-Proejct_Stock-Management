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

import com.InventoryManagement.DAO.ProviderDAO;
import com.InventoryManagement.Beans.Provider;

/**
 * Servlet implementation class ProviderServlet
 */
@WebServlet("/provider")
public class ProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProviderDAO providerDAO;
	
	public void init() {
		providerDAO = new ProviderDAO();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProviderServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		try {
			if (page==null){
				listProvider(request, response);
			}
			else {
			switch (page) {
			case "new":
				showNewForm(request, response);
				break;
			case "insert":
				insertProvider(request, response);
				break;
			case "delete":
				deleteProvider(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateProvider(request, response);
				break;
			default:
				listProvider(request, response);
				break;
			}
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listProvider(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Provider> listProvider = providerDAO.selectAllProviders();
		request.setAttribute("listProvider", listProvider);
		RequestDispatcher dispatcher = request.getRequestDispatcher("provider-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("provider-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Provider existingProvider = providerDAO.selectProvider(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("provider-form.jsp");
		request.setAttribute("provider", existingProvider);
		dispatcher.forward(request, response);

	}

	private void insertProvider(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String mobile 	= request.getParameter("mobile");
		Provider newProvider 	= new Provider(name, address, mobile);
		providerDAO.insertProvider(newProvider);
		response.sendRedirect("provider");
	}

	private void updateProvider(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id 			= Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String mobile 	= request.getParameter("mobile");

		Provider provider = new Provider(id, name, address, mobile);
		providerDAO.updateProvider(provider);
		response.sendRedirect("provider");
	}

	private void deleteProvider(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		providerDAO.deleteProvider(id);
		response.sendRedirect("provider");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
