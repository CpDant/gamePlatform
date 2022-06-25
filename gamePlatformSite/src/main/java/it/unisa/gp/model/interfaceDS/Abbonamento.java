package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.AbbonamentoBean;


public interface Abbonamento {

	public void doSave(AbbonamentoBean product) throws SQLException;
	
	public void doUpdate(AbbonamentoBean product, int costo, int durata) throws SQLException;

	public boolean doDelete(String name) throws SQLException;

	public AbbonamentoBean doRetrieveByKey(String name) throws SQLException;
	
	public Collection<AbbonamentoBean> doRetrieveAll(String order) throws SQLException;
}
