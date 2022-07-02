package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;
import it.unisa.gp.model.bean.AssistenteClientiBean;
import it.unisa.gp.model.interfaceDS.AssistenteClienti;

public class AssistenteClientiDS implements AssistenteClienti {
	private static final String TABLE_NAME = "assistente_clienti";
	private DataSource ds = null;
	
	public AssistenteClientiDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public synchronized void doSave(AssistenteClientiBean ass) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + AssistenteClientiDS.TABLE_NAME
				+ " (CODICE_FISCALE, NOME, COGNOME, DATA_NASCITA, RUOLO, EMAIL, PASS_WORD,"
				+ "RETRIBUZIONE_ANNUALE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, ass.getCodiceFiscale());
			preparedStmt.setString(2, ass.getNome());
			preparedStmt.setString(3, ass.getCognome());
			preparedStmt.setDate(4, java.sql.Date.valueOf(ass.getDataNascita()));
			preparedStmt.setString(5, ass.getRuolo());
			preparedStmt.setString(6, ass.getEmail());
			preparedStmt.setString(7, ass.getPassWord());
			preparedStmt.setInt(8, ass.getRetribuzioneAnnuale());

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
	public synchronized void doUpdate(AssistenteClientiBean ass, String nome, String cognome, LocalDate dataNascita, String email,
			String password, int retribuzioneAnnuale) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + AssistenteClientiDS.TABLE_NAME
				+ " SET NOME = ?, COGNOME = ?, DATA_NASCITA = ?, EMAIL = ?, PASS_WORD = ?,"
				+ "RETRIBUZIONE_ANNUALE = ?" + " WHERE CODICE_FISCALE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setString(1, nome);
			preparedStmt.setString(2, cognome);
			preparedStmt.setDate(3, java.sql.Date.valueOf(dataNascita));
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, password);
			preparedStmt.setInt(6, retribuzioneAnnuale);
			preparedStmt.setString(9, ass.getCodiceFiscale());

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

		String deleteSQL = "DELETE FROM " + AssistenteClientiDS.TABLE_NAME + " WHERE CODICE_FISCALE = ?";

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
	public synchronized AssistenteClientiBean doRetrieveByKey(String codiceFiscale) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AssistenteClientiBean ass = new AssistenteClientiBean(null,null,null,null,null,null,0);

		String selectSQL = "SELECT * FROM " + AssistenteClientiDS.TABLE_NAME + " WHERE CODICE_FISCALE = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codiceFiscale);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				ass.setCodiceFiscale(rs.getString("CODICE_FISCALE"));
				ass.setNome(rs.getString("NOME"));
				ass.setCognome(rs.getString("COGNOME"));
				ass.setDataNascita((rs.getDate("DATA_NASCITA")).toLocalDate());
				ass.setEmail(rs.getString("EMAIL"));
				ass.setPassWord(rs.getString("PASS_WORD"));
				ass.setRetribuzioneAnnuale(rs.getInt("RETRIBUZIONE_ANNUALE"));
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
		return ass;
	}

	@Override
	public synchronized Collection<AssistenteClientiBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AssistenteClientiBean> array = new LinkedList<AssistenteClientiBean>();

		String selectSQL = "SELECT * FROM " + AssistenteClientiDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				AssistenteClientiBean ass = new AssistenteClientiBean(null,null,null,null,null,null,0);
				ass.setCodiceFiscale(rs.getString("CODICE_FISCALE"));
				ass.setNome(rs.getString("NOME"));
				ass.setCognome(rs.getString("COGNOME"));
				ass.setDataNascita((rs.getDate("DATA_NASCITA")).toLocalDate());
				ass.setEmail(rs.getString("EMAIL"));
				ass.setPassWord(rs.getString("PASS_WORD"));
				ass.setRetribuzioneAnnuale(rs.getInt("RETRIBUZIONE_ANNUALE"));
				array.add(ass);
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
		return array;
	}

}
