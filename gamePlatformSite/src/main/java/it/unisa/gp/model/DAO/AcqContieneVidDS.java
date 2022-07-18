package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.AcqContieneVidBean;
import it.unisa.gp.model.interfaceDS.AcqContieneVid;


public class AcqContieneVidDS implements AcqContieneVid{

	private static final String TABLE_NAME = "acq_contiene_vid";
	
	private DataSource ds = null;
	

	public AcqContieneVidDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	
	@Override
	public synchronized void doSave(int id, String codiceVid) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		PreparedStatement preparedStmtCosto = null;
		
		String insertSQL = "INSERT INTO " + AcqContieneVidDS.TABLE_NAME
				+ " (id, CODICE_VIDEOGIOCO, COSTO) VALUES (?, ?, ?)";
		
		String selectSQL = "SELECT COSTO FROM VIDEOGIOCO WHERE CODICE = ?";
		
		try {
			connection = ds.getConnection();
			
			preparedStmtCosto = connection.prepareStatement(selectSQL);
			preparedStmtCosto.setString(1, codiceVid);
			ResultSet rs = preparedStmtCosto.executeQuery();
			int costo = 0;
			if(rs.next()) {
				costo = rs.getInt("costo");
			}
			
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, codiceVid);
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
	public synchronized boolean doDelete(int id, String codiceVid) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AcqContieneVidDS.TABLE_NAME + " WHERE id = ? AND CODICE_VIDEOGIOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, codiceVid);

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
	public synchronized AcqContieneVidBean doRetrieveByKey(int id, String codiceVid) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AcqContieneVidBean bean = new AcqContieneVidBean(0,null,0);

		String selectSQL = "SELECT * FROM " + AcqContieneVidDS.TABLE_NAME + " WHERE id = ? AND CODICE_VIDEOGIOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, codiceVid);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setCodiceVideogioco(rs.getString("CODICE_VIDEOGIOCO"));
				bean.setCosto(rs.getInt("COSTO"));
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
	public synchronized Collection<AcqContieneVidBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AcqContieneVidBean> array = new LinkedList<AcqContieneVidBean>();

		String selectSQL = "SELECT * FROM " + AcqContieneVidDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				AcqContieneVidBean bean = new AcqContieneVidBean(0,null,0);
				
				bean.setId(rs.getInt("id"));
				bean.setCodiceVideogioco(rs.getString("CODICE_VIDEOGIOCO"));
				bean.setCosto(rs.getInt("COSTO"));
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
	public synchronized Collection<AcqContieneVidBean> doRetrieveAllVid(int id, String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AcqContieneVidBean> array = new LinkedList<AcqContieneVidBean>();

		String selectSQL = "SELECT * FROM " + AcqContieneVidDS.TABLE_NAME + " WHERE ID=?";
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				AcqContieneVidBean bean = new AcqContieneVidBean(0,null,0);
				
				bean.setId(rs.getInt("id"));
				bean.setCodiceVideogioco(rs.getString("CODICE_VIDEOGIOCO"));
				bean.setCosto(rs.getInt("COSTO"));
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
