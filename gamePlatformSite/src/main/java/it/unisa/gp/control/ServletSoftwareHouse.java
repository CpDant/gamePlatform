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

import it.unisa.gp.model.DAO.SoftwareHouseDS;

import it.unisa.gp.model.bean.SoftwareHouseBean;

import it.unisa.gp.model.interfaceDS.SoftwareHouse;

/**
 * Servlet implementation class ServletSoftwareHouse
 */
@WebServlet("/ServletSoftwareHouse")
public class ServletSoftwareHouse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSoftwareHouse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		out.println("<p> Ciao </p>");
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		SoftwareHouse abb = new SoftwareHouseDS(ds);
		
		SoftwareHouseBean bean = new SoftwareHouseBean("ea sportss", "Canada", LocalDate.of(1987,12,22));
		
		/*
		try {
			abb.doSave(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		try {
			abb.doUpdate(bean,"Brasie",LocalDate.of(2012, 2, 12));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		try {
			abb.doDelete(bean.getNomeUnivoco());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		try {
			bean = abb.doRetrieveByKey("ea sportsss");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(bean.toString());
		*/
		
		
		
		
		Collection<SoftwareHouseBean> software = null;

		try {
			software = (Collection<SoftwareHouseBean>) abb.doRetrieveAll("NOME_UNIVOCO DESC");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(software.toString());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
