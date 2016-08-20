package com.bclw.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bclw.bean.Teacher;
import com.bclw.service.AdminServiceImp;
import com.bclw.tool.MD5Util;

public class TeacherManServlet extends standardServlet {

	private static final long	serialVersionUID	= 2891640103984320679L;

	public TeacherManServlet() {
		super();
	}

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
			GetPam(request, response, "teacher");

			// 判断要对表执行的操作
			switch (action) {
				case "update": {
					doUpdate(request, adsi);
					doSelect(adsi, pageSize, currentPageNo);// 修改之后的页面
				}
					break;
				case "insert": {
					doInsert(adsi, request);
					doSelect(adsi, pageSize, currentPageNo);
					GetPam(request, response, "teacher");
				}
					break;
				case "select": {
					doSelect(adsi, pageSize, currentPageNo);
				}
					break;

				case "delete": {
					doSelect(adsi, pageSize, currentPageNo);
					doDelete(currentPage, id, request);
					totalPage= csi.getpagecount(pageSize, "teacher");
					doSelect(adsi, pageSize, currentPageNo);
				}
					break;
				// 跳转到编辑页面
				case "redirect": {

					String uname = request.getParameter("name");
					Teacher a = null;
					a = adsi.findteacheruser(uname);
					request.setAttribute("OneData", a);
					request.getRequestDispatcher(
							"/Admin/Update/teacherUpdate.jsp").forward(request,
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
		doReturn(request, response, "/Admin/teacherMan.jsp");
	}

	public void init() throws ServletException {

	}

	@Override
	protected
	void doSelect(AdminServiceImp adsi, int pageSize, int currentPageNo)
			throws Exception {
		// 获取当前页面
		currentPage = adsi.getAllTeacher(pageSize, currentPageNo);
		if (currentPageNo > 1 && currentPage.size() < 1) {
			currentPageNo = currentPageNo - 1;
			this.currentPageNo = currentPageNo;
			currentPage = adsi.getAllTeacher(pageSize, currentPageNo);
		}
	}

	@Override
	protected
	void doUpdate(HttpServletRequest request, AdminServiceImp adsi)
			throws Exception {

		String uname = request.getParameter("name");
		String tname = request.getParameter("tname");
		String upass = request.getParameter("upass");
		String texp = request.getParameter("texp");
		int tid = Integer.parseInt(request.getParameter("id"));

		Teacher a = null;
		a = new Teacher(tid, uname, upass, tname, texp);

		if (adsi.teacherupdate(a)) {
			/* request.setAttribute("data", adsi.findAcaname(acaname)); */
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
				if ((Teacher) currentPage.get(i) != null) {
					Teacher a = (Teacher) currentPage.get(i);
					if (a.getTid() == id)
						if (!adsi.teacherdel(a))
							request.setAttribute("msg", "删除失败！");
				}
			}

	}

	@Override
	protected
	void doInsert(AdminServiceImp adsi, HttpServletRequest request)
			throws Exception {

		if (request.getParameter("name") != null
				&& request.getParameter("texp") != null
				&& request.getParameter("upass") != null
				&& request.getParameter("tname") != null) {

			String uname = request.getParameter("name");
			String texp = request.getParameter("texp");
			String upass = request.getParameter("upass");
			String tname = request.getParameter("tname");
			upass = MD5Util.MD5(upass);

			if (adsi.teacheradd(new Teacher(uname, upass, tname, texp))) {
				request.setAttribute("msg", "插入成功！");
			} else {
				request.setAttribute("msg", "插入失败");
			}
		} else {
			request.setAttribute("msg", "插入失败");
		}
	}
}
