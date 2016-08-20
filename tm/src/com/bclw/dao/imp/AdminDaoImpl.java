package com.bclw.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bclw.bean.Admin;

public class AdminDaoImpl extends AbstractDao {
	String sql;
	@SuppressWarnings("finally")
	public Admin findUser(String uname){//检测用户是否存在，和密码
		sql="select * from admin where uname='"+uname+'\'';
		System.out.print(sql);
		Object[] objs = new Object[] {};
		Admin admin=null;
		try {
			admin=(Admin)super.sqlExecute3(sql, objs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}finally{
		return admin;
		}
	}
	public boolean update(Admin a) {//修改密码
		sql = "update admin set uname=?,upass=? where aid=?";
		Object[] objs = new Object[] { a.getUname(),a.getUpass(),a.getAid()};
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
	protected Object rowMapper(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Admin admin=new Admin();
		admin.setAid(rs.getInt("aid"));
		admin.setUname(rs.getString("uname"));
		admin.setUpass(rs.getString("upass"));
		return admin;
	}

}
