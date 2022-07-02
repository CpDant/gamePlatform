package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import it.unisa.gp.model.bean.AddSupVidBean;

public interface AddSupVid {

	public void doSave(AddSupVidBean addSupBean, String codiceFiscale, String nome, String cognome, LocalDate dataNascita,
			String email, String passWord, int retribuzioneAnnuale) throws SQLException;
	
	public boolean doDelete(String supVid, String admin) throws SQLException;

	public AddSupVidBean doRetrieveByKey(String supVid, String admin) throws SQLException;
	
	public Collection<AddSupVidBean> doRetrieveAll(String order) throws SQLException;
	
	//metodo doUpdate non implementato poich� nella tabella ci sono solo chiavi
}
