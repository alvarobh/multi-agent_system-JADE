package es.usal.pa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import jade.content.lang.sl.SLCodec;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class Usuario extends Agent{ 
	public void setup() {	
	//Definir los servicios
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		
		AID id_usuario = this.getAID();
		addBehaviour(new CyclicBehaviour(this){
			public void action() {
				// Preguntamos al usuario si desea viaje u ocio
				Scanner sc = new Scanner(System.in);
				String respuesta,agente;
				String destino, fechaIda, fechaVuelta;
				String ciudad, fecha;
				String datosViaje, datosOcio;
				System.out.println("--------------------------------------------------");
				System.out.println("|                Bienvenido                      |");
				System.out.println("--------------------------------------------------");
				System.out.println("¿Desea hacer un viaje?(S/N):");

				respuesta = sc.nextLine();
				if(respuesta.equalsIgnoreCase("s")) {					
					System.out.printf("Destino: ");
					destino = sc.nextLine();
					do {
						do {
							System.out.printf("Fecha ida(1-30): ");
							fechaIda = sc.nextLine();
						}while(1>Integer.parseInt(fechaIda) || Integer.parseInt(fechaIda)>30);
						do {
							System.out.printf("Fecha vuelta(2-31): ");
							fechaVuelta = sc.nextLine();
						}while(2>Integer.parseInt(fechaVuelta) || Integer.parseInt(fechaVuelta)>31);
					}while(Integer.parseInt(fechaVuelta) <= Integer.parseInt(fechaIda));
					
					
					InformacionSolicitud respuesta_alojamiento = new InformacionSolicitud("Alojamiento",destino,fechaIda,fechaVuelta,id_usuario);
					Utils.enviarMensaje(this.myAgent,"peticiones",respuesta_alojamiento, "ontologia");
					
					ACLMessage aclVuelta = this.myAgent.blockingReceive(MessageTemplate.MatchOntology("ontologia_respuesta"));
					//try {
						try {
							Tipo_Mensaje m = (Tipo_Mensaje) aclVuelta.getContentObject();
							System.out.println("--------------------------------------------------");
							System.out.println("|            AQUI ESTA SU RESPUESTA               |");
							System.out.println("--------------------------------------------------");
							if(m.getNombre_hotel().equals("NO")) {
								System.out.println("No se ha podido realizar la reserva");
							}else {
								System.out.println("El nombre de tu hotel es: " +m.getNombre_hotel());
							}
						} catch (UnreadableException e) {
							e.printStackTrace();
						}
				}							
				System.out.println("¿Desea ver actividades de ocio?(S/N)");
				respuesta = sc.nextLine();
					
				if(respuesta.equalsIgnoreCase("s")) {
						// Solicita el usuario la información de la actividad			
						System.out.printf("Ciudad: ");
						ciudad = sc.nextLine();
						do {
						System.out.printf("Fecha(1-31): ");
						fecha = sc.nextLine();
						}while(1>Integer.parseInt(fecha) || Integer.parseInt(fecha)>31);
						
						InformacionSolicitud respuesta_alojamiento = new InformacionSolicitud("Ocio",ciudad,fecha,"",id_usuario);
						Utils.enviarMensaje(this.myAgent,"peticiones",respuesta_alojamiento, "ontologia");
						
						ACLMessage aclVuelta = this.myAgent.blockingReceive(MessageTemplate.MatchOntology("ontologia_respuesta"));
						//esperar mensaje de solicitud aceptada o rechazada
							Tipo_Mensaje m;
							try {
								m = (Tipo_Mensaje) aclVuelta.getContentObject();
								System.out.println("--------------------------------------------------");
								System.out.println("|            AQUI ESTA SU RESPUESTA               |");
								System.out.println("--------------------------------------------------");
								if(m.getActividades() == null) {
									System.out.println("No hay actividades para esa fecha");
								}else {
									System.out.println("Las actividades disponibles son:");
									for(int i=0;i<m.getActividades().size();i++) {
										System.out.println((m.getActividades().get(i)));
									}
								}
								TimeUnit.SECONDS.sleep(2);
								} catch (UnreadableException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				}
			}
		});
		}
}
