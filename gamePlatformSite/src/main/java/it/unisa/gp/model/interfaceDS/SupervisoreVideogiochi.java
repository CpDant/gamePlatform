package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import it.unisa.gp.model.bean.SupervisoreVideogiochiBean;


public interface SupervisoreVideogiochi {

	public void doSave(SupervisoreVideogiochiBean sup) throws SQLException;
	
	public void doUpdate(SupervisoreVideogiochiBean sup, String nome, String cognome, LocalDate dataNascita,
			String email, String password, int retribuzioneAnnuale) throws SQLException;

	public boolean doDelete(String codiceFiscale) throws SQLException;

	public SupervisoreVideogiochiBean doRetrieveByKey(String codiceFiscale) throws SQLException;
	
	public SupervisoreVideogiochiBean doRetrieveByKeyEmail(String email) throws SQLException;
	
	public Collection<SupervisoreVideogiochiBean> doRetrieveAll(String order) throws SQLException;
}
