package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

import it.unisa.gp.model.bean.RecensioneBean;
import it.unisa.gp.model.bean.RecensioneBean.Grado;

public interface Recensione {

	public void doSave(RecensioneBean rec) throws SQLException;
	
	public void doUpdate(RecensioneBean rec, LocalDateTime dataOraIns, String descrizione, Grado gradoDiApprezzamento) throws SQLException;

	public boolean doDelete(String codiceFiscaleCliente,  String codice) throws SQLException;

	public RecensioneBean doRetrieveByKey(String codiceFiscaleCliente,  String codice) throws SQLException;
	
	public Collection<RecensioneBean> doRetrieveAll(String order) throws SQLException;
}
