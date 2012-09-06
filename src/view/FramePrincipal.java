/*
 * FramePrincipal.java
 *
 * Created on 21/06/2012, 20:47:51
 */
package view;

import java.io.IOException;
import javax.swing.UnsupportedLookAndFeelException;
import model.CapturaLog;
import model.LancaAgentes;
import model.LancaWidgets;
import model.LimpaGrafico;
import model.SimulaCurto;
import org.openide.util.Exceptions;

/**
 *
 * @author lucas
 */
public class FramePrincipal extends javax.swing.JFrame {

    LancaWidgets lancaWidgets;
    LancaAgentes lancaAgentes;
    DialogSimulaCurto dialogSimulaCurto;
    DialogConfig dialogConfig;
    SimulaCurto simulaCurto;
    LimpaGrafico limpaGrafico;
    
    public FramePrincipal() {
        
        initComponents();
        
        lancaWidgets = new LancaWidgets(painel1);
        
        lancaWidgets.visualizarWidgets();
        
        lancaAgentes = new LancaAgentes();
        
        simulaCurto = new SimulaCurto(lancaWidgets.getGrafico(),lancaAgentes);
        
        limpaGrafico = new LimpaGrafico(simulaCurto, lancaWidgets.getChavesVector());
        
        dialogSimulaCurto = new DialogSimulaCurto(lancaWidgets.getGrafico(),simulaCurto);
        
        dialogConfig = new DialogConfig();
        
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        botaoLancaAgentes = new javax.swing.JButton();
        botaoSimulaCurto = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        botaoTensao = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        painel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SMRAquiraz");

        jToolBar1.setRollover(true);

        botaoLancaAgentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lanca_agentes.png"))); // NOI18N
        botaoLancaAgentes.setToolTipText("Lançar Agentes");
        botaoLancaAgentes.setFocusable(false);
        botaoLancaAgentes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoLancaAgentes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botaoLancaAgentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLancaAgentesActionPerformed(evt);
            }
        });
        jToolBar1.add(botaoLancaAgentes);

        botaoSimulaCurto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/simula_curto.png"))); // NOI18N
        botaoSimulaCurto.setToolTipText("Simular Curto-circuito");
        botaoSimulaCurto.setFocusable(false);
        botaoSimulaCurto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoSimulaCurto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botaoSimulaCurto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSimulaCurtoActionPerformed(evt);
            }
        });
        jToolBar1.add(botaoSimulaCurto);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/simula_perda_se.png"))); // NOI18N
        jButton3.setToolTipText("Simular Perda de SE");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/termina_agentes.png"))); // NOI18N
        jButton4.setToolTipText("Terminar Agentes");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/visualisa_info.png"))); // NOI18N
        jButton5.setToolTipText("Visualizar Informções");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/limpa_diagrama.png"))); // NOI18N
        jButton6.setToolTipText("Limpar Diagrama");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reinicia_agentes.png"))); // NOI18N
        jButton7.setToolTipText("Reiniciar Agentes");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton7);

        botaoTensao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/simula_dinamica.png"))); // NOI18N
        botaoTensao.setToolTipText("Sumulação Dinâmica");
        botaoTensao.setFocusable(false);
        botaoTensao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botaoTensao.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botaoTensao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoTensaoActionPerformed(evt);
            }
        });
        jToolBar1.add(botaoTensao);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/visualisa_log.png"))); // NOI18N
        jButton1.setToolTipText("Visualisa arquivos de log");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        painel1.setBackground(new java.awt.Color(254, 254, 254));
        painel1.setToolTipText("");

        javax.swing.GroupLayout painel1Layout = new javax.swing.GroupLayout(painel1);
        painel1.setLayout(painel1Layout);
        painel1Layout.setHorizontalGroup(
            painel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1158, Short.MAX_VALUE)
        );
        painel1Layout.setVerticalGroup(
            painel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(painel1);

        jMenu1.setText("Arquivo");

        jMenuItem1.setText("Lançar Agentes");
        jMenu1.add(jMenuItem1);

        jMenu3.setText("Simular Curto");

        jMenuItem2.setText("Trecho");
        jMenu3.add(jMenuItem2);

        jMenuItem3.setText("Subestação");
        jMenu3.add(jMenuItem3);

        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1172, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoLancaAgentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLancaAgentesActionPerformed
        
        lancaAgentes.lancaContainers();
        botaoLancaAgentes.setEnabled(false);
    }//GEN-LAST:event_botaoLancaAgentesActionPerformed

    private void botaoSimulaCurtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSimulaCurtoActionPerformed
        
        if (dialogSimulaCurto.isVisible()) {
            //nada a fazer
        }else{
            dialogSimulaCurto.setVisible(true);
        }
        
    }//GEN-LAST:event_botaoSimulaCurtoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

       if (dialogConfig.isVisible()) {
            //nada a fazer
        }else{
            dialogConfig.setVisible(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        limpaGrafico.limpaGrafico();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void botaoTensaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoTensaoActionPerformed
        
        DialogNivelDeTensao dialogNivelDeTensao = new DialogNivelDeTensao(lancaWidgets.chavesVector, lancaWidgets.getGrafico());
        dialogNivelDeTensao.setVisible(true);
    }//GEN-LAST:event_botaoTensaoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        CapturaLog capturaLog = new CapturaLog();
        
        capturaLog.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (InstantiationException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IllegalAccessException ex) {
            Exceptions.printStackTrace(ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Exceptions.printStackTrace(ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoLancaAgentes;
    private javax.swing.JButton botaoSimulaCurto;
    private javax.swing.JButton botaoTensao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel painel1;
    // End of variables declaration//GEN-END:variables
}
