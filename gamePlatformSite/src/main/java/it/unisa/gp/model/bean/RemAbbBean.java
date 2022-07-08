package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class RemAbbBean implements Serializable{
	private static final long serialVersionUID = 1L;
		private String codiceFiscaleSupVid;
		private String nomeAbb;
		
	public RemAbbBean(String codiceFiscaleSupVid, String nomeAbb) {
		super();
		this.codiceFiscaleSupVid = codiceFiscaleSupVid;
		this.nomeAbb = nomeAbb;
	}

	public String getCodiceFiscaleSupVid() {
		return codiceFiscaleSupVid;
	}

	public void setCodiceFiscaleSupVid(String codiceFiscaleSupVid) {
		this.codiceFiscaleSupVid = codiceFiscaleSupVid;
	}

	public String getNomeAbb() {
		return nomeAbb;
	}

	public void setNomeAbb(String nomeAbb) {
		this.nomeAbb = nomeAbb;
	}

	@Override
	public String toString() {
		return "RemAbbBean [codiceFiscaleSupVid=" + codiceFiscaleSupVid + ", nomeAbb=" + nomeAbb + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RemAbbBean other = (RemAbbBean) obj;
		return Objects.equals(codiceFiscaleSupVid, other.codiceFiscaleSupVid) && Objects.equals(nomeAbb, other.nomeAbb);
	}
	
	
}
