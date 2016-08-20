package com.bclw.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bclw.bean.Course;
import com.bclw.dao.inter.Dm;


public class courseDaoImpl extends AbstractDao implements Dm<Course> {
	private String sql;

	// 添加课程
	@Override
	public boolean add(Course t) {
		sql = "insert into course(coname,coexp) values(?,?)";
		Object[] objs = new Object[] { t.getConame(), t.getCoexp() };
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

	// 删除课程
	@Override
	public boolean delete(Course t) {
		sql = "delete from course where coid=?";
		Object[] objs = new Object[] { t.getCoid() };
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

	// 修改课程
	@Override
	public boolean update(Course t) {
		sql = "update course set coname=?,coexp=? where coid=?";
		Object[] objs = new Object[] { t.getConame(),t.getCoexp(),t.getCoid() };
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
	public ArrayList<Course> getAllCourse(int Pagesize, int Pageno) {
		// TODO Auto-generated method stub
		ArrayList<Course> course=null;
		try{
		sql = "SELECT * FROM(SELECT TOP "+(Pagesize*Pageno)+" ROW_NUMBER() OVER(ORDER BY coid ASC) AS ROWID,* FROM course) AS TEMP1 WHERE ROWID>"+((Pageno-1)*Pagesize);
/*		sql="SELECT * FROM course ORDER BY coid asc OFFSET "+(Pageno-1)+" ROWS FETCH NEXT "+Pagesize+" ROWS ONLY";			*/
		System.out.println(sql);
		Object[] objs = new Object[] {};
		course=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return course;
	}
	public ArrayList<Course> getAllCourse() {
		// TODO Auto-generated method stub
		ArrayList<Course> course=null;
		try{
		sql = "SELECT * FROM Course";
		System.out.println(sql);
		Object[] objs = new Object[] {};
		course=this.sqlExecute(sql, objs);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return course;
	}
	@SuppressWarnings("finally")
	public Course findConame(String coname){//检测课程名称是否存在
		sql="select * from course where coname='"+coname+'\'';
		Object[] objs = new Object[] {};
		Course co=new Course();
		try {
			co=(Course)super.sqlExecute3(sql, objs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}finally{
		return co;
		}
	}

	@Override
	protected Object rowMapper(ResultSet rs) throws SQLException {

		/*
		 * t t = new t(); t.setId(rs.getString("id"));
		 * t.setName(rs.getString("name"));
		 * t.setBirthday(rs.getDate("birthday"));
		 * t.setMoney(rs.getFloat("money"));
		 */
		Course course=new Course();
		course.setCoid(rs.getInt("coid"));
		course.setConame(rs.getString("Coname"));
		course.setCoexp(rs.getString("Coexp"));
		return course;
	}

}
