package dto;

public class TipoDeContactoDTO 
{
	private int idTipoDeContacto;
	private String tipoDeContacto;
	public TipoDeContactoDTO(int idTipoDeContacto, String tipoDeContacto)
	{
		this.idTipoDeContacto = idTipoDeContacto;
		this.tipoDeContacto = tipoDeContacto;
	}
	public int getIdTipoDeContacto() {
		return idTipoDeContacto;
	}
	public void setIdTipoDeContacto(int idTipoDeContacto) {
		this.idTipoDeContacto = idTipoDeContacto;
	}
	public String getTipoDeContacto() {
		return tipoDeContacto;
	}
	public void setTipoDeContacto(String tipoDeContacto) {
		this.tipoDeContacto = tipoDeContacto;
	}
	
	
	

}
