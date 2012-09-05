/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import javax.swing.JComponent;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Utilities;

/**
 *
 * @author lucas
 */
public class Graficos {

    public Scene scene;
    public LayerWidget mainLayer;
    public LayerWidget connectionLayer;
    public WidgetAction move;
    public WidgetAction hoverAction;
    public WidgetAction popupMenuAction;

    public Graficos() {
        scene = new Scene();
        mainLayer = new LayerWidget(scene);
        connectionLayer = new LayerWidget(scene);
        scene.addChild(mainLayer);
        scene.addChild(connectionLayer);

    }

    public ImageWidget createImageWidget(String location, String toolTipText, Point position) {
        ImageWidget w = new ImageWidget(scene, Utilities.loadImage(location));
        w.setPreferredLocation(position);
        w.setToolTipText(toolTipText);
        mainLayer.addChild(w);
        return w;
    }
    
    public LabelWidget createLabelWidget(String label, Point position, Widget widget){
        
        LabelWidget w = new LabelWidget(scene, label);
        w.setPreferredLocation(position);
                
        if (widget == null) {
            mainLayer.addChild(w);
            mainLayer.revalidate();
        }else{
            widget.addChild(w);
        }
        
        return w;
    }

    public JComponent createView() {
        return scene.createView();
    }
}
