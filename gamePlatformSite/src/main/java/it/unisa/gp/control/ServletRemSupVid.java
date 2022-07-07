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

import it.unisa.gp.model.DAO.RemSupVidDS;
import it.unisa.gp.model.bean.RemSupVidBean;
import it.unisa.gp.model.interfaceDS.RemSupVid;

/**
 * Servlet implementation class ServletRemSupVid
 */
@WebServlet("/ServletRemSupVid")
public class ServletRemSupVid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRemSupVid() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<p> Ciao </p>");
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		RemSupVid rsv = new RemSupVidDS(ds);
		RemSupVidBean bean = new RemSupVidBean("ab4c15", "ABCD123");
		
		try {
			rsv.doSave(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
