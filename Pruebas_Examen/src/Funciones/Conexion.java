package Funciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	/**
	 * Variables
	 */
	private static String driver;
	private static String url;
	private static String User;
	private static String Password;
	private static Connection con;

	public static Connection conectar() {
		driver = "oracle.jdbc.driver.OracleDriver";
		url = "jdbc:oracle:thin:@localhost:1521";
		User = "EXAMEN";
		Password = "EXAMEN";
		con = null;
		try {
			if (con == null || con.isClosed()) {
				Class.forName(driver);
				con = DriverManager.getConnection(url, User, Password);
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public static void desconectar() {
		try {
			if (con != null && con.isClosed()) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
