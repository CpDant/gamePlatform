package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

import it.unisa.gp.model.bean.AddInAbbBean;
import it.unisa.gp.model.bean.RemInAbbBean;
import it.unisa.gp.model.interfaceDS.AddInAbb;
import it.unisa.gp.model.interfaceDS.RemInAbb;

public class RemInAbbDS implements RemInAbb{
	private static final String TABLE_NAME = "rem_in_abb";
	
	private DataSource ds = null;
	

	public RemInAbbDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public void doSave(RemInAbbBean remInAbb) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + RemInAbbDS.TABLE_NAME
				+ " (codice_fiscale_sup_vid, CODICE_VIDEOGIOCO, nome_univoco_abb) VALUES (?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, remInAbb.getCodiceFiscaleSupVid());
			preparedStmt.setString(2, remInAbb.getCodiceVideogioco());
			preparedStmt.setString(3, remInAbb.getNomeUnivocoAbb());

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
		
		AddInAbb add = new AddInAbbDS(ds);
		add.doDelete(remInAbb.getCodiceVideogioco(), remInAbb.getNomeUnivocoAbb());
	}

	@Override
	public boolean doDelete(String codiceSup, String codiceVid, String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RemInAbbDS.TABLE_NAME + " WHERE codice_fiscale_sup_vid = ? AND CODICE_VIDEOGIOCO = ? AND nome_univoco_abb = ?";

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
	public RemInAbbBean doRetrieveByKey(String codiceSup, String codiceVid, String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		RemInAbbBean bean = new RemInAbbBean(null,null,null);

		String selectSQL = "SELECT * FROM " + RemInAbbDS.TABLE_NAME + " WHERE codice_fiscale_sup_vid = ? AND CODICE_VIDEOGIOCO = ? AND nome_univoco_abb = ?";

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
	public Collection<RemInAbbBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<RemInAbbBean> array = new LinkedList<RemInAbbBean>();

		String selectSQL = "SELECT * FROM " + RemInAbbDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				RemInAbbBean bean = new RemInAbbBean(null,null,null);
				
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
