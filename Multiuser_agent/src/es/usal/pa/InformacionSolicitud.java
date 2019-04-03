package es.usal.pa;

import java.io.Serializable;

import jade.core.AID;

public class InformacionSolicitud implements Serializable{

	String tipo_solicitud;
	String ciudad;
	String fechaIda;
	String fechaVuelta;
	AID tipoIDUsuario;
	public InformacionSolicitud(String solicitud,String ciudad,String ida,String vuelta,AID tipoID) {
		this.tipo_solicitud = solicitud;
		this.ciudad = ciudad;
		this.fechaIda = ida;
		this.fechaVuelta = vuelta;
		this.tipoIDUsuario = tipoID;
	}
	
	public AID getTipoIDUsuario() {
		return tipoIDUsuario;
	}

	public void setTipoIDUsuario(AID tipoIDUsuario) {
		this.tipoIDUsuario = tipoIDUsuario;
	}

	public String getTipo_solicitud() {
		return tipo_solicitud;
	}

	public void setTipo_solicitud(String tipo_solicitud) {
		this.tipo_solicitud = tipo_solicitud;
	}

	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getFechaIda() {
		return fechaIda;
	}
	public void setFechaIda(String fechaIda) {
		this.fechaIda = fechaIda;
	}
	public String getFechaVuelta() {
		return fechaVuelta;
	}
	public void setFechaVuelta(String fechaVuelta) {
		this.fechaVuelta = fechaVuelta;
	}
	
	
}
