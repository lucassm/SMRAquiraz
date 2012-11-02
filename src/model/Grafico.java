package model;
/* ===========================================================
 * Grafico com JFreeChart : a free chart library for the Java
 * ===========================================================
 */

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

/**
 * An example of a time series chart.  For the most part, default settings are
 * used, except that the renderer is modified to show filled shapes (as well as
 * lines) at each data point.
 */
public class Grafico{

    public Double[] dadosX;
    public Double[] dadosY;
    public XYSeries s1;
    public JFreeChart jfreChart;
    public ChartPanel chartPanel;
   
    //ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",true));


    public Grafico(Double[] dadosX, Double[] dadosY) {
        
        this.dadosX = dadosX;
        this.dadosY = dadosY;
    }

    
    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart("Grafico de Coodenação de Seletividade",
        		"eixo x",
        		"eixo y",
        		dataset,
        		PlotOrientation.VERTICAL,
        		true,
        		true,
        		false);
        chart.setAntiAlias(true);
        chart.setBackgroundPaint(Color.white);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis xAxis = new LogarithmicAxis("Múltiplo (Icc/Ipickup)");
        xAxis.setAutoRange(true);
        NumberAxis yAxis = new LogarithmicAxis("Tempo (s)");
        yAxis.setAutoRange(true);
        
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);
        
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        
        return chart;
    }


    public XYSeriesCollection createDataset(Double[] eixoX, Double[] eixoY) {

        s1 = new XYSeries("Dados");

        for(int i=0;i<eixoX.length;i++){
        	s1.add(eixoX[i],eixoY[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);

        return dataset;
    }

    
    public JPanel createPanel() {

        jfreChart = createChart(createDataset(dadosX, dadosY));
        
        chartPanel = new ChartPanel(jfreChart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setVisible(true);
        
        return chartPanel;
    }
}