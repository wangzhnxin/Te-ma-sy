package com.bclw.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractDao {
	private static Connection conn;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	Object obj = null;
	ArrayList<Object> objs;
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> sqlExecute(String sql, Object[] args)throws SQLException {//查询用
		try{
			objs=new ArrayList<Object>();
		conn= Db.getConn();//连接数据库
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject((i + 1), args[i]);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Object ob=(Object)this.rowMapper(rs);
				objs.add(ob);
			}
			return ((ArrayList<T>) objs);
		}finally{
			this.close(conn,ps, rs);
		}
	}
	public boolean sqlExecute2(String sql, Object[] args)throws SQLException {//增删改用
		try{
		 conn = Db.getConn();//连接数据库
		 /*conn.setAutoCommit(false);*/
		int line;
		System.out.println(sql);
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject((i + 1), args[i]);
			}
			 line=ps.executeUpdate();
			 System.out.print(line);
			if(line==0){
				conn.rollback();
				return false;
			}else{
				conn.commit();
				return true;
			}
		}finally{
			this.close(conn,ps, rs);
		}
			
	}
	public Object sqlExecute3(String sql, Object[] args)throws SQLException {//查询单个用

		try {
			// 获取连接
			 conn = Db.getConn();//连接数据库
			// 执行SQL语句
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject((i + 1), args[i]);
			}
			rs = ps.executeQuery();

			// 处理结果
			while (rs.next()) {
				obj = rowMapper(rs);
			}
		} finally {
			// 释放资源
			this.close(conn,ps, rs);
		}
		return obj;

	}
	public int sqlExecute4(String sql)throws SQLException {//分页查询总条数
		int sum=0;//条数统计
		try {
			// 获取连接
			 conn = Db.getConn();//连接数据库
			// 执行SQL语句
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
				sum=rs.getInt("sum");
				}
		} finally {
			// 释放资源
			this.close(conn,ps, rs);
		}
		return sum;

	}

	// 抽象方法，只能在其继承的子类中执行
	abstract protected Object rowMapper(ResultSet rs) throws SQLException;
	public static ResultSet getResultSet(){
		return rs;
	}
	public static  PreparedStatement getPs(){
		return ps;
	}
	public static Connection getConn(){
		return conn;
	}
	public void close(Connection conn, PreparedStatement st, ResultSet rs) {//关闭数据连接
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
	}
	
}
