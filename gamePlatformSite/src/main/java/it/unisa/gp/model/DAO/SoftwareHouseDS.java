package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.SoftwareHouseBean;
import it.unisa.gp.model.interfaceDS.SoftwareHouse;

public class SoftwareHouseDS implements SoftwareHouse{

	private static final String TABLE_NAME = "software_house";
	
	private DataSource ds = null;
	

	public SoftwareHouseDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	
	@Override
	public void doSave(SoftwareHouseBean soft) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + SoftwareHouseDS.TABLE_NAME
				+ " (NOME_UNIVOCO, LOCAZIONE, DATA_DI_FONDAZIONE) VALUES (?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, soft.getNomeUnivoco());
			preparedStmt.setString(2, soft.getLocazione());
			preparedStmt.setDate(3, java.sql.Date.valueOf(soft.getDataDiFondazione()));

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
	public void doUpdate(SoftwareHouseBean soft, String locazione, LocalDate dataFondazione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + SoftwareHouseDS.TABLE_NAME
				+ " SET LOCAZIONE = ? , DATA_DI_FONDAZIONE = ?" + " WHERE NOME_UNIVOCO = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setString(1, locazione);
			preparedStmt.setDate(2, java.sql.Date.valueOf(dataFondazione));
			preparedStmt.setString(3, soft.getNomeUnivoco());
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
	public boolean doDelete(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + SoftwareHouseDS.TABLE_NAME + " WHERE NOME_UNIVOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, name);

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
	public SoftwareHouseBean doRetrieveByKey(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		SoftwareHouseBean bean = new SoftwareHouseBean(null,null,null);

		String selectSQL = "SELECT * FROM " + SoftwareHouseDS.TABLE_NAME + " WHERE NOME_UNIVOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, name);
			

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setNomeUnivoco(rs.getString("NOME_UNIVOCO"));
				bean.setLocazione(rs.getString("LOCAZIONE"));
				Date date = rs.getDate("DATA_DI_FONDAZIONE");
				bean.setDataDiFondazione(date.toLocalDate());
				
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
	public Collection<SoftwareHouseBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<SoftwareHouseBean> softHouse = new LinkedList<SoftwareHouseBean>();

		String selectSQL = "SELECT * FROM " + SoftwareHouseDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				SoftwareHouseBean bean = new SoftwareHouseBean(null,null,null);
				
				bean.setNomeUnivoco(rs.getString("NOME_UNIVOCO"));
				bean.setLocazione(rs.getString("LOCAZIONE"));
				Date date = rs.getDate("DATA_DI_FONDAZIONE");
				bean.setDataDiFondazione(date.toLocalDate());
				softHouse.add(bean);
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
		return softHouse;
	}

}
