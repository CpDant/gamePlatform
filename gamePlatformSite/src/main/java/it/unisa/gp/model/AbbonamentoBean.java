package it.unisa.gp.model;

import java.io.Serializable;
import java.util.Objects;

public class AbbonamentoBean implements Serializable{

	private String nomeUnivoco;
    private int costo;
    private int durata;
    
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

	public String toString() {
		return "AbbonamentoBean [nomeUnivoco=" + nomeUnivoco + ", costo=" + costo + ", durata=" + durata + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbbonamentoBean other = (AbbonamentoBean) obj;
		return costo == other.costo && durata == other.durata && Objects.equals(nomeUnivoco, other.nomeUnivoco);
	}

}
