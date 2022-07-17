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

import it.unisa.gp.model.DAO.AddAssClDS;
import it.unisa.gp.model.DAO.AddSupVidDS;
import it.unisa.gp.model.bean.AddAssClBean;
import it.unisa.gp.model.bean.AddSupVidBean;
import it.unisa.gp.model.bean.AdministratorsBean;
import it.unisa.gp.model.interfaceDS.AddAssCl;
import it.unisa.gp.model.interfaceDS.AddSupVid;

/**
 * Servlet implementation class AddOperatorServlet
 */
@WebServlet("/AddOperatorServlet")
public class AddOperatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOperatorServlet() {
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
		AdministratorsBean adminBean = (AdministratorsBean) request.getSession().getAttribute("utente");
		String codFisAdm = adminBean.getCodiceFiscale();
		
		String codFisOp = request.getParameter("codFis");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String data = request.getParameter("data");
		String email = request.getParameter("email");
		String ruolo = request.getParameter("ruolo");
		String passw = request.getParameter("password");
		String retrAnn = request.getParameter("retrAnn");
		
		if (ruolo.equals("supVid")) {
			AddSupVid addSupDS = new AddSupVidDS(ds);
			AddSupVidBean addSupBean = new AddSupVidBean(codFisOp, codFisAdm);
			try {
				addSupDS.doSave(addSupBean, codFisOp, nome, cognome, LocalDate.parse(data), email, passw, Integer.parseInt(retrAnn));
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else { // assCl
			AddAssCl addAssDS = new AddAssClDS(ds);
			AddAssClBean addAssBean = new AddAssClBean(codFisOp, codFisAdm);
			try {
				addAssDS.doSave(addAssBean, codFisOp, nome, cognome, LocalDate.parse(data), email, passw, Integer.parseInt(retrAnn));
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.getSession().setAttribute("codFisOpAgg", codFisOp);
		request.getSession().setAttribute("ruoloOpAgg", ruolo);
		response.sendRedirect(request.getContextPath()+"/opAddCorrect.jsp");
	}

}
