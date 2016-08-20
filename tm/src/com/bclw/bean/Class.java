package com.bclw.bean;

import java.io.Serializable;

public class Class implements Serializable{//班级
/**
	 * 
	 */
	private static final long	serialVersionUID	= -9011207136732278268L;
private int cid;//班级id
private String cname;//班级名称
private String cexp;//班级说明
private int pid;//所属专业
private String pname;//所属专业名字
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public String getCexp() {
	return cexp;
}
public void setCexp(String pexp) {
	this.cexp = pexp;
}
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public Class(String cname, String cexp, int pid) {
	super();
	setCname(cname);
	setCexp(cexp);
	setPid(pid);
}
public Class(int cid, String cname, String cexp, int pid) {
	this(cname,cexp,pid);
	setCid(cid);
}
public Class(String cname, String cexp, int pid,String pname) {
	super();
	setCname(cname);
	setCexp(cexp);
	setPid(pid);
	setPname(pname);
}
public Class(int cid, String cname, String cexp, int pid,String pname) {
	this(cname,cexp,pid);
	setCid(cid);
	setPname(pname);
}
public Class() {
	// TODO Auto-generated constructor stub
}
public String getPname() {
	return pname;
}
public void setPname(String pname) {
	this.pname = pname;
}

}
