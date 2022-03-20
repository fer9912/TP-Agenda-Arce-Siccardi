package presentacion.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.LocalidadDTO;
import dto.TipoDeContactoDTO;

public class VentanaEditarTipoDeContacto extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TipoDeContacto;
	private static VentanaEditarTipoDeContacto INSTANCE;
	private JButton btnAgregarTipoDeContacto;

	public static VentanaEditarTipoDeContacto getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaEditarTipoDeContacto(); 	
			return new VentanaEditarTipoDeContacto();
		}
		else
			return INSTANCE;
	}

	private VentanaEditarTipoDeContacto() 
	{
		super();
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				cerrar();
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 267, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 386, 242);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLocalidad = new JLabel("Tipo De Contacto");
		lblLocalidad.setBounds(10, 11, 113, 14);
		panel.add(lblLocalidad);
		
		TipoDeContacto = new JTextField();
		TipoDeContacto.setBounds(10, 31, 212, 20);
		panel.add(TipoDeContacto);
		TipoDeContacto.setColumns(10);
		
		
		btnAgregarTipoDeContacto = new JButton("Guardar");
		btnAgregarTipoDeContacto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregarTipoDeContacto.setBounds(20, 77, 180, 23);
		panel.add(btnAgregarTipoDeContacto);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTipoDeContacto() 
	{
		return TipoDeContacto;
	}
	
	

   public JButton getBtnEditarTipoDeContacto() {
		return btnAgregarTipoDeContacto;
	}

	public void setBtnAgregarTipoDeContacto(JButton btnAgregarTipoDeContacto) {
		this.btnAgregarTipoDeContacto = btnAgregarTipoDeContacto;
	}

public void llenarInfoTipoDeContacto(TipoDeContactoDTO tipo) {
	   this.TipoDeContacto.setText(tipo.getTipoDeContacto());
   }

	public void cerrar()
	{
		this.TipoDeContacto.setText(null);
		this.dispose();
	}	
	
}