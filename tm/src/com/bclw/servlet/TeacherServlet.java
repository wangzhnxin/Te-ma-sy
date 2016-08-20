package com.bclw.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bclw.bean.Cware;
import com.bclw.bean.Tcotext;
import com.bclw.bean.Teacher;
import com.bclw.service.AdminServiceImp;
import com.bclw.service.CommonServiceImp;
import com.bclw.service.TeacherServiceImp;

public class TeacherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2705238028555275284L;

	private AdminServiceImp		adsi				= new AdminServiceImp();
	private CommonServiceImp	csi					= new CommonServiceImp();
	private String				action;										// 用户的动作
	private int					pageSize			= 2;						// 页面大小
	private int					totalPage;										// 总页数
	private int					currentPageNo		= 1;						// 当前页数
	private ArrayList<?>		currentPage;									// 当前页面
	private TeacherServiceImp	tsi					= new TeacherServiceImp();

	public TeacherServlet() {
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

		try {
			if (request.getParameter("action") != null)
				action = request.getParameter("action");
			System.out.println("action=" + action);
			// 判断要对表执行的操作
			switch (action) {

			// 查询教学内容
				case "selectTCotext": {

					doselectTCotext(request);
					request.getRequestDispatcher("/Teacher/TCotext.jsp")
							.forward(request, response);
				}
					break;
				// 删除教学内容
				case "delTCotext": {
					delTCotext(request);
					doselectTCotext(request);
					request.getRequestDispatcher("/Teacher/TCotext.jsp")
							.forward(request, response);
				}
					break;
				case "addTCotext": {
					addTCotext(request);
					doselectTCotext(request);
					request.getRequestDispatcher("/Teacher/TCotext.jsp")
							.forward(request, response);
				}
					break;
				// 查找其他老师课程安排
				case "selectOthers": {
					doSelectOthers(request, tsi, "arrangement");
					request.getRequestDispatcher("/Teacher/selectOthers.jsp")
							.forward(request, response);
				}
					break;
				// 查找自己的课程安排
				case "select": {
					
					doSelect(request, tsi, "arrangement");
					request.getRequestDispatcher("/Teacher/teacherMain.jsp")
							.forward(request, response);
				}
					break;
				// 删除课件
				case "delCware": {
					delCware(request);
					selectCware(request);
					request.getRequestDispatcher("/Teacher/Cware.jsp").forward(
							request, response);
				}
				break;
				// 查看课件
				case "selectCware": {
					selectCware(request);
					request.getRequestDispatcher("/Teacher/Cware.jsp").forward(
							request, response);
				}
					break;
				// 上传课件
				case "uploadFile": {
					boolean flag = uploadFile(request);
					request.setAttribute("flag", flag);
					selectCware(request);
					request.getRequestDispatcher("/Teacher/Cware.jsp").forward(
							request, response);
				}
					break;
				// 下载课件
				case "downloadFile": {
					downloadFile(request, response);
					request.getRequestDispatcher("/fortest.jsp").forward(
							request, response);
				}
					break;
				default: {
					System.out.println("default");
					request.getRequestDispatcher("/erro/404.jsp").forward(
							request, response);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			request.getRequestDispatcher("/erro/404.jsp").forward(request,
					response);
		} catch (Exception ex) {
			ex.printStackTrace();
			request.getRequestDispatcher("/erro/404.jsp").forward(request,
					response);
		}
	}

	// 删除课件
	private void delCware(HttpServletRequest request) throws Exception {
		int cwid = 0;
		String addr = null;
		String path = getServletContext().getRealPath("");

		if (request.getParameter("cwid") != null)
			cwid = Integer.parseInt(request.getParameter("cwid"));
		if (request.getParameter("addr") != null)
			addr = request.getParameter("addr");

		File f = new File(path + addr);
		if (f.exists()) 
			f.delete();
			
			Cware t = new Cware();
			t.setCwid(cwid);
			if (tsi.cwaredel(t))
				request.setAttribute("msg", "删除成功！");
			else
				request.setAttribute("msg", "删除失败！");
	
			

	}

	// 查看课件
	private void selectCware(HttpServletRequest request) throws Exception {
		int tcid = 0;
		currentPageNo = 1;
		currentPage = null;

		if (request.getParameter("tcid") != null)
			tcid = Integer.parseInt(request.getParameter("tcid"));
		if (request.getParameter("currentPageNo") != null)
			currentPageNo = Integer.parseInt(request
					.getParameter("currentPageNo"));

		totalPage = csi.getpagecountbyCware(pageSize, tcid);
		currentPage = tsi.getAllCware(pageSize, currentPageNo, tcid);

		if (currentPage.size() < 1 && currentPageNo > 1) {
			currentPageNo -= 1;
			currentPage = tsi.getAllCware(pageSize, currentPageNo, tcid);
		}

		request.setAttribute("tcid", tcid);
		request.setAttribute("currentPageNo", currentPageNo);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("currentPage", currentPage);
	}

/*	*//**
	 * 生成新文件名称（年月日时分秒+二位随机码）
	 * 
	 * @return
	 *//*
	public String getNewFileName() {
		String newFileName = "";
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		newFileName = simpleDateFormat.format(date);
		String random = getRandom(2);
		return newFileName + random;
	}

	*//**
	 * 获取n位随机数
	 * 
	 * @param n
	 * @return
	 *//*
	public String getRandom(int n) {
		String randomString = "";
		if (n == 1) {
			randomString = (int) (Math.random() * 10) + "";
		} else {
			randomString = getRandom(n - 1) + (int) (Math.random() * 10);
		}
		return randomString;
	}*/

	// 文件下载
	private void downloadFile(HttpServletRequest request,
			HttpServletResponse response) {
		String url = null;
		FileInputStream fis = null;
		ServletOutputStream out = null;

		try {
			if (request.getParameter("url") != null)
				url = request.getParameter("url");
			fis = new FileInputStream(url);
			out = response.getOutputStream();
			out.flush();
			int temp = 0;
			while ((temp = fis.read()) != -1 && fis != null) {
				out.write(temp);
			}
			out.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				fis.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	// 上传文件
	private boolean uploadFile(HttpServletRequest request) {
		boolean flag = false;
		String userName;
		int tcid = 0;
		Teacher t = (Teacher) request.getSession(false).getAttribute("teacher");
		userName = t.getUname();
		if (request.getParameter("tcid") != null)
			tcid = Integer.parseInt(request.getParameter("tcid"));
		System.out.println("tcid=" + tcid);
		String savePath = getServletContext().getRealPath("/UploadFiles")
				+ "\\" + userName;// 获取文件要保存的目录
		/* String tempPath = getServletContext().getRealPath("/UploadFiles"); */// 获取文件要临时保存的目录
		DiskFileItemFactory dfi = new DiskFileItemFactory();// 磁盘对象
		/* dfi.setRepository(new File(tempPath +"/")); */// 设置临时目录
		/* dfi.setSizeThreshold(1024 * 8); */// 8k的缓冲区,文件大于8K则保存到临时目录
		ServletFileUpload sfu = new ServletFileUpload(dfi);// 声明解析request的对象
		sfu.setHeaderEncoding("UTF-8"); // 处理文件名中文
		sfu.setFileSizeMax(1024 * 1024 * 5);// 设置每个文件最大为5M
		sfu.setSizeMax(1024 * 1024 * 10);// 一共最多能上传10M

		try {
			// 处理表单请求
			List<FileItem> itemList = sfu.parseRequest(request);
			for (FileItem fileItem : itemList) {
				// 对应表单中的控件的name
				String fieldName = fileItem.getFieldName();
				System.out.println("控件名称：" + fieldName);
				// 如果是普通表单控件
				if (fileItem.isFormField()) {
					String value = fileItem.getString();
					// 重新编码,解决乱码
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					System.out.println("普通内容：" + fieldName + "=" + value);
					// 上传文件
				} else {
					// 获得文件大小
					Long size = fileItem.getSize();
					// 获得文件名
					String fileName = fileItem.getName();
					System.out.println("文件名：" + fileName + "\t大小：" + size
							+ "byte");

					// 设置不允许上传的文件格式
					if (fileName.endsWith(".exe")) {
						request.setAttribute("msg", "不允许上传的类型！");
					} else {
						System.out.println(savePath);
						// 将文件保存到指定的路径
						File file = new File(savePath);
						if (!file.exists())
							file.mkdir();
						file = new File(savePath, fileName);
						fileItem.write(file);
						savePath = savePath
								.substring(savePath.indexOf("Up") - 1);
						Cware cw = new Cware(fileName, savePath + "\\"
								+ fileName, tcid);

						if (tsi.cwareadd(cw)) {
							request.setAttribute("url", savePath + "\\"
									+ fileName);
							request.setAttribute("msg", "上传成功！");
							flag = true;
						} else {
							flag = false;
						}
					}
				}
			}
		} catch (FileSizeLimitExceededException e) {
			request.setAttribute("msg", "文件太大");
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// 删除教学内容
	private void delTCotext(HttpServletRequest request) throws Exception {
		Tcotext t = new Tcotext();
		if (request.getParameter("tcid") != null)
			t.setTcid(Integer.parseInt(request.getParameter("tcid")));
		if (tsi.tcotextdel(t))
			request.setAttribute("msg", "删除成功！");
		else
			request.setAttribute("msg", "删除失败！");
	}

	// 添加教学内容
	private void addTCotext(HttpServletRequest request) throws Exception {
		String tctitle = null;
		String tcexp = null;
		Date tcdate = null;
		int arid = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (request.getParameter("arid") != null)
			arid = Integer.parseInt(request.getParameter("arid"));

		if (request.getParameter("tctitle") != null)
			tctitle = request.getParameter("tctitle");

		if (request.getParameter("tcexp") != null)
			tcexp = request.getParameter("tcexp");

		if (request.getParameter("tcdate") != null)
			tcdate = sdf.parse(request.getParameter("tcdate"));

		Tcotext t = new Tcotext(tctitle, tcdate, tcexp, arid);

		if (tsi.tcotextadd(t))
			request.setAttribute("msg", "添加成功！");
		else
			request.setAttribute("msg", "添加失败！");
	}

	// 教学内容内容查询
	private void doselectTCotext(HttpServletRequest request) throws Exception {
		int arid = 0;
		int tid=0;
		currentPage = null;
		currentPageNo = 1;

		if(request.getParameter("tid")!=null)
		{
			tid=Integer.parseInt(request.getParameter("tid"));
			System.out.println("tid="+tid);
			request.getSession(false).setAttribute("tid", tid);
		}
		if (request.getParameter("currentPageNo") != null) {
			currentPageNo = Integer.parseInt(request.getParameter("currentPageNo"));
		}

		if (request.getParameter("pageSize") != null)
			pageSize = Integer.parseInt(request.getParameter("pageSize"));

		if (request.getParameter("arid") != null)
			arid = Integer.parseInt(request.getParameter("arid"));

		totalPage = csi.getpagecountbyTcotext(pageSize, arid);

		currentPage = tsi.getAllTcotextbytid(pageSize, currentPageNo, arid);

		if (currentPageNo > 1 && currentPage.size() < 1) {
			currentPageNo = currentPageNo - 1;
			currentPage = tsi.getAllTcotextbytid(pageSize, currentPageNo, arid);
		}

		request.setAttribute("arid", arid);
		request.setAttribute("currentPageNo", currentPageNo);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("currentPage", currentPage);
	}

	// 其他教师课程安排查询
	private void doSelectOthers(HttpServletRequest request,
			TeacherServiceImp tsi, String table) throws NumberFormatException,
			Exception {
		String tname = null;
		currentPage = null;
		currentPageNo = 1;
		String acaid = null;

		if (request.getParameter("currentPageNo") != null) {
			currentPageNo = Integer.parseInt(request
					.getParameter("currentPageNo"));
		}

		if (request.getParameter("pageSize") != null)
			pageSize = Integer.parseInt(request.getParameter("pageSize"));

		if (request.getParameter("acaid") != null)
			acaid = request.getParameter("acaid");

		if (request.getParameter("tname") != null)
			// 去除tname中的空格
			tname = request.getParameter("tname").replace(" ", "");
		/*
		 * if (tsi.findLikeUser(tname) != null) { Teacher t;
		 * 
		 * t = tsi.findLikeUser(tname); }
		 */
		totalPage = csi.getpagecount3(pageSize, acaid, tname);

		currentPage = tsi.getAllArrangementByTname(pageSize, currentPageNo,
				tname, acaid);

		request.setAttribute("acaid", acaid);
		request.setAttribute("tname", tname);
		request.setAttribute("classes", adsi.getAllClass());
		request.setAttribute("sections", adsi.getAllSection());
		request.setAttribute("acayears", adsi.getAllAcayear());
		request.setAttribute("teachers", adsi.getAllTeacher());
		request.setAttribute("courses", adsi.getAllCourse());
		request.setAttribute("currentPageNo", currentPageNo);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("currentPage", currentPage);

	}

	// 自己课程安排容查询
	private void doSelect(HttpServletRequest request, TeacherServiceImp tsi,
			String table) throws Exception {
		currentPage = null;
		currentPageNo = 1;
		String tid = null;
		String acaid = null;

		Teacher t = (Teacher) request.getSession(false).getAttribute("teacher");
		if (request.getParameter("currentPageNo") != null) {
			currentPageNo = Integer.parseInt(request
					.getParameter("currentPageNo"));
		}
		if (request.getParameter("pageSize") != null)
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		if (request.getParameter("acaid") != null)
			acaid = request.getParameter("acaid");

		tid = String.valueOf(t.getTid());

		totalPage = csi.getpagecount2(pageSize, table, acaid,
				Integer.parseInt(tid));

		// 获取当前页面
		currentPage = tsi.getAllArrangementByTid(pageSize, currentPageNo, tid,
				acaid);

		request.setAttribute("acaid", acaid);
		request.setAttribute("classes", adsi.getAllClass());
		request.setAttribute("sections", adsi.getAllSection());
		request.setAttribute("acayears", adsi.getAllAcayear());
		request.setAttribute("teachers", adsi.getAllTeacher());
		request.setAttribute("courses", adsi.getAllCourse());
		request.setAttribute("currentPageNo", currentPageNo);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("currentPage", currentPage);
	}

	public void init() throws ServletException {

	}

}
