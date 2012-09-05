/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import java.util.Vector;
import javax.swing.JPanel;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author lucas
 */
public class LancaWidgets {

    public JPanel painel;
    public Graficos grafico = null;
    public Subestacao AQZ = null;
    public Subestacao JAB = null;
    public Subestacao MSJ = null;
    public Subestacao AGF = null;
    public Barra barraAQZ = null;
    public Barra barraJAB = null;
    public Barra barraMSJ = null;
    public Barra barraAGF = null;
    public Trecho T1 = null;
    public Trecho T2 = null;
    public Trecho T3 = null;
    public Trecho T4 = null;
    public Trecho T5 = null;
    public Trecho T6 = null;
    public Trecho T7 = null;
    public Trecho T8 = null;
    public Trecho T9 = null;
    public Trecho T10 = null;
    public Chave CH11 = null;
    public Chave CH12 = null;
    public Chave CH13 = null;
    public Chave CH14 = null;
    public Chave CH21 = null;
    public Chave CH22 = null;
    public Chave CH23 = null;
    public Chave CH24 = null;
    public Chave CH31 = null;
    public Chave CH32 = null;
    public Chave CH33 = null;
    public Chave CH34 = null;
    public Chave CH35 = null;
    public Chave CH41 = null;
    public Chave CH42 = null;
    public Chave CH43 = null;
    public Chave CH44 = null;
    public Chave CH45 = null;
    public Vector<Widget> chavesVector;

