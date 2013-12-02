package com.scannerapp.ws.common.util.db;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.StructDescriptor;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

import com.scannerapp.ws.common.exception.DaoException;
import com.scannerapp.ws.common.exception.FatalException;
import com.scannerapp.ws.common.util.configuration.ApplicationConstants;
import com.scannerapp.ws.common.util.configuration.I18NUtils;
import com.scannerapp.ws.common.util.db.beans.Catalog;
import com.scannerapp.ws.common.util.log.MessageLogger;

/**
 * 
 * This class is concerned with all the database operations. Following
 * operations are supported.
 * <ul>
 * <li>Selecting a single bean</li>
 * <li>Selecting a List of beans</li>
 * <li>Selecting a row as a hashMap of a table</li>
 * <li>Selecting a List of rows of hashMap of a table</li>
 * <li>Inserting a bean Updating a bean executing a dml query</li>
 * </ul>
 * 
 * @author Niraj Salot <niraj.salot@spec-india.com>
 * @since Apr 27, 2011
 * 
 */
public class SqlUtil {

	Logger logger = MessageLogger.getLogger();

	ConnectionFactory connectionFactory;

	/**
	 * All static methods are supplied, so no instance of this class is to be
	 * created.
	 * 
	 */
	public SqlUtil() {
		connectionFactory = new ConnectionFactory();
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	/**
	 * Executes a SQL Query with or without replacement parameters.
	 * 
	 * @param <T>
	 *            The type of bean class that the function returns
	 * @param query
	 *            The SQL query to be executed
	 * @param beanClass
	 *            The bean class which is to be returned.
	 * @param params
	 *            The replacement parameters which are to be passed to query
	 * @return A first row from result. The bean class
	 * @throws FatalException
	 */
	public <T> T getQueryBean(String query, Class<T> beanClass,
			Object... params) throws Exception {

		logger.debug("Inside getQueryBean ..SQL Query is :" + query);
		Connection connection = connectionFactory.getDBConnection();
		ResultSetHandler<T> resultSetHandler = new BeanHandler<T>(beanClass);
		QueryRunner queryRunner = new QueryRunner(true);
		T result = null;

		try {
			result = queryRunner.query(connection, query, resultSetHandler,
					params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE, "sqlutil.querybean"));
		} finally {
			DbUtils.closeQuietly(connection);
		}

		return result;
	}

