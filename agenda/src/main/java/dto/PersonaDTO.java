package dto;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private String email;
	private String fecha;
	private int domicilio;
	private DomicilioDTO domicilioDTO;

	public PersonaDTO(int idPersona, String nombre, String telefono, String email, String fecha, int domicilio)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.fecha = fecha;
		this.domicilio = domicilio;
	}
	
	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	public int getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(int domicilio) {
		this.domicilio = domicilio;
	}

	public DomicilioDTO getDomicilioDTO() {
		return domicilioDTO;
	}

	public void setDomicilioDTO(DomicilioDTO domicilioDTO) {
		this.domicilioDTO = domicilioDTO;
	}
	
	
}
