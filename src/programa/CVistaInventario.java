package programa;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		btnAgregarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CVentanas.mostrarIngreso();
			}
		});
		btnAgregarProducto.setBounds(54, 282, 131, 23);
		frame.getContentPane().add(btnAgregarProducto);
		
		JButton btnActualizarProducto = new JButton("Actualizar Producto");
		btnActualizarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CVentanas.mostrarActualizar();
			}
		});
		btnActualizarProducto.setBounds(233, 282, 131, 23);
		frame.getContentPane().add(btnActualizarProducto);
		
		JButton btnEliminarProducto = new JButton("Eliminar Producto");
		btnEliminarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					CVentanas.eliminarProducto(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del producto")));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEliminarProducto.setBounds(408, 282, 131, 23);
		frame.getContentPane().add(btnEliminarProducto);
		
		JButton btnRecargar = new JButton("Recargar");
		btnRecargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrar();
			}
		});
		btnRecargar.setBounds(408, 306, 131, 23);
		frame.getContentPane().add(btnRecargar);
		
		mostrar();
	}
	
	public void mostrar() {
		
		DefaultTableModel modelo=new DefaultTableModel();
		ResultSet rs=CConexion.getTabla("SELECT id_producto,nombre_producto,precio,marca,existencias FROM productos2");
		modelo.setColumnIdentifiers(new Object[]{" ID producto","Nombre","Precio","Marca","Existencias"});
		
		try {
			modelo.addRow(new Object[]{" ID producto","Nombre","Precio","Marca","Existencias"});
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
