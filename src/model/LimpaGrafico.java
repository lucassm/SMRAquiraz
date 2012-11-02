/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;

/**
 *
 * @author lucas
 */
public class LimpaGrafico {

    SvgWidget widget;
    Vector<Widget> chavesVector;
    Vector<String> estadoChavesVector;
    Simulacao simulacao;

    public LimpaGrafico(Simulacao simulacao, Vector chavesVector, Vector estadoVector) {

        this.simulacao = simulacao;
        this.chavesVector = chavesVector;
        this.estadoChavesVector = estadoVector;

    }

    public void limpaGrafico() {

        for (int i = 0; i < chavesVector.size(); i++) {

            if (estadoChavesVector.get(i).equalsIgnoreCase("aberto")) {
                SvgWidget w = (SvgWidget) chavesVector.get(i);
                try {
                    w.setSvgUri(new File("src/imagens/religadorNA.svg").toURI().toString());
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }else if (estadoChavesVector.get(i).equalsIgnoreCase("fechado")){
                SvgWidget w = (SvgWidget) chavesVector.get(i);
                try {
                    w.setSvgUri(new File("src/imagens/religadorNF.svg").toURI().toString());
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        
        Simulacao.EfeitoAlerta objetoAlerta = null;
        
        for (int i = 0; i < simulacao.efeitosAlerta.size(); i++) {
            objetoAlerta = simulacao.efeitosAlerta.get(i);
            
            widget = (SvgWidget) objetoAlerta.w;
            widget.setVisible(false);
            objetoAlerta.acao = false;
        }
        
        simulacao.efeitoAlerta.acao = false;
    }
}
