package persistencia.conexion;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion 
{
	public static Conexion instancia;
	private Connection connection;
	private Logger log = LogManager.getLogger(Conexion.class);	
	
	private Conexion()
	{		
		try
		{
			FileInputStream file = new FileInputStream("config.txt");
		    Scanner txt = new Scanner(file);
		    String conexion = txt.nextLine();
			String user = txt.nextLine();
			String pass = txt.nextLine();
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario
			this.connection = DriverManager.getConnection("jdbc:mysql://"+conexion,user,pass);
			this.connection.setAutoCommit(false);
			log.info("Conexiﾃｳn exitosa");
		}
		catch(Exception e)
		{
			log.error("Conexiﾃｳn fallida : ", e);
		}
	}
	
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
			log.info("Conexion cerrada");
		}
		catch (SQLException e) 
		{
			log.error("Error al cerrar la conexiﾃｳn!", e);
		}
		instancia = null;
	}
}
