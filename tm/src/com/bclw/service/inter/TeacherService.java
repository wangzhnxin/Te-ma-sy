package com.bclw.service.inter;

import java.util.ArrayList;

import com.bclw.bean.Arrangement;
import com.bclw.bean.Cware;
import com.bclw.bean.Tcotext;
import com.bclw.bean.Teacher;

public interface TeacherService {
	public ArrayList<Arrangement> getAllArrangementByTid(int Pagesize, int Pageno,String tid);//按查询单个老师的所有课程安排
	public ArrayList<Arrangement> getAllArrangementByTid(int Pagesize, int Pageno,String tid,String acaid);//按年份查询单个老师的课程安排
	public ArrayList<Arrangement> getAllArrangementByTname(int Pagesize, int Pageno,String tname,String acaid);//按名字和年份查询老师课程安排(模糊搜索)
	public boolean cwareadd(Cware t);//添加课件
	public boolean cwaredel(Cware t);//删除课件
	public boolean cwareupdate(Cware t);//修改课件名称
	public ArrayList<Cware> getAllCware(int Pagesize, int Pageno,int tcid);//获取当前课件的课表
	public boolean tcotextadd(Tcotext t);//添加教学内容
	public boolean tcotextdel(Tcotext t);//删除教学内容(只能删除自己的)
	public boolean tcotextupdate(Tcotext t);
	public ArrayList<Tcotext> getAllTcotextbytid(int Pagesize, int Pageno,int arid);//根据教师获取上课内容
	public Tcotext findTcid(int tcid);//查找上课内容
	public Teacher findLikeUser(String uname);//模糊查找用户
}
