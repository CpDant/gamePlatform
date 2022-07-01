package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.ModVideogBean;

public interface ModVideog {

	public void doSave(ModVideogBean rem) throws SQLException;
	
	public boolean doDelete(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException;

	public ModVideogBean doRetrieveByKey(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException;
	
	public Collection<ModVideogBean> doRetrieveAll(String order) throws SQLException;
	
	//metodo doUpdate non implementato poiché nella tabella ci sono solo chiavi

}
