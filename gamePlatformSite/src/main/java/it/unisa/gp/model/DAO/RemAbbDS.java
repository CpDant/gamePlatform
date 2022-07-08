package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;
import it.unisa.gp.model.bean.RemAbbBean;
import it.unisa.gp.model.interfaceDS.Abbonamento;
import it.unisa.gp.model.interfaceDS.RemAbb;

public class RemAbbDS implements RemAbb {
	private static final String TABLE_NAME = "rem_abb";
	
	private DataSource ds = null;
	
	public RemAbbDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public void doSave(RemAbbBean rem) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + RemAbbDS.TABLE_NAME
				+ " (CODICE_FISCALE_SUP_VID, NOME_UNIVOCO_ABB) VALUES (?, ?)";
		
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, rem.getCodiceFiscaleSupVid());
			preparedStmt.setString(2, rem.getNomeAbb());

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
		
		Abbonamento abb = new AbbonamentoDS(ds);
		abb.doDelete(rem.getNomeAbb());
	}

	@Override
	public boolean doDelete(String codiceFiscaleSupVid, String nomeAbb) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RemAbbDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND NOME_UNIVOCO_ABB = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, codiceFiscaleSupVid);
			preparedStmt.setString(2, nomeAbb);

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
	public RemAbbBean doRetrieveByKey(String codiceFiscaleSupVid, String nomeAbb) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		RemAbbBean bean = new RemAbbBean(null,null);

		String selectSQL = "SELECT * FROM " + RemAbbDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND NOME_UNIVOCO_ABB = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codiceFiscaleSupVid);
			preparedStmt.setString(2, nomeAbb);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscaleSupVid(rs.getString("CODICE_FISCALE_SUP_VID"));
				bean.setNomeAbb(rs.getString("NOME_UNIVOCO_ABB"));
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
	public Collection<RemAbbBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<RemAbbBean> array = new LinkedList<RemAbbBean>();

		String selectSQL = "SELECT * FROM " + RemAbbDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				RemAbbBean bean = new RemAbbBean(null,null);
				bean.setCodiceFiscaleSupVid(rs.getString("CODICE_FISCALE_SUP_VID"));
				bean.setNomeAbb(rs.getString("NOME_UNIVOCO_ABB"));
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
