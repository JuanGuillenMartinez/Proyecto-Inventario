package programa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VLogin {

	private JFrame frame;
	private JTextField txtCorreoElectronico;
	private JLabel imagenLogin;
	private JLabel lblCorreoElectronico;
	private JLabel lblContraseña;
	private JPasswordField passwordField;
	private JButton btnIngresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VLogin window = new VLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		imagenLogin = new JLabel("Imagen chingona");
		imagenLogin.setIcon(null);
		imagenLogin.setBackground(Color.WHITE);
		imagenLogin.setBounds(83, 33, 175, 185);
		frame.getContentPane().add(imagenLogin);
		
		lblCorreoElectronico = new JLabel("Correo electronico");
		lblCorreoElectronico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCorreoElectronico.setBounds(41, 229, 118, 26);
		frame.getContentPane().add(lblCorreoElectronico);
		
		txtCorreoElectronico = new JTextField();
		txtCorreoElectronico.setBounds(41, 261, 246, 26);
		frame.getContentPane().add(txtCorreoElectronico);
		txtCorreoElectronico.setColumns(10);
		
		lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContraseña.setBounds(41, 310, 118, 26);
		frame.getContentPane().add(lblContraseña);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(41, 341, 246, 26);
		frame.getContentPane().add(passwordField);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				char[] Pass=passwordField.getPassword();
				String password=new String(Pass);
				if(CConexion.iniciarSesion(txtCorreoElectronico.getText(), password)==true) {
					CVistaInventario window = new CVistaInventario();
					window.initialize();
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Datos incorrectos");
				}
				
			}
		});
		btnIngresar.setBounds(198, 388, 89, 23);
		frame.getContentPane().add(btnIngresar);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnRegistrarse.setBounds(41, 388, 89, 23);
		frame.getContentPane().add(btnRegistrarse);
	}
}