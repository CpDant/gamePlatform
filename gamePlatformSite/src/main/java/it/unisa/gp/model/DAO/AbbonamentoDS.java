package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;


import javax.sql.DataSource;

import it.unisa.gp.model.bean.AbbonamentoBean;
import it.unisa.gp.model.interfaceDS.Abbonamento;



public class AbbonamentoDS implements Abbonamento{
	private static final String TABLE_NAME = "abbonamento";
	private DataSource ds = null;
	

	public AbbonamentoDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	
	@Override
	public synchronized void doSave(AbbonamentoBean abb) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + AbbonamentoDS.TABLE_NAME
				+ " (NOME_UNIVOCO, COSTO, DURATA) VALUES (?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, abb.getNomeUnivoco());
			preparedStmt.setInt(2, abb.getCosto());
			preparedStmt.setInt(3, abb.getDurata());

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
	public synchronized void doUpdate(AbbonamentoBean abb, int costo, int durata) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + AbbonamentoDS.TABLE_NAME
				+ " SET COSTO = ? , DURATA = ?" + " WHERE NOME_UNIVOCO = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setInt(1, costo);
			preparedStmt.setInt(2, durata);
			preparedStmt.setString(3, abb.getNomeUnivoco());

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

		String deleteSQL = "DELETE FROM " + AbbonamentoDS.TABLE_NAME + " WHERE NOME_UNIVOCO = ?";

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
	public synchronized AbbonamentoBean doRetrieveByKey(String name) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AbbonamentoBean bean = new AbbonamentoBean(null,0,0);

		String selectSQL = "SELECT * FROM " + AbbonamentoDS.TABLE_NAME + " WHERE NOME_UNIVOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, name);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setNomeUnivoco(rs.getString("NOME_UNIVOCO"));
				bean.setCosto(rs.getInt("COSTO"));
				bean.setDurata(rs.getInt("DURATA"));
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
	public synchronized Collection<AbbonamentoBean> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AbbonamentoBean> array = new LinkedList<AbbonamentoBean>();

		String selectSQL = "SELECT * FROM " + AbbonamentoDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				AbbonamentoBean bean = new AbbonamentoBean(null,0,0);
				bean.setNomeUnivoco(rs.getString("NOME_UNIVOCO"));
				bean.setCosto(rs.getInt("COSTO"));
				bean.setDurata(rs.getInt("DURATA"));
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
