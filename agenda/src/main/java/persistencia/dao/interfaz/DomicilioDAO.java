package persistencia.dao.interfaz;

import dto.DomicilioDTO;

public interface DomicilioDAO {
	public int insert(DomicilioDTO domicilio);
	
	public int update(DomicilioDTO domicilio);

	public int get(String calle, String altura, String piso, String tipo);
	
	public int delete(DomicilioDTO domicilio);

	DomicilioDTO get(int id);
}
