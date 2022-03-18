package persistencia.dao.interfaz;


public interface DAOAbstractFactory 
{
	public PersonaDAO createPersonaDAO();

	public DomicilioDAO createDomicilioDAO();
}
