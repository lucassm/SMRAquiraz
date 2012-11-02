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
public class Subestacao {
    
    public SvgWidget widget;
    public String name;
    public float power;
    public float freePower;
    public String loadCondition;

    public Subestacao(String name, Graficos grafico, String way, Point position) {
        
        if (name != null) {
            widget = grafico.createSvgWidget(way, "<html><b>SE "+name+"</b></html>", position);
        }else{
            widget = grafico.createSvgWidget(way, null, position);
        }
        
        this.name = name;
    }
}
