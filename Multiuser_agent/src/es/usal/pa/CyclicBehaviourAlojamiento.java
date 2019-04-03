package es.usal.pa;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CyclicBehaviourAlojamiento extends CyclicBehaviour{
	Alojamiento p = new Alojamiento();
	InformacionHotel h = new InformacionHotel();
	ArrayList<InformacionHotel> datos = p.vuelta();

	public CyclicBehaviourAlojamiento(Agent agente) {
		super(agente);
	}
	public void action(){
		System.out.println("--------------------------------------------------");
		System.out.println("|                ALOJAMIENTO                     |");
		System.out.println("--------------------------------------------------");

		ACLMessage acl = this.myAgent.blockingReceive(MessageTemplate.MatchOntology("ontologia_alojamiento"));
		// proceso de petición
		try {
			InformacionSolicitud p = (InformacionSolicitud) acl.getContentObject();
			int w=0;
			boolean bandera=true;
			boolean denegar=true;
			for(InformacionHotel ih : datos) {
				w++;
				if(ih.getCiudadHotel().equalsIgnoreCase(p.getCiudad())) {
					bandera=false;
					int dia = Integer.parseInt(p.getFechaIda());
					int vuelta = Integer.parseInt(p.getFechaVuelta());
					
					 boolean h=true;
						 for(int i = dia; i < vuelta;i++){
						 	if(ih.getDiasMes()[i] == 0){
						 		h=false;
						 	}
						 }
					if(h) {
						System.out.println("Realizando reserva...");
						int[] cambioReserva = ih.getDiasMes();
						int temp,k=0;
						for(int i=0;i<30;i++) {
							if(i==(dia-1)) {
								temp=dia-1;
								while((vuelta-1)>=temp) {
									cambioReserva[i+k]-=1;
									k++;
									temp++;
									ih.setDiasMes(cambioReserva);
								}
							}
						}
						denegar=false;
						Tipo_Mensaje mensajeria = new Tipo_Mensaje(ih.getNombreHotel(),null,p.getTipoIDUsuario());
						Utils.enviarMensaje(this.myAgent,"peticiones",mensajeria,"ontologia_solicitud_alojamiento");
						break;
					}
				}
			}
			if(denegar){
				Tipo_Mensaje mensajeria = new Tipo_Mensaje("NO",null,p.getTipoIDUsuario());
				Utils.enviarMensaje(this.myAgent,"peticiones",mensajeria,"ontologia_solicitud_alojamiento");
			}
			if(bandera) {
			Tipo_Mensaje mensajeria = new Tipo_Mensaje("NO",null,p.getTipoIDUsuario());
			Utils.enviarMensaje(this.myAgent,"peticiones",mensajeria,"ontologia_solicitud_alojamiento");
			}
		} catch (UnreadableException e) {
			e.printStackTrace();
		}
	}
}
