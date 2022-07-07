package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.AddVideogBean;
import it.unisa.gp.model.bean.VideogiocoBean.Pegi;

public interface AddVideog {

	public void doSave(AddVideogBean add, String codice, String nomeSoftwareHouse, String nomeVideogioco, int dimensione,
			int annoDiProduzione, int costo, Pegi pegi) throws SQLException;
	
	public boolean doDelete(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException;

	public AddVideogBean doRetrieveByKey(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException;
	
	public Collection<AddVideogBean> doRetrieveAll(String order) throws SQLException;
	
	//metodo doUpdate non implementato poich� nella tabella ci sono solo chiavi
}
