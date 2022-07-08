package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class AddAbbBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codiceFiscaleSupVid;
	private String nomeUnivocoAbb;
	
	public AddAbbBean(String codiceFiscaleSupVid, String nomeUnivocoAbb) {
		super();
		this.codiceFiscaleSupVid = codiceFiscaleSupVid;
		this.nomeUnivocoAbb = nomeUnivocoAbb;
	}

	public String getCodiceFiscaleSupVid() {
		return codiceFiscaleSupVid;
	}

	public void setCodiceFiscaleSupVid(String codiceFiscaleSupVid) {
		this.codiceFiscaleSupVid = codiceFiscaleSupVid;
	}

	public String getNomeUnivocoAbb() {
		return nomeUnivocoAbb;
	}

	public void setNomeUnivocoAbb(String nomeUnivocoAbb) {
		this.nomeUnivocoAbb = nomeUnivocoAbb;
	}

	@Override
	public String toString() {
		return "AddAbbBean [codiceFiscaleSupVid=" + codiceFiscaleSupVid + ", nomeUnivocoAbb=" + nomeUnivocoAbb + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddAbbBean other = (AddAbbBean) obj;
		return Objects.equals(codiceFiscaleSupVid, other.codiceFiscaleSupVid)
				&& Objects.equals(nomeUnivocoAbb, other.nomeUnivocoAbb);
	}
	
}
