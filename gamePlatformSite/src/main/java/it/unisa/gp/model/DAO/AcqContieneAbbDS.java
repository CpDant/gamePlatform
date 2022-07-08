package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.AcqContieneAbbBean;
import it.unisa.gp.model.interfaceDS.AcqContieneAbb;

public class AcqContieneAbbDS implements AcqContieneAbb{

	private static final String TABLE_NAME = "acq_contiene_abb";
	
	private DataSource ds = null;
	

	public AcqContieneAbbDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public synchronized void doSave(int id, String codiceAbb) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		PreparedStatement preparedStmtCosto = null;

		String insertSQL = "INSERT INTO " + AcqContieneAbbDS.TABLE_NAME
				+ " (id, nome_univoco_abb, costo) VALUES (?,?,?)";
		
		String selectSQL = "SELECT COSTO FROM ABBONAMENTO WHERE NOME_UNIVOCO = ?";
				
		try {
			connection = ds.getConnection();	
			
			preparedStmtCosto = connection.prepareStatement(selectSQL);
			preparedStmtCosto.setString(1, codiceAbb);
			ResultSet rs = preparedStmtCosto.executeQuery();
			int costo = 0;
			if(rs.next()) {
				costo = rs.getInt("costo");
			}
			
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, codiceAbb);
			preparedStmt.setInt(3, costo);
			
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
	public synchronized boolean doDelete(int id, String codiceAbb) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AcqContieneAbbDS.TABLE_NAME + " WHERE id = ? AND nome_univoco_abb = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, codiceAbb);
			
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
	public synchronized AcqContieneAbbBean doRetrieveByKey(int id, String codiceAbb) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AcqContieneAbbBean bean = new AcqContieneAbbBean(0,null,0);

		String selectSQL = "SELECT * FROM " + AcqContieneAbbDS.TABLE_NAME + " WHERE id = ? AND nome_univoco_abb = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, codiceAbb);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setNomeUnivocoAbb(rs.getString("nome_univoco_abb"));
				bean.setCosto(rs.getInt("costo"));
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
	public synchronized Collection<AcqContieneAbbBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AcqContieneAbbBean> array = new LinkedList<AcqContieneAbbBean>();

		String selectSQL = "SELECT * FROM " + AcqContieneAbbDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				AcqContieneAbbBean bean = new AcqContieneAbbBean(0,null,0);
				
				bean.setId(rs.getInt("id"));
				bean.setNomeUnivocoAbb(rs.getString("nome_univoco_abb"));
				bean.setCosto(rs.getInt("costo"));
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
