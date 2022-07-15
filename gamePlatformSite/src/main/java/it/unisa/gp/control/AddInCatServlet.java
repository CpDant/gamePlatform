package it.unisa.gp.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



import it.unisa.gp.model.bean.VideogiocoBean.Pegi;

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
		
		
		
		if(request.getParameter("tipo").equals("videogioco")) {
			String nomeVid = request.getParameter("nome-vid");
			System.out.println(nomeVid);
			String codVid = request.getParameter("cod-vid");
			System.out.println(codVid);
			int dimVid = Integer.parseInt(request.getParameter("dim-vid"));
			System.out.println(dimVid);
			int annoProd = Integer.parseInt(request.getParameter("annoProd"));
			System.out.println(annoProd);
			int costoVid = Integer.parseInt(request.getParameter("costo"));
			System.out.println(costoVid);
			String softHouse = request.getParameter("softHouse");
			System.out.println(softHouse);
			Pegi pegi = Pegi.valueOf(request.getParameter("pegi"));
			System.out.println(pegi);
		}else {
			String nomeAbb = request.getParameter("nome-abb");
			System.out.println(nomeAbb);
			int costoAbb = Integer.parseInt(request.getParameter("costo"));
			System.out.println(costoAbb);
			int durata = Integer.parseInt(request.getParameter("durata"));
			System.out.println(durata);
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

	

