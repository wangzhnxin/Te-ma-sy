package com.bclw.service.inter;

import java.util.ArrayList;
import java.util.Date;

import com.bclw.bean.Acayear;
import com.bclw.bean.Admin;
import com.bclw.bean.Arrangement;
import com.bclw.bean.Class;
import com.bclw.bean.Course;
import com.bclw.bean.Prof;
import com.bclw.bean.Section;
import com.bclw.bean.Teacher;

public interface AdminService {
	public boolean acayearadd(Acayear acayear);//添加学年
	public boolean acayeardel(Acayear acayear);//删除学年
	public boolean acayearupdate(Acayear acayear);//修改学年
	public ArrayList<Acayear> getAllAcayear(int Pagesize, int Pageno);//查询所有的学年分页
	public ArrayList<Acayear> getAllAcayear();//查询所有的学年
	public Acayear findAcaname(String acaname);//查询学年名称是否存在
	public Admin findadminuser(String uname);//检测用户是否存在，和密码
	public boolean adminupdate(Admin a);//修改管理员密码
	public boolean classadd(Class c);//添加班级
	public boolean classdel(Class c);//删除班级
	public boolean classupdate(Class c);//增加班级
	public ArrayList<Class> getAllClass(int Pagesize, int Pageno);//查询所有班级分页
	public ArrayList<Class> getAllClass();//查询所有班级
	public Class findCname(String cname);//检测班级名称是否存在
	public boolean courseadd(Course t);//添加课程
	public boolean coursedel(Course t);//删除课程
	public boolean courseupdate(Course t);//修改课程
	public ArrayList<Course> getAllCourse(int Pagesize, int Pageno);//查询所有课程分页
	public ArrayList<Course> getAllCourse();//查询所有课程
	public Course findConame(String coname);//检测课程名称是否存在
	public boolean profadd(Prof prof);//添加专业
	public boolean profdel(Prof prof);//删除专业
	public boolean profupdate(Prof prof);//修改专业
	public ArrayList<Prof> getAllProf(int Pagesize, int Pageno);//查询所有专业分页
	public ArrayList<Prof> getAllProf();//查询所有专业
	public Prof findPname(String pname);//检测专业名称是否存在
	public boolean sectionadd(Section t);//添加课程时间安排
	public boolean sectiondel(Section t);//删除课程时间安排
	public boolean sectionupdate(Section t);//修改课程时间安排
	public ArrayList<Section> getAllSection(int Pagesize, int Pageno);//查询所有安排分页
	public ArrayList<Section> getAllSection();//查询所有安排
	public Section findSname(String sname);//检测安排名称是否存在
	public boolean teacheradd(Teacher t);//添加老师
	public boolean teacherdel(Teacher t);//删除老师
	public boolean teacherupdate(Teacher t);//修改老师信息
	public ArrayList<Teacher> getAllTeacher(int Pagesize, int Pageno);//查询所有老师
	public ArrayList<Teacher> getAllTeacher();//查询所有老师
	public Teacher findteacheruser(String uname);//检测老师用户是否存在，和密码
	public boolean agtadd(Arrangement t);//添加课程安排
	public boolean agtdel(Arrangement t);//删除课程安排
	public boolean agtupdate(Arrangement t);//修改课程安排
	public ArrayList<Arrangement> getAllArrangement(int Pagesize, int Pageno,String cols);//查询课程安排可选择不同的列排序
	public ArrayList<Arrangement> getAllArrangementByCid(int cid,Date week);//按班级查询课程安排
	public ArrayList<Arrangement> getAllArrangementByTid(int Pagesize, int Pageno,String tid);//查询单个老师的所有课程安排
	public ArrayList<Arrangement> getAllArrangementByTid(int Pagesize, int Pageno,String tid,String acaid);//按年份查询个人老师的课程安排
	
}
