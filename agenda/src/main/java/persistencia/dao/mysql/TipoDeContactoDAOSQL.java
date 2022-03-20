package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DomicilioDTO;
import dto.TipoDeContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoDeContactoDAO;

public class TipoDeContactoDAOSQL implements TipoDeContactoDAO {
	private static final String insert = "INSERT INTO tipo_contacto(idTipo_contacto, tipo_contacto) VALUES(?, ?)";
	private static final String delete = "DELETE FROM tipo_contacto WHERE idTipo_contacto = ?";
	private static final String readall = "SELECT * FROM tipo_contacto";
	private static final String getId = "SELECT * FROM tipo_contacto WHERE idTipo_contacto = ?";
    private static final String update = "UPDATE tipo_contacto t set tipo_contacto = ? where t.idTipo_contacto = ?";
	
	@Override
	public boolean insert(TipoDeContactoDTO tipoDeContacto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, tipoDeContacto.getIdTipoDeContacto());
			statement.setString(2, tipoDeContacto.getTipoDeContacto());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
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
	public boolean update(TipoDeContactoDTO tipoDeContacto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, tipoDeContacto.getTipoDeContacto());
			statement.setInt(2, tipoDeContacto.getIdTipoDeContacto());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return isUpdateExitoso;
	}


	@Override
	public boolean delete(TipoDeContactoDTO tipoDeContacto_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(tipoDeContacto_a_eliminar.getIdTipoDeContacto()));
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	@Override
	public List<TipoDeContactoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TipoDeContactoDTO> tiposDeContacto = new ArrayList<TipoDeContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				tiposDeContacto.add(getTipoDeContactoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiposDeContacto;
	}

	private TipoDeContactoDTO getTipoDeContactoDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idTipo_Contacto");
		String tipo = resultSet.getString("Tipo_Contacto");
		return new TipoDeContactoDTO(id, tipo);
	}

	@Override
	public TipoDeContactoDTO get(int id) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		List<TipoDeContactoDTO> tiposDeContacto = new ArrayList<TipoDeContactoDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getId);
			statement.setInt(1, id);
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
}
