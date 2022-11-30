package Main;

import java.sql.Connection;
import Funciones.Conexion;
import Funciones.Funciones;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = Conexion.conectar();
		Funciones.crearTabla(con);
		//Funciones.transaccion(con);
		Funciones.modificar(con);
		Funciones.borrar(con);
		//Funciones.puntoGuardado(con);
		Funciones.queryNormal(con);
		Funciones.queryNormalconwhere(con);
		Funciones.ProcedimientoAlmacenado(con);
		Funciones.FuncionAlmacenada(con);
		Conexion.desconectar();
	}

}
