package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.util.Objects;

public class AcqContieneVidBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private int costo;
	private String codiceVideogioco;
	
	public AcqContieneVidBean(int id, String codiceVideogioco) {
		super();
		this.id = id;
		this.codiceVideogioco = codiceVideogioco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodiceVideogioco() {
		return codiceVideogioco;
	}

	public void setCodiceVideogioco(String codiceVideogioco) {
		this.codiceVideogioco = codiceVideogioco;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	public String toString() {
		return "AcqContieneVidBean [id=" + id + ", codiceVideogioco=" + codiceVideogioco + ", costo=" + costo + "]";
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcqContieneVidBean other = (AcqContieneVidBean) obj;
		return Objects.equals(codiceVideogioco, other.codiceVideogioco) && id == other.id && costo == other.costo;
	}

	
}
