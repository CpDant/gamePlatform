package it.unisa.gp.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.AssistenteClientiDS;
import it.unisa.gp.model.DAO.TicketsDS;
import it.unisa.gp.model.bean.AssistenteClientiBean;
import it.unisa.gp.model.bean.ClientiBean;
import it.unisa.gp.model.bean.TicketsBean;
import it.unisa.gp.model.bean.TicketsBean.CategoriaProbl;
import it.unisa.gp.model.interfaceDS.AssistenteClienti;
import it.unisa.gp.model.interfaceDS.Tickets;

/**
 * Servlet implementation class CreazioneTicket
 */
@WebServlet("/CreazioneTicket")
public class CreazioneTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreazioneTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		AssistenteClienti assClDS = new AssistenteClientiDS(ds);
		Tickets ticketsDS = new TicketsDS(ds);
		
		ClientiBean clBean = (ClientiBean) request.getSession().getAttribute("utente");
		String descProb = request.getParameter("textArea");
		CategoriaProbl catProb = CategoriaProbl.valueOf(request.getParameter("problema"));
		int min = 100000;
		int max = 999999;
    	int id = (int) (Math.random() * (max - min)) + min;
		try {
			List<AssistenteClientiBean> colAssCl = (List<AssistenteClientiBean>) assClDS.doRetrieveAll(null);
			Random rand = new Random();
			int indice = rand.nextInt(colAssCl.size());
			ticketsDS.doSave(new TicketsBean(id,colAssCl.get(indice).getCodiceFiscale(),clBean.getCodiceFiscale(), catProb, descProb, LocalDateTime.now()));
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("idTic", id);
		response.sendRedirect(request.getContextPath() + "/ticketsCorrect.jsp");
	}

}
