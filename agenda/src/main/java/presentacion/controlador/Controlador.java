package presentacion.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.Border;

import modelo.Agenda;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DAOAbstractFactory;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaEditarPersona;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.DomicilioDTO;
import dto.PersonaDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personasEnTabla;
	private VentanaPersona ventanaPersona;
	private VentanaEditarPersona ventanaEditarPersona;
	private PersonaDTO personaSeleccionada;
	private Agenda agenda;
	private Logger log = LogManager.getLogger(Conexion.class);

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.agenda = agenda;
		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarPersona(a));
		this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));
		this.vista.getBtnEditar().addActionListener(e -> ventanaEditarPersona(e));
		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(p -> guardarPersona(p));
		this.ventanaEditarPersona = VentanaEditarPersona.getInstance();
		this.ventanaEditarPersona.getBtnGuardarPersona().addActionListener(g -> guardarCambiosPersona(g));
	}

	private void ventanaAgregarPersona(ActionEvent a) {
		this.ventanaPersona.llenarTiposDeContacto(agenda.obtenerTiposDeContacto());
		this.ventanaPersona.llenarLocalidades(agenda.obtenerLocalidades());
		this.ventanaPersona.mostrarVentana();
	}

	public void ventanaEditarPersona(ActionEvent e) {
		if (this.vista.getTablaPersonas() != null && this.vista.getTablaPersonas().getSelectedRow() != -1) {
			int filaSeleccionada = this.vista.getTablaPersonas().getSelectedRow();

			personaSeleccionada = this.personasEnTabla.get(filaSeleccionada);

			this.ventanaEditarPersona.llenarInfoPersona(personaSeleccionada, agenda.obtenerTiposDeContacto(),
					agenda.obtenerLocalidades());

			this.ventanaEditarPersona.mostrarVentana();
		}

	}

	private void guardarPersona(ActionEvent p) {
		if (validateEmail()) {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String email = this.ventanaPersona.getEmail().getText();
			String fecha = ventanaPersona.getFechaC().getText();
			int domicilio = this.guardarDomicilio();
			int tipo = Integer
					.parseInt(this.ventanaPersona.getTipoDeContactos().getSelectedItem().toString().substring(0, 1));
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel, email, fecha, domicilio, tipo);
			this.agenda.agregarPersona(nuevaPersona);
			log.info(nuevaPersona.toString());
			this.refrescarTabla();
			this.ventanaPersona.cerrar();
		}

	}

	private boolean validateEmail() {
		Border wrongBorder = BorderFactory.createLineBorder(Color.RED);
		if (!validateText(ventanaPersona.getEmail().getText())) {
			ventanaPersona.getEmail().setBorder(wrongBorder);
			return false;
		} else {
			ventanaPersona.getEmail().setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
			return true;
		}
	}

	private boolean validateEmailToEdit() {
		Border wrongBorder = BorderFactory.createLineBorder(Color.RED);
		if (!validateText(ventanaPersona.getEmail().getText())) {
			ventanaEditarPersona.getEmail().setBorder(wrongBorder);
			return false;
		} else {
			ventanaEditarPersona.getEmail()
					.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
			return true;
		}
	}

	private void guardarCambiosPersona(ActionEvent g) {
		if (validateEmailToEdit()) {
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

	public void borrarPersona(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas) {
			this.agenda.borrarPersona(this.personasEnTabla.get(fila));
		}

		this.refrescarTabla();
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

	private boolean validateText(String text) {
		String regEx = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		Pattern emailPattern = Pattern.compile(regEx);
		Matcher matcher = emailPattern.matcher(text);
		if (!matcher.matches()) {
			return false;
		} else {
			return true;
		}
	}

}
