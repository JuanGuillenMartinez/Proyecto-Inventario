import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

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
				JOptionPane.showMessageDialog(null,"Profe, cheque el ID manualmente en la ventana principal para ingresarlo en la BD");
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

class CConexion {

	private static String db="inventario";
	private static String user="root";
	private static String pass="JSevenfoldStadia@16";
	private static String host="localhost:3306	";
	private static String server="jdbc:mysql://"+host+"/"+db;
	
	public static Connection getConexion() {
		Connection cn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection(server, user, pass);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
	
	public static ResultSet getTabla(String consulta) {
		Connection cn=getConexion();
		Statement st;
		ResultSet datos=null;
		try {
			st=cn.createStatement();
			datos=st.executeQuery(consulta);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getStackTrace());
		}
		return datos;
	}
	
	public static void ingresarProducto(int id, String nombre, double precio, String marca, int existencias) throws SQLException {
		Connection cn=getConexion();
		PreparedStatement stm = cn.prepareStatement("INSERT INTO productos2 VALUES (?,?,?,?,?)");
		stm.setInt(1,id);
		stm.setString(2, nombre);
		stm.setDouble(3,precio);
		stm.setString(4, marca);
		stm.setInt(5, existencias);
		stm.executeUpdate();
	}
	
	public static void actualizarProducto(int id, String nombre, double precio, String marca, int existencias) throws SQLException {
		Connection cn=getConexion();
		PreparedStatement stm = cn.prepareStatement("UPDATE productos2 SET nombre_producto=? ,precio=? ,marca=?, existencias=? WHERE id_producto="+id);
		stm.setString(1, nombre);
		stm.setDouble(2,precio);
		stm.setString(3, marca);
		stm.setInt(4, existencias);
		stm.executeUpdate();
	}
	
	public static void eliminarProducto(int id) throws SQLException {
		Connection cn=getConexion();
		PreparedStatement stm = cn.prepareStatement("UPDATE productos2 SET nombre_producto=? ,precio=? ,marca=?, existencias=? WHERE id_producto="+id);
		stm.setString(1, "?");
		stm.setDouble(2, 0.0);
		stm.setString(3, "?");
		stm.setInt(4, 0);
		stm.executeUpdate();
	}
	
}

class CVentanas {

	public static void mostrarIngreso() {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIdDelProducto = new JLabel("ID del producto");
		lblIdDelProducto.setBounds(27, 86, 107, 14);
		frame.getContentPane().add(lblIdDelProducto);
		
		JLabel lblNombreDelProducto = new JLabel("Nombre del producto");
		lblNombreDelProducto.setBounds(27, 111, 107, 14);
		frame.getContentPane().add(lblNombreDelProducto);
		
		JLabel lblPrecioDelProducto = new JLabel("Precio del producto");
		lblPrecioDelProducto.setBounds(27, 136, 107, 14);
		frame.getContentPane().add(lblPrecioDelProducto);
		
		JLabel lblExistenciasDelProducto = new JLabel("Existencias del producto");
		lblExistenciasDelProducto.setBounds(27, 186, 166, 14);
		frame.getContentPane().add(lblExistenciasDelProducto);
		
		JLabel lblMarcaDelProducto = new JLabel("Marca del producto");
		lblMarcaDelProducto.setBounds(27, 161, 101, 14);
		frame.getContentPane().add(lblMarcaDelProducto);
		
		JLabel lblActualizarLaInformacion = new JLabel("Agregar productos");
		lblActualizarLaInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarLaInformacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblActualizarLaInformacion.setBounds(27, 27, 357, 34);
		frame.getContentPane().add(lblActualizarLaInformacion);
		
		JTextField txtId = new JTextField();
		txtId.setBounds(298, 83, 86, 20);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JTextField txtNombre = new JTextField();
		txtNombre.setBounds(298, 108, 86, 20);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JTextField txtPrecio = new JTextField();
		txtPrecio.setBounds(298, 133, 86, 20);
		frame.getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JTextField txtMarca = new JTextField();
		txtMarca.setBounds(298, 158, 86, 20);
		frame.getContentPane().add(txtMarca);
		txtMarca.setColumns(10);
		
		JTextField txtExistencias = new JTextField();
		txtExistencias.setBounds(298, 183, 86, 20);
		frame.getContentPane().add(txtExistencias);
		txtExistencias.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					CConexion.ingresarProducto(Integer.parseInt(txtId.getText()), txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), txtMarca.getText(), Integer.parseInt(txtExistencias.getText()));
					JOptionPane.showMessageDialog(null, "Producto ingresado correctamente");
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAceptar.setBounds(298, 215, 89, 23);
		frame.getContentPane().add(btnAceptar);
		frame.setVisible(true);
		
	}
	
	public static void mostrarActualizar() {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIdDelProducto = new JLabel("ID del producto");
		lblIdDelProducto.setBounds(27, 86, 107, 14);
		frame.getContentPane().add(lblIdDelProducto);
		
		JLabel lblNombreDelProducto = new JLabel("Nombre del producto");
		lblNombreDelProducto.setBounds(27, 111, 107, 14);
		frame.getContentPane().add(lblNombreDelProducto);
		
		JLabel lblPrecioDelProducto = new JLabel("Precio del producto");
		lblPrecioDelProducto.setBounds(27, 136, 107, 14);
		frame.getContentPane().add(lblPrecioDelProducto);
		
		JLabel lblExistenciasDelProducto = new JLabel("Existencias del producto");
		lblExistenciasDelProducto.setBounds(27, 186, 166, 14);
		frame.getContentPane().add(lblExistenciasDelProducto);
		
		JLabel lblMarcaDelProducto = new JLabel("Marca del producto");
		lblMarcaDelProducto.setBounds(27, 161, 101, 14);
		frame.getContentPane().add(lblMarcaDelProducto);
		
		JLabel lblActualizarLaInformacion = new JLabel("Actualizar la informacion de los productos");
		lblActualizarLaInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizarLaInformacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblActualizarLaInformacion.setBounds(27, 27, 357, 34);
		frame.getContentPane().add(lblActualizarLaInformacion);
		
		JTextField txtId = new JTextField();
		txtId.setBounds(298, 83, 86, 20);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JTextField txtNombre = new JTextField();
		txtNombre.setBounds(298, 108, 86, 20);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JTextField txtPrecio = new JTextField();
		txtPrecio.setBounds(298, 133, 86, 20);
		frame.getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JTextField txtMarca = new JTextField();
		txtMarca.setBounds(298, 158, 86, 20);
		frame.getContentPane().add(txtMarca);
		txtMarca.setColumns(10);
		
		JTextField txtExistencias = new JTextField();
		txtExistencias.setBounds(298, 183, 86, 20);
		frame.getContentPane().add(txtExistencias);
		txtExistencias.setColumns(10);
		
		JButton btnAceptar1 = new JButton("Actualizar");
		btnAceptar1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					CConexion.actualizarProducto(Integer.parseInt(txtId.getText()), txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), txtMarca.getText(), Integer.parseInt(txtExistencias.getText()));
					JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAceptar1.setBounds(298, 215, 89, 23);
		frame.add(btnAceptar1);
		frame.setVisible(true);
	}
	
	public static void eliminarProducto(int id) throws SQLException {
		int opc=Integer.parseInt(JOptionPane.showInputDialog("Desea eliminar el producto con el ID "+id+"?\n"
				+ "1) Si\n2) No"));
		if(opc==1) {
			CConexion.eliminarProducto(id);
			JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
		}
	}
	
}


