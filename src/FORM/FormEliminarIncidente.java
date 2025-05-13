/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORM;

import Clases.GestorProblema;
import Clases.Incidente;
import Clases.Problema;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class FormEliminarIncidente extends javax.swing.JFrame {
    private GestorProblema gestor;
     int filaSeleccionada;
    /**
     * Creates new form FormEliminarIncidente
     */
    public FormEliminarIncidente(GestorProblema gestor) {
        this.gestor=gestor;
        initComponents();
        configurarTabla1();
        configurarTabla2();
        cargarProblemasEnTabla();
        
        
    

  
        
    }
    
    
    
    private void configurarTabla1() {
    String[] columnas = {"ID", "Descripción", "Incidentes"};
    
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Hace que la tabla no sea editable
        }
        
    };
    

    jTable1.setModel(modelo);
}
    
    private void cargarProblemasEnTabla() {
         DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
    modelo.setRowCount(0); // Limpia la tabla antes de agregar nuevas filas

    for (Problema problema : gestor.getListaProblemas()) {
        if (problema != null) {
            modelo.addRow(new Object[]{problema.getId(), problema.getDescripcion(), problema.listarIncidentes()});
        }
    }
     
}
    private void configurarTabla2() {
        String[] columnas = {"ID", "Usuario", "Categoria", "Detalle", "Fecha"};
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Hace que la tabla no sea editable
        }
    };
    jTable2.setModel(modelo);
    }
    
   public void cargarIncidentesEnTabla(int idProblema) {
    DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
    modelo.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

    Problema problema = gestor.obtenerProblemaPorId(idProblema);
    
    if (problema != null) {
        Incidente[] incidentes = problema.getIncidentes();
        boolean hayIncidentes = false; 
        
        for (Incidente incidente : incidentes) { // Verifica si hay al menos un incidente
            modelo.addRow(new Object[]{
                incidente.getID(),
                incidente.getUsuario(),
                incidente.getCategoria(),
                incidente.getDetalle(),
                incidente.getFecha()
            });
            hayIncidentes = true;
        }

        if (!hayIncidentes) {
            JOptionPane.showMessageDialog(this, "No hay incidentes registrados para este problema.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "No se encontró el problema seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void eliminarUltimoIncidente() {
     filaSeleccionada = jTable1.getSelectedRow();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un problema.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int idProblema = (int) jTable1.getValueAt(filaSeleccionada, 0);
    
    if (gestor.eliminarIncidente(idProblema)) {
        JOptionPane.showMessageDialog(this, "Incidente eliminado correctamente.");
        cargarIncidentesEnTabla(idProblema);
    } else {
        JOptionPane.showMessageDialog(this, "No hay incidentes para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Problemas Registrados ");

        jLabel2.setText("Incidentes Relacionados al problema");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton1.setText("Eliminar Incidente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Consultar Incidentes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(245, 245, 245))
            .addGroup(layout.createSequentialGroup()
                .addGap(279, 279, 279)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       eliminarUltimoIncidente();
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        filaSeleccionada = jTable1.getSelectedRow();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un problema.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int idProblema = (int) jTable1.getValueAt(filaSeleccionada, 0);
    
   
        
        cargarIncidentesEnTabla( idProblema);
       
    }//GEN-LAST:event_jButton2ActionPerformed

   
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
            java.util.logging.Logger.getLogger(FormEliminarIncidente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEliminarIncidente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEliminarIncidente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEliminarIncidente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
      //  java.awt.EventQueue.invokeLater(new Runnable() {
          //  public void run() {
             //   new FormEliminarIncidente().setVisible(true);
            //}
        //});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
