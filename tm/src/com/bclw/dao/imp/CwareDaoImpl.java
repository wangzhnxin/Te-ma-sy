package com.bclw.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bclw.bean.Cware;
import com.bclw.dao.inter.Dm;

public class CwareDaoImpl extends AbstractDao implements Dm<Cware>{
	private String sql;
	@Override
	public boolean add(Cware t) {
		// TODO Auto-generated method stub
		try {
			sql = "insert into cware(cwname,addr,tcid) values(?,?,?)";
			Object[] objs = new Object[] { t.getCwname(),t.getAddr(),t.getTcid() };
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
	public boolean delete(Cware t) {
		// TODO Auto-generated method stub
		try {
			sql = "delete from cware where cwid=" + t.getCwid();
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
	public ArrayList<Cware> getAllCware(int Pagesize, int Pageno,int tcid) {//获取当前课件的课表
		// TODO Auto-generated method stub
		ArrayList<Cware> cware = null;
		try{
		sql = "SELECT * FROM(SELECT TOP "+(Pagesize*Pageno)+" ROW_NUMBER() OVER(ORDER BY cwid ASC) AS ROWID,* FROM cware where tcid="+tcid+") AS TEMP1 WHERE ROWID>"+((Pageno-1)*Pagesize);
		System.out.println(sql);
		Object[] objs = new Object[] {};
		cware=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return cware;
	}

	@Override
	public boolean update(Cware t) {//修改课件名称
		// TODO Auto-generated method stub
		try {
			sql = "update cware set cwname=? where cwid="
					+ t.getCwid();
			Object[] objs = new Object[] {t.getCwname()};
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
	protected Object rowMapper(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Cware cware=new Cware();
		cware.setTcid(rs.getInt("tcid"));
		cware.setCwname(rs.getString("cwname"));
		cware.setAddr(rs.getString("addr"));
		cware.setCwid(rs.getInt("cwid"));
		return cware;
	}

}
