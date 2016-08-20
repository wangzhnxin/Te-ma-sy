package com.bclw.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bclw.bean.Course;
import com.bclw.service.AdminServiceImp;

public class courseManServlet extends standardServlet {

	
	
	private static final long	serialVersionUID	= 3041550428769915123L;

	public courseManServlet() {
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
			GetPam(request, response, "course");
			
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
					GetPam(request, response, "course");
				}
					break;
				case "select": {
					doSelect(adsi, pageSize, currentPageNo);
				}
					break;

				case "delete": {
					doSelect(adsi, pageSize, currentPageNo);
					doDelete(currentPage, id, request);
					totalPage= csi.getpagecount(pageSize, "course");
					doSelect(adsi, pageSize, currentPageNo);
				}
					break;
				// 跳转到编辑页面
				case "redirect": {

					String coname = request.getParameter("name");
					Course a = null;
					a = adsi.findConame(coname);
					request.setAttribute("OneData", a);
					request.getRequestDispatcher("/Admin/Update/courseUpdate.jsp")
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
		doReturn(request, response, "/Admin/courseMan.jsp");
	}

	public void init() throws ServletException {

	}

	@Override
	protected
	void doSelect(AdminServiceImp adsi, int pageSize, int currentPageNo)
			throws Exception {
		// 获取当前页面
		currentPage = adsi.getAllCourse(pageSize, currentPageNo);
		if (currentPageNo>1&& currentPage.size()<1) {
			currentPageNo=currentPageNo-1;
			this.currentPageNo = currentPageNo;
			currentPage = adsi.getAllCourse(pageSize, currentPageNo);
		}
	}

	@Override
	protected
	void doUpdate(HttpServletRequest request, AdminServiceImp adsi)
			throws Exception {

		String coname = request.getParameter("name");
		String coexp = request.getParameter("coexp");
		int coid = Integer.parseInt(request.getParameter("id"));

		Course a=null;
		a=new Course(coid, coname, coexp);

		if (adsi.courseupdate(a)) {
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
				if ((Course) currentPage.get(i) != null) {
					Course a = (Course) currentPage.get(i);
					if (a.getCoid() == id)
						if (!adsi.coursedel(a))
							request.setAttribute("msg", "删除失败！");
				}
			}

	}

	@Override
	protected
	void doInsert(AdminServiceImp adsi, HttpServletRequest request)
			throws Exception {

		if (request.getParameter("name") != null
				) {

			String coname = request.getParameter("name");
			String coexp = request.getParameter("coexp");

			if (adsi.courseadd(new Course(coname, coexp))) {
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
