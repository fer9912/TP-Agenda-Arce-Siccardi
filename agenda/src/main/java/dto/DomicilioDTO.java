package dto;

public class DomicilioDTO 
{
	private int idDomicilio;
	private String calle;
	private String altura;
	private String piso;
	private String tipoDomicilio;

	public DomicilioDTO(int idDomicilio, String calle, String altura, String piso, String tipoD)
	{
		this.idDomicilio = idDomicilio;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.tipoDomicilio = tipoD;
	}
	
	public int getIdDomicilio() 
	{
		return this.idDomicilio;
	}


	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getTipoDomicilio() {
		return tipoDomicilio;
	}

	public void setTipoDomicilio(String tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}

}
