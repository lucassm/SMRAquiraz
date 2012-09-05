/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Vector;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author lucas
 */
public class LimpaGrafico {

    Widget widget;
    Vector<Widget> chavesVector;
    SimulaCurto simulaCurto;

    public LimpaGrafico(SimulaCurto simulaCurto, Vector chavesVector) {

        this.simulaCurto = simulaCurto;
        this.chavesVector = chavesVector;

    }

    public void limpaGrafico() {

        for (int i = 0; i < chavesVector.size(); i++) {

            Widget w = chavesVector.get(i);
            w.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        }
        
        EfeitoAlerta objetoAlerta = null;
        
        for (int i = 0; i < simulaCurto.efeitosAlerta.size(); i++) {
            System.out.println("Widget apagado: "+i);
            objetoAlerta = (EfeitoAlerta) simulaCurto.efeitosAlerta.get(i);
            widget = objetoAlerta.w;
            widget.setVisible(false);
            objetoAlerta.acao = false;
        }
        
        simulaCurto.efeitoAlerta.acao = false;
    }
}
