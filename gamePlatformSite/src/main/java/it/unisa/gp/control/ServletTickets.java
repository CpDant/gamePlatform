package it.unisa.gp.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.util.Collection;

import it.unisa.gp.model.DAO.TicketsDS;
import it.unisa.gp.model.bean.TicketsBean;
import it.unisa.gp.model.bean.TicketsBean.CategoriaProbl;
import it.unisa.gp.model.interfaceDS.Tickets;

/**
 * Servlet implementation class ServletTickets
 */
@WebServlet("/ServletTickets")
public class ServletTickets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTickets() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<p> Ciao </p>");
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		Tickets tic = new TicketsDS(ds);
		/*
		TicketsBean bean = new TicketsBean(54,"sballo","CJDEJFKMWOI3", CategoriaProbl.account, "ciao afvelhcavsdhjk l avcbvbad kjvcabnvad jbcvakbjuac", LocalDateTime.now());
		TicketsBean bean1 = new TicketsBean(546,"sballo","GDFSAOIAFSJM", CategoriaProbl.pagamenti, "testro pers", LocalDateTime.now());
		TicketsBean bean2 = new TicketsBean(1,"zzasf654","GDFSAOIAFSJM", CategoriaProbl.abbonamenti, "example test", LocalDateTime.now());
		//funziona
		try {
			//tic.doSave(bean);
			tic.doSave(bean1);
			tic.doSave(bean2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			tic.doUpdate(bean, false, CategoriaProbl.pagamenti, "bunogiorno", LocalDateTime.of(2000,6,8,21,56));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		*/
		
		
		/*
		try {
			tic.doDelete(bean.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	
		/*//funziona
		try {
			bean = tic.doRetrieveByKey(123);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(bean.toString()); 	//print di debug
		*/
		
		Collection<TicketsBean> tick = null;

		try {
			tick = (Collection<TicketsBean>) tic.doRetrieveAllCliente(null, "GDFSAOIAFSJM");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(tick.toString()); 
		
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
