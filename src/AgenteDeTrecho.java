/*****************************************************
 **
 **			AGENTE DE TRECHO
 ** 
 *****************************************************/
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREInitiator;
import jade.proto.SubscriptionResponder;

public class AgenteDeTrecho extends Agent {

    private static final long serialVersionUID = 1L;
    MessageTemplate filtro1 = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
    Element agenteAlimentadorBD = null;

    public void setup() {

        final Element agenteTrechoBD = carregarBD(this.getLocalName());

        /***************************************************************
         *                                                             *
         * Comportamento Temporal TickerBehaviour de 100ms             *
         *                                                             *
         **************************************************************/
        addBehaviour(new TickerBehaviour(this, 100) {

            /*
             * Método onTick()
             * 
             * Este método executao comportamento temporal de 100ms
             * e verifica por alguma requisição por parte do sistema
             * para que possa iniciar o procedimento de recomposição
             * 
             */
            public void onTick() {
                ACLMessage msg1 = receive();
                if (msg1 != null) {

                    exibirMensagem(msg1);
                    if (msg1.getContent().contains("curto")) {

                        String agenteAlimentador = agenteTrechoBD.getChild("agenteAA").getAttributeValue("nome");
                        ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
                        msg2.setContent(msg1.getContent());
                        msg2.setEncoding("curto");
                        msg2.addReceiver(new AID(agenteAlimentador, AID.ISLOCALNAME));

                        /***********************************************                
                         *                                             *
                         * Comportamento FIPA-Request                  *
                         *                                             *
                         **********************************************/
                        addBehaviour(new AchieveREInitiator(myAgent, msg2) {

                            protected void handleInform(ACLMessage inform) {
                                exibirMensagem(inform);
                            }
                        });//fim do comportamento FIPA-Request
                    }

                }//fim do if msg != null

            }//fim do if (msg.Content == "curto")
        });//fim do TickerBehaviour

        
        
        /***************************************************************
         *                                                             *
         *              Comportamento FIPA-Subscribe                   *
         *                                                             *
         **************************************************************/
        addBehaviour(new SubscriptionResponder(this, filtro1, null) {

            /**
             * Método handleSubscription()
             * 
             * Este método recebe uma mensagem do tipo SUBSCRIBE de seu respectivo
             * agente de alimentador para que possa realizar a recomposição de seu
             * trecho, analisando então as condições de carregamento dos condutores
             * caso realise a recomposição
             */
            protected ACLMessage handleSubscription(final ACLMessage subscription) {

                exibirMensagem(subscription);

                // Carregando o banco de dados do agente alimentador
                try {
                    agenteAlimentadorBD = (Element) subscription.getContentObject();
                } catch (UnreadableException e) {
                    e.printStackTrace();
                }

                //criando mensagem de retorno
                ACLMessage msgRetorno = subscription.createReply();

                //criando mensagem para fechar chave
                ACLMessage msgFechaChave = new ACLMessage(ACLMessage.REQUEST);
                msgFechaChave.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                msgFechaChave.setContent("fechar");


                //Se recebeu o banco de dados do agente de alimentador com sucesso
                //então entra no if()
                if (agenteAlimentadorBD != null) {

                    //Carrega os agentes de trecho do alimentador em questão em 
                    //um objeto Lista
                    List agentesTrecho = agenteAlimentadorBD.getChild("agentesAT").getChildren();
                    Boolean existeTrechoRecomposto = false;

                    //Analisa a viabilidade da recomposição através da capacidade dos trechos ja recompostos
                    for (int i = 0; i < agentesTrecho.size(); i++) {

                        //Captura um agente de trecho do objeto Lista
                        Element agenteTrecho = (Element) agentesTrecho.get(i);

                        //Entra no if() caso o trecho ja tenha sido recomposto
                        if (agenteTrecho.getAttributeValue("estado").equalsIgnoreCase("rescomposto")) {

                            existeTrechoRecomposto = true;

                            double carregamento = Double.parseDouble(agenteTrecho.getAttributeValue("carregamento"));
                            double carregamentoMaximo = Double.parseDouble(agenteTrecho.getAttributeValue("carregamentoMaximo"));
                            double carregamentoAdicional = Double.parseDouble(agenteAlimentadorBD.getChild("agentesAT").getChild(myAgent.getLocalName()).getAttributeValue("carregamento"));

                            //analisa se em caso re recomposição do trecho atual um trecho recomposto sofrerá sobrecarga
                            if (carregamento + carregamentoAdicional <= carregamentoMaximo) {

                                msgRetorno.setPerformative(ACLMessage.AGREE);
                                msgRetorno.setContent("Trecho Recomposto");
                            } else {

                                msgRetorno.setPerformative(ACLMessage.REFUSE);
                                msgRetorno.setContent("Nao foi Possivel Recompor o Trecho");
                            }//fim de if (carregamento + carregamentoAdicional <= carregamentoMaximo)
                            
                        }//fim de if (agenteTrecho.getAttributeValue("estado").equalsIgnoreCase("rescomposto"))
                        
                    }//fim de for() que a analisa a capacidade dos condutores após recomosição

                    //Caso seja o primeiro trecho a ser remposto no alimentador
                    //a não se faz necessario a analse de carregamento dos demais trechos
                    //entra-se no if() para aceitar a recomposição
                    if (!existeTrechoRecomposto) {

                        msgRetorno.setPerformative(ACLMessage.AGREE);
                    }
                    
                    /*****************************************
                     * Inicio do Algoritmo de recomposição   *
                     ****************************************/
                    
                    //Entra no if() caso seja o primeiro trecho de recomposição
                    if (agenteAlimentadorBD.getAttributeValue("codigo").equalsIgnoreCase("1")) {


                        String melhorPropositor = agenteAlimentadorBD.getChild("melhorPropositor").getAttributeValue("nome");
                        String chave = agenteAlimentadorBD.getChild("agentesAA").getChild(melhorPropositor).getAttributeValue("chave");
                        String recurso = agenteAlimentadorBD.getChild("agentesAA").getChild(melhorPropositor).getAttributeValue("recurso");

                        //Caso o agente recurso indicado pelo melhor propositor for justamente o trecho defeituoso, então
                        //a recomposição não poderá ser realizada pelo agente presente na variável melhor propositor
                        if (agenteAlimentadorBD.getChild("agentesAT").getChild(recurso).getAttributeValue("estado").equalsIgnoreCase("defeituoso")) {
                            msgRetorno.setPerformative(ACLMessage.REFUSE);

                        } else {
                            //Seta a ordem de recomposição do agente equipamento a ser fechado
                            agenteAlimentadorBD.getChild("agentesAE").getChild(chave).setAttribute("ordem", "1");
                            msgFechaChave.addReceiver(new AID(chave, AID.ISLOCALNAME));
                        }//Fim do if() que avalia se o trecho a ser recomposto é o defeituoso

                    } else {//caso não seja o primeiro trecho a ser recomposto cai no else()
                        
                        //Detectando a chave a ser fechada
                        String chave1 = agenteAlimentadorBD.getChild("agentesAT").getChild(myAgent.getLocalName()).getAttributeValue("chave1");
                        String chave2 = agenteAlimentadorBD.getChild("agentesAT").getChild(myAgent.getLocalName()).getAttributeValue("chave2");
                        String chaveEscolhida = null;
                        int codigo = 0;
                        int codigoChave1 = Integer.parseInt(agenteAlimentadorBD.getChild("agentesAE").getChild(chave1).getAttributeValue("codigo"));
                        int codigoChave2 = Integer.parseInt(agenteAlimentadorBD.getChild("agentesAE").getChild(chave2).getAttributeValue("codigo"));
                        int ordem = 0;
                        int maxOrdem = 0;
                        
                        //Cria uma lista com todos as agentes de equipamento
                        List agentesAE = agenteAlimentadorBD.getChild("agentesAE").getChildren();
                        
                        //Este for() encontra a ultima chave fechada pela recomposição automática
                        for (int j = 0; j < agentesAE.size(); j++) {

                            //Captura um agente de equipamento do objeto Lista
                            Element agenteAE = (Element) agentesAE.get(j);
                            
                            //Entra no if() se o agente de equipamento já houver participado
                            //da recomposição
                            if (!agenteAE.getAttributeValue("ordem").equalsIgnoreCase("null")) {
                                
                                ordem = Integer.parseInt(agenteAE.getAttributeValue("ordem"));
                                
                                //seleciona o último agente de equipamento a ter participado
                                //da recomposição
                                if (ordem > maxOrdem) {
                                    
                                    maxOrdem = ordem;
                                    codigo = Integer.parseInt(agenteAE.getAttributeValue("codigo"));
                                }//fim do if (ordem > maxOrdem)

                            }//fim do if (!agenteAE.getAttributeValue("ordem").equalsIgnoreCase("null"))
                            
                        }//fim do for()
                        
                        //Este if() toma a decisão de qual das duas chaves que isolam o trecho
                        //deve ser fechada para a realização da recomposição
                        if (Math.abs(codigo - codigoChave1) < Math.abs(codigo - codigoChave2)) {
                            chaveEscolhida = chave1;
                        } else {
                            chaveEscolhida = chave2;
                        }
                        
                        //Atualiza a chave de recomposião como a mais recentemente utilizada
                        agenteAlimentadorBD.getChild("agentesAE").getChild(chaveEscolhida).setAttribute("ordem", String.valueOf(maxOrdem + 1));
                        
                        msgFechaChave.addReceiver(new AID(chaveEscolhida, AID.ISLOCALNAME));
                    }//fim do if (agenteAlimentadorBD.getAttributeValue("codigo").equalsIgnoreCase("1"))

                    
                    //Analisa se a corrente disponível é suficiente para realizar a recomposição
                    double correnteDisponivel = Double.parseDouble(agenteAlimentadorBD.getChild("correnteDisponivel").getAttributeValue("valor"));
                    double correnteNecessaria = Double.parseDouble(agenteAlimentadorBD.getChild("agentesAT").getChild(myAgent.getLocalName()).getAttributeValue("carregamento"));
                    
                    //Caso a corrente disponível não seja suficiente para executar a recomposição
                    //uma mensagem de REFUSE é enviada para o agente de alimentador
                    if (correnteDisponivel - correnteNecessaria < 0) {
                        msgRetorno.setPerformative(ACLMessage.REFUSE);
                    }//fim do if()

                }//fim de if (agenteAlimentadorBD != null)

                //Entra no if() caso o fechamento da chave seja autorizado
                if (msgRetorno.getPerformative() == ACLMessage.AGREE) {

                    /***********************************************************
                     *                                                         *
                     *      Comportamento FIPA-RequestInitiator                *
                     *                                                         *
                     **********************************************************/
                    addBehaviour(new AchieveREInitiator(myAgent, msgFechaChave) {
                        
                        /*
                         *  método handleInform()
                         * 
                         * Este método envia o comando de fechamento par a chave
                         * selecionada e ao receber uma mensagem de confirmação
                         * do tipo AGREE do agente de equipamento retorna outra 
                         * do tipo INFORM para que o agente de alimentador possa
                         * iniciar a recomposição de outro trecho
                         */
                        protected void handleInform(ACLMessage agree) {

                            ACLMessage msg = subscription.createReply();
                            msg.setPerformative(ACLMessage.INFORM);

                            try {
                                msg.setContentObject(agenteAlimentadorBD);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            send(msg);
                        }
                    });//fim do comportamento FIPA-RequestInitiator
                }//fim do if(msgRetorno.getPerformative() == ACLMessage.AGREE)

                //Retorna o banco de dados modificado para o agente de alimentador
                try {
                    msgRetorno.setContentObject(agenteAlimentadorBD);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return msgRetorno;
            }//fim de handleSubscription
        });//fim do comportamento FIPA-Subscribe

    }//fim do método setup()

    /**
     * Método exibirMensagem
     * 
     * @param msg
     */
    public void exibirMensagem(ACLMessage msg) {

        String conteudo = null;
        boolean objeto = true;

        try {
            Element elemento = (Element) msg.getContentObject();
            conteudo = elemento.getName();
        } catch (UnreadableException e) {
            objeto = false;
        }

        if (objeto) {
            System.out.println("\n\n===============<<MENSAGEM>>================\n"
                    + "De: " + msg.getSender() + "\n"
                    + "Para: " + this.getName() + "\n"
                    + "Conteudo: Banco de Dados " + conteudo + "\n"
                    + "===========================================");
        } else {
            System.out.println("\n\n===============<<MENSAGEM>>================\n"
                    + "De: " + msg.getSender() + "\n"
                    + "Para: " + this.getName() + "\n"
                    + "Conteudo: " + msg.getContent() + "\n"
                    + "===========================================");
        }

    }//fim do método exibirMenssagem

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
        }//fim do try

        agenteBD = doc.getRootElement();
        return agenteBD;

    }//fim do método carregarBD
}//fim da Classe AgenteDeTrecho