package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.WishlistBean;
import it.unisa.gp.model.interfaceDS.Wishlist;

public class WishlistDS implements Wishlist{

	
	private static final String TABLE_NAME = "wishlist";
	
	private DataSource ds = null;
	

	public WishlistDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	
	@Override
	public synchronized void doSave(WishlistBean wish) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + WishlistDS.TABLE_NAME
				+ " (CODICE_FISCALE_CLIENTE, N_PROD) VALUES (?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, wish.getCodiceFiscaleCliente());
			preparedStmt.setInt(2, wish.getnProd());
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
	public synchronized void doUpdate(WishlistBean wish, int nProd) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + WishlistDS.TABLE_NAME
				+ " SET N_PROD = ?" + " WHERE CODICE_FISCALE_CLIENTE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setInt(1, nProd);
			preparedStmt.setString(2, wish.getCodiceFiscaleCliente());
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
	public synchronized boolean doDelete(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + WishlistDS.TABLE_NAME + " WHERE CODICE_FISCALE_CLIENTE = ?";

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
	public synchronized WishlistBean doRetrieveByKey(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		WishlistBean bean = new WishlistBean(null,0);

		String selectSQL = "SELECT * FROM " + WishlistDS.TABLE_NAME + " WHERE CODICE_FISCALE_CLIENTE = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, name);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setnProd(rs.getInt("N_PROD"));
				
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
	public synchronized Collection<WishlistBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<WishlistBean> wish = new LinkedList<WishlistBean>();

		String selectSQL = "SELECT * FROM " + WishlistDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				WishlistBean bean = new WishlistBean(null,0);
				
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setnProd(rs.getInt("N_PROD"));
				wish.add(bean);
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
		return wish;
	}

}
