package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.AddInAbbBean;
import it.unisa.gp.model.interfaceDS.AddInAbb;

public class AddInAbbDS implements AddInAbb{

	private static final String TABLE_NAME = "add_in_abb";
	
	private DataSource ds = null;
	

	public AddInAbbDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	
	@Override
	public synchronized void doSave(AddInAbbBean addInAbb) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + AddInAbbDS.TABLE_NAME
				+ " (codice_fiscale_sup_vid, CODICE_VIDEOGIOCO, nome_univoco_abb) VALUES (?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, addInAbb.getCodiceFiscaleSupVid());
			preparedStmt.setString(2, addInAbb.getCodiceVideogioco());
			preparedStmt.setString(3, addInAbb.getNomeUnivocoAbb());

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
	public synchronized boolean doDelete(String codiceSup, String codiceVid, String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AddInAbbDS.TABLE_NAME + " WHERE codice_fiscale_sup_vid = ? AND CODICE_VIDEOGIOCO = ? AND nome_univoco_abb = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, codiceSup);
			preparedStmt.setString(2, codiceVid);
			preparedStmt.setString(3, name);

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
	public synchronized AddInAbbBean doRetrieveByKey(String codiceSup, String codiceVid, String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AddInAbbBean bean = new AddInAbbBean(null,null,null);

		String selectSQL = "SELECT * FROM " + AddInAbbDS.TABLE_NAME + " WHERE codice_fiscale_sup_vid = ? AND CODICE_VIDEOGIOCO = ? AND nome_univoco_abb = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codiceSup);
			preparedStmt.setString(2, codiceVid);
			preparedStmt.setString(3, name);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscaleSupVid(rs.getString("codice_fiscale_sup_vid"));
				bean.setCodiceVideogioco(rs.getString("CODICE_VIDEOGIOCO"));
				bean.setNomeUnivocoAbb(rs.getString("nome_univoco_abb"));
				
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
	public synchronized Collection<AddInAbbBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AddInAbbBean> array = new LinkedList<AddInAbbBean>();

		String selectSQL = "SELECT * FROM " + AddInAbbDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				AddInAbbBean bean = new AddInAbbBean(null,null,null);
				
				bean.setCodiceFiscaleSupVid(rs.getString("codice_fiscale_sup_vid"));
				bean.setCodiceVideogioco(rs.getString("CODICE_VIDEOGIOCO"));
				bean.setNomeUnivocoAbb(rs.getString("nome_univoco_abb"));
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
