package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.*;
import java.util.Collection;

import it.unisa.gp.model.bean.AbbonamentoBean;
import it.unisa.gp.model.bean.AdministratorsBean;

public interface Administrators {

	public void doSave(AdministratorsBean adm) throws SQLException;
	
	public void doUpdate(AdministratorsBean adm, String nome, String cognome, LocalDate localDate,
			String email, String passWord, int retribuzioneAnnuale) throws SQLException;

	public boolean doDelete(String codiceFiscale) throws SQLException;

	public AdministratorsBean doRetrieveByKey(String codiceFiscale) throws SQLException;
	
	public Collection<AdministratorsBean> doRetrieveAll(String order) throws SQLException;
}