package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import it.unisa.gp.model.bean.AddAssClBean;

public interface AddAssCl {
	public void doSave(AddAssClBean addAssBean, String codiceFiscale, String nome, String cognome, LocalDate dataNascita,
			String email, String passWord, int retribuzioneAnnuale) throws SQLException;
	
	public boolean doDelete(String assCl, String admin) throws SQLException;

	public AddAssClBean doRetrieveByKey(String assCl, String admin) throws SQLException;
	
	public Collection<AddAssClBean> doRetrieveAll(String order) throws SQLException;
}
