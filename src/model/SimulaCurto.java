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
                        agenteDeTrecho = "AT1";
                        break;
                    case 1:
                        agenteDeTrecho = "AT2";
                        break;
                    case 2:
                        agenteDeTrecho = "AT4";
                        break;
                    case 3:
                        agenteDeTrecho = "AT5";
                        break;
                    case 4:
                        agenteDeTrecho = "AT7";
                        break;
                    case 5:
                        agenteDeTrecho = "AT8";
                        break;
                    case 6:
                        agenteDeTrecho = "AT9";
                        break;
                    case 7:
                        agenteDeTrecho = "AT11";
                        break;
                    case 8:
                        agenteDeTrecho = "AT12";
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

        if (trecho.equalsIgnoreCase("AT1")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(431, 127));

        } else if (trecho.equalsIgnoreCase("AT2")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(691, 127));

        } else if (trecho.equalsIgnoreCase("AT3")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(828, 127));

        } else if (trecho.equalsIgnoreCase("AT4")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(431, 236));

        } else if (trecho.equalsIgnoreCase("AT5")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(691, 236));

        } else if (trecho.equalsIgnoreCase("AT6")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(828, 236));

        } else if (trecho.equalsIgnoreCase("AT7")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(394, 342));

        } else if (trecho.equalsIgnoreCase("AT8")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(575, 342));

        } else if (trecho.equalsIgnoreCase("AT9")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(696, 342));

        } else if (trecho.equalsIgnoreCase("AT10")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(834, 342));

        } else if (trecho.equalsIgnoreCase("AT11")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(445, 445));

        } else if (trecho.equalsIgnoreCase("AT12")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(695, 445));

        } else if (trecho.equalsIgnoreCase("AT13")) {

            w = grafico.createSvgWidget("file:///home/lucas/NetBeansProjects/SMRAquiraz/src/imagens/curto.svg", null, new Point(570, 590));

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
            }else if(!acao){
                return;
            }

        }//fim do while 1   
    }
}