package it.unisa.gp.model;

import java.io.Serializable;
import java.util.Objects;

public class DispositivoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nome;
	private String codiceVideogioco;
	
	public DispositivoBean(String nome, String codiceVideogioco) {
		super();
		this.nome = nome;
		this.codiceVideogioco = codiceVideogioco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodiceVideogioco() {
		return codiceVideogioco;
	}

	public void setCodiceVideogioco(String codiceVideogioco) {
		this.codiceVideogioco = codiceVideogioco;
	}

	public String toString() {
		return "DispositivoBean [nome=" + nome + ", codiceVideogioco=" + codiceVideogioco + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DispositivoBean other = (DispositivoBean) obj;
		return Objects.equals(codiceVideogioco, other.codiceVideogioco) && Objects.equals(nome, other.nome);
	}
	
	
}
