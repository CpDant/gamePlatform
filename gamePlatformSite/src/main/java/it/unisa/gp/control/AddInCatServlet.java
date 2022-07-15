package it.unisa.gp.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
		
		
		if(request.getParameter("tipo").equals("videogioco")) {
			String nome = request.getParameter("nome-vid");
			String cod = request.getParameter("cod-vid");
			int dim = Integer.parseInt(request.getParameter("dim-vid"));
			int annoProd = Integer.parseInt(request.getParameter("annoProd"));
			int costo = Integer.parseInt(request.getParameter("costo"));
			String softHouse = request.getParameter("softHouse");
			Pegi pegi = Pegi.valueOf(request.getParameter("pegi"));
			
			AddVideog addVidDS = new AddVideogDS(ds);
			try {
				addVidDS.doSave(new AddVideogBean(supVidBean.getCodiceFiscale(), cod), cod, softHouse, nome, dim, annoProd, costo, pegi);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			String nome = request.getParameter("nome-abb");
			int costo = Integer.parseInt(request.getParameter("costo"));
			int durata = Integer.parseInt(request.getParameter("durata"));
			
			AddAbb addAbbDS = new AddAbbDS(ds);
			try {
				addAbbDS.doSave(new AddAbbBean(supVidBean.getCodiceFiscale(), nome), nome, costo, durata);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] checkboxNamesList = request.getParameterValues("vidContenuti");
			AddInAbb addInAbbDS = new AddInAbbDS(ds);
			for (int i = 0; i < checkboxNamesList.length; i++) {			    
				try {
					addInAbbDS.doSave(new AddInAbbBean(supVidBean.getCodiceFiscale(), checkboxNamesList[i], nome));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		Part part = request.getPart("inputImage");
        String fileName = part.getSubmittedFileName();
        
        String path = getServletContext().getRealPath("/"+"img"+File.separator+fileName);
        System.out.println(path);
        
        InputStream is = part.getInputStream();
        boolean test = uploadFile(is,path);
        if(test){
            System.out.println("uploaded");
        }else{
            System.out.println("something wrong");
        }
        
        if(request.getParameter("tipo").equals("videogioco")) {
        	response.sendRedirect(request.getContextPath() + "/catalogoVidSup.jsp");
        } else {
        	response.sendRedirect(request.getContextPath() + "/catalogoAbbSup.jsp");
        }
	}
	
	public boolean uploadFile(InputStream is, String path){
        boolean test = false;
        try{
            byte[] byt = new byte[is.available()];
            is.read();
            
            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();
            
            test = true;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return test;
    }
}

	

