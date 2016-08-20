package com.bclw.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bclw.bean.Arrangement;
import com.bclw.dao.inter.Dm;

public class arrangementDaoImpl extends AbstractDao implements Dm<Arrangement> {
	String sql;
	SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
	// 添加课程安排
	@Override
	public boolean add(Arrangement t) {
		sql = "insert into arrangement(acaid,pid,cid,coid,tid,sid,_week,aexp) values(?,?,?,?,?,?,?,?)";
		Object[] objs = new Object[] { t.getAcaid(), t.getPid(), t.getCid(),
				t.getCoid(), t.getTid(), t.getSid(),sdf.format(t.getWeek()), t.getAexp() };
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

	// 删除课程安排
	@Override
	public boolean delete(Arrangement t) {
		sql = "delete from arrangement where arid=?";
		Object[] objs = new Object[] { t.getArid() };
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

	//
	@Override
	public boolean update(Arrangement t) {
		sql = "update arrangement set acaid=?,pid=?,cid=?,coid=?,tid=?,sid=?,_week=?,aexp=? where arid=?";
		Object[] objs = new Object[] { t.getAcaid(), t.getPid(), t.getCid(),
				t.getCoid(), t.getTid(), t.getSid(),sdf.format(t.getWeek()),t.getAexp(),t.getArid() };
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
	public ArrayList<Arrangement> getAllArrangement(int Pagesize, int Pageno,String cols) {
		// TODO Auto-generated method stub
		ArrayList<Arrangement> arrangement=null;
		try{
		sql = "SELECT * FROM(SELECT TOP "+(Pagesize*Pageno)+" ROW_NUMBER() OVER(ORDER BY "+cols+" ASC) AS ROWID,arrangement.*,acayear.acaname,class.cname,course.coname,prof.pname,section.sname,teacher.tname FROM acayear,class,course,prof,section,teacher,arrangement where acayear.acaid=arrangement.acaid and class.cid=arrangement.cid and course.coid=arrangement.coid and prof.pid=arrangement.pid and section.sid=arrangement.sid and teacher.tid=teacher.tid) AS TEMP1 WHERE  ROWID>"+((Pageno-1)*Pagesize);
		System.out.println(sql);
		Object[] objs = new Object[] {};
		arrangement=this.sqlExecute(sql, objs);
/*		while(rs.next()){
			Arrangement ag=(Arrangement) this.rowMapper(rs);
			arrangement.add(ag);
			
		}*/
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return arrangement;
	}
	public ArrayList<Arrangement> getAllArrangementByCid(int cid,Date week) {
		// TODO Auto-generated method stub
		ArrayList<Arrangement> arrangement=null;
		try{
		sql = "select * from (SELECT  arrangement.arid,arrangement.acaid,arrangement.pid,arrangement.cid,arrangement.coid,arrangement.tid,arrangement.sid,arrangement._week,arrangement.aexp aexp,acayear.acaname,class.cname,course.coname,prof.pname,section.sname FROM arrangement left join acayear on acayear.acaid=arrangement.acaid left join class on class.cid=arrangement.cid left join course on course.coid=arrangement.coid left join prof on prof.pid=arrangement.pid left join  section on  section.sid=arrangement.sid) as a left join  teacher  on  a.tid=teacher.tid where a.cid="+cid+" and _week='"+sdf.format(week)+"'";
		System.out.println(sql);
		Object[] objs = new Object[] {};
		arrangement=this.sqlExecute(sql, objs);
/*		while(rs.next()){
			Arrangement ag=(Arrangement) this.rowMapper(rs);
			arrangement.add(ag);
			
		}*/
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return arrangement;
	}
	public ArrayList<Arrangement> getAllArrangementByTid(int Pagesize, int Pageno,String tid) {
		// TODO Auto-generated method stub
		ArrayList<Arrangement> arrangement=null;
		try{
		sql = "select * from (SELECT top "+(Pagesize*Pageno)+" ROW_NUMBER() OVER(ORDER BY arrangement.arid ASC) AS ROWID,arrangement.arid,arrangement.acaid,arrangement.pid,arrangement.cid,arrangement.coid,arrangement.tid,arrangement.sid,arrangement._week,arrangement.aexp aexp,acayear.acaname,class.cname,course.coname,prof.pname,section.sname FROM arrangement left join acayear on acayear.acaid=arrangement.acaid left join class on class.cid=arrangement.cid left join course on course.coid=arrangement.coid left join prof on prof.pid=arrangement.pid left join section on section.sid=arrangement.sid) as a left join  teacher  on  a.tid=teacher.tid where ROWID>"+((Pageno-1)*Pagesize)+" and a.tid="+tid;
		System.out.println(sql);
		Object[] objs = new Object[] {};
		arrangement=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return arrangement;
	}
	public ArrayList<Arrangement> getAllArrangementByTid(int Pagesize, int Pageno,String tid,String acaid) {
		// TODO Auto-generated method stub
		ArrayList<Arrangement> arrangement=null;
		try{
			sql = "select * from(select top "+(Pagesize*Pageno)+" ROW_NUMBER() OVER(ORDER BY a.arid ASC) AS ROWID,a.*,teacher.tname from (SELECT  arrangement.arid,arrangement.acaid,arrangement.pid,arrangement.cid,arrangement.coid,arrangement.tid,arrangement.sid,arrangement._week,arrangement.aexp aexp,acayear.acaname,class.cname,course.coname,prof.pname,section.sname FROM arrangement left join acayear on acayear.acaid=arrangement.acaid left join class on class.cid=arrangement.cid left join course on course.coid=arrangement.coid left join prof on prof.pid=arrangement.pid left join  section on  section.sid=arrangement.sid) as a left join  teacher  on  a.tid=teacher.tid where  a.tid="+tid+" and acaid="+acaid+") as TEMP1 where ROWID>"+((Pageno-1)*Pagesize);
		System.out.println(sql);
		Object[] objs = new Object[] {};
		arrangement=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return arrangement;
	}
	public ArrayList<Arrangement> getAllArrangementByTname(int Pagesize, int Pageno,String tname,String acaid) {
		// TODO Auto-generated method stub
		ArrayList<Arrangement> arrangement=null;
		try{
			sql = "select * from(select top "+(Pagesize*Pageno)+" ROW_NUMBER() OVER(ORDER BY a.arid ASC) AS ROWID,a.*,teacher.tname from (SELECT  arrangement.arid,arrangement.acaid,arrangement.pid,arrangement.cid,arrangement.coid,arrangement.tid,arrangement.sid,arrangement._week,arrangement.aexp aexp,acayear.acaname,class.cname,course.coname,prof.pname,section.sname FROM arrangement left join acayear on acayear.acaid=arrangement.acaid left join class on class.cid=arrangement.cid left join course on course.coid=arrangement.coid left join prof on prof.pid=arrangement.pid left join  section on  section.sid=arrangement.sid) as a left join  teacher  on  a.tid=teacher.tid where acaid="+acaid+" and tname like '%"+tname+"%') as TEMP1 where ROWID>"+((Pageno-1)*Pagesize);
/*		sql = "SELECT * FROM(SELECT TOP "+(Pagesize*Pageno)+" ROW_NUMBER() OVER(ORDER BY arid ASC) AS ROWID,arrangement.*,acayear.acaname,class.cname,course.coname,prof.pname,section.sname,teacher.tname FROM acayear,class,course,prof,section,teacher,arrangement where acayear.acaid=arrangement.acaid and class.cid=arrangement.cid and course.coid=arrangement.coid and prof.pid=arrangement.pid and section.sid=arrangement.sid and teacher.tid=teacher.tid) AS TEMP1 WHERE  ROWID>"+((Pageno-1)*Pagesize)+" and acaid="+acaid+" and tname like %"+tname+"%";*/
		System.out.println(sql);
		Object[] objs = new Object[] {};
		arrangement=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return arrangement;
	}
	@Override
	protected Object rowMapper(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Arrangement arrangement=new Arrangement();
		arrangement.setAcaid(rs.getInt("acaid"));
		arrangement.setAexp(rs.getString("aexp"));
		arrangement.setArid(rs.getInt("arid"));
		arrangement.setCid(rs.getInt("cid"));
		arrangement.setCoid(rs.getInt("coid"));
		arrangement.setPid(rs.getInt("pid"));
		arrangement.setSid(rs.getInt("sid"));
		arrangement.setTid(rs.getInt("tid"));
		arrangement.setWeek(rs.getDate("_week"));
		arrangement.setAcaname(rs.getString("acaname"));
		arrangement.setCname(rs.getString("cname"));
		arrangement.setConame(rs.getString("coname"));
		arrangement.setPname(rs.getString("pname"));
		arrangement.setSname(rs.getString("sname"));
		arrangement.setTname(rs.getString("tname"));
		return arrangement;
	}

}