    public LancaWidgets(JPanel jPanel) {

        this.painel = jPanel;

        grafico = new Graficos();

        AQZ = new Subestacao("AQZ", getGrafico(), "imagens/seAquiraz.png", new Point(25, 270));
        JAB = new Subestacao("JAB", getGrafico(), "imagens/seJabuti.png", new Point(1045, 70));
        MSJ = new Subestacao("MSJ", getGrafico(), "imagens/seMessejana.png", new Point(1045, 200));
        AGF = new Subestacao("AGF", getGrafico(), "imagens/seAguaFria.png", new Point(1045, 330));
        //SE aero1 = new SE("Eólica", graph, "imagens/aero01.png", new Point(265, 590));
        //SE aero2 = new SE("Eólica", graph, "imagens/aero02.png", new Point(345, 590));

        barraAQZ = new Barra("Barra Aquiraz", getGrafico(), "imagens/barraAquiraz.png", new Point(145, 90));
        barraJAB = new Barra("Barra Jabuti", getGrafico(), "imagens/e01.png", new Point(765, 70));
        barraMSJ = new Barra("Barra Messejana", getGrafico(), "imagens/e01.png", new Point(765, 200));
        barraAGF = new Barra("Barra Agua Fria", getGrafico(), "imagens/e02.png", new Point(765, 330));

        T1 = new Trecho("Trecho 11", getGrafico(), "imagens/e11.png", new Point(245, 110));
        T2 = new Trecho("Trecho 12", getGrafico(), "imagens/e12.png", new Point(605, 110));
        T3 = new Trecho("Trecho 21", getGrafico(), "imagens/e21.png", new Point(245, 200));
        T4 = new Trecho("Trecho 22", getGrafico(), "imagens/e22.png", new Point(605, 240));
        T5 = new Trecho("Trecho 31", getGrafico(), "imagens/e31.png", new Point(245, 370));
        T6 = new Trecho("Trecho 32", getGrafico(), "imagens/e32.png", new Point(385, 330));
        T7 = new Trecho("Trecho 33", getGrafico(), "imagens/e33.png", new Point(605, 370));
        T8 = new Trecho("Trecho 41", getGrafico(), "imagens/e41.png", new Point(245, 500));
        T9 = new Trecho("Trecho 42", getGrafico(), "imagens/e42.png", new Point(535, 500));
        T10 = new Trecho("Trecho 43", getGrafico(), "imagens/e43.png", new Point(441, 590));

        CH11 = new Chave("CH11", getGrafico(), "imagens/d1.png", new Point(196, 90));
        CH12 = new Chave("CH12", getGrafico(), "imagens/d1.png", new Point(425, 150));
        CH13 = new Chave("CH13", getGrafico(), "imagens/d1.png", new Point(555, 90));
        CH14 = new Chave("CH14", getGrafico(), "imagens/d1.png", new Point(715, 90));
        CH21 = new Chave("CH21", getGrafico(), "imagens/d2.png", new Point(196, 220));
        CH22 = new Chave("CH22", getGrafico(), "imagens/d2.png", new Point(425, 280));
        CH23 = new Chave("CH23", getGrafico(), "imagens/d2.png", new Point(555, 220));
        CH24 = new Chave("CH24", getGrafico(), "imagens/d2.png", new Point(715, 220));
        CH31 = new Chave("CH31", getGrafico(), "imagens/d3.png", new Point(195, 350));
        CH32 = new Chave("CH32", getGrafico(), "imagens/d3.png", new Point(335, 350));
        CH33 = new Chave("CH33", getGrafico(), "imagens/d3.png", new Point(555, 350));
        CH34 = new Chave("CH34", getGrafico(), "imagens/d3.png", new Point(715, 350));
        CH35 = new Chave("CH35", getGrafico(), "imagens/d3.png", new Point(265, 540));
        CH41 = new Chave("CH41", getGrafico(), "imagens/d4.png", new Point(195, 480));
        CH42 = new Chave("CH42", getGrafico(), "imagens/d4.png", new Point(485, 480));
        CH43 = new Chave("CH43", getGrafico(), "imagens/d4.png", new Point(715, 480));
        CH44 = new Chave("CH44", getGrafico(), "imagens/d4.png", new Point(425, 540));
        CH45 = new Chave("CH45", getGrafico(), "imagens/d4.png", new Point(345, 540));

        grafico.createImageWidget("imagens/redeAquiraz.png", null, new Point(455, 5));

        grafico.createImageWidget("imagens/coelce.png", null, new Point(840, 575));

        grafico.createImageWidget("imagens/ufc.png", null, new Point(1025, 525));

        painel.setLayout(new java.awt.BorderLayout());

        chavesVector = new Vector();

        chavesVector.add(CH11.widget);

        chavesVector.add(CH12.widget);

        chavesVector.add(CH13.widget);

        chavesVector.add(CH14.widget);

        chavesVector.add(CH21.widget);

        chavesVector.add(CH22.widget);

        chavesVector.add(CH23.widget);

        chavesVector.add(CH24.widget);

        chavesVector.add(CH31.widget);

        chavesVector.add(CH32.widget);

        chavesVector.add(CH33.widget);

        chavesVector.add(CH34.widget);

        //chavesVector.add(CH35.widget);

        chavesVector.add(CH41.widget);

        chavesVector.add(CH42.widget);

        chavesVector.add(CH43.widget);

        chavesVector.add(CH44.widget);

        //chavesVector.add(CH45.widget);
        
        Vector<Servidor> servidores = new Vector(16);
        
        Servidor servidor1 = new Servidor(4001, 10);

        servidores.add(servidor1);
        Servidor servidor2 = new Servidor(4002, 10);

        servidores.add(servidor2);
        Servidor servidor3 = new Servidor(4003, 10);

        servidores.add(servidor3);
        Servidor servidor4 = new Servidor(4004, 10);

        servidores.add(servidor4);
        Servidor servidor5 = new Servidor(4005, 10);

        servidores.add(servidor5);
        Servidor servidor6 = new Servidor(4006, 10);

        servidores.add(servidor6);
        Servidor servidor7 = new Servidor(4007, 10);

        servidores.add(servidor7);
        Servidor servidor8 = new Servidor(4008, 10);

        servidores.add(servidor8);
        Servidor servidor9 = new Servidor(4009, 10);

        servidores.add(servidor9);
        Servidor servidor10 = new Servidor(4010, 10);

        servidores.add(servidor10);
        Servidor servidor11 = new Servidor(4011, 10);

        servidores.add(servidor11);
        Servidor servidor12 = new Servidor(4012, 10);

        servidores.add(servidor12);
        Servidor servidor13 = new Servidor(4013, 10);

        servidores.add(servidor13);
        Servidor servidor14 = new Servidor(4014, 10);

        servidores.add(servidor14);
        Servidor servidor15 = new Servidor(4015, 10);

        servidores.add(servidor15);
        Servidor servidor16 = new Servidor(4016, 10);

        servidores.add(servidor16);

        servidor1.LancaThread(servidores, chavesVector);
    }
    
    public void visualizarWidgets(){
        
        getPainel().add("Center", getGrafico().createView());
    }

    /**
     * @return the painel
     */
    public JPanel getPainel() {
        return painel;
    }

    /**
     * @param painel the painel to set
     */
    public void setPainel(JPanel painel) {
        this.painel = painel;
    }

    /**
     * @return the grafico
     */
    public Graficos getGrafico() {
        return grafico;
    }

