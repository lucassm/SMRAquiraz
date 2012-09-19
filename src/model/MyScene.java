
package model;

import java.awt.RenderingHints;
import org.netbeans.api.visual.widget.Scene;


class MyScene extends Scene {
    
    public void paintChildren () {
        
        Object anti = getGraphics ().getRenderingHint (RenderingHints.KEY_ANTIALIASING);
        
        Object textAnti = getGraphics ().getRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING);

        
        getGraphics ().setRenderingHint (
                
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        getGraphics ().setRenderingHint (
                
                RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        
        super.paintChildren ();

        
        getGraphics ().setRenderingHint (RenderingHints.KEY_ANTIALIASING, anti);
        
        getGraphics ().setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING, textAnti);
    
    }

}