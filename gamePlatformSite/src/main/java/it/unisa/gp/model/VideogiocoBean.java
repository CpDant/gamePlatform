package it.unisa.gp.model;

import java.io.Serializable;
import java.util.Objects;

public class VideogiocoBean implements Serializable {
	private String codice; 
    private String nomeSoftwareHouse;
    private String nomeVideogioco;
    private int dimensione;
    public  enum Pegi{tre,sette,dodici,sedici, diciotto};
    private int  annoDiProduzione;
    private int costo;
    
	public VideogiocoBean(String codice, String nomeSoftwareHouse, String nomeVideogioco, int dimensione,
			int annoDiProduzione, int costo) {
		super();
		this.codice = codice;
		this.nomeSoftwareHouse = nomeSoftwareHouse;
		this.nomeVideogioco = nomeVideogioco;
		this.dimensione = dimensione;
		this.annoDiProduzione = annoDiProduzione;
		this.costo = costo;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
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

	public String toString() {
		return "VideogiocoBean [codice=" + codice + ", nomeSoftwareHouse=" + nomeSoftwareHouse + ", nomeVideogioco="
				+ nomeVideogioco + ", dimensione=" + dimensione + ", annoDiProduzione=" + annoDiProduzione + ", costo="
				+ costo + "]";
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
				&& Objects.equals(nomeVideogioco, other.nomeVideogioco);
	}
	
}
