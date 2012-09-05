
package model.tensao;

/**
 *
 * @author lucas
 */
public class Fonte {
    
    public double tensao;
    public double carregamentoMaximo;
    public double carregamentoAtual;
    
    public Fonte(double tensao, double carregamentoAtual, double carregamentoMaximo){
        
        this.tensao = tensao;
        this.carregamentoMaximo = carregamentoMaximo;
        this.carregamentoAtual = carregamentoAtual;
        
    }
    
    public void atualizaInformacoes(double carregamentoAtual){
        
        this.carregamentoAtual = carregamentoAtual;
    }
}
