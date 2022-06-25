package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class RemAssistClBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codiceFiscaleAssistCl;
	private String codiceFiscaleAdmin;
	
	public RemAssistClBean(String codiceFiscaleAssistCl, String codiceFiscaleAdmin) {
		super();
		this.codiceFiscaleAssistCl = codiceFiscaleAssistCl;
		this.codiceFiscaleAdmin = codiceFiscaleAdmin;
	}
	
	public String getCodiceFiscaleAssistCl() {
		return codiceFiscaleAssistCl;
	}
	
	public void setCodiceFiscaleAssistCl(String codiceFiscaleAssistCl) {
		this.codiceFiscaleAssistCl = codiceFiscaleAssistCl;
	}
	
	public String getCodiceFiscaleAdmin() {
		return codiceFiscaleAdmin;
	}
	
	public void setCodiceFiscaleAdmin(String codiceFiscaleAdmin) {
		this.codiceFiscaleAdmin = codiceFiscaleAdmin;
	}

	public String toString() {
		return "RemAssistClBean [codiceFiscaleAssistCl=" + codiceFiscaleAssistCl + ", codiceFiscaleAdmin="
				+ codiceFiscaleAdmin + "]";
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RemAssistClBean other = (RemAssistClBean) obj;
		return Objects.equals(codiceFiscaleAdmin, other.codiceFiscaleAdmin)
				&& Objects.equals(codiceFiscaleAssistCl, other.codiceFiscaleAssistCl);
	}
	
	
	
	
}
