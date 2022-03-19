package dto;

public class LocalidadDTO 
{
	private int idLocalidad;
	private String nombreLocalidad;
	private String partido;
	private String codigoPostal;
	public LocalidadDTO(int idLocalidad, String localidad, String partido, String codigoPostal)
	{
		this.idLocalidad = idLocalidad;
		this.nombreLocalidad = localidad;
		this.partido = partido;
		this.codigoPostal = codigoPostal;
	}
	
	public int getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}
	public void setLocalidad(String localidad) {
		this.nombreLocalidad = localidad;
	}
	public String getPartido() {
		return partido;
	}
	public void setPartido(String partido) {
		this.partido = partido;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	

}
