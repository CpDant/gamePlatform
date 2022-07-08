package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.AbbonamentoBean;
import it.unisa.gp.model.bean.AddAbbBean;
import it.unisa.gp.model.interfaceDS.Abbonamento;
import it.unisa.gp.model.interfaceDS.AddAbb;

public class AddAbbDS implements AddAbb{

	private static final String TABLE_NAME = "add_abb";
	
	private DataSource ds = null;
	
	public AddAbbDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public void doSave(AddAbbBean addBean, String nomeUnivoco, int costo, int durata) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + AddAbbDS.TABLE_NAME
				+ " (CODICE_FISCALE_SUP_VID, NOME_UNIVOCO_ABB) VALUES (?, ?)";
		
		AbbonamentoBean abbBean = new AbbonamentoBean(nomeUnivoco,costo,durata);
		Abbonamento abb = new AbbonamentoDS(ds);
		abb.doSave(abbBean);
		
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, addBean.getCodiceFiscaleSupVid());
			preparedStmt.setString(2, addBean.getNomeUnivocoAbb());

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
	public boolean doDelete(String codSup, String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AddAbbDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND NOME_UNIVOCO_ABB = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, codSup);
			preparedStmt.setString(2, nome);

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
	public AddAbbBean doRetrieveByKey(String codSup, String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AddAbbBean bean = new AddAbbBean(null,null);

		String selectSQL = "SELECT * FROM " + AddAbbDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND NOME_UNIVOCO_ABB = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codSup);
			preparedStmt.setString(2, nome);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscaleSupVid(rs.getString("CODICE_FISCALE_SUP_VID"));
				bean.setNomeUnivocoAbb(rs.getString("NOME_UNIVOCO_ABB"));
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
	public Collection<AddAbbBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AddAbbBean> array = new LinkedList<AddAbbBean>();

		String selectSQL = "SELECT * FROM " + AddAbbDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				AddAbbBean bean = new AddAbbBean(null,null);
				bean.setCodiceFiscaleSupVid(rs.getString("CODICE_FISCALE_SUP_VID"));
				bean.setNomeUnivocoAbb(rs.getString("NOME_UNIVOCO_ABB"));
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
