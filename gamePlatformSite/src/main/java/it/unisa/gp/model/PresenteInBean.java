package it.unisa.gp.model;

import java.io.Serializable;
import java.util.Objects;

public class PresenteInBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nomeUnivoco;
	private String codiceVidoegioco;
	
	public PresenteInBean(String nomeUnivoco, String codiceVidoegioco) {
		super();
		this.nomeUnivoco = nomeUnivoco;
		this.codiceVidoegioco = codiceVidoegioco;
	}

	public String getNomeUnivoco() {
		return nomeUnivoco;
	}

	public void setNomeUnivoco(String nomeUnivoco) {
		this.nomeUnivoco = nomeUnivoco;
	}

	public String getCodiceVidoegioco() {
		return codiceVidoegioco;
	}

	public void setCodiceVidoegioco(String codiceVidoegioco) {
		this.codiceVidoegioco = codiceVidoegioco;
	}

	public String toString() {
		return "PresenteInBean [nomeUnivoco=" + nomeUnivoco + ", codiceVidoegioco=" + codiceVidoegioco + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PresenteInBean other = (PresenteInBean) obj;
		return Objects.equals(codiceVidoegioco, other.codiceVidoegioco)
				&& Objects.equals(nomeUnivoco, other.nomeUnivoco);
	}
	
	
	
}
