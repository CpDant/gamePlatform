package it.unisa.gp.model.bean;

import java.io.Serializable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

public class Carrello implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Collection<VideogiocoBean> arrVidBean;
	private Collection<AbbonamentoBean> arrAbbBean;
	private int totale;
	
	public Carrello() {
		super();
		this.arrVidBean = new LinkedList<VideogiocoBean>();
		this.arrAbbBean = new LinkedList<AbbonamentoBean>();
		this.totale = 0;
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
	
	public void addVid(VideogiocoBean vidBean) {
		arrVidBean.add(vidBean);
		totale += vidBean.getCosto();
	}
	
	public void addAbb(AbbonamentoBean abbBean) {
		arrAbbBean.add(abbBean);
		totale += abbBean.getCosto();
	}
	
	public void remVid(VideogiocoBean vidBean) {
		arrVidBean.remove(vidBean);
		totale -= vidBean.getCosto();
	}
	
	public void remAbb(AbbonamentoBean abbBean) {
		arrAbbBean.remove(abbBean);
		totale -= abbBean.getCosto();
	}
	
	public void svuota() {
		arrVidBean = new LinkedList<VideogiocoBean>();;
		arrAbbBean = new LinkedList<AbbonamentoBean>();;
		totale = 0;
	}

}
