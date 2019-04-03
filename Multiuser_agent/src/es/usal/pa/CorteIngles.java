package es.usal.pa;

import java.util.ArrayList;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class CorteIngles extends Agent{
	@Override 
	protected void setup()
	{		
		//Definir los servicios
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setName("Servicio Mensaje");
		
		//Establezco el tipo del servicio para poder localizarlo cuando haga una busqueda
		sd.setType("peticiones");
		
		sd.addOntologies("ontologia_alojamiento");
		sd.addOntologies("ontologia_ocio");
		sd.addOntologies("ontologia");
		sd.addOntologies("ontologia_solicitud_ocio");
		sd.addOntologies("ontologia_solicitud_alojamiento");
		
		//El agente que quiere usar el servicio tiene que "hablar" el lenguaje FIPA-SL
		sd.addLanguages(new SLCodec().getName());
		
		dfd.addServices(sd);
		
		//Avisar al DF de que este agente da el servicio definido
		try 
		{
			DFService.register(this, dfd);
		} 
		catch (FIPAException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addBehaviour(new CyclicBehaviourPeticiones(this));
	}
}
