package dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectionFactory.ConnectionFactory;
import modelo.Huesped;

public class HuespedDAO {
	
	
	final private Connection con;
	private String apellido;
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}

	
	public void salvar(Huesped huesped, Integer reserva) {
		try(con){
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huespedes"
						+ "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono , idreserva)"
						+" VALUES(?, ?, ?, ?, ?, ? )", 
						Statement.RETURN_GENERATED_KEYS);
		
			try(statement){	
				statement.setString(1, huesped.getNombre());		
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, huesped.getFecha_nacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				//statement.setInt(6, huesped.getIdreserva());
				statement.setInt(6, (reserva));
					
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();	
				
				try(resultSet){
					while(resultSet.next()) {
						huesped.setId(resultSet.getInt(1));
						System.out.println(String.format(
										"Fue guardado el huesped %s",
										huesped));	
					}
				}
			}
		}catch(SQLException e) {
					throw new RuntimeException(e);
				}	
    }
	
	
	public List<Huesped> listar() {		
		List<Huesped> resultado =new ArrayList<>();		
		ConnectionFactory factory = new ConnectionFactory();
		final Connection con = factory.recuperaConexion();
		
		try(con){

			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, IDRESERVA FROM HUESPEDES");
			
			try(statement){
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet){
					while(resultSet.next()) {
						Huesped fila = new Huesped(resultSet.getInt("ID"),
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"),
								resultSet.getDate("FECHA_NACIMIENTO"),
								resultSet.getString("NACIONALIDAD"),
								resultSet.getString("TELEFONO"),
								resultSet.getInt("IDRESERVA"));
						
						resultado.add(fila);						
					}	
				}				
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
			}
		return resultado;
	}
	
	public int eliminar(Integer id) {
	    try {
	        final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ? ");

	        try (statement) {
	            statement.setInt(1, id);
	            statement.execute();

	            int updateCount = statement.getUpdateCount();

	            return updateCount;
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	
	
	public int modificar(String nombre, String apellido, 
			Date fecha_nacimiento, String nacionalidad, 
			String telefono, Integer idreserva, Integer id) {
	    try {
	    	
	        final PreparedStatement statement = con.prepareStatement(
	        		"UPDATE HUESPEDES SET "
	        				+ " NOMBRE = ?, " 
	        				+ " APELLIDO = ?,"
	        				+ " FECHA_NACIMIENTO = ?,"
							+ " NACIONALIDAD = ?," 
	        				+ " TELEFONO = ?," 
							+ " idReserva = ?" 
	        				+ " WHERE ID = ? ");
	        try (statement) {
	        	statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, fecha_nacimiento);
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setInt(6, idreserva);
				statement.setInt(7, id);
	            statement.execute();

	            int updateCount = statement.getUpdateCount();

	            return updateCount;
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

	
	//listardo del reporte
	
	public List<Huesped> listar(String nombre) {
		List<Huesped> resultado =new ArrayList<>();		
		ConnectionFactory factory = new ConnectionFactory();
		final Connection con = factory.recuperaConexion();
		
		try(con){
			
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, IDRESERVA "
					+ " FROM HUESPEDES "
					+" WHERE NOMBRE = ? "
					+ " OR APELLIDO = ? ");
			
			try(statement){
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet){
					while(resultSet.next()) {
						Huesped fila = new Huesped(resultSet.getInt("ID"),
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"),
								resultSet.getDate("FECHA_NACIMIENTO"),
								resultSet.getString("NACIONALIDAD"),
								resultSet.getString("TELEFONO"),
								resultSet.getInt("IDRESERVA"));
						
						resultado.add(fila);						
					}	
				}				
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
			}
		return resultado;
	}
	
	
}
