package dto;

public class LocalidadDTO 
{
	private int idLocalidad;
	private String nombreLocalidad;
	private String provincia;
	private String pais;
	public LocalidadDTO(int idLocalidad, String localidad, String provincia, String pais)
	{
		this.idLocalidad = idLocalidad;
		this.nombreLocalidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
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
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String  pais) {
		this.pais = pais;
	}
	

}
