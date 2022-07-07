package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.AcqContieneAbbBean;

public interface AcqContieneAbb {

	public void doSave(AcqContieneAbbBean acqAbb) throws SQLException;
	
	public boolean doDelete(int id, String codiceAbb) throws SQLException;

	public AcqContieneAbbBean doRetrieveByKey(int id, String codiceAbb) throws SQLException;
	
	public Collection<AcqContieneAbbBean> doRetrieveAll(String order) throws SQLException;
	
	//metodo doUpdate non implementato poich� nella tabella ci sono solo chiavi
}
