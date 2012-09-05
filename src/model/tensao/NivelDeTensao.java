package model.tensao;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.List;
import java.util.Vector;
import model.Grafico;
import model.Graficos;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;

/**
 *
 * @author lucas
 */
public class NivelDeTensao {

    public String horario;
    public Analisador analisador;
    public Graficos grafico;

    public NivelDeTensao(String horario, Vector chaves, Graficos grafic) {

        this.horario = horario;
        grafico = grafic;
        this.analisador = new Analisador(this.horario, chaves, grafico);
    }
}

class Analisador implements Runnable {

    public Fonte fonteAQZ;
    public Fonte fonteJAB;
    public Fonte fonteMSJ;
    public Fonte fonteAGF;
    public Trecho trecho11;
    public Trecho trecho12;
    public Trecho trecho21;
    public Trecho trecho22;
    public Trecho trecho31;
    public Trecho trecho32;
    public Trecho trecho33;
    public Trecho trecho41;
    public Trecho trecho42;
    public Trecho trecho43;
    public Chave chave11;
    public Chave chave12;
    public Chave chave13;
    public Chave chave14;
    public Chave chave21;
    public Chave chave22;
    public Chave chave23;
    public Chave chave24;
    public Chave chave31;
    public Chave chave32;
    public Chave chave33;
    public Chave chave34;
    public Chave chave41;
    public Chave chave42;
    public Chave chave43;
    public Chave chave44;
    public String horario;
    public Vector<Widget> chavesWidgetVector;
    public Vector<Chave> chavesVector;
    public Animacao2 animacao2;
    public Graficos grafico;
    public LabelWidget relogio;
    public Thread thread;

    public Analisador(String hor, Vector chaves, Graficos grafic) {

        horario = hor;
        chavesWidgetVector = chaves;
        chavesVector = new Vector<Chave>();
        grafico = grafic;

        fonteAQZ = new Fonte(13.8e3, 0, 1200);
        fonteAGF = new Fonte(13.8e3, 0, 200);
        fonteJAB = new Fonte(13.8e3, 0, 200);
        fonteMSJ = new Fonte(13.8e3, 0, 200);
        trecho11 = new Trecho("Trecho11", 10, 0.5, 200);
        trecho12 = new Trecho("Trecho12", 10, 0.5, 200);
        trecho21 = new Trecho("Trecho21", 10, 0.5, 200);
        trecho22 = new Trecho("Trecho22", 10, 0.5, 200);
        trecho31 = new Trecho("Trecho31", 10, 0.5, 200);
        trecho32 = new Trecho("Trecho32", 10, 0.5, 200);
        trecho33 = new Trecho("Trecho33", 10, 0.5, 200);
        trecho41 = new Trecho("Trecho41", 10, 0.5, 200);
        trecho42 = new Trecho("Trecho42", 10, 0.5, 200);
        trecho43 = new Trecho("Trecho43", 10, 0.5, 200);

        chave11 = new Chave("CH11", horario, null, trecho11, fonteAQZ.tensao);
        chavesVector.add(chave11);
        chave12 = new Chave("CH12", horario, trecho11, trecho21, chave11.tensaoCarga);
        chavesVector.add(chave12);
        chave13 = new Chave("CH13", horario, trecho11, trecho12, chave11.tensaoCarga);
        chavesVector.add(chave13);
        chave14 = new Chave("CH14", horario, trecho12, null, chave13.tensaoCarga);
        chavesVector.add(chave14);
        chave21 = new Chave("CH21", horario, null, trecho21, fonteAQZ.tensao);
        chavesVector.add(chave21);
        chave22 = new Chave("CH22", horario, trecho21, trecho32, chave21.tensaoCarga);
        chavesVector.add(chave22);
        chave23 = new Chave("CH23", horario, trecho21, trecho22, chave21.tensaoCarga);
        chavesVector.add(chave23);
        chave24 = new Chave("CH24", horario, trecho22, null, chave23.tensaoCarga);
        chavesVector.add(chave24);
        chave31 = new Chave("CH31", horario, null, trecho31, fonteAQZ.tensao);
        chavesVector.add(chave31);
        chave32 = new Chave("CH32", horario, trecho31, trecho32, chave31.tensaoCarga);
        chavesVector.add(chave32);
        chave33 = new Chave("CH33", horario, trecho32, trecho33, chave32.tensaoCarga);
        chavesVector.add(chave33);
        chave34 = new Chave("CH34", horario, trecho33, null, chave33.tensaoCarga);
        chavesVector.add(chave34);
        chave41 = new Chave("CH41", horario, null, trecho41, fonteAQZ.tensao);
        chavesVector.add(chave41);
        chave42 = new Chave("CH42", horario, trecho41, trecho42, chave41.tensaoCarga);
        chavesVector.add(chave42);
        chave43 = new Chave("CH43", horario, trecho42, null, chave42.tensaoCarga);
        chavesVector.add(chave43);
        chave44 = new Chave("CH44", horario, trecho41, trecho43, chave41.tensaoCarga);
        chavesVector.add(chave44);

        for (int i = 0; i < chavesWidgetVector.size(); i++) {

            criaLabelWidget(chavesWidgetVector.get(i), chavesVector.get(i));

        }

        relogio = grafico.createLabelWidget(horario, new Point(1250, 30), null);
        Font fonte = new Font("Arial", 5, 20);
        relogio.setFont(fonte);

        thread = new Thread(this, "threadAnalisador");
        thread.start();
    }

