package com.bclw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserExitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5283357710516401720L;

	public UserExitServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=null;
    
		try{
			if(request.getSession(false)!=null)
			session=request.getSession(false);
		      if(session!=null)
		      {
		    	  session.invalidate();
		    	  response.sendRedirect("../login.jsp");
		      }
		      else
		      {
		    	  response.sendRedirect("../login.jsp");
		      }
		}
		catch(Exception e){
			e.printStackTrace();
			request.getRequestDispatcher("/erro/404.jsp").forward(request, response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
