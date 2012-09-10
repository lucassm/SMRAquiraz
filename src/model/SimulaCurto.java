/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import javax.swing.JOptionPane;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;

/**
 *
 * @author lucas
 */
public class SimulaCurto {

    Graficos grafico;
    EfeitoAlerta efeitoAlerta;
    List<EfeitoAlerta> efeitosAlerta;
    LancaAgentes lancaAgentes;
    JOptionPane janelaAlerta;

    public SimulaCurto(Graficos grafico, LancaAgentes lancaAgentes) {
        this.grafico = grafico;
        efeitosAlerta = new ArrayList<EfeitoAlerta>();
        this.lancaAgentes = lancaAgentes;
    }

    public void simulaCurto(JList jList1, JList jList2) {

        if (!lancaAgentes.agentesLancados) {
            
            janelaAlerta = new JOptionPane();
            janelaAlerta.showMessageDialog(janelaAlerta, "Para Simular um curto-circuito é necessário lançar os agentes!", null, JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            int[] indices1 = null;
            String agenteDeTrecho = null;
            String msg1 = null;
            String msg2 = null;
            indices1 = null;

            
            indices1 = jList1.getSelectedIndices();
            msg2 = (String) jList2.getSelectedValue();
            
            for (int i = 0; i < indices1.length; i++) {

                switch (indices1[i]) {
                    case 0:
                        agenteDeTrecho = "AT11";
                        break;
                    case 1:
                        agenteDeTrecho = "AT12";
                        break;
                    case 2:
                        agenteDeTrecho = "AT21";
                        break;
                    case 3:
                        agenteDeTrecho = "AT22";
                        break;
                    case 4:
                        agenteDeTrecho = "AT31";
                        break;
                    case 5:
                        agenteDeTrecho = "AT32";
                        break;
                    case 6:
                        agenteDeTrecho = "AT33";
                        break;
                    case 7:
                        agenteDeTrecho = "AT41";
                        break;
                    case 8:
                        agenteDeTrecho = "AT42";
                        break;
                    case 9:
                        agenteDeTrecho = "AT43";
                        break;
                }

                animacao(agenteDeTrecho);

                msg1 = "( INFORM \n:sender (Agent-Identifier :name AMS) \n:receiver (set (Agent-Identifier :name ";
                msg1 += agenteDeTrecho;
                msg1 += ")) :content \"curto,"+msg2+"\"\n)";

                Socket cliente = null;

                try {
                    cliente = new Socket("localhost", 6789);
                    cliente.setSoTimeout(100);
                    DataInputStream in = new DataInputStream(cliente.getInputStream());
                    DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                    out.writeBytes(msg1);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }


    }

    public void animacao(String trecho) {

        Widget w = null;

        if (trecho.equalsIgnoreCase("AT11")) {

            w = grafico.createImageWidget("imagens/curto.png", null, new Point(300, 50));

        } else if (trecho.equalsIgnoreCase("AT12")) {

            w = grafico.createImageWidget("imagens/curto.png", null, new Point(625, 50));

        } else if (trecho.equalsIgnoreCase("AT21")) {

            w = grafico.createImageWidget("imagens/curto.png", null, new Point(300, 175));

        } else if (trecho.equalsIgnoreCase("AT22")) {

            w = grafico.createImageWidget("imagens/curto.png", null, new Point(625, 175));

        } else if (trecho.equalsIgnoreCase("AT31")) {

            w = grafico.createImageWidget("imagens/curto.png", null, new Point(260, 310));

        } else if (trecho.equalsIgnoreCase("AT32")) {

            w = grafico.createImageWidget("imagens/curto.png", null, new Point(480, 310));

        } else if (trecho.equalsIgnoreCase("AT33")) {

            w = grafico.createImageWidget("imagens/curto.png", null, new Point(620, 310));

        } else if (trecho.equalsIgnoreCase("AT41")) {

            w = grafico.createImageWidget("imagens/curto.png", null, new Point(300, 440));

        } else if (trecho.equalsIgnoreCase("AT42")) {

            w = grafico.createImageWidget("imagens/curto.png", null, new Point(625, 440));

        } else if (trecho.equalsIgnoreCase("AT43")) {

            w = grafico.createImageWidget("imagens/curto.png", null, new Point(470, 580));

        }

        efeitoAlerta = new EfeitoAlerta(w, grafico);
        efeitosAlerta.add(efeitoAlerta);
    }
}

class EfeitoAlerta implements Runnable {

    Widget w;
    Graficos grafico;
    Thread thread;
    public boolean acao = true;

    public EfeitoAlerta(Widget w, Graficos grafico) {
        this.w = w;
        this.grafico = grafico;

        thread = new Thread(this, "EfeitoAlerta");
        thread.start();
    }

    public void run() {

        while (true) {

            if (acao) {
                Animacao animacao1 = new Animacao(grafico.scene.getSceneAnimator(), w, thread);

                try {
                    thread.sleep(500);
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }

        }//fim do while 1   
    }
}