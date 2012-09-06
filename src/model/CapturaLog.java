/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.ServerSocket;

import java.net.Socket;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import org.openide.util.Exceptions;
import view.DialogVisualisarLogs;

/**
 *
 * @author lucas
 */
public class CapturaLog extends Thread{

    DialogVisualisarLogs janela;
    
    public CapturaLog() {
        janela = new DialogVisualisarLogs();
        janela.setVisible(true);
    }
    
    public void run(){
                
        //Declara o ServerSocket
        ServerSocket serv = null;

        //Declara o Socket de comunicação
        Socket s = null;

        //Declara o leitor para a entrada de dados
        BufferedReader entrada = null;
        try {
            //Cria o ServerSocket na porta 7000 se estiver disponível
            serv = new ServerSocket(7000);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        while (true) {
            
            try {
                //Aguarda uma conexão na porta especificada e cria retorna o socket que irá comunicar com o cliente
                s = serv.accept();
                //Cria um BufferedReader para o canal da stream de entrada de dados do socket s
                entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
                
                while (true) {                    
                    
                    //Aguarda por algum dado e imprime a linha recebida quando recebe
                    String msg = entrada.readLine();
                    if (msg.equalsIgnoreCase("quit")) {
                        s.close();
                    }else{
                                                
                        DefaultListModel model = new DefaultListModel();
                        JList lista = janela.getjList1();
                        model = (DefaultListModel) lista.getModel();
                        model.addElement(msg);
                    }
                    
                }//fim do while()
                
                //trata possíveis excessões de input/output. Note que as excessões são as mesmas utilizadas para as classes de java.io
            } catch (IOException e) {

                System.out.println("Conexão encerrada");


            }
        }//fim do while()
    }


    
}//fim da classe CapturaLog