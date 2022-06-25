package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ClientiBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private LocalDateTime dataNascita;
	private final String ruolo;
	private String email;
	private String passWord;
	private String username;
    private int videogiochiFruibili;
    private String indFatt;
    private int numeroCartaPagam;
    
	public ClientiBean(String codiceFiscale, String nome, String cognome, LocalDateTime dataNascita, String ruolo,
			String email, String passWord, String username, int videogiochiFruibili, String indFatt,
			int numeroCartaPagam) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.ruolo = ruolo;
		this.email = email;
		this.passWord = passWord;
		this.username = username;
		this.videogiochiFruibili = videogiochiFruibili;
		this.indFatt = indFatt;
		this.numeroCartaPagam = numeroCartaPagam;
	}
	public String getIndFatt() {
		return indFatt;
	}
	public void setIndFatt(String indFatt) {
		this.indFatt = indFatt;
	}
	public int getNumeroCartaPagam() {
		return numeroCartaPagam;
	}
	public void setNumeroCartaPagam(int numeroCartaPagam) {
		this.numeroCartaPagam = numeroCartaPagam;
	}
	public String getRuolo() {
		return ruolo;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getVideogiochiFruibili() {
		return videogiochiFruibili;
	}
	public void setVideogiochiFruibili(int videogiochiFruibili) {
		this.videogiochiFruibili = videogiochiFruibili;
	}
	
	public String toString() {
		return "ClientiBean [codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataNascita=" + dataNascita + ", ruolo=" + ruolo + ", email=" + email + ", passWord=" + passWord
				+ ", username=" + username + ", videogiochiFruibili=" + videogiochiFruibili + ", indFatt=" + indFatt
				+ ", numeroCartaPagam=" + numeroCartaPagam + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientiBean other = (ClientiBean) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale) && Objects.equals(cognome, other.cognome)
				&& Objects.equals(dataNascita, other.dataNascita) && Objects.equals(email, other.email)
				&& Objects.equals(indFatt, other.indFatt) && Objects.equals(nome, other.nome)
				&& numeroCartaPagam == other.numeroCartaPagam && Objects.equals(passWord, other.passWord)
				&& Objects.equals(ruolo, other.ruolo) && Objects.equals(username, other.username)
				&& videogiochiFruibili == other.videogiochiFruibili;
	}
	
}
