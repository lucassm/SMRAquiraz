package model;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;
import view.DialogFuncao50;
import view.DialogFuncao51;

/**
 *
 * @author lucas
 */
public class Chave {

    public SvgWidget widget;
    public String feeder;
    public String manufacturer;
    public ComponenteGrafico componenteGrafico;
    public DialogFuncao50 dialogFuncao50;
    public DialogFuncao51 dialogFuncao51;

    public Chave(final String name, final Graficos grafico, String way, String tipo, Point position) {

        widget = grafico.createSvgWidget(way, "<html><b>" + name + "</b></html>", position);
        widget.addChild(new LODWidget(widget.getScene(), 5, 0.1, name));


        if (tipo.equalsIgnoreCase("alimentador")) {

            widget.getActions().addAction(ActionFactory.createPopupMenuAction(new PopupMenuProvider() {

                @Override
                public JPopupMenu getPopupMenu(Widget widget, Point localLocation) {

                    JPopupMenu popup = new JPopupMenu();

                    JMenuItem item1 = new JMenuItem("Função 50");
                    JMenuItem item2 = new JMenuItem("Função 51");
                    JMenuItem item3 = new JMenuItem("Função 50N");
                    JMenuItem item4 = new JMenuItem("Função 51N");
                    
                    item1.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            dialogFuncao50 = new DialogFuncao50();
                            dialogFuncao50.setVisible(true);
                        }
                    });

                    item2.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            dialogFuncao51 = new DialogFuncao51();
                            dialogFuncao51.setVisible(true);
                        }
                    });

                    popup.add(item1);
                    popup.add(item2);
                    popup.add(item3);
                    popup.add(item4);
                    return popup;
                }
            }));

        }else if(tipo.equalsIgnoreCase("subestacao")){
            
        }//fim do if else

    }//fim do construtor

}//fim da classe