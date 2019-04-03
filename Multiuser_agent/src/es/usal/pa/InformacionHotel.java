package es.usal.pa;

import java.util.ArrayList;

import jade.core.AID;
import jade.util.leap.Serializable;

public class InformacionHotel implements Serializable{
	//creamos variables
	String ciudadHotel;
	String nombreHotel;
	int numHab;
	int[] diasMes = new int[31];
	AID tipoIDUsuario;
	ArrayList<InformacionHotel> hoteles;
	
	public InformacionHotel(String ciudad,String nombre,int num){
		ciudadHotel = ciudad;
		nombreHotel = nombre;
		this.numHab = num;
		for(int i=0;i<=30;i++) {
			diasMes[i] = num;
		}
	}
	public AID getTipoIDUsuario() {
		return tipoIDUsuario;
	}
	public void setTipoIDUsuario(AID tipoIDUsuario) {
		this.tipoIDUsuario = tipoIDUsuario;
	}
	public InformacionHotel() {
		this.hoteles = new ArrayList<>();
	}

	public String getCiudadHotel() {
		return ciudadHotel;
	}

	public void setCiudadHotel(String ciudadHotel) {
		this.ciudadHotel = ciudadHotel;
	}

	public String getNombreHotel() {
		return nombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}

	public int getNumHab() {
		return numHab;
	}

	public void setNumHab(int numHab) {
		this.numHab = numHab;
	}
	public ArrayList<InformacionHotel> getHoteles(){
		return hoteles;
	}
	public void setHoteles(ArrayList<InformacionHotel> hoteles){
		this.hoteles = hoteles;
	}

	public int[] getDiasMes() {
		return diasMes;
	}

	public void setDiasMes(int[] diasMes) {
		this.diasMes = diasMes;
	}
}
