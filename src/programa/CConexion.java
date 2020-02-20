package programa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
			JOptionPane.showMessageDialog(null, "hola");
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
	
	
}
