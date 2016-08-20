package com.bclw.bean;

import java.util.Date;

public class Tcotext {
	private int tcid;//编号
	private String tctitle;//上课主题
	private  Date tcdate;//课程时间
	private String tcexp;//课程内容
	private int arid;//课程安排id
	private int tid;//教师id
	private int pid;//专业id
	public int getTcid() {
		return tcid;
	}
	public void setTcid(int tcid) {
		this.tcid = tcid;
	}
	public Date getTcdate() {
		return tcdate;
	}
	public void setTcdate(Date tcdate) {
		this.tcdate = tcdate;
	}
	public String getTcexp() {
		return tcexp;
	}
	public void setTcexp(String tcexp) {
		this.tcexp = tcexp;
	}
	public int getArid() {
		return arid;
	}
	public void setArid(int arid) {
		this.arid = arid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public Tcotext() {
	}
	public Tcotext(String tctitle,Date tcdate, String tcexp, int arid/*, int tid,int pid*/) {
		this.tcdate = tcdate;
		this.tcexp = tcexp;
		this.arid = arid;
/*		this.tid = tid;*/
		this.tctitle=tctitle;
/*		this.pid=pid;*/
	}
	public Tcotext(int tcid,String tctitle, Date tcdate, String tcexp, int arid/*, int tid,int pid*/) {
		this(tctitle,tcdate,tcexp,arid/*,tid,pid*/);
		setTcid(tcid);
	}
	public String getTctitle() {
		return tctitle;
	}
	public void setTctitle(String tctitle) {
		this.tctitle = tctitle;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}	
}
