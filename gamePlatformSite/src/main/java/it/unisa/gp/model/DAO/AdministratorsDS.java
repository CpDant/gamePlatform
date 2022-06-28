package it.unisa.gp.model.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.AdministratorsBean;
import it.unisa.gp.model.interfaceDS.Administrators;

public class AdministratorsDS implements Administrators{

	private static final String TABLE_NAME = "administrators";
	
	private DataSource ds = null;

	public AdministratorsDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public void doSave(AdministratorsBean adm) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + AdministratorsDS.TABLE_NAME
				+ " (CODICE_FISCALE, NOME, COGNOME, DATA_NASCITA, RUOLO, EMAIL, PASS_WORD, RETRIBUZIONE_ANNUALE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, adm.getCodiceFiscale());
			preparedStmt.setString(2, adm.getNome());
			preparedStmt.setString(3, adm.getCognome());
			preparedStmt.setDate(4, java.sql.Date.valueOf(adm.getDataNascita()));
			preparedStmt.setString(5, adm.getRuolo());
			preparedStmt.setString(6, adm.getEmail());
			preparedStmt.setString(7, adm.getPassWord());
			preparedStmt.setInt(8, adm.getRetribuzioneAnnuale());

			preparedStmt.executeUpdate();

			connection.setAutoCommit(false);
			connection.commit();
		} finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}	
	}

	@Override
	public void doUpdate(AdministratorsBean adm, String nome, String cognome, LocalDate dataNascita, String email,
			String passWord, int retribuzioneAnnuale) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + AdministratorsDS.TABLE_NAME
				+ " SET NOME = ? , COGNOME = ? , DATA_NASCITA = ? , EMAIL = ? , PASS_WORD = ?, RETRIBUZIONE_ANNUALE = ?" + " WHERE CODICE_FISCALE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setString(1, nome);
			preparedStmt.setString(2, cognome);
			preparedStmt.setDate(3, java.sql.Date.valueOf(dataNascita));
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, passWord);
			preparedStmt.setInt(6, retribuzioneAnnuale);
			preparedStmt.setString(7, adm.getCodiceFiscale());
			
			preparedStmt.executeUpdate();

			connection.setAutoCommit(false);
			connection.commit();
		} finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}	
	}

	@Override
	public synchronized boolean doDelete(String codiceFiscale) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AdministratorsDS.TABLE_NAME + " WHERE CODICE_FISCALE = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, codiceFiscale);

			result = preparedStmt.executeUpdate();

		} finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	@Override
	public AdministratorsBean doRetrieveByKey(String codiceFiscale) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AdministratorsBean bean = new AdministratorsBean(null, null, null, null, null, null, 0);

		String selectSQL = "SELECT * FROM " + AdministratorsDS.TABLE_NAME + " WHERE CODICE_FISCALE = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codiceFiscale);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscale(rs.getString("CODICE_FISCALE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCognome(rs.getString("COGNOME"));
				Date date = rs.getDate("DATA_NASCITA"); 
				bean.setDataNascita(date.toLocalDate());
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPassWord(rs.getString("PASS_WORD"));
				bean.setRetribuzioneAnnuale(rs.getInt("RETRIBUZIONE_ANNUALE"));
			}

		} finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

	@Override
	public synchronized Collection<AdministratorsBean> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AdministratorsBean> amministratori = new LinkedList<AdministratorsBean>();

		String selectSQL = "SELECT * FROM " + AdministratorsDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			AdministratorsBean bean = new AdministratorsBean(null, null, null, null, null, null, 0);
			while (rs.next()) {
				
				bean.setCodiceFiscale(rs.getString("CODICE_FISCALE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCognome(rs.getString("COGNOME"));
				Date date = rs.getDate("DATA_NASCITA"); 
				bean.setDataNascita(date.toLocalDate());
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPassWord(rs.getString("PASS_WORD"));
				bean.setRetribuzioneAnnuale(rs.getInt("RETRIBUZIONE_ANNUALE"));
				amministratori.add(bean);
			}

		} finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return amministratori;
	}

}