    /**
     * @param grafico the grafico to set
     */
    public void setGrafico(Graficos grafico) {
        this.grafico = grafico;
    }

    /**
     * @return the AQZ
     */
    public Subestacao getAQZ() {
        return AQZ;
    }

    /**
     * @param AQZ the AQZ to set
     */
    public void setAQZ(Subestacao AQZ) {
        this.AQZ = AQZ;
    }

    /**
     * @return the JAB
     */
    public Subestacao getJAB() {
        return JAB;
    }

    /**
     * @param JAB the JAB to set
     */
    public void setJAB(Subestacao JAB) {
        this.JAB = JAB;
    }

    /**
     * @return the MSJ
     */
    public Subestacao getMSJ() {
        return MSJ;
    }

    /**
     * @param MSJ the MSJ to set
     */
    public void setMSJ(Subestacao MSJ) {
        this.MSJ = MSJ;
    }

    /**
     * @return the AGF
     */
    public Subestacao getAGF() {
        return AGF;
    }

    /**
     * @param AGF the AGF to set
     */
    public void setAGF(Subestacao AGF) {
        this.AGF = AGF;
    }

    /**
     * @return the barraAQZ
     */
    public Barra getBarraAQZ() {
        return barraAQZ;
    }

    /**
     * @param barraAQZ the barraAQZ to set
     */
    public void setBarraAQZ(Barra barraAQZ) {
        this.barraAQZ = barraAQZ;
    }

    /**
     * @return the barraJAB
     */
    public Barra getBarraJAB() {
        return barraJAB;
    }

    /**
     * @param barraJAB the barraJAB to set
     */
    public void setBarraJAB(Barra barraJAB) {
        this.barraJAB = barraJAB;
    }

    /**
     * @return the barraMSJ
     */
    public Barra getBarraMSJ() {
        return barraMSJ;
    }

    /**
     * @param barraMSJ the barraMSJ to set
     */
    public void setBarraMSJ(Barra barraMSJ) {
        this.barraMSJ = barraMSJ;
    }

    /**
     * @return the barraAGF
     */
    public Barra getBarraAGF() {
        return barraAGF;
    }

    /**
     * @param barraAGF the barraAGF to set
     */
    public void setBarraAGF(Barra barraAGF) {
        this.barraAGF = barraAGF;
    }

    /**
     * @return the T1
     */
    public Trecho getT1() {
        return T1;
    }

    /**
     * @param T1 the T1 to set
     */
    public void setT1(Trecho T1) {
        this.T1 = T1;
    }

    /**
     * @return the T2
     */
    public Trecho getT2() {
        return T2;
    }

    /**
     * @param T2 the T2 to set
     */
    public void setT2(Trecho T2) {
        this.T2 = T2;
    }

    /**
     * @return the T3
     */
    public Trecho getT3() {
        return T3;
    }

    /**
     * @param T3 the T3 to set
     */
    public void setT3(Trecho T3) {
        this.T3 = T3;
    }

    /**
     * @return the T4
     */
    public Trecho getT4() {
        return T4;
    }

    /**
     * @param T4 the T4 to set
     */
    public void setT4(Trecho T4) {
        this.T4 = T4;
    }

    /**
     * @return the T5
     */
    public Trecho getT5() {
        return T5;
    }

    /**
     * @param T5 the T5 to set
     */
    public void setT5(Trecho T5) {
        this.T5 = T5;
    }

    /**
     * @return the T6
     */
    public Trecho getT6() {
        return T6;
    }

    /**
     * @param T6 the T6 to set
     */
    public void setT6(Trecho T6) {
        this.T6 = T6;
    }

    /**
     * @return the T7
     */
    public Trecho getT7() {
        return T7;
    }

    /**
     * @param T7 the T7 to set
     */
    public void setT7(Trecho T7) {
        this.T7 = T7;
    }

    /**
     * @return the T8
     */
    public Trecho getT8() {
        return T8;
    }

    /**
     * @param T8 the T8 to set
     */
    public void setT8(Trecho T8) {
        this.T8 = T8;
    }

    /**
     * @return the T9
     */
    public Trecho getT9() {
        return T9;
    }

    /**
     * @param T9 the T9 to set
     */
    public void setT9(Trecho T9) {
        this.T9 = T9;
    }

    /**
     * @return the T10
     */
    public Trecho getT10() {
        return T10;
    }

    /**
     * @param T10 the T10 to set
     */
    public void setT10(Trecho T10) {
        this.T10 = T10;
    }

    /**
     * @return the CH11
     */
    public Chave getCH11() {
        return CH11;
    }

