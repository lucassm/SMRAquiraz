/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import org.netbeans.api.visual.widget.ImageWidget;

/**
 *
 * @author lucas
 */
public class Barra {
    
    public ImageWidget widget;
    
    public Barra(String name, Graficos grafico, String way, Point position){
        widget = grafico.createImageWidget(way, name, position);
        //graph.createHoverAction(widget);
    }
    
}
