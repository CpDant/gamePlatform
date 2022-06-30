package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;
import it.unisa.gp.model.bean.FatturaBean;
import it.unisa.gp.model.interfaceDS.Fattura;

public class FatturaDS implements Fattura {
	private static final String TABLE_NAME = "fattura";
	private DataSource ds = null;
	
	public FatturaDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public synchronized void doSave(int id, LocalDateTime dataOra) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		PreparedStatement preparedStmtNumInc = null;
		PreparedStatement preparedStmtIndFatt = null;
		PreparedStatement preparedStmtCosto = null;
		
		String insertSQL = "INSERT INTO " + FatturaDS.TABLE_NAME
				+ " (ID, NUMERO, COSTO_IVA, COSTO_NETTO, DATA_ORA, IND_FATT) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		String numIncSQL = "SELECT MAX(NUMERO) FROM " + FatturaDS.TABLE_NAME;
		
		String indFattSQL = "SELECT IND_FATT FROM ACQUISTI "
				+ "WHERE ID = ?";
		
		String costoSQL = "SELECT COSTO_IVA, COSTO_NETTO FROM ACQUISTI WHERE ID = ?";
		
		
		
		try {
			connection = ds.getConnection();
			
			preparedStmtNumInc = connection.prepareStatement(numIncSQL);
			ResultSet rs = preparedStmtNumInc.executeQuery();
			int numero = 0;
			if(rs.next()) {
				numero = rs.getInt("MAX(NUMERO)") + 1;
			}
			
			preparedStmtIndFatt = connection.prepareStatement(indFattSQL);
			preparedStmtIndFatt.setInt(1, id);
			ResultSet rs1 = preparedStmtIndFatt.executeQuery();
			String indFatt = null;
			if(rs1.next()) {
				indFatt = rs1.getString("IND_FATT");
			}
			
			preparedStmtCosto = connection.prepareStatement(costoSQL);
			preparedStmtCosto.setInt(1, id);
			ResultSet rs2 = preparedStmtCosto.executeQuery();
			int costoIva = 0;
			int costoNetto = 0;
			if(rs2.next()) {
				costoIva = rs2.getInt("COSTO_IVA");
				costoNetto = rs2.getInt("COSTO_NETTO");
			}
			
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setInt(1, id);
			preparedStmt.setInt(2, numero);
			preparedStmt.setInt(3, costoIva);
			preparedStmt.setInt(4, costoNetto);
			preparedStmt.setTimestamp(5, java.sql.Timestamp.valueOf(dataOra));
			preparedStmt.setString(6, indFatt);

			preparedStmt.executeUpdate();

			connection.setAutoCommit(false);
			connection.commit();
		} finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
				if (preparedStmtNumInc != null)
					preparedStmtNumInc.close();
				if (preparedStmtIndFatt != null)
					preparedStmtIndFatt.close();
				if (preparedStmtCosto != null)
					preparedStmtCosto.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	@Override
	public synchronized void doUpdate(FatturaBean fat, int costoIva, int costoNetto, LocalDateTime dataOra, String indFatt)
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + FatturaDS.TABLE_NAME
				+ " SET COSTO_IVA = ?, COSTO_NETTO = ?, DATA_ORA = ?, IND_FATT = ?" + " WHERE ID = ? AND NUMERO = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setInt(1, costoIva);
			preparedStmt.setInt(2, costoNetto);
			preparedStmt.setTimestamp(3, java.sql.Timestamp.valueOf(dataOra));
			preparedStmt.setString(4, indFatt);
			preparedStmt.setInt(5, fat.getId());
			preparedStmt.setInt(6, fat.getNumero());
			
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
	public synchronized boolean doDelete(int id, int numero) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + FatturaDS.TABLE_NAME + " WHERE ID = ? AND NUMERO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setInt(1, id);
			preparedStmt.setInt(2, numero);

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
	public synchronized FatturaBean doRetrieveByKey(int id, int numero) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		FatturaBean bean = new FatturaBean(0,0,0,0,null,null);

		String selectSQL = "SELECT * FROM " + FatturaDS.TABLE_NAME + " WHERE ID = ? AND NUMERO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);
			preparedStmt.setInt(2, numero);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("ID"));
				bean.setNumero(rs.getInt("NUMERO"));
				bean.setCostoIva(rs.getInt("COSTO_IVA"));
				bean.setCostoNetto(rs.getInt("COSTO_NETTO"));
				bean.setDataOra(rs.getTimestamp("DATA_ORA").toLocalDateTime());
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
	public synchronized Collection<FatturaBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<FatturaBean> array = new LinkedList<FatturaBean>();

		String selectSQL = "SELECT * FROM " + FatturaDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				FatturaBean bean = new FatturaBean(0,0,0,0,null,null);
				bean.setId(rs.getInt("ID"));
				bean.setNumero(rs.getInt("NUMERO"));
				bean.setCostoIva(rs.getInt("COSTO_IVA"));
				bean.setCostoNetto(rs.getInt("COSTO_NETTO"));
				bean.setDataOra(rs.getTimestamp("DATA_ORA").toLocalDateTime());
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

}
