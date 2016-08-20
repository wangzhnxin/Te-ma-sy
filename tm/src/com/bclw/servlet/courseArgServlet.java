package com.bclw.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bclw.bean.Arrangement;
import com.bclw.bean.Class;
import com.bclw.service.AdminServiceImp;

public class courseArgServlet extends HttpServlet {

	private static final long		serialVersionUID	= -2824015973491577043L;
	private SimpleDateFormat		sdf					= new SimpleDateFormat(
																"EEEE");
	private Date					week;
	private String					action				= null;
	private AdminServiceImp			adsi				= new AdminServiceImp();
	private ArrayList<Arrangement>	AList;
	private boolean					flag				= false;
	private int						sid;
	private int						tid;
	private int						coid;
	private int						cid;
	private int						acaid;
	private String					aexp;

	public courseArgServlet() {
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
			if (request.getParameter("action") != null) {
				action = request.getParameter("action");
				System.out.println("asaaaction=" + action);
			}

			// 判断要对表执行的操作
			switch (action) {
				case "update": {

				}
					break;
				case "insert": {
					doInsert(adsi, request);
					doSelect(adsi, request);
				}
					break;
				case "select": {
					doSelect(adsi, request);
				}
					break;

				case "delete": {
					System.out.println("dodelete");
					this.doDelete(request);
					doSelect(adsi, request);
				}
					break;
				// 跳转到编辑页面
				/*
				 * case "redirect": {
				 * 
				 * request.getRequestDispatcher(
				 * "/Admin/Update/classUpdate.jsp").forward(request, response);
				 * } return;
				 */
				default: {
					request.getRequestDispatcher("/erro/404.jsp").forward(
							request, response);
					return;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			request.getRequestDispatcher("/erro/404.jsp").forward(request,
					response);
		}
		request.getRequestDispatcher("/Admin/courseArg.jsp").forward(request,
				response);
	}

	public void init() throws ServletException {

	}

	void doSelect(AdminServiceImp adsi, HttpServletRequest request)
			throws Exception {
		if (request.getParameter("scid") != null
				&& request.getParameter("sweek") != null
				&& !request.getParameter("scid").equals("")
				&& !request.getParameter("sweek").equals("")) {
			System.out.println("not null");
			System.out.println(request.getParameter("scid").length());
			cid = Integer.parseInt(request.getParameter("scid"));

			/*
			 * week = sdf.parse(new
			 * String(request.getParameter("sweek").getBytes
			 * ("ISO-8859-1"),"utf-8"));
			 */

			week = sdf.parse(request.getParameter("sweek"));

			AList = adsi.getAllArrangementByCid(cid, week);

			if (AList.size() > 0) {
				request.setAttribute("AList", AList);
				flag = true;
			} else
				flag = false;

			System.out.println(request.getParameter("sweek"));

			request.setAttribute("scid", cid);
			request.setAttribute("sweek", request.getParameter("sweek"));
		} else
			flag = false;

		request.setAttribute("flag", flag);
		request.setAttribute("classes", adsi.getAllClass());
		request.setAttribute("sections", adsi.getAllSection());
		request.setAttribute("acayears", adsi.getAllAcayear());
		request.setAttribute("teachers", adsi.getAllTeacher());
		request.setAttribute("courses", adsi.getAllCourse());
	}

	void doUpdate(HttpServletRequest request, AdminServiceImp adsi)
			throws Exception {

		String cname = request.getParameter("name");
		int pid = Integer.parseInt(request.getParameter("pid"));
		String cexp = request.getParameter("cexp");
		int cid = Integer.parseInt(request.getParameter("id"));

		Class a = null;
		a = new Class(cid, cname, cexp, pid);

		if (adsi.classupdate(a)) {
			request.setAttribute("msg", "更新成功！");
		}

		else
			request.setAttribute("msg", "更新失败！");

	}

	void doDelete(HttpServletRequest request) throws Exception {
		int arid;
		if (request.getParameter("id") != null) {
			arid = Integer.parseInt(request.getParameter("id"));
			Arrangement a = new Arrangement();
			a.setArid(arid);
			if (adsi.agtdel(a))
				request.setAttribute("msg", "删除成功！");
			else
				request.setAttribute("msg", "删除失败");
		} else
			request.setAttribute("msg", "删除失败");

	}

	void doInsert(AdminServiceImp adsi, HttpServletRequest request)
			throws Exception {

		String cidAndPname = "";
		int pid;
		// 星期
		if (request.getParameter("week") != null) {
			/*
			 * week = sdf.parse(new
			 * String(request.getParameter("week").getBytes( "ISO-8859-1"),
			 * "utf-8"));
			 */
			week = sdf.parse(request.getParameter("week"));
		}
		// 说明
		if (request.getParameter("aexp") != null)
			aexp = request.getParameter("aexp");
		// 课程
		if (request.getParameter("coid") != null)
			coid = Integer.parseInt(request.getParameter("coid"));
		// 年份
		if (request.getParameter("acaid") != null)
			acaid = Integer.parseInt(request.getParameter("acaid"));
		// 节次
		if (request.getParameter("sid") != null)
			sid = Integer.parseInt(request.getParameter("sid"));
		// 班级和专业
		if (request.getParameter("cidAndPname") != null)
			cidAndPname = request.getParameter("cidAndPname");

		if (request.getParameter("tid") != null)
			tid = Integer.parseInt(request.getParameter("tid"));

		cid = Integer.parseInt(cidAndPname.split(",")[0]);
		pid = adsi.findPname(cidAndPname.split(",")[1]).getPid();

		if (adsi.agtadd(new Arrangement(acaid, pid, cid, coid, tid, sid, week,
				aexp))) {
			request.setAttribute("msg", "插入成功！");
		} else {
			request.setAttribute("msg", "插入失败");
		}
	}
}
