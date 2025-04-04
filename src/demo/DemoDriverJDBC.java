package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoDriverJDBC {

	public static void useDB(Connection con, String db) throws SQLException {
		Statement stmt = con.createStatement();
		stmt.execute("use mydb");
		stmt.close();
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/";
		Connection con = DriverManager.getConnection(url, "root", "secret");
		System.out.println(con.isClosed());

		useDB(con, "mydb");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM MyData");

		while (rs.next()) {
			String data = rs.getString("mycol");
			System.out.println(data);
		}

		stmt.close();
		System.out.println(con.isClosed());
	}
}
