package br.com.moraes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlAccess {

	private static Connection connect = null;

	private MySqlAccess() {}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connect == null) {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/festa?user=root&password=123456");
		}
		return connect;
	}
}
