package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.ClientiBean;
import it.unisa.gp.model.interfaceDS.Clienti;

public class ClientiDS implements Clienti{

	private static final String TABLE_NAME = "clienti";
	
	private DataSource ds = null;
	
	public ClientiDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
		
	
	@Override
	public synchronized void doSave(ClientiBean clienti) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + ClientiDS.TABLE_NAME
				+ " (CODICE_FISCALE, NOME, COGNOME, DATA_NASCITA, RUOLO, EMAIL, PASS_WORD, USERNAME, IND_FATT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, clienti.getCodiceFiscale());
			preparedStmt.setString(2, clienti.getNome());
			preparedStmt.setString(3, clienti.getCognome());
			preparedStmt.setDate(4, java.sql.Date.valueOf(clienti.getDataNascita()));
			preparedStmt.setString(5, clienti.getRuolo());
			preparedStmt.setString(6, clienti.getEmail());
			preparedStmt.setString(7, clienti.getPassWord());
			preparedStmt.setString(8, clienti.getUsername());
			preparedStmt.setString(9, clienti.getIndFatt());

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
	public synchronized void doUpdate(ClientiBean clienti, String nome, String cognome, LocalDate dataNascita,
			String email, String passWord, String username, String indFatt)
			throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + ClientiDS.TABLE_NAME
				+ " SET NOME = ?, COGNOME = ?, DATA_NASCITA = ?, EMAIL = ?, PASS_WORD = ?, USERNAME = ?, IND_FATT = ?" + " WHERE CODICE_FISCALE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setString(1, nome);
			preparedStmt.setString(2, cognome);
			preparedStmt.setDate(3, java.sql.Date.valueOf(dataNascita));
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, passWord);
			preparedStmt.setString(6, username);
			preparedStmt.setString(7, indFatt);
			preparedStmt.setString(8, clienti.getCodiceFiscale());
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
	public synchronized boolean doDelete(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ClientiDS.TABLE_NAME + " WHERE CODICE_FISCALE = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, name);

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
	public synchronized ClientiBean doRetrieveByKey(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		ClientiBean bean = new ClientiBean(null,null,null,null,null,null,null,null);

		String selectSQL = "SELECT * FROM " + ClientiDS.TABLE_NAME + " WHERE CODICE_FISCALE = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, name);
			

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscale(rs.getString("CODICE_FISCALE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCognome(rs.getString("COGNOME"));
				Date date = rs.getDate("DATA_NASCITA");
				bean.setDataNascita(date.toLocalDate());
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPassWord(rs.getString("PASS_WORD"));
				bean.setUsername(rs.getString("USERNAME"));
				bean.setIndFatt(rs.getString("IND_FATT"));
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
	public synchronized Collection<ClientiBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<ClientiBean> array = new LinkedList<ClientiBean>();

		String selectSQL = "SELECT * FROM " + ClientiDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				ClientiBean bean = new ClientiBean(null,null,null,null,null,null,null,null);
				bean.setCodiceFiscale(rs.getString("CODICE_FISCALE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCognome(rs.getString("COGNOME"));
				Date date = rs.getDate("DATA_NASCITA");
				bean.setDataNascita(date.toLocalDate());
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPassWord(rs.getString("PASS_WORD"));
				bean.setUsername(rs.getString("USERNAME"));
				bean.setIndFatt(rs.getString("IND_FATT"));
				array.add(bean);
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
	public ClientiBean doRetrieveByKeyEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		ClientiBean bean = new ClientiBean(null,null,null,null,null,null,null,null);

		String selectSQL = "SELECT * FROM " + ClientiDS.TABLE_NAME + " WHERE EMAIL = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, email);
			

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscale(rs.getString("CODICE_FISCALE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCognome(rs.getString("COGNOME"));
				Date date = rs.getDate("DATA_NASCITA");
				bean.setDataNascita(date.toLocalDate());
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPassWord(rs.getString("PASS_WORD"));
				bean.setUsername(rs.getString("USERNAME"));
				bean.setIndFatt(rs.getString("IND_FATT"));
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
}


