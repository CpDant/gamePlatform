package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.DispositivoBean;

public interface Dispositivo {

	//metodo doUpdate non implementato poiché non possibile aggiornare tuple
	
	public void doSave(DispositivoBean wish) throws SQLException;
	
	public boolean doDelete(String name, String cod) throws SQLException;

	public DispositivoBean doRetrieveByKey(String name, String cod) throws SQLException;
	
	public Collection<DispositivoBean> doRetrieveAll(String order) throws SQLException;
	
}
