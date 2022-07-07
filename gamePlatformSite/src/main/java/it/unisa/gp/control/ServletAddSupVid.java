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

import it.unisa.gp.model.DAO.AddSupVidDS;
import it.unisa.gp.model.bean.AddSupVidBean;
import it.unisa.gp.model.interfaceDS.AddSupVid;

/**
 * Servlet implementation class ServletAddSupVid
 */
@WebServlet("/ServletAddSupVid")
public class ServletAddSupVid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddSupVid() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<p> Ciao </p>");
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		AddSupVid asv = new AddSupVidDS(ds);
		AddSupVidBean bean = new AddSupVidBean("ab4c15","ABCD123");
		
		
		/*// funziona
		try {
			asv.doSave(bean,"ab4c15", "Alberto", "Bianchi", LocalDate.of(1998, 11, 3), "alberto@email", "fullPassword", 1600);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//funziona
		try {
			asv.doDelete(bean.getCodiceFiscaleSupVid(),bean.getCodiceFiscaleAdmin());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//funziona
		try {
			bean = asv.doRetrieveByKey("ab4c15","ABCD123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(bean.toString()); 	//print di debug
		
		
		//funziona
		Collection<AddSupVidBean> addsupvid = null;
		
		try {
			addsupvid = (Collection<AddSupVidBean>) asv.doRetrieveAll("CODICE_FISCALE_SUP_VID ASC");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(addsupvid.toString());
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
