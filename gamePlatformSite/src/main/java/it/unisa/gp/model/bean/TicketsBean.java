package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class TicketsBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private String codiceFiscaleAssistCl;
    private String codiceFiscaleCliente;
    private boolean resolved;
    public  enum CategoriaProbl{account, pagamenti, abbonamenti, rimborso, fattura};
    private String messaggio;
    private LocalDateTime dataOra;
    
	public TicketsBean(int id, String codiceFiscaleAssistCl, String codiceFiscaleCliente,
			String messaggio, LocalDateTime dataOra) {
		super();
		this.id = id;
		this.codiceFiscaleAssistCl = codiceFiscaleAssistCl;
		this.codiceFiscaleCliente = codiceFiscaleCliente;
		this.resolved = false;
		this.messaggio = messaggio;
		this.dataOra = dataOra;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodiceFiscaleAssistCl() {
		return codiceFiscaleAssistCl;
	}
	public void setCodiceFiscaleAssistCl(String codiceFiscaleAssistCl) {
		this.codiceFiscaleAssistCl = codiceFiscaleAssistCl;
	}
	public String getCodiceFiscaleCliente() {
		return codiceFiscaleCliente;
	}
	public void setCodiceFiscaleCliente(String codiceFiscaleCliente) {
		this.codiceFiscaleCliente = codiceFiscaleCliente;
	}
	public boolean isResolved() {
		return resolved;
	}
	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	public LocalDateTime getDataOra() {
		return dataOra;
	}

	public String toString() {
		return "TicketsBean [id=" + id + ", codiceFiscaleAssistCl=" + codiceFiscaleAssistCl + ", codiceFiscaleCliente="
				+ codiceFiscaleCliente + ", resolved=" + resolved + ", messaggio=" + messaggio + ", dataOra=" + dataOra
				+ "]";
	}
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketsBean other = (TicketsBean) obj;
		return Objects.equals(codiceFiscaleAssistCl, other.codiceFiscaleAssistCl)
				&& Objects.equals(codiceFiscaleCliente, other.codiceFiscaleCliente)
				&& Objects.equals(dataOra, other.dataOra) && id == other.id
				&& Objects.equals(messaggio, other.messaggio) && resolved == other.resolved;
	}	
}
