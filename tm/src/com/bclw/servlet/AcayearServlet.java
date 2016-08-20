package com.bclw.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bclw.bean.Acayear;
import com.bclw.service.AdminServiceImp;

public class AcayearServlet extends standardServlet {

	private static final long	serialVersionUID	= -8914628777547070027L;

	public AcayearServlet() {
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
			GetPam(request, response, "acayear");

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
					GetPam(request, response, "acayear");
				}
					break;
				case "select": {
					doSelect(adsi, pageSize, currentPageNo);
				}
					break;

				case "delete": {
					doSelect(adsi, pageSize, currentPageNo);
					doDelete(currentPage, id, request);
					totalPage= csi.getpagecount(pageSize, "acayear");
					doSelect(adsi, pageSize, currentPageNo);
				}
					break;
				// 跳转到编辑页面
				case "redirect": {

					String acaname = request.getParameter("name");
					Acayear a = null;
					a = adsi.findAcaname(acaname);
					request.setAttribute("OneData", a);
					request.getRequestDispatcher(
							"/Admin/Update/acayearUpdate.jsp").forward(request,
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
		doReturn(request, response, "/Admin/AcaYearMan.jsp");
	}

	public void init() throws ServletException {
		// Put your code here
	}

	@Override
	protected void doSelect(AdminServiceImp adsi, int pageSize, int currentPageNo)
			throws Exception {
		// 获取当前页面
		currentPage = adsi.getAllAcayear(pageSize, currentPageNo);
		if (currentPageNo > 1 && currentPage.size() < 1) {
			currentPageNo = currentPageNo - 1;
			this.currentPageNo = currentPageNo;
			currentPage = adsi.getAllAcayear(pageSize, currentPageNo);
		}
	}

	@Override
	protected
	void doUpdate(HttpServletRequest request, AdminServiceImp adsi)
			throws Exception {

		String acaname = request.getParameter("name");
		String acaexp = request.getParameter("acaexp");
		int acaid = Integer.parseInt(request.getParameter("id"));

		Acayear a = null;
		a = new Acayear(acaid, acaname, acaexp);

		if (adsi.acayearupdate(a)) {
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
				if ((Acayear) currentPage.get(i) != null) {
					Acayear a = (Acayear) currentPage.get(i);
					if (a.getAcaid() == id)
						if (!adsi.acayeardel(a))
							request.setAttribute("msg", "删除失败！");
				}
			}

	}

	@Override
	protected
	void doInsert(AdminServiceImp adsi, HttpServletRequest request)
			throws Exception {

		if (request.getParameter("name") != null) {

			String acaname = request.getParameter("name");
			String acaexp = request.getParameter("acaexp");

			if (adsi.acayearadd(new Acayear(acaname, acaexp))) {
				request.setAttribute("msg", "插入成功！");
			} else {
				request.setAttribute("msg", "插入失败");
			}
		} else {
			request.setAttribute("msg", "插入失败");
		}
	}
}
