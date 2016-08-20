package com.bclw.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bclw.bean.Prof;

import com.bclw.service.AdminServiceImp;

public class profManServlet extends standardServlet {

	private static final long	serialVersionUID	= 6063551175329785880L;

	public profManServlet() {
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
			GetPam(request, response, "prof");
			
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
					GetPam(request, response, "Teacher");
				}
					break;
				case "select": {
					doSelect(adsi, pageSize, currentPageNo);
				}
					break;

				case "delete": {
					doSelect(adsi, pageSize, currentPageNo);
					doDelete(currentPage, id, request);
					totalPage= csi.getpagecount(pageSize, "prof");
					doSelect(adsi, pageSize, currentPageNo);
				}
					break;
				// 跳转到编辑页面
				case "redirect": {

					String pname = request.getParameter("name");
					Prof a = null;
					a = adsi.findPname(pname);
					request.setAttribute("OneData", a);
					request.getRequestDispatcher("/Admin/Update/profUpdate.jsp")
							.forward(request, response);
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
		doReturn(request, response, "/Admin/profMan.jsp");
	}

	public void init() throws ServletException {

	}

	@Override
	protected
	void doSelect(AdminServiceImp adsi, int pageSize, int currentPageNo)
			throws Exception {
		// 获取当前页面
		currentPage = adsi.getAllProf(pageSize, currentPageNo);
		if (currentPageNo>1 &&currentPage.size()<1) {
			currentPageNo=currentPageNo-1;
			this.currentPageNo = currentPageNo;
			currentPage = adsi.getAllProf(pageSize, currentPageNo);
		}
	}

	@Override
	protected
	void doUpdate(HttpServletRequest request, AdminServiceImp adsi)
			throws Exception {

		String pname = request.getParameter("name");
		String pexp = request.getParameter("pexp");
		int pid = Integer.parseInt(request.getParameter("id"));

		Prof a = null;
		a = new Prof(pid, pname, pexp);

		if (adsi.profupdate(a)) {
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
				if ((Prof) currentPage.get(i) != null) {
					Prof a = (Prof) currentPage.get(i);
					if (a.getPid() == id)
						if (!adsi.profdel(a))
							request.setAttribute("msg", "删除失败！");
				}
			}

	}

	@Override
	protected
	void doInsert(AdminServiceImp adsi, HttpServletRequest request)
			throws Exception {

		if (request.getParameter("name") != null) {

			String pname = request.getParameter("name");
			String pexp = request.getParameter("pexp");

			if (adsi.profadd(new Prof(pname, pexp))) {
				request.setAttribute("msg", "插入成功！");
			}
			else {
				request.setAttribute("msg", "插入失败");
			}

		} else {
			request.setAttribute("msg", "插入失败");
		}
	}

}
