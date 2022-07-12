package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

import javax.sql.DataSource;

import it.unisa.gp.model.DAO.AbbonamentoDS;
import it.unisa.gp.model.DAO.VideogiocoDS;
import it.unisa.gp.model.interfaceDS.Abbonamento;
import it.unisa.gp.model.interfaceDS.Videogioco;

public class Carrello implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Collection<VideogiocoBean> arrVidBean;
	private Collection<AbbonamentoBean> arrAbbBean;
	private int totale;
	private Videogioco vidDS;
	private Abbonamento abbDS;
	
	
	
	public Carrello(DataSource ds) {
		super();
		this.arrVidBean = new LinkedList<VideogiocoBean>();
		this.arrAbbBean = new LinkedList<AbbonamentoBean>();
		this.totale = 0;
		vidDS = new VideogiocoDS(ds);
		abbDS = new AbbonamentoDS(ds);
		
	}

	public Collection<VideogiocoBean> getArrVidBean() {
		return arrVidBean;
	}

	public Collection<AbbonamentoBean> getArrAbbBean() {
		return arrAbbBean;
	}

	public int getTotale() {
		return totale;
	}

	public void setTotale(int totale) {
		this.totale = totale;
	}

	@Override
	public String toString() {
		return "Carrello [arrVidBean=" + arrVidBean + ", arrAbbBean=" + arrAbbBean + ", totale=" + totale + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrello other = (Carrello) obj;
		return Objects.equals(arrAbbBean, other.arrAbbBean) && Objects.equals(arrVidBean, other.arrVidBean)
				&& totale == other.totale;
	}
	
	public void addVid(String codice) {
		VideogiocoBean vidBean = null;
		try {
			vidBean = vidDS.doRetrieveByKey(codice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arrVidBean.add(vidBean);
		totale += vidBean.getCosto();
	}
	
	public void addAbb(String nomeUnivoco) {
		AbbonamentoBean abbBean = null;
		try {
			abbBean = abbDS.doRetrieveByKey(nomeUnivoco);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arrAbbBean.add(abbBean);
		totale += abbBean.getCosto();
	}
	
	public void remVid(String codice) {
		VideogiocoBean vidBean = null;
		try {
			vidBean = vidDS.doRetrieveByKey(codice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arrVidBean.remove(vidBean);
		totale -= vidBean.getCosto();
	}
	
	public void remAbb(String nomeUnivoco) {
		AbbonamentoBean abbBean = null;
		try {
			abbBean = abbDS.doRetrieveByKey(nomeUnivoco);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arrAbbBean.remove(abbBean);
		totale -= abbBean.getCosto();
	}
	
	public void svuota() {
		arrVidBean = new LinkedList<VideogiocoBean>();;
		arrAbbBean = new LinkedList<AbbonamentoBean>();;
		totale = 0;
	}

}
