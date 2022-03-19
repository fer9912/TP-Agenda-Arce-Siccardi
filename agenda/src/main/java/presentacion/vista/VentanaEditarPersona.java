package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class VentanaEditarPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombre;
	private JTextField telefono;
	private static VentanaEditarPersona INSTANCE;
	private JTextField fechaC;
	private JTextField email;
	private JTextField calle;
	private JLabel lblCalle;
	private JLabel lblDomicilio;
	private JTextField altura;
	private JTextField piso;
	private JTextField tipoDomicilio;
	private JButton btnGuardarPersona;
	private JComboBox<String> tipoDeContactos;
	private JComboBox<String> localidad;
	private DefaultComboBoxModel<String> modelTipos = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modelLocalidades = new DefaultComboBoxModel<String>();

	public static VentanaEditarPersona getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaEditarPersona(); 	
			return new VentanaEditarPersona();
		}
		else
			return INSTANCE;
	}

	private VentanaEditarPersona() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 416, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 386, 353);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 62, 113, 14);
		panel.add(lblTelfono);
		
		nombre = new JTextField();
		nombre.setBounds(10, 31, 164, 20);
		panel.add(nombre);
		nombre.setColumns(10);
		
		telefono = new JTextField();
		telefono.setBounds(10, 83, 164, 20);
		panel.add(telefono);
		telefono.setColumns(10);
		
		fechaC = new JTextField();
		fechaC.setColumns(10);
		fechaC.setBounds(204, 83, 164, 20);
		panel.add(fechaC);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(204, 31, 164, 20);
		panel.add(email);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(209, 11, 113, 14);
		panel.add(lblEmail);
		
		JLabel lblTelfono_1 = new JLabel("Fecha de cumpleaños");
		lblTelfono_1.setBounds(209, 62, 113, 14);
		panel.add(lblTelfono_1);
		
		calle = new JTextField();
		calle.setColumns(10);
		calle.setBounds(10, 161, 164, 20);
		panel.add(calle);
		
		lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 142, 113, 14);
		panel.add(lblCalle);
		
		lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDomicilio.setBounds(10, 114, 113, 14);
		panel.add(lblDomicilio);
		

		DefaultComboBoxModel<String> modelLocalidad = this.getModelLocalidades();
		localidad = new JComboBox();
		localidad.setModel(modelLocalidad);
		localidad.setBounds(209, 209, 159, 22);
		panel.add(localidad);
		
		altura = new JTextField();
		altura.setColumns(10);
		altura.setBounds(209, 161, 72, 20);
		panel.add(altura);
		
		piso = new JTextField();
		piso.setColumns(10);
		piso.setBounds(296, 161, 72, 20);
		panel.add(piso);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(296, 142, 72, 14);
		panel.add(lblPiso);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(209, 142, 77, 14);
		panel.add(lblAltura);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(209, 192, 164, 14);
		panel.add(lblLocalidad);
		
		JLabel lblTipocasaDpto = new JLabel("Tipo (Casa, Dpto, etc...)");
		lblTipocasaDpto.setBounds(10, 192, 164, 14);
		panel.add(lblTipocasaDpto);
		
		tipoDomicilio = new JTextField();
		tipoDomicilio.setColumns(10);
		tipoDomicilio.setBounds(10, 210, 164, 20);
		panel.add(tipoDomicilio);
		
		btnGuardarPersona = new JButton("Guardar");
		btnGuardarPersona.setBounds(147, 306, 89, 23);
		panel.add(btnGuardarPersona);
		
		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto:");
		lblTipoDeContacto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTipoDeContacto.setBounds(10, 262, 113, 14);
		panel.add(lblTipoDeContacto);
		
		DefaultComboBoxModel<String> model = this.getModelTipos();
		tipoDeContactos = new JComboBox<String>(model);
		tipoDeContactos.setBounds(160, 259, 208, 22);
		panel.add(tipoDeContactos);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return nombre;
	}

	public JTextField getTxtTelefono() 
	{
		return telefono;
	}

	public JButton getBtnGuardarPersona() 
	{
		return btnGuardarPersona;
	}

	public JTextField getFechaC() {
		return fechaC;
	}

	public void setFechaC(JTextField fechaC) {
		this.fechaC = fechaC;
	}

	public JTextField getEmail() {
		return email;
	}

	public void setEmail(JTextField email) {
		this.email = email;
	}

	public JTextField getCalle() {
		return calle;
	}

	public void setCalle(JTextField calle) {
		this.calle = calle;
	}

	public JTextField getAltura() {
		return altura;
	}

	public void setAltura(JTextField altura) {
		this.altura = altura;
	}

	public JTextField getPiso() {
		return piso;
	}

	public void setPiso(JTextField piso) {
		this.piso = piso;
	}

	public JTextField getTipoDomicilio() {
		return tipoDomicilio;
	}

	public void setTipoDomicilio(JTextField tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}

	public void cerrar()
	{
		this.nombre.setText(null);
		this.telefono.setText(null);
		this.dispose();
	}
	
	
	public void llenarInfoPersona(PersonaDTO persona,List<TipoDeContactoDTO> tiposDeContacto,List<LocalidadDTO> localidades) {
		this.nombre.setText(persona.getNombre()); 
		this.email.setText(persona.getEmail());
		this.telefono.setText(persona.getTelefono());
		this.fechaC.setText(persona.getFecha());
		this.calle.setText(persona.getDomicilioDTO().getCalle());
		this.altura.setText(persona.getDomicilioDTO().getAltura());
		this.piso.setText(persona.getDomicilioDTO().getPiso());
		this.tipoDomicilio.setText(persona.getDomicilioDTO().getTipoDomicilio());
		
		this.getModelTipos().addElement(persona.getIdTipoContacto() + " - " + persona.getTipoContacto());
		
		this.getModelLocalidades().addElement(persona.getLocalidad().getIdLocalidad() + " - " + persona.getLocalidad().getNombreLocalidad());
		
		llenarTiposDeContacto(tiposDeContacto, persona.getTipoContacto());
		
		llenarLocalidades(localidades, persona.getLocalidad().getNombreLocalidad());
	}

	public void llenarTiposDeContacto(List<TipoDeContactoDTO> tiposDeContacto,String tipoActual) {
		for (TipoDeContactoDTO t : tiposDeContacto)
		{
			if(t.getTipoDeContacto().equals(tipoActual)) {
				continue;
			}
			
			this.getModelTipos().addElement(t.getIdTipoDeContacto() + " - " + t.getTipoDeContacto());
		}
		
	}
	
	public void llenarLocalidades(List<LocalidadDTO> localidades,String localidadActual) {
		for (LocalidadDTO l : localidades)
		{
			if(l.getNombreLocalidad().equals(localidadActual)) {
				continue;
			}
			
			this.getModelLocalidades().addElement(l.getIdLocalidad() + " - " + l.getNombreLocalidad());
		}
		
	}
	public DefaultComboBoxModel<String> getModelTipos() {
		return modelTipos;
	}

	public void setModelTipos(DefaultComboBoxModel<String> modelTipos) {
		this.modelTipos = modelTipos;
	}
	
	public DefaultComboBoxModel<String> getModelLocalidades() {
		return modelLocalidades;
	}

	public void setModelLocalidades(DefaultComboBoxModel<String> modelLocalidades) {
		this.modelLocalidades = modelLocalidades;
	}


	public JComboBox<String> getTipoDeContactos() {
		return tipoDeContactos;
	}

	public JComboBox<String> getLocalidad() {
		return localidad;
	}

	public void setLocalidad(JComboBox<String> localidad) {
		this.localidad = localidad;
	}

	public void setTipoDeContactos(JComboBox<String> tipoDeContactos) {
		this.tipoDeContactos = tipoDeContactos;
	}
	


}
