package model.tensao;

public class Trecho {

    public double comprimento;
    public double impedancia;
    public double carregamentoMaximo;

    public Trecho(String nome, double comp, double imp, double cargamax) {

        comprimento = comp;
        impedancia = imp;
        carregamentoMaximo = cargamax;
    }
}