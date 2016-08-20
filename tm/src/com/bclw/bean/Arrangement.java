package com.bclw.bean;

import java.util.Date;

public class Arrangement {
private int acaid;//学年id
private int pid;//专业id
private int cid;//班级id
private int coid;//课程id
private int tid;//教师id
private int sid;//上课节次id
private Date week;//上课星期
private String aexp;//上课说明
private int arid;//课程安排id
private String acaname;
private String cname;
private String coname;
private String pname;
private String sname;
private String tname;

public int getAcaid() {
	return acaid;
}
public void setAcaid(int acaid) {
	this.acaid = acaid;
}
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public int getCoid() {
	return coid;
}
public void setCoid(int coid) {
	this.coid = coid;
}
public int getTid() {
	return tid;
}
public void setTid(int tid) {
	this.tid = tid;
}
public int getSid() {
	return sid;
}
public void setSid(int sid) {
	this.sid = sid;
}
public Date getWeek() {
	return week;
}
public void setWeek(Date week) {
	this.week = week;
}
public String getAexp() {
	return aexp;
}
public void setAexp(String aexp) {
	this.aexp = aexp;
}
public Arrangement(int acaid, int pid, int cid, int coid, int tid, int sid,
		Date week, String aexp) {
	this.acaid = acaid;
	this.pid = pid;
	this.cid = cid;
	this.coid = coid;
	this.tid = tid;
	this.sid = sid;
	this.week = week;
	this.aexp = aexp;
}
public Arrangement() {
	// TODO Auto-generated constructor stub
}
public int getArid() {
	return arid;
}
public void setArid(int arid) {
	this.arid = arid;
}
public String getAcaname() {
	return acaname;
}
public void setAcaname(String acaname) {
	this.acaname = acaname;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public String getConame() {
	return coname;
}
public void setConame(String coname) {
	this.coname = coname;
}
public String getPname() {
	return pname;
}
public void setPname(String pname) {
	this.pname = pname;
}
public String getSname() {
	return sname;
}
public void setSname(String sname) {
	this.sname = sname;
}
public String getTname() {
	return tname;
}
public void setTname(String tname) {
	this.tname = tname;
}
public Arrangement(int acaid, int pid, int cid, int coid, int tid, int sid,
		Date week, String aexp, int arid, String acaname, String cname,
		String coname, String pname, String sname, String tname) {
	this.acaid = acaid;
	this.pid = pid;
	this.cid = cid;
	this.coid = coid;
	this.tid = tid;
	this.sid = sid;
	this.week = week;
	this.aexp = aexp;
	this.arid = arid;
	this.acaname = acaname;
	this.cname = cname;
	this.coname = coname;
	this.pname = pname;
	this.sname = sname;
	this.tname = tname;
}

}
