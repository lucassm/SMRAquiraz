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
public class Trecho {
    
    public SvgWidget widget;
    public int customersNumber;
    public float power;
    
    public Trecho(String name, Graficos grafico, String way, Point position){
        
        widget = grafico.createSvgWidget(way, "<html><b>"+name+"</b></html>", position);
        //graph.createHoverAction(widget);
    }
    
}
