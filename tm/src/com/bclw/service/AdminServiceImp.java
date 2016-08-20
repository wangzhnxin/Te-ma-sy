package com.bclw.service;

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
import com.bclw.dao.imp.AcayearDaoImpl;
import com.bclw.dao.imp.AdminDaoImpl;
import com.bclw.dao.imp.ClassDaoImpl;
import com.bclw.dao.imp.ProfDaoImpl;
import com.bclw.dao.imp.arrangementDaoImpl;
import com.bclw.dao.imp.courseDaoImpl;
import com.bclw.dao.imp.sectionDaoImpl;
import com.bclw.dao.imp.teacherDaoImpl;
import com.bclw.service.inter.AdminService;

public class AdminServiceImp implements AdminService{
	
	AcayearDaoImpl adi=new AcayearDaoImpl();
	public boolean acayearadd(Acayear acayear){//添加学年
		 return adi.add(acayear);
	}
	public boolean acayeardel(Acayear acayear){//删除学年
		return adi.delete(acayear);
	}
	public boolean acayearupdate(Acayear acayear){//修改学年
		return adi.update(acayear);
	}
	public ArrayList<Acayear> getAllAcayear(int Pagesize, int Pageno){//查询所有的学年分页
		return adi.getAllAcayear(Pagesize, Pageno);
	}
	public ArrayList<Acayear> getAllAcayear(){//查询所有的学年
		return adi.getAllAcayear();
	}
	public Acayear findAcaname(String acaname){//查询学年名称是否存在
		return adi.findAcaname(acaname);
	}
	
	
	AdminDaoImpl addi=new AdminDaoImpl();
	public Admin findadminuser(String uname){//检测用户是否存在，和密码
		return addi.findUser(uname);
	}
	public boolean adminupdate(Admin a) {//修改管理员密码
	return addi.update(a);
	}
	
	ClassDaoImpl cdi=new ClassDaoImpl();
	public boolean classadd(Class c) {//添加班级
		return cdi.add(c);
	}
	public boolean classdel(Class c) {//删除班级
		return cdi.delete(c);
	}
	public boolean classupdate(Class c) {
		return cdi.update(c);
	}
	public ArrayList<Class> getAllClass(int Pagesize, int Pageno) {//查询所有班级分页
		return cdi.getAllClass(Pagesize, Pageno);
	}
	public ArrayList<Class> getAllClass() {//查询所有班级
		return cdi.getAllClass();
	}
	public Class findCname(String cname){//检测班级名称是否存在
		return cdi.findCname(cname);
	}
	courseDaoImpl codi=new courseDaoImpl();
	public boolean courseadd(Course t) {//添加课程
		return codi.add(t);
	}
	public boolean coursedel(Course t) {//删除课程
		return codi.delete(t);
	}
	public boolean courseupdate(Course t) {//修改课程
		return codi.update(t);
	}
	public ArrayList<Course> getAllCourse(int Pagesize, int Pageno) {//查询所有课程分页
		return codi.getAllCourse(Pagesize, Pageno);
	}
	public ArrayList<Course> getAllCourse() {//查询所有课程
		return codi.getAllCourse();
	}
	public Course findConame(String coname){//检测课程名称是否存在
		return codi.findConame(coname);
	}
	
	
	ProfDaoImpl pdi=new ProfDaoImpl();
	public boolean profadd(Prof prof) {//添加专业
		return pdi.add(prof);
	}
	public boolean profdel(Prof prof) {//删除专业
		return pdi.delete(prof);
	}
	public boolean profupdate(Prof prof) {//修改专业
		return pdi.update(prof);
	}
	public ArrayList<Prof> getAllProf(int Pagesize, int Pageno) {//查询所有专业分页
		return pdi.getAllProf(Pagesize, Pageno);
	}
	public ArrayList<Prof> getAllProf() {//查询所有专业
		return pdi.getAllProf();
	}
	public Prof findPname(String pname){//检测专业名称是否存在
		return pdi.findPname(pname);
	}
	
	sectionDaoImpl sdi=new sectionDaoImpl();
	public boolean sectionadd(Section t) {//添加课程时间安排
		return sdi.add(t);
	}
	public boolean sectiondel(Section t) {//删除课程时间安排
		return sdi.delete(t);
	}
	public boolean sectionupdate(Section t) {//修改课程时间安排
		return sdi.update(t);
	}
	public ArrayList<Section> getAllSection(int Pagesize, int Pageno) {//查询所有安排分页
		return sdi.getAllSection(Pagesize, Pageno);
	}
	public ArrayList<Section> getAllSection() {//查询所有安排
		return sdi.getAllSection();
	}
	public Section findSname(String sname){//检测安排名称是否存在
		return sdi.findSname(sname);
	}
	teacherDaoImpl tdi=new teacherDaoImpl();
	public boolean teacheradd(Teacher t) {//添加老师
		return tdi.add(t);
	}
	public boolean teacherdel(Teacher t) {//删除老师
		return tdi.delete(t);
	}
	public boolean teacherupdate(Teacher t) {//修改老师信息
		return tdi.update(t);
	}
	public ArrayList<Teacher> getAllTeacher(int Pagesize, int Pageno) {//查询所有老师
		return tdi.getAllTeacher(Pagesize, Pageno);
	}
	public ArrayList<Teacher> getAllTeacher() {//查询所有老师
		return tdi.getAllTeacher();
	}
	public Teacher findteacheruser(String uname){//检测老师用户是否存在，和密码
		return tdi.findUser(uname);
	}
	arrangementDaoImpl ardi=new arrangementDaoImpl();
	public boolean agtadd(Arrangement t) {//添加课程安排
		return ardi.add(t);
	}
	public boolean agtdel(Arrangement t) {//删除课程安排
		return ardi.delete(t);
	}
	public boolean agtupdate(Arrangement t) {//修改课程安排
		return ardi.update(t);
	}
	public ArrayList<Arrangement> getAllArrangement(int Pagesize, int Pageno,String cols) {//查询课程安排可选择不同的列排序
		return ardi.getAllArrangement(Pagesize, Pageno, cols);
	}
	public ArrayList<Arrangement> getAllArrangementByCid(int cid,Date week) {//按班级查询课程安排
		return ardi.getAllArrangementByCid(cid, week);
				
	}
	public ArrayList<Arrangement> getAllArrangementByTid(int Pagesize, int Pageno,String tid) {//查询单个老师的所有课程安排
	return ardi.getAllArrangementByTid(Pagesize, Pageno, tid);
	}
	public ArrayList<Arrangement> getAllArrangementByTid(int Pagesize, int Pageno,String tid,String acaid) {//按年份查询个人老师的课程安排
		return ardi.getAllArrangementByTid(Pagesize, Pageno, tid, acaid);
	}
}
