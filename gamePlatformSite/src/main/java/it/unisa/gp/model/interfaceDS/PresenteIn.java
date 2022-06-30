package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.PresenteInBean;

public interface PresenteIn {

	public void doSave(PresenteInBean pres) throws SQLException;
	
	public boolean doDelete(String name, String codice) throws SQLException;

	public PresenteInBean doRetrieveByKey(String name,String codice) throws SQLException;
	
	public Collection<PresenteInBean> doRetrieveAll(String order) throws SQLException;
	
	//metodo doUpdate non implementato poiché nella tabella ci sono solo chiavi
}
