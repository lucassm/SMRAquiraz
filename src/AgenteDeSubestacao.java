
// AgenteDeSubestacao.java
// Copyright (C) 2012 Lucas S Melo <lucas@lucas-laptop>
//
// SMRAquiraz é um software livre; você pode redistribui-lo e/ou
// modifica-lo dentro dos termos da Licença Pública Geral GNU como
// publicada pela Fundação do Software Livre (FSF); na versão 3 da
// Licença, ou (na sua opnião) qualquer versão.
//
// Este programa é distribuido na esperança que possa ser  util,
// mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer
// MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
// Licença Pública Geral GNU para maiores detalhes.
// 
// Você deve ter recebido uma cópia da Licença Pública Geral GNU
// junto com este programa, se não, acesse <http://www.gnu.org/licenses/>


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

    MessageTemplate filtro1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST));
    MessageTemplate filtro2 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.CFP), MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET));
    ACLMessage msgInfoProblem = null;
    Element agenteSubestacaoBD = null;
    int codigo = 1;
    String content = null;

    public void setup() {

        agenteSubestacaoBD = carregarBD(this.getLocalName());

        addBehaviour(new TickerBehaviour(this, 100) {

            @Override
            protected void onTick() {

                msgInfoProblem = receive(filtro1);
                if (msgInfoProblem != null) {
                    
                    content = msgInfoProblem.getContent();
                    List agentesChave = agenteSubestacaoBD.getChild("agentesAE").getChildren();
                    ACLMessage msgAbrirAEs = new ACLMessage(ACLMessage.REQUEST);
                    msgAbrirAEs.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                    msgAbrirAEs.setContent("Abrir");

                                        for (int i = 0; i < agentesChave.size(); i++) {
                        Element agenteChave = (Element) agentesChave.get(i);
                        msgAbrirAEs.addReceiver(new AID(agenteChave.getName(), AID.ISLOCALNAME));
                    }

                    /**
                     * Comportamento FIPA-Request
                     */
                    addBehaviour(new AchieveREInitiator(myAgent, msgAbrirAEs) {

                        protected void handleInform(ACLMessage inform) {
                            exibirMensagem(inform);
                        }
                    });//fim do comportamento AchieveREInitiator

                    ACLMessage msgSubscribeAA = new ACLMessage(ACLMessage.SUBSCRIBE);
                    msgSubscribeAA.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
                    msgSubscribeAA.addReceiver(new AID(agenteSubestacaoBD.getChild("agentesAA").getChild("AQZ1").getName(), AID.ISLOCALNAME));
                    msgSubscribeAA.setContent(msgInfoProblem.getContent());

                    /**
                     * Comportamento FIPA-Subscribe
                     */
                    addBehaviour(new SubscriptionInitiator(myAgent, msgSubscribeAA) {

                        protected void handleAgree(ACLMessage agree) {

                            exibirMensagem(agree);
                        }

                        protected void handleInform(ACLMessage inform) {

                            exibirMensagem(inform);

                            codigo++;

                            List agentesAA = agenteSubestacaoBD.getChild("agentesAA").getChildren();

                            for (int i = 0; i < agentesAA.size(); i++) {

                                Element agenteAA = (Element) agentesAA.get(i);
                                int codigoAgente = Integer.parseInt(agenteAA.getAttributeValue("codigo"));

                                if (codigoAgente == codigo) {

                                    ACLMessage msg = new ACLMessage(ACLMessage.SUBSCRIBE);
                                    msg.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
                                    msg.addReceiver(new AID(agenteAA.getName(), AID.ISLOCALNAME));
                                    msg.setContent(content);
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