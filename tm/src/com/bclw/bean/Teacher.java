package com.bclw.bean;



public class Teacher {//教师
	/**
	 * 
	 */
	private int tid;//教师id
	private String uname;//教师用户名
	private String upass;//教师密码
	private String tname;//教师名
	private String texp;//教师说明
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
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
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTexp() {
		return texp;
	}
	public void setTexp(String texp) {
		this.texp = texp;
	}
	public Teacher(String uname, String upass) {
		super();
		this.uname = uname;
		this.upass = upass;
	}
	public Teacher(int tid, String uname, String upass) {
		super();
		this.tid = tid;
		this.uname = uname;
		this.upass = upass;
	}
	public Teacher(String uname, String upass, String tname, String texp) {
		super();
		this.uname = uname;
		this.upass = upass;
		this.tname = tname;
		this.texp = texp;
	}
	public Teacher(int tid, String uname, String upass, String tname,
			String texp) {
		super();
		this.tid = tid;
		this.uname = uname;
		this.upass = upass;
		this.tname = tname;
		this.texp = texp;
	}
	public Teacher() {
		// TODO Auto-generated constructor stub
	}
	
}
