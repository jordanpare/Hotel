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

import modelo.Reserva;

public class ReservaDAO {
	
	private Connection con;	

	public ReservaDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Reserva reserva) {
		try{
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservas"
						+ "(dataEntrada, dataSalida, valor, formaPago)"
						+" VALUES(?, ?, ?, ? )", 
						Statement.RETURN_GENERATED_KEYS);
		
			try(statement){	
				statement.setDate(1, reserva.getDataEntrada());		
				statement.setDate(2, reserva.getDataSalida());
				statement.setString(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
					
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();	
				
				try(resultSet){
					while(resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
						System.out.println(String.format(
										"Fue guardado la reserva %s",
										reserva));	
					}
				}
			}
		}catch(SQLException e) {
					throw new RuntimeException(e);
				}	
    }
	

	public List<Reserva> listar() {
		List<Reserva> resultado =new ArrayList<>();
		ConnectionFactory factory = new ConnectionFactory();
		final Connection con = factory.recuperaConexion();
		
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT ID, DATAENTRADA, DATASALIDA, VALOR, FORMAPAGO FROM RESERVAS ");
				try (statement){
					final ResultSet resultSet = statement.executeQuery();
					try(resultSet){
						while (resultSet.next()) {
							Reserva fila = new Reserva(resultSet.getInt("ID"),
									resultSet.getDate("DATAENTRADA"),
									resultSet.getDate("DATAENTRADA"),
									resultSet.getString("VALOR"),
									resultSet.getString("FORMAPAGO"));
							resultado.add(fila);
						}		
					};
				}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}

	
	public int eliminar(Integer id) {
	    try {
	        final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ? ");

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
	
	
	public int modificar(Date dataEntrada, Date dataSalida, String valor, String formaPago, Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET " 
					+ " DATAENTRADA = ?, "
					+ " DATASALIDA = ?, " 
					+ " VALOR = ?," 
					+ " FORMAPAGO = ? " 
					+ " WHERE ID = ? ");

			try (statement) {
				statement.setDate(1, (java.sql.Date) dataEntrada);
				statement.setDate(2, (java.sql.Date) dataSalida);
				statement.setString(3, valor);
				statement.setString(4, formaPago);
				statement.setInt(5, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> listar(String busqueda) {
		List<Reserva> resultado = new ArrayList<>();

		final Connection con = new ConnectionFactory().recuperaConexion();

		try (con) {

			//var querySelect = "SELECT ID, DATAENTRADA, DATASALIDA, VALOR, FORMAPAGO FROM RESERVAS WHERE Id = ?";
			
			final PreparedStatement statement = con.prepareStatement("SELECT ID, DATAENTRADA, DATASALIDA, VALOR, FORMAPAGO"
					+ " FROM RESERVAS "
					+ " WHERE Id = ? ");

			try (statement) {

				statement.setString(1, busqueda);

				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("ID"),
								resultSet.getDate("DATENTRADA"),
								resultSet.getDate("DATAsSALIDA"),
								resultSet.getString("VALOR"),
								resultSet.getString("FORMAPAGO"));

						resultado.add(fila);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
	
	
}
