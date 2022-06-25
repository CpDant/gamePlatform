package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class ModVideogBean implements Serializable{

	private String codiceFiscaleSupVid;
	private String codiceVideogioco;
	private static final long serialVersionUID = 1L;
	
	public ModVideogBean(String codiceFiscaleSupVid, String codiceVideogioco) {
		super();
		this.codiceFiscaleSupVid = codiceFiscaleSupVid;
		this.codiceVideogioco = codiceVideogioco;
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

	public String toString() {
		return "ModVideogBean [codiceFiscaleSupVid=" + codiceFiscaleSupVid + ", codiceVideogioco=" + codiceVideogioco + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModVideogBean other = (ModVideogBean) obj;
		return Objects.equals(codiceFiscaleSupVid, other.codiceFiscaleSupVid)
				&& Objects.equals(codiceVideogioco, other.codiceVideogioco);
	}
	
}
