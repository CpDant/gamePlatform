package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.DispositivoBean;
import it.unisa.gp.model.interfaceDS.Dispositivo;

public class DispositivoDS implements Dispositivo{

	
	private static final String TABLE_NAME = "dispositivo";
	
	private DataSource ds = null;
	

	public DispositivoDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	
	
	@Override
	public void doSave(DispositivoBean disp) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + DispositivoDS.TABLE_NAME
				+ " (NOME, CODICE_VIDEOGIOCO) VALUES (?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, disp.getNome());
			preparedStmt.setString(2, disp.getCodiceVideogioco());
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
	public boolean doDelete(String name, String cod) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + DispositivoDS.TABLE_NAME + " WHERE NOME = ? AND CODICE_VIDEOGIOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, cod);

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
	public DispositivoBean doRetrieveByKey(String name, String cod) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		DispositivoBean bean = new DispositivoBean(null,null);

		String selectSQL = "SELECT * FROM " + DispositivoDS.TABLE_NAME + " WHERE NOME = ? AND CODICE_VIDEOGIOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, cod);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setNome(rs.getString("NOME"));
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
	public Collection<DispositivoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<DispositivoBean> disp = new LinkedList<DispositivoBean>();

		String selectSQL = "SELECT * FROM " + DispositivoDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				DispositivoBean bean = new DispositivoBean(null,null);
				
				bean.setNome(rs.getString("NOME"));
				bean.setCodiceVideogioco(rs.getString("CODICE_VIDEOGIOCO"));
				disp.add(bean);
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
		return disp;
	}

}
