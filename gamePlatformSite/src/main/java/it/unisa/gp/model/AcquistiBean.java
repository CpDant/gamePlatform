package it.unisa.gp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class AcquistiBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String codiceRiscatto;
	private String codiceFiscaleCliente;
	private int costoIva;
	private int costoNetto;
	LocalDateTime dataOra;
	private String indFatt;
	private long numeroCartaPag;
	
	public AcquistiBean(int id, String codiceRiscatto, String codiceFiscaleCliente, int costoIva, int costoNetto,
			LocalDateTime dataOra, String indFatt, long numeroCartaPag) {
		super();
		this.id = id;
		this.codiceRiscatto = codiceRiscatto;
		this.codiceFiscaleCliente = codiceFiscaleCliente;
		this.costoIva = costoIva;
		this.costoNetto = costoNetto;
		this.dataOra = dataOra;
		this.indFatt = indFatt;
		this.numeroCartaPag = numeroCartaPag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodiceRiscatto() {
		return codiceRiscatto;
	}

	public void setCodiceRiscatto(String codiceRiscatto) {
		this.codiceRiscatto = codiceRiscatto;
	}

	public String getCodiceFiscaleCliente() {
		return codiceFiscaleCliente;
	}

	public void setCodiceFiscaleCliente(String codiceFiscaleCliente) {
		this.codiceFiscaleCliente = codiceFiscaleCliente;
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

	public long getNumeroCartaPag() {
		return numeroCartaPag;
	}

	public void setNumeroCartaPag(long numeroCartaPag) {
		this.numeroCartaPag = numeroCartaPag;
	}

	public String toString() {
		return "AcquistiBean [id=" + id + ", codiceRiscatto=" + codiceRiscatto + ", codiceFiscaleCliente="
				+ codiceFiscaleCliente + ", costoIva=" + costoIva + ", costoNetto=" + costoNetto + ", dataOra="
				+ dataOra + ", indFatt=" + indFatt + ", numeroCartaPag=" + numeroCartaPag + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcquistiBean other = (AcquistiBean) obj;
		return Objects.equals(codiceFiscaleCliente, other.codiceFiscaleCliente)
				&& Objects.equals(codiceRiscatto, other.codiceRiscatto) && costoIva == other.costoIva
				&& costoNetto == other.costoNetto && Objects.equals(dataOra, other.dataOra) && id == other.id
				&& Objects.equals(indFatt, other.indFatt) && numeroCartaPag == other.numeroCartaPag;
	}
}
