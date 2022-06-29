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
import it.unisa.gp.model.DAO.AssistenteClientiDS;
import it.unisa.gp.model.bean.AssistenteClientiBean;
import it.unisa.gp.model.interfaceDS.AssistenteClienti;

/**
 * Servlet implementation class TesterServlet
 */
@WebServlet("/TesterServlet")
public class TesterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TesterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<p> Ciao </p>");

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		AssistenteClienti ass = new AssistenteClientiDS(ds);
		AssistenteClientiBean bean = new AssistenteClientiBean("VTLCRIH703H10DEd", "Ciro", "Vitale", LocalDate.of(2001,06,12),
				"ciro@email.it", "passwordTest", 10000);

		/*
		try {
			ass.doSave(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

		/*
		try {
			ass.doUpdate(bean,"Alfredo", "Cuozzo", LocalDate.of(2001,06,13),
					"alf@email.it", "passwordTest1", 100001, 1, 5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		// funziona
		try {
			ass.doDelete(bean.getCodiceFiscale());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		// funziona
		try {
			bean = ass.doRetrieveByKey("VTLCRI01H12H703D");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println(bean.toString()); 	//print di debug
		*/
		/*
		// funziona
		Collection<AssistenteClientiBean> abbonament = null;

		try {
			abbonament = (Collection<AssistenteClientiBean>) ass.doRetrieveAll(null);
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
