package smarttrolleygui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Thomas & Sam
 * Inspired from Alasdair's spike
 *
 */
public class SqlConnection {

	private String ip;
	private String userName;
	private String password;
	
	private String url;
	Connection connection;
	
	/**
	 * Method that receives relevant connection info
	 * @param ip
	 * @param userName
	 * @param password
	 */
	public SqlConnection(String ip, String userName, String password) {
		
		try {
			Class.forName("java.sql.DriverManager");
		} catch (ClassNotFoundException e) {
			System.out.println("couldnt launch sql driver");
		}

		//set essential properties
		this.ip = ip;
		this.userName = userName;
		this.password = password;

		compileUrl();		
	}

	/**
	 * Opens a connection (if not already open) for sending mysql queries to the database
	 * @throws SQLException
	 */
	public void openConnection(){	

		try{
			connection = null;
			connection = DriverManager.getConnection(url, userName,password);
		} catch (SQLException ex) {
			
			System.out.println("Connection failed to open");
			
		}
	}
	
	/**
	 * Send a query to the predefined database and returns the results returned by the server
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet sendQuery(String query) throws SQLException {
		
		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery(query);
		
		return results;
	}
	
	/**
	 * provides public access to close the connection
	 * @throws SQLException
	 */
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException ex) {
			System.out.println("Connection did not close properly");
		}
	}
	
	/**
	 * Compiles a valid URL from the connection info provided
	 */
	private void compileUrl() {
		//construct the url assuming use of mysql and the standard port.
		url = "jdbc:mysql://" + ip  + "/" + userName + "?";	
	}

	
}
