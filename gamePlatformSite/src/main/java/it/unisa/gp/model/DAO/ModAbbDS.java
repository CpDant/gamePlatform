package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.AbbonamentoBean;
import it.unisa.gp.model.bean.ModAbbBean;
import it.unisa.gp.model.interfaceDS.Abbonamento;
import it.unisa.gp.model.interfaceDS.ModAbb;

public class ModAbbDS implements ModAbb{

	
	private static final String TABLE_NAME = "mod_abb";
	
	private DataSource ds = null;
	
	public ModAbbDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");	
	}
	
	
	@Override
	public void doSave(ModAbbBean modBean, String nomeUnivoco, int costo, int durata, boolean eliminato) throws SQLException {
		/*
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		String insertSQL = "INSERT INTO " + ModAbbDS.TABLE_NAME
				+ " (CODICE_FISCALE_SUP_VID, nome_univoco_abb) VALUES (?, ?)";
		
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, modBean.getCodiceFiscaleSupVid());
			preparedStmt.setString(2, modBean.getNomeUnivocoAbb());

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
		*/
		Abbonamento abb = new AbbonamentoDS(ds);
		AbbonamentoBean abbBean = new AbbonamentoBean(nomeUnivoco,0,0);
		abb.doUpdate(abbBean,costo,durata,eliminato);
		
	}

	@Override
	public boolean doDelete(String codSup, String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ModAbbDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND nome_univoco_abb = ?";

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
	public ModAbbBean doRetrieveByKey(String codSup, String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		ModAbbBean bean = new ModAbbBean(null,null);

		String selectSQL = "SELECT * FROM " + ModAbbDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND nome_univoco_abb = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codSup);
			preparedStmt.setString(2, nome);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscaleSupVid(rs.getString("CODICE_FISCALE_SUP_VID"));
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
	public Collection<ModAbbBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<ModAbbBean> array = new LinkedList<ModAbbBean>();

		String selectSQL = "SELECT * FROM " + ModAbbDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				ModAbbBean bean = new ModAbbBean(null,null);
				bean.setCodiceFiscaleSupVid(rs.getString("CODICE_FISCALE_SUP_VID"));
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
