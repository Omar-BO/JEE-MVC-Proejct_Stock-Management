package com.InventoryManagement.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.InventoryManagement.Beans.CurrentUser;
import com.InventoryManagement.Beans.LoginBean;
import com.InventoryManagement.DAO.LoginDAO;;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LoginDAO loginDao;

    public void init() {
        loginDao = new LoginDAO();
    }
       
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        //LoginBean loginBean = new LoginBean();
        //loginBean.setUsername(username);
        //loginBean.setPassword(password);
        CurrentUser.username=username;
        CurrentUser.password=password;
        try {
            if (loginDao.validate()) {
                //HttpSession session = request.getSession();
                request.setAttribute("username", username);
                //int role=loginDao.role();
                loginDao.role();
                //request.setAttribute("role",role);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
//                dispatcher.forward(request, response);
            	response.sendRedirect("home");
            } else {
            	int test=1;
            	request.setAttribute("test", test);
        		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        		dispatcher.forward(request, response);
                //response.sendRedirect("index.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}

}
