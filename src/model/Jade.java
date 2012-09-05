/**
 * 
 * Classe LancaAgentes.java
 * @author lucas
 * 
 */

package model;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class Jade {
    
    public Runtime rt;
    public ContainerController cc;
    public Profile p;
    int port = 1099;
    
    /**
     * 
     * Construtor da Classe JadeRun.
     * Inicializa as variáveis de Instancia
     * e cria um MainContainer
     * 
     */
    public Jade(){
        
        rt = Runtime.instance();
        p = new ProfileImpl("localhost",port,"lucas-laptop");
        cc = rt.createMainContainer(p);
    }
    
    public ContainerController criaAgenteContainer(){
        
        Profile p1 = new ProfileImpl();
        ContainerController cc1 = rt.createAgentContainer(p1);
        return cc1;
    }
    

    public AgentController startAgent(ContainerController cc,String name, String local,Object[] args){
        
        AgentController ac = null;
        if (cc != null){
            try{
                ac = cc.createNewAgent(name,local,args);
                ac.start();
                return ac;
            }catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        return null;
        
    }
    
}