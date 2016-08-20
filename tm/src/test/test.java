package test;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.bclw.bean.Acayear;
import com.bclw.bean.Admin;
import com.bclw.bean.Course;
import com.bclw.bean.Prof;
import com.bclw.bean.Section;
import com.bclw.dao.imp.*;
import com.bclw.service.AdminServiceImp;
import com.bclw.tool.MD5Util;

public class test {

	public static void main(String[] args) {
	
		AdminServiceImp adsi=new AdminServiceImp();
		SimpleDateFormat sdf=new SimpleDateFormat("EEEE");
		Date week;
		try {
			week=sdf.parse("");
			
			System.out.println("size="+adsi.getAllArrangementByCid(5, week).size());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	
	/*	Date date;
		try {
			date = sdf.parse( "15:30" );
			Section t=new Section("111",date,date);
			sectionDaoImpl sdi=new sectionDaoImpl();
			sdi.add(t);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*Db db = new Db();
		Connection conn = db.getConn();
		if (conn == null)
			System.out.println("null");*/

/*		Course c = new Course(1,"2334","233");
		courseDaoImpl cdi=new courseDaoImpl();
		System.out.print(cdi.update(c));*/
		/*AdminServiceImp adi=new AdminServiceImp();*/
		/*Admin ad=adi.findadminuser("cs");
		System.out.println(ad.getUpass());*/
/*		AcayearDaoImpl adi=new AcayearDaoImpl();
		ArrayList<Acayear> aca=adi.getAllAcayear(2, 3);
		Iterator<Acayear> it;
		it=aca.iterator();
		while(it.hasNext()){
			Acayear temp=it.next();
			System.out.println(temp.getAcaname());
		}*/
/*		ProfDaoImpl p=new ProfDaoImpl();
		ArrayList<Prof> pf=p.getAllProf(5, 2);
		Iterator<Prof> it;
		it=pf.iterator();
		while(it.hasNext()){
			Prof temp=it.next();
			System.out.println(temp.getPname());
		}*/
/*		courseDaoImpl co=new courseDaoImpl();
		ArrayList<Course> cou=co.getAllCourse(3, 3);
		Iterator<Course> it;
		it=cou.iterator();
		while(it.hasNext()){
			Course temp=it.next();
			System.out.println(temp.getConame());
		}*/
		
	}

}
