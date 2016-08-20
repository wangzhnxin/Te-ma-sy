package com.bclw.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.bclw.bean.Section;
import com.bclw.dao.inter.Dm;


public class sectionDaoImpl extends AbstractDao implements Dm<Section> {
	private String sql;
	SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm" );
	@Override
	public boolean add(Section t) {
		 sql = "insert into section(sname,start,endtime) values(?,?,?)";
		Object[] objs = null;
		objs = new Object[] { t.getSname(), sdf.format(t.getStart()),sdf.format(t.getEnd()) };
		try {
			return super.sqlExecute2(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Section t) {
		 sql = "delete from section where sid=?";
		Object[] objs = new Object[] { t.getSid() };
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
	public boolean update(Section t) {
		 sql = "update section set sname=?,start=?,endtime=? where sid=?";
		Object[] objs = null;
		objs = new Object[] { t.getSname(),sdf.format(t.getStart()),sdf.format(t.getEnd()),t.getSid() };
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
	public ArrayList<Section> getAllSection(int Pagesize, int Pageno) {
		// TODO Auto-generated method stub
		ArrayList<Section> section=null;
		try{
		sql = "SELECT * FROM(SELECT TOP "+(Pagesize*Pageno)+" ROW_NUMBER() OVER(ORDER BY sid ASC) AS ROWID,* FROM section) AS TEMP1 WHERE ROWID>"+((Pageno-1)*Pagesize);
		System.out.println(sql);
		Object[] objs = new Object[] {};
		section=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return section;
	}
	public ArrayList<Section> getAllSection() {
		// TODO Auto-generated method stub
		ArrayList<Section> section=null;
		try{
		sql = "SELECT * FROM section";
		System.out.println(sql);
		Object[] objs = new Object[] {};
		section=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return section;
	}
	@SuppressWarnings("finally")
	public Section findSname(String sname){//检测安排名称是否存在
		sql="select * from section where sname='"+sname+'\'';
		Object[] objs = new Object[] {};
		Section section=new Section();
		try {
			section=(Section)super.sqlExecute3(sql, objs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}finally{
		return section;
		}
	}
	@Override
	protected Object rowMapper(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Section section=new Section();
		section.setSid(rs.getInt("sid"));
		section.setSname(rs.getString("sname"));
		section.setStart(rs.getTime("start"));
		section.setEnd(rs.getTime("endtime"));
		return section;
	}

}
