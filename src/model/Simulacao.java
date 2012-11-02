/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jfree.data.xy.Vector;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;

/**
 *
 * @author lucas
 */
public class Simulacao {

    Graficos grafico;
    EfeitoAlerta efeitoAlerta;
    List<EfeitoAlerta> efeitosAlerta;
    LancaAgentes lancaAgentes;
    JOptionPane janelaAlerta;
    public JComboBox listaHorario;
    public JComboBox listaDispositivo;
    public JComboBox listaFuncao;
    public ButtonGroup prioridade;
    public ButtonGroup relatorio;

    public Simulacao(Graficos arg1, LancaAgentes arg2) {

        grafico = arg1;
        lancaAgentes = arg2;
        efeitosAlerta = new ArrayList<EfeitoAlerta>();
    }

    public void simulacao(JComboBox arg1, JComboBox arg2, JComboBox arg3, ButtonGroup arg4, ButtonGroup arg5) {

        listaHorario = arg1;
        listaDispositivo = arg2;
        listaFuncao = arg3;
        prioridade = arg4;
        relatorio = arg5;

        Enumeration botoes = prioridade.getElements();

        while (botoes.hasMoreElements()) {

            AbstractButton botao = (AbstractButton) botoes.nextElement();
            if (botao.isSelected()) {

                String[] agentes = {"AQZ1", "AQZ2", "AQZ3", "AQZ4", "JAB1", "MSJ1", "AGF1"};
                
                File file = null;
                SAXBuilder builder = null;
                Document doc = null;
                Element xmlElement = null;

                for (int i = 0; i < agentes.length; i++) {

                    file = new File("src/xml/" + agentes[i] + ".xml");

                    builder = new SAXBuilder();

                    try {
                        doc = builder.build(file);
                    } catch (JDOMException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }// fim do try

                    xmlElement = doc.getRootElement();
                    
                    xmlElement.getChild("prioridade").setAttribute("valor", botao.getText());

                    XMLOutputter outputter = new XMLOutputter();
                    FileWriter out;
                    try {
                        out = new FileWriter(file);
                        outputter.output(doc, out);
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }//fim do for

                break;
            }//fim do if
            
        }//fim do while

        String agenteDeTrecho = null;
        String msg1 = null;
        int indice = listaDispositivo.getSelectedIndex();

        switch (indice) {
            case 0:
                agenteDeTrecho = "AT1";
                break;
            case 1:
                agenteDeTrecho = "AT4";
                break;
            case 2:
                agenteDeTrecho = "AT7";
                break;
            case 3:
                agenteDeTrecho = "AT11";
                break;
            case 4:
                agenteDeTrecho = "AT2";
                break;
            case 5:
                agenteDeTrecho = "AT5";
                break;
            case 6:
                agenteDeTrecho = "AT8";
                break;
            case 7:
                agenteDeTrecho = "AT9";
                break;
            case 8:
                agenteDeTrecho = "AT3";
                break;
            case 9:
                agenteDeTrecho = "AT6";
                break;
            case 10:
                agenteDeTrecho = "AT10";
                break;
        }

        animacao(agenteDeTrecho);

        msg1 = "( INFORM \n:ontology \"atuacao da protecao\" \n \n:protocol \"fipa-request\" \n :sender (Agent-Identifier :name AMS) \n:receiver (set (Agent-Identifier :name ";
        msg1 += "_" + listaDispositivo.getSelectedItem().toString();
        msg1 += ")) :content \" " + listaFuncao.getSelectedItem().toString() + "," + listaHorario.getSelectedItem().toString() + "\"\n)";

        System.out.println(msg1);

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

    public void animacao(String trecho) {

        Widget w = null;

        if (trecho.equalsIgnoreCase("AT1")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(431, 127));

        } else if (trecho.equalsIgnoreCase("AT2")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(691, 127));

        } else if (trecho.equalsIgnoreCase("AT3")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(828, 127));

        } else if (trecho.equalsIgnoreCase("AT4")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(431, 236));

        } else if (trecho.equalsIgnoreCase("AT5")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(691, 236));

        } else if (trecho.equalsIgnoreCase("AT6")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(828, 236));

        } else if (trecho.equalsIgnoreCase("AT7")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(394, 342));

        } else if (trecho.equalsIgnoreCase("AT8")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(575, 342));

        } else if (trecho.equalsIgnoreCase("AT9")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(696, 342));

        } else if (trecho.equalsIgnoreCase("AT10")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(834, 342));

        } else if (trecho.equalsIgnoreCase("AT11")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(445, 445));

        } else if (trecho.equalsIgnoreCase("AT12")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(695, 445));

        } else if (trecho.equalsIgnoreCase("AT13")) {

            w = grafico.createSvgWidget(new File("src/imagens/curto.svg").toURI().toString(), null, new Point(570, 590));

        }

        efeitoAlerta = new EfeitoAlerta(w, grafico);
        efeitosAlerta.add(efeitoAlerta);


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
                } else if (!acao) {
                    return;
                }

            }//fim do while 1   
        }
    }
}