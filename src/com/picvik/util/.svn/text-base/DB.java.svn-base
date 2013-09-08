package com.picvik.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * 
 * @author Abbas / Sushma
 */

public class DB {
	// private static DataSource data;
	private static DataSource data;// , data1 = null;
	static int timeCounter = 0;
	static boolean isActiveConnectionsWatcherStarted = false;
	protected static Connection conn;
	protected DatabaseMetaData dmd;
	protected ResultSetMetaData rsmd = null;
	protected ResultSet rs;
	protected Statement stmt;
	protected static boolean isSeekingConnectionFirstTime = true;
	protected static boolean isEnteredBMTCFirstTime = true;
	protected static boolean isEnteredCelcabsFirstTime = true;
	protected static int activeConnections;
	static String SPACES = "                           " + "                 ";
	private static DB db = new DB();

	private DB() {
	}

	public static DB getInstance() {
		return db;
	}

	public static void main(String[] args) {
		db.createDatabase("picvik");
	}

	/**
	 * create a database; drops it first if it exists
	 * 
	 * @param databaseName
	 */
	public void createDatabase(String databaseName) {
		try {
			Statement stmt;

			// Register the JDBC driver for MySQL.
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for
			// database named mysql on the localhost
			// with the default port number 3306.
			String url = "jdbc:mysql://localhost:3306/mysql";

			// Get a connection to the database for a
			// user named root with a blank password.
			// This user is the default administrator
			// having full privileges to do anything.
			Connection con = DriverManager.getConnection(url,
					RuntimeSettings.dbUserID, 
					RuntimeSettings.dbPassword);

			// Display URL and connection information
			System.out.println("URL: " + url);
			System.out.println("Connection: " + con);

			// Get a Statement object
			stmt = con.createStatement();

			// Create the new database after 1st dropping it if it exists
			stmt.executeUpdate("drop database if exists " + databaseName);

			stmt.executeUpdate("CREATE DATABASE " + databaseName);
			close(stmt);
			close(con);
		} catch (Exception e) {
			e.printStackTrace();
		}// end catch
	}

	public static DataSource getDataSource() {
		if (data != null) {
			return data;
		}
		try {
			Context ctx = new InitialContext();
			if (ctx == null) {
				MyLog.log_and_print("context is null! in loginaction");
				data = null;
				return null;

			}
			data = (DataSource) ctx.lookup("java:comp/env/jdbc/tmsDS");
			if (data == null) {
				MyLog.log_and_print("did NOT get datasource!");
			}
			// else {
			// test the datasource that it is able to get a connection!
			// Connection tempConn = data.getConnection();
			// update(tempConn, "insert into addresses(" +
			// "customer_id, address_type, street) values(" +
			// "1, 'Home', 'diamond district');");
			// tempConn.close();
			// }
		} catch (Exception e) {
			MyLog.log_and_print("exception in getDatasource()");
		}
		return data;
	}

	/**
	 * get the count of rows in a table
	 * 
	 * @param tableName
	 * @param whereClause
	 *            "" if there is no where selection else 'where a=b" say
	 * @return
	 */
	public static int getTMSCount(String tableName, String whereClause) {
		Connection con = getConnection();
		int count = getCount(con, tableName, whereClause);
		close(con);
		return count;
	}

	public static int getCount(Connection con, String tableName,
			String whereClause) {
		int result = 0;
		String selectQuery = "SELECT count(*)  from " + tableName + " "
				+ whereClause;
		ResultSet rs = readFromDB(selectQuery, con);
		try {
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException ex) {
			MyLog.myIO("catch in Taxi.findCount() line # 60");
		}
		close(rs);
		return result;
	}

