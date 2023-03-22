package DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {

	public void getJDBCConnection(String dbname) {

		String url = "jdbc:mysql://localhost:3306" + "/" + dbname;
		String username = "root";
		String password = "root";

		try {
			Connection con = DriverManager.getConnection(url, username, password);

			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from emp");

			while (rs.next()) {
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("name"));
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
