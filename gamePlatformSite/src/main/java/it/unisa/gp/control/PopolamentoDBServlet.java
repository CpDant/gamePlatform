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
import it.unisa.gp.model.DAO.AcqContieneAbbDS;
import it.unisa.gp.model.DAO.AcqContieneVidDS;
import it.unisa.gp.model.DAO.AcquistiDS;
import it.unisa.gp.model.DAO.AddAssClDS;
import it.unisa.gp.model.DAO.AssistenteClientiDS;
import it.unisa.gp.model.DAO.ClientiDS;
import it.unisa.gp.model.DAO.FatturaDS;
import it.unisa.gp.model.DAO.ModAbbDS;
import it.unisa.gp.model.DAO.ModVideogDS;
import it.unisa.gp.model.DAO.RemInAbbDS;
import it.unisa.gp.model.DAO.RemSupVidDS;
import it.unisa.gp.model.DAO.RemVideogDS;
import it.unisa.gp.model.DAO.SoftwareHouseDS;
import it.unisa.gp.model.DAO.SupervisoreVideogiochiDS;
import it.unisa.gp.model.DAO.AddInAbbDS;
import it.unisa.gp.model.DAO.AddSupVidDS;
import it.unisa.gp.model.DAO.AdministratorsDS;
import it.unisa.gp.model.DAO.VideogiocoDS;
import it.unisa.gp.model.bean.AbbonamentoBean;
import it.unisa.gp.model.bean.AcqContieneAbbBean;
import it.unisa.gp.model.bean.AcqContieneVidBean;
import it.unisa.gp.model.bean.AcquistiBean;
import it.unisa.gp.model.bean.AddAssClBean;
import it.unisa.gp.model.bean.AssistenteClientiBean;
import it.unisa.gp.model.bean.ClientiBean;
import it.unisa.gp.model.bean.ModAbbBean;
import it.unisa.gp.model.bean.ModVideogBean;
import it.unisa.gp.model.bean.RemInAbbBean;
import it.unisa.gp.model.bean.RemSupVidBean;
import it.unisa.gp.model.bean.RemVideogBean;
import it.unisa.gp.model.bean.SoftwareHouseBean;
import it.unisa.gp.model.bean.SupervisoreVideogiochiBean;
import it.unisa.gp.model.bean.AddInAbbBean;
import it.unisa.gp.model.bean.AddSupVidBean;
import it.unisa.gp.model.bean.AdministratorsBean;
import it.unisa.gp.model.bean.VideogiocoBean;
import it.unisa.gp.model.bean.VideogiocoBean.Pegi;
import it.unisa.gp.model.interfaceDS.Abbonamento;
import it.unisa.gp.model.interfaceDS.AcqContieneAbb;
import it.unisa.gp.model.interfaceDS.AcqContieneVid;
import it.unisa.gp.model.interfaceDS.Acquisti;
import it.unisa.gp.model.interfaceDS.AddAssCl;
import it.unisa.gp.model.interfaceDS.AssistenteClienti;
import it.unisa.gp.model.interfaceDS.Clienti;
import it.unisa.gp.model.interfaceDS.Fattura;
import it.unisa.gp.model.interfaceDS.ModAbb;
import it.unisa.gp.model.interfaceDS.ModVideog;
import it.unisa.gp.model.interfaceDS.RemInAbb;
import it.unisa.gp.model.interfaceDS.RemSupVid;
import it.unisa.gp.model.interfaceDS.RemVideog;
import it.unisa.gp.model.interfaceDS.SoftwareHouse;
import it.unisa.gp.model.interfaceDS.SupervisoreVideogiochi;
import it.unisa.gp.model.interfaceDS.AddInAbb;
import it.unisa.gp.model.interfaceDS.AddSupVid;
import it.unisa.gp.model.interfaceDS.Administrators;
import it.unisa.gp.model.interfaceDS.Videogioco;

/**
 * Servlet implementation class PopolamentoDBServlet
 */
