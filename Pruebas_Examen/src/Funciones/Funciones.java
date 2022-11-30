package Funciones;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Types;

public class Funciones {

	public static int crearTabla(Connection con) {
		String sql = "create table jugadores\n" + "    (id number,\n" + "    nombre varchar(50),\n"
				+ "    profesion varchar(50),\n" + "    edad number\n" + "    ,CONSTRAINT id_pk PRIMARY key (id))";
		PreparedStatement ps;
		int resultado = 0;

		try {
			ps = con.prepareStatement(sql);
			resultado = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Tabla creada1");
		}
		return resultado;
	}

	public static void transaccion(Connection con) {
		PreparedStatement ps;
		try {
			con.setAutoCommit(false);
			int id = 1;
			String nombre = "pepe";
			String profesion = "furbo";
			int edad = 45;
			String sql = "insert into jugadores (id, nombre, profesion, edad) values (?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setString(3, profesion);
			ps.setInt(4, edad);
			ps.executeUpdate();

			String sql2 = "insert into jugadores (id, nombre, profesion, edad) values ('2', 'jose', 'baloncesto', '25')";
			ps = con.prepareStatement(sql2);
			ps.executeUpdate();

			int id2 = 3;
			String nombre2 = "juan";
			String profesion2 = "jugador de lol";
			int edad2 = 34;
			String sql3 = "insert into jugadores (id, nombre, profesion, edad) values (?, ?, ?, ?)";
			ps = con.prepareStatement(sql3);
			ps.setInt(1, id2);
			ps.setString(2, nombre2);
			ps.setString(3, profesion2);
			ps.setInt(4, edad2);
			ps.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void modificar(Connection con) {
		PreparedStatement ps;
		String sql = "UPDATE jugadores SET nombre = ? where id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "alberto");
			ps.setInt(2, 1);
			System.out.println("filas modificadas: " + ps.executeUpdate());
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void borrar(Connection con) {
		PreparedStatement ps;
		String sql = "delete from jugadores where id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, 2);
			System.out.println("filas borradas: " + ps.executeUpdate());
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void puntoGuardado(Connection con) {
		PreparedStatement ps;
		try {
			con.setAutoCommit(false);
			int id = 1;
			String nombre = "pepe";
			String profesion = "furbo";
			int edad = 45;
			String sql = "insert into jugadores (id, nombre, profesion, edad) values (?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setString(3, profesion);
			ps.setInt(4, edad);
			ps.executeUpdate();

			Savepoint guardar = con.setSavepoint();
			String sql2 = "insert into jugadores (id, nombre, profesion, edad) values ('2', 'jose', 'baloncesto', '25')";
			ps = con.prepareStatement(sql2);
			ps.executeUpdate();
			// guardar = con.setSavepoint();
			con.rollback(guardar);

			int id2 = 3;
			String nombre2 = "juan";
			String profesion2 = "jugador de lol";
			int edad2 = 34;
			String sql3 = "insert into jugadores (id, nombre, profesion, edad) values (?, ?, ?, ?)";
			ps = con.prepareStatement(sql3);
			ps.setInt(1, id2);
			ps.setString(2, nombre2);
			ps.setString(3, profesion2);
			ps.setInt(4, edad2);
			ps.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void queryNormal(Connection con) {
		PreparedStatement ps;
		String sql = "select * from jugadores";
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void queryNormalconwhere(Connection con) {
		PreparedStatement ps;
		String sql = "select * from jugadores where id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ProcedimientoAlmacenado(Connection con) {
		CallableStatement cs;
		String sql = "{call PROCEDIMIENTO(?,?)}";
		
		try {
			cs = con.prepareCall(sql);
			cs.setString(1, "juan");
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.execute();
			String algo = cs.getString(2);
			System.out.println(algo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void FuncionAlmacenada(Connection con) {
		CallableStatement cs;
		String sql = "{? = call FUNCIONALMACENADA()}";
		
		try {
			cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			String algo = cs.getString(1);
			System.out.println(algo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
