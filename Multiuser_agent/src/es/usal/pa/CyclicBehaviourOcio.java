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

public class CyclicBehaviourOcio extends CyclicBehaviour{

	Ocio o = new Ocio();
	ArrayList<InformacionOcio> datos2 = o.vuelta();
	ArrayList<InformacionOcio> listadoActividades = new ArrayList<>();
	ArrayList<String> lista = new ArrayList<>();
	public CyclicBehaviourOcio(Agent agente) {
		super(agente);
	}
	public void action(){
		System.out.println("--------------------------------------------------");
		System.out.println("|                    Ocio                        |");
		System.out.println("--------------------------------------------------");
		//recibo el mensaje de corteIngles
		ACLMessage acl = this.myAgent.blockingReceive(MessageTemplate.MatchOntology("ontologia_ocio"));
		// proceso de petición
		try {
			InformacionSolicitud p = (InformacionSolicitud) acl.getContentObject();
			System.out.println("La peticion es: " + p.getCiudad() +" en la fecha: "+ p.getFechaIda());
			System.out.println("Ocio ha recibido la peticion");
			//ahora pasamos a comprobar si hay habitaciones libres o no
			for(InformacionOcio io : datos2) {
				
                if(io.getCiudad().equalsIgnoreCase(p.getCiudad())) {
                    
                    int dia = Integer.parseInt(p.getFechaIda());
                    if(io.getDiasMes()[dia-1] > 0) {
                         
                        int[] cambioReserva = io.getDiasMes();                         
                            if(1 == cambioReserva[dia-1]) {
                                listadoActividades.add(io);
                            }
                    }//end of if
                }//end of if
            }//end of for			

			if(listadoActividades.size() == 0){
            	System.out.println("No hay actividades para esa fecha");
            	Tipo_Mensaje tm = new Tipo_Mensaje("",null,p.getTipoIDUsuario());
            	Utils.enviarMensaje(this.myAgent,"peticiones",tm,"ontologia_solicitud_ocio");
            }else {
            	int i=0;
				System.out.println("La/s actividad/es disponible/s para esa fecha son:");
				for(InformacionOcio ocio:listadoActividades) {
					System.out.println(ocio.getActividad());
					lista.add(ocio.getActividad());
				} 
				Tipo_Mensaje tm = new Tipo_Mensaje("",lista,p.getTipoIDUsuario());
				Utils.enviarMensaje(this.myAgent,"peticiones",tm,"ontologia_solicitud_ocio");
            }
			listadoActividades.clear();
           
		} catch (UnreadableException e) {
			e.printStackTrace();
		}
	}
}
