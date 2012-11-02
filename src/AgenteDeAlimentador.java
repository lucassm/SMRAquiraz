
// AgenteDeAlimentador.java
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
 **			AGENTE DE ALIMENTADOR
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
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.domain.FIPANames;
import jade.proto.AchieveREResponder;
import jade.proto.ContractNetInitiator;
import jade.proto.ContractNetResponder;
import jade.proto.SubscriptionInitiator;
import jade.proto.AchieveREInitiator;
import jade.proto.SubscriptionResponder;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import model.Funcionalidades;
import org.openide.util.Exceptions;

public class AgenteDeAlimentador extends Agent {

    //Variaveis de Campo
    public MessageTemplate filtro1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchEncoding("curto"));
    public MessageTemplate filtro2 = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
            MessageTemplate.MatchPerformative(ACLMessage.CFP));
    public MessageTemplate filtro3 = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE), MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE));
    public MessageTemplate filtro4 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchContent("atualizar"));
    public MessageTemplate filtro5 = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST), MessageTemplate.MatchEncoding("modificaBD"));
    public MessageTemplate filtro6 = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST), MessageTemplate.MatchEncoding("solicitaBD"));
    public AID melhorPropositor = null;
    public ACLMessage cfpMessage = null;
    public OneShotBehaviour comportamentoDeInicializacao = null;
    public OneShotBehaviour comportamentoDePrioridade = null;
    public ContractNetInitiator protocoloContractNetInitiator = null;
    public ContractNetResponder protocoloContractNetResponder = null;
    public SubscriptionInitiator protocoloSubscriptionInitiator = null;
    public SubscriptionResponder protocoloSubscriptionResponder = null;
    public AchieveREResponder protocoloAchieveREResponder = null;
    public AchieveREResponder protocoloAchieveREResponder2 = null;
    public AchieveREResponder comportamentoModificaBD = null;
    public AchieveREResponder comportamentoSolicitaBD = null;
    public TickerBehaviour comportamentoTemporal100ms = null;
    public TickerBehaviour comportamentoTemporal1minuto = null;
    public double corrente = 0;
    public double maxCorrente = 0;
    public int cont = 0;
    public int cont2 = 0;
    public Element agenteAlimentadorBD = null;
    public Element agenteAlimentadorBDAux = null;
    public String horario = null;
    public Funcionalidades funcionalidades = null;
    

    public void setup() {


        /***********************************************************************
         *                                                                     *
         *          Comportamento OneShot de Inicialização do agente           * 
         *                                                                     * 
         **********************************************************************/
        comportamentoDeInicializacao = new OneShotBehaviour(this) {

            @Override
            public void action() {

                funcionalidades = new Funcionalidades(myAgent);
                agenteAlimentadorBD = funcionalidades.carregarBD(myAgent.getLocalName());
            }
        };

        /***********************************************************************
         *                                                                     *
         *          Comportamento OneShot de Prioridade                        * 
         *                                                                     * 
         **********************************************************************/
        comportamentoDePrioridade = new OneShotBehaviour(this) {

            @Override
            public void action() {

                protocoloContractNetInitiator.reset(cfpMessage);
            }
        };

        /***********************************************************************
         *                                                                     *
         *          Comportamento Modificação do Banco de Dados                * 
         *                                                                     * 
         **********************************************************************/
        comportamentoModificaBD = new AchieveREResponder(this, filtro5) {

            public ACLMessage handleRequest(ACLMessage request) {

                try {
                    agenteAlimentadorBD = (Element) request.getContentObject();
                } catch (UnreadableException ex) {
                    Exceptions.printStackTrace(ex);
                }

                System.out.println("Corrente Disponivel Recebida: " + agenteAlimentadorBD.getChild("correnteDisponivel").getAttributeValue("valor"));

                ACLMessage inform = request.createReply();
                inform.setPerformative(ACLMessage.INFORM);
                inform.setContent("BD Modificado");
                return inform;
            }
        };


        /***********************************************************************
         *                                                                     *
         *          Comportamento Solicitação do Banco de Dados                * 
         *                                                                     * 
         **********************************************************************/
        comportamentoSolicitaBD = new AchieveREResponder(this, filtro6) {

            public ACLMessage handleRequest(ACLMessage request) {

                ACLMessage inform = request.createReply();
                inform.setPerformative(ACLMessage.INFORM);

                try {
                    inform.setContentObject(agenteAlimentadorBD);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }

                return inform;
            }
        };

        /***********************************************************************
         *                                                                     *
         *          Comportamento FIPA-ContractNetInitiator                    * 
         *                                                                     * 
         **********************************************************************/
        ACLMessage msgACLMessage = new ACLMessage(ACLMessage.CFP);
        protocoloContractNetInitiator = new ContractNetInitiator(this, msgACLMessage) {

            /**
             * Método handlePropose
             * 
             * Este método exibe as mensagens enviadas pelos propositores
             * 
             */
            protected void handlePropose(ACLMessage propose, Vector v) {

                funcionalidades.exibirMensagem(propose);
            }

            /**
             * Método handleRefuse
             * 
             * Este método exibe as mensagens de recusa enviadas pelos propositores
             * 
             */
            protected void handleRefuse(ACLMessage refuse) {

                funcionalidades.exibirMensagem(refuse);
            }

            /**
             * Método handleAllresponses
             * 
             * Este método seleciona a melhor proposta enviada pelos propositores
             * e retorna com uma mensagem ACCEPT_PROPOSE para o melhor propositor
             * e com uma mensagem REJECT_PROPOSE para os demais.
             * 
             */
            protected void handleAllResponses(Vector respostas, Vector aceitas) {

                melhorPropositor = null;
                maxCorrente = 0;
                Enumeration e1 = respostas.elements();
                float correnteNecessaria = 0;
                String agenteRecurso = null;
                boolean mesmoAlimentador = false;


                while (e1.hasMoreElements()) {

                    ACLMessage msg = (ACLMessage) e1.nextElement();
                    if (msg.getPerformative() != ACLMessage.REFUSE) {

                        Element agenteDeAlimentadorBD2 = null;
                        try {
                            agenteDeAlimentadorBD2 = (Element) msg.getContentObject();
                        } catch (UnreadableException e) {
                            e.printStackTrace();
                        }

                        agenteRecurso = agenteAlimentadorBD.getChild("agentesAA").getChild(agenteDeAlimentadorBD2.getAttributeValue("nome")).getAttributeValue("recurso");
                        correnteNecessaria = Float.parseFloat(agenteAlimentadorBD.getChild("agentesAT").getChild(agenteRecurso).getAttributeValue("carregamento"));

                        corrente = Float.parseFloat(agenteDeAlimentadorBD2.getChild("correnteDisponivel").getAttributeValue("valor"));

                        if (corrente >= correnteNecessaria) {
                            if (agenteDeAlimentadorBD2.getAttributeValue("nome").contains("AQZ")) {
                                if (mesmoAlimentador) {
                                    if (corrente > maxCorrente) {
                                        maxCorrente = corrente;
                                        melhorPropositor = msg.getSender();
                                    }//fim do if (corrente>maxCorrente)
                                } else {
                                    maxCorrente = corrente;
                                    melhorPropositor = msg.getSender();
                                }//fim do if (mesmoAlimentador)

                                mesmoAlimentador = true;

                            } else {
                                if (corrente > maxCorrente && !mesmoAlimentador) {
                                    maxCorrente = corrente;
                                    melhorPropositor = msg.getSender();
                                }//fim do if (corrente>maxCorrente && !mesmoAlimentador)

                            }//fim do if(agenteDeAlimentadorBD2.getAttributeValue("nome").contains("AQZ"))

                        }//fim do if(potencia>maxpotencia)

                    }//fim do if (msgPerformative != ACLMessage.REFUSE)

                }//fim do while(e.hasMoreElements())

                /*
                 * Este If Verifica se nenhuma proposta foi aceita pelo agente
                 * de Alimentador Solicitante.
                 */
                if (melhorPropositor == null) {

                    Enumeration propostas = respostas.elements();
                    Element agenteAABD = null;
                    Element agenteAABDSelecionado = null;
                    while (propostas.hasMoreElements()) {
                        ACLMessage proposta = (ACLMessage) propostas.nextElement();
                        try {
                            agenteAABD = (Element) proposta.getContentObject();
                        } catch (UnreadableException ex) {
                            Exceptions.printStackTrace(ex);
                        }

                        List agentesAA = agenteAlimentadorBD.getChild("agentesAA").getChildren();

                        for (int i = 0; i < agentesAA.size(); i++) {
                            Element agenteAA = (Element) agentesAA.get(i);

                            if (agenteAABD.getAttributeValue("estado").equalsIgnoreCase("recompondo")) {
                                agenteAABDSelecionado = agenteAABD;
                                break;
                            }
                        }//fim do for()

                    }//fim do while()

                    /*
                     * Este If verifica se algum dos agentes propositores estão
                     * Recompondo algum trecho.
                     */
                    if (agenteAABDSelecionado != null) {


                        String tipoDePrioridade = agenteAlimentadorBD.getChild("prioridade").getAttributeValue("valor");

                        /*
                         * Este If seleciona o tipo de prioridade do sistema
                         */
                        if (tipoDePrioridade.equalsIgnoreCase("carga")) {

                            double corrente1 = 0;
                            double corrente2 = 0;
                            List agentesAT1 = agenteAlimentadorBD.getChild("agentesAT").getChildren();
                            for (int i = 0; i < agentesAT1.size(); i++) {

                                Element agenteAT = (Element) agentesAT1.get(i);
                                if (agenteAT.getAttributeValue("afetado").equalsIgnoreCase("sim")) {
                                    corrente1 += Double.parseDouble(agenteAT.getAttributeValue("carregamento"));
                                }
                            }
                            
                            if (corrente1 > corrente2) {

                                String agenteRecomposto = agenteAABDSelecionado.getChild("agenteRecomposto").getAttributeValue("valor");
                                Element agenteRecompostoBD = funcionalidades.carregarBD(agenteRecomposto);
                                List agentesAE = agenteRecompostoBD.getChild("agentesAE").getChildren();

                                //mensagem para abrir equipamentos chave
                                ACLMessage msgAbreEquipamento = new ACLMessage(ACLMessage.REQUEST);
                                msgAbreEquipamento.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                                msgAbreEquipamento.setContent("Abrir");

                                for (int i = 0; i < agentesAE.size(); i++) {
                                    Element agenteAE = (Element) agentesAE.get(i);

                                    if (agenteAE.getAttributeValue("tipo").equalsIgnoreCase("encontro")) {
                                        msgAbreEquipamento.addReceiver(new AID(agenteAE.getName(), AID.ISLOCALNAME));
                                    }
                                }

                                /***************************************************************
                                 * 
                                 * Comportamento FIPA-Request Iniciante para abrir chaves
                                 * 
                                 **************************************************************/
                                addBehaviour(new AchieveREInitiator(myAgent, msgAbreEquipamento) {

                                    protected void handleInform(ACLMessage inform) {

                                        funcionalidades.exibirMensagem(inform);
                                        //agenteAlimentadorBDAux.getChild("agentesAE").getChild(inform.getSender().getLocalName()).setAttribute("estado", "aberto");
                                    }
                                });//fim do protocolo FIPA-Request Iniciante para abrir chaves

                                double correnteCedida = Double.parseDouble(agenteAABDSelecionado.getChild("correnteCedida").getAttributeValue("valor"));

                                double correnteDisponivel = Double.parseDouble(agenteAABDSelecionado.getChild("correnteDisponivel").getAttributeValue("valor"));

                                agenteAABDSelecionado.getChild("correnteDisponivel").setAttribute("valor", String.valueOf(correnteCedida + correnteDisponivel));

                                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                                msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                                msg.setEncoding("modificaBD");
                                msg.addReceiver(new AID(agenteAABDSelecionado.getAttributeValue("nome"), AID.ISLOCALNAME));

                                try {
                                    msg.setContentObject(agenteAABDSelecionado);
                                } catch (IOException ex) {
                                    Exceptions.printStackTrace(ex);
                                }

                                addBehaviour(new AchieveREInitiator(myAgent, msg) {

                                    @Override
                                    protected void handleInform(ACLMessage inform) {
                                        super.handleInform(inform);
                                    }
                                });

                                addBehaviour(comportamentoDePrioridade);
                            }//fim do if (corrente1>corrente2)

                        } else if (tipoDePrioridade.equalsIgnoreCase("clientes")) {

                            double clientes1 = 0;
                            double clientes2 = 0;
                            List agentesAT1 = agenteAlimentadorBD.getChild("agentesAT").getChildren();

                            for (int i = 0; i < agentesAT1.size(); i++) {

                                Element agenteAT = (Element) agentesAT1.get(i);
                                if (agenteAT.getAttributeValue("afetado").equalsIgnoreCase("sim")) {
                                    clientes1 += Double.parseDouble(agenteAT.getAttributeValue("clientes"));
                                }
                            }

                            ACLMessage msgSolicitaBD = new ACLMessage(ACLMessage.REQUEST);
                            msgSolicitaBD.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                            msgSolicitaBD.setEncoding("solicitaBD");
                            msgSolicitaBD.addReceiver(new AID(agenteAABDSelecionado.getChild("agenteRecomposto").getAttributeValue("nome"), AID.ISLOCALNAME));
                            msgSolicitaBD.setContent("solicitaBD");

                            addBehaviour(new AchieveREInitiator(myAgent, msgSolicitaBD) {

                                @Override
                                protected void handleInform(ACLMessage inform) {

                                    try {
                                        agenteAlimentadorBDAux = (Element) inform.getContentObject();
                                        agenteAlimentadorBDAux.getAttributeValue("nome");
                                    } catch (UnreadableException ex) {
                                        Exceptions.printStackTrace(ex);
                                    }
                                }
                            });

                            if (agenteAlimentadorBDAux.getAttributeValue("nome").equalsIgnoreCase(agenteAABDSelecionado.getChild("agenteRecomposto").getAttributeValue("nome"))) {
                                
                                List agentesAT2 = agenteAlimentadorBDAux.getChild("agentesAT").getChildren();

                                for (int i = 0; i < agentesAT2.size(); i++) {

                                    Element agenteAT = (Element) agentesAT2.get(i);

                                    if (agenteAT.getAttributeValue("afetado").equalsIgnoreCase("sim")) {
                                        clientes2 += Double.parseDouble(agenteAT.getAttributeValue("clientes"));
                                    }
                                }

                                if (clientes1 > clientes2) {

                                    //mensagem para abrir equipamentos chave
                                    ACLMessage msgAbreEquipamento = new ACLMessage(ACLMessage.REQUEST);
                                    msgAbreEquipamento.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                                    msgAbreEquipamento.setContent("Abrir");

                                    List agentesAE = agenteAlimentadorBDAux.getChild("agentesAE").getChildren();

                                    for (int i = 0; i < agentesAE.size(); i++) {
                                        Element agenteAE = (Element) agentesAE.get(i);

                                        if (agenteAE.getAttributeValue("tipo").equalsIgnoreCase("encontro")) {
                                            msgAbreEquipamento.addReceiver(new AID(agenteAE.getName(), AID.ISLOCALNAME));
                                        }
                                    }

                                    /***************************************************************
                                     * 
                                     * Comportamento FIPA-Request Iniciante para abrir chaves
                                     * 
                                     **************************************************************/
                                    addBehaviour(new AchieveREInitiator(myAgent, msgAbreEquipamento) {

                                        protected void handleInform(ACLMessage inform) {

                                            funcionalidades.exibirMensagem(inform);
                                            //agenteAlimentadorBDAux.getChild("agentesAE").getChild(inform.getSender().getLocalName()).setAttribute("estado", "aberto");
                                        }
                                    });//fim do protocolo FIPA-Request Iniciante para abrir chaves

                                    System.out.println(agenteAABDSelecionado.getAttributeValue("nome"));
                                    System.out.println(agenteAABDSelecionado.getChild("correnteCedida").getAttributeValue("valor"));
                                    double correnteCedida = Double.parseDouble(agenteAABDSelecionado.getChild("correnteCedida").getAttributeValue("valor"));
                                    
                                    double correnteDisponivel = Double.parseDouble(agenteAABDSelecionado.getChild("correnteDisponivel").getAttributeValue("valor"));
                                    System.out.println("Corrente Disponivel: " + String.valueOf(correnteCedida + correnteDisponivel));

                                    agenteAABDSelecionado.getChild("correnteDisponivel").setAttribute("valor", String.valueOf(correnteCedida + correnteDisponivel));

                                    ACLMessage msgAtualizaBD = new ACLMessage(ACLMessage.REQUEST);
                                    msgAtualizaBD.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                                    msgAtualizaBD.setEncoding("modificaBD");
                                    msgAtualizaBD.addReceiver(new AID(agenteAABDSelecionado.getAttributeValue("nome"), AID.ISLOCALNAME));

                                    try {
                                        msgAtualizaBD.setContentObject(agenteAABDSelecionado);
                                    } catch (IOException ex) {
                                        Exceptions.printStackTrace(ex);
                                    }

                                    addBehaviour(new AchieveREInitiator(myAgent, msgAtualizaBD) {

                                        @Override
                                        protected void handleInform(ACLMessage inform) {
                                            super.handleInform(inform);
                                        }
                                    });

                                    addBehaviour(comportamentoDePrioridade);
                                    
                                }//fim do if(clientes1>clientes2)

                            } else {
                                addBehaviour(comportamentoDePrioridade);
                            }
                            //fim da prioridade de clientes
                        }else if(tipoDePrioridade.equalsIgnoreCase("Cargas Prioritaria")){
                            
                            double cargas1 = 0;
                            double cargas2 = 0;
                            
                            List agentesAT1 = agenteAlimentadorBD.getChild("agentesAT").getChildren();

                            for (int i = 0; i < agentesAT1.size(); i++) {

                                Element agenteAT = (Element) agentesAT1.get(i);
                                if (agenteAT.getAttributeValue("afetado").equalsIgnoreCase("sim")) {
                                    cargas1 += Double.parseDouble(agenteAT.getAttributeValue("cargaImportante"));
                                }
                            }

                            ACLMessage msgSolicitaBD = new ACLMessage(ACLMessage.REQUEST);
                            msgSolicitaBD.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                            msgSolicitaBD.setEncoding("solicitaBD");
                            msgSolicitaBD.addReceiver(new AID(agenteAABDSelecionado.getChild("agenteRecomposto").getAttributeValue("nome"), AID.ISLOCALNAME));
                            msgSolicitaBD.setContent("solicitaBD");

                            addBehaviour(new AchieveREInitiator(myAgent, msgSolicitaBD) {

                                @Override
                                protected void handleInform(ACLMessage inform) {

                                    try {
                                        agenteAlimentadorBDAux = (Element) inform.getContentObject();
                                        agenteAlimentadorBDAux.getAttributeValue("nome");
                                    } catch (UnreadableException ex) {
                                        Exceptions.printStackTrace(ex);
                                    }
                                }
                            });

                            if (agenteAlimentadorBDAux.getAttributeValue("nome").equalsIgnoreCase(agenteAABDSelecionado.getChild("agenteRecomposto").getAttributeValue("nome"))) {
                                
                                List agentesAT2 = agenteAlimentadorBDAux.getChild("agentesAT").getChildren();

                                for (int i = 0; i < agentesAT2.size(); i++) {

                                    Element agenteAT = (Element) agentesAT2.get(i);

                                    if (agenteAT.getAttributeValue("afetado").equalsIgnoreCase("sim")) {
                                        cargas2 += Double.parseDouble(agenteAT.getAttributeValue("cargaImportante"));
                                    }
                                }

                                if (cargas1 > cargas2) {

                                    //mensagem para abrir equipamentos chave
                                    ACLMessage msgAbreEquipamento = new ACLMessage(ACLMessage.REQUEST);
                                    msgAbreEquipamento.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                                    msgAbreEquipamento.setContent("Abrir");

                                    List agentesAE = agenteAlimentadorBDAux.getChild("agentesAE").getChildren();

                                    for (int i = 0; i < agentesAE.size(); i++) {
                                        Element agenteAE = (Element) agentesAE.get(i);

                                        if (agenteAE.getAttributeValue("tipo").equalsIgnoreCase("encontro")) {
                                            msgAbreEquipamento.addReceiver(new AID(agenteAE.getName(), AID.ISLOCALNAME));
                                        }
                                    }

                                    /***************************************************************
                                     * 
                                     * Comportamento FIPA-Request Iniciante para abrir chaves
                                     * 
                                     **************************************************************/
                                    addBehaviour(new AchieveREInitiator(myAgent, msgAbreEquipamento) {

                                        protected void handleInform(ACLMessage inform) {

                                            funcionalidades.exibirMensagem(inform);
                                            //agenteAlimentadorBDAux.getChild("agentesAE").getChild(inform.getSender().getLocalName()).setAttribute("estado", "aberto");
                                        }
                                    });//fim do protocolo FIPA-Request Iniciante para abrir chaves

                                    System.out.println(agenteAABDSelecionado.getAttributeValue("nome"));
                                    System.out.println(agenteAABDSelecionado.getChild("correnteCedida").getAttributeValue("valor"));
                                    double correnteCedida = Double.parseDouble(agenteAABDSelecionado.getChild("correnteCedida").getAttributeValue("valor"));
                                    
                                    double correnteDisponivel = Double.parseDouble(agenteAABDSelecionado.getChild("correnteDisponivel").getAttributeValue("valor"));
                                    System.out.println("Corrente Disponivel: " + String.valueOf(correnteCedida + correnteDisponivel));

                                    agenteAABDSelecionado.getChild("correnteDisponivel").setAttribute("valor", String.valueOf(correnteCedida + correnteDisponivel));

                                    ACLMessage msgAtualizaBD = new ACLMessage(ACLMessage.REQUEST);
                                    msgAtualizaBD.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                                    msgAtualizaBD.setEncoding("modificaBD");
                                    msgAtualizaBD.addReceiver(new AID(agenteAABDSelecionado.getAttributeValue("nome"), AID.ISLOCALNAME));

                                    try {
                                        msgAtualizaBD.setContentObject(agenteAABDSelecionado);
                                    } catch (IOException ex) {
                                        Exceptions.printStackTrace(ex);
                                    }

                                    addBehaviour(new AchieveREInitiator(myAgent, msgAtualizaBD) {

                                        @Override
                                        protected void handleInform(ACLMessage inform) {
                                            super.handleInform(inform);
                                        }
                                    });

                                    addBehaviour(comportamentoDePrioridade);
                                    
                                }//fim do if(cargas1>cargas2)

                            } else {
                                addBehaviour(comportamentoDePrioridade);
                            }
                            //fim da prioridade de cargas Prioritárias
                            
                        }else if(tipoDePrioridade.equalsIgnoreCase("Eletrodependentes")){
                            
                            double eletro1 = 0;
                            double eletro2 = 0;
                            
                            List agentesAT1 = agenteAlimentadorBD.getChild("agentesAT").getChildren();

                            for (int i = 0; i < agentesAT1.size(); i++) {

                                Element agenteAT = (Element) agentesAT1.get(i);
                                if (agenteAT.getAttributeValue("afetado").equalsIgnoreCase("sim")) {
                                    eletro1 += Double.parseDouble(agenteAT.getAttributeValue("eletrodependentes"));
                                }
                            }

                            ACLMessage msgSolicitaBD = new ACLMessage(ACLMessage.REQUEST);
                            msgSolicitaBD.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                            msgSolicitaBD.setEncoding("solicitaBD");
                            msgSolicitaBD.addReceiver(new AID(agenteAABDSelecionado.getChild("agenteRecomposto").getAttributeValue("nome"), AID.ISLOCALNAME));
                            msgSolicitaBD.setContent("solicitaBD");

                            addBehaviour(new AchieveREInitiator(myAgent, msgSolicitaBD) {

                                @Override
                                protected void handleInform(ACLMessage inform) {

                                    try {
                                        agenteAlimentadorBDAux = (Element) inform.getContentObject();
                                        agenteAlimentadorBDAux.getAttributeValue("nome");
                                    } catch (UnreadableException ex) {
                                        Exceptions.printStackTrace(ex);
                                    }
                                }
                            });

                            if (agenteAlimentadorBDAux.getAttributeValue("nome").equalsIgnoreCase(agenteAABDSelecionado.getChild("agenteRecomposto").getAttributeValue("nome"))) {
                                
                                List agentesAT2 = agenteAlimentadorBDAux.getChild("agentesAT").getChildren();

                                for (int i = 0; i < agentesAT2.size(); i++) {

                                    Element agenteAT = (Element) agentesAT2.get(i);

                                    if (agenteAT.getAttributeValue("afetado").equalsIgnoreCase("sim")) {
                                        eletro2 += Double.parseDouble(agenteAT.getAttributeValue("eletrodependentes"));
                                    }
                                }

                                if (eletro1 > eletro2) {

                                    //mensagem para abrir equipamentos chave
                                    ACLMessage msgAbreEquipamento = new ACLMessage(ACLMessage.REQUEST);
                                    msgAbreEquipamento.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                                    msgAbreEquipamento.setContent("Abrir");

                                    List agentesAE = agenteAlimentadorBDAux.getChild("agentesAE").getChildren();

                                    for (int i = 0; i < agentesAE.size(); i++) {
                                        Element agenteAE = (Element) agentesAE.get(i);

                                        if (agenteAE.getAttributeValue("tipo").equalsIgnoreCase("encontro")) {
                                            msgAbreEquipamento.addReceiver(new AID(agenteAE.getName(), AID.ISLOCALNAME));
                                        }
                                    }

                                    /***************************************************************
                                     * 
                                     * Comportamento FIPA-Request Iniciante para abrir chaves
                                     * 
                                     **************************************************************/
                                    addBehaviour(new AchieveREInitiator(myAgent, msgAbreEquipamento) {

                                        protected void handleInform(ACLMessage inform) {

                                            funcionalidades.exibirMensagem(inform);
                                            //agenteAlimentadorBDAux.getChild("agentesAE").getChild(inform.getSender().getLocalName()).setAttribute("estado", "aberto");
                                        }
                                    });//fim do protocolo FIPA-Request Iniciante para abrir chaves

                                    System.out.println(agenteAABDSelecionado.getAttributeValue("nome"));
                                    System.out.println(agenteAABDSelecionado.getChild("correnteCedida").getAttributeValue("valor"));
                                    double correnteCedida = Double.parseDouble(agenteAABDSelecionado.getChild("correnteCedida").getAttributeValue("valor"));
                                    
                                    double correnteDisponivel = Double.parseDouble(agenteAABDSelecionado.getChild("correnteDisponivel").getAttributeValue("valor"));
                                    System.out.println("Corrente Disponivel: " + String.valueOf(correnteCedida + correnteDisponivel));

                                    agenteAABDSelecionado.getChild("correnteDisponivel").setAttribute("valor", String.valueOf(correnteCedida + correnteDisponivel));

                                    ACLMessage msgAtualizaBD = new ACLMessage(ACLMessage.REQUEST);
                                    msgAtualizaBD.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                                    msgAtualizaBD.setEncoding("modificaBD");
                                    msgAtualizaBD.addReceiver(new AID(agenteAABDSelecionado.getAttributeValue("nome"), AID.ISLOCALNAME));

                                    try {
                                        msgAtualizaBD.setContentObject(agenteAABDSelecionado);
                                    } catch (IOException ex) {
                                        Exceptions.printStackTrace(ex);
                                    }

                                    addBehaviour(new AchieveREInitiator(myAgent, msgAtualizaBD) {

                                        @Override
                                        protected void handleInform(ACLMessage inform) {
                                            super.handleInform(inform);
                                        }
                                    });

                                    addBehaviour(comportamentoDePrioridade);
                                    
                                }//fim do if(eletro1>eletro2)

                            } else {
                                addBehaviour(comportamentoDePrioridade);
                            }
                            //fim da prioridade de cargas Prioritárias
                        }

                    }//fim do if(agenteAABDSelecionado != null)

                }//fim do if(melhorPropositor != null)

                Enumeration e2 = respostas.elements();
                while (e2.hasMoreElements()) {
                    ACLMessage msg = (ACLMessage) e2.nextElement();
                    if (msg.getPerformative() != ACLMessage.REFUSE) {

                        if (msg.getSender() == melhorPropositor) {
                            ACLMessage resposta = msg.createReply();
                            resposta.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                            resposta.setContent("Proposta Aceita");
                            aceitas.addElement(resposta);
                        } else {
                            ACLMessage resposta = msg.createReply();
                            resposta.setPerformative(ACLMessage.REJECT_PROPOSAL);
                            resposta.setContent("Proposta Recusada");

                            aceitas.addElement(resposta);
                        }//fim do if(msg.getSender()==melhorPropositor)

                    }//fim do if (msg != REFUSE)

                }//fim do while(e.hasMoreElements())

            }//fim do Método handleAllResponses

            /**
             * Método handleInform
             * 
             * Este método recebe uma menssagem INFORM de permissão do melhor 
             * propositor para que possa iniciar a recomposição lançando o
             * comportamento FIPA-Subscribe.
             * 
             */
            protected void handleInform(final ACLMessage inform) {

                funcionalidades.exibirMensagem(inform);

                if (inform.getContent().equalsIgnoreCase("permissao")) {

                    //Seta o melhor propositor no BD
                    agenteAlimentadorBD.getChild("melhorPropositor").setAttribute("nome", inform.getSender().getLocalName());
                    agenteAlimentadorBD.setAttribute("codigo", "1");
                    cont = 1;

                    //Seta o valor de corrente disponivel para realizar a recomposição 
                    agenteAlimentadorBD.getChild("correnteDisponivel").setAttribute("valor", String.valueOf(maxCorrente));

                    //prepara a mensagem a ser enviada pelo comportamento FIPA-Subscribe
                    ACLMessage subscribemsg = new ACLMessage(ACLMessage.SUBSCRIBE);
                    subscribemsg.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
                    //seta o primeiro agente trecho (agente recurso) a ter seu trecho restabelecido como receptor da menssagem
                    subscribemsg.addReceiver(new AID(agenteAlimentadorBD.getChild("agentesAA").getChild(inform.getSender().getLocalName()).getAttributeValue("recurso"),
                            AID.ISLOCALNAME));
                    try {
                        subscribemsg.setContentObject(agenteAlimentadorBD);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //Lança o protocolo FIPA-Subscribe
                    protocoloSubscriptionInitiator.reset(subscribemsg);
                    addBehaviour(protocoloSubscriptionInitiator);

                } else {

                    System.out.println("\nOcorreu um problema na Recomposição!\n"
                            + "O agente selecionado como melhor propositor não enviou mensagem de permissão"
                            + "para o agente solicitante");
                }
            }//fim do método handleInform
        };//fim do FIPAContractNetInitiator


        /***********************************************************************
         *                                                                     *
         *              Comportamento SubscriptionInitiator                    *
         *                                                                     *
         **********************************************************************/
        ACLMessage msgSubscribeACLMessage = new ACLMessage(ACLMessage.SUBSCRIBE);
        protocoloSubscriptionInitiator = new SubscriptionInitiator(this, msgSubscribeACLMessage) {

            /**
             * Método handleInform
             * 
             * --> Este método recebe uma mensagem do tipo INFORM do agente de trecho
             * informando que sua recomposição aconteceu com sucesso;
             * --> Seta o estado do trecho para recomposto;
             * --> Seta o estado do melhor propositor para restaurando;
             * --> Atualiza o valor da corrente disponível para recomposição;
             * --> Atualiza o valor da corrente necessária para recomposição;
             * --> Seleciona o próximo trecho a ser recomposto;
             * --> Reseta o comportamento FIPA-Subscribe com o agente correspondente
             * ao próximo trecho a ser recomposto se não encerra o protocolo;
             * 
             */
            protected void handleInform(ACLMessage inform) {

                funcionalidades.exibirMensagem(inform);

                //atualiza o banco de dados Agente de Alimentador
                try {
                    agenteAlimentadorBD = (Element) inform.getContentObject();
                } catch (UnreadableException e1) {
                    e1.printStackTrace();
                }

                //altera o passo da recomposiçãocorrente
                cont++;
                agenteAlimentadorBD.setAttribute("codigo", String.valueOf(cont));

                // seta o estado do agente de trecho para recomposto
                agenteAlimentadorBD.getChild("agentesAT").getChild(inform.getSender().getLocalName()).setAttribute("estado", "recomposto");

                //seta o agente de alimentador para restaurando
                agenteAlimentadorBD.getChild("agentesAA").getChild(melhorPropositor.getLocalName()).setAttribute("estado", "restaurando");

                //atualiza o valor de corrente disponível negociada pelo agente de alimentador
                double correnteUtilizada = Double.parseDouble(agenteAlimentadorBD.getChild("agentesAT").getChild(inform.getSender().getLocalName()).getAttributeValue("carregamento"));
                double correnteRestante = Double.parseDouble(agenteAlimentadorBD.getChild("correnteDisponivel").getAttributeValue("valor")) - correnteUtilizada;
                agenteAlimentadorBD.getChild("correnteDisponivel").setAttribute("valor", String.valueOf(correnteRestante));

                //atualiza o valor de corrente necessaria para o agente realizar a recomposição
                double correnteNecessaria = Double.parseDouble(agenteAlimentadorBD.getChild("correnteNecessaria").getAttributeValue("valor"));
                agenteAlimentadorBD.getChild("correnteNecessaria").setAttribute("valor", String.valueOf(correnteNecessaria - correnteUtilizada));

                //prepara a mensagem do tipo SUBSCRIBE a ser enviada ao próximo agente de trecho
                ACLMessage msg = new ACLMessage(ACLMessage.SUBSCRIBE);
                msg.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);

                List agentesTrecho = agenteAlimentadorBD.getChild("agentesAT").getChildren();

                //seleção do próximo agente a ser recomposto
                for (int i = 0; i < agentesTrecho.size(); i++) {

                    Element agenteTrecho = (Element) agentesTrecho.get(i);

                    String estado = agenteTrecho.getAttributeValue("estado");
                    String prioridade = agenteTrecho.getAttributeValue("prioridade");

                    int codigo1 = Integer.parseInt(agenteAlimentadorBD.getChild("agentesAT").getChild(inform.getSender().getLocalName()).getAttributeValue("codigo"));
                    int codigo2 = Integer.parseInt(agenteTrecho.getAttributeValue("codigo"));

                    //testa condições de agente não recomposto, agente não defeituoso e agente vizinho
                    if (!estado.equalsIgnoreCase("recomposto") && !estado.equalsIgnoreCase("defeituoso")
                            && Math.abs(codigo1 - codigo2) == 1) {
                        msg.addReceiver(new AID(agenteTrecho.getName(), AID.ISLOCALNAME));
                    } else {
                        agenteAlimentadorBD.setAttribute("estado", "recomposto");
                    }//fim do if

                }//fim do for

                if (msg.getAllReceiver() != null) {

                    try {
                        msg.setContentObject(agenteAlimentadorBD);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    protocoloSubscriptionInitiator.reset(msg);
                }//fim do if
            }//fim do metodo handleInform

            /**
             * Método handleAgree
             * 
             * Este método exibe a mensagem de confirmação enviada pelo agente
             * do trecho que irá ser recomposto.
             * 
             */
            protected void handleAgree(ACLMessage agree) {

                funcionalidades.exibirMensagem(agree);
            }

            /**
             * Método handleRefuse
             * 
             * Este método recebe uma mensagem do tipo REFUSE do agente correspondente
             * ao trecho em recomposição cuja corrente disponível não foi suficiente para
             * realizar a recomposição.
             * --> Detecta quanta corrente falta para realizar a recomposição;
             * --> Reinicia o comportamento FIPA-ContractNet com a nova quantidade de corrente
             * necessária para realizar a recomposição.
             * 
             */
            protected void handleRefuse(ACLMessage refuse) {

                funcionalidades.exibirMensagem(refuse);


                ACLMessage msg = new ACLMessage(ACLMessage.CFP);
                msg.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
                msg.setContent("curto" + "," + horario + "," + agenteAlimentadorBD.getChild("correnteNecessaria").getAttributeValue("valor"));

                List agentesAlimentadores = agenteAlimentadorBD.getChild("agentesAA").getChildren();

                for (int i = 0; i < agentesAlimentadores.size(); i++) {

                    Element agenteAlimentador = (Element) agentesAlimentadores.get(i);

                    if (!agenteAlimentador.getAttributeValue("estado").equalsIgnoreCase("restaurando") && !agenteAlimentador.getName().equalsIgnoreCase(agenteAlimentadorBD.getChild("melhorPropositor").getAttributeValue("nome"))) {
                        msg.addReceiver(new AID(agenteAlimentador.getName(), AID.ISLOCALNAME));
                    }
                }
                System.out.println(msg.getAllReceiver());
                System.out.println(msg.getContent());

                protocoloContractNetInitiator.reset(msg);
                addBehaviour(protocoloContractNetInitiator);
                protocoloSubscriptionInitiator.reset();

            }//fim do método handleRefuse
        };//fim do comportamento SubscriptionInitiator 


        /***********************************************************************
         * 
         * Comportamento FIPA-RequestParticipante
         * 
         **********************************************************************/
        protocoloAchieveREResponder = new AchieveREResponder(this, filtro1) {

            /**
             * Método handleRequest
             * 
             * Este método recebe uma mensagem do tipo REQUEST de um agente de trecho
             * que teve seu trecho afetado por ulgum problema que precisa ser isolado
             * --> Altera o estado do agente e do trecho onde se localiza o defeito
             * para defeituoso;
             * --> Calcula a quantidade de carga necessária para realizar a recomposição;
             * --> Envia comando de abertura a todos os agentes de equipamento pertencentes
             * ao alimentador onde o problema se encontra;
             * --> Prepara a mensagem do tipo CFP que será utilizada na negociação de potência
             * a ser requisitada aos agentes de alimentador que podem fornecer ajuda ao agente
             * alimentador em questão
             * --> Lança o comportamento FIPA-ContractNetInitiator.
             * 
             */
            protected ACLMessage handleRequest(ACLMessage request) {

                atualizarBancoDeDados(request);

                horario = request.getContent().split(",")[1];

                ACLMessage msg = request.createReply();
                msg.setPerformative(ACLMessage.INFORM);
                msg.setContent("Pedido Recebido com sucesso");

                Element agenteEquipamentoBD = funcionalidades.carregarBD(request.getSender().getLocalName());
                String trechoAfetado = agenteEquipamentoBD.getAttributeValue("agenteAT");

                //Alterando estado do agente de Trecho para "defeituoso"

                agenteAlimentadorBD.setAttribute("estado", "defeituoso");
                agenteAlimentadorBD.getChild("agentesAT").getChild(trechoAfetado).setAttribute("estado", "defeituoso");

                calculaAberturaDeChaves(myAgent, agenteAlimentadorBD);

                List agentesTrecho = agenteAlimentadorBD.getChild("agentesAT").getChildren();
                float cargaPerdida = 0;

                for (int i = 0; i < agentesTrecho.size(); i++) {

                    Element agenteTrecho = (Element) agentesTrecho.get(i);

                    if (agenteTrecho.getAttributeValue("estado").equalsIgnoreCase("defeituoso") || agenteTrecho.getAttributeValue("afetado").equalsIgnoreCase("não")) {
                    } else if (agenteTrecho.getAttributeValue("afetado").equalsIgnoreCase("sim")) {
                        cargaPerdida += Float.parseFloat(agenteTrecho.getAttributeValue("carregamento"));
                    }
                }

                String cargaPerdida1 = String.valueOf(cargaPerdida);
                agenteAlimentadorBD.getChild("correnteNecessaria").setAttribute("valor", cargaPerdida1);

                final List agentesEquipamento = agenteAlimentadorBD.getChild("agentesAE").getChildren();

                List<Element> agentesRecurso = agenteAlimentadorBD.getChild("agentesAA").getChildren();

                cfpMessage = new ACLMessage(ACLMessage.CFP);
                cfpMessage.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
                cfpMessage.setContent(request.getContent() + "," + cargaPerdida1);

                //Adiciona agentes de alimentadores na lista de mensagens do protocolo ContractNetInitiator
                for (int i = 0; i < agentesRecurso.size(); i++) {

                    Element element = (Element) agentesRecurso.get(i);
                    String destino = element.getName();
                    cfpMessage.addReceiver(new AID(destino, AID.ISLOCALNAME));
                }//fim do for

                //Atualiza a mensagem de iniciação do protocolo ContractNetInitiator
                protocoloContractNetInitiator.reset(cfpMessage);

                //Este comportamento temporam verifica se todas as mensagens para abertura das chaves a jusante do cuto
                //foram enviadas e se o estado das mesmas foi alterado para aberto, para que assim, a negociação possa
                //ser iniciada.
                comportamentoTemporal100ms = new TickerBehaviour(myAgent, 100) {

                    public void onTick() {
                        boolean todosAbertos = false;
                        List agentesEquipamento2 = agenteAlimentadorBD.getChild("agentesAE").getChildren();
                        int cont = 0;
                        for (int i = 0; i < agentesEquipamento2.size(); i++) {
                            Element agenteEquipamento = (Element) agentesEquipamento.get(i);
                            if (agenteEquipamento.getAttributeValue("estado").equalsIgnoreCase("aberto")) {
                                cont += 1;
                            }
                        }
                        if (cont == cont2) {
                            addBehaviour(protocoloContractNetInitiator);
                            comportamentoTemporal100ms.stop();
                        }

                    }
                };
                addBehaviour(comportamentoTemporal100ms);
                return msg;
            }//fim do método handleRequest
        };//fim do comportamento AchieveREResponder


        /***********************************************************************
         * 
         * Comportamento FIPA-ContractNetParticipante
         * 
         **********************************************************************/
        protocoloContractNetResponder = new ContractNetResponder(this, filtro2) {

            /**
             * Método handleCfp
             * 
             * Este método recebe uma mensagem de solicitação de ajuda CFP do agente de 
             * alimentador afetado e caso este não esteja também sob efeito de algum
             * defeito também, retorna uma mensagem do tipo PROPOSE, se não envia uma
             * mensagem do tipo REFUSE.
             * 
             */
            protected ACLMessage handleCfp(ACLMessage cfp) {

                funcionalidades.exibirMensagem(cfp);

                String[] dados = cfp.getContent().split(",");
                horario = dados[1];
                cfp.setContent(dados[0] + "," + dados[1]);
                atualizarBancoDeDados(cfp);
                cfp.setContent(dados[2]);

                ACLMessage proposta = cfp.createReply();
                proposta.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
                System.out.println(agenteAlimentadorBD.getAttributeValue("estado"));
                if (agenteAlimentadorBD.getAttributeValue("estado").equalsIgnoreCase("defeituoso")
                        || agenteAlimentadorBD.getAttributeValue("estado").equalsIgnoreCase("recomposto")) {
                    proposta.setPerformative(ACLMessage.REFUSE);
                } else {
                    proposta.setPerformative(ACLMessage.PROPOSE);
                }

                try {
                    proposta.setContentObject(agenteAlimentadorBD);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return proposta;
            }

            /**
             * 
             * Método handleAcceptProposal
             * 
             * Este método recebe a informação de que sua proposta selecionada
             * como a melhor proposta.
             * --> Atualiza o valor da corrente disponível do agente;
             * --> Envia uma mensagem do tipo INFORM autorizando a realização da
             * recomposição.
             * 
             */
            protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage proposta, ACLMessage aceita) {

                funcionalidades.exibirMensagem(aceita);

                //Atualiza o banco de dados do agente de Alimentador subtraindo a corrente que foi cedida
                //ao agente de Alimentador solicitante.
                double correnteDiponivel = Double.parseDouble(agenteAlimentadorBD.getChild("correnteDisponivel").getAttributeValue("valor"));
                double correnteCedida = Double.parseDouble(cfp.getContent());
                System.out.println("Corrente Cedida");
                System.out.println(correnteCedida);

                agenteAlimentadorBD.getChild("correnteDisponivel").setAttribute("valor", String.valueOf(correnteDiponivel - correnteCedida));
                agenteAlimentadorBD.getChild("correnteCedida").setAttribute("valor", String.valueOf(correnteCedida));
                agenteAlimentadorBD.setAttribute("estado", "recompondo");
                agenteAlimentadorBD.getChild("agenteRecomposto").setAttribute("nome", cfp.getSender().getLocalName());

                //envia mensagem de confirmação para o agente solicitante
                ACLMessage inform = aceita.createReply();
                inform.setPerformative(ACLMessage.INFORM);
                inform.setContent("permissao");
                return inform;
            }
        };//fim do comportamento FIPAContractNetResponder

        /***********************************************************************
         * 
         * Comportamento FIPA-SubscribeResponder
         * 
         **********************************************************************/
        protocoloSubscriptionResponder = new SubscriptionResponder(this, filtro3) {

            /*
             * Método handleSubscription
             * 
             * Este método recebe uma mensagem do tipo SUBSCRIBE do seu respectivo 
             * agente de subestação autorizando o início do processo de recomposição
             * do alimentador em questão.
             * --> Calcula a corrente necessária para realizar a recomposição;
             * --> Adiciona os agentes de alimentador que podem ajudar como receptores
             * da menssagem CFP que será inicializada no protocolo FIPA-ContractNet
             * --> Lança o protocolo FIPA-ContractNetInitiator iniciando o processo de
             * negociação de potência para realizar a recomposição;
             * 
             */
            protected ACLMessage handleSubscription(final ACLMessage subscribe) {

                ACLMessage msg4 = new ACLMessage(ACLMessage.CFP);
                msg4.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);

                atualizarBancoDeDados(subscribe);

                List agentesAT = agenteAlimentadorBD.getChild("agentesAT").getChildren();
                double cargaPerdida = 0.0;
                String cargaPerdida1 = null;

                for (int i = 0; i < agentesAT.size(); i++) {

                    Element agenteAT = (Element) agentesAT.get(i);
                    cargaPerdida += Double.parseDouble(agenteAT.getAttributeValue("carregamento"));

                }

                cargaPerdida1 = String.valueOf(cargaPerdida);
                agenteAlimentadorBD.getChild("correnteNecessaria").setAttribute("valor", cargaPerdida1);


                msg4.setContent(subscribe.getContent() + "," + cargaPerdida1);

                //Adiciona agentes de alimentadores na lista de mensagens do protocolo ContractNetInitiator

                List agentesRecurso = agenteAlimentadorBD.getChild("agentesAA").getChildren();

                for (int i = 0; i < agentesRecurso.size(); i++) {

                    Element element = (Element) agentesRecurso.get(i);
                    String destino = element.getName();
                    msg4.addReceiver(new AID(destino, AID.ISLOCALNAME));
                }//fim do for


                protocoloContractNetInitiator.reset(msg4);
                addBehaviour(protocoloContractNetInitiator);

                boolean recomposto = false;


                addBehaviour(new TickerBehaviour(myAgent, 100) {

                    public void onTick() {

                        if (agenteAlimentadorBD.getAttributeValue("estado").equalsIgnoreCase("recomposto")) {
                            ACLMessage msg = subscribe.createReply();
                            msg.setPerformative(ACLMessage.INFORM);
                            msg.setContent("Alimentador Recomposto com sucesso");
                            send(msg);
                            this.stop();
                        }
                    }
                });

                ACLMessage agree = subscribe.createReply();
                agree.setPerformative(ACLMessage.AGREE);
                agree.setContent("Recomposicao do Alimentador " + myAgent.getLocalName() + " sera iniciada.");

                return agree;
            }
        };//fim do comportamento FIPA-SubscriptionResponder


        /***********************************************************************
         * 
         * Comportamento FIPA-AchieveREResponder
         * 
         **********************************************************************/
        protocoloAchieveREResponder2 = new AchieveREResponder(this, filtro4) {

            protected ACLMessage handleRequest(ACLMessage request) {

                funcionalidades.exibirMensagem(request);
                ACLMessage inform = request.createReply();
                inform.setPerformative(ACLMessage.INFORM);
                inform.setContent("mensagem recebida com sucesso");

                funcionalidades.carregarBD(myAgent.getLocalName());

                return inform;
            }
        };



        /***********************************************************************
         * 
         * Lançamentos de Comportamentos Primarios
         * 
         **********************************************************************/
        addBehaviour(comportamentoDeInicializacao);
        addBehaviour(comportamentoModificaBD);
        addBehaviour(comportamentoSolicitaBD);
        addBehaviour(protocoloAchieveREResponder);
        addBehaviour(protocoloContractNetResponder);
        addBehaviour(protocoloSubscriptionResponder);
        addBehaviour(protocoloAchieveREResponder2);


    }//fim do método Setup

    /**
     * Método atualizarBancoDeDados
     * 
     * @param msg 
     */
    public void atualizarBancoDeDados(ACLMessage msg) {

        String[] dados = msg.getContent().split(",");

        List<String> valor = new ArrayList<String>();

        List agentesAE = agenteAlimentadorBD.getChild("agentesAE").getChildren();

        //Este laço for() percorre os agentes de equipamento para carregar as informações
        //de corrente para o horário especificado pelo usuário
        for (int i = 0; i < agentesAE.size(); i++) {

            Element agenteAE1 = (Element) agentesAE.get(i);

            Element agenteAE2 = funcionalidades.carregarBD(agenteAE1.getName());

            List carregamento = agenteAE2.getChild("carregamento").getChildren();

            //Este laço for() percorre os horários das amostras presentes no banco
            //de dados dos agentes de equipamento
            for (int j = 0; j < carregamento.size(); j++) {

                Element amostra = (Element) carregamento.get(j);

                if (amostra.getAttributeValue("horario").equalsIgnoreCase(dados[1])) {

                    valor.add(agenteAE2.getName() + "," + amostra.getValue());
                    break;
                }//fim do if() que encontra a amostra do horario especificado

            }//fim do for() que percorre os valores de corrente armazenada


        }//fim do for() que percorre os agentes de equipamento

        //===============<<Seta o campo correnteDisponivel>>====================
        //Este trecho de código calcula, no horário especificado pelo usuário,
        //quanto de corrente o agente de alimentador pode fornecer caso seja
        //solicitado por ajuda por outro agente de alimentador
        int valoresChave1 = Integer.parseInt(valor.get(0).split(",")[1]);
        int correnteAtual = valoresChave1;

        if (agenteAlimentadorBD.getChild("correnteDisponivel").getAttributeValue("valor").equalsIgnoreCase("null")) {
            int correnteMaxima = Integer.parseInt(agenteAlimentadorBD.getChild("correnteMaxima").getAttributeValue("valor"));
            agenteAlimentadorBD.getChild("correnteDisponivel").setAttribute("valor", String.valueOf(correnteMaxima - correnteAtual));
        }

        //======================================================================


        List agentesAT = agenteAlimentadorBD.getChild("agentesAT").getChildren();
        int corrente1 = 0;
        int corrente2 = 0;

        //Este laço for() percorre os agentes de trecho para setar seu carregamento
        for (int i = 0; i < agentesAT.size(); i++) {

            Element agenteAT = (Element) agentesAT.get(i);
            String chave1 = agenteAT.getAttributeValue("chave1");
            String chave2 = agenteAT.getAttributeValue("chave2");

            //Este laço for() encontra os valores de corrente para as chaves
            //presentes nas extremidades do trecho em questão
            for (int j = 0; j < valor.size(); j++) {

                if (valor.get(j).contains(chave1)) {
                    String[] valores1 = valor.get(j).split(",");
                    corrente1 = Integer.parseInt(valores1[1]);
                }//fim do if()

                if (valor.get(j).contains(chave2)) {
                    String[] valores2 = valor.get(j).split(",");
                    corrente2 = Integer.parseInt(valores2[1]);
                }//fim do if()
            }//fim do for() que encontra os valores de corrente das chaves que delimitam o agente de trecho

            agenteAT.setAttribute("carregamento", String.valueOf(corrente1 - corrente2));

        }//fim do for que percorre os agentes de trecho

    }// fim do método atualizarBancoDeDados

    public void calculaAberturaDeChaves(Agent agente, Element agenteBancoDeDados) {

        agenteAlimentadorBDAux = agenteBancoDeDados;

        List agentesAE = agenteAlimentadorBDAux.getChild("agentesAE").getChildren();
        List agentesAT = agenteAlimentadorBDAux.getChild("agentesAT").getChildren();
        Element agenteATSobFalta = null;

        for (int i = 0; i < agentesAT.size(); i++) {

            agenteATSobFalta = (Element) agentesAT.get(i);

            if (agenteATSobFalta.getAttributeValue("estado").equalsIgnoreCase("defeituoso")) {
                break;
            }
        }


        int agenteATSobFaltaCodigo = Integer.parseInt(agenteATSobFalta.getAttributeValue("codigo"));
        for (int i = 0; i < agentesAT.size(); i++) {

            Element agenteAT = (Element) agentesAT.get(i);
            int codigoAT = Integer.parseInt(agenteAT.getAttributeValue("codigo"));
            if (agenteATSobFaltaCodigo < codigoAT) {
                agenteAT.setAttribute("afetado", "sim");
            } else if (agenteATSobFaltaCodigo > codigoAT) {
                agenteAT.setAttribute("afetado", "não");
            }
        }

        //mensagem para abrir equipamentos chave
        ACLMessage msg3 = new ACLMessage(ACLMessage.REQUEST);
        msg3.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        msg3.setContent("Abrir");

        //mensagem para fechar equipamentos chave
        ACLMessage msg4 = new ACLMessage(ACLMessage.REQUEST);
        msg4.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        msg4.setContent("Fechar");

        String chaveQueAtuou = agenteAlimentadorBDAux.getChild("agentesAT").getChild(agenteATSobFalta.getName()).getAttributeValue("chave1");
        String codigo = agenteAlimentadorBDAux.getChild("agentesAE").getChild(chaveQueAtuou).getAttributeValue("codigo");
        int numcodigo = Integer.parseInt(codigo);
        cont2 = 0;

        for (int i = 0; i < agentesAE.size(); i++) {

            Element element = (Element) agentesAE.get(i);
            int numcodigo2 = Integer.parseInt(element.getAttributeValue("codigo"));
            if (numcodigo2 >= numcodigo || element.getAttributeValue("tipo").equalsIgnoreCase("encontro")) {
                cont2++;
                String destino = element.getName();
                msg3.addReceiver(new AID(destino, AID.ISLOCALNAME));
            } else {
                String destino = element.getName();
                msg4.addReceiver(new AID(destino, AID.ISLOCALNAME));
            }
        }

        /***************************************************************
         * 
         * Comportamento FIPA-Request Iniciante para fechar chaves
         * 
         **************************************************************/
        addBehaviour(new AchieveREInitiator(agente, msg4) {

            protected void handleInform(ACLMessage inform) {

                funcionalidades.exibirMensagem(inform);
                agenteAlimentadorBDAux.getChild("agentesAE").getChild(inform.getSender().getLocalName()).setAttribute("estado", "fechado");
            }
        });//fim do protocolo FIPA-Request Iniciante para fechar chaves

        /***************************************************************
         * 
         * Comportamento FIPA-Request Iniciante para abrir chaves
         * 
         **************************************************************/
        addBehaviour(new AchieveREInitiator(agente, msg3) {

            protected void handleInform(ACLMessage inform) {

                funcionalidades.exibirMensagem(inform);
                agenteAlimentadorBDAux.getChild("agentesAE").getChild(inform.getSender().getLocalName()).setAttribute("estado", "aberto");
            }
        });//fim do protocolo FIPA-Request Iniciante para abrir chaves
    }
}//fim da Classe AgenteDeAlimnetador

