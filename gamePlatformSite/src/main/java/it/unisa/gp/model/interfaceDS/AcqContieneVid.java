package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.AcqContieneVidBean;

public interface AcqContieneVid {

	public void doSave(AcqContieneVidBean acqVid) throws SQLException;
	
	public boolean doDelete(int id, String codiceVid) throws SQLException;

	public AcqContieneVidBean doRetrieveByKey(int id, String codiceVid) throws SQLException;
	
	public Collection<AcqContieneVidBean> doRetrieveAll(String order) throws SQLException;
	
	//metodo doUpdate non implementato poich� nella tabella ci sono solo chiavi
}
