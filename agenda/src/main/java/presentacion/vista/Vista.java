package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.DomicilioDTO;
import dto.PersonaDTO;
import modelo.Agenda;

import javax.swing.JButton;

import persistencia.conexion.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Vista
{
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private JButton btnEditar;
	JButton btnVerAgenda;
	JButton btnVerTipoDeContacto;
	JButton btnLocalidades;
	private List<PersonaDTO> personasEnTabla;
	private Agenda agenda;
	private DefaultTableModel modelPersonas;
	private  String[] nombreColumnasPersona = {"Nombre y apellido","Telefono","Email","Fecha de Nacimiento","Calle","Altura","Piso","Tipo de Domicilio","Tipo de Contacto","Localidad"};
	private  String[] nombreColumnasTipoDeContacto = {"Tipo de Contacto"};
	private  String[] nombreColumnasLocalidades = {"Localidad","Provincia","Pais"};

	public Vista() 
	{
		super();
		initialize();
	}


	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 803, 351);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 783, 290);
		panel.add(spPersonas);
		
		modelPersonas = new DefaultTableModel(null,nombreColumnasPersona);
		tablaPersonas = new JTable(modelPersonas);

		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
		
		spPersonas.setViewportView(tablaPersonas);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregar.setBounds(192, 312, 89, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(291, 312, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(390, 312, 89, 23);
		panel.add(btnBorrar);
		
		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(489, 312, 89, 23);
		panel.add(btnReporte);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(823, 11, 175, 302);
		frame.getContentPane().add(panel_1);
		
		btnVerAgenda = new JButton("Ver Agenda");
		btnVerAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modelPersonas = new DefaultTableModel(null,nombreColumnasPersona);
				tablaPersonas = new JTable(modelPersonas);

				tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
				tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
				tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
				tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
				
				spPersonas.setViewportView(tablaPersonas);
				
			}
		});
		btnVerAgenda.setBounds(823, 25, 75, 302);
		panel_1.add(btnVerAgenda);
		
		String verTipoContact = "Ver Tipos de Contacto";
		btnVerTipoDeContacto = new JButton(verTipoContact);
		btnVerTipoDeContacto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelPersonas = new DefaultTableModel(null,nombreColumnasTipoDeContacto);
				tablaPersonas = new JTable(modelPersonas);

				tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
				tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
				
				spPersonas.setViewportView(tablaPersonas);
			}
		});
		panel_1.add(btnVerTipoDeContacto);
		
		btnLocalidades = new JButton("Ver Localidades");
		btnLocalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				modelPersonas = new DefaultTableModel(null,nombreColumnasLocalidades);
				tablaPersonas = new JTable(modelPersonas);

				tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
				tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
				tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(103);
				tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
				tablaPersonas.getColumnModel().getColumn(2).setPreferredWidth(103);
				tablaPersonas.getColumnModel().getColumn(2).setResizable(false);
				
				spPersonas.setViewportView(tablaPersonas);
			}
		});
		panel_1.add(btnLocalidades);
		
	}
	
	public JButton getBtnVerAgenda() {
		return btnVerAgenda;
	}


	public void setBtnVerAgenda(JButton btnVerAgenda) {
		this.btnVerAgenda = btnVerAgenda;
	}


	public JButton getBtnVerTipoDeContacto() {
		return btnVerTipoDeContacto;
	}


	public void setBtnVerTipoDeContacto(JButton btnVerTipoDeContacto) {
		this.btnVerTipoDeContacto = btnVerTipoDeContacto;
	}


	public JButton getBtnLocalidades() {
		return btnLocalidades;
	}


	public void setBtnLocalidades(JButton btnLocalidades) {
		this.btnLocalidades = btnLocalidades;
	}


	public void show()
	{
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir de la Agenda?", 
		             "Confirmación", JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	Conexion.getConexion().cerrarConexion();
		           System.exit(0);
		        }
		    }
		});
		this.frame.setVisible(true);
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}

	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	public JButton getBtnReporte() 
	{
		return btnReporte;
	}
	
	public JButton getBtnEditar() 
	{
		return btnEditar;
	}
	
	
	public DefaultTableModel getModelPersonas() 
	{
		return modelPersonas;
	}
	
	public JTable getTablaPersonas()
	{
		return tablaPersonas;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnasPersona;
	}


	public void llenarTabla(List<PersonaDTO> personasEnTabla) {
		this.getModelPersonas().setRowCount(0); //Para vaciar la tabla
		this.getModelPersonas().setColumnCount(0);
		this.getModelPersonas().setColumnIdentifiers(this.getNombreColumnas());

		for (PersonaDTO p : personasEnTabla)
		{
			DomicilioDTO d = p.getDomicilioDTO();
			String nombre = p.getNombre();
			String tel = p.getTelefono();
			String email = p.getEmail();
			String fecha = p.getFecha();
			String calle = d.getCalle();
			String altura = d.getAltura();
			String piso = d.getPiso();
			String tipoD = d.getTipoDomicilio();
			String tipoContacto = p.getTipoContacto();
			String localidad = p.getLocalidad().getNombreLocalidad();
			Object[] fila = {nombre, tel, email, fecha, calle, altura, piso, tipoD, tipoContacto, localidad};
			this.getModelPersonas().addRow(fila);
		}
		
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
}
