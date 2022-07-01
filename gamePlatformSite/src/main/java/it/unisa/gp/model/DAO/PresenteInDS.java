package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.PresenteInBean;
import it.unisa.gp.model.interfaceDS.PresenteIn;

public class PresenteInDS implements PresenteIn{

	private static final String TABLE_NAME = "presente_in";
	
	private DataSource ds = null;
	

	public PresenteInDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	
	@Override
	public synchronized void doSave(PresenteInBean pres) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + PresenteInDS.TABLE_NAME
				+ " (NOME_UNIVOCO, CODICE_VIDEOGIOCO) VALUES (?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, pres.getNomeUnivoco());
			preparedStmt.setString(2, pres.getCodiceVideogioco());

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
	public synchronized boolean doDelete(String name, String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + PresenteInDS.TABLE_NAME + " WHERE NOME_UNIVOCO = ? AND CODICE_VIDEOGIOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, name);
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
	public synchronized PresenteInBean doRetrieveByKey(String name, String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		PresenteInBean bean = new PresenteInBean(null,null);

		String selectSQL = "SELECT * FROM " + PresenteInDS.TABLE_NAME + " WHERE NOME_UNIVOCO = ? AND CODICE_VIDEOGIOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, codice);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setNomeUnivoco(rs.getString("NOME_UNIVOCO"));
				bean.setCodiceVideogioco(rs.getString("CODICE_VIDEOGIOCO"));
				
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
	public synchronized Collection<PresenteInBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<PresenteInBean> array = new LinkedList<PresenteInBean>();

		String selectSQL = "SELECT * FROM " + PresenteInDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				PresenteInBean bean = new PresenteInBean(null,null);
				
				bean.setNomeUnivoco(rs.getString("NOME_UNIVOCO"));
				bean.setCodiceVideogioco(rs.getString("CODICE_VIDEOGIOCO"));
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
