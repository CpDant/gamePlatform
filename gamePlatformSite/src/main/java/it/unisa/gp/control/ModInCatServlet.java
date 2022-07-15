package it.unisa.gp.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.ModAbbDS;
import it.unisa.gp.model.DAO.ModVideogDS;
import it.unisa.gp.model.bean.ModAbbBean;
import it.unisa.gp.model.bean.ModVideogBean;
import it.unisa.gp.model.bean.SupervisoreVideogiochiBean;
import it.unisa.gp.model.bean.VideogiocoBean.Pegi;
import it.unisa.gp.model.interfaceDS.ModAbb;
import it.unisa.gp.model.interfaceDS.ModVideog;

/**
 * Servlet implementation class ModInCatServlet
 */
@WebServlet("/ModInCatServlet")
public class ModInCatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModInCatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		SupervisoreVideogiochiBean supVidBean = (SupervisoreVideogiochiBean) request.getSession().getAttribute("utente");
		
		
		if(request.getParameter("tipo").equals("videogioco")) {
			String nome = request.getParameter("nome-vid");
			String cod = request.getParameter("videogSelect");
			int dim = Integer.parseInt(request.getParameter("dim-vid"));
			int annoProd = Integer.parseInt(request.getParameter("annoProd"));
			int costo = Integer.parseInt(request.getParameter("costo"));
			Pegi pegi = Pegi.valueOf(request.getParameter("pegi"));
			
			ModVideog modVidDS = new ModVideogDS(ds);
			try {
				modVidDS.doSave(new ModVideogBean(supVidBean.getCodiceFiscale(), cod), cod, nome, dim, annoProd, costo, pegi, false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			String nome = request.getParameter("abbSelect");
			int costo = Integer.parseInt(request.getParameter("costo"));
			int durata = Integer.parseInt(request.getParameter("durata"));
			
			ModAbb modAbbDS = new ModAbbDS(ds);
			try {
				modAbbDS.doSave(new ModAbbBean(supVidBean.getCodiceFiscale(), nome), nome, costo, durata, false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		response.sendRedirect(request.getContextPath() + "/modInCat.jsp");
	}

}
