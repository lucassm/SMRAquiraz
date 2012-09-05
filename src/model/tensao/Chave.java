package model.tensao;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Chave {

    public double tensaoFonte;
    public double tensaoCarga;
    public double corrente;
    public Element xmlElement;
    public Trecho trecho1;
    public Trecho trecho2;
    String nome;

    public Chave(String nom, String horario, Trecho t1, Trecho t2, double tFont) {
        
        nome=nom;
        xmlElement = carregaXML(nome);
        
        List amostras = xmlElement.getChild("carregamento").getChildren("amostra");

        for (int i = 0; i < amostras.size(); i++) {
            Element amostra = (Element) amostras.get(i);
            String horarioString = amostra.getAttributeValue("horario");
            if (horarioString.equalsIgnoreCase(horario)) {

                corrente = Double.parseDouble(amostra.getValue());
            }
        }
        
        trecho1 = t1;
        trecho2 = t2;
        
        if (trecho1 == null) {
            tensaoFonte = tFont;
            tensaoCarga = tensaoFonte;
        }else{
            tensaoFonte = tFont - (trecho1.impedancia * trecho1.comprimento) * corrente;
            tensaoCarga = tensaoFonte;
        }
    }
    
    public void atualizar(String horario, double tFont){
        
        xmlElement = carregaXML(nome);
        
        List amostras = xmlElement.getChild("carregamento").getChildren("amostra");

        for (int i = 0; i < amostras.size(); i++) {
            Element amostra = (Element) amostras.get(i);
            String horarioString = amostra.getAttributeValue("horario");
            if (horarioString.equalsIgnoreCase(horario)) {
                corrente = Double.parseDouble(amostra.getValue());
                System.out.println(corrente + horario);
            }
        }

        if (trecho1 == null) {
            tensaoFonte = tFont;
            tensaoCarga = tensaoFonte;
        }else{
            tensaoFonte = tFont - (trecho1.impedancia * trecho1.comprimento) * corrente;
            tensaoCarga = tensaoFonte;
        }
        
    }

    public Element carregaXML(String nome) {

        File file = null;
        SAXBuilder builder = null;
        Document doc = null;
        Element agenteBD = null;

        file = new File("src/xml/" + nome + ".xml");
        builder = new SAXBuilder();

        try {
            doc = builder.build(file);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }//fim do try
        
        agenteBD = doc.getRootElement();
        return agenteBD;

    }//fim do método carregaXML
}
