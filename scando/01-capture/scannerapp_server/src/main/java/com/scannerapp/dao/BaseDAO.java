package com.scannerapp.dao;

import com.scannerapp.ws.common.util.db.SqlUtil;


/**
 * BaseDAO class which should be accessed by every DAO
 *
 * @author Amit Patel <amit.patel@spec-india.com>
 * @since Jan 23, 2012
 */
public class BaseDAO {
	
	private SqlUtil  sqlUtil =  null;
	
	public BaseDAO() {
		sqlUtil = new SqlUtil();
	}
	
	protected SqlUtil getSqlUtil() {
		return sqlUtil;
	}
	
	protected void setSqlUtil(SqlUtil sqlUtil) {
		this.sqlUtil = sqlUtil;
	}
}
