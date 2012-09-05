package model;
/* ===========================================================
 * Grafico com JFreeChart : a free chart library for the Java
 * ===========================================================
 */

import java.awt.Color;
import java.io.IOException;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * An example of a time series chart.  For the most part, default settings are
 * used, except that the renderer is modified to show filled shapes (as well as
 * lines) at each data point.
 */
public class Grafico extends ApplicationFrame {

    public Double[] dados;
    public XYSeries s1;
    
    {
        // set a theme using the new shadow generator feature available in
        // 1.0.14 - for backwards compatibility it is not enabled by default
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",
                true));
    }

    /**
     *
     *
     * @param dados 
     * @param title  the frame title.
     * @param caminho  thhe way to file
     */
    public Grafico(Double[] dados) {
        
        super(null);
        
        this.dados = dados;
        ChartPanel chartPanel = (ChartPanel) createPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(1000,700));
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return A chart.
     */
    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart("Grafico",
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
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.black);
        plot.setRangeGridlinePaint(Color.black);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        /*XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }*/

        return chart;

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     */
    public XYSeriesCollection createDataset(Double[] dados) {

        s1 = new XYSeries("Serie I");
        s1.setMaximumItemCount(500);

        for(int i=0;i<dados.length;i++){
        	s1.add(i,dados[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);

        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * @param parameterObject TODO
     * @return A panel.
     */
    public JPanel createPanel() {

        JFreeChart chart = createChart(createDataset(dados));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     *	public static void main(String[] args) {
     *
     *   	Grafico demo = new Grafico(
     *          "Grafico XY","/home/lucas/foo");
     * 	demo.pack();
     *	RefineryUtilities.centerFrameOnScreen(demo);
     *	demo.setVisible(true);
     *
     * }
     *
     *
     */
    

}
