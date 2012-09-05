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

        jade.startAgent(jade.cc, "AT11", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT12", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT21", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT22", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT31", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT32", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT33", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT41", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT42", "AgenteDeTrecho", null);
        jade.startAgent(jade.cc, "AT43", "AgenteDeTrecho", null);

        jade.startAgent(jade.cc, "CH11", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH12", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH13", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH14", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH21", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH22", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH23", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH24", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH31", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH32", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH33", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH34", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH41", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH42", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH43", "AgenteDeEquipamento", null);
        jade.startAgent(jade.cc, "CH44", "AgenteDeEquipamento", null);
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