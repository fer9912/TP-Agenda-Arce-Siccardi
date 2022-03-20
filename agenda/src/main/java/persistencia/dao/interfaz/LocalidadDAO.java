package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadDAO {

	
	public boolean insert(LocalidadDTO localidad);

	public boolean delete(LocalidadDTO localidad_a_eliminar);
	
	public List<LocalidadDTO> readAll();

	public LocalidadDTO get(int idTipoContacto);
	
	public boolean update(LocalidadDTO localidad_actualizar);

}
