/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import org.openide.util.Exceptions;

/**
 *
 * @author lucas
 */
public class SimulaPerdaSE {

    String mensagem = null;
    String horario = null;
    Socket cliente = null;
    JList jList = null;
    JOptionPane janelaAlerta = null;
    LancaAgentes lancaAgentes = null;

    public SimulaPerdaSE(LancaAgentes lancaAgentes) {
        
        this.lancaAgentes = lancaAgentes;

    }

    public void simulaPerdaSE(JList jList) {

        this.jList = jList;
        horario = (String) jList.getSelectedValue();
        
        if (!lancaAgentes.agentesLancados) {
            janelaAlerta = new JOptionPane();
            janelaAlerta.showMessageDialog(janelaAlerta, "Para Simular a perda da subestação é necessário lançar os agentes!", null, JOptionPane.INFORMATION_MESSAGE);

        } else {
            mensagem = "( REQUEST \n:sender (Agent-Identifier :name AMS) \n:receiver (set (Agent-Identifier :name ";
            mensagem += "AQZ";
            mensagem += ")) :content \"problema," + horario + "\"\n:protocol fipa-request\n)";


            try {
                cliente = new Socket("localhost", 6789);
                cliente.setSoTimeout(100);
                DataInputStream in = new DataInputStream(cliente.getInputStream());
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                out.writeBytes(mensagem);
            } catch (IOException ex) {
            }//fim do try-catch

            try {
                cliente.close();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }//fim do if()
    }
}
