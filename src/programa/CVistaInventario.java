package programa;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import programa.CConexion;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class CVistaInventario {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CVistaInventario window = new CVistaInventario();
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
	public CVistaInventario() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 606, 379);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(54, 78, 485, 193);
		frame.getContentPane().add(table);
		
		JLabel lblVistaDeTodos = new JLabel("Vista de todos los productos en el almacen ");
		lblVistaDeTodos.setHorizontalAlignment(SwingConstants.CENTER);
		lblVistaDeTodos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVistaDeTodos.setBounds(54, 27, 485, 33);
		frame.getContentPane().add(lblVistaDeTodos);
		
		JButton btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.setBounds(54, 282, 131, 23);
		frame.getContentPane().add(btnAgregarProducto);
		
		JButton btnActualizarProducto = new JButton("Actualizar Producto");
		btnActualizarProducto.setBounds(233, 282, 131, 23);
		frame.getContentPane().add(btnActualizarProducto);
		
		JButton btnEliminarProducto = new JButton("Eliminar Producto");
		btnEliminarProducto.setBounds(408, 282, 131, 23);
		frame.getContentPane().add(btnEliminarProducto);
		
		mostrar();
	}
	
	public void mostrar() {
		
		DefaultTableModel modelo=new DefaultTableModel();
		ResultSet rs=CConexion.getTabla("SELECT id_producto,nombre_producto,precio,marca,existencias FROM productos2");
		modelo.setColumnIdentifiers(new Object[]{"id_producto","nombre_producto","precio","marca","existencias"});
		
		try {
			modelo.addRow(new Object[]{"id_producto","nombre_producto","precio","marca","existencias"});
			while(rs.next()) {
				modelo.addRow(new Object[]{rs.getString("id_producto"),rs.getString("nombre_producto"),
				rs.getString("precio"),rs.getString("marca"),rs.getString("existencias")});
			}
			table.setModel(modelo);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getStackTrace());
		}
		
	}
}

