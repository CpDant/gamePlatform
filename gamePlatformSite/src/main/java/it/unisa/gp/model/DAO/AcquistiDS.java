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
	public synchronized void doSave(int id, String codFisc, String codiceRiscatto, long numeroCartaPag) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		PreparedStatement preparedStmtIndFatt = null;
		
		
		
		String insertSQL = "INSERT INTO " + AcquistiDS.TABLE_NAME
				+ " (ID, CODICE_RISCATTO, CODICE_FISCALE_CLIENTE, COSTO_IVA, COSTO_NETTO, DATA_ORA, IND_FATT, NUMERO_CARTA_PAGAM) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		String indFattSQL = "SELECT IND_FATT FROM CLIENTI "
				+ "WHERE CODICE_FISCALE = ?";
		
		try {
			connection = ds.getConnection();
			
			preparedStmtIndFatt = connection.prepareStatement(indFattSQL);
			preparedStmtIndFatt.setString(1, codFisc);
			ResultSet rs2 = preparedStmtIndFatt.executeQuery();
			String indFatt = null;
			if(rs2.next()) {
				indFatt = rs2.getString("IND_FATT");
			}
			
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, codiceRiscatto);
			preparedStmt.setString(3, codFisc);
			preparedStmt.setInt(4, 0);
			preparedStmt.setInt(5, 0);
			preparedStmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			preparedStmt.setString(7, indFatt);
			preparedStmt.setLong(8, numeroCartaPag);
			preparedStmt.executeUpdate();

			connection.setAutoCommit(false);
			connection.commit();
			
		} finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
				if (preparedStmtIndFatt != null)
					preparedStmtIndFatt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}

	@Override
	public synchronized void doUpdate(AcquistiBean acq, String codRisc, int costIva, int costNetto, LocalDateTime dataOra,
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
	public synchronized boolean doDelete(int name) throws SQLException {
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
	public synchronized AcquistiBean doRetrieveByKey(int id) throws SQLException {
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
	public synchronized Collection<AcquistiBean> doRetrieveAll(String order) throws SQLException {
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


	@Override
	public synchronized void doUpdate(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmtUpdate = null;
		PreparedStatement preparedStmtCostoVid = null;
		PreparedStatement preparedStmtCostoAbb = null;
		
		String costoVideogiocoSQL = "SELECT SUM(CONT_VID.COSTO) AS COSTO_VID FROM acq_contiene_vid AS CONT_VID, videogioco AS VID"
				+ " WHERE VID.CODICE = CONT_VID.CODICE_VIDEOGIOCO AND CONT_VID.ID = ?";
		
		String costoAbbonamentoSQL = "SELECT SUM(CONT_ABB.COSTO) AS COSTO_ABB FROM acq_contiene_abb AS CONT_ABB, abbonamento AS ABB"
				+ " WHERE ABB.NOME_UNIVOCO = CONT_ABB.NOME_UNIVOCO_ABB AND CONT_ABB.ID = ?";
		
		String updateSQL = "UPDATE " + AcquistiDS.TABLE_NAME + " SET COSTO_IVA = ?, COSTO_NETTO = ? "
				+ "WHERE ID = ?";
		
		try {
			connection = ds.getConnection();
			
			preparedStmtCostoAbb = connection.prepareStatement(costoAbbonamentoSQL);
			preparedStmtCostoAbb.setInt(1, id);
			ResultSet rs = preparedStmtCostoAbb.executeQuery();
			int costoAbb = 0;
			if(rs.next()) {
				costoAbb = rs.getInt("COSTO_ABB");
			}
			
			preparedStmtCostoVid = connection.prepareStatement(costoVideogiocoSQL);
			preparedStmtCostoVid.setInt(1, id);
			ResultSet rs1 = preparedStmtCostoVid.executeQuery();
			int costoVid = 0;
			if(rs1.next()) {
				costoVid = rs1.getInt("COSTO_VID");
			}
			
			int costo = costoVid + costoAbb;
			int costoIva = costo * AcquistiBean.iva / 100;
			int costoNetto = costo - costoIva;
			
			preparedStmtUpdate = connection.prepareStatement(updateSQL);
			preparedStmtUpdate.setInt(1, costoIva);
			preparedStmtUpdate.setInt(2, costoNetto);
			preparedStmtUpdate.setInt(3, id);
			
			preparedStmtUpdate.executeUpdate();

			connection.setAutoCommit(false);
			connection.commit();
			
		} finally {
			try {
				if (preparedStmtUpdate != null)
					preparedStmtUpdate.close();
				if (preparedStmtCostoVid != null)
					preparedStmtCostoVid.close();
				if (preparedStmtCostoAbb != null)
					preparedStmtCostoAbb.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

}
