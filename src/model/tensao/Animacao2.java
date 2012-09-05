package model.tensao;

import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.util.Vector;
import model.tensao.Chave;
import org.netbeans.api.visual.animator.Animator;
import org.netbeans.api.visual.animator.SceneAnimator;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;

public class Animacao2 extends Animator {

    int cont;
    public Vector<Widget> chavesVector;
    public Vector<Chave> chaves;
    public LabelWidget relogio;
    public String horario;

    public Animacao2(SceneAnimator sceneAnimator, Vector<Widget> chavesVect, Vector<Chave> chs, LabelWidget relog, String horas) {

        super(sceneAnimator);
        chavesVector = chavesVect;
        chaves = chs;
        relogio = relog;
        horario = horas;
        cont = 0;
        start();
    }

    @Override
    protected void tick(double d) {

        cont++;

        if (cont == 1) {
            
            relogio.setLabel(horario);
            for (int i = 0; i < chavesVector.size(); i++) {

                List lista = chavesVector.get(i).getChildren();
                LabelWidget w1 = (LabelWidget) lista.get(0);
                w1.setLabel(String.valueOf(chaves.get(i).tensaoFonte / 1000)+" kV");
                LabelWidget w2 = (LabelWidget) lista.get(1);
                w2.setLabel(String.valueOf(chaves.get(i).tensaoCarga / 1000)+" kV");
                LabelWidget w3 = (LabelWidget) lista.get(2);
                w3.setLabel(String.valueOf(chaves.get(i).corrente)+" A");
            }
            System.out.println("Ação Executada");
        }
    }
}