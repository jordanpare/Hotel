package controller;

import java.sql.Date;
import java.util.List;

import connectionFactory.ConnectionFactory;
import dao.HuespedDAO;
import modelo.Huesped;

public class HuespedController {
	
	private HuespedDAO huespedDAO;

	public HuespedController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
	}

	public List<Huesped> listar() {
		return huespedDAO.listar();
	}
	
	public List<Huesped> listar(String busqueda) {
		return huespedDAO.listar(busqueda);
	}
	
	public void salvar(Huesped huesped, Integer reserva) {
		huespedDAO.salvar(huesped, reserva);
	}

	public int modificar(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, Integer idreserva, Integer id) {
		return huespedDAO.modificar(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, idreserva, id);
	}

	public int eliminar(Integer id) {
		return huespedDAO.eliminar(id);
	}



}
