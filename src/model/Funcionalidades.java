package model;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author lucas
 */
public class Funcionalidades {

    public Agent agent = null;
    
    public Funcionalidades(Agent arg1){
        
        agent = arg1;
    }
    
    /**
     * 
     * Método exibirMensagem
     *
     **/
    public void exibirMensagem(ACLMessage msg) {

        String conteudo = null;
        boolean objeto = true;
        Socket socket = null;
        PrintStream ps = null;
        Locale locale = new Locale("pt", "BR");
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm':'ss':'SS", locale);

        try {

            socket = new Socket("localhost", 7000);
            ps = new PrintStream(socket.getOutputStream());
            ps.println("Mensagem tipo " + ACLMessage.getPerformative(msg.getPerformative()) + " recebida por " + agent.getLocalName() + " de " + msg.getSender().getLocalName() + " em " + formatador.format(calendar.getTime()));
            ps.println("quit");
        } catch (Exception e) {
        }

        try {
            Element elemento = (Element) msg.getContentObject();
            conteudo = elemento.getName();
        } catch (UnreadableException e) {
            objeto = false;
        }

        if (objeto) {
            System.out.println("\n\n===============<<MENSAGEM>>================\n"
                    + "De: " + msg.getSender() + "\n"
                    + "Para: " + agent.getName() + "\n"
                    + "Conteudo: Banco de Dados " + conteudo + "\n"
                    + "===========================================");
        } else {
            System.out.println("\n\n===============<<MENSAGEM>>================\n"
                    + "De: " + msg.getSender() + "\n"
                    + "Para: " + agent.getName() + "\n"
                    + "Conteudo: " + msg.getContent() + "\n"
                    + "===========================================");
        }

    }//fim do método exibirMenssagem

    
    /**
     * 
     * Método carregarBD
     *
     **/
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
