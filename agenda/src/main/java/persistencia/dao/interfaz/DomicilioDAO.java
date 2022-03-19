package persistencia.dao.interfaz;

import dto.DomicilioDTO;

public interface DomicilioDAO {
	public int insert(DomicilioDTO domicilio);
	
	public int update(DomicilioDTO domicilio);

	public int get(String calle, String altura, String piso, String tipo);

	DomicilioDTO get(int id);
}
