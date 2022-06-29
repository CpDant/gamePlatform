package it.unisa.gp.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.ClientiDS;
import it.unisa.gp.model.bean.ClientiBean;
import it.unisa.gp.model.interfaceDS.Clienti;

/**
 * Servlet implementation class ServletClienti
 */
@WebServlet("/ServletClienti")
public class ServletClienti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClienti() {
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
		Clienti abb = new ClientiDS(ds);
		
		ClientiBean bean = new ClientiBean("DNTFNC02A07L245L", "Francesco Paolo", "D'Antuono", LocalDate.of(2005, 1, 7), "francescopaolo177@gmail.com" , "admin" , "CpDant", 20, "Via Casa D'Auria 3", 53);
		
		
		try {
			abb.doSave(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		try {
			abb.doUpdate(bean,"Francesco Paolo", "D'Antuono", LocalDate.of(2002, 1, 7), "francescopaolo177@gmail.com", "admin", "CpDant", 25, "Via Casa D'Auria 3", 50);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		try {
			abb.doDelete(bean.getCodiceFiscale());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		try {
			bean = abb.doRetrieveByKey("DNTFNC02A07L245X");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(bean.toString());
		*/
		
		
		
		
		Collection<ClientiBean> software = null;

		try {
			software = (Collection<ClientiBean>) abb.doRetrieveAll("CODICE_FISCALE DESC");
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
