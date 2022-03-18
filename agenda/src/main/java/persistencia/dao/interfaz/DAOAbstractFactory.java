package persistencia.dao.interfaz;


public interface DAOAbstractFactory 
{
	public PersonaDAO createPersonaDAO();
	public DomicilioDAO createDomicilioDAO();
	public LocalidadDAO createLocalidadDAO();
	public TipoDeContactoDAO createTipoDeContactoDAO();
}
