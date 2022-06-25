package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;



public class RecensioneBean implements Serializable{
	
	public enum Grado{uno, due, tre, quattro, cinque};
	
	private static final long serialVersionUID = 1L;
	private String codiceFiscaleCliente;
	private String codice;
	private LocalDateTime dataOraIns;
	private String descrizione;
	private Grado gradoDiApprezzamento;
	
	public RecensioneBean(String codiceFiscaleCliente, String codice, LocalDateTime dataOraIns, String descrizione,
			Grado gradoDiApprezzamento) {
		super();
		this.codiceFiscaleCliente = codiceFiscaleCliente;
		this.codice = codice;
		this.dataOraIns = dataOraIns;
		this.descrizione = descrizione;
		this.gradoDiApprezzamento = gradoDiApprezzamento;
	}

	public String getCodiceFiscaleCliente() {
		return codiceFiscaleCliente;
	}

	public void setCodiceFiscaleCliente(String codiceFiscaleCliente) {
		this.codiceFiscaleCliente = codiceFiscaleCliente;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public LocalDateTime getDataOraIns() {
		return dataOraIns;
	}

	public void setDataOraIns(LocalDateTime dataOraIns) {
		this.dataOraIns = dataOraIns;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Grado getGradoDiApprezzamento() {
		return gradoDiApprezzamento;
	}

	public void setGradoDiApprezzamento(Grado gradoDiApprezzamento) {
		this.gradoDiApprezzamento = gradoDiApprezzamento;
	}
	
	public String toString() {
		return "RecensioneBean [codiceFiscaleCliente=" + codiceFiscaleCliente + ", codice=" + codice + ", dataOraIns="
				+ dataOraIns + ", descrizione=" + descrizione + ", gradoDiApprezzamento=" + gradoDiApprezzamento + "]";
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecensioneBean other = (RecensioneBean) obj;
		return Objects.equals(codice, other.codice) && Objects.equals(codiceFiscaleCliente, other.codiceFiscaleCliente)
				&& Objects.equals(dataOraIns, other.dataOraIns) && Objects.equals(descrizione, other.descrizione)
				&& gradoDiApprezzamento == other.gradoDiApprezzamento;
	}
	
}
