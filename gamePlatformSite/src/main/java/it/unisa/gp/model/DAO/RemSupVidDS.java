package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.RemSupVidBean;
import it.unisa.gp.model.interfaceDS.RemSupVid;
import it.unisa.gp.model.interfaceDS.SupervisoreVideogiochi;

public class RemSupVidDS implements RemSupVid{

	private static final String TABLE_NAME = "rem_sup_vid";
	
	private DataSource ds = null;
	
	public RemSupVidDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public synchronized void doSave(RemSupVidBean remSupVidBean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + RemSupVidDS.TABLE_NAME
				+ " (CODICE_FISCALE_SUP_VID, CODICE_FISCALE_ADMIN) VALUES (?, ?)";
				
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, remSupVidBean.getCodiceFiscaleSupVid());
			preparedStmt.setString(2, remSupVidBean.getCodiceFiscaleAdmin());

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
		
		SupervisoreVideogiochi supVid = new SupervisoreVideogiochiDS(ds);
		supVid.doDelete(remSupVidBean.getCodiceFiscaleSupVid());
		
	}

	@Override
	public synchronized boolean doDelete(String supVid, String admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RemSupVidDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND CODICE_FISCALE_ADMIN = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, supVid);
			preparedStmt.setString(2, admin);

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
	public synchronized RemSupVidBean doRetrieveByKey(String supVid, String admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		RemSupVidBean bean = new RemSupVidBean(null,null);

		String selectSQL = "SELECT * FROM " + RemSupVidDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND CODICE_FISCALE_ADMIN = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, supVid);
			preparedStmt.setString(2, admin);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscaleSupVid(rs.getString("CODICE_FISCALE_SUP_VID"));
				bean.setCodiceFiscaleAdmin(rs.getString("CODICE_FISCALE_ADMIN"));
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
	public synchronized Collection<RemSupVidBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<RemSupVidBean> array = new LinkedList<RemSupVidBean>();

		String selectSQL = "SELECT * FROM " + RemSupVidDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				RemSupVidBean bean = new RemSupVidBean(null,null);
				bean.setCodiceFiscaleSupVid(rs.getString("CODICE_FISCALE_SUP_VID"));
				bean.setCodiceFiscaleAdmin(rs.getString("CODICE_FISCALE_ADMIN"));
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
