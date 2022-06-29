package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import it.unisa.gp.model.bean.ClientiBean;

public interface Clienti {

	public void doSave(ClientiBean clienti) throws SQLException;
	
	public void doUpdate(ClientiBean clienti, String nome, String cognome, LocalDate dataNascita, String email, String passWord, String username, int vidFruibili, String indFatt, long numCartaPag) throws SQLException;

	public boolean doDelete(String name) throws SQLException;

	public ClientiBean doRetrieveByKey(String name) throws SQLException;
	
	public Collection<ClientiBean> doRetrieveAll(String order) throws SQLException;
}
