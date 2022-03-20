package presentacion.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import modelo.Agenda;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DAOAbstractFactory;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaEditarLocalidad;
import presentacion.vista.VentanaEditarPersona;
import presentacion.vista.VentanaEditarTipoDeContacto;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaTipoDeContacto;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personasEnTabla;
	private List<LocalidadDTO> localidadesEnTabla;
	private List<TipoDeContactoDTO> tipoDeContactoEnTabla;
	private VentanaPersona ventanaPersona;
	private VentanaEditarPersona ventanaEditarPersona;
	private VentanaLocalidad ventanaLocalidad;
	private VentanaTipoDeContacto ventanaTipoDeContacto;
	private VentanaEditarLocalidad ventanaEditarLocalidad;
	private VentanaEditarTipoDeContacto ventanaEditarTipoDeContacto;
	private PersonaDTO personaSeleccionada;
	private LocalidadDTO localidadSeleccionada;
	private TipoDeContactoDTO tipoDeContactoSeleccionado;
	private Agenda agenda;
	private Logger log = LogManager.getLogger(Conexion.class);

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.agenda = agenda;
		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregar(a));
		this.vista.getBtnBorrar().addActionListener(s -> borrar(s));
		this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));
		this.vista.getBtnEditar().addActionListener(e -> ventanaEditar(e));
		this.vista.getBtnVerAgenda().addActionListener(c -> cargarTablaPersonas(c));
		this.vista.getBtnLocalidades().addActionListener(c -> cargarTablaLocalidades(c));
		this.vista.getBtnVerTipoDeContacto().addActionListener(c -> cargarTablaTipoDeContacto(c));
		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(p -> guardar(p));
		this.ventanaEditarPersona = VentanaEditarPersona.getInstance();
		this.ventanaEditarPersona.getBtnGuardarPersona().addActionListener(g -> guardarCambios(g));
		this.ventanaLocalidad = VentanaLocalidad.getInstance();
		this.ventanaTipoDeContacto = VentanaTipoDeContacto.getInstance();
		this.ventanaEditarLocalidad = VentanaEditarLocalidad.getInstance();
		this.ventanaEditarTipoDeContacto = VentanaEditarTipoDeContacto.getInstance();
		this.ventanaLocalidad.getBtnAgregarLocalidad().addActionListener(l -> guardar(l));
		this.ventanaTipoDeContacto.getBtnAgregarTipoDeContacto().addActionListener(t -> guardar(t));
		this.ventanaEditarLocalidad.getBtnEditarLocalidad().addActionListener(l -> guardarCambios(l));
		this.ventanaEditarTipoDeContacto.getBtnEditarTipoDeContacto().addActionListener(t -> guardarCambios(t));
	}

	private void ventanaAgregar(ActionEvent a) {

		if (this.vista.getVistaSeleccionada() == 1) {
			this.ventanaPersona.llenarTiposDeContacto(agenda.obtenerTiposDeContacto());
			this.ventanaPersona.llenarLocalidades(agenda.obtenerLocalidades());
			this.ventanaPersona.mostrarVentana();
		}

		if (this.vista.getVistaSeleccionada() == 2) {
			this.ventanaLocalidad.mostrarVentana();
		}

		if (this.vista.getVistaSeleccionada() == 3) {

			this.ventanaTipoDeContacto.mostrarVentana();

		}
	}

	public void ventanaEditar(ActionEvent e) {
		if (this.vista.getTablaPersonas() != null && this.vista.getTablaPersonas().getSelectedRow() != -1) {

			int filaSeleccionada = this.vista.getTablaPersonas().getSelectedRow();

			if (this.vista.getVistaSeleccionada() == 1) {
				
				personaSeleccionada = this.personasEnTabla.get(filaSeleccionada);

				this.ventanaEditarPersona.llenarInfoPersona(personaSeleccionada, agenda.obtenerTiposDeContacto(),
						agenda.obtenerLocalidades());

				this.ventanaEditarPersona.mostrarVentana();
			}

			if (this.vista.getVistaSeleccionada() == 2) {

				localidadSeleccionada = this.localidadesEnTabla.get(filaSeleccionada);
				
				this.ventanaEditarLocalidad.llenarInfoLocalidad(localidadSeleccionada);
				
				this.ventanaEditarLocalidad.mostrarVentana();

			}

			if (this.vista.getVistaSeleccionada() == 3) {
               
				tipoDeContactoSeleccionado = this.tipoDeContactoEnTabla.get(filaSeleccionada);
				
				this.ventanaEditarTipoDeContacto.llenarInfoTipoDeContacto(tipoDeContactoSeleccionado);
				
				this.ventanaEditarTipoDeContacto.mostrarVentana();
			}
		}

	}

	private void guardar(ActionEvent p) {

		if (this.vista.getVistaSeleccionada() == 1) {
			if (validateTextField(ventanaPersona.getEmail(), "email")
					&& validateTextField(ventanaPersona.getFechaC(), "date")) {
				String nombre = this.ventanaPersona.getTxtNombre().getText();
				String tel = ventanaPersona.getTxtTelefono().getText();
				String email = this.ventanaPersona.getEmail().getText();
				String fecha = ventanaPersona.getFechaC().getText();
				int domicilio = this.guardarDomicilio();
				int tipo = Integer.parseInt(
						this.ventanaPersona.getTipoDeContactos().getSelectedItem().toString().substring(0, 1));
				PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel, email, fecha, domicilio, tipo);
				this.agenda.agregarPersona(nuevaPersona);
				log.info(nuevaPersona.toString());
				this.refrescarTabla();
				this.ventanaPersona.cerrar();
			}
		}

		if (this.vista.getVistaSeleccionada() == 2) {
			
          if(this.ventanaLocalidad.getLocalidad().getText().trim().isEmpty()) {
        	  return;
          }
          
          String localidad = ventanaLocalidad.getLocalidad().getText();
          String provincia = ventanaLocalidad.getProvincia().getText();
          String pais = ventanaLocalidad.getPais().getText();
          
          LocalidadDTO nuevaLocalidad = new LocalidadDTO(0,localidad,provincia,pais);
          this.agenda.agregarLocalidad(nuevaLocalidad);
          log.info(nuevaLocalidad.toString());
          this.refrescarTablaLocalidades();
          this.ventanaLocalidad.cerrar();
		}

		if (this.vista.getVistaSeleccionada() == 3) {
         if(this.ventanaTipoDeContacto.getTipoDeContacto().getText().trim().isEmpty()) {
        	 return;
         }
			String tipoDeContacto = ventanaTipoDeContacto.getTipoDeContacto().getText();
			
			TipoDeContactoDTO nuevoTipo = new TipoDeContactoDTO(0, tipoDeContacto);
			this.agenda.agregarTipoDeContacto(nuevoTipo);
			log.info(nuevoTipo.toString());
			this.refrescarTablaTiposDeContacto();
			this.ventanaTipoDeContacto.cerrar();
         
		}
	}

	private void guardarCambios(ActionEvent g) {

		if (this.vista.getVistaSeleccionada() == 1) {
			if (validateTextField(ventanaEditarPersona.getEmail(), "email")
					&& validateTextField(ventanaEditarPersona.getFechaC(), "date")) {
				String nombre = this.ventanaEditarPersona.getTxtNombre().getText();
				String tel = ventanaEditarPersona.getTxtTelefono().getText();
				String email = this.ventanaEditarPersona.getEmail().getText();
				String fecha = ventanaEditarPersona.getFechaC().getText();
				int domicilio = this.guardarCambiosDomicilio(1);
				int tipo = Integer.parseInt(
						this.ventanaEditarPersona.getTipoDeContactos().getSelectedItem().toString().substring(0, 1));
				PersonaDTO personaActualizada = new PersonaDTO(personaSeleccionada.getIdPersona(), nombre, tel, email,
						fecha, domicilio, tipo);
				this.agenda.actualizarPersona(personaActualizada);
				log.info(personaActualizada.toString());
				this.refrescarTabla();
				this.ventanaEditarPersona.cerrar();
			}
		}

		if (this.vista.getVistaSeleccionada() == 2) {
			if(this.ventanaEditarLocalidad.getLocalidad().getText().trim().isEmpty()) {
	        	  return;
	          }
			
	          String localidad = ventanaEditarLocalidad.getLocalidad().getText();
	          String provincia = ventanaEditarLocalidad.getProvincia().getText();
	          String pais = ventanaEditarLocalidad.getPais().getText();
	          
	          LocalidadDTO localidadActualizada = new LocalidadDTO(localidadSeleccionada.getIdLocalidad(),localidad,provincia,pais);
	          this.agenda.actualizarLocalidad(localidadActualizada);
	          this.refrescarTablaLocalidades();
	          this.ventanaEditarLocalidad.cerrar();
			
		}

		if (this.vista.getVistaSeleccionada() == 3) {
			if(this.ventanaEditarTipoDeContacto.getTipoDeContacto().getText().trim().isEmpty()) {
	        	 return;
	         }
			
			String tipo = ventanaEditarTipoDeContacto.getTipoDeContacto().getText();
			TipoDeContactoDTO tipoNuevo = new TipoDeContactoDTO(tipoDeContactoSeleccionado.getIdTipoDeContacto(), tipo);
			this.agenda.actualizarTipoDeContacto(tipoNuevo);
			this.refrescarTablaTiposDeContacto();
			this.ventanaEditarTipoDeContacto.cerrar();
			
			
		}
	}

	private int guardarDomicilio() {
		String calle = this.ventanaPersona.getCalle().getText();
		String altura = ventanaPersona.getAltura().getText();
		String tipoD = this.ventanaPersona.getTipoDomicilio().getText();
		String piso = ventanaPersona.getPiso().getText();
		int localidad = Integer
				.parseInt(this.ventanaPersona.getLocalidad().getSelectedItem().toString().substring(0, 1));
		DomicilioDTO nuevoDomicilio = new DomicilioDTO(0, calle, altura, piso, tipoD, localidad);
		return this.agenda.agregarDomicilio(nuevoDomicilio);

	}

	private int guardarCambiosDomicilio(int id) {
		String calle = this.ventanaEditarPersona.getCalle().getText();
		String altura = ventanaEditarPersona.getAltura().getText();
		String tipoD = this.ventanaEditarPersona.getTipoDomicilio().getText();
		String piso = ventanaEditarPersona.getPiso().getText();
		int localidad = Integer
				.parseInt(this.ventanaEditarPersona.getLocalidad().getSelectedItem().toString().substring(0, 1));
		DomicilioDTO Domicilio = new DomicilioDTO(id, calle, altura, piso, tipoD, localidad);
		return this.agenda.actualizarDomicilio(Domicilio);

	}

	private void mostrarReporte(ActionEvent r) {
		ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
		reporte.mostrar();
	}

	public void borrar(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		
		if (this.vista.getVistaSeleccionada() == 1) {
			
			for (int fila : filasSeleccionadas) {
				this.agenda.borrarPersona(this.personasEnTabla.get(fila));
			}
			this.refrescarTabla();
		}

		if (this.vista.getVistaSeleccionada() == 2) {

			for (int fila : filasSeleccionadas) {
				this.agenda.borrarLocalidad(this.localidadesEnTabla.get(fila));
			}
			this.refrescarTablaLocalidades();
		}

		if (this.vista.getVistaSeleccionada() == 3) {
			
			for (int fila : filasSeleccionadas) {
				this.agenda.borrarTipoDeContacto(this.tipoDeContactoEnTabla.get(fila));
			}
			this.refrescarTablaTiposDeContacto();

		}

	}

	public void cargarTablaPersonas(ActionEvent c) {

		this.vista.setVistaSeleccionada(1);

		this.vista.getBtnAgregar().setText("Agregar Persona");

		this.vista.getBtnEditar().setText("Editar Persona");

		this.vista.getBtnBorrar().setText("Borrar Persona");

		this.vista.cargarTablaPersonas();

		refrescarTabla();
	}

	public void cargarTablaLocalidades(ActionEvent c) {

		this.vista.setVistaSeleccionada(2);

		this.vista.getBtnAgregar().setText("Agregar Localidad");

		this.vista.getBtnEditar().setText("Editar Localidad");

		this.vista.getBtnBorrar().setText("Borrar Localidad");

		this.vista.getBtnReporte().setVisible(false);

		this.vista.cargarTablaLocalidades();

		refrescarTablaLocalidades();
	}

	public void cargarTablaTipoDeContacto(ActionEvent c) {

		this.vista.setVistaSeleccionada(3);

		this.vista.getBtnAgregar().setText("Agregar Tipo de Contacto");

		this.vista.getBtnEditar().setText("Editar Tipo de Contacto");

		this.vista.getBtnBorrar().setText("Borrar Tipo de Contacto");

		this.vista.getBtnAgregar().setBounds(20, 312, 200, 23);

		this.vista.getBtnEditar().setBounds(280, 312, 200, 23);

		this.vista.getBtnBorrar().setBounds(540, 312, 200, 23);

		this.vista.getBtnReporte().setVisible(false);

		this.vista.cargarTablaTipodeContacto();

		refrescarTablaTiposDeContacto();
	}

	private void refrescarTablaLocalidades() {
		this.localidadesEnTabla = agenda.obtenerLocalidades();

		this.vista.llenarTablaLocalidades(localidadesEnTabla);

	}

	private void refrescarTablaTiposDeContacto() {
		this.tipoDeContactoEnTabla = agenda.obtenerTiposDeContacto();

		this.vista.llenarTablaTiposDeContacto(tipoDeContactoEnTabla);
	}

	public void inicializar() {
		this.refrescarTabla();
		this.vista.show();
	}

	private void refrescarTabla() {
		this.personasEnTabla = agenda.obtenerPersonas();
		for (PersonaDTO p : this.personasEnTabla) {
			setDomicilio(p);
			setTipoContacto(p);
			setLocalidad(p);
		}
		this.vista.llenarTabla(this.personasEnTabla);
	}

	private void setLocalidad(PersonaDTO p) {
		p.setLocalidad(this.agenda.getLocalidad(p.getDomicilioDTO().getLocalidad()));

	}

	private void setTipoContacto(PersonaDTO p) {
		p.setTipoContacto(this.agenda.getTipoContacto(p.getIdTipoContacto()).getTipoDeContacto());

	}

	private void setDomicilio(PersonaDTO p) {
		p.setDomicilioDTO(this.agenda.getDomicilio(p.getDomicilio()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	private boolean validateText(String text, String type) {
		String regEx = (type == "email")
				? "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
						+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
				: "\\d{4}-\\d{2}-\\d{2}";
		Pattern emailPattern = Pattern.compile(regEx);
		Matcher matcher = emailPattern.matcher(text);
		if (!matcher.matches()) {
			return false;
		} else {
			return true;
		}
	}

	private boolean validateTextField(JTextField textField, String type) {
		Border wrongBorder = BorderFactory.createLineBorder(Color.RED);
		if (!validateText(textField.getText(), type)) {
			textField.setBorder(wrongBorder);
			return false;
		} else {
			textField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
			return true;
		}
	}

}
