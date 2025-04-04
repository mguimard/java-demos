package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver chargé");
		} catch (ClassNotFoundException e) {
			System.out.println("Ooops, driver pas chargé");
		}
	}

	private static Connection connect() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "secret");
		System.out.println(con.getMetaData().getDatabaseProductName());
		System.out.println(con.getMetaData().getDatabaseMajorVersion());
		return con;
	}

	private static void useDB(Connection c, String dbName) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.execute("use " + dbName);
	}

	public static void main(String[] args) throws SQLException {
		Connection con = connect();
		useDB(con, "mydb");

		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery("SELECT * FROM MyData");

		rs.afterLast();
		rs.previous();
		String col = rs.getString("mycol");
		System.out.println(col);

		rs.beforeFirst();
		rs.next();
		rs.next();
		rs.previous();

		String s = rs.getString("mycol");
		System.out.println(s);

		con.close();
		stmt.close();
		rs.close();

		// rs.next();
	}

	public static void main8(String[] args) throws SQLException {
		Connection con = connect();
		useDB(con, "mydb");

		/*
		 * +-------+ | mycol | +-------+ | A | | B | | C | | D | | 1234 | +-------+
		 */
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery("SELECT * FROM MyData");

		System.out.println(rs.getRow());

		rs.setFetchDirection(ResultSet.FETCH_REVERSE);

		rs.next();
		rs.relative(2);
		String s = rs.getString("mycol");
		System.out.println(s);

	}

	public static void main6(String[] args) throws SQLException {
		Connection con = connect();
		useDB(con, "mydb");

		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery("SELECT * FROM MyData");

		while (rs.next()) {
			String s = rs.getString("mycol");
			System.out.println(s);
		}

	}

	public static void main4(String[] args) throws SQLException {
		Connection con = connect();
		useDB(con, "mydb");

		Statement stmt = con.createStatement();
		int rowsAdded = stmt.executeUpdate("INSERT INTO MyData VALUES(1234)");

		System.out.println("Added rows: " + rowsAdded);

	}

	public static void main3(String[] args) throws SQLException {
		Connection con = connect();
		useDB(con, "mydb");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM MyData");

		while (rs.next()) {
			int i = rs.getInt("mycol");
			System.out.println(i);
		}

	}

	public static void main2(String[] args) throws SQLException {
		Connection con = connect();
		useDB(con, "mydb");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM MyData");

		while (!stmt.isClosed() && rs.next()) {
			String s = rs.getString("mycol");
			System.out.println(s);
			stmt.close();
		}

	}
}