    public void atualizar() {

        String horarioAtual = horario;
        String[] horasEMinutos = horarioAtual.split(":");

        if (horasEMinutos[1].equalsIgnoreCase("30")) {

            if (horasEMinutos[0].equalsIgnoreCase("09")) {
                horario = "10:00";
            } else {
                
                int horas = Integer.parseInt(horasEMinutos[0]);
                
                if (horas <= 9) {
                    horario = "0" + String.valueOf(horas + 1) + ":" + "00";
                } else {
                    horario = String.valueOf(horas + 1) + ":" + "00";
                }
            }

        } else {
            horario = horasEMinutos[0] + ":" + "30";
        }

        System.out.println(horario);

        Vector chaves = new Vector();
        chave11.atualizar(horario, fonteAQZ.tensao);
        chaves.add(chave11);
        chave12.atualizar(horario, chave11.tensaoCarga);
        chaves.add(chave12);
        chave13.atualizar(horario, chave11.tensaoCarga);
        chaves.add(chave13);
        chave14.atualizar(horario, chave13.tensaoCarga);
        chaves.add(chave14);
        chave21.atualizar(horario, fonteAQZ.tensao);
        chaves.add(chave21);
        chave22.atualizar(horario, chave21.tensaoCarga);
        chaves.add(chave22);
        chave23.atualizar(horario, chave21.tensaoCarga);
        chaves.add(chave23);
        chave24.atualizar(horario, chave23.tensaoCarga);
        chaves.add(chave24);
        chave31.atualizar(horario, fonteAQZ.tensao);
        chaves.add(chave31);
        chave32.atualizar(horario, chave31.tensaoCarga);
        chaves.add(chave32);
        chave33.atualizar(horario, chave32.tensaoCarga);
        chaves.add(chave33);
        chave34.atualizar(horario, chave33.tensaoCarga);
        chaves.add(chave34);
        chave41.atualizar(horario, fonteAQZ.tensao);
        chaves.add(chave41);
        chave42.atualizar(horario, chave41.tensaoCarga);
        chaves.add(chave42);
        chave43.atualizar(horario, chave42.tensaoCarga);
        chaves.add(chave43);
        chave44.atualizar(horario, chave31.tensaoCarga);
        chaves.add(chave44);

        animacao2 = new Animacao2(grafico.scene.getSceneAnimator(), chavesWidgetVector, chaves, relogio, horario);

    }

    public void criaLabelWidget(Widget widget, Chave chave) {

        LabelWidget w1 = grafico.createLabelWidget(String.valueOf(chave.tensaoFonte / 1000)+" kV", new Point(0, -35), widget);
        LabelWidget w2 = grafico.createLabelWidget(String.valueOf(chave.tensaoCarga / 1000)+ " kV", new Point(0, -20), widget);
        LabelWidget w3 = grafico.createLabelWidget(String.valueOf(chave.corrente)+" A", new Point(0, -5), widget);

    }

    public synchronized void run() {

        while (true) {
            try {
                thread.sleep(10000);
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
            }
            atualizar();
        }//fim do while
    }//fim do metodo run
}