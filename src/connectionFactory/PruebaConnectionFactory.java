package connectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConnectionFactory {
	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperaConexion();
		
		System.out.println("Cerrando conexion!!");
		
		connection.close();
	}

}