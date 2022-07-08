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
import it.unisa.gp.model.DAO.AddAssClDS;
import it.unisa.gp.model.DAO.AssistenteClientiDS;
import it.unisa.gp.model.DAO.ClientiDS;
import it.unisa.gp.model.DAO.FatturaDS;
import it.unisa.gp.model.DAO.ModAbbDS;
import it.unisa.gp.model.DAO.RemInAbbDS;
import it.unisa.gp.model.DAO.RemSupVidDS;
import it.unisa.gp.model.DAO.RemVideogDS;
import it.unisa.gp.model.DAO.SoftwareHouseDS;
import it.unisa.gp.model.DAO.SupervisoreVideogiochiDS;
import it.unisa.gp.model.DAO.AddInAbbDS;
import it.unisa.gp.model.DAO.AddSupVidDS;
import it.unisa.gp.model.DAO.VideogiocoDS;
import it.unisa.gp.model.bean.AbbonamentoBean;
import it.unisa.gp.model.bean.AcquistiBean;
import it.unisa.gp.model.bean.AddAssClBean;
import it.unisa.gp.model.bean.AssistenteClientiBean;
import it.unisa.gp.model.bean.ClientiBean;
import it.unisa.gp.model.bean.ModAbbBean;
import it.unisa.gp.model.bean.RemInAbbBean;
import it.unisa.gp.model.bean.RemSupVidBean;
import it.unisa.gp.model.bean.RemVideogBean;
import it.unisa.gp.model.bean.SoftwareHouseBean;
import it.unisa.gp.model.bean.SupervisoreVideogiochiBean;
import it.unisa.gp.model.bean.AddInAbbBean;
import it.unisa.gp.model.bean.AddSupVidBean;
import it.unisa.gp.model.bean.VideogiocoBean;
import it.unisa.gp.model.bean.VideogiocoBean.Pegi;
import it.unisa.gp.model.interfaceDS.Abbonamento;
import it.unisa.gp.model.interfaceDS.Acquisti;
import it.unisa.gp.model.interfaceDS.AddAssCl;
import it.unisa.gp.model.interfaceDS.AssistenteClienti;
import it.unisa.gp.model.interfaceDS.Clienti;
import it.unisa.gp.model.interfaceDS.Fattura;
import it.unisa.gp.model.interfaceDS.ModAbb;
import it.unisa.gp.model.interfaceDS.RemInAbb;
import it.unisa.gp.model.interfaceDS.RemSupVid;
import it.unisa.gp.model.interfaceDS.RemVideog;
import it.unisa.gp.model.interfaceDS.SoftwareHouse;
import it.unisa.gp.model.interfaceDS.SupervisoreVideogiochi;
import it.unisa.gp.model.interfaceDS.AddInAbb;
import it.unisa.gp.model.interfaceDS.AddSupVid;
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
        
        AcqContieneAbb abb = new AcqContieneAbbDS(ds);
        AcqContieneVid vid = new AcqContieneVidDS(ds);
        Acquisti acq = new AcquistiDS(ds);
        Fattura fat = new FatturaDS(ds);
        
        
        
        int id = 123;
        
        
        /*
        AcqContieneAbbBean contAbbBean = new AcqContieneAbbBean(id, "alfredo cuozzo");
        AcqContieneAbbBean contAbbBean1 = new AcqContieneAbbBean(id, "donato cuozzo");
        
        AcqContieneVidBean contVidBean = new AcqContieneVidBean(id, "asc5");
        */
        
        /*
        Abbonamento abb = new AbbonamentoDS(ds);
        AbbonamentoBean abbBean1 = new AbbonamentoBean("donato cuozzo",30,50);
        AbbonamentoBean abbBean2 = new AbbonamentoBean("alfredo cuozzo",30,50);

        SoftwareHouse soft = new SoftwareHouseDS(ds);
        SoftwareHouseBean softBean = new SoftwareHouseBean("ea sports", "usa", LocalDate.of(1995, 10, 3));

        Videogioco vid = new VideogiocoDS(ds);
        VideogiocoBean vidBean1 = new VideogiocoBean("asc5", "ea sports", "Fifa 21", 10000, 2015, 50, Pegi.tre);
        VideogiocoBean vidBean2 = new VideogiocoBean("ads465", "ea sports", "rocket", 645, 2015, 60, Pegi.tre);
        VideogiocoBean vidBean3 = new VideogiocoBean("cas6541", "ea sports", "ufc", 64544, 2015, 30, Pegi.tre);

        SupervisoreVideogiochi sup = new SupervisoreVideogiochiDS(ds);
        SupervisoreVideogiochiBean supBean = new SupervisoreVideogiochiBean("acs4csa45", "ciro", "Vitale", LocalDate.of(2001, 10, 3), "ciro@email", "passwrod", 51000);

        AddInAbb add = new AddInAbbDS(ds);
        AddInAbbBean addBean1 = new AddInAbbBean("acs4csa45", "asc5", "donato cuozzo");
        AddInAbbBean addBean2 = new AddInAbbBean("acs4csa45", "ads465", "donato cuozzo");
        AddInAbbBean addBean3 = new AddInAbbBean("acs4csa45", "cas6541", "donato cuozzo");
        AddInAbbBean addBean4 = new AddInAbbBean("acs4csa45", "cas6541", "alfredo cuozzo");
        AddInAbbBean addBean5 = new AddInAbbBean("acs4csa45", "asc5", "alfredo cuozzo");
<<<<<<< HEAD
=======
        */
