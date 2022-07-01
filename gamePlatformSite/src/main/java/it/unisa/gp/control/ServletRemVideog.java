package it.unisa.gp.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.RemVideogDS;
import it.unisa.gp.model.bean.AbbonamentoBean;
import it.unisa.gp.model.bean.RemVideogBean;
import it.unisa.gp.model.interfaceDS.RemVideog;

/**
 * Servlet implementation class ServletRemVideog
 */
@WebServlet("/ServletRemVideog")
public class ServletRemVideog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRemVideog() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<p> Ciao </p>");
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		RemVideog abb = new RemVideogDS(ds);
		RemVideogBean bean = new RemVideogBean("ABCD123","abc12");
		
		/* //funziona
		try {
			abb.doSave(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//funziona
		try {
			abb.doDelete(bean.getCodiceFiscaleSupVid(), bean.getCodiceVideogioco());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		//funziona
		try {
			bean = abb.doRetrieveByKey("ABCD123","abc12");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(bean.toString()); 	//print di debug
		
		
		//funziona
		Collection<RemVideogBean> abbonament = null;
		
		try {
			abbonament = (Collection<RemVideogBean>) abb.doRetrieveAll("CODICE_FISCALE_SUP_VID, CODICE_VIDEOGIOCO ASC");
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
