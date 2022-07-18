package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

import it.unisa.gp.model.bean.FatturaBean;

public interface Fattura {
	public void doSave(int id, LocalDateTime dataOra) throws SQLException;
	
	public void doUpdate(FatturaBean fat, int costoIva, int costoNetto, LocalDateTime dataOra, String indFatt) throws SQLException;

	public boolean doDelete(int id, int numero) throws SQLException;

	public FatturaBean doRetrieveByKey(int id, int numero) throws SQLException;
	
	public FatturaBean doRetrieveByKeyAcquisti(int codiceAcquisto) throws SQLException;
	
	public Collection<FatturaBean> doRetrieveAll(String order) throws SQLException;
}
