package es.usal.pa;

import java.util.ArrayList;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Ocio extends Agent{
	//metodo para generar la base de datos de los hoteles
		public ArrayList<InformacionOcio> vuelta(){
			ArrayList<InformacionOcio> ocio = new ArrayList<>();
			InformacionOcio ocio1 = new InformacionOcio("Vigo","Vela",true,true);
			InformacionOcio ocio2 = new InformacionOcio("Plasencia","Escalada",true,true);
			InformacionOcio ocio3 = new InformacionOcio("Madrid","Patinaje",true,true);
			InformacionOcio ocio4 = new InformacionOcio("Madrid","Open Tenis",true,false);
			int[] diasOperativo = {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			InformacionOcio ocio5 = new InformacionOcio("Madrid","Mercado Medieval",diasOperativo);
			ocio.add(ocio1);
			ocio.add(ocio2);
			ocio.add(ocio3);
			ocio.add(ocio4);
			ocio.add(ocio5);
			return ocio;
		}
	public void setup() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setName("Servicio Mensaje");
		//Establezco el tipo del servicio para poder localizarlo cuando haga una busqueda
		sd.setType("Ocio");//tenemos que cambiar enviarMensaje
		sd.addOntologies("ontologia_ocio");//----------------------------------------------------------------------
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
		addBehaviour(new CyclicBehaviourOcio(this));
	}
}
