package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class AssistenteClientiBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private final String RUOLO = "assCl";
	private String email;
	private String passWord;
    private int retribuzioneAnnuale;

	public AssistenteClientiBean(String codiceFiscale, String nome, String cognome, LocalDate dataNascita,
			String email, String passWord, int retribuzioneAnnuale) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.email = email;
		this.passWord = passWord;
		this.retribuzioneAnnuale = retribuzioneAnnuale;
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
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
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

	public String getRuolo() {
		return RUOLO;
	}

	public String toString() {
		return "AssistenteClientiBean [codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataNascita=" + dataNascita + ", ruolo=" + RUOLO + ", email=" + email + ", passWord=" + passWord
				+ ", retribuzioneAnnuale=" + retribuzioneAnnuale + "]";
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
				&& retribuzioneAnnuale == other.retribuzioneAnnuale && Objects.equals(RUOLO, other.RUOLO);
	}
	
}
