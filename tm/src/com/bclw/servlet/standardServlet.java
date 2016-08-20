package com.bclw.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bclw.service.AdminServiceImp;
import com.bclw.service.CommonServiceImp;

public abstract class standardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7296890978093057483L;
	protected AdminServiceImp	adsi			= new AdminServiceImp();
	protected CommonServiceImp	csi				= new CommonServiceImp();
	protected String			action;									// 用户的动作
	protected int				pageSize		= 2;						// 页面大小
	protected int				totalPage;									// 总页数
	protected int				currentPageNo	= 1;						// 当前页数
	protected int				totalRow;									// 表的总数据条数
	protected ArrayList<?>		currentPage;								// 当前页面
	protected int				id;
	protected String			table;										// 用户要操作的表

	public standardServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void GetPam(HttpServletRequest request,
			HttpServletResponse response,String table)
			throws NullPointerException, Exception {

		if (request.getParameter("currentPageNo") != null) {
			currentPageNo = Integer.parseInt(request
					.getParameter("currentPageNo"));

		}

		if (request.getParameter("pageSize") != null)
			pageSize = Integer.parseInt(request.getParameter("pageSize"));

		if (request.getParameter("id") != null)
			id = Integer.parseInt(request.getParameter("id"));

		if (request.getParameter("action") != null)
			action = request.getParameter("action");

		totalPage = csi.getpagecount(pageSize, table);

	}

	protected void doReturn(HttpServletRequest request,
			HttpServletResponse response, String path) throws ServletException,
			IOException {
		request.setAttribute("currentPageNo", currentPageNo);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected abstract void doSelect(AdminServiceImp adsi, int pageSize, int currentPageNo)throws Exception;

	protected abstract void doUpdate(HttpServletRequest request, AdminServiceImp adsi)throws Exception;

	protected abstract void doDelete(ArrayList<?> currentPage, int id,
			HttpServletRequest request)throws Exception;

	protected abstract void doInsert(AdminServiceImp adsi, HttpServletRequest request)throws Exception;

	public void init() throws ServletException {
		// Put your code here
	}

}
