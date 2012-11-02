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
    public Trecho trecho1;
    public Trecho trecho2;
    public Trecho trecho3;
    public Trecho trecho4;
    public Trecho trecho5;
    public Trecho trecho6;
    public Trecho trecho7;
    public Trecho trecho8;
    public Trecho trecho9;
    public Trecho trecho10;
    public Trecho trecho11;
    public Trecho trecho12;
    public Trecho trecho13;
    public Chave _21I7;
    public Chave _RP1;
    public Chave _RS1;
    public Chave _RL1;
    public Chave _21I6;
    public Chave _RP2;
    public Chave _RS2;
    public Chave _RL2;
    public Chave _21I5;
    public Chave _RS3;
    public Chave _RS4;
    public Chave _RL3;
    public Chave _21I4;
    public Chave _RC1;
    public Chave _RS5;
    public Chave _RL4;
    public Chave _21F8;
    public Chave _21M3;
    public Chave _21F7;
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
        trecho1 = new Trecho("Trecho1", 10, 0.5, 200);
        trecho2 = new Trecho("Trecho2", 10, 0.5, 200);
        trecho3 = new Trecho("Trecho3", 10, 0.5, 200);
        trecho4 = new Trecho("Trecho4", 10, 0.5, 200);
        trecho5 = new Trecho("Trecho5", 10, 0.5, 200);
        trecho6 = new Trecho("Trecho6", 10, 0.5, 200);
        trecho7 = new Trecho("Trecho7", 10, 0.5, 200);
        trecho8 = new Trecho("Trecho8", 10, 0.5, 200);
        trecho9 = new Trecho("Trecho9", 10, 0.5, 200);
        trecho10 = new Trecho("Trecho10", 10, 0.5, 200);
        trecho11 = new Trecho("Trecho11", 10, 0.5, 200);
        trecho12 = new Trecho("Trecho12", 10, 0.5, 200);
        trecho13 = new Trecho("Trecho13", 10, 0.5, 200);
        
        _21I7 = new Chave("_21I7", horario, null, trecho1, fonteAQZ.tensao);
        chavesVector.add(_21I7);
        _RP1 = new Chave("_RP1", horario, trecho1, trecho4, _21I7.tensaoCarga);
        chavesVector.add(_RP1);
        _RS1 = new Chave("_RS1", horario, trecho1, trecho2, _21I7.tensaoCarga);
        chavesVector.add(_RS1);
        _RL1 = new Chave("_RL1", horario, trecho2, null, _RS1.tensaoCarga);
        chavesVector.add(_RL1);
        _21I6 = new Chave("_21I6", horario, null, trecho4, fonteAQZ.tensao);
        chavesVector.add(_21I6);
        _RP2 = new Chave("_RP2", horario, trecho4, trecho8, _21I6.tensaoCarga);
        chavesVector.add(_RP2);
        _RS2 = new Chave("_RS2", horario, trecho4, trecho5, _21I6.tensaoCarga);
        chavesVector.add(_RS2);
        _RL2 = new Chave("_RL2", horario, trecho5, null, _RS2.tensaoCarga);
        chavesVector.add(_RL2);
        _21I5 = new Chave("_21I5", horario, null, trecho7, fonteAQZ.tensao);
        chavesVector.add(_21I5);
        _RS3 = new Chave("_RS3", horario, trecho7, trecho8, _21I5.tensaoCarga);
        chavesVector.add(_RS3);
        _RS4 = new Chave("_RS4", horario, trecho8, trecho9, _RS3.tensaoCarga);
        chavesVector.add(_RS4);
        _RL3 = new Chave("_RL3", horario, trecho9, null, _RS4.tensaoCarga);
        chavesVector.add(_RL3);
        _21I4 = new Chave("_21I4", horario, null, trecho11, fonteAQZ.tensao);
        chavesVector.add(_21I4);
        _RS5 = new Chave("_RC1", horario, trecho11, trecho12, _21I4.tensaoCarga);
        chavesVector.add(_RS5);
        _RL4 = new Chave("_RS5", horario, trecho12, null, _RS5.tensaoCarga);
        chavesVector.add(_RL4);
        _RC1 = new Chave("_RC1", horario, trecho11, trecho13, _21I4.tensaoCarga);
        chavesVector.add(_RC1);
        _21F8 = new Chave("_21F8", horario, null, trecho3, fonteJAB.tensao);
        chavesVector.add(_21F8);
        _21M3 = new Chave("_21M3", horario, null, trecho6, fonteMSJ.tensao);
        chavesVector.add(_21M3);
        _21F7 = new Chave("_21F7", horario, null, trecho10, fonteAGF.tensao);
        chavesVector.add(_21F7);
        
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
        _21I7.atualizar(horario, fonteAQZ.tensao);
        chaves.add(_21I7);
        _RP1.atualizar(horario, _21I7.tensaoCarga);
        chaves.add(_RP1);
        _RS1.atualizar(horario, _21I7.tensaoCarga);
        chaves.add(_RS1);
        _RL1.atualizar(horario, _RS1.tensaoCarga);
        chaves.add(_RL1);
        _21I6.atualizar(horario, fonteAQZ.tensao);
        chaves.add(_21I6);
        _RP2.atualizar(horario, _21I6.tensaoCarga);
        chaves.add(_RP2);
        _RS2.atualizar(horario, _21I6.tensaoCarga);
        chaves.add(_RS2);
        _RL2.atualizar(horario, _RS2.tensaoCarga);
        chaves.add(_RL2);
        _21I5.atualizar(horario, fonteAQZ.tensao);
        chaves.add(_21I5);
        _RS3.atualizar(horario, _21I5.tensaoCarga);
        chaves.add(_RS3);
        _RS4.atualizar(horario, _RS3.tensaoCarga);
        chaves.add(_RS4);
        _RL3.atualizar(horario, _RS4.tensaoCarga);
        chaves.add(_RL3);
        _21I4.atualizar(horario, fonteAQZ.tensao);
        chaves.add(_21I4);
        _RS5.atualizar(horario, _21I4.tensaoCarga);
        chaves.add(_RS5);
        _RL4.atualizar(horario, _RS5.tensaoCarga);
        chaves.add(_RL4);
        _RC1.atualizar(horario, _21I5.tensaoCarga);
        chaves.add(_RC1);
        _21F8.atualizar(horario, fonteJAB.tensao);
        chaves.add(_21F8);
        _21M3.atualizar(horario, fonteMSJ.tensao);
        chaves.add(_21M3);
        _21F7.atualizar(horario, fonteAGF.tensao);
        chaves.add(_21F7);
        

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