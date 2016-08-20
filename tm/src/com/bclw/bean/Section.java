package com.bclw.bean;

import java.io.Serializable;
import java.util.Date;

public class Section implements Serializable{//课程安排
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5857941306796967632L;
	private int sid;//安排id
	private String sname;//安排名称
	private Date start;//开始时间
	private Date end;//结束时间
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Section(String sname, Date start, Date end) {
		super();
		this.sname = sname;
		this.start = start;
		this.end = end;
	}
	public Section(int sid, String sname, Date start, Date end) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.start = start;
		this.end = end;
	}
	public Section() {
		// TODO Auto-generated constructor stub
	}

}
