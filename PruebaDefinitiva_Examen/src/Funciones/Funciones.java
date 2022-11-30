package Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Funciones {

	public static void CrearTabla(Connection con) {
		int resultado = 0;
		String sql = "CREATE TABLE JUGADORES \n"
				+ "(ID NUMBER,\n"
				+ "NOMBRE VARCHAR(50),\n"
				+ "PROFESION VARCHAR(50),\n"
				+ "EDAD NUMBER,\n"
				+ "CONSTRAINT ID_PK PRIMARY KEY (ID))";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			resultado = ps.executeUpdate();
			ps.close();
			System.out.println("filas creadas: " + resultado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Tabla ya creada");
		}
	}
	
	public static void InsertarJugadores(Connection con) {
		int resultado1 = 0;
		int resultado2 = 0;
		int resultado3 = 0;
		String sql1 = "INSERT INTO JUGADORES (ID, NOMBRE, PROFESION, EDAD) VALUES (?, ?, ?, ?)";
		String sql2 = "INSERT INTO JUGADORES (ID, NOMBRE, PROFESION, EDAD) VALUES (?, ?, ?, ?)";
		String sql3 = "INSERT INTO JUGADORES (ID, NOMBRE, PROFESION, EDAD) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql1);
			ps.setInt(1, 1);
			ps.setString(2, "pepe");
			ps.setString(3, "albanil");
			ps.setInt(4, 43);
			resultado1 = ps.executeUpdate();
			
			ps = con.prepareStatement(sql2);
			ps.setInt(1, 2);
			ps.setString(2, "alberto");
			ps.setString(3, "jefe construccion");
			ps.setInt(4, 36);
			resultado2 = ps.executeUpdate();
			
			ps = con.prepareStatement(sql3);
			ps.setInt(1, 3);
			ps.setString(2, "cristina");
			ps.setString(3, "de hacienda");
			ps.setInt(4, 28);
			resultado3 = ps.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
			ps.close();
			System.out.println("filas creadas: " + resultado1);
			System.out.println("filas creadas: " + resultado2);
			System.out.println("filas creadas: " + resultado3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Jugadores ya insertados");
		}
	}
	public static void MostrarTodosLosJugadores(Connection con) {
		ResultSet rs = null;
		String sql = "SELECT * FROM JUGADORES";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("jugador con id: " + rs.getInt(1) + ", con el nombre: " + rs.getString(2)
				+ ", con la profesion de: " + rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
