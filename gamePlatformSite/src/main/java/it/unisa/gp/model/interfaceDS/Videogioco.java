package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;
import it.unisa.gp.model.bean.VideogiocoBean;
import it.unisa.gp.model.bean.VideogiocoBean.Pegi;

public interface Videogioco {
	public void doSave(VideogiocoBean vid) throws SQLException;
	
	public void doUpdate(VideogiocoBean vid, String nomeVideogioco, int dimensione, Pegi pegi,
			int annoProduzione, int costo) throws SQLException;

	public boolean doDelete(String codice) throws SQLException;

	public VideogiocoBean doRetrieveByKey(String codice) throws SQLException;
	
	public Collection<VideogiocoBean> doRetrieveAll(String order) throws SQLException;
}