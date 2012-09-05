
package model;

import java.awt.Point;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;

/**
 *
 * @author lucas
 */
public class Chave {
    
    public ImageWidget widget;
    public String feeder;
    public String manufacturer;
    public ComponenteGrafico componenteGrafico;
    
    
    public Chave(final String name, final Graficos grafico, String way, Point position){
        
        widget = grafico.createImageWidget(way, "<html><b>"+name+"</b></html>", position);
//        widget.getActions().addAction(ActionFactory.createSelectAction(new SelectProvider() {
//
//            @Override
//            public boolean isAimingAllowed(Widget widget, Point point, boolean bln) {
//                return true;
//            }
//
//            @Override
//            public boolean isSelectionAllowed(Widget widget, Point point, boolean bln) {
//                return true;
//            }
//
//            @Override
//            public void select(Widget widget, Point point, boolean bln) {
//                
//                componenteGrafico = new ComponenteGrafico(widget);
//            }
//        }));
        
        widget.bringToBack();
    }
}