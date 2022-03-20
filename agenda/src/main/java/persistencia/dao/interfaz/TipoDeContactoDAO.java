package persistencia.dao.interfaz;

import java.util.List;

import dto.TipoDeContactoDTO;

public interface TipoDeContactoDAO {

	public boolean insert(TipoDeContactoDTO tipoDeContacto);

	public boolean delete(TipoDeContactoDTO tipoDeContacto_a_eliminar);

	public List<TipoDeContactoDTO> readAll();

	public TipoDeContactoDTO get(int idTipoContacto);
	
	public boolean update(TipoDeContactoDTO contacto_actualizar);
}
