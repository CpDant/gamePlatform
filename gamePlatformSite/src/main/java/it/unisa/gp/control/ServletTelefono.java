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

import it.unisa.gp.model.DAO.TelefonoDS;
import it.unisa.gp.model.bean.SoftwareHouseBean;
import it.unisa.gp.model.bean.TelefonoBean;
import it.unisa.gp.model.interfaceDS.Telefono;


/**
 * Servlet implementation class ServletTelefono
 */
@WebServlet("/ServletTelefono")
public class ServletTelefono extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTelefono() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<p> Ciao </p>");
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Telefono tel = new TelefonoDS(ds);
		TelefonoBean bean = new TelefonoBean(543126789,"DNTFNC02A07L245X");

		/* funziona
		try {
			tel.doSave(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//funziona
		try {
			tel.doDelete(bean.getNumero());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//funziona
		try {
			bean = tel.doRetrieveByKey(345126789);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(bean.toString());
		
		
		//funziona
		Collection<TelefonoBean> telefono = null;

		try {
			telefono = (Collection<TelefonoBean>) tel.doRetrieveAll("NUMERO DESC");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(telefono.toString());
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
