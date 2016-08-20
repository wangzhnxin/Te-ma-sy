package com.bclw.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.bclw.bean.Tcotext;
import com.bclw.dao.inter.Dm;

public class TcotextDaoImpl extends AbstractDao implements Dm<Tcotext>{
	private String sql;
	SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
	@Override
	public boolean add(Tcotext t) {
		// TODO Auto-generated method stub
		try {
			sql = "insert into tcotext(tctitle,tcdate,tcexp,arid) values(?,?,?,?)";
			System.out.println("sdf="+t.getTcdate());
			Object[] objs = new Object[] {t.getTctitle(),sdf.format(t.getTcdate()),t.getTcexp(),t.getArid() };
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
	public boolean delete(Tcotext t) {
		// TODO Auto-generated method stub
		try {
			sql = "delete from tcotext where tcid=" + t.getTcid();
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
	public boolean update(Tcotext t) {//修改的时候无法修改谁上传者。
		// TODO Auto-generated method stub
		try {
			sql = "update tcotext set tctitle=?,tcdate=?,tcexp=? where tcid="
					+ t.getTcid();
			Object[] objs = new Object[] {t.getTctitle(),t.getTcdate(),t.getTcexp()};
			return super.sqlExecute2(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}  catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<Tcotext> getAllTcotextbytid(int Pagesize, int Pageno,int arid) {
		// TODO Auto-generated method stub
		ArrayList<Tcotext> tco=null;
		try{
		sql =  "SELECT * FROM(SELECT TOP "+(Pagesize*Pageno)+" ROW_NUMBER() OVER(ORDER BY arid ASC) AS ROWID,* FROM tcotext where arid="+arid+" ) AS TEMP1 WHERE ROWID>"+((Pageno-1)*Pagesize);
		System.out.println(sql);
		Object[] objs = new Object[] {};
		tco=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return tco;
	}
	@SuppressWarnings("finally")
	public Tcotext findTcid(int tcid){//查找上课内容
		sql="select * from tcotext where tcid='"+tcid;
		System.out.print(sql);
		Object[] objs = new Object[] {};
		Tcotext tc=null;
		try {
			tc=(Tcotext)super.sqlExecute3(sql, objs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}finally{
		return tc;
		}
	}
	@Override
	protected Object rowMapper(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Tcotext tco=new Tcotext();
		tco.setArid(rs.getInt("arid"));
/*		tco.setPid(rs.getInt("pid"));*/
		tco.setTcdate(rs.getDate("tcdate"));
		tco.setTcexp(rs.getString("tcexp"));
		tco.setTcid(rs.getInt("tcid"));
		tco.setTctitle(rs.getString("tctitle"));
/*		tco.setTid(rs.getInt("tid"));*/
		return tco;
	}

}
