package it.unisa.gp.model;

import java.io.Serializable;
import java.util.Objects;

public class AddInAbbBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codiceFiscaleSupVid;
	private String codiceVideogioco;
	private String nomeUnivocoAbb;
	
	public AddInAbbBean(String codiceFiscaleSupVid, String codiceVideogioco, String nomeUnivocoAbb) {
		super();
		this.codiceFiscaleSupVid = codiceFiscaleSupVid;
		this.codiceVideogioco = codiceVideogioco;
		this.nomeUnivocoAbb = nomeUnivocoAbb;
	}

	public String getCodiceFiscaleSupVid() {
		return codiceFiscaleSupVid;
	}

	public void setCodiceFiscaleSupVid(String codiceFiscaleSupVid) {
		this.codiceFiscaleSupVid = codiceFiscaleSupVid;
	}

	public String getCodiceVideogioco() {
		return codiceVideogioco;
	}

	public void setCodiceVideogioco(String codiceVideogioco) {
		this.codiceVideogioco = codiceVideogioco;
	}

	public String getNomeUnivocoAbb() {
		return nomeUnivocoAbb;
	}

	public void setNomeUnivocoAbb(String nomeUnivocoAbb) {
		this.nomeUnivocoAbb = nomeUnivocoAbb;
	}
	
	public String toString() {
		return "AddInAbbBean [codiceFiscaleSupVid=" + codiceFiscaleSupVid + ", codiceVideogioco=" + codiceVideogioco
				+ ", nomeUnivocoAbb=" + nomeUnivocoAbb + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddInAbbBean other = (AddInAbbBean) obj;
		return Objects.equals(codiceFiscaleSupVid, other.codiceFiscaleSupVid)
				&& Objects.equals(codiceVideogioco, other.codiceVideogioco)
				&& Objects.equals(nomeUnivocoAbb, other.nomeUnivocoAbb);
	}

}
