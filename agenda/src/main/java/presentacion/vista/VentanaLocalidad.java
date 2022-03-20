package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

;

public class VentanaLocalidad extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField localidad;
	private JTextField provincia;
	private static VentanaLocalidad INSTANCE;
	private JTextField pais;
	private JLabel lblpais;
	private JButton btnAgregarLocalidad;

	public static VentanaLocalidad getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaLocalidad(); 	
			return new VentanaLocalidad();
		}
		else
			return INSTANCE;
	}

	private VentanaLocalidad() 
	{
		super();
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 230, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 386, 242);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 11, 113, 14);
		panel.add(lblLocalidad);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 62, 113, 14);
		panel.add(lblProvincia);
		
		localidad = new JTextField();
		localidad.setBounds(10, 31, 164, 20);
		panel.add(localidad);
		localidad.setColumns(10);
		
		provincia = new JTextField();
		provincia.setBounds(10, 83, 164, 20);
		panel.add(provincia);
		provincia.setColumns(10);
		
		pais = new JTextField();
		pais.setColumns(10);
		pais.setBounds(10, 139, 164, 20);
		panel.add(pais);
		
		lblpais = new JLabel("Pais");
		lblpais.setBounds(10, 114, 113, 14);
		panel.add(lblpais);		
		
		
		btnAgregarLocalidad = new JButton("Agregar Localidad");
		btnAgregarLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregarLocalidad.setBounds(20, 185, 150, 23);
		panel.add(btnAgregarLocalidad);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getLocalidad() 
	{
		return localidad;
	}

	public JTextField getProvincia() 
	{
		return provincia;
	}

	public JButton getBtnAgregarLocalidad() 
	{
		return btnAgregarLocalidad;
	}

	public JTextField getPais() {
		return pais;
	}

	public void setCalle(JTextField calle) {
		this.pais = calle;
	}


	public void cerrar()
	{
		this.localidad.setText(null);
		this.provincia.setText(null);
		this.dispose();
	}	
	
}
