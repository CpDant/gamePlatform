package it.unisa.gp.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Collection;

import it.unisa.gp.model.DAO.AbbonamentoDS;
import it.unisa.gp.model.DAO.AcquistiDS;
import it.unisa.gp.model.DAO.AssistenteClientiDS;
import it.unisa.gp.model.DAO.ClientiDS;
import it.unisa.gp.model.DAO.SoftwareHouseDS;
import it.unisa.gp.model.DAO.SupervisoreVideogiochiDS;
import it.unisa.gp.model.DAO.AddInAbbDS;
import it.unisa.gp.model.DAO.VideogiocoDS;
import it.unisa.gp.model.bean.AbbonamentoBean;
import it.unisa.gp.model.bean.AcquistiBean;
import it.unisa.gp.model.bean.AssistenteClientiBean;
import it.unisa.gp.model.bean.ClientiBean;
import it.unisa.gp.model.bean.SoftwareHouseBean;
import it.unisa.gp.model.bean.SupervisoreVideogiochiBean;
import it.unisa.gp.model.bean.AddInAbbBean;
import it.unisa.gp.model.bean.VideogiocoBean;
import it.unisa.gp.model.bean.VideogiocoBean.Pegi;
import it.unisa.gp.model.interfaceDS.Abbonamento;
import it.unisa.gp.model.interfaceDS.Acquisti;
import it.unisa.gp.model.interfaceDS.AssistenteClienti;
import it.unisa.gp.model.interfaceDS.Clienti;
import it.unisa.gp.model.interfaceDS.SoftwareHouse;
import it.unisa.gp.model.interfaceDS.SupervisoreVideogiochi;
import it.unisa.gp.model.interfaceDS.AddInAbb;
import it.unisa.gp.model.interfaceDS.Videogioco;

/**
 * Servlet implementation class TesterServlet
 */
@WebServlet("/TesterServlet")
public class TesterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TesterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        out.println("<p> Ciao </p>");

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        Abbonamento abb = new AbbonamentoDS(ds);
        AbbonamentoBean abbBean = new AbbonamentoBean("donato cuozzo",30,50);

        SoftwareHouse soft = new SoftwareHouseDS(ds);
        SoftwareHouseBean softBean = new SoftwareHouseBean("ea sports", "usa", LocalDate.of(1995, 10, 3));

        Videogioco vid = new VideogiocoDS(ds);
        VideogiocoBean vidBean = new VideogiocoBean("asc5", "ea sports", "Fifa 21", 10000, 2015, 50, Pegi.tre);

        SupervisoreVideogiochi sup = new SupervisoreVideogiochiDS(ds);
        SupervisoreVideogiochiBean supBean = new SupervisoreVideogiochiBean("acs4csa45", "ciro", "Vitale", LocalDate.of(2001, 10, 3), "ciro@email", "passwrod", 51000);

        AddInAbb add = new AddInAbbDS(ds);
        AddInAbbBean bean = new AddInAbbBean("acs4csa45", "asc5", "donato cuozzo");

        try {
            abb.doSave(abbBean);
            soft.doSave(softBean);
            vid.doSave(vidBean);
            sup.doSave(supBean);
            add.doSave(bean);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		

		/*
		try {
			AddInAbbBean bean = new AddInAbbBean(123, 1, 0, 0, null, null);
			fat.doUpdate(bean, 10, 10000, LocalDateTime.now(), "Via indFFFFFF");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		/*
		try {
			fat.doDelete(123, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		AddInAbbBean bean = null;
		try {
			bean = fat.doRetrieveByKey(123, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println(bean.toString()); 	//print di debug
		*/
		
		/*
		Collection<AddInAbbBean> abbonament = null;

		try {
			abbonament = (Collection<AddInAbbBean>) fat.doRetrieveAll(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(abbonament.toString()); 
		*/
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
