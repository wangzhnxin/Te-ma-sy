package com.bclw.bean;

import java.io.Serializable;

public class Prof implements Serializable{//专业
/**
	 * 
	 */
	private static final long	serialVersionUID	= 2619669030378688736L;
private int pid;//专业id
private String pname;//专业名称
private String pexp;//专业说明
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public String getPname() {
	return pname;
}
public void setPname(String pname) {
	this.pname = pname;
}
public String getPexp() {
	return pexp;
}
public void setPexp(String pexp) {
	this.pexp = pexp;
}
public Prof(String pname, String pexp) {
	super();
	setPname(pname);
	setPexp(pexp);
}
public Prof(int pid, String pname, String pexp) {
	this(pname, pexp);
	setPid(pid);
}
public Prof() {
	// TODO Auto-generated constructor stub
}
}