	/**
	 * Executes a SQL Query with or without replacement parameters. This method
	 * expects SQL Connection object as inputs
	 * 
	 * @param connection
	 *            Connection object got from Datasource lookup
	 * @param <T>
	 *            The type of bean class that the function returns
	 * @param query
	 *            The SQL query to be executed
	 * @param beanClass
	 *            The bean class which is to be returned.
	 * @param params
	 *            The replacement parameters which are to be passed to query
	 * @return A first row from result. The bean class
	 * @throws FatalException
	 */
	public <T> T getQueryBean(Connection connection, String query,
			Class<T> beanClass, Object... params) throws FatalException {

		logger.debug("Inside getQueryBean with Connection Object as Input ..SQL Query is :"
				+ query);
		ResultSetHandler<T> resultSetHandler = new BeanHandler<T>(beanClass);
		QueryRunner queryRunner = new QueryRunner(true);
		T result = null;

		try {
			result = queryRunner.query(connection, query, resultSetHandler,
					params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE, "sqlutil.querybean"));
		}
		return result;
	}

	/**
	 * Executes a SQL Query with or without replacement parameters.
	 * 
	 * @param <T>
	 *            The type of bean class that the function returns
	 * @param query
	 *            The SQL query to be executed
	 * @param beanClass
	 *            The bean class which is to be returned.
	 * @param params
	 *            The replacement parameters which are to be passed to query
	 * @return A List containing bean class
	 * @throws FatalException
	 */
	public <T> List<T> getQueryBeans(String query, Class<T> beanClass,
			Object... params) throws Exception {

		logger.debug("Inside getQueryBeans ..SQL Query is :" + query);
		Connection connection = connectionFactory.getDBConnection();

		ResultSetHandler<List<T>> resultSetHandler = new BeanListHandler<T>(
				beanClass);
		QueryRunner queryRunner = new QueryRunner(true);
		List<T> result = null;

		try {
			result = queryRunner.query(connection, query, resultSetHandler,
					params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DaoException(
					I18NUtils.getMessageFromBundle(
							ApplicationConstants.DB_MESSAGES_FILE,
							"sqlutil.querybeans"));
		} finally {
			DbUtils.closeQuietly(connection);
		}

		return result;

	}

	/**
	 * Executes a SQL Query with or without replacement parameters.
	 * 
	 * @param connection
	 *            Connection object got from Datasource lookup
	 * @param <T>
	 *            The type of bean class that the function returns
	 * @param query
	 *            The SQL query to be executed
	 * @param beanClass
	 *            The bean class which is to be returned.
	 * @param params
	 *            The replacement parameters which are to be passed to query
	 * @return A List containing bean class
	 * @throws FatalException
	 */
	public <T> List<T> getQueryBeans(Connection connection, String query,
			Class<T> beanClass, Object... params) throws FatalException {

		logger.debug("Inside getQueryBeans with Connection Object..SQL Query is :"
				+ query);
		ResultSetHandler<List<T>> resultSetHandler = new BeanListHandler<T>(
				beanClass);
		QueryRunner queryRunner = new QueryRunner(true);
		List<T> result = null;

		try {
			result = queryRunner.query(connection, query, resultSetHandler,
					params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DaoException(
					I18NUtils.getMessageFromBundle(
							ApplicationConstants.DB_MESSAGES_FILE,
							"sqlutil.querybeans"));
		}
		return result;

	}

	/**
	 * Executes a SQL Query with or without replacement parameters.
	 * 
	 * @param query
	 *            The SQL query to be executed
	 * @param params
	 *            The replacement parameters which are to be passed to query
	 * @return First row from query. A Map containing key as columnNames and
	 *         value as database value.
	 * @throws FatalException
	 */
	public Map<String, Object> getQueryMap(String query, Object... params)
			throws Exception {

		logger.debug("Inside getQueryMap ..SQL Query is :" + query);
		Connection connection = connectionFactory.getDBConnection();
		ResultSetHandler<Map<String, Object>> handler = new MapHandler();
		QueryRunner queryRunner = new QueryRunner(true);
		Map<String, Object> result = null;

		try {
			result = queryRunner.query(connection, query, handler, params);
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE, "sqlutil.querymap"));
		} finally {
			DbUtils.closeQuietly(connection);
		}

		return result;
	}

	/**
	 * Executes a SQL Query with or without replacement parameters.
	 * 
	 * @param connection
	 *            Connection object got from Datasource lookup
	 * @param query
	 *            The SQL query to be executed
	 * @param params
	 *            The replacement parameters which are to be passed to query
	 * @return First row from query. A Map containing key as columnNames and
	 *         value as database value.
	 * @throws FatalException
	 */
	public Map<String, Object> getQueryMap(Connection connection, String query,
			Object... params) throws FatalException {

		logger.debug("Inside getQueryMap with Connection..SQL Query is :"
				+ query);
		ResultSetHandler<Map<String, Object>> handler = new MapHandler();
		QueryRunner queryRunner = new QueryRunner(true);
		Map<String, Object> result = null;

		try {
			result = queryRunner.query(connection, query, handler, params);
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE, "sqlutil.querymap"));
		}
		return result;
	}

	/**
	 * Executes a SQL Query with or without replacement parameters.
	 * 
	 * @param query
	 *            The SQL query to be executed
	 * @param params
	 *            The SQL query to be executed
	 * @return A List containing a Map which contains key as columnNames and
	 *         value as database value.
	 * @throws FatalException
	 */
	public List<Map<String, Object>> getQueryMaps(String query,
			Object... params) throws Exception {

		// System.out.println("query="+query);
		// System.out.println("params="+params);
		logger.debug("Inside getQueryMaps ..SQL Query is :" + query);
		Connection connection = connectionFactory.getDBConnection();
		ResultSetHandler<List<Map<String, Object>>> handler = new MapListHandler();
		QueryRunner queryRunner = new QueryRunner();
		List<Map<String, Object>> result = null;
		try {
			result = queryRunner.query(connection, query, handler, params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE, "sqlutil.querymaps"));
		} finally {
			DbUtils.closeQuietly(connection);
		}

		return result;
	}

	/**
	 * Executes a SQL Query with or without replacement parameters.
	 * 
	 * @param connection
	 *            Connection object got from Datasource lookup
	 * @param query
	 *            The SQL query to be executed
	 * @param params
	 *            The SQL query to be executed
	 * @return A List containing a Map which contains key as columnNames and
	 *         value as database value.
	 * @throws FatalException
	 */
	public List<Map<String, Object>> getQueryMaps(Connection connection,
			String query, Object... params) throws FatalException {

		logger.debug("Inside getQueryMaps with Connection object..SQL Query is :"
				+ query);
		ResultSetHandler<List<Map<String, Object>>> handler = new MapListHandler();
		QueryRunner queryRunner = new QueryRunner();
		List<Map<String, Object>> result = null;
		try {
			result = queryRunner.query(connection, query, handler, params);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE, "sqlutil.querymaps"));
		}
		return result;
	}

	/**
	 * Execute an SQL INSERT, UPDATE, or DELETE query with placement parameters.
	 * 
	 * @param query
	 *            The query to be executed.
	 * @param params
	 *            The replacement parameters which are passed to the query
	 * @return Number of rows affected by the query.
	 * @throws FatalException
	 */
	public long dmlExecutor(String query, Object... params) throws Exception {

		System.out.println("paramas=" + params);
		logger.debug("Inside dmlExecutor ..SQL Query is :" + query);
		Connection conn = connectionFactory.getDBConnection();

		QueryRunner queryRunner = new QueryRunner(true);
		long result = 0;

		try {
			result = queryRunner.update(conn, query, params);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(e.getMessage(), e);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutor"));
		}
		DbUtils.commitAndCloseQuietly(conn);

		return result;
	}

	/**
	 * Execute an SQL INSERT, UPDATE, or DELETE query with placement parameters.
	 * 
	 * @param query
	 *            The query to be executed.
	 * @param params
	 *            The replacement parameters which are passed to the query
	 * @return Number of rows affected by the query.
	 * @throws FatalException
	 * 
	 * @see Method : public long dmlExecutor(String query, Object... params)
	 *      this method is same as dmlExecutor it does not write query to log
	 *      file
	 */
	public long dmlQueryExecutor(String query, Object... params)
			throws Exception {

		Connection conn = connectionFactory.getDBConnection();
		QueryRunner queryRunner = new QueryRunner(true);

		long result = 0;

		try {
			result = queryRunner.update(conn, query, params);
		} catch (SQLException sqlEx) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(sqlEx.getMessage(), sqlEx);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutor"));
		}
		DbUtils.commitAndCloseQuietly(conn);

		return result;
	}

	/**
	 * Execute an SQL INSERT, UPDATE, or DELETE query with placement parameters.
	 * 
	 * @param conn
	 *            Connection object got from Datasource lookup
	 * @param query
	 *            The query to be executed.
	 * @param params
	 *            The replacement parameters which are passed to the query
	 * @return Number of rows affected by the query.
	 * @throws FatalException
	 */
	public long dmlExecutor(Connection conn, String query, Object... params)
			throws FatalException {

		logger.debug("Inside dmlExecutor with Connection object..SQL Query is :"
				+ query);
		QueryRunner queryRunner = new QueryRunner(true);
		long result = 0;

		try {
			result = queryRunner.update(conn, query, params);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(e.getMessage(), e);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutor"));
		}
		return result;
	}

	/**
	 * Execute an SQL INSERT, UPDATE, or DELETE query with placement parameters.
	 * 
	 * @param conn
	 *            Connection object got from Datasource lookup
	 * @param query
	 *            The query to be executed.
	 * @param params
	 *            The replacement parameters which are passed to the query
	 * @return Number of rows affected by the query.
	 * @throws FatalException
	 * 
	 * @see Method : public long dmlExecutor(Connection conn, String query,
	 *      Object... params) this method is same as dmlExecutor it does not
	 *      write query to log file
	 */
	public long dmlQueryExecutor(Connection conn, String query,
			Object... params) throws FatalException {

		QueryRunner queryRunner = new QueryRunner(true);
		long result = 0;
		try {
			result = queryRunner.update(conn, query, params);
		} catch (SQLException sqlEx) {
			logger.error(sqlEx.getMessage(), sqlEx);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutor"));
		}

		return result;
	}

	/**
	 * Execute an SQL INSERT, UPDATE, or DELETE query with placement parameters.
	 * 
	 * @param query
	 *            The query to be executed.
	 * @param params
	 *            The replacement parameters which are passed to the query
	 * @param sequenceField
	 *            Name of the Oracle sequence field
	 * @return Number of rows affected by the query. Incase of insert query ,
	 *         Return sequence id of Oracle which is inserted in the Database.
	 * @throws FatalException
	 */
	public long dmlExecutor(String query, Object[] param, String sequenceField)
			throws Exception {

		logger.debug("Inside dmlExecutor in sequenceField method  ..SQL Query is :"
				+ query);
		DBQueryRunner qryRunner = new DBQueryRunner();
		Connection conn = connectionFactory.getDBConnection();
		long result = 0;
		try {
			if (query.toLowerCase().indexOf("insert") != -1)
				result = qryRunner.insert(conn, query, param, sequenceField);
			else
				result = qryRunner.updatedata(conn, query, param);
		} catch (SQLException sqlEx) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(sqlEx.getMessage(), sqlEx);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutorseq"));

		} catch (IOException ioEx) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(ioEx.getMessage(), ioEx);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutorseq"));

		} catch (Exception ex) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(ex.getMessage(), ex);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutorseq"));

		}

		DbUtils.commitAndCloseQuietly(conn);
		return result;
	}

	/**
	 * Execute an SQL INSERT, UPDATE, or DELETE query with placement parameters.
	 * 
	 * @param conn
	 *            Connection object got from Datasource lookup
	 * @param query
	 *            The query to be executed.
	 * @param params
	 *            The replacement parameters which are passed to the query
	 * @param sequenceField
	 *            Name of the Oracle sequence field
	 * @return Number of rows affected by the query. Incase of insert query ,
	 *         Return sequence id of Oracle which is inserted in the Database.
	 * @throws FatalException
	 */
	public long dmlExecutor(Connection conn, String query, Object[] param,
			String sequenceField) throws FatalException {

		logger.debug("Inside dmlExecutor in sequenceField method  with Connection object..SQL Query is :"
				+ query);
		DBQueryRunner qryRunner = new DBQueryRunner();
		long result = 0;
		try {
			if (query.toLowerCase().indexOf("insert") != -1)
				result = qryRunner.insert(conn, query, param, sequenceField);
			else
				result = qryRunner.updatedata(conn, query, param);
		} catch (SQLException sqlEx) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(sqlEx.getMessage(), sqlEx);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutorseq"));

		} catch (IOException ioEx) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(ioEx.getMessage(), ioEx);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutorseq"));

		} catch (Exception ex) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(ex.getMessage(), ex);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutorseq"));

		}

		return result;
	}

	/**
	 * Batch execute an SQL INSERT, UPDATE, or DELETE query with placement
	 * parameters. The caller needs to pass the Connection Object. The Caller
	 * Also need to make sure that Connection is properly closed and committed.
	 * 
	 * @param conn
	 *            Connection object got from Datasource lookup
	 * @param sql
	 *            The sql query to be executed.
	 * @param params
	 *            List of Object arrays parameters to be set in query.
	 * @return The number of rows updated per statement
	 * @throws FatalException
	 */
	public int[] dmlBatchExecuter(Connection conn, String sql,
			List<Object[]> params) throws FatalException {

		DBQueryRunner queryRunner = new DBQueryRunner();
		int[] result;
		try {
			result = queryRunner.executeBatch(conn, sql, params);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(e.getMessage(), e);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutor"));
		} catch (IOException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(e.getMessage(), e);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutor"));
		}
		return result;
	}

	/**
	 * Batch execute an SQL INSERT, UPDATE, or DELETE query with placement
	 * parameters.
	 * 
	 * @param sql
	 *            The sql query to be executed.
	 * @param params
	 *            List of Object arrays parameters to be set in query.
	 * @return The number of rows updated per statement
	 * @throws FatalException
	 */
	public int[] dmlBatchExecuter(String sql, List<Object[]> params)
			throws Exception {

		DBQueryRunner queryRunner = new DBQueryRunner();
		Connection connection = connectionFactory.getDBConnection();

		int[] result;

		try {
			result = queryRunner.executeBatch(connection, sql, params);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(connection);
			logger.error(e.getMessage(), e);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutor"));
		} catch (IOException e) {
			DbUtils.rollbackAndCloseQuietly(connection);
			logger.error(e.getMessage(), e);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.dmlexecutor"));
		}
		DbUtils.commitAndCloseQuietly(connection);
		return result;
	}

	/**
	 * 
	 * This method is used to insert bean into database through column-field
	 * mapping bean.
	 * 
	 * @param bean
	 *            Model bean
	 * @return insert query and sequenceField is not null then returns newly
	 *         returns column value of inserted row. insert query and
	 *         sequenceField null then returns zero.
	 * @throws FatalException
	 * 
	 */
	public long insert(Catalog bean) throws Exception {

		long result = 0;
		if (bean != null) {

			Field[] fields = bean.getClass().getDeclaredFields();

			StringBuffer query = new StringBuffer();
			StringBuffer value = new StringBuffer();
			HashMap fieldMappings = null;
			List<Object> paramList = new ArrayList<Object>();
			try {
				fieldMappings = (HashMap) bean.getClass()
						.getMethod("getFieldMappings", null).invoke(bean, null);
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			}

			int count;
			Object[] param = new Object[fields.length];
			query.append("Insert into ");
			query.append(fieldMappings.get(DbConstant.BEAN_TABLENAME));
			query.append('(');
			try {
				boolean first = true;

				if (fieldMappings.get(DbConstant.SEQUENCE) != null
						&& fieldMappings.get(DbConstant.SEQUENCE_FIELD) != null) {
					query.append((String) fieldMappings
							.get(DbConstant.SEQUENCE_FIELD));
					value.append(fieldMappings.get(DbConstant.SEQUENCE));
					first = false;
				}

				for (count = 0; count < fields.length; count++) {

					if (fieldMappings.containsKey(fields[count].getName())
							&& (fieldMappings.get(DbConstant.SEQUENCE_FIELD) == null || !fields[count]
									.getName()
									.equals((String) fieldMappings
											.get(DbConstant.SEQUENCE_FIELD)))) {

						param[count] = bean
								.getClass()
								.getDeclaredMethod(
										"get"
												+ fields[count].getName()
														.substring(0, 1)
														.toUpperCase()
												+ fields[count].getName()
														.substring(1), null)
								.invoke(bean, null);

						if (param[count] != null) {
							if (((param[count] instanceof Integer)
									|| (param[count] instanceof Float)
									|| (param[count] instanceof Double)
									|| (param[count] instanceof Long) || ((param[count] instanceof Byte)))
									&& param[count].toString().equals("0"))
								continue;

							if (!first && count <= fields.length - 1) {
								query.append(',');
								value.append(',');
							} else
								first = false;
							query.append((String) fieldMappings
									.get(fields[count].getName()));
							if (param[count] instanceof java.sql.Date) {
								value.append("to_date(?,'yyyy-MM-dd')");
								paramList.add(param[count].toString());
							} else if (param[count] instanceof InputStream) {
								value.append('?');
								paramList.add(param[count]);
							} else {
								value.append('?');
								paramList.add(param[count].toString());
							}

						}
					}
				}

				query.append(" ) ");
				query.append(" values(");
				value.append(" )");
				query.append(value);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();

			} catch (SecurityException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();

			} catch (InvocationTargetException e) {

				e.printStackTrace();

			} catch (NoSuchMethodException e) {

				e.printStackTrace();

			}

			Object[] strParam = new Object[paramList.size()];
			for (count = 0; count < paramList.size(); count++) {
				strParam[count] = paramList.get(count);
			}

			result = dmlExecutor(query.toString(), strParam,
					(String) fieldMappings.get(DbConstant.SEQUENCE_FIELD));
		}

		return result;
	}

	/**
	 * 
	 * This method is used to update record into database through column-field
	 * mapping bean.
	 * 
	 * @param setBean
	 *            Model setter bean for 'set' criteria of the update query.
	 * @param criteriaBean
	 *            Array of criteria bean for 'where' criteria of the update
	 *            query. default operation of different fields is 'and'
	 *            operation. to change bean's default operation call
	 *            setOperation() method
	 * @param beanOprn
	 *            Array of beanOprn describes operation between different
	 *            Catalog beans in parameter 'criteriaBean' for example :
	 *            'and'/'or' operation
	 * @return update query returns count of affected rows.
	 * @throws FatalException
	 * 
	 */
	public long update(Catalog setBean, Catalog[] criteriaBean,
			String[] beanOprn) throws Exception {
		long result = 0;

		List<Object> paramSetList = new ArrayList<Object>();
		List<String> paramCriteriaList = new ArrayList<String>();
		Field[] setFields = setBean.getClass().getDeclaredFields();
		StringBuffer query = new StringBuffer();
		StringBuffer criteria = new StringBuffer();
		HashMap<String, String> setFieldMappings = null;
		HashMap<String, String>[] criteriaFieldMappings = null;

		try {
			setFieldMappings = (HashMap<String, String>) setBean.getClass()
					.getMethod("getFieldMappings", null).invoke(setBean, null);

			criteriaFieldMappings = new HashMap[criteriaBean.length];
			for (int count = 0; count < criteriaBean.length; count++)
				criteriaFieldMappings[count] = (HashMap<String, String>) criteriaBean[count]
						.getClass().getMethod("getFieldMappings", null)
						.invoke(criteriaBean[count], null);

		} catch (Exception e) {

			e.printStackTrace();

		}

		int count;
		Object[] setParam = new Object[setFields.length];
		Object[][] criteriaParam = new Object[criteriaBean.length][setFields.length];
		query.append("Update ");
		query.append(setFieldMappings.get(DbConstant.BEAN_TABLENAME));

		boolean setFirst = true;
		boolean criteriaFirst = true;
		for (count = 0; count < setFields.length; count++) {
			if (setFieldMappings.containsKey(setFields[count].getName())) {
				try {
					setParam[count] = setBean
							.getClass()
							.getDeclaredMethod(
									"get"
											+ setFields[count].getName()
													.substring(0, 1)
													.toUpperCase()
											+ setFields[count].getName()
													.substring(1), null)
							.invoke(setBean, null);
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				}

				if (setParam[count] != null
						&& !(((setParam[count] instanceof Integer)
								|| (setParam[count] instanceof Float)
								|| (setParam[count] instanceof Double)
								|| (setParam[count] instanceof Long) || ((setParam[count] instanceof Byte))) && setParam[count]
								.toString().equals("0"))) {

					if (setFirst) {
						setFirst = false;
						query.append(" set "
								+ setFieldMappings.get(setFields[count]
										.getName()));
					} else
						query.append(" , "
								+ setFieldMappings.get(setFields[count]
										.getName()));

					if (setParam[count] instanceof java.sql.Date) {
						query.append(" = to_date(?,'yyyy-MM-dd') ");
						paramSetList.add(setParam[count].toString());
					} else if (setParam[count] instanceof InputStream) {
						query.append(" = ? ");
						paramSetList.add(setParam[count]);
					} else {
						query.append(" = ? ");
						paramSetList.add(setParam[count].toString());
					}
				}

			}
			if (count < criteriaParam.length) {
				boolean firstBracket = true;
				for (int innerCount = 0; innerCount < setFields.length; innerCount++) {
					if (setFieldMappings.containsKey(setFields[innerCount]
							.getName())) {
						try {
							criteriaParam[count][innerCount] = criteriaBean[count]
									.getClass()
									.getDeclaredMethod(
											"get"
													+ setFields[innerCount]
															.getName()
															.substring(0, 1)
															.toUpperCase()
													+ setFields[innerCount]
															.getName()
															.substring(1), null)
									.invoke(criteriaBean[count], null);
						} catch (IllegalArgumentException e) {

							e.printStackTrace();
						} catch (SecurityException e) {

							e.printStackTrace();
						} catch (IllegalAccessException e) {

							e.printStackTrace();
						} catch (InvocationTargetException e) {

							e.printStackTrace();
						} catch (NoSuchMethodException e) {

							e.printStackTrace();
						}
						if (criteriaParam[count][innerCount] != null
								&& !criteriaParam[count][innerCount].toString()
										.equals("0")) {

							if (((criteriaParam[count][innerCount] instanceof Integer)
									|| (criteriaParam[count][innerCount] instanceof Float)
									|| (criteriaParam[count][innerCount] instanceof Double)
									|| (criteriaParam[count][innerCount] instanceof Long) || ((criteriaParam[count][innerCount] instanceof Byte)))
									&& criteriaParam[count][innerCount]
											.toString().equals("0"))
								continue;

							if (criteriaFirst) {
								criteriaFirst = false;
								criteria.append(" where ("
										+ criteriaFieldMappings[count]
												.get(setFields[innerCount]
														.getName()));
							} else if (count > 0 && firstBracket) {
								criteria.append(" "
										+ beanOprn[count - 1]
										+ " ("
										+ criteriaFieldMappings[count]
												.get(setFields[innerCount]
														.getName()));
								firstBracket = false;
							} else
								criteria.append(" "
										+ criteriaBean[count].getOperation()
										+ " "
										+ criteriaFieldMappings[count]
												.get(setFields[innerCount]
														.getName()));

							if (criteriaParam[count][innerCount] instanceof java.sql.Date) {
								criteria.append(" = to_date(?,'yyyy-MM-dd') ");
							} else
								criteria.append(" = ? ");
							paramCriteriaList
									.add(criteriaParam[count][innerCount]
											.toString());

						}
					}
				}
				criteria.append(" ) ");
			}

		}

		query.append(criteria);

		Object[] strParam = new Object[paramSetList.size()
				+ paramCriteriaList.size()];
		for (count = 0; count < paramSetList.size(); count++) {
			strParam[count] = paramSetList.get(count);
		}
		for (int criteriaCount = 0; criteriaCount < paramCriteriaList.size(); criteriaCount++) {
			strParam[count + criteriaCount] = paramCriteriaList
					.get(criteriaCount);
		}
		result = dmlExecutor(query.toString(), strParam, null);

		return result;
	}

	/**
	 * This method is used to Call the procedure.
	 * 
	 * @param conn
	 *            Connection object retrieved from Datasource lookup
	 * @param procedurename
	 *            the procedurename
	 * @param inparam
	 *            the inparam
	 * @param outparamtypes
	 *            the outparamtypes
	 * @param returntype
	 *            the returntype
	 * @return the object[]
	 * @throws FatalException
	 *             the fatal exception
	 */
	public Object[] callProcedure(Connection conn, String procedurename,
			Object[] inparam, int[] outparamtypes, String returntype)
			throws FatalException {

		logger.debug("Method : callProcedure");
		logger.debug("Procedure Name : " + procedurename);
		if (inparam != null) {
			for (int count = 0; count < inparam.length; count++)
				logger.debug("In Parameter" + count + " : " + inparam[count]);
		}
		if (outparamtypes != null) {
			for (int count = 0; count < outparamtypes.length; count++)
				logger.debug("Out Parameter Types" + count + " : "
						+ outparamtypes[count]);
		}
		Object[] obj = null;
		ArrayList<Map<String, Object>> lArrFinal = null;
		ResultSet lRs = null;
		int inlength = 0;
		int outlength = 0;
		int flag[] = null;
		inlength = inparam.length;
		if (outparamtypes != null && outparamtypes.length > 0) {
			outlength = outparamtypes.length;
			flag = new int[outlength];
		}
		CallableStatement cs = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append("call " + procedurename + "(");
		for (int count = 1; count <= (inlength + outlength); count++) {
			buffer.append("?");

			if (count < (inlength + outlength)) {
				buffer.append(",");
			}
		}
		buffer.append(")");

		try {

			cs = ((Connection) conn).prepareCall(buffer.toString());

			for (int count = 1; count <= inlength; count++) {
				cs.setObject(count, inparam[count - 1]);
			}
			if (outparamtypes != null) {
				if (outlength == 0) {
					flag = new int[outlength + 1];
					flag[0] = 1;
				}
				// This loop will only execute when out put is there
				for (int count = inlength + 1; count <= (inlength + outlength); count++) {
					cs.registerOutParameter(count, outparamtypes[count
							- inlength - 1]);
					flag[count - inlength - 1] = 0;
				}
			}

			cs.execute();

			if (outparamtypes != null) {
				if (outlength == 0)
					obj = new Object[outlength + 1];
				else
					obj = new Object[outlength];

				if (outlength == 0)
					outlength = 1;

				for (int count = inlength + 1; count <= (inlength + outlength); count++) {
					if (flag[0] == 1) {
						lArrFinal = new ArrayList<Map<String, Object>>();
						lRs = (ResultSet) cs.getResultSet();
						ResultSetMetaData rsMetaData = lRs.getMetaData();
						int numberOfColumns = rsMetaData.getColumnCount();

						if (returntype == null) {
							lRs.next();
							for (int i = 1; i < numberOfColumns + 1; i++) {
								String columnName = rsMetaData.getColumnName(i);
								obj[count - inlength - 1] = lRs
										.getObject(columnName);
							}
						}

						if ("Map".equalsIgnoreCase(returntype)) {
							while (lRs.next()) {

								java.util.Map<String, Object> Hmvalue = new HashMap<String, Object>();
								for (int i = 1; i < numberOfColumns + 1; i++) {
									String columnName = rsMetaData
											.getColumnName(i);
									Hmvalue.put(columnName,
											lRs.getObject(columnName));
								}
								lArrFinal.add(Hmvalue);

								obj[count - inlength - 1] = lArrFinal;
							}
						}

					} else {
						obj[count - inlength - 1] = cs.getObject(count);
					}
				}
			}

			// DbUtils.commitAndCloseQuietly(conn); - Do not explict commit. DAO
			// Layer will commit and close the connection object
			return obj;
		} catch (Exception exception) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(exception.getMessage(), exception);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.callprocedure"));
		} finally {
			if (lRs != null) {
				try {
					lRs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					throw new DaoException(I18NUtils.getMessageFromBundle(
							ApplicationConstants.DB_MESSAGES_FILE,
							"sqlutil.callprocedure"));
				}
				lRs = null;
			}
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					throw new DaoException(I18NUtils.getMessageFromBundle(
							ApplicationConstants.DB_MESSAGES_FILE,
							"sqlutil.callprocedure"));
				}
				cs = null;
			}
		}

	}

	/**
	 * This method is used to Call the procedure.
	 * 
	 * @param procedurename
	 *            the procedurename
	 * @param inparam
	 *            the inparam
	 * @param outparamtypes
	 *            the outparamtypes
	 * @param returntype
	 *            the returntype
	 * @return the object[]
	 * @throws FatalException
	 *             the fatal exception
	 */
	public Object[] callProcedure(String procedurename, Object[] inparam,
			int[] outparamtypes, String returntype) throws Exception {
		logger.debug("Method : callProcedure");
		logger.debug("Procedure Name : " + procedurename);
		Connection conn = connectionFactory.getDBConnection();
		if (inparam != null) {
			for (int count = 0; count < inparam.length; count++)
				logger.debug("In Parameter" + count + " : " + inparam[count]);
		}
		if (outparamtypes != null) {
			for (int count = 0; count < outparamtypes.length; count++)
				logger.debug("Out Parameter Types" + count + " : "
						+ outparamtypes[count]);
		}
		Object[] obj = null;
		ArrayList<Map<String, Object>> lArrFinal = null;
		ResultSet lRs = null;
		int inlength = 0;
		int outlength = 0;
		int flag[] = null;
		inlength = inparam.length;
		if (outparamtypes != null && outparamtypes.length > 0) {
			outlength = outparamtypes.length;
			flag = new int[outlength];
		}
		CallableStatement cs = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append("call " + procedurename + "(");
		for (int count = 1; count <= (inlength + outlength); count++) {
			buffer.append("?");

			if (count < (inlength + outlength)) {
				buffer.append(",");
			}
		}
		buffer.append(")");

		try {

			cs = ((Connection) conn).prepareCall(buffer.toString());

			for (int count = 1; count <= inlength; count++) {
				cs.setObject(count, inparam[count - 1]);
			}
			if (outparamtypes != null) {
				if (outlength == 0) {
					flag = new int[outlength + 1];
					flag[0] = 1;
				}
				// This loop will only execute when out put is there
				for (int count = inlength + 1; count <= (inlength + outlength); count++) {
					cs.registerOutParameter(count, outparamtypes[count
							- inlength - 1]);
					flag[count - inlength - 1] = 0;
				}
			}

			cs.execute();

			if (outparamtypes != null) {
				if (outlength == 0)
					obj = new Object[outlength + 1];
				else
					obj = new Object[outlength];

				if (outlength == 0)
					outlength = 1;

				for (int count = inlength + 1; count <= (inlength + outlength); count++) {
					if (flag[0] == 1) {
						lArrFinal = new ArrayList<Map<String, Object>>();
						lRs = (ResultSet) cs.getResultSet();
						ResultSetMetaData rsMetaData = lRs.getMetaData();
						int numberOfColumns = rsMetaData.getColumnCount();

						if (returntype == null) {
							lRs.next();
							for (int i = 1; i < numberOfColumns + 1; i++) {
								String columnName = rsMetaData.getColumnName(i);
								obj[count - inlength - 1] = lRs
										.getObject(columnName);
							}
						}

						if ("Map".equalsIgnoreCase(returntype)) {
							while (lRs.next()) {

								java.util.Map<String, Object> Hmvalue = new HashMap<String, Object>();
								for (int i = 1; i < numberOfColumns + 1; i++) {
									String columnName = rsMetaData
											.getColumnName(i);
									Hmvalue.put(columnName,
											lRs.getObject(columnName));
								}
								lArrFinal.add(Hmvalue);

								obj[count - inlength - 1] = lArrFinal;
							}
						}

					} else {
						obj[count - inlength - 1] = cs.getObject(count);
					}
				}
			}

			return obj;
		} catch (Exception exception) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(exception.getMessage(), exception);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.callprocedure"));
		} finally {
			if (lRs != null) {
				try {
					lRs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					throw new DaoException(I18NUtils.getMessageFromBundle(
							ApplicationConstants.DB_MESSAGES_FILE,
							"sqlutil.callprocedure"));
				}
				lRs = null;
			}
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					throw new DaoException(I18NUtils.getMessageFromBundle(
							ApplicationConstants.DB_MESSAGES_FILE,
							"sqlutil.callprocedure"));
				}
				cs = null;
			}
			DbUtils.commitAndCloseQuietly(conn);
		}

	}

	/**
	 * This method is used to Call the procedure which contains PL/SQL Types as
	 * Input Parameter [String/Number Array as IN Parameter].
	 * 
	 * @param procedurename
	 *            the procedurename
	 * @param inparam
	 *            the inparam
	 * @param outparamtypes
	 *            the outparamtypes
	 * @param returntype
	 *            the returntype
	 * @return the object[]
	 * @throws FatalException
	 *             the fatal exception
	 */
	public Object[] callProcedureWithType(String procedurename,
			Object[] inparam, int[] outparamtypes, String returntype)
			throws Exception {
		logger.debug("Method : callProcedure");
		logger.debug("Procedure Name : " + procedurename);
		Connection conn = connectionFactory.getDBConnection();
		if (inparam != null) {
			for (int count = 0; count < inparam.length; count++)
				logger.debug("In Parameter" + count + " : " + inparam[count]);
		}
		if (outparamtypes != null) {
			for (int count = 0; count < outparamtypes.length; count++)
				logger.debug("Out Parameter Types" + count + " : "
						+ outparamtypes[count]);
		}
		Object[] obj = null;
		ArrayList<Map<String, Object>> lArrFinal = null;
		ResultSet lRs = null;
		int inlength = 0;
		int outlength = 0;
		int flag[] = null;
		inlength = inparam.length;
		if (outparamtypes != null && outparamtypes.length > 0) {
			outlength = outparamtypes.length;
			flag = new int[outlength];
		}
		OracleCallableStatement cs = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append(" " + procedurename + "(");
		for (int count = 1; count <= (inlength + outlength); count++) {
			buffer.append("?");

			if (count < (inlength + outlength)) {
				buffer.append(",");
			}
		}
		buffer.append(");");

		try {

			cs = (OracleCallableStatement) conn.prepareCall(" begin"
					+ buffer.toString() + " end;");

			for (int count = 1; count <= inlength; count++) {
				Object object = inparam[count - 1];
				Class<? extends Object> cClass = object.getClass();
				if (cClass.isArray()) {
					Class dataType = cClass.getComponentType();
					if (dataType.toString().equals("class java.lang.String")) {
						String data[] = (String[]) object;
						cs.setPlsqlIndexTable(count, data, data.length,
								data.length, OracleTypes.VARCHAR,
								DbConstant.MAX_LENGTH_ARRAY_VALUE);
					} else {
						int data[] = (int[]) object;
						cs.setPlsqlIndexTable(count, data, data.length,
								data.length, OracleTypes.NUMBER, 0);
					}
				} else {
					cs.setObject(count, object);
				}
			}
			if (outparamtypes != null) {
				if (outlength == 0) {
					flag = new int[outlength + 1];
					flag[0] = 1;
				}
				// This loop will only execute when out put is there
				for (int count = inlength + 1; count <= (inlength + outlength); count++) {
					cs.registerOutParameter(count, outparamtypes[count
							- inlength - 1]);
					flag[count - inlength - 1] = 0;
				}
			}

			cs.execute();

			if (outparamtypes != null) {
				if (outlength == 0)
					obj = new Object[outlength + 1];
				else
					obj = new Object[outlength];

				if (outlength == 0)
					outlength = 1;

				for (int count = inlength + 1; count <= (inlength + outlength); count++) {
					if (flag[0] == 1) {
						lArrFinal = new ArrayList<Map<String, Object>>();
						lRs = (ResultSet) cs.getResultSet();
						ResultSetMetaData rsMetaData = lRs.getMetaData();
						int numberOfColumns = rsMetaData.getColumnCount();

						if (returntype == null) {
							lRs.next();
							for (int i = 1; i < numberOfColumns + 1; i++) {
								String columnName = rsMetaData.getColumnName(i);
								obj[count - inlength - 1] = lRs
										.getObject(columnName);
							}
						}

						if ("Map".equalsIgnoreCase(returntype)) {
							while (lRs.next()) {

								java.util.Map<String, Object> Hmvalue = new HashMap<String, Object>();
								for (int i = 1; i < numberOfColumns + 1; i++) {
									String columnName = rsMetaData
											.getColumnName(i);
									Hmvalue.put(columnName,
											lRs.getObject(columnName));
								}
								lArrFinal.add(Hmvalue);

								obj[count - inlength - 1] = lArrFinal;
							}
						}

					} else {
						obj[count - inlength - 1] = cs.getObject(count);
					}
				}
			}

			return obj;
		} catch (Exception exception) {
			exception.printStackTrace();
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(exception.getMessage(), exception);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.callprocedure"));
		} finally {
			if (lRs != null) {
				try {
					lRs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					throw new DaoException(I18NUtils.getMessageFromBundle(
							ApplicationConstants.DB_MESSAGES_FILE,
							"sqlutil.callprocedure"));
				}
				lRs = null;
			}
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					throw new DaoException(I18NUtils.getMessageFromBundle(
							ApplicationConstants.DB_MESSAGES_FILE,
							"sqlutil.callprocedure"));
				}
				cs = null;
			}
			DbUtils.commitAndCloseQuietly(conn);
		}

	}

	/**
	 * This method is used to Call the procedure which contains PL/SQL Types as
	 * Input Parameter [String/Number Array as IN Parameter].
	 * 
	 * @param conn
	 *            Connection object retrieved from Datasource lookup
	 * @param procedurename
	 *            the procedurename
	 * @param inparam
	 *            the inparam
	 * @param outparamtypes
	 *            the outparamtypes
	 * @param returntype
	 *            the returntype
	 * @return the object[]
	 * @throws FatalException
	 *             the fatal exception
	 */
	public Object[] callProcedureWithType(Connection conn,
			String procedurename, Object[] inparam, int[] outparamtypes,
			String returntype) throws FatalException {
		logger.debug("Method : callProcedure");
		logger.debug("Procedure Name : " + procedurename);
		if (inparam != null) {
			for (int count = 0; count < inparam.length; count++)
				logger.debug("In Parameter" + count + " : " + inparam[count]);
		}
		if (outparamtypes != null) {
			for (int count = 0; count < outparamtypes.length; count++)
				logger.debug("Out Parameter Types" + count + " : "
						+ outparamtypes[count]);
		}
		Object[] obj = null;
		ArrayList<Map<String, Object>> lArrFinal = null;
		ResultSet lRs = null;
		int inlength = 0;
		int outlength = 0;
		int flag[] = null;
		inlength = inparam.length;
		if (outparamtypes != null && outparamtypes.length > 0) {
			outlength = outparamtypes.length;
			flag = new int[outlength];
		}
		OracleCallableStatement cs = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append(" " + procedurename + "(");
		for (int count = 1; count <= (inlength + outlength); count++) {
			buffer.append("?");

			if (count < (inlength + outlength)) {
				buffer.append(",");
			}
		}
		buffer.append(");");

		try {

			cs = (OracleCallableStatement) conn.prepareCall(" begin"
					+ buffer.toString() + " end;");

			for (int count = 1; count <= inlength; count++) {
				Object object = inparam[count - 1];
				Class<? extends Object> cClass = object.getClass();
				if (cClass.isArray()) {
					Class dataType = cClass.getComponentType();
					if (dataType.toString().equals("class java.lang.String")) {
						String data[] = (String[]) object;
						cs.setPlsqlIndexTable(count, data, data.length,
								data.length, OracleTypes.VARCHAR,
								DbConstant.MAX_LENGTH_ARRAY_VALUE);
					} else {
						int data[] = (int[]) object;
						cs.setPlsqlIndexTable(count, data, data.length,
								data.length, OracleTypes.NUMBER, 0);
					}
				} else {
					cs.setObject(count, object);
				}
			}
			if (outparamtypes != null) {
				if (outlength == 0) {
					flag = new int[outlength + 1];
					flag[0] = 1;
				}
				// This loop will only execute when out put is there
				for (int count = inlength + 1; count <= (inlength + outlength); count++) {
					cs.registerOutParameter(count, outparamtypes[count
							- inlength - 1]);
					flag[count - inlength - 1] = 0;
				}
			}

			cs.execute();

			if (outparamtypes != null) {
				if (outlength == 0)
					obj = new Object[outlength + 1];
				else
					obj = new Object[outlength];

				if (outlength == 0)
					outlength = 1;

				for (int count = inlength + 1; count <= (inlength + outlength); count++) {
					if (flag[0] == 1) {
						lArrFinal = new ArrayList<Map<String, Object>>();
						lRs = (ResultSet) cs.getResultSet();
						ResultSetMetaData rsMetaData = lRs.getMetaData();
						int numberOfColumns = rsMetaData.getColumnCount();

						if (returntype == null) {
							lRs.next();
							for (int i = 1; i < numberOfColumns + 1; i++) {
								String columnName = rsMetaData.getColumnName(i);
								obj[count - inlength - 1] = lRs
										.getObject(columnName);
							}
						}

						if ("Map".equalsIgnoreCase(returntype)) {
							while (lRs.next()) {

								java.util.Map<String, Object> Hmvalue = new HashMap<String, Object>();
								for (int i = 1; i < numberOfColumns + 1; i++) {
									String columnName = rsMetaData
											.getColumnName(i);
									Hmvalue.put(columnName,
											lRs.getObject(columnName));
								}
								lArrFinal.add(Hmvalue);

								obj[count - inlength - 1] = lArrFinal;
							}
						}

					} else {
						obj[count - inlength - 1] = cs.getObject(count);
					}
				}
			}

			return obj;
		} catch (Exception exception) {
			exception.printStackTrace();
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(exception.getMessage(), exception);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.callprocedure"));
		} finally {
			if (lRs != null) {
				try {
					lRs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					throw new DaoException(I18NUtils.getMessageFromBundle(
							ApplicationConstants.DB_MESSAGES_FILE,
							"sqlutil.callprocedure"));
				}
				lRs = null;
			}
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					throw new DaoException(I18NUtils.getMessageFromBundle(
							ApplicationConstants.DB_MESSAGES_FILE,
							"sqlutil.callprocedure"));
				}
				cs = null;
			}
			// DbUtils.commitAndCloseQuietly(conn);
		}

	}

	/**
	 * This method is used to Call the procedure which expects Object Type As
	 * output parameter.
	 * 
	 * @param procedurename
	 *            the procedurename
	 * @param inparam
	 *            the inparam
	 * @param outparamtypes
	 *            the outparamtypes
	 * @return the object[]
	 * @throws FatalException
	 *             the fatal exception
	 */
	public Map<String, Object> callProcedureWithObjectType(Connection conn,
			String procedurename, Object[] inparam,
			Map<String, String> classNameMap, Map<Integer, String> typeNameMap,
			int[] outparamtypes) throws FatalException {
		logger.debug("Method : callProcedure");
		logger.debug("Procedure Name : " + procedurename);
		// Connection conn = connectionFactory.getCommonDataSourceConnection();
		if (inparam != null) {
			for (int count = 0; count < inparam.length; count++)
				logger.debug("In Parameter" + count + " : " + inparam[count]);
		}
		if (outparamtypes != null) {
			for (int count = 0; count < outparamtypes.length; count++)
				logger.debug("Out Parameter Types" + count + " : "
						+ outparamtypes[count]);
		}
		Map<String, Object> obj = new HashMap<String, Object>();
		String singleClassNameKey = "";
		int inlength = 0;
		int outlength = 0;
		int flag[] = null;
		inlength = inparam.length;
		if (outparamtypes != null && outparamtypes.length > 0) {
			outlength = outparamtypes.length;
			flag = new int[outlength];
		}
		CallableStatement cs = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append("call " + procedurename + "(");
		for (int count = 1; count <= (inlength + outlength); count++) {
			buffer.append("?");

			if (count < (inlength + outlength)) {
				buffer.append(",");
			}
		}
		buffer.append(")");

		try {

			Map<String, Class<?>> map = conn.getTypeMap();
			Iterator<Entry<String, String>> it = classNameMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> pairs = it.next();
				singleClassNameKey = pairs.getKey();
				map.put(pairs.getKey(), Class.forName(pairs.getValue()));
			}
			conn.setTypeMap(map);

			cs = ((Connection) conn).prepareCall(buffer.toString());

			for (int count = 1; count <= inlength; count++) {
				cs.setObject(count, inparam[count - 1]);
			}
			if (outparamtypes != null) {
				if (outlength == 0) {
					flag = new int[outlength + 1];
					flag[0] = 1;
				}
				// This loop will only execute when out put is there
				for (int count = inlength + 1; count <= (inlength + outlength); count++) {
					if (typeNameMap.containsKey(count)) {
						String typeName = typeNameMap.get(count).toString();
						cs.registerOutParameter(count, outparamtypes[count
								- inlength - 1], typeName);
					} else {
						cs.registerOutParameter(count, outparamtypes[count
								- inlength - 1]);
					}
					flag[count - inlength - 1] = 0;
				}
			}

			cs.execute();
			java.sql.Array objectArray = cs.getArray(inlength + outlength);
			ResultSet rs = objectArray.getResultSet();
			List<Object> arrayOfObjects = null;
			while (rs.next()) {
				if (rs.getObject(2) instanceof oracle.sql.STRUCT) {
					oracle.sql.STRUCT objectStruct = (oracle.sql.STRUCT) rs
							.getObject(2);
					StructDescriptor desc = objectStruct.getDescriptor();
					Object[] objectValues = objectStruct.getAttributes();
					for (int i = 0; i < objectValues.length; i++) {
						if (objectValues[i] instanceof oracle.sql.ARRAY) {
							oracle.sql.ARRAY upperARR = (ARRAY) objectValues[i];
							obj.put(desc.getMetaData().getColumnName(i + 1),
									(Object[]) upperARR.getArray());
						} else {
							obj.put(desc.getMetaData().getColumnName(i + 1),
									objectValues[i]);
						}
					}
				} else {
					if (arrayOfObjects == null)
						arrayOfObjects = new ArrayList<Object>();
					arrayOfObjects.add(rs.getObject(2));
				}
			}
			if (arrayOfObjects != null) {
				obj.put(singleClassNameKey, arrayOfObjects);
			}
			rs.close();
			return obj;
		} catch (Exception exception) {
			DbUtils.rollbackAndCloseQuietly(conn);
			logger.error(exception.getMessage(), exception);
			throw new DaoException(I18NUtils.getMessageFromBundle(
					ApplicationConstants.DB_MESSAGES_FILE,
					"sqlutil.callprocedure"));
		} finally {
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
					throw new DaoException(I18NUtils.getMessageFromBundle(
							ApplicationConstants.DB_MESSAGES_FILE,
							"sqlutil.callprocedure"));
				}
				cs = null;
			}
			// DbUtils.commitAndCloseQuietly(conn);
		}

	}

	/**
	 * Close a <code>Connection</code>
	 * 
	 * @param connection
	 *            Connection to close.
	 */
	public void closeQuietly(Connection connection) {
		DbUtils.closeQuietly(connection);
	}

	/**
	 * Performs a rollback on the <code>Connection</code> then closes it, avoid
	 * closing if null and hide any SQLExceptions that occur.
	 * 
	 * @param connection
	 *            Connection to rollback. A null value is legal.
	 */
	public void rollbackAndCloseQuietly(Connection connection) {
		DbUtils.rollbackAndCloseQuietly(connection);
	}

	/**
	 * Commits a <code>Connection</code> then closes it, avoid closing if null
	 * and hide any SQLExceptions that occur.
	 * 
	 * @param connection
	 *            Connection to close.
	 */

	public void commitAndCloseQuietly(Connection connection) {
		DbUtils.commitAndCloseQuietly(connection);
	}

}
