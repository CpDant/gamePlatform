package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class AziendaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String pIva;
	private String codiceFiscaleCliente;
	private String sdi;
	private String pec;
	
	public AziendaBean(String pIva, String codiceFiscaleCliente, String sdi, String pec) {
		super();
		this.pIva = pIva;
		this.codiceFiscaleCliente = codiceFiscaleCliente;
		this.sdi = sdi;
		this.pec = pec;
	}

	public String getpIva() {
		return pIva;
	}

	public void setpIva(String pIva) {
		this.pIva = pIva;
	}

	public String getCodiceFiscaleCliente() {
		return codiceFiscaleCliente;
	}

	public void setCodiceFiscaleCliente(String codiceFiscaleCliente) {
		this.codiceFiscaleCliente = codiceFiscaleCliente;
	}

	public String getSdi() {
		return sdi;
	}

	public void setSdi(String sdi) {
		this.sdi = sdi;
	}

	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	public String toString() {
		return "AziendaBean [pIva=" + pIva + ", codiceFiscaleCliente=" + codiceFiscaleCliente + ", sdi=" + sdi + ", pec="
				+ pec + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AziendaBean other = (AziendaBean) obj;
		return Objects.equals(codiceFiscaleCliente, other.codiceFiscaleCliente) && Objects.equals(pIva, other.pIva)
				&& Objects.equals(pec, other.pec) && Objects.equals(sdi, other.sdi);
	}
}
