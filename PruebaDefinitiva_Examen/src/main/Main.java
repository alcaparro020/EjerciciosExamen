package main;

import java.sql.Connection;

import Conexion.Conexion;
import Funciones.Funciones;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = Conexion.Conectar();
		Funciones.CrearTabla(con);
		Funciones.InsertarJugadores(con);
		Funciones.MostrarTodosLosJugadores(con);
		Conexion.Desconectar();
	}

}
