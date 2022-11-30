package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521";
	private static String User = "EXAMEN";
	private static String Password = "EXAMEN";
	private static Connection con = null;
	
	public static Connection Conectar() {
		try {
			if (con==null || con.isClosed()) {
				Class.forName(driver);
				con = DriverManager.getConnection(url, User, Password);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void Desconectar() {
		try {
			if(con!=null && con.isClosed()) {
				con = null;
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
