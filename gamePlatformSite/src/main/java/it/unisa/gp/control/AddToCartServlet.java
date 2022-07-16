package it.unisa.gp.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.AbbonamentoDS;
import it.unisa.gp.model.DAO.VideogiocoDS;
import it.unisa.gp.model.bean.AbbonamentoBean;
import it.unisa.gp.model.bean.Carrello;
import it.unisa.gp.model.bean.VideogiocoBean;
import it.unisa.gp.model.interfaceDS.Abbonamento;
import it.unisa.gp.model.interfaceDS.Videogioco;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruolo = (String) request.getSession().getAttribute("roles");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if(ruolo != null) {
			Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
			String id = request.getParameter("id");
			if(id.contains(" Pass")) {
				Abbonamento abbDS = new AbbonamentoDS(ds);
				AbbonamentoBean abbBean = null;
				try {
					abbBean = abbDS.doRetrieveByKey(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Collection<AbbonamentoBean> colAbb = carrello.getArrAbbBean();
				if(!colAbb.contains(abbBean)) {
					carrello.addAbb(id);
				}else {
					response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
					return;
				}
				
			}else {
				Videogioco vidDS = new VideogiocoDS(ds);
				VideogiocoBean vidBean = null;
				try {
					vidBean = vidDS.doRetrieveByKey(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Collection<VideogiocoBean> colVid = carrello.getArrVidBean();
				if(!colVid.contains(vidBean)) {
					carrello.addVid(id);
				}else {
					response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
					return;
				}
				
			}
			
			response.sendRedirect(request.getContextPath() + "/carrello.jsp");
		}else {
			response.sendRedirect(request.getContextPath() + "/login-form.jsp");
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
