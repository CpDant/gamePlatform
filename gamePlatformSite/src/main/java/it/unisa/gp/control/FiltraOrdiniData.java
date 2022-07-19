package it.unisa.gp.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



import it.unisa.gp.model.DAO.AcquistiDS;
import it.unisa.gp.model.bean.AcquistiBean;
import it.unisa.gp.model.bean.ClientiBean;
import it.unisa.gp.model.interfaceDS.Acquisti;

/**
 * Servlet implementation class FiltraOrdiniData
 */
@WebServlet("/FiltraOrdiniData")
public class FiltraOrdiniData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltraOrdiniData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ClientiBean clBean = (ClientiBean) request.getSession().getAttribute("utente");
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		
		Acquisti acquistiDS = new AcquistiDS(ds);
		Collection<AcquistiBean> colAcq = null;
		try {
			colAcq = acquistiDS.doRetrieveAllByDate(clBean.getCodiceFiscale(), first, last, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html");
		request.setAttribute("orderList", colAcq);
		request.getRequestDispatcher("ordini.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
