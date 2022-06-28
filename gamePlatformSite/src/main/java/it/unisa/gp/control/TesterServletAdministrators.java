package it.unisa.gp.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.util.Collection;

import it.unisa.gp.model.DAO.AdministratorsDS;
import it.unisa.gp.model.bean.AdministratorsBean;
import it.unisa.gp.model.interfaceDS.Administrators;

/**
 * Servlet implementation class TesterServletAdministrators
 */
@WebServlet("/TesterServletAdministrators")
public class TesterServletAdministrators extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TesterServletAdministrators() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<p> Ciao </p>");
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Administrators adm = new AdministratorsDS(ds);
		AdministratorsBean bean = new AdministratorsBean("nuovo","Andrea","Genovese", LocalDate.of(2000,5,7), "emailnuova", "paolo", 207);
		
		/*
		//funziona
		try {
			adm.doSave(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		//funziona
		try {
			adm.doUpdate(bean,"Giuse","Spera", LocalDate.of(2025,9,11), "email", "passwordFiga", 2500);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
				
		
		/*	//funziona
		try {
			adm.doDelete(bean.getCodiceFiscale());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
			//funziona
		try {
			bean = adm.doRetrieveByKey("125");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(bean.toString()); 	//print di debug
		*/
		
		/*
		Collection<AdministratorsBean> admini = null;

		try {
			admini = (Collection<AdministratorsBean>) adm.doRetrieveAll("CODICE_FISCALE ASC");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(admini.toString()); 
		*/
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
