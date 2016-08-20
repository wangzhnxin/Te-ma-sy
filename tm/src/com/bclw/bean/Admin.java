package com.bclw.bean;

public class Admin {//管理员
private int aid;//管理员id
private String uname;//管理员用户名
private String upass;//管理员密码
public int getAid() {
	return aid;
}
public void setAid(int aid) {
	this.aid = aid;
}
public String getUname() {
	return uname;
}
public void setUname(String uname) {
	this.uname = uname;
}
public String getUpass() {
	return upass;
}
public void setUpass(String upass) {
	this.upass = upass;
}
public Admin(String uname, String upass) {
	super();
	setUname(uname);
	setUpass(upass);
}
public Admin(int aid, String uname, String upass) {
	this(uname,upass);
	setAid(aid);
}
public Admin() {
	// TODO Auto-generated constructor stub
}

}