    /**
     * @param CH11 the CH11 to set
     */
    public void setCH11(Chave CH11) {
        this.CH11 = CH11;
    }

    /**
     * @return the CH12
     */
    public Chave getCH12() {
        return CH12;
    }

    /**
     * @param CH12 the CH12 to set
     */
    public void setCH12(Chave CH12) {
        this.CH12 = CH12;
    }

    /**
     * @return the CH13
     */
    public Chave getCH13() {
        return CH13;
    }

    /**
     * @param CH13 the CH13 to set
     */
    public void setCH13(Chave CH13) {
        this.CH13 = CH13;
    }

    /**
     * @return the CH14
     */
    public Chave getCH14() {
        return CH14;
    }

    /**
     * @param CH14 the CH14 to set
     */
    public void setCH14(Chave CH14) {
        this.CH14 = CH14;
    }

    /**
     * @return the CH21
     */
    public Chave getCH21() {
        return CH21;
    }

    /**
     * @param CH21 the CH21 to set
     */
    public void setCH21(Chave CH21) {
        this.CH21 = CH21;
    }

    /**
     * @return the CH22
     */
    public Chave getCH22() {
        return CH22;
    }

    /**
     * @param CH22 the CH22 to set
     */
    public void setCH22(Chave CH22) {
        this.CH22 = CH22;
    }

    /**
     * @return the CH23
     */
    public Chave getCH23() {
        return CH23;
    }

    /**
     * @param CH23 the CH23 to set
     */
    public void setCH23(Chave CH23) {
        this.CH23 = CH23;
    }

    /**
     * @return the CH24
     */
    public Chave getCH24() {
        return CH24;
    }

    /**
     * @param CH24 the CH24 to set
     */
    public void setCH24(Chave CH24) {
        this.CH24 = CH24;
    }

    /**
     * @return the CH31
     */
    public Chave getCH31() {
        return CH31;
    }

    /**
     * @param CH31 the CH31 to set
     */
    public void setCH31(Chave CH31) {
        this.CH31 = CH31;
    }

    /**
     * @return the CH32
     */
    public Chave getCH32() {
        return CH32;
    }

    /**
     * @param CH32 the CH32 to set
     */
    public void setCH32(Chave CH32) {
        this.CH32 = CH32;
    }

    /**
     * @return the CH33
     */
    public Chave getCH33() {
        return CH33;
    }

    /**
     * @param CH33 the CH33 to set
     */
    public void setCH33(Chave CH33) {
        this.CH33 = CH33;
    }

    /**
     * @return the CH34
     */
    public Chave getCH34() {
        return CH34;
    }

    /**
     * @param CH34 the CH34 to set
     */
    public void setCH34(Chave CH34) {
        this.CH34 = CH34;
    }

    /**
     * @return the CH35
     */
    public Chave getCH35() {
        return CH35;
    }

    /**
     * @param CH35 the CH35 to set
     */
    public void setCH35(Chave CH35) {
        this.CH35 = CH35;
    }

    /**
     * @return the CH41
     */
    public Chave getCH41() {
        return CH41;
    }

    /**
     * @param CH41 the CH41 to set
     */
    public void setCH41(Chave CH41) {
        this.CH41 = CH41;
    }

    /**
     * @return the CH42
     */
    public Chave getCH42() {
        return CH42;
    }

    /**
     * @param CH42 the CH42 to set
     */
    public void setCH42(Chave CH42) {
        this.CH42 = CH42;
    }

    /**
     * @return the CH43
     */
    public Chave getCH43() {
        return CH43;
    }

    /**
     * @param CH43 the CH43 to set
     */
    public void setCH43(Chave CH43) {
        this.CH43 = CH43;
    }

    /**
     * @return the CH44
     */
    public Chave getCH44() {
        return CH44;
    }

    /**
     * @param CH44 the CH44 to set
     */
    public void setCH44(Chave CH44) {
        this.CH44 = CH44;
    }

    /**
     * @return the CH45
     */
    public Chave getCH45() {
        return CH45;
    }

    /**
     * @param CH45 the CH45 to set
     */
    public void setCH45(Chave CH45) {
        this.CH45 = CH45;
    }

    /**
     * @return the chavesVector
     */
    public Vector<Widget> getChavesVector() {
        return chavesVector;
    }

    /**
     * @param chavesVector the chavesVector to set
     */
    public void setChavesVector(Vector<Widget> chavesVector) {
        this.chavesVector = chavesVector;
    }
    
}
