/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tst.leitorfebrabam.view;

import br.com.tst.leitorfebrabam.model.V2;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author p006184
 */
public class FaturaV2 extends javax.swing.JFrame {

    private V2 v2;

    /**
     * Creates new form FaturaV2
     */
    public FaturaV2(V2 v2) {
        initComponents();
        configComponent();
        this.v2 = v2;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        jButtonHeader = new javax.swing.JButton();
        jLabelTtileMenu = new javax.swing.JLabel();
        jButtonResumo = new javax.swing.JButton();
        jButtonBilhetes = new javax.swing.JButton();
        jButtonServiços = new javax.swing.JButton();
        jButtonDescontos = new javax.swing.JButton();
        jButtonTrailler = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelHeader = new javax.swing.JPanel();
        jLabelTitleHeader = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        jPanelMenu.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMenu.setForeground(new java.awt.Color(255, 255, 255));

        jButtonHeader.setBackground(new java.awt.Color(0, 153, 255));
        jButtonHeader.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jButtonHeader.setForeground(new java.awt.Color(255, 255, 255));
        jButtonHeader.setText("HEADER");

        jLabelTtileMenu.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelTtileMenu.setForeground(new java.awt.Color(102, 102, 102));
        jLabelTtileMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTtileMenu.setText("FATURA V2");

        jButtonResumo.setBackground(new java.awt.Color(0, 153, 255));
        jButtonResumo.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jButtonResumo.setForeground(new java.awt.Color(255, 255, 255));
        jButtonResumo.setText("RESUMO");
        jButtonResumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResumoActionPerformed(evt);
            }
        });

        jButtonBilhetes.setBackground(new java.awt.Color(0, 153, 255));
        jButtonBilhetes.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jButtonBilhetes.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBilhetes.setText("BILHETES");

        jButtonServiços.setBackground(new java.awt.Color(0, 153, 255));
        jButtonServiços.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jButtonServiços.setForeground(new java.awt.Color(255, 255, 255));
        jButtonServiços.setText("SERVIÇOS");

        jButtonDescontos.setBackground(new java.awt.Color(0, 153, 255));
        jButtonDescontos.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jButtonDescontos.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDescontos.setText("DESCONTOS");

        jButtonTrailler.setBackground(new java.awt.Color(0, 153, 255));
        jButtonTrailler.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jButtonTrailler.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTrailler.setText("TRAILLER");

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTtileMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonServiços, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jButtonHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonResumo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBilhetes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDescontos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(jButtonTrailler, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTtileMenu)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonHeader)
                .addGap(18, 18, 18)
                .addComponent(jButtonResumo)
                .addGap(18, 18, 18)
                .addComponent(jButtonBilhetes)
                .addGap(18, 18, 18)
                .addComponent(jButtonServiços)
                .addGap(18, 18, 18)
                .addComponent(jButtonDescontos)
                .addGap(18, 18, 18)
                .addComponent(jButtonTrailler)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanelHeader.setBackground(new java.awt.Color(255, 255, 255));

        jLabelTitleHeader.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelTitleHeader.setForeground(new java.awt.Color(102, 102, 102));
        jLabelTitleHeader.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTitleHeader.setText("HEADER");

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabelTitleHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                        .addGap(262, 262, 262))
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addContainerGap())))
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitleHeader)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonResumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResumoActionPerformed
        
        

    }//GEN-LAST:event_jButtonResumoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBilhetes;
    private javax.swing.JButton jButtonDescontos;
    private javax.swing.JButton jButtonHeader;
    private javax.swing.JButton jButtonResumo;
    private javax.swing.JButton jButtonServiços;
    private javax.swing.JButton jButtonTrailler;
    private javax.swing.JLabel jLabelTitleHeader;
    private javax.swing.JLabel jLabelTtileMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

    private void configComponent() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}
