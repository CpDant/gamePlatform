package it.unisa.gp.control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.AddAbbDS;
import it.unisa.gp.model.DAO.AddInAbbDS;
import it.unisa.gp.model.DAO.AddVideogDS;
import it.unisa.gp.model.bean.AddAbbBean;
import it.unisa.gp.model.bean.AddInAbbBean;
import it.unisa.gp.model.bean.AddVideogBean;
import it.unisa.gp.model.bean.SupervisoreVideogiochiBean;
import it.unisa.gp.model.bean.VideogiocoBean.Pegi;
import it.unisa.gp.model.interfaceDS.AddAbb;
import it.unisa.gp.model.interfaceDS.AddInAbb;
import it.unisa.gp.model.interfaceDS.AddVideog;

/**
 * Servlet implementation class AddInCatServlet
 */
@WebServlet("/AddInCatServlet")
public class AddInCatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddInCatServlet() {
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
		SupervisoreVideogiochiBean supVidBean = (SupervisoreVideogiochiBean) request.getSession().getAttribute("utente");
		
		String codVid = null;
		String nomeAbb = null;
		if(request.getParameter("tipo").equals("videogioco")) {
			String nome = request.getParameter("nome-vid");
			codVid = request.getParameter("cod-vid");
			int dim = Integer.parseInt(request.getParameter("dim-vid"));
			int annoProd = Integer.parseInt(request.getParameter("annoProd"));
			int costo = Integer.parseInt(request.getParameter("costo"));
			String softHouse = request.getParameter("softHouse");
			Pegi pegi = Pegi.valueOf(request.getParameter("pegi"));
			
			Part part = request.getPart("inputImage");
	        String fileName = codVid+"_1.jpg";
	        System.out.println(fileName);
	        
	        String path = getServletContext().getRealPath("/"+"img"+"/videog"+File.separator+fileName);
	        System.out.println(path);
	        part.write(path);
			
			AddVideog addVidDS = new AddVideogDS(ds);
			try {
				addVidDS.doSave(new AddVideogBean(supVidBean.getCodiceFiscale(), codVid), codVid, softHouse, nome, dim, annoProd, costo, pegi);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			nomeAbb = request.getParameter("nome-abb");
			int costo = Integer.parseInt(request.getParameter("costo"));
			int durata = Integer.parseInt(request.getParameter("durata"));
			
			Part part = request.getPart("inputImage");
	        String fileName = nomeAbb+".jpg";
	        System.out.println(fileName);
	        
	        String path = getServletContext().getRealPath("/"+"img"+"/abb"+File.separator+fileName);
	        System.out.println(path);
	        part.write(path);
			
			AddAbb addAbbDS = new AddAbbDS(ds);
			try {
				addAbbDS.doSave(new AddAbbBean(supVidBean.getCodiceFiscale(), nomeAbb), nomeAbb, costo, durata);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] checkboxNamesList = request.getParameterValues("vidContenuti");
			AddInAbb addInAbbDS = new AddInAbbDS(ds);
			for (int i = 0; i < checkboxNamesList.length; i++) {			    
				try {
					addInAbbDS.doSave(new AddInAbbBean(supVidBean.getCodiceFiscale(), checkboxNamesList[i], nomeAbb));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
        
        if(request.getParameter("tipo").equals("videogioco")) {
        	response.sendRedirect(request.getContextPath() + "/prodottoVideog.jsp?id=" + codVid);
        } else {
        	response.sendRedirect(request.getContextPath() + "/prodottoAbb.jsp?id=" + nomeAbb);
        }
      }
	
}
