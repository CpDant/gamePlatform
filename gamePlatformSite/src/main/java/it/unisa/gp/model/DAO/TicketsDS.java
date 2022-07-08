package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.TicketsBean;
import it.unisa.gp.model.bean.TicketsBean.CategoriaProbl;
import it.unisa.gp.model.interfaceDS.Tickets;

public class TicketsDS implements Tickets{

	private static final String TABLE_NAME = "tickets";
	
	private DataSource ds = null;
	
	public TicketsDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}		
	

	@Override
	public synchronized void doSave(TicketsBean tic) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + TicketsDS.TABLE_NAME
				+ " (ID, CODICE_FISCALE_ASSIST_CL, CODICE_FISCALE_CLIENTE, RESOLVED, CATEGORIA_PROBL, MESSAGGIO, DATA_ORA) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setLong(1, tic.getId());
			preparedStmt.setString(2, tic.getCodiceFiscaleAssistCl());
			preparedStmt.setString(3, tic.getCodiceFiscaleCliente());
			preparedStmt.setBoolean(4, tic.isResolved());
			preparedStmt.setString(5, tic.getCategoria().toString());
			preparedStmt.setString(6, tic.getMessaggio());
			preparedStmt.setTimestamp(7, java.sql.Timestamp.valueOf(tic.getDataOra()));

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
	public synchronized void doUpdate(TicketsBean tic, boolean resolved, CategoriaProbl categoriaProbl,
			String messaggio, LocalDateTime dataOra) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + TicketsDS.TABLE_NAME
				+ " SET RESOLVED = ? , CATEGORIA_PROBL = ? , MESSAGGIO = ?, DATA_ORA = ?" + " WHERE ID = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setBoolean(1, resolved);
			preparedStmt.setString(2, categoriaProbl.toString());
			preparedStmt.setString(3, messaggio);
			preparedStmt.setTimestamp(4, java.sql.Timestamp.valueOf(dataOra));
			preparedStmt.setInt(5, tic.getId());
			
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
	public synchronized boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TicketsDS.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setInt(1, id);

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
	public synchronized TicketsBean doRetrieveByKey(int id) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		TicketsBean bean = new TicketsBean(0, null, null, null, null, null);

		String selectSQL = "SELECT * FROM " + TicketsDS.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("ID"));
				bean.setCodiceFiscaleAssistCl(rs.getString("CODICE_FISCALE_ASSIST_CL"));
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setResolved(rs.getBoolean("Resolved"));
				bean.setCategoria(CategoriaProbl.valueOf(rs.getString("CATEGORIA_PROBL")));											
				bean.setMessaggio(rs.getString("MESSAGGIO"));
				bean.setDataOra((rs.getTimestamp("DATA_ORA").toLocalDateTime()));
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
	
	public synchronized Collection<TicketsBean> doRetrieveAllCliente(String order, String codFisc) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<TicketsBean> array = new LinkedList<TicketsBean>();

		String selectSQL = "SELECT * FROM " + TicketsDS.TABLE_NAME + " WHERE codice_fiscale_cliente = ?";
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codFisc);
			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				TicketsBean bean = new TicketsBean(0, null, null, null, null, null);
				bean.setId(rs.getInt("ID"));
				bean.setCodiceFiscaleAssistCl(rs.getString("CODICE_FISCALE_ASSIST_CL"));
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setResolved(rs.getBoolean("Resolved"));
				bean.setCategoria(CategoriaProbl.valueOf(rs.getString("CATEGORIA_PROBL")));											
				bean.setMessaggio(rs.getString("MESSAGGIO"));
				bean.setDataOra((rs.getTimestamp("DATA_ORA").toLocalDateTime()));
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

	public synchronized Collection<TicketsBean> doRetrieveAllAssCl(String order, String codFisc) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
	
		Collection<TicketsBean> array = new LinkedList<TicketsBean>();
	
		String selectSQL = "SELECT * FROM " + TicketsDS.TABLE_NAME + " WHERE codice_fiscale_assist_cl = ?";
		
	
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
	
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codFisc);
			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				TicketsBean bean = new TicketsBean(0, null, null, null, null, null);
				bean.setId(rs.getInt("ID"));
				bean.setCodiceFiscaleAssistCl(rs.getString("CODICE_FISCALE_ASSIST_CL"));
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setResolved(rs.getBoolean("Resolved"));
				bean.setCategoria(CategoriaProbl.valueOf(rs.getString("CATEGORIA_PROBL")));											
				bean.setMessaggio(rs.getString("MESSAGGIO"));
				bean.setDataOra((rs.getTimestamp("DATA_ORA").toLocalDateTime()));
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

	@Override
	public synchronized Collection<TicketsBean> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<TicketsBean> array = new LinkedList<TicketsBean>();

		String selectSQL = "SELECT * FROM " + TicketsDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				TicketsBean bean = new TicketsBean(0, null, null, null, null, null);
				bean.setId(rs.getInt("ID"));
				bean.setCodiceFiscaleAssistCl(rs.getString("CODICE_FISCALE_ASSIST_CL"));
				bean.setCodiceFiscaleCliente(rs.getString("CODICE_FISCALE_CLIENTE"));
				bean.setResolved(rs.getBoolean("Resolved"));
				bean.setCategoria(CategoriaProbl.valueOf(rs.getString("CATEGORIA_PROBL")));											
				bean.setMessaggio(rs.getString("MESSAGGIO"));
				bean.setDataOra((rs.getTimestamp("DATA_ORA").toLocalDateTime()));
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
