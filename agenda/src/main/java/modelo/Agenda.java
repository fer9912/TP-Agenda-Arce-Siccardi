package modelo;

import java.util.List;

import dto.DomicilioDTO;
import dto.PersonaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.PersonaDAO;


public class Agenda 
{
	private PersonaDAO persona;	
	private DomicilioDAO domicilio;
	
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.domicilio = metodo_persistencia.createDomicilioDAO();
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona)
	{
		this.persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		this.persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return this.persona.readAll();		
	}

	public int agregarDomicilio(DomicilioDTO nuevoDomicilio) {
		return this.domicilio.insert(nuevoDomicilio);		
	}

	public DomicilioDTO getDomicilio(int idDomicilio) {
		return this.domicilio.get(idDomicilio);
		
	}
	
}
