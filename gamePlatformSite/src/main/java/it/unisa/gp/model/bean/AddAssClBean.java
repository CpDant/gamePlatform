package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class AddAssClBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codiceFiscaleAssCl;
	private String codiceFiscaleAdmin;
	
	public AddAssClBean(String codiceFiscaleAssCl, String codiceFiscaleAdmin) {
		super();
		this.codiceFiscaleAssCl = codiceFiscaleAssCl;
		this.codiceFiscaleAdmin = codiceFiscaleAdmin;
	}

	public String getCodiceFiscaleAssCl() {
		return codiceFiscaleAssCl;
	}

	public void setCodiceFiscaleAssCl(String codiceFiscaleAssCl) {
		this.codiceFiscaleAssCl = codiceFiscaleAssCl;
	}

	public String getCodiceFiscaleAdmin() {
		return codiceFiscaleAdmin;
	}

	public void setCodiceFiscaleAdmin(String codiceFiscaleAdmin) {
		this.codiceFiscaleAdmin = codiceFiscaleAdmin;
	}

	public String toString() {
		return "AddAssClBean [codiceFiscaleAssCl=" + codiceFiscaleAssCl + ", codiceFiscaleAdmin=" + codiceFiscaleAdmin
				+ "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddAssClBean other = (AddAssClBean) obj;
		return Objects.equals(codiceFiscaleAdmin, other.codiceFiscaleAdmin)
				&& Objects.equals(codiceFiscaleAssCl, other.codiceFiscaleAssCl);
	}
}
