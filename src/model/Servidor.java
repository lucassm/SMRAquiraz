package model;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;
import org.openide.util.Utilities;

class ThreadServidor implements Runnable{

    Servidor servidor1 = null;
    Servidor servidor2 = null;
    Servidor servidor3 = null;
    Servidor servidor4 = null;
    Servidor servidor5 = null;
    Servidor servidor6 = null;
    Servidor servidor7 = null;
    Servidor servidor8 = null;
    Servidor servidor9 = null;
    Servidor servidor10 = null;
    Servidor servidor11 = null;
    Servidor servidor12 = null;
    Servidor servidor13 = null;
    Servidor servidor14 = null;
    Servidor servidor15 = null;
    Servidor servidor16 = null;
    
    Vector chavesVector = null;
    
    Thread t;

    public ThreadServidor(Vector servidores, Vector chavesVector) {
        
        servidor1 = (Servidor) servidores.get(0);
        servidor2 = (Servidor) servidores.get(1);
        servidor3 = (Servidor) servidores.get(2);
        servidor4 = (Servidor) servidores.get(3);
        servidor5 = (Servidor) servidores.get(4);
        servidor6 = (Servidor) servidores.get(5);
        servidor7 = (Servidor) servidores.get(6);
        servidor8 = (Servidor) servidores.get(7);
        servidor9 = (Servidor) servidores.get(8);
        servidor10 = (Servidor) servidores.get(9);
        servidor11 = (Servidor) servidores.get(10);
        servidor12 = (Servidor) servidores.get(11);
        servidor13 = (Servidor) servidores.get(12);
        servidor14 = (Servidor) servidores.get(13);
        servidor15 = (Servidor) servidores.get(14);
        servidor16 = (Servidor) servidores.get(15);
        
        this.chavesVector = chavesVector;
        
        t=new Thread(this, "Novo");
        t.start();
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public void run() {
        int cont = 1;
        
        
        while (true) {
            
            switch (cont) {
                case 1:
                    cont++;
                    servidor1.escuta(chavesVector,servidor1.socket);
                case 2:
                    cont++;
                    servidor2.escuta(chavesVector,servidor2.socket);
                case 3:
                    cont++;
                    servidor3.escuta(chavesVector,servidor3.socket);
                case 4:
                    cont++;
                    servidor4.escuta(chavesVector,servidor4.socket);
                case 5:
                    cont++;
                    servidor5.escuta(chavesVector,servidor5.socket);
                case 6:
                    cont++;
                    servidor6.escuta(chavesVector,servidor6.socket);
                case 7:
                    cont++;
                    servidor7.escuta(chavesVector,servidor7.socket);
                case 8:
                    cont++;
                    servidor8.escuta(chavesVector,servidor8.socket);
                case 9:
                    cont++;
                    servidor9.escuta(chavesVector,servidor9.socket);
                case 10:
                    cont++;
                    servidor10.escuta(chavesVector,servidor10.socket);
                case 11:
                    cont++;
                    servidor11.escuta(chavesVector,servidor11.socket);
                case 12:
                    cont++;
                    servidor12.escuta(chavesVector,servidor12.socket);
                case 13:
                    cont++;
                    servidor13.escuta(chavesVector,servidor13.socket);
                case 14:
                    cont++;
                    servidor14.escuta(chavesVector,servidor14.socket);
                case 15:
                    cont++;
                    servidor15.escuta(chavesVector,servidor15.socket);
                case 16:
                    cont = 1;
                    servidor16.escuta(chavesVector,servidor16.socket);
            }

        }
    }
    
}

/**
 *
 * @author lucas
 */
public class Servidor{
    
    ThreadServidor threadServidor = null;
    ServerSocket serverSocket = null;
    Socket socket = null;
    
    
    public Servidor(int porta, int timeOut){
        
        try {
            serverSocket = new ServerSocket(porta);
            serverSocket.setSoTimeout(timeOut);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }    
        System.out.println("Rodando Servidor na porta " + String.valueOf(porta) + " ...");
        
    }
    
    public void LancaThread(Vector servidores,Vector chavesVector){
        
        threadServidor = new ThreadServidor(servidores, chavesVector);
        
    }
    
    public void escuta(Vector chaveVector, Socket socket) {
        
        Vector chaveVector1 = chaveVector;

            try {
                socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                String s = in.readUTF();
                System.out.println(s);
                
                for (int i = 0; i < chaveVector1.size(); i++) {
                    
                    ImageWidget widget =  (ImageWidget) chaveVector1.get(i);
                    
                    String[] parametros = s.split(" ");
                    String chave = parametros[0].replaceAll("_", "");
                    if (widget.getToolTipText().contains(chave)) {
                        if (parametros[1].contains("Abrir")) {
                            widget.setImage(Utilities.loadImage("imagens/religadorNA.png"));
                            break;
                            
                        }else if(parametros[1].contains("Fechar")){
                            widget.setImage(Utilities.loadImage("imagens/religadorNF.png"));
                            break;
                            
                        }
                        
                    }
                    
                }
                
            } catch (IOException ex) {

            }
    }
    
}