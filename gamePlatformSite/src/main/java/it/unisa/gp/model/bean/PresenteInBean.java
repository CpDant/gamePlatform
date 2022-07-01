package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class PresenteInBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nomeUnivoco;
	private String codiceVideogioco;
	
	public PresenteInBean(String nomeUnivoco, String codiceVideogioco) {
		super();
		this.nomeUnivoco = nomeUnivoco;
		this.codiceVideogioco = codiceVideogioco;
	}

	public String getNomeUnivoco() {
		return nomeUnivoco;
	}

	public void setNomeUnivoco(String nomeUnivoco) {
		this.nomeUnivoco = nomeUnivoco;
	}

	public String getCodiceVideogioco() {
		return codiceVideogioco;
	}

	public void setCodiceVideogioco(String codiceVideogioco) {
		this.codiceVideogioco = codiceVideogioco;
	}

	public String toString() {
		return "PresenteInBean [nomeUnivoco=" + nomeUnivoco + ", codiceVidoegioco=" + codiceVideogioco + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PresenteInBean other = (PresenteInBean) obj;
		return Objects.equals(codiceVideogioco, other.codiceVideogioco)
				&& Objects.equals(nomeUnivoco, other.nomeUnivoco);
	}
	
	
	
}
