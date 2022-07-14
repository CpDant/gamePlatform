package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.AziendaBean;

public interface Azienda {

	public void doSave(AziendaBean azi) throws SQLException;
	
	public void doUpdate(AziendaBean azi, String sdi, String pec) throws SQLException;

	public boolean doDelete(String pIva) throws SQLException;

	public AziendaBean doRetrieveByKey(String pIva) throws SQLException;
	
	public AziendaBean doRetrieveByKeyCodFis(String codFis) throws SQLException;
	
	public Collection<AziendaBean> doRetrieveAll(String order) throws SQLException;

}
