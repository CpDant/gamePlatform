package it.unisa.gp.model;

import java.io.Serializable;
import java.util.Objects;

public class AcqContieneAbbBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String nomeUnivocoAbb;
	
	public AcqContieneAbbBean(int id, String nomeUnivocoAbb) {
		super();
		this.id = id;
		this.nomeUnivocoAbb = nomeUnivocoAbb;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeUnivocoAbb() {
		return nomeUnivocoAbb;
	}

	public void setNomeUnivocoAbb(String nomeUnivocoAbb) {
		this.nomeUnivocoAbb = nomeUnivocoAbb;
	}

	public String toString() {
		return "AcqContieneAbbBean [id=" + id + ", nomeUnivocoAbb=" + nomeUnivocoAbb + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcqContieneAbbBean other = (AcqContieneAbbBean) obj;
		return id == other.id && Objects.equals(nomeUnivocoAbb, other.nomeUnivocoAbb);
	}
	
	
	
	
}
