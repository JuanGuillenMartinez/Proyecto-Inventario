package programa;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CVentanas {

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