@WebServlet("/PopolamentoDBServlet")
public class PopolamentoDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PopolamentoDBServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        out.println("<p> Ciao </p>");

        
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        
        // Creazione DataSource necessari
        Administrators adminDS = new AdministratorsDS(ds);
        AddAssCl addAssClDS = new AddAssClDS(ds);
        AddSupVid addSupVidDS = new AddSupVidDS(ds);
        
        
        
        
        // Administrators
        AdministratorsBean adminBean = new AdministratorsBean("CZZLRD76L31F205Y", "Alfredo", "Cuozzo", LocalDate.of(1976, 7, 31), "alfred@email.it", "acs9q8s6", 40000);
        
        // Add Assistente Clienti
        AddAssClBean addAssClBean1 = new AddAssClBean("RSSMRA80C16H501O", "CZZLRD76L31F205Y");
        AddAssClBean addAssClBean2 = new AddAssClBean("DNGMHL95T28M082G", "CZZLRD76L31F205Y");
        
        // Add Supervisore Videogiochi
        AddSupVidBean addSupVidBean1 = new AddSupVidBean("NDRNCL71A02F205D", "CZZLRD76L31F205Y");
        AddSupVidBean addSupVidBean2 = new AddSupVidBean("VRRMSM99S23F839O", "CZZLRD76L31F205Y");
        
        
        // Aggiunta al DB
        try {
        	// Administrators
			adminDS.doSave(adminBean);
			
			// Assistente Clienti
			addAssClDS.doSave(addAssClBean1, "RSSMRA80C16H501O", "Mario", "Rossi", LocalDate.of(1980, 3, 16), "mar.rossi@gmail.com", "1324pass!@", 32000);
			addAssClDS.doSave(addAssClBean2, "DNGMHL95T28M082G", "Michele", "De Angelis", LocalDate.of(1995, 12, 28), "dex.ang@libero.it", "99acm!mila", 35000);
			
			// Supervisore Videogiochi
			addSupVidDS.doSave(addSupVidBean1, "NDRNCL71A02F205D", "Nicola", "Andreazzoli", LocalDate.of(1971, 1, 2), "andr1971@hotmail.it", "sa5618aspq", 40000);
			addSupVidDS.doSave(addSupVidBean2, "VRRMSM99S23F839O", "Massimo", "Varriale", LocalDate.of(1999, 11, 23), "max.va10@gmail.com", "CiAo!@1999", 37000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        /*
        AcqContieneAbb aca = new AcqContieneAbbDS(ds);
        AcqContieneVid acv = new AcqContieneVidDS(ds);
        Acquisti acq = new AcquistiDS(ds);
        Fattura fat = new FatturaDS(ds);
                
        int id = 123;
        
       
        Abbonamento abb = new AbbonamentoDS(ds);
        AbbonamentoBean abbBean1 = new AbbonamentoBean("xbox pass",30,15);
        AbbonamentoBean abbBean2 = new AbbonamentoBean("playstation pass",30,25);

        SoftwareHouse soft = new SoftwareHouseDS(ds);
        SoftwareHouseBean softBean = new SoftwareHouseBean("ea sports", "usa", LocalDate.of(1995, 10, 3));

        Videogioco vid = new VideogiocoDS(ds);
        VideogiocoBean vidBean1 = new VideogiocoBean("asc5", "ea sports", "Fifa 21", 10000, 2015, 50, Pegi.tre);
        VideogiocoBean vidBean2 = new VideogiocoBean("ads465", "ea sports", "rocket", 645, 2015, 60, Pegi.tre);
        VideogiocoBean vidBean3 = new VideogiocoBean("cas6541", "ea sports", "ufc", 64544, 2015, 30, Pegi.tre);

        SupervisoreVideogiochi sup = new SupervisoreVideogiochiDS(ds);
        SupervisoreVideogiochiBean supBean = new SupervisoreVideogiochiBean("acs4csa45", "ciro", "Vitale", LocalDate.of(2001, 10, 3), "ciro@email", "passwrod", 51000);

        AddInAbb add = new AddInAbbDS(ds);
        AddInAbbBean addBean1 = new AddInAbbBean("acs4csa45", "asc5", "xbox pass");
        AddInAbbBean addBean2 = new AddInAbbBean("acs4csa45", "ads465", "xbox pass");
        AddInAbbBean addBean3 = new AddInAbbBean("acs4csa45", "cas6541", "xbox pass");
        AddInAbbBean addBean4 = new AddInAbbBean("acs4csa45", "cas6541", "playstation pass");
        AddInAbbBean addBean5 = new AddInAbbBean("acs4csa45", "asc5", "playstation pass"); 
        
 
        try {
        	
        	abb.doSave(abbBean1);
			abb.doSave(abbBean2);
			soft.doSave(softBean);
			vid.doSave(vidBean1);
			vid.doSave(vidBean2);
			vid.doSave(vidBean3);
			sup.doSave(supBean);
			add.doSave(addBean1);
			add.doSave(addBean2);
			add.doSave(addBean3);
			add.doSave(addBean4);
			add.doSave(addBean5);
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		*/
        
    
        
        /*
        RemInAbb remAbb = new RemInAbbDS(ds);
        RemInAbbBean remBean = new RemInAbbBean("acs4csa45", "cas6541", "donato cuozzo");
        
        ModAbb modAbb = new ModAbbDS(ds);
        ModAbbBean modBean = new ModAbbBean("acs4csa45", "alfredo cuozzo");
        
        AddSupVid addSupVid = new AddSupVidDS(ds);
        AddSupVidBean addSupVidBean = new AddSupVidBean("mioCodiceFisc","ABCD123");
        
        RemSupVid remSupVid = new RemSupVidDS(ds);
        RemSupVidBean remSupVidBean = new RemSupVidBean("acs4csa45","ABCD123");
        
        */

        
        //Tickets tick = new TicketsDS(ds)
        
        /*
        ModAbb modAbb = new ModAbbDS(ds);
        ModAbbBean bean1 = new ModAbbBean("acs4csa45", "alfredo cuozzo");
        ModAbbBean bean2 = new ModAbbBean("acs4csa45", "donato cuozzo");
        */
        
        /*
        RemAssCl remAssCl = new RemAssClDS(ds);
        RemAssClBean remAssClBean = new RemAssClBean("sballo","ABCD123");
        */
        
        /*
        Acquisti acq = new AcquistiDS(ds);
        //AcquistiBean acqBean = new AcquistiBean(123,"CODICE","CJDEJFKMWOI3",30,70,LocalDateTime.of(2022,10,14,17,34,21),null,5333122);
        AcqContieneVid acqVid = new AcqContieneVidDS(ds);
        AcqContieneAbb acqAbb = new AcqContieneAbbDS(ds);
        
        RemVideog rem = new RemVideogDS(ds);
        RemVideogBean beanR = new RemVideogBean("acs4csa45", "asc5");
        
        ModVideog modVid = new ModVideogDS(ds);
        ModVideogBean modBean = new ModVideogBean("acs4csa45", "ads465");
        
        Fattura fat = new FatturaDS(ds);
        
        try {
			acq.doSave(123,"GDFSAOIAFSJM", "CODICE", LocalDateTime.of(2022,10,14,17,34,21), 5333122L);
			acqVid.doSave(123, "ads465");
			acqVid.doSave(123, "asc5");
			acqAbb.doSave(123, "xbox pass");
			acq.doUpdate(123);
			
        	rem.doSave(beanR);
        	modVid.doSave(modBean, "ads465", "rocket", 645, 2015, 100, Pegi.tre, false);        	
			
        	
        	fat.doSave(123, LocalDateTime.now());
        	
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
        
        
        /*
        AddVideog addVid = new AddVideogDS(ds);
        AddVideogBean addVidBean1 = new AddVideogBean("berrfsdsd","ads4657");
        AddVideogBean addVidBean2 = new AddVideogBean("berrfsdsd","xcas65412");
        */
        
        /*
        try {
            /*
            abb.doSave(abbBean1);
            abb.doSave(abbBean2);
            soft.doSave(softBean);
            vid.doSave(vidBean1);
            vid.doSave(vidBean2);
            vid.doSave(vidBean3);
            sup.doSave(supBean);
            add.doSave(addBean1);
            add.doSave(addBean2);
            add.doSave(addBean3);
            add.doSave(addBean4);
            add.doSave(addBean5);
        	
            modAbb.doSave(bean1,"alfredo cuozzo",20,10);
        	modAbb.doSave(bean2,"donato cuozzo",40,100);
        	
        	addAssCl.doSave(addAssBean, "sballo", "Marco", "Maffeo", LocalDate.of(2002,1,7), "banana33@gmail.com", "fragola86", 2);;
        	
        	acq.doSave(acqBean);
        	acqVid.doSave(acqVidBean1);
        	acqVid.doSave(acqVidBean2);
        	acqAbb.doSave(acqAbbBean1);
        	acqAbb.doSave(acqAbbBean2);
        	addVid.doSave(addVidBean2);

        	addAssCl.doSave(addAssBean, "zzasf654", "Luigi", "Consiglio", LocalDate.of(2001, 1, 1), "em", "passw", 5000);
        	addVid.doSave(addVidBean2,"xcas65412", "ea sports", "moto gp", 645, 2020, 73, Pegi.sette);
        	*/
        	/*
        	acq.doSave(id, "CJDEJFKMWOI3", "RISCATTOX", LocalDateTime.now(), 654851544566L);
        	abb.doSave(contAbbBean1);
        	abb.doSave(contAbbBean);
        	vid.doSave(contVidBean);
        	acq.doUpdate(id);
        	
        
        	fat.doSave(id, LocalDateTime.now());
        	
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */
        
        
        

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
