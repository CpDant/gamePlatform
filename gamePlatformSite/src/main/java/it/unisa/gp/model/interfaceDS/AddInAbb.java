package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.AddInAbbBean;

public interface AddInAbb {

	public void doSave(AddInAbbBean addInAbb) throws SQLException;
	
	public boolean doDelete(String codiceVid, String name) throws SQLException;

	public AddInAbbBean doRetrieveByKey(String codiceVid, String name) throws SQLException;
	
	public Collection<AddInAbbBean> doRetrieveByKey(String name) throws SQLException;
	
	public Collection<AddInAbbBean> doRetrieveAll(String order) throws SQLException;
	
	//metodo doUpdate non implementato poich� nella tabella ci sono solo chiavi
}
