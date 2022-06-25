package it.unisa.gp.model.bean;
import java.io.Serializable;
import java.util.Objects;

public class WishContieneAbbBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codiceFiscaleCliente;
	private String nomeUnivocoAbb;
	
	public WishContieneAbbBean(String codiceFiscaleCliente, String nomeUnivocoAbb) {
		super();
		this.codiceFiscaleCliente = codiceFiscaleCliente;
		this.nomeUnivocoAbb = nomeUnivocoAbb;
	}

	public String getCodiceFiscaleCliente() {
		return codiceFiscaleCliente;
	}

	public void setCodiceFiscaleCliente(String codiceFiscaleCliente) {
		this.codiceFiscaleCliente = codiceFiscaleCliente;
	}

	public String getNomeUnivocoAbb() {
		return nomeUnivocoAbb;
	}

	public void setNomeUnivocoAbb(String nomeUnivocoAbb) {
		this.nomeUnivocoAbb = nomeUnivocoAbb;
	}

	public String toString() {
		return "WishContieneAbbBean [codiceFiscaleCliente=" + codiceFiscaleCliente + ", nomeUnivocoAbb=" + nomeUnivocoAbb
				+ "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WishContieneAbbBean other = (WishContieneAbbBean) obj;
		return Objects.equals(codiceFiscaleCliente, other.codiceFiscaleCliente)
				&& Objects.equals(nomeUnivocoAbb, other.nomeUnivocoAbb);
	}

}
