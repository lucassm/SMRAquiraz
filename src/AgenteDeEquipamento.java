
// AgenteDeEquipamento.java
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
 **	AGENTE DE EQUIPAMENTO                        
 **
 ****************************************************/
import jade.core.AID;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.jdom.Element;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREInitiator;
import jade.proto.AchieveREResponder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import model.Funcionalidades;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.openide.util.Exceptions;

public class AgenteDeEquipamento extends Agent {

    MessageTemplate filtro1 = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST), MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
    MessageTemplate filtro2 = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST), MessageTemplate.MatchOntology("atuacao da protecao"));
    AchieveREResponder comportamentoDeAtuacaoDaProtecao = null;
    AchieveREInitiator comportamentoRequestParaAlimentador = null;
    AchieveREResponder comportamentoDeMonitoramentoDoEstadoDasChaves = null;
    OneShotBehaviour comportamentoDeInicializacao = null;
    Element agenteDeEquipamentoBD = null;
    Element agenteDeAlimentadorBD = null;
    Funcionalidades funcionalidades = null;
    Socket cliente = null;
    DataInputStream in = null;
    DataOutputStream out = null;
    int porta = 0;

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
                agenteDeEquipamentoBD = funcionalidades.carregarBD(myAgent.getLocalName());
                agenteDeAlimentadorBD = funcionalidades.carregarBD(agenteDeEquipamentoBD.getAttributeValue("agenteAA"));
                porta = Integer.parseInt(agenteDeAlimentadorBD.getChild("agentesAE").getChild(myAgent.getLocalName()).getAttributeValue("porta"));
            }
        };
        

        /***********************************************************************
         *                                                                     *
         *              Comportamento de Atuação da Proteção                   *
         *                                                                     *
         **********************************************************************/
        comportamentoDeAtuacaoDaProtecao = new AchieveREResponder(this, filtro2) {
            @Override
            protected ACLMessage handleRequest(ACLMessage request) {

                ACLMessage inform = request.createReply();
                inform.setPerformative(ACLMessage.INFORM);
                inform.setContent("mensagem recebida com sucesso");

                ACLMessage msgInfoAgenteAlimentador = new ACLMessage(ACLMessage.REQUEST);
                msgInfoAgenteAlimentador.setContent(request.getContent());
                msgInfoAgenteAlimentador.setEncoding("curto");
                msgInfoAgenteAlimentador.addReceiver(new AID(agenteDeEquipamentoBD.getAttributeValue("agenteAA"), AID.ISLOCALNAME));

                comportamentoRequestParaAlimentador.reset(msgInfoAgenteAlimentador);
                addBehaviour(comportamentoRequestParaAlimentador);

                return inform;
            }
        };


        /**
         * *********************************************************************
         *
         * Comportamento de Request para Agente de Alimentador
         *
         **********************************************************************
         */
        comportamentoRequestParaAlimentador = new AchieveREInitiator(this, null) {
            @Override
            protected void handleInform(ACLMessage inform) {
            }
        };

        
        /***********************************************************************
         * 
         * Comportamento de Monitoramento do Estado das Chaves
         * 
         ***********************************************************************/
        comportamentoDeMonitoramentoDoEstadoDasChaves = new AchieveREResponder(this, filtro1) {
            protected ACLMessage handleRequest(ACLMessage request) {

                funcionalidades.exibirMensagem(request);
                String s = request.toString();
                System.out.println(s);

                ACLMessage msg = request.createReply();
                msg.setPerformative(ACLMessage.INFORM);

                if (request.getContent().equalsIgnoreCase("Fechar")) {
                    msg.setContent("Fechado");
                    try {
                        cliente = new Socket("localhost", porta);
                        cliente.setSoTimeout(10);
                        DataInputStream in = new DataInputStream(cliente.getInputStream());
                        DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                        out.writeUTF(myAgent.getLocalName() + " " + "Fechar");
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                } else if (request.getContent().equalsIgnoreCase("Abrir")) {
                    msg.setContent("Aberto");
                    try {
                        cliente = new Socket("localhost", porta);
                        cliente.setSoTimeout(10);
                        DataInputStream in = new DataInputStream(cliente.getInputStream());
                        DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                        out.writeUTF(myAgent.getLocalName() + " " + "Abrir");
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }

                return msg;
            }
        };


        /*
         * Lançamento dos Comportamentos do Agente de Equipamento
         */
        addBehaviour(comportamentoDeAtuacaoDaProtecao);
        addBehaviour(comportamentoRequestParaAlimentador);
        addBehaviour(comportamentoDeMonitoramentoDoEstadoDasChaves);
        addBehaviour(comportamentoDeInicializacao);

    }//fim do método setup
}