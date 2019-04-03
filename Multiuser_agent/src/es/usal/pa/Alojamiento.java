package es.usal.pa;

import java.util.ArrayList;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Alojamiento extends Agent{
	
	//metodo para generar la base de datos de los hoteles
	public ArrayList<InformacionHotel> vuelta(){
		ArrayList<InformacionHotel> hoteles = new ArrayList<>();
		InformacionHotel hotel1 = new InformacionHotel("Vigo","Playa Samil",2);
		InformacionHotel hotel2 = new InformacionHotel("Plasencia","Parador",1);
		InformacionHotel hotel3 = new InformacionHotel("Madrid","Plaza Colon",2);
		InformacionHotel hotel4 = new InformacionHotel("Madrid","Bernabeu",1);
		InformacionHotel hotel5 = new InformacionHotel("Madrid","Cibeles",3);
		hoteles.add(hotel1);
		hoteles.add(hotel2);
		hoteles.add(hotel3);
		hoteles.add(hotel4);
		hoteles.add(hotel5);
		return hoteles;
	}
	public void setup() {		
		//------------------------------------------
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setName("Servicio Mensaje");
		
		//Establezco el tipo del servicio para poder localizarlo cuando haga una busqueda
		sd.setType("Alojamiento");
		
		sd.addOntologies("ontologia_alojamiento");//--------------------------------------------------------------------
		
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
		addBehaviour(new CyclicBehaviourAlojamiento(this));
	}
	
}
