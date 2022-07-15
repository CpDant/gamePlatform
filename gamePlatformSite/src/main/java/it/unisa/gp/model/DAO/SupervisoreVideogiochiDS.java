package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;
import it.unisa.gp.model.bean.SupervisoreVideogiochiBean;
import it.unisa.gp.model.interfaceDS.SupervisoreVideogiochi;

public class SupervisoreVideogiochiDS implements SupervisoreVideogiochi {
	private static final String TABLE_NAME = "supervisore_videogiochi";
	private DataSource ds = null;
	
	public SupervisoreVideogiochiDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}

	@Override
	public synchronized void doSave(SupervisoreVideogiochiBean sup) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + SupervisoreVideogiochiDS.TABLE_NAME
				+ " (CODICE_FISCALE, NOME, COGNOME, DATA_NASCITA, RUOLO, EMAIL, PASS_WORD,"
				+ "RETRIBUZIONE_ANNUALE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, sup.getCodiceFiscale());
			preparedStmt.setString(2, sup.getNome());
			preparedStmt.setString(3, sup.getCognome());
			preparedStmt.setDate(4, java.sql.Date.valueOf(sup.getDataNascita()));
			preparedStmt.setString(5, sup.getRuolo());
			preparedStmt.setString(6, sup.getEmail());
			preparedStmt.setString(7, sup.getPassWord());
			preparedStmt.setInt(8, sup.getRetribuzioneAnnuale());

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
	public synchronized void doUpdate(SupervisoreVideogiochiBean sup, String nome, String cognome, LocalDate dataNascita,
			String email, String password, int retribuzioneAnnuale) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + SupervisoreVideogiochiDS.TABLE_NAME
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
			preparedStmt.setString(7, sup.getCodiceFiscale());

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

		String deleteSQL = "DELETE FROM " + SupervisoreVideogiochiDS.TABLE_NAME + " WHERE CODICE_FISCALE = ?";

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
	public synchronized SupervisoreVideogiochiBean doRetrieveByKey(String codiceFiscale) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		SupervisoreVideogiochiBean sup = new SupervisoreVideogiochiBean(null,null,null,null,null,null,0);

		String selectSQL = "SELECT * FROM " + SupervisoreVideogiochiDS.TABLE_NAME + " WHERE CODICE_FISCALE = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codiceFiscale);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				sup.setCodiceFiscale(rs.getString("CODICE_FISCALE"));
				sup.setNome(rs.getString("NOME"));
				sup.setCognome(rs.getString("COGNOME"));
				sup.setDataNascita((rs.getDate("DATA_NASCITA")).toLocalDate());
				sup.setEmail(rs.getString("EMAIL"));
				sup.setPassWord(rs.getString("PASS_WORD"));
				sup.setRetribuzioneAnnuale(rs.getInt("RETRIBUZIONE_ANNUALE"));
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
		return sup;
	}

	@Override
	public synchronized Collection<SupervisoreVideogiochiBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<SupervisoreVideogiochiBean> array = new LinkedList<SupervisoreVideogiochiBean>();

		String selectSQL = "SELECT * FROM " + SupervisoreVideogiochiDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				SupervisoreVideogiochiBean sup = new SupervisoreVideogiochiBean(null,null,null,null,null,null,0);
				sup.setCodiceFiscale(rs.getString("CODICE_FISCALE"));
				sup.setNome(rs.getString("NOME"));
				sup.setCognome(rs.getString("COGNOME"));
				sup.setDataNascita((rs.getDate("DATA_NASCITA")).toLocalDate());
				sup.setEmail(rs.getString("EMAIL"));
				sup.setPassWord(rs.getString("PASS_WORD"));
				sup.setRetribuzioneAnnuale(rs.getInt("RETRIBUZIONE_ANNUALE"));
				array.add(sup);
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

	@Override
	public SupervisoreVideogiochiBean doRetrieveByKeyEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		SupervisoreVideogiochiBean sup = new SupervisoreVideogiochiBean(null,null,null,null,null,null,0);

		String selectSQL = "SELECT * FROM " + SupervisoreVideogiochiDS.TABLE_NAME + " WHERE EMAIL = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, email);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				sup.setCodiceFiscale(rs.getString("CODICE_FISCALE"));
				sup.setNome(rs.getString("NOME"));
				sup.setCognome(rs.getString("COGNOME"));
				sup.setDataNascita((rs.getDate("DATA_NASCITA")).toLocalDate());
				sup.setEmail(rs.getString("EMAIL"));
				sup.setPassWord(rs.getString("PASS_WORD"));
				sup.setRetribuzioneAnnuale(rs.getInt("RETRIBUZIONE_ANNUALE"));
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
		return sup;
	}

}
