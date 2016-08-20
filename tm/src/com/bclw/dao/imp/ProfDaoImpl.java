package com.bclw.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bclw.dao.inter.Dm;
import com.bclw.bean.*;

public class ProfDaoImpl extends AbstractDao implements Dm<Prof> {
	String sql;

	@Override
	public boolean add(Prof prof) {
		// TODO Auto-generated method stub
		try {
			sql = "insert into prof(pname,pexp) values(?,?)";
			Object[] objs = new Object[] { prof.getPname(), prof.getPexp() };
			return super.sqlExecute2(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Prof prof) {
		// TODO Auto-generated method stub
		try {
			sql = "delete from prof where pid=" + prof.getPid();
			Object[] objs = new Object[] {};
			return super.sqlExecute2(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Prof prof) {
		// TODO Auto-generated method stub
		try {
			sql = "update prof set pname=?,pexp=? where pid=" + prof.getPid();
			Object[] objs = new Object[] { prof.getPname(), prof.getPexp() };
			return super.sqlExecute2(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<Prof> getAllProf(int Pagesize, int Pageno) {
		// TODO Auto-generated method stub
		ArrayList<Prof> prof = null;
		try{
		sql = "SELECT * FROM(SELECT TOP "+(Pagesize*Pageno)+" ROW_NUMBER() OVER(ORDER BY pid ASC) AS ROWID,* FROM prof) AS TEMP1 WHERE ROWID>"+((Pageno-1)*Pagesize);
		System.out.println(sql);
		Object[] objs = new Object[] {};
		prof=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return prof;
	}
	public ArrayList<Prof> getAllProf() {
		// TODO Auto-generated method stub
		ArrayList<Prof> prof = null;
		try{
		sql = "SELECT * FROM prof";
		System.out.println(sql);
		Object[] objs = new Object[] {};
		prof=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return prof;
	}
	@SuppressWarnings("finally")
	public Prof findPname(String pname){//检测专业名称是否存在
		sql="select * from prof where pname='"+pname+'\'';
		Object[] objs = new Object[] {};
		Prof prof=new Prof();
		try {
			prof=(Prof)super.sqlExecute3(sql, objs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}finally{
		return prof;
		}
	}
	@Override
	protected Object rowMapper(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Prof prof = new Prof();
		prof.setPid(rs.getInt("pid"));
		prof.setPname(rs.getString("pname"));
		prof.setPexp(rs.getString("pexp"));
		return prof;
	}

}
