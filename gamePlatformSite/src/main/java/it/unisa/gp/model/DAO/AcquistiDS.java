package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.AcquistiBean;
import it.unisa.gp.model.interfaceDS.Acquisti;

public class AcquistiDS implements Acquisti{

	
	private static final String TABLE_NAME = "acquisti";
	
	private DataSource ds = null;
	

	public AcquistiDS(DataSource ds) {
		
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	
	@Override
	public synchronized void doSave(AcquistiBean acq) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + AcquistiDS.TABLE_NAME
				+ " (ID, CODICE_RISCATTO, CODICE_FISCALE_CLIENTE, COSTO_IVA, COSTO_NETTO, DATA_ORA, IND_FATT, NUMERO_CARTA_PAGAM) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setInt(1, acq.getId());
			preparedStmt.setString(2, acq.getCodiceRiscatto());
			preparedStmt.setString(3, acq.getCodiceFiscaleCliente());
			preparedStmt.setInt(4, acq.getCostoIva());
			preparedStmt.setInt(5, acq.getCostoNetto());
			preparedStmt.setTimestamp(6, Timestamp.valueOf(acq.getDataOra()));
			preparedStmt.setString(7, acq.getIndFatt());
			preparedStmt.setLong(8, acq.getNumeroCartaPag());
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
	public void doUpdate(AcquistiBean acq, String codRisc, int costIva, int costNetto, LocalDateTime dataOra,
			String indFatt, long numCartaPag) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + AcquistiDS.TABLE_NAME
				+ " SET CODICE_RISCATTO = ?, COSTO_IVA = ?, COSTO_NETTO = ?, DATA_ORA = ?, IND_FATT = ?, NUMERO_CARTA_PAGAM = ?" + " WHERE ID = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setString(1, codRisc);
			preparedStmt.setInt(2, costIva);
			preparedStmt.setInt(3, costNetto);
			preparedStmt.setTimestamp(4, java.sql.Timestamp.valueOf(dataOra));
			preparedStmt.setString(5, indFatt);
			preparedStmt.setLong(6, numCartaPag);
			preparedStmt.setInt(7, acq.getId());
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
	public boolean doDelete(int name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AcquistiDS.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setInt(1, name);

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
	public AcquistiBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AcquistiBean bean = new AcquistiBean(0,null,null,0,0,null,null,0);

		String selectSQL = "SELECT * FROM " + AcquistiDS.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("ID"));
				bean.setCodiceRiscatto(rs.getString("CODICE_RISCATTO"));
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setCostoIva(rs.getInt("COSTO_IVA"));
				bean.setCostoNetto(rs.getInt("COSTO_NETTO"));
				bean.setDataOra(rs.getTimestamp("DATA_ORA").toLocalDateTime());
				bean.setIndFatt(rs.getString("IND_FATT"));
				bean.setNumeroCartaPag(rs.getLong("NUMERO_CARTA_PAGAM"));
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
	public Collection<AcquistiBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AcquistiBean> acq = new LinkedList<AcquistiBean>();

		String selectSQL = "SELECT * FROM " + AcquistiDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				AcquistiBean bean = new AcquistiBean(0,null,null,0,0,null,null,0);
				
				bean.setId(rs.getInt("ID"));
				bean.setCodiceRiscatto(rs.getString("CODICE_RISCATTO"));
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setCostoIva(rs.getInt("COSTO_IVA"));
				bean.setCostoNetto(rs.getInt("COSTO_NETTO"));
				bean.setDataOra(rs.getTimestamp("DATA_ORA").toLocalDateTime());
				bean.setIndFatt(rs.getString("IND_FATT"));
				bean.setNumeroCartaPag(rs.getLong("NUMERO_CARTA_PAGAM"));
				acq.add(bean);
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
		return acq;
	}

}
