package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.sql.DataSource;
import entidades.OperacionEnidad;

public class TransacionPool {
	@Resource(name = "jdbc/Cryptomanager")
	private DataSource pisina;

	public TransacionPool(DataSource pisina) {
		this.pisina = pisina;
	}
	public ArrayList<OperacionEnidad> obternerListaTransaciones() {
		// TODO Auto-generated method stub
		Connection con = null;
		ArrayList<OperacionEnidad> listaOperaciones = new ArrayList<OperacionEnidad>();
		try {
			con = pisina.getConnection();
			PreparedStatement ps = con.prepareStatement("select t.id_trans as ID, tp.nombre as modo, m.logo, m.nick, m.nombre, t.cant_mon as \"Cantidad\",prec_tot as \"Precio Total\", (prec_tot/cant_mon) as \"Precio Unitario\", fecha from trans as t left join moneda as m on t.nick_mon = m.nick left join tp_trans as tp on t.id_tipo = tp.id_tipo;");
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				int id = resultado.getInt("ID");
				String nick = resultado.getString("nick");
				String nombre = resultado.getString("nombre");
				String img = resultado.getString("logo");
				double Cantidad = resultado.getDouble("Cantidad");
				double PT = resultado.getDouble("Precio Total");
				double PU = resultado.getDouble("Precio Unitario");
				String mode = resultado.getString("modo");
				String date = resultado.getString("fecha");
				OperacionEnidad opreacionDao = new OperacionEnidad(id, img, nick, Cantidad, PT, date, mode, PU, nombre);
				listaOperaciones.add(opreacionDao);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
		}
		return listaOperaciones;
	}
	
	public ArrayList<OperacionEnidad> obternerListaTransacionesByNick(String Nick){
		Connection con = null;
		ArrayList<OperacionEnidad> listaTransaciones = new ArrayList<OperacionEnidad>();
		try {
			con = pisina.getConnection();
			PreparedStatement ps = con.prepareStatement("select t.id_trans as ID, tp.nombre as modo, m.logo, m.nick, m.nombre, t.cant_mon as \"Cantidad\",prec_tot as \"Precio Total\", (prec_tot/cant_mon) as \"Precio Unitario\", fecha from trans as t left join moneda as m on t.nick_mon = m.nick left join tp_trans as tp on t.id_tipo = tp.id_tipo where m.nick =?;");
			ps.setString(1, Nick);
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				int id = resultado.getInt("ID");
				String nick = resultado.getString("nick");
				String nombre = resultado.getString("nombre");
				String img = resultado.getString("logo");
				double Cantidad = resultado.getDouble("Cantidad");
				double PT = resultado.getDouble("Precio Total");
				double PU = resultado.getDouble("Precio Unitario");
				String mode = resultado.getString("modo");
				String date = resultado.getString("fecha");
				OperacionEnidad opreacionDao = new OperacionEnidad(id, img, nick, Cantidad, PT, date, mode, PU, nombre);
				listaTransaciones.add(opreacionDao);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaTransaciones;
	}

	public boolean agregarTrasacion(OperacionEnidad ope) {
		Connection con = null;
		boolean estado = false; 
		try {
			con = pisina.getConnection();
			int modo = 1;
			PreparedStatement ps = con.prepareStatement("insert into trans (id_tipo, fecha, nick_mon, cant_mon, prec_tot) values (?,?, ?, ?, ?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			if (ope.getMode().equals("Venta")) {
				modo = -1;
			}
			
			ps.setInt(1, modo);
			ps.setString(2, FuncionesVarias.getDateTime());
			ps.setString(3, ope.getNik());
			ps.setDouble(4, ope.getCantidad());
			ps.setDouble(5, ope.getPrice());
			ps.executeUpdate();
			estado = true;

		} catch (SQLException e) {
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return estado;
	}
	

}
