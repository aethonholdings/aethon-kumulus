package com.scannerapp.dao;

/**
 * Factory class for DAO access
 * 
 * @author Shalin Shah <shalin.shah@spec-india.com>
 * @since 10/July/2013
 */
public class DAOFactory {

	private static LoginDAO loginDAO = null;
	private static NodeDAO nodeDAO = null;

	/**
	 * Method to get the {@link LoginDAO} instance.
	 * 
	 * @return
	 */
	public static LoginDAO getLoginDAO() {

		if (loginDAO == null)
			loginDAO = new LoginDAO();

		return loginDAO;
	}

	/**
	 * Method to get the {@link NodeDAO} instance.
	 * 
	 * @return
	 */
	public static NodeDAO getNodeDAO() {

		if (nodeDAO == null)
			nodeDAO = new NodeDAO();

		return nodeDAO;
	}
}
