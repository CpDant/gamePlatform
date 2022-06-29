package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import it.unisa.gp.model.bean.TicketsBean;

public interface Tickets {

	public void doSave(TicketsBean tic) throws SQLException;
	
	public void doUpdate(TicketsBean tic, String codiceFiscaleAssistCl, String codiceFiscaleCliente, boolean resolved, String messaggio, LocalDate dataOra) throws SQLException;

	public boolean doDelete(int id) throws SQLException;

	public TicketsBean doRetrieveByKey(int id) throws SQLException;
	
	public Collection<TicketsBean> doRetrieveAll(String order) throws SQLException;
}
