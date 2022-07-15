package it.unisa.gp.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.RemAbbDS;
import it.unisa.gp.model.bean.RemAbbBean;
import it.unisa.gp.model.bean.SupervisoreVideogiochiBean;
import it.unisa.gp.model.interfaceDS.RemAbb;

/**
 * Servlet implementation class RemAbbFromCatServlet
 */
@WebServlet("/RemAbbFromCatServlet")
public class RemAbbFromCatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemAbbFromCatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		RemAbb remAbbDS = new RemAbbDS(ds);		
		SupervisoreVideogiochiBean supVidBean = (SupervisoreVideogiochiBean) request.getSession().getAttribute("utente");
		
		String id = request.getParameter("id");
		System.out.println(id);
		
		try {
			remAbbDS.doSave(new RemAbbBean(supVidBean.getCodiceFiscale(), id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/catalogoAbbSup.jsp");
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
