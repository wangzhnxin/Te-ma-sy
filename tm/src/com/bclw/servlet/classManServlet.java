package com.bclw.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bclw.bean.Class;
import com.bclw.bean.Prof;
import com.bclw.service.AdminServiceImp;

public class classManServlet extends standardServlet {


	/**
	 * 
	 */
	private static final long	serialVersionUID	= -572631631516205999L;

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 给父类的字段赋值 没有获取到值的为默认值 默认当前页为1 页面大小为2
			GetPam(request, response, "Class");

			// 判断要对表执行的操作
			switch (action) {
				case "update": {
					doUpdate(request, adsi);
					doSelect(adsi, pageSize, currentPageNo);// 修改之后的页面
					ArrayList<Prof> profs;
					profs=adsi.getAllProf();
 					request.setAttribute("profs", profs);
				}
					break;
				case "insert": {
					doInsert(adsi, request);
					doSelect(adsi, pageSize, currentPageNo);
					GetPam(request, response, "Class");
					ArrayList<Prof> profs;
					profs=adsi.getAllProf();
 					request.setAttribute("profs", profs);
				}
					break;
				case "select": {
					doSelect(adsi, pageSize, currentPageNo);
					ArrayList<Prof> profs;
					profs=adsi.getAllProf();
 					request.setAttribute("profs", profs);
				}
					break;

				case "delete": {
					doSelect(adsi, pageSize, currentPageNo);
					doDelete(currentPage, id, request);
					totalPage= csi.getpagecount(pageSize, "Class");
					doSelect(adsi, pageSize, currentPageNo);
					ArrayList<Prof> profs;
					profs=adsi.getAllProf();
 					request.setAttribute("profs", profs);
				}
					break;
				// 跳转到编辑页面
				case "redirect": {

					String cname = request.getParameter("name");
					Class a = null;
					a = adsi.findCname(cname);
					request.setAttribute("OneData", a);
					ArrayList<Prof> profs;
					profs=adsi.getAllProf();
 					request.setAttribute("profs", profs);
					request.getRequestDispatcher(
							"/Admin/Update/classUpdate.jsp").forward(request,
							response);
				}
					return;
				default: {
					request.getRequestDispatcher("/erro/404.jsp").forward(
							request, response);
				}
			}
		} catch (Exception ex) {
			request.getRequestDispatcher("/erro/404.jsp").forward(request,
					response);
			ex.printStackTrace();
		}

		// 结尾必须有 此方用来法返回页面
		doReturn(request, response, "/Admin/classMan.jsp");
	}

	public void init() throws ServletException {
		// Put your code here
	}

	@Override
	protected
	void doSelect(AdminServiceImp adsi, int pageSize, int currentPageNo)
			throws Exception {
		// 获取当前页面
		currentPage = adsi.getAllClass(pageSize, currentPageNo);
		if (currentPageNo > 1 && currentPage.size() < 1) {
			currentPageNo = currentPageNo - 1;
			this.currentPageNo = currentPageNo;
			currentPage = adsi.getAllClass(pageSize, currentPageNo);
		}
	}

	@Override
	protected
	void doUpdate(HttpServletRequest request, AdminServiceImp adsi)
			throws Exception {

		String cname = request.getParameter("name");
		int pid = Integer.parseInt(request.getParameter("pid"));
		String cexp = request.getParameter("cexp");
		int cid = Integer.parseInt(request.getParameter("id"));
		
		

		Class a = null;
		a = new Class(cid, cname, cexp, pid) ;

		if (adsi.classupdate(a)) {
			request.setAttribute("msg", "更新成功！");
		}

		else
			request.setAttribute("msg", "更新失败！");

	}

	@Override
	protected
	void doDelete(ArrayList<?> currentPage, int id, HttpServletRequest request)
			throws Exception {
		if (currentPage != null)
			for (int i = 0; i < currentPage.size(); i++) {
				// 循环遍历当前页面找到与要删除对象一样ID的对象把他删除
				if ((Class) currentPage.get(i) != null) {
					Class a = (Class) currentPage.get(i);
					if (a.getCid() == id)
						if (!adsi.classdel(a))
							request.setAttribute("msg", "删除失败！");
				}
			}

	}

	@Override
	protected
	void doInsert(AdminServiceImp adsi, HttpServletRequest request)
			throws Exception {

		if (request.getParameter("name") != null) {

			String cname = request.getParameter("name");
			System.out.println("pid="+request.getParameter("pid"));
			int pid = Integer.parseInt(request.getParameter("pid"));
			String cexp = request.getParameter("cexp");

			if (adsi.classadd(new Class( cname, cexp, pid))) {
				request.setAttribute("msg", "插入成功！");
			} else {
				request.setAttribute("msg", "插入失败");
			}
		} else {
			request.setAttribute("msg", "插入失败");
		}
	}
}
