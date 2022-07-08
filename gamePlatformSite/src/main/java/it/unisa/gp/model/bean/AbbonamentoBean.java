package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class AbbonamentoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nomeUnivoco;
    private int costo;
    private int durata;
    private boolean eliminato = false; 
    
	public AbbonamentoBean(String nomeUnivoco, int costo, int durata) {
		super();
		this.nomeUnivoco = nomeUnivoco;
		this.costo = costo;
		this.durata = durata;
	}
	
	public String getNomeUnivoco() {
		return nomeUnivoco;
	}
	public void setNomeUnivoco(String nomeUnivoco) {
		this.nomeUnivoco = nomeUnivoco;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}

	public boolean isEliminato() {
		return eliminato;
	}

	public void setEliminato(boolean eliminato) {
		this.eliminato = eliminato;
	}
	
	
	public String toString() {
		return "AbbonamentoBean [nomeUnivoco=" + nomeUnivoco + ", costo=" + costo + ", durata=" + durata + ", eliminato=" +eliminato+ "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbbonamentoBean other = (AbbonamentoBean) obj;
		return costo == other.costo && durata == other.durata && Objects.equals(nomeUnivoco, other.nomeUnivoco) && eliminato == other.eliminato;
	}



}
