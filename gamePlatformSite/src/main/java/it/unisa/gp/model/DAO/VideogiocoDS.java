package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;
import it.unisa.gp.model.bean.VideogiocoBean;
import it.unisa.gp.model.bean.VideogiocoBean.Pegi;
import it.unisa.gp.model.interfaceDS.Videogioco;

public class VideogiocoDS implements Videogioco {
	private static final String TABLE_NAME = "videogioco";
	private DataSource ds = null;
	
	public VideogiocoDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	@Override
	public synchronized void doSave(VideogiocoBean vid) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + VideogiocoDS.TABLE_NAME
				+ " (CODICE, NOME_SOFTWARE_HOUSE, NOME_VIDEOGIOCO, DIMENSIONE, PEGI, "
				+ "ANNO_DI_PRODUZIONE, COSTO) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, vid.getCodice());
			preparedStmt.setString(2, vid.getNomeSoftwareHouse());
			preparedStmt.setString(3, vid.getNomeVideogioco());
			preparedStmt.setInt(4, vid.getDimensione());
			preparedStmt.setString(5, vid.getPegi().toString());
			preparedStmt.setInt(6, vid.getAnnoDiProduzione());
			preparedStmt.setInt(7, vid.getCosto());

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
	public synchronized void doUpdate(VideogiocoBean vid, String nomeVideogioco, int dimensione, Pegi pegi, int annoProduzione,
			int costo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String updateSQL = "UPDATE " + VideogiocoDS.TABLE_NAME
				+ " SET NOME_VIDEOGIOCO = ?, DIMENSIONE = ?, PEGI = ?, ANNO_DI_PRODUZIONE = ?, COSTO = ?" + " WHERE CODICE = ?";
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(updateSQL);
			preparedStmt.setString(1, nomeVideogioco);
			preparedStmt.setInt(2, dimensione);
			preparedStmt.setString(3, pegi.toString());
			preparedStmt.setInt(4, annoProduzione);
			preparedStmt.setInt(5, costo);
			preparedStmt.setString(6, vid.getCodice());

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
	public synchronized boolean doDelete(String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + VideogiocoDS.TABLE_NAME + " WHERE CODICE = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, codice);

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
	public synchronized VideogiocoBean doRetrieveByKey(String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		VideogiocoBean bean = new VideogiocoBean(null,null,null,0,0,0,null);

		String selectSQL = "SELECT * FROM " + VideogiocoDS.TABLE_NAME + " WHERE CODICE = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codice);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getString("CODICE"));
				bean.setNomeSoftwareHouse(rs.getString("NOME_SOFTWARE_HOUSE"));
				bean.setNomeVideogioco(rs.getString("NOME_VIDEOGIOCO"));
				bean.setDimensione(rs.getInt("DIMENSIONE"));
				bean.setPegi(Pegi.valueOf(rs.getString("PEGI")));
				bean.setAnnoDiProduzione(rs.getInt("ANNO_DI_PRODUZIONE"));
				bean.setCosto(rs.getInt("COSTO"));
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
	public synchronized Collection<VideogiocoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<VideogiocoBean> array = new LinkedList<VideogiocoBean>();

		String selectSQL = "SELECT * FROM " + VideogiocoDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				VideogiocoBean bean = new VideogiocoBean(null,null,null,0,0,0,null);
				bean.setCodice(rs.getString("CODICE"));
				bean.setNomeSoftwareHouse(rs.getString("NOME_SOFTWARE_HOUSE"));
				bean.setNomeVideogioco(rs.getString("NOME_VIDEOGIOCO"));
				bean.setDimensione(rs.getInt("DIMENSIONE"));
				bean.setPegi(Pegi.valueOf(rs.getString("PEGI")));
				bean.setAnnoDiProduzione(rs.getInt("ANNO_DI_PRODUZIONE"));
				bean.setCosto(rs.getInt("COSTO"));
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
