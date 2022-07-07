package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.ModVideogBean;
import it.unisa.gp.model.bean.VideogiocoBean.Pegi;

public interface ModVideog {

	public void doSave(ModVideogBean rem, String codice, String nomeVideogioco, int dimensione, int annoProduzione,
			int costo, Pegi pegi) throws SQLException;
	
	public boolean doDelete(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException;

	public ModVideogBean doRetrieveByKey(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException;
	
	public Collection<ModVideogBean> doRetrieveAll(String order) throws SQLException;
	
	//metodo doUpdate non implementato poich� nella tabella ci sono solo chiavi

}
