package it.unisa.gp.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.util.Collection;

import it.unisa.gp.model.DAO.AbbonamentoDS;
import it.unisa.gp.model.bean.AbbonamentoBean;
import it.unisa.gp.model.interfaceDS.Abbonamento;

/**
 * Servlet implementation class ServletAbbonamento
 */
@WebServlet("/ServletAbbonamento")
public class ServletAbbonamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAbbonamento() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<p> Ciao </p>");
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Abbonamento abb = new AbbonamentoDS(ds);
		AbbonamentoBean bean = new AbbonamentoBean("donato cuozzo",30,50);
		
	
		try {
			abb.doSave(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		try {
			abb.doUpdate(bean,45,67);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// funziona
		try {
			abb.doDelete(bean.getNomeUnivoco());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// funziona
		try {
			bean = abb.doRetrieveByKey("alfredo cuozzo");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(bean.toString()); 	//print di debug
		
		
		// funziona
		Collection<AbbonamentoBean> abbonament = null;
	
		try {
			abbonament = (Collection<AbbonamentoBean>) abb.doRetrieveAll("NOME_UNIVOCO ASC");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(abbonament.toString()); 
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
