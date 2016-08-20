package com.bclw.bean;

import java.io.Serializable;

public class Course  implements Serializable{//课程
/**
	 * 
	 */
	private static final long	serialVersionUID	= -4426261749766682661L;
private int coid;//课程id
private String coname;//课程名称
private String coexp;//课程说明
public int getCoid() {
	return coid;
}
public void setCoid(int coid) {
	this.coid = coid;
}
public String getConame() {
	return coname;
}
public void setConame(String coname) {
	this.coname = coname;
}
public String getCoexp() {
	return coexp;
}
public void setCoexp(String coexp) {
	this.coexp = coexp;
}
public Course(String coname, String coexp) {
	super();
	setConame(coname);
	setCoexp(coexp);
}
public Course(int coid, String coname, String coexp) {
	this(coname,coexp);
	setCoid(coid);
}
public Course() {
	// TODO Auto-generated constructor stub
}
}
