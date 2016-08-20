package com.bclw.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bclw.bean.Section;
import com.bclw.service.AdminServiceImp;

public class sectionManServlet extends standardServlet {

	private SimpleDateFormat	sdf					= new SimpleDateFormat(
															"HH:mm");

	private static final long	serialVersionUID	= 3290613260762279979L;

	public sectionManServlet() {
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
			GetPam(request, response, "section");

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
					GetPam(request, response, "section");
				}
					break;
				case "select": {
					doSelect(adsi, pageSize, currentPageNo);
				}
					break;

				case "delete": {
					doSelect(adsi, pageSize, currentPageNo);
					doDelete(currentPage, id, request);
					totalPage= csi.getpagecount(pageSize, "section");
					doSelect(adsi, pageSize, currentPageNo);
				}
					break;
				// 跳转到编辑页面
				case "redirect": {
					String sname = request.getParameter("name");
					Section a = null;
					a = adsi.findSname(sname);
					
					SimpleDateFormat sdf=new SimpleDateFormat("HH");
					request.setAttribute("id", a.getSid());
					request.setAttribute("name", a.getSname().substring(1,2));
					request.setAttribute("startHour", sdf.format(a.getStart()));
					request.setAttribute("endHour",sdf.format(a.getEnd()));
					sdf=new SimpleDateFormat("mm");
					request.setAttribute("startMinute",sdf.format(a.getStart()) );
					request.setAttribute("endMinute" ,sdf.format(a.getEnd()));
					
					request.getRequestDispatcher(
							"/Admin/Update/sectionUpdate.jsp").forward(request,
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
		doReturn(request, response, "/Admin/sectionMan.jsp");
	}

	public void init() throws ServletException {

	}

	@Override
	protected
	void doSelect(AdminServiceImp adsi, int pageSize, int currentPageNo)
			throws Exception {
		// 获取当前页面
		currentPage = adsi.getAllSection(pageSize, currentPageNo);
		if (currentPageNo > 1 && currentPage.size() < 1) {
			currentPageNo = currentPageNo - 1;
			this.currentPageNo = currentPageNo;
			currentPage = adsi.getAllSection(pageSize, currentPageNo);
		}
	}

	@Override
	protected
	void doUpdate(HttpServletRequest request, AdminServiceImp adsi)
			throws Exception {

		String sname = request.getParameter("name");
		String start = request.getParameter("startHour") + ":"
				+ request.getParameter("startMinute");
		String end = request.getParameter("endHour") + ":"
				+ request.getParameter("endMinute");
		int sid = Integer.parseInt(request.getParameter("id"));

		Section a = null;
		a = new Section(sid, sname, sdf.parse(start), sdf.parse(end));

		if (adsi.sectionupdate(a)) {
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
				if ((Section) currentPage.get(i) != null) {
					Section a = (Section) currentPage.get(i);
					if (a.getSid() == id)
						if (!adsi.sectiondel(a))
							request.setAttribute("msg", "删除失败！");
				}
			}

	}

	@Override
	protected
	void doInsert(AdminServiceImp adsi, HttpServletRequest request)
			throws Exception {

		String sname = request.getParameter("name");
		String start = request.getParameter("startHour") + ":"
				+ request.getParameter("startMinute");
		String end = request.getParameter("endHour") + ":"
				+ request.getParameter("endMinute");

		if (adsi.sectionadd(new Section(sname, sdf.parse(start), sdf.parse(end)))) {
			request.setAttribute("msg", "插入成功！");
		} else {
			request.setAttribute("msg", "插入失败");
		}

	}

}
