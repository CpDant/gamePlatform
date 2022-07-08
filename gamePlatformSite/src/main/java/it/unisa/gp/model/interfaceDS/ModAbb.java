package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;


import it.unisa.gp.model.bean.ModAbbBean;

public interface ModAbb {

	public void doSave(ModAbbBean modBean, String nomeUnivoco, int costo, int durata,  boolean eliminato) throws SQLException;
	
	public boolean doDelete(String codSup, String nome) throws SQLException;

	public ModAbbBean doRetrieveByKey(String codSup, String nome) throws SQLException;
	
	public Collection<ModAbbBean> doRetrieveAll(String order) throws SQLException;
	
	//metodo doUpdate non implementato poich� nella tabella ci sono solo chiavi
}
