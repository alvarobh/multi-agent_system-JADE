package es.usal.pa;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.states.MsgReceiver;


public class CyclicBehaviourPeticiones extends CyclicBehaviour{
		
	public CyclicBehaviourPeticiones(Agent agente) {
		super(agente);
	}
	@Override
	public void action() {
		System.out.println("--------------------------------------------------");
		System.out.println("|                CorteIngles                     |");
		System.out.println("--------------------------------------------------");
		ACLMessage acl = this.myAgent.blockingReceive();
		if(acl.getOntology().equals("ontologia")){
			try {
			InformacionSolicitud p = (InformacionSolicitud) acl.getContentObject();			
			if(p.getTipo_solicitud().equals("Alojamiento")) {
				Utils.enviarMensaje(this.myAgent,"Alojamiento",acl.getContentObject(),"ontologia_alojamiento");
			}else {
				Utils.enviarMensaje(this.myAgent,"Ocio",acl.getContentObject(),"ontologia_ocio");
			}
			//enviar mensaje, con ciertos cambios, como en el ejemplo de clase
			} catch (UnreadableException e) {
				e.printStackTrace();
			}
		}
		if(acl.getOntology().equals("ontologia_solicitud_alojamiento")) {
				// proceso de petición
				try {
					Tipo_Mensaje p = (Tipo_Mensaje) acl.getContentObject();
					if(p.getNombre_hotel().equals("NO")) {
						System.err.println("No ha sido posible efectuar la reserva");
					}else {
						System.out.println("Se ha efectuado una reserva en el hotel: " + p.getNombre_hotel());
					}
	            	ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
		        	aclMessage.addReceiver(p.getId_usuario());
	                aclMessage.setOntology("ontologia_respuesta");
	                //el lenguaje que se define para el servicio
	                aclMessage.setLanguage(new SLCodec().getName());
	                //el mensaje se transmita en XML
	                aclMessage.setEnvelope(new Envelope());
					//cambio la codificacion de la carta
					aclMessage.getEnvelope().setPayloadEncoding("ISO8859_1");
	                //aclMessage.getEnvelope().setAclRepresentation(FIPANames.ACLCodec.XML); 
	        		aclMessage.setContentObject((Serializable) p);
	        		this.myAgent.send(aclMessage);  
				} catch (UnreadableException e) {
				e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if(acl.getOntology().equals("ontologia_solicitud_ocio")) {
				//proceso de petición
				try {
					Tipo_Mensaje ioA = (Tipo_Mensaje)acl.getContentObject();
					System.out.println("Devolviendo respuesta de ocio");
					ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
		        	aclMessage.addReceiver(ioA.getId_usuario());
	                aclMessage.setOntology("ontologia_respuesta");
	                //el lenguaje que se define para el servicio
	                aclMessage.setLanguage(new SLCodec().getName());
	                //el mensaje se transmita en XML
	                aclMessage.setEnvelope(new Envelope());
					//cambio la codificacion de la carta
					aclMessage.getEnvelope().setPayloadEncoding("ISO8859_1");
	                //aclMessage.getEnvelope().setAclRepresentation(FIPANames.ACLCodec.XML); 
	        		aclMessage.setContentObject((Serializable) ioA);
	        		this.myAgent.send(aclMessage);  
				} catch (UnreadableException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
		}
	}
}
