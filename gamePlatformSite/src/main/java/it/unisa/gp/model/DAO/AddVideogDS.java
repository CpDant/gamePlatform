package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.gp.model.bean.AddVideogBean;
import it.unisa.gp.model.bean.VideogiocoBean;
import it.unisa.gp.model.bean.VideogiocoBean.Pegi;
import it.unisa.gp.model.interfaceDS.AddVideog;
import it.unisa.gp.model.interfaceDS.Videogioco;

public class AddVideogDS implements AddVideog{

private static final String TABLE_NAME = "add_videog";
	
	private DataSource ds = null;
	
	public AddVideogDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public void doSave(AddVideogBean add, String codice, String nomeSoftwareHouse, String nomeVideogioco, int dimensione,
			int annoDiProduzione, int costo, Pegi pegi) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + AddVideogDS.TABLE_NAME
				+ " (CODICE_FISCALE_SUP_VID, CODICE_VIDEOGIOCO) VALUES (?, ?)";
		
		VideogiocoBean bean = new VideogiocoBean(codice,nomeSoftwareHouse,nomeVideogioco,dimensione,annoDiProduzione,costo,pegi);
		Videogioco vid = new VideogiocoDS(ds);
		vid.doSave(bean);
		
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, add.getCodiceFiscaleSupVid());
			preparedStmt.setString(2, add.getCodiceVideogioco());

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
	public boolean doDelete(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AddVideogDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND CODICE_VIDEOGIOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, codiceFiscaleSupVid);
			preparedStmt.setString(2, codiceVideogioco);

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
	public AddVideogBean doRetrieveByKey(String codiceFiscaleSupVid, String codiceVideogioco) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AddVideogBean bean = new AddVideogBean(null,null);

		String selectSQL = "SELECT * FROM " + AddVideogDS.TABLE_NAME + " WHERE CODICE_FISCALE_SUP_VID = ? AND CODICE_VIDEOGIOCO = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, codiceFiscaleSupVid);
			preparedStmt.setString(2, codiceVideogioco);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscaleSupVid(rs.getString("CODICE_FISCALE_SUP_VID"));
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
	public Collection<AddVideogBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AddVideogBean> array = new LinkedList<AddVideogBean>();

		String selectSQL = "SELECT * FROM " + AddVideogDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				AddVideogBean bean = new AddVideogBean(null,null);
				bean.setCodiceFiscaleSupVid(rs.getString("CODICE_FISCALE_SUP_VID"));
				bean.setCodiceVideogioco(rs.getString("CODICE_VIDEOGIOCO"));
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
