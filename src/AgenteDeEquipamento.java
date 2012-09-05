
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.jdom.Element;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREResponder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.openide.util.Exceptions;

public class AgenteDeEquipamento extends Agent {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    MessageTemplate filtro = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
            MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
    Socket cliente = null;
    DataInputStream in = null;
    DataOutputStream out = null;
    Element agenteDeEquipamentoBD = null;
    Element agenteDeAlimentadorBD = null;
    int porta = 0;

    public void setup() {

        agenteDeEquipamentoBD = carregarBD(this.getLocalName());
        agenteDeAlimentadorBD = carregarBD(agenteDeEquipamentoBD.getAttributeValue("agenteAA"));
        porta = Integer.parseInt(agenteDeAlimentadorBD.getChild("agentesAE").getChild(this.getLocalName()).getAttributeValue("porta"));

        addBehaviour(new AchieveREResponder(this, filtro) {

            protected ACLMessage handleRequest(ACLMessage request) {

                exibirMensagem(request);
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
        });
    }//fim do método setup

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
}