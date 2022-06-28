package it.unisa.gp.model.interfaceDS;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;

import it.unisa.gp.model.bean.SoftwareHouseBean;

public interface SoftwareHouse {

	public void doSave(SoftwareHouseBean soft) throws SQLException;
	
	public void doUpdate(SoftwareHouseBean soft, String locazione, LocalDate dataFondazione) throws SQLException;

	public boolean doDelete(String name) throws SQLException;

	public SoftwareHouseBean doRetrieveByKey(String name) throws SQLException;
	
	public Collection<SoftwareHouseBean> doRetrieveAll(String order) throws SQLException;
}
