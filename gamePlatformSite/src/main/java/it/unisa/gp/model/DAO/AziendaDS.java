package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.AziendaBean;
import it.unisa.gp.model.interfaceDS.Azienda;

public class AziendaDS implements Azienda{

	private static final String TABLE_NAME = "azienda";
	
	private DataSource ds = null;

	public AziendaDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	@Override
	public synchronized void doSave(AziendaBean azi) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + AziendaDS.TABLE_NAME
				+ " (P_IVA, CODICE_FISCALE_CLIENTE, SDI, PEC) VALUES (?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, azi.getpIva());
			preparedStmt.setString(2, azi.getCodiceFiscaleCliente());
			preparedStmt.setString(3, azi.getSdi());
			preparedStmt.setString(4, azi.getPec());
			
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
	public synchronized void doUpdate(AziendaBean azi, String sdi, String pec) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + AziendaDS.TABLE_NAME
				+ " SET SDI = ?, PEC = ?" + " WHERE P_IVA = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setString(1, sdi);
			preparedStmt.setString(2, pec);
			preparedStmt.setString(3, azi.getpIva());

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
	public synchronized boolean doDelete(String pIva) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AziendaDS.TABLE_NAME + " WHERE P_IVA = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, pIva);

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
	public synchronized AziendaBean doRetrieveByKey(String pIva) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AziendaBean bean = new AziendaBean(null,null,null,null);

		String selectSQL = "SELECT * FROM " + AziendaDS.TABLE_NAME + " WHERE P_IVA = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, pIva);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setpIva(rs.getString("P_IVA"));
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setSdi(rs.getString("SDI"));
				bean.setPec(rs.getString("PEC"));
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
	public synchronized AziendaBean doRetrieveByKeyCodFis(String codFis) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AziendaBean bean = new AziendaBean(null,null,null,null);

		String selectSQL = "SELECT * FROM " + AziendaDS.TABLE_NAME + " WHERE CODICE_FISCALE_CLIENTE = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codFis);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setpIva(rs.getString("P_IVA"));
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setSdi(rs.getString("SDI"));
				bean.setPec(rs.getString("PEC"));
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
	public synchronized Collection<AziendaBean> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AziendaBean> array = new LinkedList<AziendaBean>();

		String selectSQL = "SELECT * FROM " + AziendaDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				AziendaBean bean = new AziendaBean(null,null,null,null);
				bean.setpIva(rs.getString("P_IVA"));
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setSdi(rs.getString("SDI"));
				bean.setPec(rs.getString("PEC"));
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
