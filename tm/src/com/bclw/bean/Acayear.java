package com.bclw.bean;

import java.io.Serializable;

public class Acayear implements Serializable{//学年
/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
private int acaid;//学年id
private String acaname;//学年名称
private String acaexp;//学年说明
public int getAcaid() {
	return acaid;
}
public void setAcaid(int acaid) {
	this.acaid = acaid;
}
public String getAcaname() {
	return acaname;
}
public void setAcaname(String acaname) {
	this.acaname = acaname;
}
public String getAcaexp() {
	return acaexp;
}
public void setAcaexp(String acaexp) {
	this.acaexp = acaexp;
}
public Acayear(String acaname, String acaexp) {
	super();
	setAcaname(acaname);
	setAcaexp(acaexp);
}
public Acayear(int acaid, String acaname, String acaexp) {
	this(acaname,acaexp);
	setAcaid(acaid);
}
public Acayear() {
	// TODO Auto-generated constructor stub
}

}
