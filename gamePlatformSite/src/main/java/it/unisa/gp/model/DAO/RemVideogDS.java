package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.RemVideogBean;
import it.unisa.gp.model.interfaceDS.RemVideog;

public class RemVideogDS implements RemVideog{

	private static final String TABLE_NAME = "rem_videog";
	
	private DataSource ds = null;
	
	public RemVideogDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public void doSave(RemVideogBean rem) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + RemVideogDS.TABLE_NAME
				+ " (CODICE_FISCALE_SUP_VID, CODICE_VIDEOGIOCO) VALUES (?, ?)";
		
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, rem.getCodiceFiscaleSupVid());
			preparedStmt.setString(2, rem.getCodiceVideogioco());

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
		
		VideogiocoDS bean = new VideogiocoDS(ds);
		bean.doDelete(rem.getCodiceVideogioco());
		
	}

	@Override
	public boolean doDelete(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RemVideogDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND CODICE_VIDEOGIOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, codiceFiscaleSupVid);
			preparedStmt.setString(2, codiceVideogioco);

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
	public RemVideogBean doRetrieveByKey(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		RemVideogBean bean = new RemVideogBean(null,null);

		String selectSQL = "SELECT * FROM " + RemVideogDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND CODICE_VIDEOGIOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codiceFiscaleSupVid);
			preparedStmt.setString(2, codiceVideogioco);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscaleSupVid(rs.getString("CODICE_FISCALE_SUP_VID"));
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
	public Collection<RemVideogBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<RemVideogBean> array = new LinkedList<RemVideogBean>();

		String selectSQL = "SELECT * FROM " + RemVideogDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				RemVideogBean bean = new RemVideogBean(null,null);
				bean.setCodiceFiscaleSupVid(rs.getString("CODICE_FISCALE_SUP_VID"));
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
