package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class VideogiocoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String codice; 
    private String nomeSoftwareHouse;
    private String nomeVideogioco;
    private int dimensione;
    public  enum Pegi{tre,sette,dodici,sedici, diciotto};
    private Pegi pegi;
    private int  annoDiProduzione;
    private int costo;
    private boolean eliminato = false;
    
	public VideogiocoBean(String codice, String nomeSoftwareHouse, String nomeVideogioco, int dimensione,
			int annoDiProduzione, int costo, Pegi pegi) {
		super();
		this.codice = codice;
		this.nomeSoftwareHouse = nomeSoftwareHouse;
		this.nomeVideogioco = nomeVideogioco;
		this.dimensione = dimensione;
		this.annoDiProduzione = annoDiProduzione;
		this.costo = costo;
		this.pegi = pegi;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public Pegi getPegi() {
		return pegi;
	}
	public void setPegi(Pegi pegi) {
		this.pegi = pegi;
	}
	public String getNomeSoftwareHouse() {
		return nomeSoftwareHouse;
	}
	public void setNomeSoftwareHouse(String nomeSoftwareHouse) {
		this.nomeSoftwareHouse = nomeSoftwareHouse;
	}
	public String getNomeVideogioco() {
		return nomeVideogioco;
	}
	public void setNomeVideogioco(String nomeVideogioco) {
		this.nomeVideogioco = nomeVideogioco;
	}
	public int getDimensione() {
		return dimensione;
	}
	public void setDimensione(int dimensione) {
		this.dimensione = dimensione;
	}
	public int getAnnoDiProduzione() {
		return annoDiProduzione;
	}
	public void setAnnoDiProduzione(int annoDiProduzione) {
		this.annoDiProduzione = annoDiProduzione;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	public boolean isEliminato() {
		return eliminato;
	}
	public void setEliminato(boolean eliminato) {
		this.eliminato = eliminato;
	}

	public String toString() {
		return "VideogiocoBean [codice=" + codice + ", nomeSoftwareHouse=" + nomeSoftwareHouse + ", nomeVideogioco="
				+ nomeVideogioco + ", dimensione=" + dimensione + ", pegi=" + pegi +", annoDiProduzione=" + annoDiProduzione + ", costo="
				+ costo + ", eliminato=" + eliminato +"]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideogiocoBean other = (VideogiocoBean) obj;
		return annoDiProduzione == other.annoDiProduzione && Objects.equals(codice, other.codice)
				&& costo == other.costo && dimensione == other.dimensione
				&& Objects.equals(nomeSoftwareHouse, other.nomeSoftwareHouse)
				&& Objects.equals(nomeVideogioco, other.nomeVideogioco)
				&& Objects.equals(pegi, other.pegi)
				&& eliminato == other.eliminato;
	}
	
}
