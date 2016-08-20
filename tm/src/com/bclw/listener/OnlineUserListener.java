package com.bclw.listener;

import java.util.HashSet;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineUserListener implements HttpSessionListener {
	private HashSet<Object>	sessions	= new HashSet<Object>();

	@Override
	public void sessionCreated(HttpSessionEvent event) {

/*		ServletContext ctx = event.getSession().getServletContext();
		Integer numSessions = (Integer) ctx.getAttribute("numSessions");
		if (numSessions == null) {
			numSessions = new Integer(1);
		} else {
			int count = numSessions.intValue();
			numSessions = new Integer(count + 1);
		}
		ctx.setAttribute("numSessions", numSessions);*/
		System.out.println("创建session......"); 
        ServletContext context=event.getSession().getServletContext(); 
        Integer count=(Integer)context.getAttribute("count"); 
        if(count==null){ 
            count=new Integer(1); 
        }else{ 
            int co = count.intValue( ); 
            count= new Integer(co+1); 
        } 
        context.setAttribute("count", count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {

/*		ServletContext ctx = event.getSession().getServletContext();
		Integer numSessions = (Integer) ctx.getAttribute("numSessions");
		if (numSessions == null) {
			numSessions = new Integer(0);
		} else {
			int count = numSessions.intValue();
			numSessions = new Integer(count - 1);
		}
		ctx.setAttribute("numSessions", numSessions);*/
		 System.out.println("销毁session......"); 
	        ServletContext context=event.getSession().getServletContext(); 
	        Integer count=(Integer)context.getAttribute("count"); 
	        int co=count.intValue(); 
	        count=new Integer(co-1); 
	        context.setAttribute("count", count); 
	     
	}

}
