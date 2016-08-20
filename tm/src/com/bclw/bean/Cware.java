package com.bclw.bean;

public class Cware {
	private int cwid;//课件id
	private String cwname;//课件名称
	private String addr;//下载路径
	private int tcid;//内容表id
	public int getCwid() {
		return cwid;
	}
	public void setCwid(int cwid) {
		this.cwid = cwid;
	}
	public String getCwname() {
		return cwname;
	}
	public void setCwname(String cwname) {
		this.cwname = cwname;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getTcid() {
		return tcid;
	}
	public void setTcid(int tcid) {
		this.tcid = tcid;
	}
	public Cware(){
	}
	public Cware(String cwname, String addr, int tcid) {
		this.cwname = cwname;
		this.addr = addr;
		this.tcid = tcid;
	}
	public Cware(int cwid, String cwname, String addr, int tcid) {
		this.cwid = cwid;
		this.cwname = cwname;
		this.addr = addr;
		this.tcid = tcid;
	}


}
