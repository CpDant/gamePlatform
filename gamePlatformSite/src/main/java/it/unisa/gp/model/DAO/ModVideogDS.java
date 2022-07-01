package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.ModVideogBean;
import it.unisa.gp.model.interfaceDS.ModVideog;

public class ModVideogDS implements ModVideog {

	private static final String TABLE_NAME = "mod_videog";
	
	private DataSource ds = null;
	
	public ModVideogDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");	
	}
	
	@Override
	public void doSave(ModVideogBean mod) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + ModVideogDS.TABLE_NAME
				+ " (CODICE_FISCALE_SUP_VID, CODICE_VIDEOGIOCO) VALUES (?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, mod.getCodiceFiscaleSupVid());
			preparedStmt.setString(2, mod.getCodiceVideogioco());

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
	public boolean doDelete(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ModVideogDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND CODICE_VIDEOGIOCO = ?";

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
	public ModVideogBean doRetrieveByKey(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		ModVideogBean bean = new ModVideogBean(null,null);

		String selectSQL = "SELECT * FROM " + ModVideogDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND CODICE_VIDEOGIOCO = ?";

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
	public Collection<ModVideogBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<ModVideogBean> array = new LinkedList<ModVideogBean>();

		String selectSQL = "SELECT * FROM " + ModVideogDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				ModVideogBean bean = new ModVideogBean(null,null);
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
