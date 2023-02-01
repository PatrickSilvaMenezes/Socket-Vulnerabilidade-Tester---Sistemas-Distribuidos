/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package testevulnerabilidadeclient;

/**
 *
 * @author patri
 */
public class PortScan_Client extends javax.swing.JFrame {

    private TesteVulnerabilidadeClient client = null;
    public PortScan_Client() {
        initComponents();
        client = new TesteVulnerabilidadeClient(this);
    }
    
    public void recebeMensagemServidor(String receivedServer){
    
        portList.add(receivedServer);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        ipTarget = new java.awt.TextField();
        btStartScan = new java.awt.Button();
        label2 = new java.awt.Label();
        portList = new java.awt.List();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label1.setBackground(new java.awt.Color(255, 255, 255));
        label1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        label1.setText("IP ALVO:");

        ipTarget.setName("ipTarget"); // NOI18N
        ipTarget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipTargetActionPerformed(evt);
            }
        });

        btStartScan.setBackground(new java.awt.Color(51, 153, 255));
        btStartScan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btStartScan.setLabel("START SCAN");
        btStartScan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btStartScanMouseReleased(evt);
            }
        });
        btStartScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartScanActionPerformed(evt);
            }
        });

        label2.setBackground(new java.awt.Color(255, 255, 255));
        label2.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        label2.setForeground(new java.awt.Color(0, 0, 255));
        label2.setText("PORT SCAN TESTER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(387, 387, 387)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(label2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(23, 23, 23)
                            .addComponent(ipTarget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(portList, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE))
                .addGap(208, 208, 208))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btStartScan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(384, 384, 384))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ipTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(portList, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btStartScan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ipTargetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipTargetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipTargetActionPerformed

    private void btStartScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartScanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btStartScanActionPerformed

    private void btStartScanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btStartScanMouseReleased
        if (ipTarget.getText().length()!=0)    
        {
            client.ConexaoServidor(ipTarget.getText());
            ipTarget.setEditable(false);
            btStartScan.setEnabled(false);
        }
            
        
    }//GEN-LAST:event_btStartScanMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(PortScan_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PortScan_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PortScan_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PortScan_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PortScan_Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btStartScan;
    private java.awt.TextField ipTarget;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.List portList;
    // End of variables declaration//GEN-END:variables
}