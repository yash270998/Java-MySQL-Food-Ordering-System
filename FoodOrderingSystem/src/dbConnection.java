import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
	public static Connection con = null;
	private String IP = "127.0.0.1";
	private String portNumber = "3306";
	private String databaseName = "foodsystem";
	private String username = "root";
	private String password = "root";
	public void connecttoDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://"+IP+":"+portNumber+"/"+databaseName, username, password);
		if (con != null) {
//			System.out.println("connected !");
		}
	}

	public static Connection getConnection(){
		return con;
	}

	public void closeConnection() throws SQLException {
		if (con != null) {
			System.out.println("closing connection");
			con.close();
		}
	}
}
