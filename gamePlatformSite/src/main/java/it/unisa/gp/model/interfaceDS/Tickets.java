package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

import it.unisa.gp.model.bean.TicketsBean;
import it.unisa.gp.model.bean.TicketsBean.CategoriaProbl;

public interface Tickets {

	public void doSave(TicketsBean tic) throws SQLException;
	
	public void doUpdate(TicketsBean tic, boolean resolved, CategoriaProbl categoriaProbl, String messaggio, LocalDateTime dataOra) throws SQLException;

	public boolean doDelete(int id) throws SQLException;

	public TicketsBean doRetrieveByKey(int id) throws SQLException;
	
	public Collection<TicketsBean> doRetrieveAll(String order) throws SQLException;
}
