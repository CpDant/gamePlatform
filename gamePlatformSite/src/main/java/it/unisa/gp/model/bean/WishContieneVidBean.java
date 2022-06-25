package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class WishContieneVidBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codiceFiscaleCliente;
	private String codiceVideogioco;
	
	public WishContieneVidBean(String codiceFiscaleCliente, String codiceVideogioco) {
		super();
		this.codiceFiscaleCliente = codiceFiscaleCliente;
		this.codiceVideogioco = codiceVideogioco;
	}

	public String getCodiceFiscaleCliente() {
		return codiceFiscaleCliente;
	}

	public void setCodiceFiscaleCliente(String codiceFiscaleCliente) {
		this.codiceFiscaleCliente = codiceFiscaleCliente;
	}

	public String getCodiceVideogioco() {
		return codiceVideogioco;
	}

	public void setCodiceVideogioco(String codiceVideogioco) {
		this.codiceVideogioco = codiceVideogioco;
	}

	public String toString() {
		return "WishContieneVidBean [codiceFiscaleCliente=" + codiceFiscaleCliente + ", codiceVideogioco="
				+ codiceVideogioco + "]";
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WishContieneVidBean other = (WishContieneVidBean) obj;
		return Objects.equals(codiceFiscaleCliente, other.codiceFiscaleCliente)
				&& Objects.equals(codiceVideogioco, other.codiceVideogioco);
	}
	
}
