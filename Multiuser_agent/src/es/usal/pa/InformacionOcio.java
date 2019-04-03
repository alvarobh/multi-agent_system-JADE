package es.usal.pa;

import java.io.Serializable;
import java.util.ArrayList;

import jade.core.AID;

public class InformacionOcio implements Serializable{
	String ciudad;
	String actividad;
	int[] diasMes = new int[31];
	AID tipoIDUsuario;
	ArrayList<InformacionOcio> ocio;
	
	public InformacionOcio(String ciudad,String actividad,boolean respuesta,boolean respuesta2) {
		this.ciudad = ciudad;
		this.actividad = actividad;
		if(respuesta) {
			for(int i=0;i<15;i++) {
				diasMes[i]=1;
			}
		}else {
			for(int j=0;j<15;j++){
				diasMes[j]=0;
			}
		}
		if(respuesta2) {
			for(int i=15;i<=30;i++) {
				diasMes[i]=1;
			}
		}else {
			for(int j=15;j<=30;j++){
				diasMes[j]=0;
			}
		}
	}
	public InformacionOcio(String nombre,String actividad, int[] dias) {
		this.ciudad = nombre;
		this.actividad = actividad;
		this.diasMes = dias;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public int[] getDiasMes() {
		return diasMes;
	}
	public void setDiasMes(int[] diasMes) {
		this.diasMes = diasMes;
	}
	public ArrayList<InformacionOcio> getOcio() {
		return ocio;
	}
	public void setOcio(ArrayList<InformacionOcio> ocio) {
		this.ocio = ocio;
	}
	public InformacionOcio() {
		this.ocio = new ArrayList<>();
	}
}