>>>>>>> branch 'master' of https://github.com/CpDant/gamePlatform.git
        
        /*
        RemVideog rem = new RemVideogDS(ds);
        RemVideogBean beanR = new RemVideogBean("acs4csa45", "cas6541");
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
        AddAssCl addAssCl = new AddAssClDS(ds);
<<<<<<< HEAD
        AddAssClBean addAssBean = new AddAssClBean("zzasf654", "ABCD123");
=======
        AddAssClBean addAssBean = new AddAssClBean("sballo", "ABCD123");
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
        AcquistiBean acqBean = new AcquistiBean(2,"CODICE","CJDEJFKMWOI3",30,70,LocalDateTime.of(2022,10,14,17,34,21),null,5333122);
        */
      
        /*
        AcqContieneVid acqVid = new AcqContieneVidDS(ds);
        AcqContieneVidBean acqVidBean1 = new AcqContieneVidBean(1,"ads465");
        AcqContieneVidBean acqVidBean2 = new AcqContieneVidBean(2,"asc5");
        
        
        AcqContieneAbb acqAbb = new AcqContieneAbbDS(ds);
        AcqContieneAbbBean acqAbbBean1 = new AcqContieneAbbBean(1,"alfredo cuozzo");
        AcqContieneAbbBean acqAbbBean2 = new AcqContieneAbbBean(2,"donato cuozzo");
        */
        
        /*
        AddVideog addVid = new AddVideogDS(ds);
        AddVideogBean addVidBean1 = new AddVideogBean("berrfsdsd","ads4657");
        AddVideogBean addVidBean2 = new AddVideogBean("berrfsdsd","xcas65412");
        */
        
>>>>>>> branch 'master' of https://github.com/CpDant/gamePlatform.git
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
<<<<<<< HEAD
=======
        	modAbb.doSave(bean1,"alfredo cuozzo",20,10);
        	modAbb.doSave(bean2,"donato cuozzo",40,100);
        	
        	addAssCl.doSave(addAssBean, "sballo", "Marco", "Maffeo", LocalDate.of(2002,1,7), "banana33@gmail.com", "fragola86", 2);;
        	
        	acq.doSave(acqBean);
        	acqVid.doSave(acqVidBean1);
        	acqVid.doSave(acqVidBean2);
        	acqAbb.doSave(acqAbbBean1);
        	acqAbb.doSave(acqAbbBean2);
        	addVid.doSave(addVidBean2);
>>>>>>> branch 'master' of https://github.com/CpDant/gamePlatform.git
        	
<<<<<<< HEAD
        	addAssCl.doSave(addAssBean, "zzasf654", "Luigi", "Consiglio", LocalDate.of(2001, 1, 1), "em", "passw", 5000);
=======
        	addVid.doSave(addVidBean2,"xcas65412", "ea sports", "moto gp", 645, 2020, 73, Pegi.sette);
        	*/
        	/*
        	acq.doSave(id, "CJDEJFKMWOI3", "RISCATTOX", LocalDateTime.now(), 654851544566L);
        	abb.doSave(contAbbBean1);
        	abb.doSave(contAbbBean);
        	vid.doSave(contVidBean);
        	acq.doUpdate(id);
        	*/
        	fat.doSave(id, LocalDateTime.now());
>>>>>>> branch 'master' of https://github.com/CpDant/gamePlatform.git
        	
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
