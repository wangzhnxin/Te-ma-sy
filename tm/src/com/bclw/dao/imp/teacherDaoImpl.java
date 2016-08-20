package com.bclw.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bclw.bean.Teacher;
import com.bclw.dao.inter.Dm;

public class teacherDaoImpl extends AbstractDao implements Dm<Teacher> {
	private String sql;

	@Override
	public boolean add(Teacher t) {
		sql = "insert into teacher(tname,uname,texp,upass) values(?,?,?,?)";
		Object[] objs = new Object[] { t.getTname(), t.getUname(), t.getTexp(),
				t.getUpass(), };
		try {
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
	public boolean delete(Teacher t) {
		sql = "delete from teacher where tid=?";
		Object[] objs = new Object[] { t.getTid() };
		try {
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
	public boolean update(Teacher t) {
		sql = "update teacher set tname=?,uname=?,texp=?,upass=? where tid=?";
		Object[] objs = new Object[] { t.getTname(), t.getUname(), t.getTexp(),
				t.getUpass(),t.getTid() };
		try {
			return super.sqlExecute2(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<Teacher> getAllTeacher(int Pagesize, int Pageno) {
		// TODO Auto-generated method stub
		ArrayList<Teacher> teacher=null;
		try{
		sql = "SELECT * FROM(SELECT TOP "+(Pagesize*Pageno)+" ROW_NUMBER() OVER(ORDER BY tid ASC) AS ROWID,* FROM teacher) AS TEMP1 WHERE ROWID>"+((Pageno-1)*Pagesize);
		System.out.println(sql);
		Object[] objs = new Object[] {};
		teacher=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return teacher;
	}
	public ArrayList<Teacher> getAllTeacher() {
		// TODO Auto-generated method stub
		ArrayList<Teacher> teacher=null;
		try{
		sql = "SELECT * FROM teacher";
		System.out.println(sql);
		Object[] objs = new Object[] {};
		teacher=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return teacher;
	}
	@SuppressWarnings("finally")
	public Teacher findUser(String uname){//检测用户是否存在，和密码
		sql="select * from teacher where uname='"+uname+'\'';
		Object[] objs = new Object[] {};
		Teacher tc=new Teacher();
		try {
			tc=(Teacher)super.sqlExecute3(sql, objs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}finally{
		return tc;
		}
	}
	@SuppressWarnings("finally")
	public Teacher findLikeUser(String uname){//模糊查找用户
		sql="select * from teacher where uname like'%"+uname+"'%'";
		Object[] objs = new Object[] {};
		Teacher tc=new Teacher();
		try {
			tc=(Teacher)super.sqlExecute3(sql, objs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}finally{
		return tc;
		}
	}
	

	@Override
	protected Object rowMapper(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
/*		return null;*/
		Teacher teacher=new Teacher();
		teacher.setTid(rs.getInt("tid"));
		teacher.setTname(rs.getString("tname"));
		teacher.setUname(rs.getString("uname"));
		teacher.setUpass(rs.getString("upass"));
		teacher.setTexp(rs.getString("texp"));
		return teacher;
	}

}
