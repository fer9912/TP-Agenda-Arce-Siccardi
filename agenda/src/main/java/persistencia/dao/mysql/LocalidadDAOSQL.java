package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOSQL implements LocalidadDAO{
	private static final String insert = "INSERT INTO localidad(idLocalidad, localidad, partido, codigo_postal) VALUES(?, ?, ?, ?)";
	private static final String delete = "DELETE FROM localidad WHERE idLocalidad = ?";
	private static final String readall = "SELECT * FROM localidad";
	private static final String getId = "SELECT * FROM localidad WHERE idLocalidad = ?";
	
	
	@Override
	public boolean insert(LocalidadDTO localidad) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, localidad.getIdLocalidad());
			statement.setString(2, localidad.getLocalidad());
			statement.setString(3, localidad.getPartido());
			statement.setString(4, localidad.getCodigoPostal());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}

	@Override
	public boolean delete(LocalidadDTO localidad_a_eliminar) {

		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(localidad_a_eliminar.getIdLocalidad()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	@Override
	public List<LocalidadDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				localidades.add(getLocalidadDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return localidades;
	}
	
	private LocalidadDTO getLocalidadDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idLocalidad");
		String localidad = resultSet.getString("Localidad");
		String partido = resultSet.getString("Partido");
		String codigoPostal = resultSet.getString("Codigo_Postal");
		return new LocalidadDTO(id, localidad, partido, codigoPostal);
	}

	@Override
	public LocalidadDTO get(int idLocalidad) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		List<LocalidadDTO> tiposDeContacto = new ArrayList<LocalidadDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getId);
			statement.setInt(1, idLocalidad);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				tiposDeContacto.add(getTipoDeContactoDTO(resultSet));
			}
			return tiposDeContacto.get(0);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;		
		}
	}
	private LocalidadDTO getTipoDeContactoDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idLocalidad");
		String localidad = resultSet.getString("Localidad");
		String partido = resultSet.getString("Partido");
		String codigoPostal = resultSet.getString("Codigo_Postal");
		return new LocalidadDTO(id, localidad, partido, codigoPostal);
	}
}
