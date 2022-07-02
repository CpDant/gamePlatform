package it.unisa.gp.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;
import it.unisa.gp.model.bean.AddAssClBean;
import it.unisa.gp.model.bean.AssistenteClientiBean;
import it.unisa.gp.model.bean.SupervisoreVideogiochiBean;
import it.unisa.gp.model.interfaceDS.AddAssCl;
import it.unisa.gp.model.interfaceDS.AssistenteClienti;
import it.unisa.gp.model.interfaceDS.SupervisoreVideogiochi;

public class AddAssClDS implements AddAssCl{

	private static final String TABLE_NAME = "add_assist_cl";
	
	private DataSource ds = null;
	
	public AddAssClDS(DataSource ds) {
		this.ds = ds;
		
		System.out.println("Creazione DataSource...");
	}
	
	@Override
	public synchronized void doSave(AddAssClBean addAssBean, String codiceFiscale, String nome, String cognome, LocalDate dataNascita,
			String email, String passWord, int retribuzioneAnnuale) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		
		String insertSQL = "INSERT INTO " + AddAssClDS.TABLE_NAME
				+ " (CODICE_FISCALE_ASSIST_CL, CODICE_FISCALE_ADMIN) VALUES (?, ?)";
		
		
		AssistenteClientiBean assClBean = new AssistenteClientiBean(codiceFiscale,nome,cognome,dataNascita,email,passWord,retribuzioneAnnuale);
		AssistenteClienti assCl = new AssistenteClientiDS(ds);
		assCl.doSave(assClBean);
		
		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(insertSQL);
			preparedStmt.setString(1, addAssBean.getCodiceFiscaleAssCl());
			preparedStmt.setString(2, addAssBean.getCodiceFiscaleAdmin());

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
	public synchronized boolean doDelete(String assCl, String admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AddAssClDS.TABLE_NAME + " WHERE CODICE_FISCALE_ASSIST_CL = ? AND CODICE_FISCALE_ADMIN = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(deleteSQL);
			preparedStmt.setString(1, assCl);
			preparedStmt.setString(2, admin);

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
	public synchronized AddAssClBean doRetrieveByKey(String assCl, String admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		AddAssClBean bean = new AddAssClBean(null,null);

		String selectSQL = "SELECT * FROM " + AddAssClDS.TABLE_NAME + " WHERE CODICE_FISCALE_ASSIST_CL = ? AND CODICE_FISCALE_ADMIN = ?";

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);
			preparedStmt.setString(1, assCl);
			preparedStmt.setString(2, admin);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscaleAssCl(rs.getString("CODICE_FISCALE_ASSIST_CL"));
				bean.setCodiceFiscaleAdmin(rs.getString("CODICE_FISCALE_ADMIN"));
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
	public synchronized Collection<AddAssClBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStmt = null;

		Collection<AddAssClBean> array = new LinkedList<AddAssClBean>();

		String selectSQL = "SELECT * FROM " + AddAssClDS.TABLE_NAME;
		

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStmt = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				AddAssClBean bean = new AddAssClBean(null,null);
				bean.setCodiceFiscaleAssCl(rs.getString("CODICE_FISCALE_ASSIST_CL"));
				bean.setCodiceFiscaleAdmin(rs.getString("CODICE_FISCALE_ADMIN"));
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
