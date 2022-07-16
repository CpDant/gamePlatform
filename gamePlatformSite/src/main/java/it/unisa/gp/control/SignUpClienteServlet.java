package it.unisa.gp.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.ClientiDS;
import it.unisa.gp.model.DAO.TelefonoDS;
import it.unisa.gp.model.bean.Carrello;
import it.unisa.gp.model.bean.ClientiBean;
import it.unisa.gp.model.bean.TelefonoBean;
import it.unisa.gp.model.interfaceDS.Clienti;
import it.unisa.gp.model.interfaceDS.Telefono;

/**
 * Servlet implementation class SignUpClienteServlet
 */
@WebServlet("/SignUpClienteServlet")
public class SignUpClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpClienteServlet() {
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
		String redirectedPage;
		
		String codFis = request.getParameter("codFis");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String data = request.getParameter("data");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String indFatt = request.getParameter("indFatt");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		
		System.out.println(tel2);
		int num = 1;
		if (tel2 != "") {
			num++;
		}

		ClientiBean clienteBean = new ClientiBean(codFis, nome, cognome, LocalDate.parse(data), email, password, username, indFatt);
		TelefonoBean telBean1 = null;
		TelefonoBean telBean2 = null;
		if(num == 1) {
			telBean1 = new TelefonoBean(Long.valueOf(tel1), codFis);
		} else {
			telBean1 = new TelefonoBean(Long.valueOf(tel1), codFis);
			telBean2 = new TelefonoBean(Long.valueOf(tel2), codFis);
		}
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Clienti clDS = new ClientiDS(ds);
		Telefono telDS = new TelefonoDS(ds);
		
		try {
			clDS.doSave(clienteBean);
			if(num == 1) {
			telDS.doSave(telBean1);
			} else {
				telDS.doSave(telBean1);
				telDS.doSave(telBean2);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			request.getSession().setAttribute("utente", clienteBean);
			request.getSession().setAttribute("roles", "cliente");
			request.getSession().setAttribute("carrello", new Carrello(ds));
			
			redirectedPage = "/protected.jsp";
			
		} catch (Exception e) {
			request.getSession().setAttribute("roles", null);
			redirectedPage = "/signup-form.jsp";
		}
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}
}
