/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JList;
import org.openide.util.Exceptions;

/**
 *
 * @author lucas
 */
public class SimulaPerdaSE {
    
    String mensagem = null;
    Socket cliente = null;
    JList jList = null;
    
    public SimulaPerdaSE(JList jList){
        
        this.jList = jList;
        String horario = (String) jList.getSelectedValue();
        
        mensagem = "( REQUEST \n:sender (Agent-Identifier :name AMS) \n:receiver (set (Agent-Identifier :name ";
        mensagem += "AQZ";
        mensagem += ")) :content \"problema,"+horario+"\"\n:protocol fipa-request\n)";


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
    }
}
