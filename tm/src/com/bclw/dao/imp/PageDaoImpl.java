package com.bclw.dao.imp;
import java.sql.ResultSet;
import java.sql.SQLException; 
public class PageDaoImpl extends AbstractDao{
	

	public int getrowcount(String table) throws Exception {//总条数
		@SuppressWarnings("unused")
		int sum = 0;
		String sql = "select count(*) as sum from " +table;	
		return sum=this.sqlExecute4(sql);
	}
	public int getrowcount2(String table,String col,int tid) throws Exception {//教师查询自己的课程安排的时候的总条数
		@SuppressWarnings("unused")
		int sum=0;//条数统计
		String sql = "select count(*) as sum from "+table+" where acaid="+col+" and tid="+tid+" group by tid";	
		return sum=this.sqlExecute4(sql);	
	}
	public int getpagecount2(int pagesize,String table,String col,int tid) throws Exception {//总页数
		return (getrowcount2(table,col,tid) - 1) / pagesize + 1;
	}
	public int getrowcount3(String col,String like) throws Exception {////模糊查询其他老师总条数
		@SuppressWarnings("unused")
		int sum=0;//条数统计
		String sql = "select count(*) as sum from (select a.*,teacher.tname from (SELECT  arrangement.arid,arrangement.acaid,arrangement.pid,arrangement.cid,arrangement.coid,arrangement.tid,arrangement.sid,arrangement._week,arrangement.aexp aexp,acayear.acaname,class.cname,course.coname,prof.pname,section.sname FROM arrangement left join acayear on acayear.acaid=arrangement.acaid left join class on class.cid=arrangement.cid left join course on course.coid=arrangement.coid left join prof on prof.pid=arrangement.pid left join  section on  section.sid=arrangement.sid) as a left join  teacher  on  a.tid=teacher.tid) as b where acaid="+col+" and tname like '%"+like+"%'";	
		return sum=this.sqlExecute4(sql);
	}
	public int getpagecount3(int pagesize,String col,String like) throws Exception {//模糊查询其他老师总页数
		return (getrowcount3(col,like) - 1) / pagesize + 1;
	}
	public int getrowcountbyTcotext(int arid) throws Exception {//教学内容总条数
		@SuppressWarnings("unused")
		int sum=0;//条数统计
		String sql = "select count(*) as sum from tcotext where arid=" +arid;	
		return sum=this.sqlExecute4(sql);
	}
	public int getpagecountbyTcotext(int pagesize,int arid) throws Exception {//总页数
		return (getrowcountbyTcotext(arid) - 1) / pagesize + 1;
	}
	public int getrowcountbyCware(int tcid) throws Exception {//当前教学内容的课件总数
		@SuppressWarnings("unused")
		int sum=0;//条数统计
		String sql = "select count(*) as sum from cware where tcid=" +tcid;	
		return sum=this.sqlExecute4(sql);
	}
	public int getpagecountbyCware(int pagesize,int tcid) throws Exception {//当前教学内容总页数
		return (getrowcountbyCware(tcid) - 1) / pagesize + 1;
	}	
	public int getpagecount(int pagesize,String table) throws Exception {//总页数
		return (getrowcount(table) - 1) / pagesize + 1;
	}

	@Override
	protected Object rowMapper(ResultSet rs) throws SQLException {
		return null;
	}
}
