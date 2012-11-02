/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import org.openide.util.Exceptions;

/**
 *
 * @author lucas
 */
public class AtualizaAgentes {

    String mensagem = null;
    String[] agentes = {"AQZ1", "AQZ2", "AQZ3", "AQZ4", "JAB1", "MSJ1", "AGF1"};
    JOptionPane janela = null;
    Socket cliente = null;

    public AtualizaAgentes() {

        for (int i = 0; i < agentes.length; i++) {
            
            mensagem = "( REQUEST \n:sender (Agent-Identifier :name AMS) \n:receiver (set (Agent-Identifier :name ";
            mensagem += agentes[i];
            mensagem += ")) :content \"atualizar\"\n:protocol fipa-request\n)";

            cliente = null;

            try {
                cliente = new Socket("localhost", 6789);
                cliente.setSoTimeout(100);
                DataInputStream in = new DataInputStream(cliente.getInputStream());
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                out.writeBytes(mensagem);
            } catch (IOException ex) {

            }//fim do try-catch
        }//fim do for()
        
        try {
            cliente.close();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        janela = new JOptionPane();
        janela.showMessageDialog(janela, "Mensagem enviada e agentes atualizados.", null, JOptionPane.INFORMATION_MESSAGE);
    }
}
