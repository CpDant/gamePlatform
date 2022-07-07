package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import it.unisa.gp.model.bean.RemAssClBean;

public interface RemAssCl {

	public void doSave(RemAssClBean remAssBean, String codiceFiscale) throws SQLException;
	
	public boolean doDelete(String assCl, String admin) throws SQLException;

	public RemAssClBean doRetrieveByKey(String assCl, String admin) throws SQLException;
	
	public Collection<RemAssClBean> doRetrieveAll(String order) throws SQLException;
}
