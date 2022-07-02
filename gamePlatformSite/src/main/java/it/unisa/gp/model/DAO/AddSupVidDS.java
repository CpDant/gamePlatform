package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.AddSupVidBean;
import it.unisa.gp.model.bean.SupervisoreVideogiochiBean;
import it.unisa.gp.model.interfaceDS.AddSupVid;
import it.unisa.gp.model.interfaceDS.SupervisoreVideogiochi;

public class AddSupVidDS implements AddSupVid{

	private static final String TABLE_NAME = "add_sup_vid";
	
	private DataSource ds = null;
	
	public AddSupVidDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public synchronized void doSave(AddSupVidBean addSupBean, String codiceFiscale, String nome, String cognome, LocalDate dataNascita,
			String email, String passWord, int retribuzioneAnnuale) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + AddSupVidDS.TABLE_NAME
				+ " (CODICE_FISCALE_SUP_VID, CODICE_FISCALE_ADMIN) VALUES (?, ?)";
		
		
		SupervisoreVideogiochiBean supVidBean = new SupervisoreVideogiochiBean(codiceFiscale,nome,cognome,dataNascita,email,passWord,retribuzioneAnnuale);
		SupervisoreVideogiochi supVid = new SupervisoreVideogiochiDS(ds);
		supVid.doSave(supVidBean);
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, addSupBean.getCodiceFiscaleSupVid());
			preparedStmt.setString(2, addSupBean.getCodiceFiscaleAdmin());

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
	public synchronized boolean doDelete(String supVid, String admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AddSupVidDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND CODICE_FISCALE_ADMIN = ?";

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
	public synchronized AddSupVidBean doRetrieveByKey(String supVid, String admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AddSupVidBean bean = new AddSupVidBean(null,null);

		String selectSQL = "SELECT * FROM " + AddSupVidDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND CODICE_FISCALE_ADMIN = ?";

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
	public synchronized Collection<AddSupVidBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AddSupVidBean> array = new LinkedList<AddSupVidBean>();

		String selectSQL = "SELECT * FROM " + AddSupVidDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				AddSupVidBean bean = new AddSupVidBean(null,null);
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
