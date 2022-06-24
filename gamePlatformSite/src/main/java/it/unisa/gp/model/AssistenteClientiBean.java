package it.unisa.gp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class AssistenteClientiBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private LocalDateTime dataNascita;
	private final String ruolo;
	private String email;
	private String passWord;
    private int retribuzioneAnnuale;
    private int ticket_da_risolvere;
    private int ticket_risolti;

	public AssistenteClientiBean(String codiceFiscale, String nome, String cognome, LocalDateTime dataNascita, String ruolo,
			String email, String passWord, int retribuzioneAnnuale, int ticket_da_risolvere, int ticket_risolti) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.ruolo = "assCl";
		this.email = email;
		this.passWord = passWord;
		this.retribuzioneAnnuale = retribuzioneAnnuale;
		this.ticket_da_risolvere = 0;
		this.ticket_risolti = 0;
	}
    
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDateTime getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDateTime dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getRetribuzioneAnnuale() {
		return retribuzioneAnnuale;
	}
	public void setRetribuzioneAnnuale(int retribuzioneAnnuale) {
		this.retribuzioneAnnuale = retribuzioneAnnuale;
	}
	public int getTicket_da_risolvere() {
		return ticket_da_risolvere;
	}
	public void setTicket_da_risolvere(int ticket_da_risolvere) {
		this.ticket_da_risolvere = ticket_da_risolvere;
	}
	public int getTicket_risolti() {
		return ticket_risolti;
	}
	public void setTicket_risolti(int ticket_risolti) {
		this.ticket_risolti = ticket_risolti;
	}

	public String getRuolo() {
		return ruolo;
	}

	public String toString() {
		return "AssistenteClientiBean [codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataNascita=" + dataNascita + ", ruolo=" + ruolo + ", email=" + email + ", passWord=" + passWord
				+ ", retribuzioneAnnuale=" + retribuzioneAnnuale + ", ticket_da_risolvere=" + ticket_da_risolvere
				+ ", ticket_risolti=" + ticket_risolti + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssistenteClientiBean other = (AssistenteClientiBean) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale) && Objects.equals(cognome, other.cognome)
				&& Objects.equals(dataNascita, other.dataNascita) && Objects.equals(email, other.email)
				&& Objects.equals(nome, other.nome) && Objects.equals(passWord, other.passWord)
				&& retribuzioneAnnuale == other.retribuzioneAnnuale && Objects.equals(ruolo, other.ruolo)
				&& ticket_da_risolvere == other.ticket_da_risolvere && ticket_risolti == other.ticket_risolti;
	}
	
}
