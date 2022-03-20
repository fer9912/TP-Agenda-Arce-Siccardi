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
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DomicilioDAO;

public class DomicilioDAOSQL implements DomicilioDAO{
	private static final String insert = "INSERT INTO domicilio(idDomicilio, calle, altura, piso, tipo_domicilio, localidad ) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String getD = "SELECT d.idDomicilio FROM domicilio d WHERE d.Calle = ? AND d.Altura = ? AND d.Piso = ? AND d.Tipo_Domicilio = ?";
	private static final String getId = "SELECT d.* FROM domicilio d WHERE d.idDomicilio = ?";
	private static final String update = "UPDATE domicilio d set calle = ? , altura = ? , piso = ? , tipo_domicilio = ? , localidad = ? where d.idDomicilio = ?";
	private static final String delete = "DELETE FROM domicilio where idDomicilio = ? ";
	private Logger log = LogManager.getLogger(DomicilioDAOSQL.class);	
	
	@Override
	public int insert(DomicilioDTO domicilio) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int idDomicilio = 0;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, domicilio.getIdDomicilio());
			statement.setString(2, domicilio.getCalle());
			statement.setString(3, domicilio.getAltura());
			statement.setString(4, domicilio.getPiso());
			statement.setString(5, domicilio.getTipoDomicilio());
			statement.setInt(6, domicilio.getLocalidad());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				idDomicilio = get(domicilio.getCalle(), domicilio.getAltura(), domicilio.getPiso(), domicilio.getTipoDomicilio());
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
		
		return idDomicilio;
	}
	
	@Override
	public int delete(DomicilioDTO domicilio) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int idDomicilio = domicilio.getIdDomicilio();
		try
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, domicilio.getIdDomicilio());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
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
		
		return idDomicilio;
	}
	
	@Override
	public int update(DomicilioDTO domicilio) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int idDomicilio = 0;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, domicilio.getCalle());
			statement.setString(2, domicilio.getAltura());
			statement.setString(3, domicilio.getPiso());
			statement.setString(4, domicilio.getTipoDomicilio());
			statement.setInt(5, domicilio.getLocalidad());
			statement.setInt(6, domicilio.getIdDomicilio());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				idDomicilio = get(domicilio.getCalle(), domicilio.getAltura(), domicilio.getPiso(), domicilio.getTipoDomicilio());
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
		
		return idDomicilio;
	}


	@Override
	public int get(String calle, String altura, String piso, String tipo) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		int idDomicilio = 0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getD);
			statement.setString(1, calle);
			statement.setString(2, altura);
			statement.setString(3, piso);
			statement.setString(4, tipo);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				idDomicilio = resultSet.getInt("idDomicilio");
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return idDomicilio;
	}
	
	@Override
	public DomicilioDTO get(int id) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		List<DomicilioDTO> domicilios = new ArrayList<DomicilioDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getId);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				domicilios.add(getDomicilioDTO(resultSet));
			}
			return domicilios.get(0);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;		
		}
	}
	
	private DomicilioDTO getDomicilioDTO(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("idDomicilio");
		String calle = resultSet.getString("Calle");
		String altura = resultSet.getString("Altura");
		String piso = resultSet.getString("Piso");
		String tipo = resultSet.getString("Tipo_Domicilio");
		int localidad = resultSet.getInt("Localidad");
		return new DomicilioDTO(id, calle, altura, piso, tipo, localidad);
	}
}
