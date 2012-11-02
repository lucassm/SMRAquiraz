package model;

import jade.wrapper.ContainerController;
import org.openide.util.Exceptions;

public class LancaAgentes {

    boolean agentesLancados;
    
    public LancaAgentes() {
        
        agentesLancados = false;
        
    }

    public void lancaContainers() {
        
        agentesLancados = true;
        
        //Main-Container
        Jade jade = new Jade();
        jade.startAgent(jade.cc, "AQZ", "AgenteDeSubestacao", null);
        jade.startAgent(jade.cc, "AQZ1", "AgenteDeAlimentador", null);
        jade.startAgent(jade.cc, "AQZ2", "AgenteDeAlimentador", null);
        jade.startAgent(jade.cc, "AQZ3", "AgenteDeAlimentador", null);
        jade.startAgent(jade.cc, "AQZ4", "AgenteDeAlimentador", null);

        jade.startAgent(jade.cc, "AT1", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT2", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT3", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT4", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT5", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT6", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT7", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT8", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT9", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT10", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT11", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT12", "AgenteDeTrecho", null);
        //jade.startAgent(jade.cc, "AT13", "AgenteDeTrecho", null);

        jade.startAgent(jade.cc, "_21I7", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_21I6", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_21I5", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_21I4", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_RC1", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_RL1", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_RL2", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_RL3", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_RL4", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_RP1", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_RP2", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_RS1", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_RS2", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_RS3", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_RS4", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "_RS5", "AgenteDeEquipamento", null);
        
        jade.startAgent(jade.cc, "tcp01", "jade.tools.SocketProxyAgent.SocketProxyAgent", null);
        jade.startAgent(jade.cc, "rma", "jade.tools.rma.rma", null);

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }

        //container 1
        ContainerController cc1 = jade.criaAgenteContainer();
        jade.startAgent(cc1, "JAB", "AgenteDeSubestacao", null);
        jade.startAgent(cc1, "JAB1", "AgenteDeAlimentador", null);

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }

        //container 2
        ContainerController cc2 = jade.criaAgenteContainer();
        jade.startAgent(cc2, "MSJ", "AgenteDeSubestacao", null);
        jade.startAgent(cc2, "MSJ1", "AgenteDeAlimentador", null);

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }

        //container 3
        ContainerController cc3 = jade.criaAgenteContainer();
        jade.startAgent(cc3, "AGF", "AgenteDeSubestacao", null);
        jade.startAgent(cc3, "AGF1", "AgenteDeAlimentador", null);
    }
}