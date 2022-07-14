package it.unisa.gp.control;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.util.Collection;

import it.unisa.gp.model.DAO.AcqContieneAbbDS;
import it.unisa.gp.model.DAO.AziendaDS;
import it.unisa.gp.model.DAO.AcqContieneVidDS;
import it.unisa.gp.model.DAO.AcquistiDS;
import it.unisa.gp.model.DAO.FatturaDS;
import it.unisa.gp.model.bean.AbbonamentoBean;
import it.unisa.gp.model.bean.AziendaBean;
import it.unisa.gp.model.bean.Carrello;
import it.unisa.gp.model.bean.ClientiBean;
import it.unisa.gp.model.bean.VideogiocoBean;
import it.unisa.gp.model.interfaceDS.AcqContieneAbb;
import it.unisa.gp.model.interfaceDS.AcqContieneVid;
import it.unisa.gp.model.interfaceDS.Acquisti;
import it.unisa.gp.model.interfaceDS.Azienda;
import it.unisa.gp.model.interfaceDS.Fattura;


/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
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
		ClientiBean cliente = (ClientiBean) request.getSession().getAttribute("utente");
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		
		Collection<VideogiocoBean> arrVid = carrello.getArrVidBean();
		Collection<AbbonamentoBean> arrAbb = carrello.getArrAbbBean();
		
		String codFisCliente = cliente.getCodiceFiscale();
		long numCarta = Long.parseLong(request.getParameter("cc-number"));
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Acquisti acqDS = new AcquistiDS(ds);		
		AcqContieneAbb acqAbbDS = new AcqContieneAbbDS(ds);
		AcqContieneVid acqVidDS = new AcqContieneVidDS(ds);
		Fattura fatDS = new FatturaDS(ds);
		Azienda azDS = new AziendaDS(ds);
		
    	int min = 100000;
		int max = 999999;
    	int id = (int) (Math.random() * (max - min)) + min;
  	
    	String codRiscatto = generateRandomHexToken(4);
    	
    	try {
    		acqDS.doSave(id,codFisCliente,codRiscatto,numCarta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	    	
		for(AbbonamentoBean abbBean : arrAbb){
			try {
				acqAbbDS.doSave(id,abbBean.getNomeUnivoco());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(VideogiocoBean vidBean : arrVid){
			try {
				acqVidDS.doSave(id,vidBean.getCodice());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			acqDS.doUpdate(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		request.getSession().setAttribute("carrello", new Carrello(ds));
		 
		if (request.getParameter("fattura") != null) {
			//checked
			
			AziendaBean azBean;
			boolean existAz = false;
			try {
				azBean = azDS.doRetrieveByKeyCodFis(codFisCliente);
				
				if(azBean.getCodiceFiscaleCliente() == null)
					existAz = false;
				else 
					existAz = true;
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			if(existAz == false) {
				String pIva = request.getParameter("pIva");
				String sdi = request.getParameter("sdi");
				String pec = request.getParameter("pec");
				
				try {
					azDS.doSave(new AziendaBean(pIva,codFisCliente,sdi,pec));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			} 

			try {
				fatDS.doSave(id, LocalDateTime.now());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("idAcquisto", id);
		response.sendRedirect(request.getContextPath()+"/thankYouPage.jsp");
		
	}
	
	
    public static String generateRandomHexToken(int byteLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[byteLength];
        secureRandom.nextBytes(token);
        return new BigInteger(1, token).toString(16); // Hexadecimal encoding
    }
	

}
