package com.scannerapp.ws.common.util.db;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.scannerapp.ws.common.util.log.MessageLogger;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This is a factory class which is used by SqlUtils to fetch database
 * connection.
 * 
 * @author Amit Patel <amit.patel@spec-india.com>
 * @since Jan 23, 2012
 * 
 */
public class ConnectionFactory {

	private static Context context = null;
	// private static Map<String, DataSource> dataSourceCache = new
	// HashMap<String, DataSource>();

	private static DataSource scannerAppDataSource = null;

	static {
		try {
			context = new InitialContext();
			scannerAppDataSource = (DataSource) context
					.lookup("java:comp/env/jdbc/scannerappDB");
		} catch (NamingException exception) {
			exception.printStackTrace();
		}
	}

	private String dataSourceName;

	/**
	 * Static functions are provided hence no instance of this class is to be
	 * created.
	 */
	public ConnectionFactory() {
		this.setDataSourceName(DbConstant.DATA_SOURCE);
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

        public void test(Connection conn) throws Exception
        {
            Statement stmt = null;
            ResultSet rs = null;
            try {
               stmt = conn.createStatement();
               // others
               rs = stmt.executeQuery("SELECT 1");
               rs.next();
            }
            finally {
               if (stmt != null) stmt.close();
               if (rs != null) rs.close();
            }
        }
        
	/**
	 * This function returns database connection from Datasource specified in
	 * application server
	 * 
	 * @return java.sql.Connection for the database.
	 */

	public Connection getDBConnection() throws Exception {

		// String driverName = QueryReader.getQueryFromPropertyFile(
		// ApplicationConstants.DB_CONN_CONST_FILE, "driverName");
		// String dbUrl = QueryReader.getQueryFromPropertyFile(
		// ApplicationConstants.DB_CONN_CONST_FILE, "dbUrl");
		// String username = QueryReader.getQueryFromPropertyFile(
		// ApplicationConstants.DB_CONN_CONST_FILE, "username");
		// String password = QueryReader.getQueryFromPropertyFile(
		// ApplicationConstants.DB_CONN_CONST_FILE, "password");

		Connection connection = null;
		Logger log = MessageLogger.getLogger();

		if (scannerAppDataSource != null) {

			try {
                                while (true)
                                {
                                    try
                                    {
                                        connection = scannerAppDataSource.getConnection();
                                        connection.setAutoCommit(false);
                                        test(connection);
                                        break;
                                    }
                                    catch (Exception e) 
                                    {
                                        try { Thread.sleep(100); }
                                        catch(InterruptedException ex) { }
                                    }
                                }
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw e;
			}
		}

		else {

			throw new Exception(
					"ERROR In Data Source. Data Source Is Not Initialized Properly.");

			// COMMENTED BELOW CODE TO USE DATA SOURCE AS CODED IN ABOVE "IF"
			// PART...

			// try {
			// Class.forName(driverName);
			// connection = DriverManager.getConnection(dbUrl, username,
			// password);
			// connection.setAutoCommit(false);
			// log.info("Connection Successfull");
			//
			// } catch (SQLException e) {
			// log.error(e.getMessage(), e);
			// throw e;
			// } catch (ClassNotFoundException e) {
			// e.printStackTrace();
			// }
		}

		return connection;
	}
}
