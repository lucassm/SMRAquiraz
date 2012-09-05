
package model;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.widget.*;

import javax.swing.*;
import java.awt.*;
import view.DialogChave;

/**
 * @author David Kaspar
 */
public class ComponenteGrafico{

    public final WidgetAction moveAction = ActionFactory.createMoveAction ();
    public int pos = 0;
    public Widget widget;

    public ComponenteGrafico (Widget widget) {
        
        this.widget = widget;
        this.widget.addChild (createMoveableComponent (new DialogChave()));
    }

    public Widget createMoveableComponent (Component component) {
        
        Widget widgetComponente = new Widget(widget.getScene());
        widgetComponente.setLayout (LayoutFactory.createVerticalFlowLayout ());
        widgetComponente.setBorder (BorderFactory.createLineBorder ());
        widgetComponente.getActions ().addAction (moveAction);

        LabelWidget label = new LabelWidget (widget.getScene(), "Componente Grafico");
        label.setOpaque(true);
        label.bringToFront();
        label.setBackground (Color.white);
        widgetComponente.addChild (label);

        ComponentWidget componentWidget = new ComponentWidget (widget.getScene(), component);
        widgetComponente.addChild (componentWidget);

        pos += 75;
        widgetComponente.setPreferredLocation (new Point (pos, 0));
        return widgetComponente;
    }

}