package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import modelo.Agenda;
import persistencia.conexion.Conexion;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.DomicilioDTO;
import dto.PersonaDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private VentanaPersona ventanaPersona; 
		private Agenda agenda;
		private Logger log = LogManager.getLogger(Conexion.class);	
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			this.ventanaPersona = VentanaPersona.getInstance();
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			this.agenda = agenda;
		}
		
		private void ventanaAgregarPersona(ActionEvent a) {
			this.ventanaPersona.mostrarVentana();
		}

		private void guardarPersona(ActionEvent p) {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String email = this.ventanaPersona.getEmail().getText();
			String fecha = ventanaPersona.getFechaC().getText();			
			int domicilio = this.guardarDomicilio();
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel, email, fecha, domicilio);
			this.agenda.agregarPersona(nuevaPersona);
			log.info(nuevaPersona.toString());
			this.refrescarTabla();
			this.ventanaPersona.cerrar();
		}

		private int guardarDomicilio() {
			String calle = this.ventanaPersona.getCalle().getText();
			String altura = ventanaPersona.getAltura().getText();
			String tipoD = this.ventanaPersona.getTipoDomicilio().getText();
			String piso = ventanaPersona.getPiso().getText();
			DomicilioDTO nuevoDomicilio = new DomicilioDTO(0,calle,altura,piso,tipoD);
			return this.agenda.agregarDomicilio(nuevoDomicilio);			
			
		}

		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
		}

		public void borrarPersona(ActionEvent s)
		{
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filasSeleccionadas)
			{
				this.agenda.borrarPersona(this.personasEnTabla.get(fila));
			}
			
			this.refrescarTabla();
		}
		
		public void inicializar()
		{
			this.refrescarTabla();
			this.vista.show();
		}
		
		private void refrescarTabla()
		{
			this.personasEnTabla = agenda.obtenerPersonas();
			for(PersonaDTO p : this.personasEnTabla ) {
				setDomicilio(p);
			}
			this.vista.llenarTabla(this.personasEnTabla);
		}

		private void setDomicilio(PersonaDTO persona) {
			persona.setDomicilioDTO(this.agenda.getDomicilio(persona.getDomicilio()));
		}

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
