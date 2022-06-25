package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class AcqContieneVidBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String codiceVidoegioco;
	
	public AcqContieneVidBean(int id, String codiceVidoegioco) {
		super();
		this.id = id;
		this.codiceVidoegioco = codiceVidoegioco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodiceVidoegioco() {
		return codiceVidoegioco;
	}

	public void setCodiceVidoegioco(String codiceVidoegioco) {
		this.codiceVidoegioco = codiceVidoegioco;
	}

	public String toString() {
		return "AcqContieneVidBean [id=" + id + ", codiceVidoegioco=" + codiceVidoegioco + "]";
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcqContieneVidBean other = (AcqContieneVidBean) obj;
		return Objects.equals(codiceVidoegioco, other.codiceVidoegioco) && id == other.id;
	}
	
	
	
}
