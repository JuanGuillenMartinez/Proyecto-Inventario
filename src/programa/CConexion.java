package programa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CConexion {

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
		PreparedStatement stm = cn.prepareStatement("DELETE FROM productos2 WHERE id_producto="+id);
		stm.executeUpdate();
	}
	
}
