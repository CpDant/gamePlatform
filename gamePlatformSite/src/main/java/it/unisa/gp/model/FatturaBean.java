package it.unisa.gp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class FatturaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private int numero;
	private int costoIva;
	private int costoNetto;
	LocalDateTime dataOra;
	private String indFatt;
	
	public FatturaBean(int id, int numero, int costoIva, int costoNetto, LocalDateTime dataOra, String indFatt) {
		super();
		this.id = id;
		this.numero = numero;
		this.costoIva = costoIva;
		this.costoNetto = costoNetto;
		this.dataOra = dataOra;
		this.indFatt = indFatt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCostoIva() {
		return costoIva;
	}

	public void setCostoIva(int costoIva) {
		this.costoIva = costoIva;
	}

	public int getCostoNetto() {
		return costoNetto;
	}

	public void setCostoNetto(int costoNetto) {
		this.costoNetto = costoNetto;
	}

	public LocalDateTime getDataOra() {
		return dataOra;
	}

	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}

	public String getIndFatt() {
		return indFatt;
	}

	public void setIndFatt(String indFatt) {
		this.indFatt = indFatt;
	}

	public String toString() {
		return "FatturaBean [id=" + id + ", numero=" + numero + ", costoIva=" + costoIva + ", costoNetto=" + costoNetto
				+ ", dataOra=" + dataOra + ", indFatt=" + indFatt + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FatturaBean other = (FatturaBean) obj;
		return costoIva == other.costoIva && costoNetto == other.costoNetto && Objects.equals(dataOra, other.dataOra)
				&& id == other.id && Objects.equals(indFatt, other.indFatt) && numero == other.numero;
	}
	
	
}
