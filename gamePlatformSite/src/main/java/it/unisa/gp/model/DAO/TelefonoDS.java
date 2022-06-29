package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.TelefonoBean;
import it.unisa.gp.model.interfaceDS.Telefono;

public class TelefonoDS implements Telefono{

	private static final String TABLE_NAME = "telefono";
	
	private DataSource ds = null;
	
	public TelefonoDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}	
	

	@Override
	public synchronized void doSave(TelefonoBean tel) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + TelefonoDS.TABLE_NAME
				+ " (NUMERO, CODICE_FISCALE_CLIENTE) VALUES (?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setLong(1, tel.getNumero());
			preparedStmt.setString(2, tel.getCodiceFiscaleCliente());

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
	public synchronized boolean doDelete(long numero) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TelefonoDS.TABLE_NAME + " WHERE NUMERO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setLong(1, numero);

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
	public synchronized TelefonoBean doRetrieveByKey(long numero) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		TelefonoBean bean = new TelefonoBean(0,null);

		String selectSQL = "SELECT * FROM " + TelefonoDS.TABLE_NAME + " WHERE NUMERO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setLong(1, numero);
			

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setNumero(rs.getLong("NUMERO"));
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));			
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
	public synchronized Collection<TelefonoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<TelefonoBean> array = new LinkedList<TelefonoBean>();

		String selectSQL = "SELECT * FROM " + TelefonoDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				TelefonoBean bean = new TelefonoBean(0,null);
				
				bean.setNumero(rs.getLong("NUMERO"));
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
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
	public synchronized void doUpdate(TelefonoBean tel, String codiceFiscaleCliente) throws SQLException {
		// non implementato poiché inutile
		
	}

}
