package it.unisa.gp.model.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SoftwareHouseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nomeUnivoco;
    private String locazione;
    private LocalDate dataDiFondazione;
	
	public SoftwareHouseBean(String nomeUnivoco, String locazione, LocalDate dataDiFondazione) {
		super();
		this.nomeUnivoco = nomeUnivoco;
		this.locazione = locazione;
		this.dataDiFondazione = dataDiFondazione;
	}

	public String getNomeUnivoco() {
		return nomeUnivoco;
	}

	public void setNomeUnivoco(String nomeUnivoco) {
		this.nomeUnivoco = nomeUnivoco;
	}

	public String getLocazione() {
		return locazione;
	}

	public void setLocazione(String locazione) {
		this.locazione = locazione;
	}

	public LocalDate getDataDiFondazione() {
		return dataDiFondazione;
	}

	public void setDataDiFondazione(LocalDate dataDiFondazione) {
		this.dataDiFondazione = dataDiFondazione;
	}

	public String toString() {
		return "SoftwareHouseBean [nomeUnivoco=" + nomeUnivoco + ", locazione=" + locazione + ", dataDiFondazione="
				+ dataDiFondazione + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoftwareHouseBean other = (SoftwareHouseBean) obj;
		return Objects.equals(dataDiFondazione, other.dataDiFondazione) && Objects.equals(locazione, other.locazione)
				&& Objects.equals(nomeUnivoco, other.nomeUnivoco);
	}

}
