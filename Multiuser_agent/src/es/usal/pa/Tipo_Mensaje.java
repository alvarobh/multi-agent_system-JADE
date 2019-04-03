package es.usal.pa;

import java.io.Serializable;
import java.util.ArrayList;

import jade.core.AID;

public class Tipo_Mensaje implements Serializable{
	String nombre_hotel;
	ArrayList<String> actividades;
	AID id_usuario;
	public Tipo_Mensaje(String nombre,ArrayList<String> ocio,AID usuario) {
		nombre_hotel=nombre;
		actividades=ocio;
		id_usuario=usuario;
	}
	public String getNombre_hotel() {
		return nombre_hotel;
	}
	public void setNombre_hotel(String nombre_hotel) {
		this.nombre_hotel = nombre_hotel;
	}
	public ArrayList<String> getActividades() {
		return actividades;
	}
	public void setActividades(ArrayList<String> actividades) {
		this.actividades = actividades;
	}
	public AID getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(AID id_usuario) {
		this.id_usuario = id_usuario;
	}
}


