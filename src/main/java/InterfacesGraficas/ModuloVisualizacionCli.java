package InterfacesGraficas;

import SistemaBancarioDistribuido.InterfaceDistribuida;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa el módulo de visualización del cliente de la aplicación 
 * bancaria.
 * 
 * @author Héctor Benavente García
 * @author Jose Sánchez Nicolás
 */
public class ModuloVisualizacionCli extends javax.swing.JFrame {
    
    private InterfaceDistribuida sistemaBancario;

    /**
     * Creates new form CorreosSLCliente
     */
    public ModuloVisualizacionCli() {
        initComponents();
        try {
            sistemaBancario = (InterfaceDistribuida) Naming.lookup("//127.0.0.1/AccesoServidor");
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.out.println("Excepción : " + e.getMessage());
        }
    }

    public void Mostrar() throws InterruptedException, RemoteException {
        total1.setText(sistemaBancario.getTotales()[0]);
        total2.setText(sistemaBancario.getTotales()[1]);
        total3.setText(sistemaBancario.getTotales()[2]);
        total4.setText(sistemaBancario.getTotales()[3]);
        total5.setText(sistemaBancario.getTotales()[4]);
        
        operando1.setText(sistemaBancario.getOperandos()[0]);
        operando2.setText(sistemaBancario.getOperandos()[1]);
        operando3.setText(sistemaBancario.getOperandos()[2]);
        operando4.setText(sistemaBancario.getOperandos()[3]);
        
        operario1.setText(sistemaBancario.getOperarios()[0]);
        operario2.setText(sistemaBancario.getOperarios()[1]);
        
        pausarOp1.setText(sistemaBancario.getBotones()[0]);
        pausarOp2.setText(sistemaBancario.getBotones()[1]);
        pausar.setText(sistemaBancario.getBotones()[2]);
        
        sleep(500);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        total1 = new javax.swing.JTextField();
        cajero1 = new javax.swing.JLabel();
        JLabeltotal1 = new javax.swing.JLabel();
        jLabeloperando1 = new javax.swing.JLabel();
        operando1 = new javax.swing.JTextField();
        total2 = new javax.swing.JTextField();
        cajero2 = new javax.swing.JLabel();
        JLabeltotal2 = new javax.swing.JLabel();
        jLabeloperando2 = new javax.swing.JLabel();
        operando2 = new javax.swing.JTextField();
        operando3 = new javax.swing.JTextField();
        total3 = new javax.swing.JTextField();
        cajero3 = new javax.swing.JLabel();
        JLabeltotal3 = new javax.swing.JLabel();
        jLabeloperando3 = new javax.swing.JLabel();
        operando4 = new javax.swing.JTextField();
        total4 = new javax.swing.JTextField();
        cajero4 = new javax.swing.JLabel();
        JLabeltotal4 = new javax.swing.JLabel();
        jLabeloperando4 = new javax.swing.JLabel();
        JLabeltotal5 = new javax.swing.JLabel();
        cajero8 = new javax.swing.JLabel();
        cajero7 = new javax.swing.JLabel();
        JLabeltotal6 = new javax.swing.JLabel();
        total5 = new javax.swing.JTextField();
        cajero9 = new javax.swing.JLabel();
        operario1 = new javax.swing.JTextField();
        cajero10 = new javax.swing.JLabel();
        operario2 = new javax.swing.JTextField();
        pausarOp1 = new javax.swing.JButton();
        pausarOp2 = new javax.swing.JButton();
        pausar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1280, 500));
        setMinimumSize(new java.awt.Dimension(1280, 500));
        setSize(new java.awt.Dimension(1280, 500));

        total1.setEditable(false);
        total1.setBackground(new java.awt.Color(255, 255, 255));
        total1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        total1.setForeground(new java.awt.Color(0, 0, 0));

        cajero1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cajero1.setText("Cajero 1");

        JLabeltotal1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabeltotal1.setText("Total:");

        jLabeloperando1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabeloperando1.setText("Operando:");

        operando1.setEditable(false);
        operando1.setBackground(new java.awt.Color(255, 255, 255));
        operando1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        operando1.setForeground(new java.awt.Color(0, 0, 0));

        total2.setEditable(false);
        total2.setBackground(new java.awt.Color(255, 255, 255));
        total2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        total2.setForeground(new java.awt.Color(0, 0, 0));

        cajero2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cajero2.setText("Cajero 2");

        JLabeltotal2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabeltotal2.setText("Total:");

        jLabeloperando2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabeloperando2.setText("Operando:");

        operando2.setEditable(false);
        operando2.setBackground(new java.awt.Color(255, 255, 255));
        operando2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        operando2.setForeground(new java.awt.Color(0, 0, 0));

        operando3.setEditable(false);
        operando3.setBackground(new java.awt.Color(255, 255, 255));
        operando3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        operando3.setForeground(new java.awt.Color(0, 0, 0));

        total3.setEditable(false);
        total3.setBackground(new java.awt.Color(255, 255, 255));
        total3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        total3.setForeground(new java.awt.Color(0, 0, 0));

        cajero3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cajero3.setText("Cajero 3");

        JLabeltotal3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabeltotal3.setText("Total:");

        jLabeloperando3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabeloperando3.setText("Operando:");

        operando4.setEditable(false);
        operando4.setBackground(new java.awt.Color(255, 255, 255));
        operando4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        operando4.setForeground(new java.awt.Color(0, 0, 0));

        total4.setEditable(false);
        total4.setBackground(new java.awt.Color(255, 255, 255));
        total4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        total4.setForeground(new java.awt.Color(0, 0, 0));

        cajero4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cajero4.setText("Cajero 4");

        JLabeltotal4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabeltotal4.setText("Total:");

        jLabeloperando4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabeloperando4.setText("Operando:");

        JLabeltotal5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabeltotal5.setText("Total:");

        cajero8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cajero7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cajero7.setText("Banco Central");

        JLabeltotal6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabeltotal6.setText("Total:");

        total5.setEditable(false);
        total5.setBackground(new java.awt.Color(255, 255, 255));
        total5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        total5.setForeground(new java.awt.Color(0, 0, 0));

        cajero9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cajero9.setText("Operario 1");

        operario1.setEditable(false);
        operario1.setBackground(new java.awt.Color(255, 255, 255));
        operario1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        operario1.setForeground(new java.awt.Color(0, 0, 0));

        cajero10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cajero10.setText("Operario 2");

        operario2.setEditable(false);
        operario2.setBackground(new java.awt.Color(255, 255, 255));
        operario2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        operario2.setForeground(new java.awt.Color(0, 0, 0));

        pausarOp1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pausarOp1.setText("Pausar Operario");
        pausarOp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pausarOp1ActionPerformed(evt);
            }
        });

        pausarOp2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pausarOp2.setText("Pausar Operario");
        pausarOp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pausarOp2ActionPerformed(evt);
            }
        });

        pausar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pausar.setText("Pausar");
        pausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pausarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(cajero8)
                        .addGap(33, 33, 33)
                        .addComponent(cajero1)
                        .addGap(253, 253, 253)
                        .addComponent(cajero2)
                        .addGap(249, 249, 249)
                        .addComponent(cajero3)
                        .addGap(249, 249, 249)
                        .addComponent(cajero4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLabeltotal1)
                            .addComponent(JLabeltotal5))
                        .addGap(57, 57, 57)
                        .addComponent(total1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(JLabeltotal2)
                        .addGap(57, 57, 57)
                        .addComponent(total2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(JLabeltotal3)
                        .addGap(57, 57, 57)
                        .addComponent(total3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(JLabeltotal4)
                        .addGap(57, 57, 57)
                        .addComponent(total4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(operario2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(pausarOp2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(551, 551, 551)
                        .addComponent(pausar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(total5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabeloperando1)
                                .addGap(25, 25, 25)
                                .addComponent(operando1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabeloperando2)
                                .addGap(25, 25, 25)
                                .addComponent(operando2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jLabeloperando3)
                                .addGap(25, 25, 25)
                                .addComponent(operando3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jLabeloperando4)
                                .addGap(25, 25, 25)
                                .addComponent(operando4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(cajero10))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cajero7)
                                .addGap(212, 212, 212)
                                .addComponent(operario1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(pausarOp1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(JLabeltotal6)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(305, 305, 305)
                                .addComponent(cajero9)))))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cajero8)
                    .addComponent(cajero1)
                    .addComponent(cajero2)
                    .addComponent(cajero3)
                    .addComponent(cajero4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLabeltotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLabeltotal3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLabeltotal4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLabeltotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLabeltotal5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabeloperando2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operando2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabeloperando3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operando3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabeloperando4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operando4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabeloperando1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(operando1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50)
                .addComponent(cajero9)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(operario1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cajero7))
                    .addComponent(pausarOp1))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajero10)
                    .addComponent(total5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLabeltotal6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(operario2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pausarOp2))
                .addGap(41, 41, 41)
                .addComponent(pausar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Maneja el evento de pausar o reanudar el Operario 1.
     * @param evt Evento de acción.
     */
    private void pausarOp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pausarOp1ActionPerformed
        try {
            sistemaBancario.pausarOp1(evt);
        } catch (RemoteException ex) {
            Logger.getLogger(ModuloVisualizacionCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pausarOp1ActionPerformed

    /**
     * Maneja el evento de pausar o reanudar el Operario 2.
     * @param evt Evento de acción.
     */
    private void pausarOp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pausarOp2ActionPerformed
        try {
            sistemaBancario.pausarOp2(evt);
        } catch (RemoteException ex) {
            Logger.getLogger(ModuloVisualizacionCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pausarOp2ActionPerformed

    /**
     * Maneja el evento de pausar o reanudar todos los cajeros y operarios.
     * @param evt Evento de acción.
     */
    private void pausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pausarActionPerformed
        try {
            sistemaBancario.pausar(evt);
        } catch (RemoteException ex) {
            Logger.getLogger(ModuloVisualizacionCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pausarActionPerformed

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     * @throws java.rmi.RemoteException
     */
    public static void main(String args[]) throws InterruptedException, RemoteException {
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
            java.util.logging.Logger.getLogger(ModuloVisualizacionCli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloVisualizacionCli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloVisualizacionCli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloVisualizacionCli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        ModuloVisualizacionCli moduloCli = new ModuloVisualizacionCli();
        moduloCli.setVisible(true);
        moduloCli.setTitle("Cliente");
        
        while(true){
            moduloCli.Mostrar();
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabeltotal1;
    private javax.swing.JLabel JLabeltotal2;
    private javax.swing.JLabel JLabeltotal3;
    private javax.swing.JLabel JLabeltotal4;
    private javax.swing.JLabel JLabeltotal5;
    private javax.swing.JLabel JLabeltotal6;
    private javax.swing.JLabel cajero1;
    private javax.swing.JLabel cajero10;
    private javax.swing.JLabel cajero2;
    private javax.swing.JLabel cajero3;
    private javax.swing.JLabel cajero4;
    private javax.swing.JLabel cajero7;
    private javax.swing.JLabel cajero8;
    private javax.swing.JLabel cajero9;
    private javax.swing.JLabel jLabeloperando1;
    private javax.swing.JLabel jLabeloperando2;
    private javax.swing.JLabel jLabeloperando3;
    private javax.swing.JLabel jLabeloperando4;
    private javax.swing.JTextField operando1;
    private javax.swing.JTextField operando2;
    private javax.swing.JTextField operando3;
    private javax.swing.JTextField operando4;
    private javax.swing.JTextField operario1;
    private javax.swing.JTextField operario2;
    private javax.swing.JButton pausar;
    private javax.swing.JButton pausarOp1;
    private javax.swing.JButton pausarOp2;
    private javax.swing.JTextField total1;
    private javax.swing.JTextField total2;
    private javax.swing.JTextField total3;
    private javax.swing.JTextField total4;
    private javax.swing.JTextField total5;
    // End of variables declaration//GEN-END:variables
}
