package it.unisa.gp.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.gp.model.DAO.WishlistDS;
import it.unisa.gp.model.bean.WishlistBean;
import it.unisa.gp.model.interfaceDS.Wishlist;

/**
 * Servlet implementation class ServletWishlist
 */
@WebServlet("/ServletWishlist")
public class ServletWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletWishlist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<p> Ciao </p>");
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Wishlist abb = new WishlistDS(ds);
		
		WishlistBean bean = new WishlistBean("DNTFNC02A07L245L",2);
		
		
		try {
			abb.doSave(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		try {
			abb.doUpdate(bean,7);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		try {
			abb.doDelete(bean.getCodiceFiscaleCliente());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		try {
			bean = abb.doRetrieveByKey("DNTFNC02A07L245X");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(bean.toString());
		*/
		
		
		
		/*
		Collection<WishlistBean> software = null;

		try {
			software = (Collection<WishlistBean>) abb.doRetrieveAll("CODICE_FISCALE_CLIENTE DESC");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(software.toString());
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
