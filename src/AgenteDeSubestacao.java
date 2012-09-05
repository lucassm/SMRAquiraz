
/*****************************************************
 **
 **			AGENTE DE SUBESTAÇÃO
 ** 
 *****************************************************/
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.FIPANames;
import jade.lang.acl.*;
import jade.proto.AchieveREInitiator;
import jade.proto.ContractNetInitiator;
import jade.proto.ContractNetResponder;
import jade.proto.SubscriptionInitiator;

public class AgenteDeSubestacao extends Agent {

    MessageTemplate filtro1 = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.INFORM),
            MessageTemplate.MatchContent("problema"));
    MessageTemplate filtro2 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.CFP), MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET));
    Element agenteSubestacaoBD = null;
    int codigo = 1;

    public void setup() {

        agenteSubestacaoBD = carregarBD(this.getLocalName());

        addBehaviour(new TickerBehaviour(this, 100) {

            @Override
            protected void onTick() {

                ACLMessage msg = receive(filtro1);
                if (msg != null) {
                    List agentesChave = agenteSubestacaoBD.getChild("agentesAE").getChildren();
                    ACLMessage msg1 = new ACLMessage(ACLMessage.REQUEST);
                    msg1.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                    msg1.setContent("Abrir");
                    
                    for (int i = 0; i < agentesChave.size(); i++) {
                        Element agenteChave = (Element) agentesChave.get(i);
                        msg1.addReceiver(new AID(agenteChave.getName(), AID.ISLOCALNAME));
                    }
                    
                    /**
                     * Comportamento FIPA-Request
                     */
                    addBehaviour(new AchieveREInitiator(myAgent, msg1){
                        
                        protected void handleInform(ACLMessage inform){
                            exibirMensagem(inform);
                        }
                    });//fim do comportamento AchieveREInitiator
                    
                    ACLMessage msg2 = new ACLMessage(ACLMessage.SUBSCRIBE);
                    msg2.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
                    msg2.addReceiver(new AID(agenteSubestacaoBD.getChild("agentesAA").getChild("AQZ1").getName(), AID.ISLOCALNAME));
                    msg2.setContent("Perda de SE");
                    
                    /**
                     * Comportamento FIPA-Subscribe
                     */
                    addBehaviour(new SubscriptionInitiator(myAgent, msg2){
                        
                        protected void handleAgree(ACLMessage agree){
                            
                            exibirMensagem(agree);
                        }
                        
                        protected void handleInform(ACLMessage inform){
                            
                            exibirMensagem(inform);
                            
                            codigo++;
                            
                            List agentesAA = agenteSubestacaoBD.getChild("agentesAA").getChildren();
                            
                            for (int i = 0; i < agentesAA.size(); i++) {
                                
                                Element agenteAA = (Element) agentesAA.get(i);
                                int codigoAgente = Integer.parseInt(agenteAA.getAttributeValue("codigo"));
                                
                                if (codigoAgente == codigo) {
                                    
                                    ACLMessage msg = new ACLMessage(ACLMessage.SUBSCRIBE);
                                    msg.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
                                    msg.addReceiver(new AID(agenteAA.getName(),AID.ISLOCALNAME));
                                    msg.setContent("Perda de SE");
                                    this.reset(msg);
                                    break;
                                }//fim do if
                                
                            }//fim do for
                            
                        }//fim do método handleInform
                        
                    });//fim do comportamento FIPA-SubscribeInitiator
                    
                }//fim do if
            }
        });//fim do comportamento TickerBehaviour


    }// fim do método setup

    /**
     * Método exibirMensagem
     * 
     * @param msg
     */
    public void exibirMensagem(ACLMessage msg) {

        System.out.println("\n\n===============<<MENSAGEM>>================");
        System.out.println("De: " + msg.getSender());
        System.out.println("Para: " + this.getName());
        System.out.println("Conteudo: " + msg.getContent());
        System.out.println("===========================================");
    }// fim do método exibirMenssagem

    /**
     * Método carregarBD
     * 
     * @param nomeAgente
     * @return agenteBD
     */
    public Element carregarBD(String nomeAgente) {

        File file = null;
        SAXBuilder builder = null;
        Document doc = null;
        Element agenteBD = null;

        file = new File("src/xml/" + nomeAgente + ".xml");
        builder = new SAXBuilder();

        try {
            doc = builder.build(file);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }// fim do try

        agenteBD = doc.getRootElement();
        return agenteBD;

    }// fim do método carregarBD
}