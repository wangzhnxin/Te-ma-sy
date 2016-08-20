package com.bclw.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bclw.bean.Class;
import com.bclw.dao.inter.Dm;

public class ClassDaoImpl extends AbstractDao implements Dm<Class> {
	String sql;
	@Override
	public boolean add(Class c) {
		// TODO Auto-generated method stub
		try {
			sql = "insert into class(cname,cexp,pid) values(?,?,?)";
			Object[] objs = new Object[] { c.getCname(),c.getCexp(),c.getPid() };
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
	public boolean delete(Class c) {
		// TODO Auto-generated method stub
		try{
		sql = "delete from class where cid=" +c.getCid();
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
	public boolean update(Class c) {
		// TODO Auto-generated method stub
		try {
			sql = "update class set cname=?,cexp=?,pid=? where cid=" +c.getCid();
			Object[] objs = new Object[] {c.getCname(),c.getCexp(),c.getPid()};
			return super.sqlExecute2(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<Class> getAllClass(int Pagesize, int Pageno) {
		// TODO Auto-generated method stub
		ArrayList<Class> cls=null;
		try{
		sql =  "SELECT * FROM(SELECT TOP " +(Pagesize*Pageno)+ " ROW_NUMBER() OVER(ORDER BY cid ASC) AS ROWID,class.*,prof.pname FROM class left join prof on class.pid=prof.pid ) AS TEMP1 WHERE ROWID>"+((Pageno-1)*Pagesize);
		System.out.println(sql);
		Object[] objs = new Object[] {};
		cls=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return cls;
	}
	public ArrayList<Class> getAllClass() {
		// TODO Auto-generated method stub
		ArrayList<Class> cls=null;
		try{
		sql = "SELECT class.*,prof.pname from class left join prof on class.pid=prof.pid";
		System.out.println(sql);
		Object[] objs = new Object[] {};
		cls=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return cls;
	}
	@SuppressWarnings("finally")
	public Class findCname(String cname){//检测班级名称是否存在
		sql="SELECT class.*,prof.pname from class left join prof on class.pid=prof.pid where cname='"+cname+'\'';
		Object[] objs = new Object[] {};
		Class cls=new Class();
		try {
			cls=(Class)super.sqlExecute3(sql, objs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}finally{
		return cls;
		}
	}

	@Override
	protected Object rowMapper(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Class c=new Class();
		c.setCid(rs.getInt("cid"));
		c.setCname(rs.getString("cname"));
		c.setCexp(rs.getString("cexp"));
		c.setPid(rs.getInt("pid"));
		c.setPname(rs.getString("pname"));
		return c;
	}

}
