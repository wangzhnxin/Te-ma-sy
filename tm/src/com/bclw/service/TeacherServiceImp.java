package com.bclw.service;

import java.util.ArrayList;

import com.bclw.bean.Arrangement;
import com.bclw.bean.Cware;
import com.bclw.bean.Tcotext;
import com.bclw.bean.Teacher;
import com.bclw.dao.imp.CwareDaoImpl;
import com.bclw.dao.imp.TcotextDaoImpl;
import com.bclw.dao.imp.arrangementDaoImpl;
import com.bclw.dao.imp.teacherDaoImpl;
import com.bclw.service.inter.TeacherService;

public class TeacherServiceImp implements TeacherService {
	arrangementDaoImpl ardi=new arrangementDaoImpl();
	public ArrayList<Arrangement> getAllArrangementByTid(int Pagesize, int Pageno,String tid) {//按查询单个老师的所有课程安排
	return ardi.getAllArrangementByTid(Pagesize, Pageno, tid);
	}
	public ArrayList<Arrangement> getAllArrangementByTid(int Pagesize, int Pageno,String tid,String acaid) {//按年份查询单个老师的课程安排
		return ardi.getAllArrangementByTid(Pagesize, Pageno, tid, acaid);
	}
	public ArrayList<Arrangement> getAllArrangementByTname(int Pagesize, int Pageno,String tname,String acaid){
		return ardi.getAllArrangementByTname(Pagesize, Pageno, tname, acaid);
	}

	
	
	CwareDaoImpl cdi=new CwareDaoImpl();
	public boolean cwareadd(Cware t) {//添加课件
		return cdi.add(t);
	}
	public boolean cwaredel(Cware t) {//删除课件
		return cdi.delete(t);
	}
	public boolean cwareupdate(Cware t) {//修改课件名称
		return cdi.update(t);
	}
	public ArrayList<Cware> getAllCware(int Pagesize, int Pageno,int tcid) {//获取当前课件的课表
		return cdi.getAllCware(Pagesize, Pageno, tcid);
	}
	TcotextDaoImpl tdi=new TcotextDaoImpl();
	public boolean tcotextadd(Tcotext t) {//添加教学内容
		return tdi.add(t);
	}
	public boolean tcotextdel(Tcotext t) {//删除教学内容(只能删除自己的)
	return tdi.delete(t);
	}
	public boolean tcotextupdate(Tcotext t) {
		return tdi.update(t);
	}
	public ArrayList<Tcotext> getAllTcotextbytid(int Pagesize, int Pageno,int arid) {
		return tdi.getAllTcotextbytid(Pagesize, Pageno, arid);
	}
	public Tcotext findTcid(int tcid){//查找上课内容
		return tdi.findTcid(tcid);
	}
	teacherDaoImpl teacher=new teacherDaoImpl();
	public Teacher findLikeUser(String uname){//模糊查找用户
		return teacher.findLikeUser(uname);
	}
}
