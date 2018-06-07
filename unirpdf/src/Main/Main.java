/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Gestores.GestorBd;
import Gestores.GestorPersonas;
import Pojos.Registro;
import Utilidades.Mensaje;
import Utilidades.Utilidades;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author itanortegaortega
 */
public class Main extends javax.swing.JFrame {
    ArrayList<Registro> listaPortada = new ArrayList<>();
    ArrayList<Registro> listaFusion = new ArrayList<>();
    GestorPersonas gestorPersonas = new GestorPersonas();
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        GestorBd.conectar();
        if(!GestorBd.estaConectado()){
            Mensaje.Warning("No hay conexión con la base de datos.");
            System.exit(0);
        }
        
        cargarListas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Lst_Portada = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Lst_Fusion = new javax.swing.JList<>();
        Btn_Portada = new javax.swing.JButton();
        Btn_Fusionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(Lst_Portada);

        jLabel1.setText("Falta crear portada:");

        jLabel2.setText("Falta fusionar:");

        jScrollPane2.setViewportView(Lst_Fusion);

        Btn_Portada.setText("Crear portada");
        Btn_Portada.setEnabled(false);
        Btn_Portada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_PortadaActionPerformed(evt);
            }
        });

        Btn_Fusionar.setText("Fusionar");
        Btn_Fusionar.setEnabled(false);
        Btn_Fusionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_FusionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Btn_Portada, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Btn_Fusionar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Portada)
                    .addComponent(Btn_Fusionar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(411, 475));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_PortadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PortadaActionPerformed
        for(int i=0; i<listaPortada.size(); i++){
            if(Utilidades.crearPdf(listaPortada.get(i).getCampo(1).toString())){
                System.out.println("Archivo creado");
            }
        }
        cargarListas();
    }//GEN-LAST:event_Btn_PortadaActionPerformed

    private void Btn_FusionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FusionarActionPerformed
        
    }//GEN-LAST:event_Btn_FusionarActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Fusionar;
    private javax.swing.JButton Btn_Portada;
    private javax.swing.JList<String> Lst_Fusion;
    private javax.swing.JList<String> Lst_Portada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    private void cargarListas() {
        DefaultListModel modeloPortada=new DefaultListModel();
        DefaultListModel modeloFusion=new DefaultListModel();
        
        modeloPortada.removeAllElements();
        modeloFusion.removeAllElements();
        
        listaPortada = gestorPersonas.faltanPorGenerarPortada(false);
        listaFusion = gestorPersonas.faltanPorFusionar(false);
        
        Btn_Portada.setEnabled(false);
        Btn_Fusionar.setEnabled(false);
        
        int i;
        if(listaPortada.size()>0){
            Btn_Portada.setEnabled(true);
            for(i=0; i<listaPortada.size();i++){
                modeloPortada.addElement(listaPortada.get(i).getCampo(1).toString());
            }
            Lst_Portada.setModel(modeloPortada);
        }
        if(listaFusion.size()>0){
            Btn_Fusionar.setEnabled(true);
            for(i=0; i<listaFusion.size();i++){
                modeloFusion.addElement(listaFusion.get(i).getCampo(1).toString());
            }
            Lst_Fusion.setModel(modeloFusion);
        }
    }
}