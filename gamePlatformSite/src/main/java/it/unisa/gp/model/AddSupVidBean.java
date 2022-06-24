package it.unisa.gp.model;

import java.io.Serializable;
import java.util.Objects;

public class AddSupVidBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codiceFiscaleSupVid;
	private String codiceFiscaleAdmin;
	
	public AddSupVidBean(String codiceFiscaleSupVid, String codiceFiscaleAdmin) {
		super();
		this.codiceFiscaleSupVid = codiceFiscaleSupVid;
		this.codiceFiscaleAdmin = codiceFiscaleAdmin;
	}

	public String getCodiceFiscaleSupVid() {
		return codiceFiscaleSupVid;
	}

	public void setCodiceFiscaleSupVid(String codiceFiscaleSupVid) {
		this.codiceFiscaleSupVid = codiceFiscaleSupVid;
	}

	public String getCodiceFiscaleAdmin() {
		return codiceFiscaleAdmin;
	}

	public void setCodiceFiscaleAdmin(String codiceFiscaleAdmin) {
		this.codiceFiscaleAdmin = codiceFiscaleAdmin;
	}

	public String toString() {
		return "AddSupVidBean [codiceFiscaleSupVid=" + codiceFiscaleSupVid + ", codiceFiscaleAdmin=" + codiceFiscaleAdmin
				+ "]";
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddSupVidBean other = (AddSupVidBean) obj;
		return Objects.equals(codiceFiscaleAdmin, other.codiceFiscaleAdmin)
				&& Objects.equals(codiceFiscaleSupVid, other.codiceFiscaleSupVid);
	}
}
