package it.unisa.gp.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.AdministratorsDS;
import it.unisa.gp.model.DAO.AssistenteClientiDS;
import it.unisa.gp.model.DAO.AziendaDS;
import it.unisa.gp.model.DAO.ClientiDS;
import it.unisa.gp.model.DAO.SupervisoreVideogiochiDS;
import it.unisa.gp.model.bean.AdministratorsBean;
import it.unisa.gp.model.bean.AssistenteClientiBean;
import it.unisa.gp.model.bean.AziendaBean;
import it.unisa.gp.model.bean.ClientiBean;
import it.unisa.gp.model.bean.SupervisoreVideogiochiBean;
import it.unisa.gp.model.interfaceDS.Administrators;
import it.unisa.gp.model.interfaceDS.AssistenteClienti;
import it.unisa.gp.model.interfaceDS.Azienda;
import it.unisa.gp.model.interfaceDS.Clienti;
import it.unisa.gp.model.interfaceDS.SupervisoreVideogiochi;

/**
 * Servlet implementation class ModificaDatiServlet
 */
@WebServlet("/ModificaDatiServlet")
public class ModificaDatiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaDatiServlet() {
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
		String roles = (String) request.getSession().getAttribute("roles");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		if(roles == null) {
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
		} else if(roles.equals("cliente")) {
			Clienti clDS = new ClientiDS(ds);
			ClientiBean clBean = (ClientiBean) request.getSession().getAttribute("utente");
			
			String nome = request.getParameter("nome");
			if(nome.equals("")) {
				nome = clBean.getNome();
			} else {
				clBean.setNome(nome);
			}
			
			String cognome = request.getParameter("cognome");
			if(cognome.equals("")) {
				cognome = clBean.getCognome();
			} else {
				clBean.setCognome(cognome);
			}
			
			String data = request.getParameter("data");
			if(data.equals("")) {
				data = clBean.getDataNascita().toString();
			} else {
				clBean.setDataNascita(LocalDate.parse(data, dtf));
			}
			
			String email = request.getParameter("email");
			if(email.equals("")) {
				email = clBean.getEmail();
			} else {
				clBean.setEmail(email);
			}
			
			String password = request.getParameter("passw");
			if(password.equals("")) {
				password = clBean.getPassWord();
			} else {
				clBean.setPassWord(password);
			}
			
			String username = request.getParameter("username");
			if(username.equals("")) {
				username = clBean.getUsername();
			} else {
				clBean.setUsername(username);
			}
			
			String indFatt = request.getParameter("indFatt");
			if(indFatt.equals("")) {
				indFatt = clBean.getIndFatt();
			} else {
				clBean.setIndFatt(indFatt);
			}
			
			try {
				clDS.doUpdate(clBean, nome, cognome, LocalDate.parse(data, dtf), email, password, username, indFatt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Azienda aziendaDS = new AziendaDS(ds);
			AziendaBean azBean = null;
			try {
				azBean = aziendaDS.doRetrieveByKeyCodFis(clBean.getCodiceFiscale());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			boolean existAz = false;
			if(azBean.getCodiceFiscaleCliente() == null)
				existAz = false;
			else 
				existAz = true;
			
			String partIva = null;
			String sdi = null;
			String pec = null;
			
			if(existAz) {
				sdi = request.getParameter("sdi");
				if(sdi.equals("")) {
					sdi = azBean.getSdi();
				}
				
				pec = request.getParameter("pec");
				if(pec.equals("")) {
					pec = azBean.getPec();
				}
				try {
					aziendaDS.doUpdate(azBean, sdi, pec);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}else {
				partIva = request.getParameter("pIva");
				sdi = request.getParameter("sdi");
				pec = request.getParameter("pec");
				
				try {
					aziendaDS.doSave(new AziendaBean(partIva, clBean.getCodiceFiscale(), sdi, pec ));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			request.getSession().setAttribute("utente", clBean);
		} else if(roles.equals("supVid")) {
			SupervisoreVideogiochi supDS = new SupervisoreVideogiochiDS(ds);
			SupervisoreVideogiochiBean supBean = (SupervisoreVideogiochiBean) request.getSession().getAttribute("utente");
			
			String nome = request.getParameter("nome");
			if(nome.equals("")) {
				nome = supBean.getNome();
			} else {
				supBean.setNome(nome);
			}
			
			String cognome = request.getParameter("cognome");
			if(cognome.equals("")) {
				cognome = supBean.getCognome();
			} else {
				supBean.setCognome(cognome);
			}
			
			String data = request.getParameter("data");
			if(data.equals("")) {
				data = supBean.getDataNascita().toString();
			} else {
				supBean.setDataNascita(LocalDate.parse(data, dtf));
			}
			
			String email = request.getParameter("email");
			if(email.equals("")) {
				email = supBean.getEmail();
			} else {
				supBean.setEmail(email);
			}
			
			String password = request.getParameter("passw");
			if(password.equals("")) {
				password = supBean.getPassWord();
			} else {
				supBean.setPassWord(password);
			}
			
			String retrAnnStr = request.getParameter("retrAnn");
			int retrAnn = 0;
			if(retrAnnStr.equals("")) {
				retrAnn = supBean.getRetribuzioneAnnuale();
			} else {
				retrAnn = Integer.parseInt(retrAnnStr);
				supBean.setRetribuzioneAnnuale(retrAnn);
			}
			
			try {
				supDS.doUpdate(supBean, nome, cognome, LocalDate.parse(data, dtf), email, password, retrAnn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("utente", supBean);
		} else if(roles.equals("assCl")) {
			AssistenteClienti assDS = new AssistenteClientiDS(ds);
			AssistenteClientiBean assBean = (AssistenteClientiBean) request.getSession().getAttribute("utente");
			
			String nome = request.getParameter("nome");
			if(nome.equals("")) {
				nome = assBean.getNome();
			} else {
				assBean.setNome(nome);
			}
			
			String cognome = request.getParameter("cognome");
			if(cognome.equals("")) {
				cognome = assBean.getCognome();
			} else {
				assBean.setCognome(cognome);
			}
			
			String data = request.getParameter("data");
			if(data.equals("")) {
				data = assBean.getDataNascita().toString();
			} else {
				assBean.setDataNascita(LocalDate.parse(data, dtf));
			}
			
			String email = request.getParameter("email");
			if(email.equals("")) {
				email = assBean.getEmail();
			} else {
				assBean.setEmail(email);
			}
			
			String password = request.getParameter("passw");
			if(password.equals("")) {
				password = assBean.getPassWord();
			} else {
				assBean.setPassWord(password);
			}
			
			String retrAnnStr = request.getParameter("retrAnn");
			int retrAnn = 0;
			if(retrAnnStr.equals("")) {
				retrAnn = assBean.getRetribuzioneAnnuale();
			} else {
				retrAnn = Integer.parseInt(retrAnnStr);
				assBean.setRetribuzioneAnnuale(retrAnn);
			}
			
			try {
				assDS.doUpdate(assBean, nome, cognome, LocalDate.parse(data, dtf), email, password, retrAnn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("utente", assBean);
		} else if(roles.equals("admin")) {
			Administrators admDS = new AdministratorsDS(ds);
			AdministratorsBean admBean = (AdministratorsBean) request.getSession().getAttribute("utente");
			
			String nome = request.getParameter("nome");
			if(nome.equals("")) {
				nome = admBean.getNome();
			} else {
				admBean.setNome(nome);
			}
			
			String cognome = request.getParameter("cognome");
			if(cognome.equals("")) {
				cognome = admBean.getCognome();
			} else {
				admBean.setCognome(cognome);
			}
			
			String data = request.getParameter("data");
			if(data.equals("")) {
				data = admBean.getDataNascita().toString();
			} else {
				admBean.setDataNascita(LocalDate.parse(data, dtf));
			}
			
			String email = request.getParameter("email");
			if(email.equals("")) {
				email = admBean.getEmail();
			} else {
				admBean.setEmail(email);
			}
			
			String password = request.getParameter("passw");
			if(password.equals("")) {
				password = admBean.getPassWord();
			} else {
				admBean.setPassWord(password);
			}
			
			String retrAnnStr = request.getParameter("retrAnn");
			int retrAnn = 0;
			if(retrAnnStr.equals("")) {
				retrAnn = admBean.getRetribuzioneAnnuale();
			} else {
				retrAnn = Integer.parseInt(retrAnnStr);
				admBean.setRetribuzioneAnnuale(retrAnn);
			}
			
			try {
				admDS.doUpdate(admBean, nome, cognome, LocalDate.parse(data, dtf), email, password, retrAnn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("utente", admBean);
		}
		
		response.sendRedirect(request.getContextPath() + "/utentePage.jsp");
	}

}