	private static ArrayList<String> getColumnNames(ResultSet rs) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			if (rs == null) {
				return result;
			}
			// if (rs != null && rs.next()) {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = rsmd.getColumnName(i).toUpperCase();
				result.add(columnName);
			}
		} catch (SQLException e) {
			MyLog.myCatch("/java", 188, e);
		}
		return result;

	}

	private static Connection getConnection(String dbName) {
		Connection conn;
		conn = getConnection();
		return conn;

	}

	private static ArrayList<String> getRowValues(ResultSet rs, int columnCount) {
		ArrayList<String> result = new ArrayList<String>();
		if (rs == null) {
			return result;
		}
		// if (rs != null && rs.next()) {
		for (int i = 1; i <= columnCount; i++) {
			try {
				String columnValue = rs.getString(i);
				result.add(columnValue);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return result;
	}

	private DB(String password, String dbname) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + dbname
							+ "?autoReconnect=true", "root", password);
		} catch (SQLException se) {
			MyLog.myCatch("/java", 100, se);
			se.printStackTrace();
		} catch (ClassNotFoundException c) {
			MyLog.myCatch("/java", 103, c);
			c.printStackTrace();
		}

	}

	public static DB getInstance(String password, String dbname) {
		return new DB(password, dbname);
	}

	/**
	 * executes the update SQL; if it is not successful, it will execute the
	 * insert SQL. If rows affected by the update query is 0, that means the
	 * update was not successful
	 * 
	 * @param updateSQL
	 *            - the update SQL string
	 * @param insertSQL
	 *            - the insert SQL string
	 * @return the number of rows affected by the update or insert command
	 */
	public static int updateElseInsertTMS(String updateSQL, String insertSQL) {
		int rowsAffected = 0;
		boolean isOK = true;
		if (!(updateSQL.trim().toUpperCase().startsWith("UPDATE "))) {
			isOK = false;
		}

		if (!(insertSQL.trim().toUpperCase().startsWith("INSERT "))) {
			isOK = false;
		}

		if (!isOK) {
			MyLog.myIO("in updateElseInsertTMS, incorrect parameters:"
					+ "updateSQL:" + updateSQL + ":, insertSQL:" + insertSQL
					+ ":");
			System.exit(1);
		}

		rowsAffected = update(updateSQL);
		if (rowsAffected < 1) {
			rowsAffected = update(insertSQL);
		}

		return rowsAffected;
	}

	public static int update(String sql) {

		int rowsUpdated = 0;
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(true);
			rowsUpdated = update(connection, sql);
		} catch (SQLException ex) {
			MyLog.myCatch("/java", 143, ex);
		} finally {
			close(connection);
		}
		return rowsUpdated;
	}

	static void text2Table(String fileName) {
		String tableName = "";
		String linesFromFile[];
		linesFromFile = AU1.textFile2Lines(fileName);
		for (int i = 0; i < linesFromFile.length; i++) {
			if (linesFromFile[i].trim().startsWith("Running query :")) {
				String str[] = linesFromFile[i].toLowerCase().split("from ");
				String str2[] = str[1].split(" ");
				tableName = str2[0];
				Connection con = null;
				ResultSet rs = null;
				try {
					con = getConnection();
					rs = readFromDB("show tables like 'temp_1_" + tableName
							+ "'", con);
					if (!rs.next()) {
						update("create table temp_1_" + tableName + " like "
								+ tableName);
					}

				} catch (SQLException e) {
					MyLog.myCatch("java", 285, e);
				} finally {
					close(rs);
					close(con);
				}

				continue;
			}

			if (linesFromFile[i].startsWith("row")) {
				String fieldValues = getFieldValues2(linesFromFile[i]);
				// String fields[] = linesFromFile[i].substring(6).split(",");
				// String fieldValues = getFieldValues(fields);
				if (tableName.contains("sms_rcvd_tbl")) {// && fields.length >
															// 22) {

					update("insert into temp_1_"
							+ tableName
							+ " values("
							+ fieldValues.substring(0,
									(fieldValues.length() - 5)) + ");");
				} else {
					update("insert into temp_1_"
							+ tableName
							+ " values("
							+ fieldValues.substring(0,
									(fieldValues.length() - 1)) + ");");
				}

			}
		}
	}

	static String getFieldValues2(String line) {
		String tokens[];
		int start = line.indexOf(":");
		String properLine = line.substring(start + 1);
		tokens = properLine.split(",");
		return getFieldValues(tokens);
	}

	static String getFieldValues(String fields[]) {
		String fieldValues = "";
		/*
		 * if (fields[0].contains(":")) { fields[0] =
		 * fields[0].substring(fields[0].indexOf(":") + 1, fields[0].length());
		 * }
		 */
		for (int i = 0; i < fields.length; i++) {
			// System.out.println("i value:" + fields[i] + ":" + i);
			if ((fields[i].matches("[ ](20)[0-9][0-9][/](0[1-9]|1[012])[/]"
					+ "(0[1-9]|[12][0-9]|3[01])"))) { // *** change to regexp
														// dd/dd/dddd
				// if (fields[i].contains("/")}{

				fieldValues += "'" + fields[i].trim() + ", "
						+ fields[i + 1].trim() + "',";
				i++;

			} else {
				fieldValues += "'" + fields[i].trim() + "',";
			}

		}
		return fieldValues;
	}

	public static ResultSet readFromBmtcDB(String query) {
		Connection connection = null;
		System.out.println("in readFromBMTCDB with query:\n" + query);
		connection = getConnection();
		return readFromDB(query, connection);
	}

	public static ResultSet readFromDB(String query, Connection connection) {
		ResultSet result = null;
		Statement stmt = null;
		try {
			MyLog.log("in readFromDB with query:\n" + query);
			if ((query.indexOf(" where ") >= 0)
					|| (query.indexOf("count ") >= 0)) {
				// ok
			} else {
				MyLog.myIO("@@@ please check as query without a where clause!");
			}
			stmt = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// System.out.println("in readFromDB inside result with query:\n" +
			// query);
			result = stmt.executeQuery(query);
			MyLog.myIO("readFromDB sql:" + query);
		} catch (SQLException se) {
			MyLog.myCatch("/java", 66, se);
		}
		return result;
	}

	public static int update(Connection connection, String sql) {
		Statement statement = null;
		int rows = 0;
		try {
			statement = connection.createStatement();
			rows = statement.executeUpdate(sql);
			MyLog.myIO("updating " + rows + " rows for sql:" + sql + ":");
		} catch (SQLException ex) {
			MyLog.myCatch("/update() while running sql:" + sql + ":", 79, ex);
		} finally {
			close(statement);
		}
		return rows;
	}

	public static void close(Connection connection) {
		// return;
		if (connection == null) {
			return;
		}
		try {
			if (connection.isClosed()) {
				connection = null;
			} else {
				try {
					connection.close();
					MyLog.log("closing a conn; total conn:" + activeConnections);
					connection = null;
				} catch (SQLException ex) {
					MyLog.myCatch("/java", 106, ex);
				}
			}
		} catch (SQLException ex) {
			MyLog.myCatch("/java", 110, ex);
		}
	}

	public static Connection getConnection() {
		Connection con = null;
		try {

			if (isSeekingConnectionFirstTime) {
				isSeekingConnectionFirstTime = false;
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException ex) {
					MyLog.myCatch("/java", 162, ex);
				}
			}
			String connectionUrl = "jdbc:mysql://" + RuntimeSettings.SERVER_IP
					+ ":" + RuntimeSettings.portNo + "/"
					+ RuntimeSettings.databaseName
					+ "?zeroDateTimeBehavior=convertToNull";
			con = DriverManager.getConnection(connectionUrl,
					RuntimeSettings.dbUserID, RuntimeSettings.dbPassword);
		} catch (SQLException ex) {
			MyLog.myCatch("/java", 217, ex);
		}
		activeConnections++;
		MyLog.log("in DBDataStandAlone added new tms conn; total connections:"
				+ activeConnections);
		return con;
	}

	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
				statement = null;
			} catch (SQLException ex) {
				MyLog.myCatch("/java", 202, ex);
			}
		}
	}

	public static void close(ResultSet rs) {
		// return;
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException ex) {
				MyLog.myCatch("/java", 214, ex);
			}
		}
	}

	public static void close(PreparedStatement preparedStmt) {
		if (preparedStmt != null) {
			try {
				preparedStmt.close();
				preparedStmt = null;
			} catch (SQLException ex) {
				MyLog.myCatch("/java", 225, ex);
			}
		}
	}

	public int runScript(String sqlStatement) {
		int rowsUpdated = update(sqlStatement);
		return rowsUpdated;
	}

	public int runScript(String[] sqlStatements) {
		for (int i = 0; i < sqlStatements.length; i++) {
			MyLog.log("running sql:" + sqlStatements[i]);
			runScript(sqlStatements[i]);
		}
		return 1;
	}

	public static boolean listCount(String dbName) {
		// MyLog.setReportName("\\RowsIn" + dbName);
		Connection myConn = null;
		Connection myConn2 = null;
		if (dbName.equalsIgnoreCase("CC")) {
			// myConn = getCelcabsConnection();
			// myConn2 = getCelcabsConnection();
		} else if (dbName.equalsIgnoreCase("tms")) {
			myConn = getConnection();
			myConn2 = getConnection();
		} else {
			MyLog.log("*** in runScript dbName is invalid and =" + dbName);
			System.exit(1);
		}

		boolean result = listCountOfRowsInAllTables(dbName, myConn, myConn2);
		close(myConn);
		close(myConn2);
		return result;
	}

	public static boolean listOracleDBTableNames(Connection conn) {
		ArrayList<String> tableNames = new ArrayList<String>();
		try {
			// Connection conn = getOracleConnection();
			Statement stmt = null;
			ResultSet rs = null;
			stmt = conn.createStatement();

			rs = stmt
					.executeQuery("select object_name from user_objects where object_type = 'TABLE'");

			while (rs.next()) {
				String tableName = rs.getString(1);
				tableNames.add(tableName);
				MyLog.log("tableName=" + tableName);
			}

			stmt.close();
			rs.close();
			for (int i = 0; i < tableNames.size(); i++) {
				String tableName = tableNames.get(i);
				String query = "select * from " + tableName
						+ " where ROWNUM < 4";
				listInitialRows(conn, query);
			}
			conn.close();
			return true;
		} catch (SQLException ex) {
			MyLog.myCatch("DBDataStandAlone", 389, ex);
		}
		return false;
	}

	public static void listInitialRows(Connection con, String query) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		MyLog.myIO("listing initialRows for " + query);
		MyLog.log("listing initialRows for " + query);
		try {
			Statement s = con.createStatement();
			// MyLog.log("Reading rows from " + tokens[0] + " table");
			rs = s.executeQuery(query);
			MyLog.myIO("got result set");
			//
			ResultSetMetaData rsmd = rs.getMetaData();
			// ResultSetMetaData rsmd = getRSMetaData("IVTSGPSDATA");
			int columnCount = rsmd.getColumnCount();

			System.out.println("after select");
			String line = "Columns:";
			for (int k = 1; k <= columnCount; k++) {
				String columnName = "";
				columnName = rsmd.getColumnName(k);
				line += columnName + ", ";
			}
			MyLog.log(line);
			int i = 0;
			int j = 0;
			while (rs.next()) {
				i++;
				if (i < 1000) {
					line = "\nrow:" + i + " - ";
					for (int k = 1; k <= columnCount; k++) {
						String columnValue = rs.getString(k);
						line += columnValue + ", ";
					}
					MyLog.log(line);
				}
			}
			MyLog.log("Total # of rows:" + i);
			MyLog.myIO("Total # of rows:" + i);
		} catch (Exception e) {
			System.out.println("inside catch");
			e.printStackTrace();
		} finally {
		}
	}

	public static boolean listCountOfRowsInAllTables(String dbName,
			Connection myConn, Connection myConn2) {
		MyLog.myReport("\r\n\r\n\t\tSnapshot of Rows in tables of Database "
				+ dbName + "\r\n\r\n");
		MyLog.log("\r\n\r\n\t\tSnapshot of Rows in tables of Database "
				+ dbName + "\r\n\r\n");
		System.out.println("\r\n\r\n\t\tSnapshot of Rows in tables of "
				+ "Database " + dbName + "\r\n\r\n");
		MyLog.myReport("Table Name             \t\t # of Rows");
		MyLog.myReport("=======================\t\t ---------\r\n");
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {
			java.sql.DatabaseMetaData dmd = null;
			try {
				dmd = myConn.getMetaData();
			} catch (SQLException e) {
				MyLog.myCatch("/java", 361, e);
				e.printStackTrace();
			}
			try {
				rs = dmd.getTables(null, null, null, null);
			} catch (SQLException e) {
				MyLog.myCatch("/java", 367, e);
				e.printStackTrace();
			}
			int rows = 0;
			int i = 0;
			boolean stop = true;
			while (rs.next()) {
				i++;
				try {
					String tablename = rs.getString(3);
					MyLog.log("" + i + ":" + tablename);
					// if (stop) {
					// continue;
					// }
					if ((tablename.contains("pkey"))
							|| tablename.contains("pg_")) {
						continue;
					}
					rs2 = readFromDB("select count(*) from " + tablename + "",
							myConn2);
					if (rs2.next()) {
						rows = rs2.getInt(1);
						MyLog.log("rows in table " + tablename + " = " + rows);
						MyLog.myReport(tablename + "\t\t\t\t " + rows);
					}
				} catch (SQLException se) {
					MyLog.myCatch("/java", 387, se);
					rows = 0;
				} catch (NullPointerException npe) {
					MyLog.myCatch("/java", 390, npe);
					rows = 0;
				} finally {
					MyLog.log("in finally of listcount");
					close(rs2);
				}
			}

			MyLog.myReport("================== e n d   o f   r e p o r t ==="
					+ "===============\r\n");
			// MyLog.resetReportName();
		} catch (SQLException ex) {
			MyLog.myCatch("DBDataStandAlone", 383, ex);
		}
		close(rs);
		return true;
	}

	public static boolean countAllRows(String dbName) {
		// MyLog.setReportName("\\RowsIn" + dbName);
		int i;
		int rows = 0;
		String[] tablename = { "sms_send_tbl", "sms_rcvd_tbl", "other_msg_tbl",
				"vehicle_assignment", "vid_mdt" };
		MyLog.myReport("\r\n\r\n\t\tSnapshot of Rows in tables of Database "
				+ dbName + "\r\n\r\n");
		MyLog.log("\r\n\r\n\t\tSnapshot of Rows in tables of Database "
				+ dbName + "\r\n\r\n");
		System.out
				.println("\r\n\r\n\t\tSnapshot of Rows in tables of Database "
						+ dbName + "\r\n\r\n");
		MyLog.myReport("Table Name             \t\t # of Rows");
		MyLog.myReport("=======================\t\t ---------\r\n");
		Connection myConn = null;
		Connection myConn2 = null;
		// ResultSet rs = null;
		ResultSet rs2 = null;
		if (dbName.equalsIgnoreCase("CC")) {
			// myConn = getCelcabsConnection();
			// myConn2 = getCelcabsConnection();
		} else if (dbName.equalsIgnoreCase("tms")) {
			myConn = getConnection();
			myConn2 = getConnection();
		} else {
			MyLog.log("*** in runScript dbName is invalid and =" + dbName);
			System.exit(1);
		}
		try {
			for (i = 0; i < tablename.length; i++) {
				rs2 = readFromDB("select count(*) from " + tablename[i] + "",
						myConn2);
				if (rs2.next()) {
					rows = rs2.getInt(1);
					MyLog.log("rows in table " + tablename[i] + " = " + rows);
					MyLog.myReport(tablename[i] + "\t\t\t\t " + rows);
				}
			}
		} catch (SQLException ex) {
			MyLog.myCatch("/java", 447, ex);
			rows = 0;
		} catch (NullPointerException npe) {
			MyLog.myCatch("/java", 455, npe);
			rows = 0;
		} finally {
			MyLog.log("in finally of countallrows");
			close(rs2);
		}

		return true;
	}

	public boolean dump() {
		Connection[] connections = { conn };

		return dump(connections, "/SQLScript.txt");
	}

	public boolean dump(Connection[] connections, String reportName) {
		boolean result = true;
		AU1.w("in dump");
		AU1.reportName = reportName; // "/SQLScript.txt";

		AU1.writeText("", true); // start a anew file

		for (int i = 0; (i < connections.length && result); i++) {
			conn = connections[i];
			result = dump(reportName);
		}
		return result;
	}

	public boolean dump(String reportName) {
		boolean result = true;
		try {
			// conn = getCelcabsConnection();
			rs = getTables();
			stmt = conn.createStatement();
			int i = 0;
			while (rs.next()) {
				i++;
				String tablename = rs.getString(3);
				String createSQL = generateCreateTableScript(tablename);
				AU1.w(createSQL);
				String insertScript = generateInsertIntoTableScript(tablename);
				AU1.w(insertScript);
			}

			// rs = s.executeQuery(query);
		} catch (SQLException se) {
			MyLog.myCatch("/java", 483, se);
		}
		AU1.reportName = "/log.txt";
		return result;
	}

	boolean runScriptFromFile(String dbName, String fileName) {
		MyLog.log("in runScriptFromFile with " + dbName + ", " + fileName);
		System.out.println("in runScriptFromFile with " + dbName + ", "
				+ fileName);
		String[] lines = AU1.textFile2SQLLines(fileName);
		// String[] lines =AU1. textFile2Lines(fileName);
		runScript(lines);
		return true;
	}

	boolean runScriptFromFile(String fileName) {
		// read the file into an array of lines
		String[] lines = AU1.textFile2SQLLines(fileName);
		// String[] lines =AU1. textFile2Lines(fileName);
		runScript(lines);
		return true;
	}

	public ResultSetMetaData getRSMetaData(String tablename) {
		ResultSetMetaData result = null;
		ResultSet tableData = getFullResultSet(tablename);
		try {
			result = tableData.getMetaData();
		} catch (SQLException se) {
			MyLog.myCatch("/java", 522, se);
		}
		return result;
	}

	public ResultSet runQuery(String query) {
		ResultSet rs = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			MyLog.myCatch("/java", 482, e);
			AU1.w("SQLException in SetUpDB.runQuery for query:\n" + query);
			e.printStackTrace();
		}
		return rs;
	}

	protected ResultSet getFullResultSet(String tableName) {
		String query = "Select * from " + tableName;
		return runQuery(query);
	}

	public String generateInsertIntoTableScript(String tablename) {
		String insertScript = "";
		ResultSetMetaData rsmd = getRSMetaData(tablename);
		ResultSet rs = getFullResultSet(tablename);
		MyLog.log("read from table: " + tablename);
		try {
			int i = 0;
			while ((rs.next()) && (i < 100)) {
				i++;
				MyLog.log("row #" + i);
				insertScript += "\r\nINSERT INTO " + tablename
						+ " VALUES (\r\n";
				int sizeTillLastLineBreak = insertScript.length();
				int columnCount = rsmd.getColumnCount();
				for (int j = 1; j <= columnCount; j++) {
					String thisColumnType = "QUOTED";
					String columnTypeName = rsmd.getColumnTypeName(j);
					if ((":DECIMAL:NUMERIC:INTEGER:".indexOf(":"
							+ columnTypeName.toUpperCase() + ":") >= 0)
							|| (columnTypeName.toUpperCase().indexOf("INT") >= 0)
							|| (columnTypeName.toUpperCase().indexOf("BOOL") >= 0)) {
						thisColumnType = "NON-QUOTED";
					}
					if (rs.getString(j) == null) {
						insertScript += "null";
					} else if (thisColumnType.equalsIgnoreCase("NON-QUOTED")) {
						insertScript += rs.getString(j);
					} else {
						insertScript += "'" + rs.getString(j) + "'";
					}
					if (j < columnCount) {
						insertScript += ", ";
						if ((insertScript.length() - sizeTillLastLineBreak) > 70) {
							insertScript += "\r\n  ";
							sizeTillLastLineBreak = insertScript.length();
						}
					} else {
						insertScript += ");\";";
					}
				}
			}
		} catch (SQLException se) {
			MyLog.myCatch("/java", 594, se);
		}
		// AU1.w(insertScript);
		return insertScript;
	}

	public void displayRows(String tablename) {
		rsmd = getRSMetaData(tablename);
		try {
			int columnCount = rsmd.getColumnCount();
			int rowCount = 0;
			while (rowCount < 2) {
				rowCount++;
				// AU1.w("# of columns: " + columnCount);
				for (int j = 1; j <= columnCount; j++) {
					String columnName = rsmd.getColumnName(j);
					String columnTypeName = rsmd.getColumnTypeName(j);
					String columnValue = rs.getString(j);
				}
			}
		} catch (SQLException ex) {
			MyLog.myCatch("/java", 590, ex);
			ex.printStackTrace();
			AU1.w("exception in SetUpDB.generateCreateTableScript");
		}
		return;
	}

	public static void displayResultSet(ResultSet resultSet) {
		// displays the current row
		try {
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();
			// AU1.w("# of columns: " + columnCount);
			for (int j = 1; j <= columnCount; j++) {
				String columnName = rsmd.getColumnName(j);
				String columnTypeName = rsmd.getColumnTypeName(j);
				String columnValue = resultSet.getString(j);
				MyLog.log("col name:" + columnName + ": columnValue:"
						+ columnValue + ":");
			}
		} catch (SQLException ex) {
			MyLog.log("exception in displayResultSet()");
			ex.printStackTrace();
		}
	}

	public String generateCreateTableScript(String tableName) {
		String createSQL = "\r\nDROP TABLE " + tableName + ";"
				+ "\r\nCREATE TABLE " + tableName + " (\r\n";
		String columnSpecifiers = getTableColumnSpecifiers(tableName);
		createSQL += columnSpecifiers;
		return createSQL;
	}

	public String getTableColumnSpecifiers(String tableName) {
		String result = "";
		rsmd = getRSMetaData(tableName);

		try {
			int columnCount = rsmd.getColumnCount();
			// AU1.w("# of columns: " + columnCount);
			for (int j = 1; j <= columnCount; j++) {
				String columnName = rsmd.getColumnName(j);
				if (columnName.equalsIgnoreCase("id")) {
					result += "id INT(11) PRIMARY KEY AUTO_INCREMENT "
							+ " NOT NULL,\r\n";
					continue;
				}
				String columnTypeName = rsmd.getColumnTypeName(j);
				int precision = rsmd.getPrecision(j);
				int scale = rsmd.getScale(j);
				int nullable = rsmd.isNullable(j);
				if (columnTypeName.equalsIgnoreCase("INTEGER")) {
					columnTypeName = "INT(11)";
				} else if (columnTypeName.equalsIgnoreCase("DECIMAL")) {
					columnTypeName = "NUMERIC";
				}
				result += " " + columnName + " " + columnTypeName;
				if (":DATETIME:TINYINT:DOUBLE:INT(11):".indexOf(":"
						+ columnTypeName.toUpperCase() + ":") < 0) {
					result += "(" + precision;
					if (columnTypeName.equalsIgnoreCase("NUMERIC")) {
						result += ", " + scale;
					}
					result += ")";
				}
				if (nullable == ResultSetMetaData.columnNoNulls) {
					result += " NOT NULL";
				} else {
					result += " NULL";
				}
				if (j < columnCount) {
					result += ",\r\n";
				} else {
					result += ");";
				}
			}
		} catch (SQLException ex) {
			MyLog.myCatch("/java", 590, ex);
			ex.printStackTrace();
			AU1.w("exception in SetUpDB.generateCreateTableScript");
		}
		// AU1.w(result);
		return result;
	}

	java.sql.DatabaseMetaData getDBMetaData() {
		java.sql.DatabaseMetaData dmd = null;
		try {
			dmd = conn.getMetaData();
		} catch (SQLException e) {
			MyLog.myCatch("/java", 603, e);
			e.printStackTrace();
		}
		return dmd;
	}

	public ResultSet getTables() {
		dmd = getDBMetaData();
		try {
			rs = dmd.getTables(null, null, null, null);
		} catch (SQLException e) {
			MyLog.myCatch("/java", 614, e);
			e.printStackTrace();
		}
		return rs;
	}

	public String getDBModelInsertCode(String tablename, String modelName) {
		String insertScript = "";
		rsmd = getRSMetaData(tablename);
		try {
			int numOfColumn = rsmd.getColumnCount();
			MyLog.log("Number Of Column is:" + numOfColumn);

			insertScript += getInstances(rsmd) + "\r\n";
			insertScript += getSetterGetters(rsmd) + "\r\n";
			insertScript += getGetInsertSql(rsmd, tablename) + "\r\n";
			insertScript += getInsertUpdate(rsmd, tablename) + "\r\n";
			insertScript += getInsertSave() + "\r\n";
			insertScript += getInsertFind_via_id(tablename, modelName) + "\r\n";
			insertScript += getToString(rsmd) + "\r\n";
			insertScript += getUpdateSql(tablename) + "\r\n";
			insertScript += getsetClassValuesFromResultSet(rsmd) + "\r\n";
			MyLog.log("************Printing Code**************");
			// MyLog.log(insertScript);

		} catch (SQLException se) {
			MyLog.myCatch("/java", 522, se);
		}
		return insertScript;
	}

	public String getsetClassValuesFromResultSet(ResultSetMetaData rsmd) {
		String text = "    void setClassValuesFromResultSet(ResultSet rs) {"
				+ "\r\n" + "        try {" + "\r\n";
		try {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {

				if (rsmd.getColumnName(i).equalsIgnoreCase("id")
						|| rsmd.getColumnName(i).equalsIgnoreCase("created_at")
						|| rsmd.getColumnName(i).equalsIgnoreCase("updated_at")) {
					continue;
				}
				if (getJavaType(rsmd.getColumnTypeName(i)).equalsIgnoreCase(
						"int")) {
					text += "            this.set"
							+ Character.toUpperCase(rsmd.getColumnName(i)
									.charAt(0))
							+ rsmd.getColumnName(i).substring(1,
									rsmd.getColumnName(i).length())
							+ "(rs.getInt(\"" + rsmd.getColumnName(i) + "\"));"
							+ "\r\n";
				} else {
					text += "            this.set"
							+ Character.toUpperCase(rsmd.getColumnName(i)
									.charAt(0))
							+ rsmd.getColumnName(i).substring(1,
									rsmd.getColumnName(i).length())
							+ "(rs.getString(\"" + rsmd.getColumnName(i)
							+ "\"));" + "\r\n";
				}
			}
			text += "        } catch (SQLException ex) {"
					+ "\r\n"
					+ "                MyLog.myCatch(\"Customer.java\", 293, ex);"
					+ "\r\n" + "        }" + "\r\n" + "    }";
		} catch (SQLException ex) {
			MyLog.myCatch("/java", 658, ex);
		}

		return text;
	}

	public String getUpdateSql(String tablename) {
		String text = "    public static int updateSQL(String sql) {" + "\r\n";
		text += "        return updateTMS(\"update " + tablename
				+ " \" + sql);" + "\r\n" + "    }";
		return text;

	}

	public String getToString(ResultSetMetaData rsmd) {
		String text = "    public String toString() {" + "\r\n"
				+ "        String result = ";
		try {
			// Loop is going till rsmd.getColumnCount()-2, because we are not
			// going to use created_at and updated_at column.
			for (int i = 1; i <= rsmd.getColumnCount() - 2; i++) {
				// Using if-else condition, because with the last element "+"
				// should not come
				if (i != rsmd.getColumnCount() - 2) {
					text += "\"" + rsmd.getColumnName(i) + " = \" + "
							+ rsmd.getColumnName(i) + " + ";
				} else {
					text += "\"" + rsmd.getColumnName(i) + " = \" + "
							+ rsmd.getColumnName(i);
				}
			}
			text += ";" + "\r\n" + "        return result ;" + "\r\n" + "    }";
		} catch (SQLException ex) {
			MyLog.myCatch("/java", 656, ex);
		}
		return text;
	}

	/*
	 * public static Test find_via_id(int id) { Test test = new Test(); test =
	 * (Test)find_model_via_id(id,test); return test; }
	 */
	public String getInsertFind_via_id(String tablename, String modelName) {
		String text = "// new program\r\n";
		text += "    public static " + modelName + " find_via_id(int id) {\r\n";
		text += "        " + modelName + " " + modelName.toLowerCase()
				+ " = new " + modelName + "();\r\n";
		text += "        " + modelName.toLowerCase() + " = (" + modelName
				+ ")find_model_via_id(id, " + modelName.toLowerCase()
				+ ");\r\n";
		text += "        " + "return " + modelName.toLowerCase() + ";\r\n";
		text += "    }\r\n";
		return text;
	}

	public String getInsertSave() {
		String text = "    public int save() {" + "\r\n"
				+ "        System.out.println(\"ID IS :\" + id);" + "\r\n"
				+ "        ArrayList<String> errors = validate();" + "\r\n"
				+ "        if (errors.size() > 0) {" + "\r\n"
				+ "            return 0;" + "\r\n" + "        }" + "\r\n"
				+ "        if (id < 0) {" + "\r\n"
				+ "            return insert();" + "\r\n" + "         }"
				+ " else {" + "\r\n" + "            return update();" + "\r\n"
				+ "        }" + "\r\n" + "    }";
		return text;
	}

	public String getInsertUpdate(ResultSetMetaData rsmd, String tablename) {
		String text = "    public int update() {" + "\r\n";
		text += "        String fields = \"";
		try {
			// Starting from 2 because first element is id and we can not update
			// id
			for (int i = 2; i <= rsmd.getColumnCount(); i++) {
				if (rsmd.getColumnName(i).equalsIgnoreCase("created_at")) {
					continue;
				}
				if (rsmd.getColumnName(i).equalsIgnoreCase("updated_at")) {
					text += "updated_at = current_timestamp \"" + ";";
				} else {
					if (getJavaType(rsmd.getColumnTypeName(i))
							.equalsIgnoreCase("int")) {
						text += rsmd.getColumnName(i) + " = \" +"
								+ rsmd.getColumnName(i) + "+ \",";
					} else {
						text += rsmd.getColumnName(i) + " = '\" +"
								+ rsmd.getColumnName(i) + "+ \"', ";
					}
				}
			}
			text += ";" + "\r\n" + "        int rows = updateTMS(\"update "
					+ tablename + " set \" + fields + \"where id = \"+"
					+ rsmd.getColumnName(1) + ");";

			text += "\r\n" + "        if(rows == 1) {" + "\r\n"
					+ "           return " + rsmd.getColumnName(1) + ";"
					+ "\r\n" + "         }" + "\r\n" + "        return 0;"
					+ "\r\n" + "    }";
		} catch (SQLException ex) {
			MyLog.myCatch("/java", 721, ex);
		}
		return text;
	}

	public String getGetInsertSql(ResultSetMetaData rsmd, String tablename) {
		String text = "    public String getInsertSQL() {" + "\r\n"
				+ "        return ";
		text += " \"" + "insert into " + tablename + "(";
		try {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				if (rsmd.getColumnName(i).equalsIgnoreCase("created_at")
						|| rsmd.getColumnName(i).equalsIgnoreCase("updated_at")) {
					continue;
				}
				// in the insert query "," with the last element will not come
				if (i == (rsmd.getColumnCount() - 2)) {
					text += rsmd.getColumnName(i);
				} else {
					text += rsmd.getColumnName(i) + ", ";
				}
			}
			text += ") values(" + " \" ";
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				if (rsmd.getColumnName(i).equalsIgnoreCase("created_at")
						|| rsmd.getColumnName(i).equalsIgnoreCase("updated_at")) {
					continue;
				}
				if (rsmd.getColumnName(i).equalsIgnoreCase("id")) {
					text += " + " + "this.id +";
					continue;
				}

				if (getJavaType(rsmd.getColumnTypeName(i)).equalsIgnoreCase(
						"" + "String")) {
					if (i == 2) {
						text += "\", '\" + " + rsmd.getColumnName(i) + " + \"'";
					} else {
						text += ", '\" + " + rsmd.getColumnName(i) + " + \"'";
					}
				}
				if (getJavaType(rsmd.getColumnTypeName(i)).equalsIgnoreCase(
						"" + "int")) {
					text += ", \" +" + rsmd.getColumnName(i) + "+ \"";
				}
			}
			// text = formatString(text);
			text += ");\"" + ";" + "\r\n" + "    }";
		} catch (SQLException ex) {
			MyLog.myCatch("/java", 666, ex);
		}
		return text;
	}

	public String formatString(ResultSetMetaData rsmd, String tablename) {

		return null;
	}

	public String getSetterGetters(ResultSetMetaData rsmd) {
		String text = "";
		try {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				// Code For Getter Method
				text += "    public "
						+ getJavaType(rsmd.getColumnTypeName(i))
						+ " get"
						+ Character
								.toUpperCase(rsmd.getColumnName(i).charAt(0))
						+ rsmd.getColumnName(i).substring(1,
								rsmd.getColumnName(i).length()) + "() " + "{"
						+ "\r\n" + "        return " + rsmd.getColumnName(i)
						+ ";" + "\r\n" + "    }" + "\r\n";

				// Code For Setter Method
				if (rsmd.getColumnName(i).equalsIgnoreCase("created_at")
						|| rsmd.getColumnName(i).equalsIgnoreCase("updated_at")) {
					continue;
				}
				text += "    public void set"
						+ Character
								.toUpperCase(rsmd.getColumnName(i).charAt(0))
						+ rsmd.getColumnName(i).substring(1,
								rsmd.getColumnName(i).length()) + "("
						+ getJavaType(rsmd.getColumnTypeName(i)) + " "
						+ rsmd.getColumnName(i) + ") {" + "\r\n"
						+ "        this." + rsmd.getColumnName(i) + " = "
						+ rsmd.getColumnName(i) + ";" + "\r\n" + "    }"
						+ "\r\n";
			}
		} catch (SQLException ex) {
			MyLog.myCatch("DBDataStandAlone", 687, ex);
		}
		return text;
	}

	String getJavaType(String rsmdColumnType) {
		String dataType = "";
		if (rsmdColumnType.equalsIgnoreCase("varchar")
				|| rsmdColumnType.equalsIgnoreCase("char")
				|| rsmdColumnType.equalsIgnoreCase("TIMESTAMP")) {
			dataType = "String";
		} else if (rsmdColumnType.equalsIgnoreCase("INT")
				|| rsmdColumnType.equalsIgnoreCase("INT UNSIGNED")
				|| rsmdColumnType.equalsIgnoreCase("DECIMAL")
				|| (rsmdColumnType.toUpperCase().indexOf("INTEGER") >= 0)) {
			dataType = "int";
		}
		MyLog.log("Java type for " + rsmdColumnType + " is " + dataType);
		return dataType;

	}

	public String getInstances(ResultSetMetaData rsmd) {
		String text = "";
		try {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				text += "\r\n    private ";
				text += getJavaType(rsmd.getColumnTypeName(i)) + " ";
				text += rsmd.getColumnName(i) + ";";
			}
		} catch (SQLException ex) {
			MyLog.myCatch("DBDataStandAlone", 652, ex);
		}
		return text;
	}

	// public String getColumnName(ResultSetMetaData rsmd, int numOfColumn){
	// String columnName="";
	// try{
	// for(int i = 1; i <= numOfColumn; i++){
	// columnName = columnName+rsmd.getColumnName(i)+";"+
	// rsmd.getColumnTypeName(i)+";";
	// }
	// }catch(SQLException se){
	// MyLog.myCatch("/java", 522, se);
	// }
	// return columnName;
	// }
	static String row2text(ResultSet rs, boolean printColumnNames) {
		String result = "";
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (printColumnNames) {
				for (int i = 1; i <= columnCount; i++) {
					result += rsmd.getColumnName(i) + ", ";
				}
				result += "\r\n";
			}
			for (int i = 1; i <= columnCount; i++) {
				result = result + rs.getString(i) + ", ";
			}
		} catch (SQLException ex) {
			MyLog.myCatch("DBDataStandAlone", 968, ex);

		}
		return result;
	}
}
