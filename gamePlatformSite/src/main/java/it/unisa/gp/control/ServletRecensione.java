package it.unisa.gp.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.RecensioneDS;
import it.unisa.gp.model.bean.RecensioneBean;
import it.unisa.gp.model.bean.RecensioneBean.Grado;
import it.unisa.gp.model.interfaceDS.Recensione;


/**
 * Servlet implementation class ServletRecensione
 */
@WebServlet("/ServletRecensione")
public class ServletRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRecensione() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<p> Ciao </p>");
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Recensione rec = new RecensioneDS(ds);
		RecensioneBean bean = new RecensioneBean("DNTFNC02A07L245L", "1234C", LocalDateTime.of(2022,1,2,10,15), "complimenti", Grado.uno);
		
		/*//funziona
		try {
			rec.doSave(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		//funziona
		try {
			rec.doUpdate(bean, LocalDateTime.of(2021,1,2,10,16), "Dovete mettere nuovi aggiornamenti", Grado.quattro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
				
		//funziona
		try {
			rec.doDelete(bean.getCodiceFiscaleCliente(),bean.getCodice());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//funziona
		try {
			bean = rec.doRetrieveByKey("DNTFNC02A07L245L","1234C");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(bean.toString()); 	//print di debug
		
		// funziona
		Collection<RecensioneBean> array = null;
		
		try {
			array = (Collection<RecensioneBean>) rec.doRetrieveAll("CODICE_FISCALE_CLIENTE ASC");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(array.toString()); 
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
