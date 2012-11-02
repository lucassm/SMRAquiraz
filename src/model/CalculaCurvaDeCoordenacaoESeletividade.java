/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class CalculaCurvaDeCoordenacaoESeletividade {
    
    public double FS;
    public double ICargaMax;
    public double RTC;
    public double TAP;
    public double Ipickup;
    public double Icc;
    public double tempo;
    public double multiplo;
    public double DIAL;
    public double alpha;
    public double beta;
    
    public CalculaCurvaDeCoordenacaoESeletividade(double FS, double ICargaMax, double RTC, double Icc, double tempo){
        
        this.FS = FS;
        this.ICargaMax = ICargaMax;
        this.RTC = RTC;
        this.Icc = Icc;
        this.tempo = tempo;
    }
    
    public double calculaTAP(){
        
        TAP = (FS * ICargaMax)/(RTC);
        
        return TAP;
    }
    
    public double calculaIpickup(){
        
        Ipickup = TAP * RTC;
        return Ipickup;
    }
    
    public double calculaMultiplo(){
        multiplo = Icc/calculaIpickup();
        
        return multiplo;
    }
}