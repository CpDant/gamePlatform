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
import it.unisa.gp.model.DAO.AddAbbDS;
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
import it.unisa.gp.model.DAO.TelefonoDS;
import it.unisa.gp.model.DAO.AddInAbbDS;
import it.unisa.gp.model.DAO.AddSupVidDS;
import it.unisa.gp.model.DAO.AddVideogDS;
import it.unisa.gp.model.DAO.AdministratorsDS;
import it.unisa.gp.model.DAO.VideogiocoDS;
import it.unisa.gp.model.bean.AbbonamentoBean;
import it.unisa.gp.model.bean.AcqContieneAbbBean;
import it.unisa.gp.model.bean.AcqContieneVidBean;
import it.unisa.gp.model.bean.AcquistiBean;
import it.unisa.gp.model.bean.AddAbbBean;
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
import it.unisa.gp.model.bean.TelefonoBean;
import it.unisa.gp.model.bean.AddInAbbBean;
import it.unisa.gp.model.bean.AddSupVidBean;
import it.unisa.gp.model.bean.AddVideogBean;
import it.unisa.gp.model.bean.AdministratorsBean;
import it.unisa.gp.model.bean.VideogiocoBean;
import it.unisa.gp.model.bean.VideogiocoBean.Pegi;
import it.unisa.gp.model.interfaceDS.Abbonamento;
import it.unisa.gp.model.interfaceDS.AcqContieneAbb;
import it.unisa.gp.model.interfaceDS.AcqContieneVid;
import it.unisa.gp.model.interfaceDS.Acquisti;
import it.unisa.gp.model.interfaceDS.AddAbb;
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
import it.unisa.gp.model.interfaceDS.Telefono;
import it.unisa.gp.model.interfaceDS.AddInAbb;
import it.unisa.gp.model.interfaceDS.AddSupVid;
import it.unisa.gp.model.interfaceDS.AddVideog;
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
        SoftwareHouse softHouseDS = new SoftwareHouseDS(ds);
        AddVideog addVidDS = new AddVideogDS(ds);
        AddAbb addAbbDS = new AddAbbDS(ds);
        AddInAbb addInAbbDS = new AddInAbbDS(ds);
        Clienti clientiDS = new ClientiDS(ds);
        Telefono telDS = new TelefonoDS(ds);
        
        
        /*
        // Administrators
        AdministratorsBean adminBean = new AdministratorsBean("CZZLRD76L31F205Y", "Alfredo", "Cuozzo", LocalDate.of(1976, 7, 31), "alfred@email.it", "acs9q8s6", 40000);
        
        // Add Assistente Clienti
        AddAssClBean addAssClBean1 = new AddAssClBean("RSSMRA80C16H501O", "CZZLRD76L31F205Y");
        AddAssClBean addAssClBean2 = new AddAssClBean("DNGMHL95T28M082G", "CZZLRD76L31F205Y");
        
        // Add Supervisore Videogiochi
        AddSupVidBean addSupVidBean1 = new AddSupVidBean("NDRNCL71A02F205D", "CZZLRD76L31F205Y");
        AddSupVidBean addSupVidBean2 = new AddSupVidBean("VRRMSM99S23F839O", "CZZLRD76L31F205Y");
        
        // Software House
        SoftwareHouseBean softHouseBean1 = new SoftwareHouseBean("Ea Sports", "USA", LocalDate.of(1982, 5, 27));
        SoftwareHouseBean softHouseBean2 = new SoftwareHouseBean("Rockstar Games", "USA", LocalDate.of(1998, 12, 1));
        SoftwareHouseBean softHouseBean3 = new SoftwareHouseBean("Bethesda", "USA", LocalDate.of(1986, 7, 10));
        SoftwareHouseBean softHouseBean4 = new SoftwareHouseBean("Ubisoft", "France", LocalDate.of(1986, 3, 28));
        SoftwareHouseBean softHouseBean5 = new SoftwareHouseBean("Epic Games", "USA", LocalDate.of(1991, 1, 15));
        
        // Add Videogiochi
        AddVideogBean addVidBean1 = new AddVideogBean("NDRNCL71A02F205D", "ASC894Q3");
        AddVideogBean addVidBean2 = new AddVideogBean("VRRMSM99S23F839O", "Q9A632ZM");
        AddVideogBean addVidBean3 = new AddVideogBean("NDRNCL71A02F205D", "BVNMQ456");
        AddVideogBean addVidBean4 = new AddVideogBean("VRRMSM99S23F839O", "QPOL7896");
        AddVideogBean addVidBean5 = new AddVideogBean("NDRNCL71A02F205D", "ZZMLPO41");
        AddVideogBean addVidBean6 = new AddVideogBean("VRRMSM99S23F839O", "PLZX0000");
        AddVideogBean addVidBean7 = new AddVideogBean("VRRMSM99S23F839O", "1PLO32Q6");     
        AddVideogBean addVidBean8 = new AddVideogBean("NDRNCL71A02F205D", "D23T479P");
        AddVideogBean addVidBean9 = new AddVideogBean("VRRMSM99S23F839O", "34F67RR2");
        AddVideogBean addVidBean10 = new AddVideogBean("NDRNCL71A02F205D", "FD32SS16");
        AddVideogBean addVidBean11 = new AddVideogBean("NDRNCL71A02F205D", "PLO094FT");
        AddVideogBean addVidBean12 = new AddVideogBean("VRRMSM99S23F839O", "2SFNR5A1");
        AddVideogBean addVidBean13 = new AddVideogBean("NDRNCL71A02F205D", "MD09THZA");
        AddVideogBean addVidBean14 = new AddVideogBean("VRRMSM99S23F839O", "CVWE44P0");      
        AddVideogBean addVidBean15 = new AddVideogBean("NDRNCL71A02F205D", "RSS567V3");
        AddVideogBean addVidBean16 = new AddVideogBean("NDRNCL71A02F205D", "ACV458V1");
        AddVideogBean addVidBean17 = new AddVideogBean("VRRMSM99S23F839O", "FCR787U0");
        AddVideogBean addVidBean18 = new AddVideogBean("VRRMSM99S23F839O", "WDT712T8");
        AddVideogBean addVidBean19 = new AddVideogBean("VRRMSM99S23F839O", "TDT789T9");
        AddVideogBean addVidBean20 = new AddVideogBean("NDRNCL71A02F205D", "ACV345T1");
        
        // Add Abbonamenti
        AddAbbBean addAbbBean1 = new AddAbbBean("VRRMSM99S23F839O", "Silver Pass");
        AddAbbBean addAbbBean2 = new AddAbbBean("VRRMSM99S23F839O", "Gold Pass");
        AddAbbBean addAbbBean3 = new AddAbbBean("NDRNCL71A02F205D", "Platinum Pass");
        
        // Add In Abbonamenti
        AddInAbbBean addInAbbBean1 = new AddInAbbBean("VRRMSM99S23F839O", "ASC894Q3", "Silver Pass");
        AddInAbbBean addInAbbBean2 = new AddInAbbBean("VRRMSM99S23F839O", "PLZX0000", "Silver Pass");
        AddInAbbBean addInAbbBean3 = new AddInAbbBean("VRRMSM99S23F839O", "BVNMQ456", "Silver Pass");
        AddInAbbBean addInAbbBean4 = new AddInAbbBean("VRRMSM99S23F839O", "FD32SS16", "Silver Pass");
        
        AddInAbbBean addInAbbBean5 = new AddInAbbBean("VRRMSM99S23F839O", "FD32SS16", "Gold Pass");
        AddInAbbBean addInAbbBean6 = new AddInAbbBean("VRRMSM99S23F839O", "PLO094FT", "Gold Pass");
        AddInAbbBean addInAbbBean7 = new AddInAbbBean("VRRMSM99S23F839O", "ACV458V1", "Gold Pass");
        AddInAbbBean addInAbbBean8 = new AddInAbbBean("VRRMSM99S23F839O", "ACV345T1", "Gold Pass");
        AddInAbbBean addInAbbBean9 = new AddInAbbBean("VRRMSM99S23F839O", "TDT789T9", "Gold Pass");
        AddInAbbBean addInAbbBean10 = new AddInAbbBean("VRRMSM99S23F839O", "MD09THZA", "Gold Pass");
        AddInAbbBean addInAbbBean11 = new AddInAbbBean("VRRMSM99S23F839O", "CVWE44P0", "Gold Pass");
        AddInAbbBean addInAbbBean12 = new AddInAbbBean("VRRMSM99S23F839O", "FCR787U0", "Gold Pass");
        AddInAbbBean addInAbbBean13 = new AddInAbbBean("VRRMSM99S23F839O", "QPOL7896", "Gold Pass");
        AddInAbbBean addInAbbBean14 = new AddInAbbBean("VRRMSM99S23F839O", "BVNMQ456", "Gold Pass");
        
        AddInAbbBean addInAbbBean15 = new AddInAbbBean("NDRNCL71A02F205D", "ASC894Q3", "Platinum Pass");
        AddInAbbBean addInAbbBean16 = new AddInAbbBean("NDRNCL71A02F205D", "Q9A632ZM", "Platinum Pass");
        AddInAbbBean addInAbbBean17 = new AddInAbbBean("NDRNCL71A02F205D", "QPOL7896", "Platinum Pass");
        AddInAbbBean addInAbbBean18 = new AddInAbbBean("NDRNCL71A02F205D", "ZZMLPO41", "Platinum Pass");
        AddInAbbBean addInAbbBean19 = new AddInAbbBean("NDRNCL71A02F205D", "PLZX0000", "Platinum Pass");
        AddInAbbBean addInAbbBean20 = new AddInAbbBean("NDRNCL71A02F205D", "1PLO32Q6", "Platinum Pass");
        AddInAbbBean addInAbbBean21 = new AddInAbbBean("NDRNCL71A02F205D", "D23T479P", "Platinum Pass");
        AddInAbbBean addInAbbBean22 = new AddInAbbBean("NDRNCL71A02F205D", "34F67RR2", "Platinum Pass");
        AddInAbbBean addInAbbBean23 = new AddInAbbBean("NDRNCL71A02F205D", "FD32SS16", "Platinum Pass");
        AddInAbbBean addInAbbBean24 = new AddInAbbBean("NDRNCL71A02F205D", "PLO094FT", "Platinum Pass");
        AddInAbbBean addInAbbBean25 = new AddInAbbBean("NDRNCL71A02F205D", "2SFNR5A1", "Platinum Pass");
        AddInAbbBean addInAbbBean26 = new AddInAbbBean("NDRNCL71A02F205D", "MD09THZA", "Platinum Pass");
        AddInAbbBean addInAbbBean27 = new AddInAbbBean("NDRNCL71A02F205D", "CVWE44P0", "Platinum Pass");
        AddInAbbBean addInAbbBean28 = new AddInAbbBean("NDRNCL71A02F205D", "RSS567V3", "Platinum Pass");
        AddInAbbBean addInAbbBean29 = new AddInAbbBean("NDRNCL71A02F205D", "ACV458V1", "Platinum Pass");
        AddInAbbBean addInAbbBean30 = new AddInAbbBean("NDRNCL71A02F205D", "FCR787U0", "Platinum Pass");
        AddInAbbBean addInAbbBean31 = new AddInAbbBean("NDRNCL71A02F205D", "WDT712T8", "Platinum Pass");
        AddInAbbBean addInAbbBean32 = new AddInAbbBean("NDRNCL71A02F205D", "ACV345T1", "Platinum Pass");
        */
        
        // Clienti e telefoni
        ClientiBean clientiBean1 = new ClientiBean("VTLCRI01H12H703D", "Ciro", "Vitale", LocalDate.of(2001, 6, 12), "cirovit@gmail.com", "as894c65a1", "pipita089", "via Roma 1");
        ClientiBean clientiBean2 = new ClientiBean("DNTFNC01A07H703I", "Francesco Paolo", "D'Antuono", LocalDate.of(2001, 1, 7), "cpdant@email.it", "piuyu3232", "cpDant", "Via De Gasperi 36A");
        ClientiBean clientiBean3 = new ClientiBean("CNSLGWE91M06H703", "Luigi", "Consiglio", LocalDate.of(2001, 9, 6), "luicons79@gmail.com", "passwordStrafiga", "luicons", "Via Uffizi Romani 7");
        ClientiBean clientiBean4 = new ClientiBean("MSCCRS01M06F839W", "Christian", "Mascolo", LocalDate.of(2001, 8, 6), "crMa@gmail.com", "asgteeta", "MascoloC11", "Via Degli Alburni 15");
        ClientiBean clientiBean5 = new ClientiBean("MRNRRT00A01H703C", "Roberto", "Miron", LocalDate.of(2000, 01, 19), "RobMir@gmail.com", "fsadjfhas", "elMiron00", "Via Milano 5");
        
        TelefonoBean telBean1 = new TelefonoBean(3891569700L, "VTLCRI01H12H703D");
        TelefonoBean telBean2 = new TelefonoBean(3891932217L, "VTLCRI01H12H703D");
        TelefonoBean telBean3 = new TelefonoBean(3335696522L, "DNTFNC01A07H703I");
        TelefonoBean telBean4 = new TelefonoBean(3452395632L, "CNSLGWE91M06H703");
        TelefonoBean telBean5 = new TelefonoBean(3332678916L, "MRNRRT00A01H703C");
        TelefonoBean telBean6 = new TelefonoBean(3885468749L, "MRNRRT00A01H703C");
        TelefonoBean telBean7 = new TelefonoBean(3713178692L, "MSCCRS01M06F839W");
        
        // Aggiunta al DB
        try {
        	/*
        	// Administrators
			adminDS.doSave(adminBean);
			
			// Assistente Clienti
			addAssClDS.doSave(addAssClBean1, "RSSMRA80C16H501O", "Mario", "Rossi", LocalDate.of(1980, 3, 16), "mar.rossi@gmail.com", "1324pass!@", 32000);
			addAssClDS.doSave(addAssClBean2, "DNGMHL95T28M082G", "Michele", "De Angelis", LocalDate.of(1995, 12, 28), "dex.ang@libero.it", "99acm!mila", 35000);
			
			// Supervisore Videogiochi
			addSupVidDS.doSave(addSupVidBean1, "NDRNCL71A02F205D", "Nicola", "Andreazzoli", LocalDate.of(1971, 1, 2), "andr1971@hotmail.it", "sa5618aspq", 40000);
			addSupVidDS.doSave(addSupVidBean2, "VRRMSM99S23F839O", "Massimo", "Varriale", LocalDate.of(1999, 11, 23), "max.va10@gmail.com", "CiAo!@1999", 37000);
			
			// Software House
			softHouseDS.doSave(softHouseBean1);
			softHouseDS.doSave(softHouseBean2);
			softHouseDS.doSave(softHouseBean3);
			softHouseDS.doSave(softHouseBean4);
			softHouseDS.doSave(softHouseBean5);
			
			// Videogiochi
			addVidDS.doSave(addVidBean1, "ASC894Q3", "Bethesda", "Skyrim", 40, 2011, 50, Pegi.diciotto);
			addVidDS.doSave(addVidBean2, "Q9A632ZM", "Bethesda", "Fallout 4", 60, 2015, 45, Pegi.diciotto);
			addVidDS.doSave(addVidBean3, "BVNMQ456", "Ea Sports", "FIFA 22", 55, 2022, 28, Pegi.tre);
			addVidDS.doSave(addVidBean4, "QPOL7896", "Ea Sports", "F1 22", 10, 2022, 35, Pegi.tre);
			addVidDS.doSave(addVidBean5, "ZZMLPO41", "Ea Sports", "NHL 21", 26, 2022, 35, Pegi.tre);
			addVidDS.doSave(addVidBean6, "PLZX0000", "Epic Games", "Fortnite", 80, 2017, 40, Pegi.dodici);
			addVidDS.doSave(addVidBean7, "1PLO32Q6", "Epic Games", "Rocket League", 13, 2015, 40, Pegi.tre);
			addVidDS.doSave(addVidBean8, "D23T479P", "Epic Games", "Fall Guys", 22, 2019, 20, Pegi.tre);
			addVidDS.doSave(addVidBean9, "34F67RR2", "Epic Games", "Unreal Tournament", 20, 2017, 35, Pegi.sette);
			addVidDS.doSave(addVidBean10, "FD32SS16", "Rockstar Games", "GTA V", 80, 2013, 40, Pegi.diciotto);
			addVidDS.doSave(addVidBean11, "PLO094FT", "Rockstar Games", "Red Dead Redemption 2", 73, 2018, 60, Pegi.diciotto);
			addVidDS.doSave(addVidBean12, "2SFNR5A1", "Rockstar Games", "Bully", 14, 2006, 25, Pegi.diciotto);
			addVidDS.doSave(addVidBean13, "MD09THZA", "Rockstar Games", "Max Payne 3", 43, 2012, 40, Pegi.diciotto);
			addVidDS.doSave(addVidBean14, "CVWE44P0", "Rockstar Games", "The Italian Job", 7, 2001, 30, Pegi.diciotto);
			addVidDS.doSave(addVidBean15, "RSS567V3", "Ubisoft", "Rainbow Six Siege", 50, 2015, 30, Pegi.diciotto);
	        addVidDS.doSave(addVidBean16, "ACV458V1", "Ubisoft", "Assassin's Creed: Valhalla", 70, 2020, 45, Pegi.diciotto);
	        addVidDS.doSave(addVidBean17, "FCR787U0", "Ubisoft", "Far Cry 6", 90, 2021, 60, Pegi.diciotto);
	        addVidDS.doSave(addVidBean18, "WDT712T8", "Ubisoft", "Watch Dogs 2", 40, 2016, 15, Pegi.sedici);
	        addVidDS.doSave(addVidBean19, "TDT789T9", "Ubisoft", "The Division 2", 65, 2019, 35, Pegi.sedici);
	        addVidDS.doSave(addVidBean20, "ACV345T1", "Ubisoft", "Assassin's Creed: Odyssey", 55, 2018, 25, Pegi.diciotto);
	        
	        // Abbonamenti
        	addAbbDS.doSave(addAbbBean1, "Silver Pass", 20, 3);
        	addAbbDS.doSave(addAbbBean2, "Gold Pass", 50, 6);
        	addAbbDS.doSave(addAbbBean3, "Platinum Pass", 80, 12);
        	
        	// Add in Abbonamenti
        	addInAbbDS.doSave(addInAbbBean1);
        	addInAbbDS.doSave(addInAbbBean2);
        	addInAbbDS.doSave(addInAbbBean3);
        	addInAbbDS.doSave(addInAbbBean4);
        	addInAbbDS.doSave(addInAbbBean5);
        	addInAbbDS.doSave(addInAbbBean6);
        	addInAbbDS.doSave(addInAbbBean7);
        	addInAbbDS.doSave(addInAbbBean8);
        	addInAbbDS.doSave(addInAbbBean9);
        	addInAbbDS.doSave(addInAbbBean10);
        	addInAbbDS.doSave(addInAbbBean11);
        	addInAbbDS.doSave(addInAbbBean12);
        	addInAbbDS.doSave(addInAbbBean13);
        	addInAbbDS.doSave(addInAbbBean14);
        	addInAbbDS.doSave(addInAbbBean15);
        	addInAbbDS.doSave(addInAbbBean16);
        	addInAbbDS.doSave(addInAbbBean17);
        	addInAbbDS.doSave(addInAbbBean18);
        	addInAbbDS.doSave(addInAbbBean19);
        	addInAbbDS.doSave(addInAbbBean20);
        	addInAbbDS.doSave(addInAbbBean21);
        	addInAbbDS.doSave(addInAbbBean22);
        	addInAbbDS.doSave(addInAbbBean23);
        	addInAbbDS.doSave(addInAbbBean24);
        	addInAbbDS.doSave(addInAbbBean25);
        	addInAbbDS.doSave(addInAbbBean26);
        	addInAbbDS.doSave(addInAbbBean27);
        	addInAbbDS.doSave(addInAbbBean28);
        	addInAbbDS.doSave(addInAbbBean29);
        	addInAbbDS.doSave(addInAbbBean30);
        	addInAbbDS.doSave(addInAbbBean31);
        	addInAbbDS.doSave(addInAbbBean32);
        	*/
        	// Clienti e telefoni
        	clientiDS.doSave(clientiBean1);
        	clientiDS.doSave(clientiBean2);
        	clientiDS.doSave(clientiBean3);
        	clientiDS.doSave(clientiBean4);
        	clientiDS.doSave(clientiBean5);
        	
        	telDS.doSave(telBean1);
        	telDS.doSave(telBean2);
        	telDS.doSave(telBean3);
        	telDS.doSave(telBean4);
        	telDS.doSave(telBean5);
        	telDS.doSave(telBean6);
        	telDS.doSave(telBean7);
        	
        	
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
