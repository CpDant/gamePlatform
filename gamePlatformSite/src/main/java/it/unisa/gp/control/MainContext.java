package it.unisa.gp.control;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class MainContext implements ServletContextListener{
	
	public void contextInitialized(ServletContextEvent event){
		
		ServletContext context = event.getServletContext();
		
		DataSource ds = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/GAME_PLATFORM");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}		

		context.setAttribute("DataSource", ds);
		System.out.println("Creazione DataSource..."+ds.toString());
		
	}
	

	public void contextDestroyed(ServletContextEvent event){
		
		ServletContext context = event.getServletContext();
		
		DataSource ds = (DataSource) context.getAttribute("DataSource");
		System.out.println("DataSource deletion...."+ds.toString());
		
	}	
}
