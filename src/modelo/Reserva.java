package modelo;

import java.sql.Date;

public class Reserva {
	
	private Integer id;
	private Date dataEntrada;
	private Date dataSalida;
	private String valor;
	private String formaPago;
	private Integer huespedId;
	private Integer reservaId;

	
	public Reserva(Integer id, Date dataEntrada, Date dataSalida, String valor, String formaPago) {
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSalida = dataSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}
	
	
	public Reserva(Date dataEntrada, Date dataSalida, String valor, String formaPago) {
		this.dataEntrada = dataEntrada;
		this.dataSalida = dataSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Reserva(Integer reservaId) {
		this.reservaId = reservaId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDataEntrada() {
		return dataEntrada;
	}


	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}


	public Date getDataSalida() {
		return dataSalida;
	}


	public void setDataSalida(Date dataSalida) {
		this.dataSalida = dataSalida;
	}


	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}


	public String getFormaPago() {
		return formaPago;
	}


	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	public Integer getHuespedId() {
		return huespedId;
	}


	public void setHuespedId(Integer huespedId) {
		this.huespedId = huespedId;
	}
	
	
	@Override
	public String toString() {
		return String.format("{id: %s, dataEntrada: %s, dataSalida: %s, Valor: %s, Forma de Pago: %s}",
				this.id,
				this.dataEntrada,
				this.dataSalida,
				this.valor,
				this.formaPago);
	}




}
