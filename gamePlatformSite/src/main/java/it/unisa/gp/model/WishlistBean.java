package it.unisa.gp.model;

import java.io.Serializable;
import java.util.Objects;

public class WishlistBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codiceFiscaleCliente;
	private int nProd;
	
	public WishlistBean(String codiceFiscaleCliente, int nProd) {
		super();
		this.codiceFiscaleCliente = codiceFiscaleCliente;
		this.nProd = nProd;
	}

	public String getCodiceFiscaleCliente() {
		return codiceFiscaleCliente;
	}

	public void setCodiceFiscaleCliente(String codiceFiscaleCliente) {
		this.codiceFiscaleCliente = codiceFiscaleCliente;
	}

	public int getnProd() {
		return nProd;
	}

	public void setnProd(int nProd) {
		this.nProd = nProd;
	}

	public String toString() {
		return "WishlistBean [codiceFiscaleCliente=" + codiceFiscaleCliente + ", nProd=" + nProd + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WishlistBean other = (WishlistBean) obj;
		return Objects.equals(codiceFiscaleCliente, other.codiceFiscaleCliente) && nProd == other.nProd;
	}
}
