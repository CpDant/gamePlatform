package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.AddAbbBean;

public interface AddAbb {

	public void doSave(AddAbbBean addBean, String nomeUnivoco, int costo, int durata) throws SQLException;
	
	public boolean doDelete(String codSup, String nome) throws SQLException;

	public AddAbbBean doRetrieveByKey(String codSup, String nome) throws SQLException;
	
	public Collection<AddAbbBean> doRetrieveAll(String order) throws SQLException;
}
