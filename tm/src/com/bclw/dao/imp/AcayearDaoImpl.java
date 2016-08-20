package com.bclw.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bclw.dao.inter.*;
import com.bclw.bean.*;

public class AcayearDaoImpl extends AbstractDao implements Dm<Acayear> {
	String sql;

	public AcayearDaoImpl() {

	}

	@Override
	public boolean add(Acayear acayear) {
		// TODO Auto-generated method stub
		try {
			sql = "insert into acayear(acaname,acaexp) values(?,?)";
			Object[] objs = new Object[] { acayear.getAcaname(),
					acayear.getAcaexp() };
			return super.sqlExecute2(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}  catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Acayear acayear) {
		// TODO Auto-generated method stub
		try {
			sql = "delete from acayear where acaid=" + acayear.getAcaid();
			Object[] objs = new Object[] {};
			return super.sqlExecute2(sql, objs);
		}  catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Acayear acayear){
		// TODO Auto-generated method stub
		try {
			sql = "update acayear set acaname=?,acaexp=? where acaid="
					+ acayear.getAcaid();
			Object[] objs = new Object[] { acayear.getAcaname(),
					acayear.getAcaexp() };
			return super.sqlExecute2(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}  catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			//这里写数据库关闭方法
		}
	}
	public ArrayList<Acayear> getAllAcayear(int Pagesize, int Pageno) {
		// TODO Auto-generated method stub
		ArrayList<Acayear> acayear = null;
		try{
		sql = "SELECT * FROM(SELECT TOP "+(Pagesize*Pageno)+" ROW_NUMBER() OVER(ORDER BY acaid ASC) AS ROWID,* FROM acayear) AS TEMP1 WHERE ROWID>"+((Pageno-1)*Pagesize);
/*		sql="SELECT * FROM acayear ORDER BY acaid asc OFFSET "+(Pageno-1)+" ROWS FETCH NEXT "+Pagesize+" ROWS ONLY";*/
		System.out.println(sql);
		Object[] objs = new Object[] {};
		acayear=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
/*		return acayear;*/
		return acayear;
	}
	public ArrayList<Acayear> getAllAcayear() {
		// TODO Auto-generated method stub
		ArrayList<Acayear> acayear = null;
		try{
		sql = "SELECT * from Acayear";
		System.out.println(sql);
		Object[] objs = new Object[] {};
		acayear=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return acayear;
	}
	@SuppressWarnings("finally")
	public Acayear findAcaname(String acaname){//检测学年名称是否存在
		sql="select * from acayear where acaname='"+acaname+'\'';
		Object[] objs = new Object[] {};
		Acayear acayear=new Acayear();
		try {
			acayear=(Acayear) super.sqlExecute3(sql, objs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}finally{
		return acayear;
		}
	}

	@Override
	protected Object rowMapper(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Acayear acayear=new Acayear();
		acayear.setAcaid(rs.getInt("acaid"));
		acayear.setAcaname(rs.getString("acaname"));
		acayear.setAcaexp(rs.getString("acaexp"));
		return acayear;
	}

}
