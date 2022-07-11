package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import it.unisa.gp.model.bean.AssistenteClientiBean;

public interface AssistenteClienti {
	public void doSave(AssistenteClientiBean ass) throws SQLException;
	
	public void doUpdate(AssistenteClientiBean sup, String nome, String cognome, LocalDate dataNascita,
			String email, String password, int retribuzioneAnnuale) throws SQLException;

	public boolean doDelete(String codiceFiscale) throws SQLException;

	public AssistenteClientiBean doRetrieveByKey(String codiceFiscale) throws SQLException;
	
	public AssistenteClientiBean doRetrieveByKeyEmail(String email) throws SQLException;
	
	public Collection<AssistenteClientiBean> doRetrieveAll(String order) throws SQLException;
}
