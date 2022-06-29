package it.unisa.gp.model.interfaceDS;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.gp.model.bean.WishlistBean;


public interface Wishlist {

	public void doSave(WishlistBean wish) throws SQLException;
	
	public void doUpdate(WishlistBean wish, int nProd) throws SQLException;

	public boolean doDelete(String name) throws SQLException;

	public WishlistBean doRetrieveByKey(String name) throws SQLException;
	
	public Collection<WishlistBean> doRetrieveAll(String order) throws SQLException;
	
	
}
