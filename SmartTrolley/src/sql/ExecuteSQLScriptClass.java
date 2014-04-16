/**
 * SmartTrolley
 *
 * This file is meant to parse and a SQL file, which will create the smarttrolley database on localhost
 *
 * @author Prashant Chakravarty
 * @author Alick Jacklin
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.0 [Date Created: 14 Apr 2014]
 */

package sql;

//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import java.io.BufferedReader;
import java.io.InputStream;
//import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;

//import com.mysql.jdbc.Statement;
import java.sql.*;
import java.util.Scanner;

import Printing.SmartTrolleyPrint;

public class ExecuteSQLScriptClass {

	/**
	 * This method should contain all the code required to parse the SQL
	 * file and execute the SQL statements within it
	 * <p>Date Modified: 14 Apr 2014
	 * @param password 
	 * @param username 
	 * @return 
	 * @see http://www.coderanch.com/t/298527/JDBC/databases/executing-PL-SQL-JDBC AND http://www.tutorialspoint.com/jdbc/jdbc-create-database.htm AND http://stackoverflow.com/questions/1497569/how-to-execute-sql-script-file-using-jdbc
	 */
	public void ExecuteSQLScript(String username, String password) {
		final String smartTrolleySQLScript = "smarttrollySQL.sql";
		BufferedReader br = null;
		Connection dbConn = null;
		CallableStatement clblstmt = null;
		Statement stmt = null;
		final String url = "jdbc:mysql://localhost/";
		/*String line = null;
		String url = "???";
		StringBuffer sql = null;*/
		Scanner s = null;
		try {
			// ExecuteSQLScript me = new ExecuteSQLScript();
			Class<? extends ExecuteSQLScriptClass> meClass = this.getClass();
			InputStream is = meClass.getResourceAsStream(smartTrolleySQLScript);
			
			s = new Scanner(is);
			s.useDelimiter("(;(\r)?\n)|(--\n)");
			
				Class.forName("com.mysql.jdbc.Driver");

				SmartTrolleyPrint.smartTrolleyPrint("Connecting to database...");
				
				dbConn = DriverManager.getConnection(url, username,password);

				SmartTrolleyPrint.smartTrolleyPrint("Creating database...");
				stmt = dbConn.createStatement();
				
				while (s.hasNext())
				{
					String SQLline = s.next();
					if (SQLline.startsWith("/*!") && SQLline.endsWith("*/"))
					{
						int i = SQLline.indexOf(' ');
						SQLline = SQLline.substring(i + 1, SQLline.length() - " */".length());
					}

					if (SQLline.trim().length() > 0)
					{
				stmt.executeUpdate(SQLline);
				SmartTrolleyPrint.smartTrolleyPrint("Database created successfully...");
					}
			}
		} catch (Exception x) {
			System.err.println("Exception caught: " + x);
			x.printStackTrace();
		} finally {
			if (s != null){
				s.close();
			}
			if (br != null) {
				try {
					br.close();
				} catch (Exception x) {
					System.err.println("Failed to close resource.");
					x.printStackTrace();
				}
			}
			if (clblstmt != null) {
				try {
					clblstmt.close();
				} catch (Exception x) {
					System.err.println("Failed to close statement.");
					x.printStackTrace();
				}
			}
			if (dbConn != null) {
				try {
					dbConn.close();
				} catch (Exception x) {
					System.err.println("Failed to close DB connection.");
					x.printStackTrace();
				}
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
		}
		 SmartTrolleyPrint.smartTrolleyPrint("Goodbye from ExecuteSQLScript!");
	}

	/**
	 * Main method, starts a new instance of this class.
	 * <p> Test(s)/User Story that it satisfies
	 * 
	 * @param args
	 * <p> Date Modified: 14 Apr 2014
	 */
	public static void main(String[] args) {

		new ExecuteSQLScriptClass();
	}

}

/************** End of ExecuteSQLScript.java **************/

