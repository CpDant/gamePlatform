package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.RemVideogBean;

public interface RemVideog {

	public void doSave(RemVideogBean rem) throws SQLException;
	
	public boolean doDelete(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException;

	public RemVideogBean doRetrieveByKey(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException;
	
	public Collection<RemVideogBean> doRetrieveAll(String order) throws SQLException;
	
	//metodo doUpdate non implementato poiché nella tabella ci sono solo chiavi
}
