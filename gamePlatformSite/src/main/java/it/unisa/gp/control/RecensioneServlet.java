package it.unisa.gp.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.RecensioneDS;
import it.unisa.gp.model.bean.ClientiBean;
import it.unisa.gp.model.bean.RecensioneBean;
import it.unisa.gp.model.bean.RecensioneBean.Grado;
import it.unisa.gp.model.interfaceDS.Recensione;

/**
 * Servlet implementation class RecensioneServlet
 */
@WebServlet("/RecensioneServlet")
public class RecensioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecensioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruolo = (String) request.getSession().getAttribute("roles");
		if(ruolo == null) {
			response.sendRedirect(request.getContextPath() + "/login-form.jsp");
		}else if(ruolo.equals("supVid")) {
			response.sendRedirect(request.getContextPath() + "/catalogoVidSup.jsp");
		}else if(ruolo.equals("cliente")){
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			Recensione recDS = new RecensioneDS(ds);
			ClientiBean cliente = (ClientiBean) request.getSession().getAttribute("utente");
			String codiceVid = request.getParameter("id");
			String descrizione = (String) request.getParameter("textArea");
			Grado gradApp = Grado.valueOf(request.getParameter("grado"));
			try {
				recDS.doSave(new RecensioneBean(cliente.getCodiceFiscale(), codiceVid, LocalDateTime.now(),descrizione,gradApp));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/prodottoVideog.jsp?id=" + codiceVid);
		}else {
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
		}
		
	}

}
