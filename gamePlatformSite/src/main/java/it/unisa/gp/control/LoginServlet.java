package it.unisa.gp.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.AdministratorsDS;
import it.unisa.gp.model.DAO.AssistenteClientiDS;
import it.unisa.gp.model.DAO.ClientiDS;
import it.unisa.gp.model.DAO.SupervisoreVideogiochiDS;
import it.unisa.gp.model.bean.AdministratorsBean;
import it.unisa.gp.model.bean.AssistenteClientiBean;
import it.unisa.gp.model.bean.Carrello;
import it.unisa.gp.model.bean.ClientiBean;
import it.unisa.gp.model.bean.SupervisoreVideogiochiBean;
import it.unisa.gp.model.interfaceDS.Administrators;
import it.unisa.gp.model.interfaceDS.AssistenteClienti;
import it.unisa.gp.model.interfaceDS.Clienti;
import it.unisa.gp.model.interfaceDS.SupervisoreVideogiochi;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			String redirectedPage;
			String ruolo = null;
			try {
				ruolo = checkLogin(email, password);
				
				ClientiBean clBean = null;
				SupervisoreVideogiochiBean supBean = null;
				AssistenteClientiBean assBean = null;
				AdministratorsBean admBean = null;
				if(ruolo.equals("cliente")) {
					Clienti clDS = new ClientiDS(ds);
					clBean = clDS.doRetrieveByKeyEmail(email);
					request.getSession().setAttribute("utente", clBean);
					request.getSession().setAttribute("carrello", new Carrello(ds)); 
				} else if(ruolo.equals("supVid")) {
					SupervisoreVideogiochi supDS = new SupervisoreVideogiochiDS(ds);
					supBean = supDS.doRetrieveByKeyEmail(email);
					request.getSession().setAttribute("utente", supBean);
				} else if(ruolo.equals("assCl")) {
					AssistenteClienti assDS = new AssistenteClientiDS(ds);
					assBean = assDS.doRetrieveByKeyEmail(email);
					request.getSession().setAttribute("utente", assBean);
				} else if(ruolo.equals("admin")) {
					Administrators admDS = new AdministratorsDS(ds);
					admBean = admDS.doRetrieveByKeyEmail(email);
					request.getSession().setAttribute("utente", admBean);
				}
				
				
				request.getSession().setAttribute("roles", ruolo);
				redirectedPage = "/protected.jsp";
				
			} catch (Exception e) {
				request.getSession().setAttribute("roles", null);
				redirectedPage = "/login-form.jsp";
			}
			response.sendRedirect(request.getContextPath() + redirectedPage);
		}

	}

	private String checkLogin(String email, String password) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStm1 = null;
		PreparedStatement preparedStm2 = null;
		PreparedStatement preparedStm3 = null;
		PreparedStatement preparedStm4 = null;
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		String checkSQL1 = "SELECT PASS_WORD, RUOLO FROM CLIENTI"
				+ " WHERE EMAIL = ?";
		
		String checkSQL2 = "SELECT PASS_WORD, RUOLO FROM ASSISTENTE_CLIENTI"
				+ " WHERE EMAIL = ?";
		
		String checkSQL3 = "SELECT PASS_WORD, RUOLO FROM SUPERVISORE_VIDEOGIOCHI"
				+ " WHERE EMAIL = ?";
		
		String checkSQL4 = "SELECT PASS_WORD, RUOLO FROM ADMINISTRATORS"
				+ " WHERE EMAIL = ?";
		
		String ruolo1 = null;
		String ruolo2 = null;
		String ruolo3 = null;
		String ruolo4 = null;
		
		try {
			connection = ds.getConnection();	
			
			preparedStm1 = connection.prepareStatement(checkSQL1);
			preparedStm1.setString(1, email);
			ResultSet rs1 = preparedStm1.executeQuery();
			String pass_word1 = null;
			if(rs1.next()) {
				pass_word1 = rs1.getString("pass_word");
				if (pass_word1.equals(password))				
					ruolo1 =  rs1.getString("ruolo");
			}	
			
			preparedStm2 = connection.prepareStatement(checkSQL2);
			preparedStm2.setString(1, email);
			ResultSet rs2 = preparedStm2.executeQuery();
			String pass_word2 = null;
			if(rs2.next()) {
				pass_word2 = rs2.getString("pass_word");
				if (pass_word2.equals(password))				
					ruolo2 =  rs2.getString("ruolo");
			}
			
			preparedStm3 = connection.prepareStatement(checkSQL3);
			preparedStm3.setString(1, email);
			ResultSet rs3 = preparedStm3.executeQuery();
			String pass_word3 = null;
			if(rs3.next()) {
				pass_word3 = rs3.getString("pass_word");
				if (pass_word3.equals(password))				
					ruolo3 =  rs3.getString("ruolo");
			}
			
			preparedStm4 = connection.prepareStatement(checkSQL4);
			preparedStm4.setString(1, email);
			ResultSet rs4 = preparedStm4.executeQuery();
			String pass_word4 = null;
			if(rs4.next()) {
				pass_word4 = rs4.getString("pass_word");
				if (pass_word4.equals(password))				
					ruolo4 =  rs4.getString("ruolo");
			}
						
			connection.setAutoCommit(false);
			connection.commit();
		} finally {
			try {
				if (preparedStm1 != null)
					preparedStm1.close();
				if (preparedStm2 != null)
					preparedStm2.close();
				if (preparedStm3 != null)
					preparedStm3.close();
				if (preparedStm4 != null)
					preparedStm4.close();
				
			} finally {
				if (connection != null)
					connection.close();
			}
		}
				
		if (ruolo1 != null) {
			return ruolo1;
		} else if (ruolo2 != null){
			return ruolo2;
		} else if (ruolo3 != null){
			return ruolo3;
		} else if (ruolo4 != null){
			return ruolo4;
		}
		else {
			throw new Exception("Invalid login and password");
		}
				
	}
	
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
