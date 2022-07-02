package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import it.unisa.gp.model.bean.RemSupVidBean;

public interface RemSupVid {

	public void doSave(RemSupVidBean addSupBean) throws SQLException;
	
	public boolean doDelete(String supVid, String admin) throws SQLException;

	public RemSupVidBean doRetrieveByKey(String supVid, String admin) throws SQLException;
	
	public Collection<RemSupVidBean> doRetrieveAll(String order) throws SQLException;
	
	//metodo doUpdate non implementato poich� nella tabella ci sono solo chiavi
}
