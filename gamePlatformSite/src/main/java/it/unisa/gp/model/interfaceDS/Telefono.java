package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.TelefonoBean;

public interface Telefono {

	public void doSave(TelefonoBean tel) throws SQLException;
	
	public void doUpdate(TelefonoBean tel, String codiceFiscaleCliente) throws SQLException;

	public boolean doDelete(long numero) throws SQLException;

	public TelefonoBean doRetrieveByKey(long numero) throws SQLException;
	
	public Collection<TelefonoBean> doRetrieveAll(String order) throws SQLException;
}
