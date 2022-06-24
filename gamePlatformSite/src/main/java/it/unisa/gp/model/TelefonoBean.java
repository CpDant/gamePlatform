package it.unisa.gp.model;

import java.io.Serializable;
import java.util.Objects;

public class TelefonoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private long numero;
	private String codiceFiscaleCliente;
	
	public TelefonoBean(long numero, String codiceFiscaleCliente) {
		super();
		this.numero = numero;
		this.codiceFiscaleCliente = codiceFiscaleCliente;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getCodiceFiscaleCliente() {
		return codiceFiscaleCliente;
	}

	public void setCodiceFiscaleCliente(String codiceFiscaleCliente) {
		this.codiceFiscaleCliente = codiceFiscaleCliente;
	}

	public String toString() {
		return "TelefonoBean [numero=" + numero + ", codiceFiscaleCliente=" + codiceFiscaleCliente + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TelefonoBean other = (TelefonoBean) obj;
		return Objects.equals(codiceFiscaleCliente, other.codiceFiscaleCliente) && numero == other.numero;
	}
	
	
}
