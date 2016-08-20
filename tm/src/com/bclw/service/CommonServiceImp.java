package com.bclw.service;

import com.bclw.dao.imp.PageDaoImpl;

public class CommonServiceImp {
	PageDaoImpl pdi=new PageDaoImpl();
	@SuppressWarnings("finally")
	public int getrowcount(String table) {//总条数
		int sum=0;
		try{
		sum=pdi.getrowcount(table);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		return sum;
		}
	}
	@SuppressWarnings("finally")
	public int getpagecount(int pagesize,String table) throws Exception {//查自己总页数
		int sum=0;
		try{
		sum=pdi.getpagecount(pagesize, table);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		return sum;
		}
	}
	@SuppressWarnings("finally")
	public int getrowcount2(String table,String col,int tid) {//查自己总条数
		int sum=0;
		try{
		sum=pdi.getrowcount2(table,col,tid);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		return sum;
		}
	}
	@SuppressWarnings("finally")
	public int getpagecount2(int pagesize,String table,String col,int tid) throws Exception {//模糊查询其他老师总页数
		int sum=0;
		try{
		sum=pdi.getpagecount2(pagesize,table,col,tid);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		return sum;
		}
	}
	@SuppressWarnings("finally")
	public int getrowcount3(String col,String like) {//模糊查询其他老师总条数
		int sum=0;
		try{
		sum=pdi.getrowcount3(col,like);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		return sum;
		}
	}
	@SuppressWarnings("finally")
	public int getpagecount3(int pagesize,String col,String like) throws Exception {//总页数
		int sum=0;
		try{
		sum=pdi.getpagecount3(pagesize,col,like);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		return sum;
		}
	}
	@SuppressWarnings("finally")
	public int getrowcountbyTcotext(int arid) throws Exception {//教学内容总条数
		int sum=0;
		try{
		sum=pdi.getrowcountbyTcotext(arid);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		return sum;
		}
	}
	@SuppressWarnings("finally")
	public int getpagecountbyTcotext(int pagesize,int arid) throws Exception {//总页数
		int sum=0;
		try{
		sum=pdi.getpagecountbyTcotext(pagesize, arid);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		return sum;
		}
	}
	@SuppressWarnings("finally")
	public int getrowcountbyCware(int tcid) throws Exception {//当前教学内容的课件总数
		int sum=0;
		try{
		sum=pdi.getrowcountbyCware(tcid);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		return sum;
		}
	}
	@SuppressWarnings("finally")
	public int getpagecountbyCware(int pagesize,int tcid) throws Exception {//当前教学内容总页数
		int sum=0;
		try{
		sum=pdi.getpagecountbyCware(pagesize, tcid);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		return sum;
		}
	}
	
		
}
