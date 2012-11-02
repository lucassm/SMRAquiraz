
package model;

import java.awt.Point;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LevelOfDetailsWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author lucas
 */
public class LODWidget extends LevelOfDetailsWidget{
    
    private static final double ZOOM_MULT = 2.0;

    /** Creates a new instance of LODDemoWidget */
    public LODWidget(Scene scene, int level, double zoom, String title) {
        super (scene, zoom, zoom * ZOOM_MULT, Double.MAX_VALUE, Double.MAX_VALUE);
        
        //setBorder (BorderFactory.createLineBorder (2));
        
        if (level > 1) {
            
            Widget vbox = new Widget (scene);
            vbox.setLayout(LayoutFactory.createVerticalFlowLayout (LayoutFactory.SerialAlignment.JUSTIFY, 1));
            addChild (vbox);
            vbox.addChild(new LODWidget (scene, level - 1, zoom * ZOOM_MULT, title));
            
        }else{
            LabelWidget label = new LabelWidget (scene, title);
            label.setFont(scene.getDefaultFont().deriveFont (8.0f));
            addChild (label);
            label.setPreferredLocation(new Point(8, 10));
        }

    }
    
}
