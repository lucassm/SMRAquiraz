
package view;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.util.Iterator;
import javax.swing.JLabel;
import model.Grafico;

public class DialogFuncao51 extends javax.swing.JFrame {

    Grafico grafico;
        
    public DialogFuncao51() {
        
        initComponents();
        
        grafico = new Grafico(new Double[]{1.5}, new Double[]{1.5});
        jPanel3.setLayout(new BorderLayout());
        jPanel3.add(grafico.createPanel(), BorderLayout.CENTER);
        jPanel3.setVisible(true);
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFS = new javax.swing.JTextField();
        jTextIcarga = new javax.swing.JTextField();
        jTextRTC = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxCurvas = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jTextTempOperacao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextIcc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabelTAP = new javax.swing.JLabel();
        jLabelIpickup = new javax.swing.JLabel();
        jLabelDIAL = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jPanel1.border.title"))); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jLabel3.text")); // NOI18N

        jTextFS.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jTextFS.text")); // NOI18N

        jTextIcarga.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jTextIcarga.text")); // NOI18N

        jTextRTC.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jTextRTC.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jLabel4.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jLabel5.text")); // NOI18N

        jComboBoxCurvas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normalmente Inversa", "Muito Inversa", "Extremamente Inversa", "Inversa de Tempo Longo" }));

        jLabel6.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jLabel6.text")); // NOI18N

        jTextTempOperacao.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jTextTempOperacao.text")); // NOI18N

        jLabel7.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jLabel7.text")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jButton1.text")); // NOI18N

        jButton2.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jButton3.text")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jLabel8.text")); // NOI18N

        jTextIcc.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jTextIcc.text")); // NOI18N

        jLabel9.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jLabel9.text")); // NOI18N

        jLabelTAP.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabelTAP.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jLabelTAP.text")); // NOI18N
        jLabelTAP.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabelIpickup.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabelIpickup.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jLabelIpickup.text")); // NOI18N
        jLabelIpickup.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabelDIAL.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabelDIAL.setText(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jLabelDIAL.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDIAL)
                            .addComponent(jTextTempOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxCurvas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextIcarga)
                                    .addComponent(jTextFS)
                                    .addComponent(jTextRTC, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(jTextIcc))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTAP)
                                    .addComponent(jLabelIpickup)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextIcarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextRTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTAP))
                        .addGap(46, 46, 46))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextIcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabelIpickup))))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxCurvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextTempOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabelDIAL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DialogFuncao51.class, "DialogFuncao51.jPanel2.border.title"))); // NOI18N
        jPanel2.setAutoscrolls(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        double FS = Double.parseDouble(jTextFS.getText());
        double ICargaMax = Double.parseDouble(jTextIcarga.getText());
        double RTC = Double.parseDouble(jTextRTC.getText());
        double Icc = Double.parseDouble(jTextIcc.getText());
        String curva = (String) jComboBoxCurvas.getSelectedItem();
        double tempo = Double.parseDouble(jTextTempOperacao.getText());
        
        double TAP = (FS * ICargaMax)/(RTC);
        
        double Ipickup = TAP *  RTC;
        
        double multiplo = Icc/Ipickup;
        
        double alfa = 1;
        double beta = 1;
        
        if (curva.equalsIgnoreCase("Normalmente Inversa")) {
            alfa=0.02;
            beta=0.14;
        }else if(curva.equalsIgnoreCase("Muito Inversa")){
            alfa=1.0;
            beta=13.5;
        }else if(curva.equalsIgnoreCase("Extremamente Inversa")){
            alfa=2.0;
            beta=80;
        }
        
        double DIAL = tempo * Math.pow(multiplo-1, alfa)/beta;
        
        DecimalFormat format = new DecimalFormat("##.####");
        
        jLabelTAP.setText(format.format(TAP).replace(".", ","));
        jLabelIpickup.setText(format.format(Ipickup).replace(".", ","));
        jLabelDIAL.setText(format.format(DIAL).replace(".", ","));
        
        Double[] IccRange = new Double[19999];
        Double[] M = new Double[19999];
        double j = Ipickup+1;
        
        for (int i = 0; i < IccRange.length; i++) {
            
            j+=0.1;
            IccRange[i] = j;
            M[i] = IccRange[i]/Ipickup;
        }
        
        Double[] tempoDouble = new Double[19999];
        
        for (int i = 0; i < IccRange.length; i++) {
            tempoDouble[i] = DIAL * beta / (Math.pow(M[i], alfa)-1);
        }
        
        Grafico grafico = new Grafico(M,tempoDouble);
        
        jPanel3.setLayout(new BorderLayout());
        jPanel3.add(grafico.createPanel(), BorderLayout.CENTER);
        jPanel3.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogFuncao51.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogFuncao51.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogFuncao51.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogFuncao51.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DialogFuncao51().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBoxCurvas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDIAL;
    private javax.swing.JLabel jLabelIpickup;
    private javax.swing.JLabel jLabelTAP;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextFS;
    private javax.swing.JTextField jTextIcarga;
    private javax.swing.JTextField jTextIcc;
    private javax.swing.JTextField jTextRTC;
    private javax.swing.JTextField jTextTempOperacao;
    // End of variables declaration//GEN-END:variables
}
