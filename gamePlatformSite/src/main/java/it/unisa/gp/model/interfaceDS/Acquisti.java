package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

import it.unisa.gp.model.bean.AcquistiBean;

public interface Acquisti {

	public void doSave(int id, String codFisc, String codiceRiscatto,
			LocalDateTime dataOra, long numeroCartaPag) throws SQLException;
	
	public void doUpdate(AcquistiBean acq, String codRisc, int costIva, int costNetto, LocalDateTime dataOra, String indFatt, long numCartaPag) throws SQLException;
	
	public void doUpdate(int id) throws SQLException;
	
	public boolean doDelete(int id) throws SQLException;

	public AcquistiBean doRetrieveByKey(int id) throws SQLException;
	
	public Collection<AcquistiBean> doRetrieveAll(String order) throws SQLException;
}
