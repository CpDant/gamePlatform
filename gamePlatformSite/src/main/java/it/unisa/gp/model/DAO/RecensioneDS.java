package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.RecensioneBean;
import it.unisa.gp.model.bean.RecensioneBean.Grado;
import it.unisa.gp.model.interfaceDS.Recensione;

public class RecensioneDS implements Recensione{

	private static final String TABLE_NAME = "recensione";
	
	private DataSource ds = null;
	
	public RecensioneDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public void doSave(RecensioneBean rec) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + RecensioneDS.TABLE_NAME
				+ " (CODICE_FISCALE_CLIENTE, CODICE, DATA_ORA_INS, DESCRIZIONE, GRADO_DI_APPREZZAMENTO) VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, rec.getCodiceFiscaleCliente());
			preparedStmt.setString(2, rec.getCodice());
			preparedStmt.setTimestamp(3, java.sql.Timestamp.valueOf(rec.getDataOraIns()));
			preparedStmt.setString(4, rec.getDescrizione());
			preparedStmt.setString(5, rec.getGradoDiApprezzamento().toString());

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
	public void doUpdate(RecensioneBean rec, LocalDateTime dataOraIns, String descrizione, Grado gradoDiApprezzamento)
			throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + RecensioneDS.TABLE_NAME
				+ " SET DATA_ORA_INS = ? , DESCRIZIONE = ? , GRADO_DI_APPREZZAMENTO = ?" + " WHERE CODICE_FISCALE_CLIENTE = ? AND CODICE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setTimestamp(1, java.sql.Timestamp.valueOf(dataOraIns));
			preparedStmt.setString(2, descrizione);
			preparedStmt.setString(3, gradoDiApprezzamento.toString());
			preparedStmt.setString(4, rec.getCodiceFiscaleCliente());
			preparedStmt.setString(5, rec.getCodice());
			
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
	public boolean doDelete(String codiceFiscaleCliente, String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RecensioneDS.TABLE_NAME + " WHERE CODICE_FISCALE_CLIENTE = ? AND CODICE = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, codiceFiscaleCliente);
			preparedStmt.setString(2, codice);
			
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
	public RecensioneBean doRetrieveByKey(String codiceFiscaleCliente, String codice) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		RecensioneBean bean = new RecensioneBean(null, null, null, null, null);

		String selectSQL = "SELECT * FROM " + RecensioneDS.TABLE_NAME + " WHERE CODICE_FISCALE_CLIENTE = ? AND CODICE = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codiceFiscaleCliente);
			preparedStmt.setString(2, codice);
			
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setCodice(rs.getString("CODICE"));
				bean.setDataOraIns((rs.getTimestamp("DATA_ORA_INS").toLocalDateTime()));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setGradoDiApprezzamento(Grado.valueOf(rs.getString("GRADO_DI_APPREZZAMENTO")));											
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
	public Collection<RecensioneBean> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<RecensioneBean> array = new LinkedList<RecensioneBean>();

		String selectSQL = "SELECT * FROM " + RecensioneDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean(null, null, null, null, null);
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setCodice(rs.getString("CODICE"));
				bean.setDataOraIns((rs.getTimestamp("DATA_ORA_INS").toLocalDateTime()));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setGradoDiApprezzamento(Grado.valueOf(rs.getString("GRADO_DI_APPREZZAMENTO")));
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
