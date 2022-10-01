package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Huesped {
	
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fecha_nacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer idreserva;
	private List<Reserva> reserva;

	public Huesped(Integer id, String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, Integer idreserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idreserva = idreserva;
	}
	
	public Huesped(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, Integer idreserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idreserva = idreserva;
		}
	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getIdreserva() {
		return idreserva;
	}

	public void setIdreserva(Integer idreserva) {
		this.idreserva = idreserva;
	}

	public List<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}
	
	public void agregar(Reserva reserva) {
		if (this.reserva == null) {
			this.reserva = new ArrayList<>();
		}
		
		this.reserva.add(reserva);
	}
	
	//se saca this.id,
	/*@Override
	public String toString() {
		return String.format("{Nombre: %s, Apellido: %s, Fecha de Nacimiento:: %s, Nacionalidad: %s, Telefono: s%, Reserva: d%}",
						this.nombre,
						this.apellido,
						this.fecha_nacimiento,
						this.nacionalidad,
						this.telefono,
						this.idreserva);
		}
*/
	
	@Override
	public String toString() {
		return "nombre: " 
		+ this.nombre + ", apellido: " 
		+ this.apellido	+ ", fecha de nacimiento: " 
		+ this.fecha_nacimiento + ", nacionalidad: " 
		+ this.nacionalidad + ", teléfono: " 
		+ this.telefono + ", reserva nro: " 
		+ this.idreserva;
	}

	public List<Reserva> getReservas() {
		return this.reserva;
	}
	

}
