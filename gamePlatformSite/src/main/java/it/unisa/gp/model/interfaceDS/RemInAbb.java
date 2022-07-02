package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.RemInAbbBean;

public interface RemInAbb {

	public void doSave(RemInAbbBean remInAbb) throws SQLException;
	
	public boolean doDelete(String codiceSup, String codiceVid, String name) throws SQLException;

	public RemInAbbBean doRetrieveByKey(String codiceSup, String codiceVid, String name) throws SQLException;
	
	public Collection<RemInAbbBean> doRetrieveAll(String order) throws SQLException;
	
	//metodo doUpdate non implementato poich� nella tabella ci sono solo chiavi
}
