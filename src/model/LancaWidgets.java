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
    public Subestacao FTZ = null;
    public Subestacao DMG = null;
    public Subestacao AQZ = null;
    public Subestacao JAB = null;
    public Subestacao MSJ = null;
    public Subestacao AGF = null;
    public Subestacao AE1 = null;
    public Subestacao AE2 = null;
    
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
    public Trecho T11 = null;
    public Trecho T12 = null;
    public Trecho T13 = null;
    
    public Chave CH11 = null;
    public Chave CH12 = null;
    public Chave CH13 = null;
    public Chave CH14 = null;
    public Chave CH15 = null;
    public Chave CH21 = null;
    public Chave CH22 = null;
    public Chave CH23 = null;
    public Chave CH24 = null;
    public Chave CH25 = null;
    public Chave CH31 = null;
    public Chave CH32 = null;
    public Chave CH33 = null;
    public Chave CH34 = null;
    public Chave CH35 = null;
    public Chave CH41 = null;
    public Chave CH42 = null;
    public Chave CH43 = null;
    public Chave CH44 = null;
    
    public Chave CHAQZ1 = null;
    public Chave CHAQZ2 = null;
    public Chave CHJAB1 = null;
    public Chave CHJAB2 = null;
    public Chave CHMSJ1 = null;
    public Chave CHAGF1 = null;
    public Chave CHAGF2 = null;
    public Chave CHAE1 = null;
    public Chave CHAE2 = null;
    
    public Vector<Widget> chavesVector;

    public LancaWidgets(JPanel jPanel) {

        this.painel = jPanel;

        grafico = new Graficos();

        FTZ = new Subestacao(null, getGrafico(), "imagens/seFTZ.png", new Point(28, 18));
        DMG = new Subestacao(null, getGrafico(), "imagens/seDMG.png", new Point(1136, 351));
        AQZ = new Subestacao("AQZ", getGrafico(), "imagens/seAQZ.png", new Point(157, 274));
        JAB = new Subestacao("JAB", getGrafico(), "imagens/seJAB.png", new Point(1055, 125));
        MSJ = new Subestacao("MSJ", getGrafico(), "imagens/seMSJ.png", new Point(1055, 265));
        AGF = new Subestacao("AGF", getGrafico(), "imagens/seAGF.png", new Point(1055, 378));
        AE1 = new Subestacao("AE1", getGrafico(), "imagens/aeroGerador.png", new Point(400, 575));
        AE2 = new Subestacao("AE2", getGrafico(), "imagens/aeroGerador.png", new Point(466, 575));
        
        barraAQZ = new Barra("Barra Aquiraz", getGrafico(), "imagens/barraAQZ.png", new Point(297, 111));
        barraJAB = new Barra("Barra Jabuti", getGrafico(), "imagens/barraJAB.png", new Point(954, 92));
        barraMSJ = new Barra("Barra Messejana", getGrafico(), "imagens/barraMSJ.png", new Point(954, 252));
        barraAGF = new Barra("Barra Agua Fria", getGrafico(), "imagens/barraAGF.png", new Point(954, 378));
        
        T1 = new Trecho("Trecho 1", getGrafico(), "imagens/trecho1.png", new Point(385, 179));
        T2 = new Trecho("Trecho 2", getGrafico(), "imagens/trecho2.png", new Point(682, 179));
        T3 = new Trecho("Trecho 3", getGrafico(), "imagens/trecho3.png", new Point(814, 179));
        
        T4 = new Trecho("Trecho 4", getGrafico(), "imagens/trecho4.png", new Point(385, 254));
        T5 = new Trecho("Trecho 5", getGrafico(), "imagens/trecho5.png", new Point(682, 285));
        T6 = new Trecho("Trecho 6", getGrafico(), "imagens/trecho6.png", new Point(814, 285));
        
        T7 = new Trecho("Trecho 7", getGrafico(), "imagens/trecho7.png", new Point(385, 393));
        T8 = new Trecho("Trecho 8", getGrafico(), "imagens/trecho8.png", new Point(500, 360));
        T9 = new Trecho("Trecho 9", getGrafico(), "imagens/trecho9.png", new Point(682, 393));
        
        
        T10 = new Trecho("Trecho 10", getGrafico(), "imagens/trecho10.png", new Point(813, 393));
        T11 = new Trecho("Trecho 11", getGrafico(), "imagens/trecho11.png", new Point(682, 502));
        T12 = new Trecho("Trecho 12", getGrafico(), "imagens/trecho12.png", new Point(385, 502));
        T13 = new Trecho("Carga", getGrafico(), "imagens/carga.png", new Point(545, 575));
        
        
        CH11 = new Chave("21I7", getGrafico(), "imagens/religadorNF.png", "alimentador",new Point(341, 161));
        CH12 = new Chave("RP1", getGrafico(), "imagens/religadorNA.png", "alimentador", new Point(531, 211));
        CH13 = new Chave("RS1", getGrafico(), "imagens/religadorNF.png", "alimentador", new Point(638, 161));
        CH14 = new Chave("RL1", getGrafico(), "imagens/religadorNA.png", "alimentador", new Point(770, 161));
        CH15 = new Chave("21F8", getGrafico(), "imagens/religadorNF.png", "alimentador", new Point(912, 161));
        
        CH21 = new Chave("21I6", getGrafico(), "imagens/religadorNF.png", "alimentador", new Point(341, 268));
        CH22 = new Chave("RP2", getGrafico(), "imagens/religadorNA.png", "alimentador", new Point(531, 318));
        CH23 = new Chave("RS2", getGrafico(), "imagens/religadorNF.png", "alimentador", new Point(638, 268));
        CH24 = new Chave("RL2", getGrafico(), "imagens/religadorNA.png", "alimentador", new Point(770, 268));
        CH25 = new Chave("21M3", getGrafico(), "imagens/religadorNF.png", "alimentador", new Point(912, 268));
        
        CH31 = new Chave("21I5", getGrafico(), "imagens/religadorNF.png", "alimentador", new Point(341, 376));
        CH32 = new Chave("RS3", getGrafico(), "imagens/religadorNF.png", "alimentador", new Point(458, 376));
        CH33 = new Chave("RS4", getGrafico(), "imagens/religadorNF.png", "alimentador", new Point(638, 376));
        CH34 = new Chave("RL3", getGrafico(), "imagens/religadorNA.png", "alimentador", new Point(770, 376));
        CH35 = new Chave("21I7", getGrafico(), "imagens/religadorNF.png", "alimentador", new Point(912, 415));
        
        CH41 = new Chave("21I4", getGrafico(), "imagens/religadorNF.png", "alimentador", new Point(341, 483));
        CH42 = new Chave("RC1", getGrafico(), "imagens/religadorNF.png", "alimentador", new Point(532, 532));
        CH43 = new Chave("RS5", getGrafico(), "imagens/religadorNF.png", "alimentador", new Point(638, 483));
        CH44 = new Chave("RL4", getGrafico(), "imagens/religadorNA.png", "alimentador", new Point(770, 483));

        CHAQZ1 = new Chave("11T1", getGrafico(), "imagens/religadorNF.png", "subestacao", new Point(256, 285));
        CHAQZ2 = new Chave("11T2", getGrafico(), "imagens/religadorNF.png", "subestacao", new Point(256, 359));
        CHJAB1 = new Chave("11T1", getGrafico(), "imagens/religadorNF.png", "subestacao", new Point(1014, 128));
        CHJAB2 = new Chave("11T2", getGrafico(), "imagens/religadorNF.png", "subestacao", new Point(1014, 186));
        CHMSJ1 = new Chave("11T1", getGrafico(), "imagens/religadorNF.png", "subestacao", new Point(1014, 269));
        CHAGF1 = new Chave("11T1", getGrafico(), "imagens/religadorNF.png", "subestacao", new Point(1014, 382));
        CHAGF2 = new Chave("11T2", getGrafico(), "imagens/religadorNF.png", "subestacao", new Point(1014, 440));
        CHAE1 = new Chave("RW1", getGrafico(), "imagens/religadorNF.png", "subestacao", new Point(400, 532));
        CHAE2 = new Chave("RW2", getGrafico(), "imagens/religadorNF.png", "subestacao", new Point(466, 532));

        grafico.createImageWidget("imagens/legenda.png", null, new Point(59, 452));

        grafico.createImageWidget("imagens/coelce.png", null, new Point(1041, 583));

        grafico.createImageWidget("imagens/ufc.png", null, new Point(1191, 547));

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

        chavesVector.add(CH35.widget);

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
