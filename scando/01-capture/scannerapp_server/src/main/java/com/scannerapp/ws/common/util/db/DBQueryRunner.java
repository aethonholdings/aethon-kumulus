package com.scannerapp.ws.common.util.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

/**
 * 
 * @author Niraj Salot <niraj.salot@spec-india.com>
 * @since Apr 27, 2011
 * 
 */
public final class DBQueryRunner extends QueryRunner {

	/**
	 * This method is used to execute insert the query. It uses sequenceField
	 * parameter to insert using oracle's sequenceField on insert it will return
	 * the sequenceID, if no sequenceField is used it will return number of rows
	 * inserted.
	 * 
	 * @param connection
	 *            Connection to the database
	 * @param query
	 *            Query to be executed.
	 * @param params
	 *            Object[] of parameters to be set for the query
	 * @param sequenceField
	 *            Field name in database which is sequenceField of
	 * @return Returns sequence number when sequenceField is used, otherwise no
	 *         of rows inserted
	 * @throws SQLException
	 * @throws IOException
	 */
	public long insert(Connection connection, String query, Object[] params,
			String sequenceField) throws SQLException, IOException {

		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		long autoId = 0;

		try {
			if (sequenceField != null)
				preparedStatement = this.prepareStatementForSequenceId(
						connection, query, sequenceField);
			else
				preparedStatement = this.prepareStatement(connection, query);
			this.setStatement(preparedStatement, params);
			preparedStatement.executeUpdate();

			if (sequenceField != null) {
				generatedKeys = preparedStatement.getGeneratedKeys();
				if (generatedKeys != null && generatedKeys.next())
					autoId = Long.parseLong(generatedKeys.getString(1));

			}
		} catch (SQLException e) {
			this.rethrow(e, query, params);

		} finally {
			if (sequenceField != null) {
				close(generatedKeys);
			}
			close(preparedStatement);
		}

		return autoId;
	}

	/**
	 * This method is used to execute update statement.
	 * 
	 * @param connection
	 *            Connection to the database
	 * @param query
	 *            Query to be executed
	 * @param params
	 *            Object[] containing parameters to be set for the query
	 * @return returns number rows updated
	 * @throws SQLException
	 * @throws IOException
	 */
	public long updatedata(Connection connection, String query, Object[] params)
			throws SQLException, IOException {

		PreparedStatement stmt = null;
		long rows = 0;

		try {
			stmt = this.prepareStatement(connection, query);
			this.setStatement(stmt, params);
			rows = stmt.executeUpdate();

		} catch (SQLException e) {
			this.rethrow(e, query, params);

		} finally {
			close(stmt);
		}

		return rows;
	}

	/**
	 * This method creates the preparedStatement which is required to get
	 * sequenceId after the query is executed.
	 * 
	 * @param connection
	 * @param query
	 * @param sequenceField
	 * @return
	 * @throws SQLException
	 */
	protected PreparedStatement prepareStatementForSequenceId(
			Connection connection, String query, String sequenceField)
			throws SQLException {

		return connection.prepareStatement(query,
				new String[] { sequenceField });
	}

	/**
	 * This method sets the parameters to the query.
	 * 
	 * @param preparedStatement
	 *            preparedStatement to which parameter needs to be attached.
	 * @param param
	 *            Object[] which contains the parameters to be mapped to the
	 *            statement.
	 * @throws SQLException
	 * @throws IOException
	 */
	protected void setStatement(PreparedStatement preparedStatement,
			Object[] param) throws SQLException, IOException {

		if (param == null) {
			return;
		}

		for (int count = 0; count < param.length; count++) {
			if (param[count] != null) {
				if (param[count] instanceof InputStream) {
					InputStream input = (InputStream) param[count];
					try {
						preparedStatement.setBinaryStream(count + 1, input,
								input.available());
					} catch (IOException e) {
						e.printStackTrace();
						throw e;
					}
				} else
					preparedStatement.setObject(count + 1, param[count]);
			} else
				preparedStatement.setNull(count + 1, Types.VARCHAR);
		}

	}

	/**
	 * This method is used to execute same dml query, with multiple parameters.
	 * i.e. updating multiple rows of a table with different values. It will
	 * execute this queries in batch.
	 * 
	 * @param connection
	 *            Connection to the database.
	 * @param query
	 *            The query to be executed.
	 * @param param
	 *            List containing Object[] of parameters to be supplied to the
	 *            query.
	 * @return an array of int in which holds number of records affected for
	 *         each query execution.
	 * @throws SQLException
	 * @throws IOException
	 */
	public int[] executeBatch(Connection connection, String query, List param)
			throws SQLException, IOException {

		if (param == null) {
			return null;
		}

		PreparedStatement stmt = null;
		int[] rows = null;
		try {
			stmt = connection.prepareStatement(query);
			for (Object obj : param) {
				if (obj instanceof Object[]) {
					Object[] paramObj = (Object[]) obj;
					this.setStatement(stmt, paramObj);
					stmt.addBatch();
				}
			}
			rows = stmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}

		return rows;

	}

}
