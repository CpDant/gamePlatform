package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;
import it.unisa.gp.model.bean.RemAbbBean;

public interface RemAbb {
	public void doSave(RemAbbBean rem) throws SQLException;
	
	public boolean doDelete(String codiceFiscaleSupVid, String nomeAbb) throws SQLException;

	public RemAbbBean doRetrieveByKey(String codiceFiscaleSupVid, String nomeAbb) throws SQLException;
	
	public Collection<RemAbbBean> doRetrieveAll(String order) throws SQLException;
}
