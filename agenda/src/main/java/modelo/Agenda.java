package modelo;

import java.util.List;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoDeContactoDAO;


public class Agenda 
{
	private PersonaDAO persona;	
	private DomicilioDAO domicilio;
	private LocalidadDAO localidad;
	private TipoDeContactoDAO tipoDeContacto;
	
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.domicilio = metodo_persistencia.createDomicilioDAO();
		this.localidad = metodo_persistencia.createLocalidadDAO();
		this.tipoDeContacto = metodo_persistencia.createTipoDeContactoDAO();
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona)
	{
		this.persona.insert(nuevaPersona);
	}
	
	public void actualizarPersona(PersonaDTO personaActualizada) {
		
		this.persona.update(personaActualizada);
	}
	

	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		this.persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return this.persona.readAll();		
	}
	
	public List<LocalidadDTO> obtenerLocalidades()
	{
		return this.localidad.readAll();		
	}
	
	public void agregarLocalidad(LocalidadDTO nuevaLocalidad)
	{
		this.localidad.insert(nuevaLocalidad);
	}

	public void borrarLocalidad(LocalidadDTO localidad_a_eliminar) 
	{
		this.localidad.delete(localidad_a_eliminar);
	}
	
	public void actualizarLocalidad(LocalidadDTO localidad) {
		this.localidad.update(localidad);
	}


	public int agregarDomicilio(DomicilioDTO nuevoDomicilio) {
		return this.domicilio.insert(nuevoDomicilio);		
	}
	
	public int borrarDomicilio(DomicilioDTO domicilio) {
		return this.domicilio.delete(domicilio);
	}
	
	public int actualizarDomicilio(DomicilioDTO Domicilio) {
		return this.domicilio.update(Domicilio);
	}

	public DomicilioDTO getDomicilio(int idDomicilio) {
		return this.domicilio.get(idDomicilio);
		
	}
	
	public List<TipoDeContactoDTO> obtenerTiposDeContacto()
	{
		return this.tipoDeContacto.readAll();		
	}
	
	public void agregarTipoDeContacto(TipoDeContactoDTO nuevoTipo)
	{
		this.tipoDeContacto.insert(nuevoTipo);
	}
	
	public void actualizarTipoDeContacto(TipoDeContactoDTO tipo_actualizar) {
		this.tipoDeContacto.update(tipo_actualizar);
	}

	public void borrarTipoDeContacto(TipoDeContactoDTO tipoDeContacto_a_eliminar) 
	{
		this.tipoDeContacto.delete(tipoDeContacto_a_eliminar);
	}

	public TipoDeContactoDTO getTipoContacto(int idTipoContacto) {
		return this.tipoDeContacto.get(idTipoContacto);
	}

	public LocalidadDTO getLocalidad(int localidad) {
		return this.localidad.get(localidad);
	}

	
}
