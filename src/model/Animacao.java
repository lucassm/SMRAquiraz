
package model;

import java.awt.Color;
import java.awt.Point;
import org.netbeans.api.visual.animator.Animator;
import org.netbeans.api.visual.animator.SceneAnimator;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;

public class Animacao extends Animator {

    public Widget w;
    Thread thread;
    int cont;

    public Animacao(SceneAnimator sceneAnimator, Widget w, Thread thread) {
        super(sceneAnimator);
        cont=0;
        this.w = w;
        this.thread = thread;
        start();
    }

    @Override
    protected void tick(double d) {

        cont++;
        
        if (cont == 1) {
            if (w.isVisible()) {
                w.setVisible(false);
            } else {
                w.setVisible(true);
            }
        }
    }
}
