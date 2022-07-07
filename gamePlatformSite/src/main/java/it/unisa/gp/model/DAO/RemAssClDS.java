package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.RemAssClBean;
import it.unisa.gp.model.bean.AssistenteClientiBean;
import it.unisa.gp.model.interfaceDS.AssistenteClienti;
import it.unisa.gp.model.interfaceDS.RemAssCl;

public class RemAssClDS implements RemAssCl{

private static final String TABLE_NAME = "rem_assist_cl";
	
	private DataSource ds = null;
	
	public RemAssClDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public synchronized void doSave(RemAssClBean remAssBean, String codiceFiscale) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + RemAssClDS.TABLE_NAME
				+ " (CODICE_FISCALE_ASSIST_CL, CODICE_FISCALE_ADMIN) VALUES (?, ?)";
		
		
		
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, remAssBean.getCodiceFiscaleAssistCl());
			preparedStmt.setString(2, remAssBean.getCodiceFiscaleAdmin());

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
		AssistenteClienti assCl = new AssistenteClientiDS(ds);
		assCl.doDelete(codiceFiscale);
		
	}

	@Override
	public synchronized boolean doDelete(String assCl, String admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RemAssClDS.TABLE_NAME + " WHERE CODICE_FISCALE_ASSIST_CL = ? AND CODICE_FISCALE_ADMIN = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, assCl);
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
	public synchronized RemAssClBean doRetrieveByKey(String assCl, String admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		RemAssClBean bean = new RemAssClBean(null,null);

		String selectSQL = "SELECT * FROM " + RemAssClDS.TABLE_NAME + " WHERE CODICE_FISCALE_ASSIST_CL = ? AND CODICE_FISCALE_ADMIN = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, assCl);
			preparedStmt.setString(2, admin);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscaleAssistCl(rs.getString("CODICE_FISCALE_ASSIST_CL"));
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
	public synchronized Collection<RemAssClBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<RemAssClBean> array = new LinkedList<RemAssClBean>();

		String selectSQL = "SELECT * FROM " + RemAssClDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				RemAssClBean bean = new RemAssClBean(null,null);
				bean.setCodiceFiscaleAssistCl(rs.getString("CODICE_FISCALE_ASSIST_CL"));
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
