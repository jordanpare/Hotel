package controller;

import java.sql.Date;
import java.util.List;

import connectionFactory.ConnectionFactory;
import dao.ReservaDAO;
import modelo.Reserva;

public class ReservaController { 

	
	private ReservaDAO reservaDAO;

	public ReservaController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(factory.recuperaConexion());

	}

	public int modificar(Date dataEntrada, Date dataSalida, String valor, String formaPago, Integer id) {
		return reservaDAO.modificar(dataEntrada, dataSalida, valor, formaPago, id);
	}

	public int eliminar(Integer id) {
		return reservaDAO.eliminar(id);
	}

	public List<Reserva> listar() {
		return reservaDAO.listar();
	}

	public List<Reserva> listar(String busqueda) {
		return reservaDAO.listar(busqueda);
	}
	
	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}

	
}
