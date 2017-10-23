/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.DesenvolvedoraDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Desenvolvedora;

/**
 *
 * @author anken
 */
public class CadDesenvolvedora extends javax.swing.JFrame {

    /**
     * Creates new form CadDesenvolvedora
     */
    public CadDesenvolvedora() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelGuia = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelPais = new javax.swing.JLabel();
        jLabelData = new javax.swing.JLabel();
        jLabelBiblioteca = new javax.swing.JLabel();
        jLabelFramework = new javax.swing.JLabel();
        jTextFieldPais = new javax.swing.JTextField();
        jTextFieldFundacao = new javax.swing.JTextField();
        jTextFieldBibliotecas = new javax.swing.JTextField();
        jTextFieldFrameworks = new javax.swing.JTextField();
        jButtonCadastrar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);

        jLabelGuia.setText("Cadastro de Desenvolvedor");

        jLabelNome.setText("Nome:");

        jTextFieldNome.setText("nome");
        jTextFieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeActionPerformed(evt);
            }
        });

        jLabelPais.setText("País de Origem:");

        jLabelData.setText("Data de Fundação:");

        jLabelBiblioteca.setText("Bibliotecas:");

        jLabelFramework.setText("Frameworks:");

        jTextFieldPais.setText("país");
        jTextFieldPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPaisActionPerformed(evt);
            }
        });

        jTextFieldFundacao.setText("data");

        jTextFieldBibliotecas.setText("bibliotecas");

        jTextFieldFrameworks.setText("frameworks");
        jTextFieldFrameworks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFrameworksActionPerformed(evt);
            }
        });

        jButtonCadastrar.setText("Cadastrar");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jButton1.setText("Ler");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelGuia)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelData)
                            .addComponent(jLabelBiblioteca)
                            .addComponent(jLabelFramework)
                            .addComponent(jLabelPais)
                            .addComponent(jLabelNome)
                            .addComponent(jButtonCadastrar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldFrameworks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldBibliotecas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldFundacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelGuia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPais)
                    .addComponent(jTextFieldPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelData)
                    .addComponent(jTextFieldFundacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBiblioteca)
                    .addComponent(jTextFieldBibliotecas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFramework)
                    .addComponent(jTextFieldFrameworks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar)
                    .addComponent(jButton1))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeActionPerformed

    private void jTextFieldPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPaisActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        Desenvolvedora dev = new Desenvolvedora();
        
        dev.setName(jTextFieldNome.getText());
        dev.setOrigin(jTextFieldPais.getText());
        dev.setFoundation(jTextFieldFundacao.getText());
        dev.setBibliotecas(jTextFieldBibliotecas.getText());
        dev.setFrameworks(jTextFieldFrameworks.getText());
        
        DesenvolvedoraDAO devDao = new DesenvolvedoraDAO();
        
        try{
            System.out.println("Escrevendo em arquivo de texto");
            devDao.criarArquivo(dev);
        } catch (IOException erro){
            System.out.println(erro);
        }
        
        //DesenvolvedoraDAO devDao = new DesenvolvedoraDAO();
//        try {
//            devDao.create(dev);
//            System.out.println("\n Leitura das Desenvolvedoras cadastradas: \n");
//            devDao.read();
//            System.out.println("\n Leitura e escrita em binário \n");
//            devDao.principal(dev);
//        } catch (IOException ex) {
//            System.out.println("DEU RUIM");
//        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jTextFieldFrameworksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFrameworksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFrameworksActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DesenvolvedoraDAO dev = new DesenvolvedoraDAO();
        
        try {
            dev.read();
        } catch (IOException ex) {
            Logger.getLogger(CadDesenvolvedora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JLabel jLabelBiblioteca;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelFramework;
    private javax.swing.JLabel jLabelGuia;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelPais;
    private javax.swing.JTextField jTextFieldBibliotecas;
    private javax.swing.JTextField jTextFieldFrameworks;
    private javax.swing.JTextField jTextFieldFundacao;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldPais;
    // End of variables declaration//GEN-END:variables
}
