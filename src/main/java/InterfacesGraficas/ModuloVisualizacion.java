package InterfacesGraficas;

import SistemaBancario.BancoCentral;
import SistemaBancario.Cajero;
import SistemaBancario.Cola;
import SistemaBancario.Operario;
import SistemaBancario.Persona;
import SistemaBancario.Solicitudes;
import SistemaBancarioDistribuido.InterfaceServidor;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa el módulo de visualización de la aplicación bancaria.
 * Se encarga de inicializar los componentes, gestionar la pausa y reanudación
 * de operarios y cajeros, y proporcionar funcionalidad para abrir el archivo
 * de registro del sistema bancario.
 * 
 * @author Héctor Benavente García
 * @author Jose Sánchez Nicolás
 */
public class ModuloVisualizacion extends javax.swing.JFrame {

    private FileWriter fileWriter; // Escritor de archivos
    private PrintWriter pw;        // Escritor de archivos

    private BancoCentral bancoCentral; // Instancia del banco central
    private Cajero[] cajeros;          // Array de cajeros
    private Operario[] operarios;      // Array de operarios

    /**
     * Constructor de la clase ModuloVisualizacion.
     */
    public ModuloVisualizacion() {
        initComponents();
        setLocationRelativeTo(null);

        // Inicialización del log del sistema bancario
        try {
            fileWriter = new FileWriter("evolucionCajeros.txt", true);
            pw = new PrintWriter(fileWriter);
        } catch (IOException e) {}

        // Inicialización de componentes relacionados con el sistema bancario
        Cola.inicializar(10, JTextCola);
        Solicitudes.inicializar(4);
        BancoCentral.inicializar(total5);

        // Inicialización de personas, cajeros y operarios, y comienzo de sus hilos
        inicializarPersonas();
        inicializarCajeros();
        inicializarOperarios();
    }

    /**
     * Inicializa las personas en el sistema bancario.
     */
    private void inicializarPersonas() {
        for (int i = 1; i <= 200; i++) {
            Persona persona = new Persona("Persona" + i);
            Thread hiloPersona = new Thread(persona);
            hiloPersona.start();
        }
    }


    /**
     * Inicializa los cajeros en el sistema bancario.
     */
    private void inicializarCajeros() {
        cajeros = new Cajero[4];      // Array de cajeros

        // Inicialización de cajeros
        cajeros[0] = new Cajero(1, total1, operando1, movimientos1);
        cajeros[1] = new Cajero(2, total2, operando2, movimientos2);
        cajeros[2] = new Cajero(3, total3, operando3, movimientos3);
        cajeros[3] = new Cajero(4, total4, operando4, movimientos4);

        // Inicio de hilos para cajeros
        for (Cajero cajero : cajeros) {
            Thread hiloCajero = new Thread(cajero);
            hiloCajero.start();
        }
    }

    /**
     * Inicializa los operarios en el sistema bancario.
     */
    private void inicializarOperarios() {
        operarios = new Operario[2];  // Array de operarios

        // Inicialización de operarios
        operarios[0] = new Operario(1, operario1, movimientos5);
        operarios[1] = new Operario(2, operario2, movimientos5);

        // Inicio de hilos para operarios
        for (Operario operario : operarios) {
            Thread hiloOperario = new Thread(operario);
            hiloOperario.start();
        }
    }
    
    /**
     * Método RMI para recoger los datos del dinero de los cajeros
     * @return 
     */
    public String[] getTotales(){
        String[] totales = {total1.getText(), total2.getText(), total3.getText(), total4.getText(), total5.getText()};
        return totales;
    }
    
    /**
     * Método RMI para recoger los datos de los operandos de los cajeros
     * @return 
     */
    public String[] getOperandos(){
        String[] operandos = {operando1.getText(), operando2.getText(), operando3.getText(), operando4.getText()};
        return operandos;
    }
    
    public String[] getOperarios(){
        String[] op = {operario1.getText(), operario2.getText()};
        return op;
    }
    
    public String[] getBotones(){
        String[] botones = {pausarOp1.getText(), pausarOp2.getText(), pausar.getText()};
        return botones;
    }
    
    /**
     * Método RMI para pausar el Operario 1
     * @param evt
     */
    public void pausarOp1(java.awt.event.ActionEvent evt){
        pausarOp1ActionPerformed(evt);
    }
    
    /**
     * Método RMI para pausar el Operario 2
     * @param evt
     */
    public void pausarOp2(java.awt.event.ActionEvent evt){
        pausarOp2ActionPerformed(evt);
    }
    
    /**
     * Método RMI para pausar el programa
     * @param evt
     */
    public void pausar(java.awt.event.ActionEvent evt){
        pausarActionPerformed(evt);
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
        JLabeltotal7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        movimientos5 = new javax.swing.JTextArea();
        JLabeltotal8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        movimientos1 = new javax.swing.JTextArea();
        JLabeltotal9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        movimientos2 = new javax.swing.JTextArea();
        JLabeltotal10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        movimientos3 = new javax.swing.JTextArea();
        JLabeltotal11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        movimientos4 = new javax.swing.JTextArea();
        cajero11 = new javax.swing.JLabel();
        JTextCola = new javax.swing.JTextField();
        log = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));

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

        JLabeltotal7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabeltotal7.setText("Movimientos realizados:");

        movimientos5.setEditable(false);
        movimientos5.setBackground(new java.awt.Color(255, 255, 255));
        movimientos5.setColumns(20);
        movimientos5.setForeground(new java.awt.Color(0, 0, 0));
        movimientos5.setRows(5);
        jScrollPane1.setViewportView(movimientos5);

        JLabeltotal8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabeltotal8.setText("Movimientos realizados:");

        jScrollPane2.setHorizontalScrollBar(null);

        movimientos1.setEditable(false);
        movimientos1.setBackground(new java.awt.Color(255, 255, 255));
        movimientos1.setColumns(20);
        movimientos1.setForeground(new java.awt.Color(0, 0, 0));
        movimientos1.setRows(5);
        jScrollPane2.setViewportView(movimientos1);

        JLabeltotal9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabeltotal9.setText("Movimientos realizados:");

        jScrollPane3.setHorizontalScrollBar(null);

        movimientos2.setEditable(false);
        movimientos2.setBackground(new java.awt.Color(255, 255, 255));
        movimientos2.setColumns(20);
        movimientos2.setForeground(new java.awt.Color(0, 0, 0));
        movimientos2.setRows(5);
        jScrollPane3.setViewportView(movimientos2);

        JLabeltotal10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabeltotal10.setText("Movimientos realizados:");

        jScrollPane4.setHorizontalScrollBar(null);

        movimientos3.setEditable(false);
        movimientos3.setBackground(new java.awt.Color(255, 255, 255));
        movimientos3.setColumns(20);
        movimientos3.setForeground(new java.awt.Color(0, 0, 0));
        movimientos3.setRows(5);
        jScrollPane4.setViewportView(movimientos3);

        JLabeltotal11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabeltotal11.setText("Movimientos realizados:");

        jScrollPane5.setHorizontalScrollBar(null);

        movimientos4.setEditable(false);
        movimientos4.setBackground(new java.awt.Color(255, 255, 255));
        movimientos4.setColumns(20);
        movimientos4.setForeground(new java.awt.Color(0, 0, 0));
        movimientos4.setRows(5);
        jScrollPane5.setViewportView(movimientos4);

        cajero11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cajero11.setText("Esperando para un cajero:");

        JTextCola.setEditable(false);
        JTextCola.setBackground(new java.awt.Color(255, 255, 255));
        JTextCola.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JTextCola.setForeground(new java.awt.Color(0, 0, 0));

        log.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        log.setText("Abrir fichero Log");
        log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JTextCola, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(cajero11))
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
                            .addGap(57, 57, 57)
                            .addComponent(jLabeloperando1)
                            .addGap(25, 25, 25)
                            .addComponent(operando1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addGap(57, 57, 57)
                            .addComponent(JLabeltotal8)
                            .addGap(162, 162, 162)
                            .addComponent(JLabeltotal9)
                            .addGap(158, 158, 158)
                            .addComponent(JLabeltotal10)
                            .addGap(158, 158, 158)
                            .addComponent(JLabeltotal11))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(69, 69, 69)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(cajero7)
                            .addGap(212, 212, 212)
                            .addComponent(cajero9)
                            .addGap(99, 99, 99)
                            .addComponent(log, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(JLabeltotal6)
                            .addGap(49, 49, 49)
                            .addComponent(total5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)
                            .addComponent(operario1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(23, 23, 23)
                            .addComponent(pausarOp1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(JLabeltotal7)
                            .addGap(158, 158, 158)
                            .addComponent(cajero10))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(65, 65, 65)
                                    .addComponent(operario2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(23, 23, 23)
                                    .addComponent(pausarOp2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(254, 254, 254)
                                    .addComponent(pausar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(cajero11)
                .addGap(18, 18, 18)
                .addComponent(JTextCola, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLabeltotal8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLabeltotal9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLabeltotal10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLabeltotal11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cajero7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cajero9)
                        .addComponent(log)))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(JLabeltotal6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(total5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operario1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pausarOp1))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLabeltotal7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajero10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(operario2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pausarOp2))
                        .addGap(41, 41, 41)
                        .addComponent(pausar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Maneja el evento de pausar o reanudar el Operario 1.
     * @param evt Evento de acción.
     */
    private void pausarOp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pausarOp1ActionPerformed
        if(operarios[0].isParado()){
            pausarOp1.setText("Pausar Operario");
            operarios[0].reanudar();
            Solicitudes.avisar();
        }else{
            try {
                pausarOp1.setText("Reanudar Operario");
                operarios[0].parar();
            } catch (InterruptedException ex) {
                Logger.getLogger(ModuloVisualizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pausarOp1ActionPerformed

    /**
     * Maneja el evento de pausar o reanudar el Operario 2.
     * @param evt Evento de acción.
     */
    private void pausarOp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pausarOp2ActionPerformed
        if(operarios[1].isParado()){
            pausarOp2.setText("Pausar Operario");
            operarios[1].reanudar();
            Solicitudes.avisar();
        }else{
            try {
                pausarOp2.setText("Reanudar Operario");
                operarios[1].parar();
            } catch (InterruptedException ex) {
                Logger.getLogger(ModuloVisualizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pausarOp2ActionPerformed

    /**
     * Maneja el evento de pausar o reanudar todos los cajeros y operarios.
     * @param evt Evento de acción.
     */
    private void pausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pausarActionPerformed
        String texto = null;
        
        if("Pausar".equals(pausar.getText())){
            pausar.setText("Reanudar");
            
            for(Operario operario : operarios){
                try {
                    texto = "Reanudar Operario";
                    operario.parar();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ModuloVisualizacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            for(Cajero cajero : cajeros){
                try {
                    cajeros[cajero.getId()-1].parar();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ModuloVisualizacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            pausar.setText("Pausar");
            
            for(Operario operario : operarios){
                texto = "Pausar Operario";
                operarios[operario.getId()-1].reanudar();
                Solicitudes.avisar();
            }
            
            for(Cajero cajero : cajeros){
                cajeros[cajero.getId()-1].reanudar();
            }
        }
        
        pausarOp1.setText(texto);
        pausarOp2.setText(texto);
    }//GEN-LAST:event_pausarActionPerformed

    /**
     * Maneja el evento de abrir el archivo de registro del sistema bancario.
     * @param evt Evento de acción.
     */
    private void logActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logActionPerformed
        String nombreArchivo = "evolucionCajeros.txt";

        try {
            File archivo = new File(nombreArchivo);

            if (archivo.exists()) {
                Desktop.getDesktop().open(archivo);
            } else {
                System.out.println("El archivo no existe.");
            }
        } catch (IOException ex) {
            System.out.println("Error al abrir el archivo: " + ex.getMessage());
        }
    }//GEN-LAST:event_logActionPerformed

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     * @throws java.net.MalformedURLException
     */
    public static void main(String args[]) throws RemoteException, MalformedURLException {
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
            java.util.logging.Logger.getLogger(ModuloVisualizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloVisualizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloVisualizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloVisualizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        ModuloVisualizacion modulo = new ModuloVisualizacion();
        modulo.setVisible(true);
        modulo.setTitle("Servidor");
        
        InterfaceServidor interfaz = new InterfaceServidor(modulo);
        Registry registry = LocateRegistry.createRegistry(1099); //rmiregistry en puerto 1099
        Naming.rebind("//127.0.0.1/AccesoServidor", interfaz);   //funciona en equipo local
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabeltotal1;
    private javax.swing.JLabel JLabeltotal10;
    private javax.swing.JLabel JLabeltotal11;
    private javax.swing.JLabel JLabeltotal2;
    private javax.swing.JLabel JLabeltotal3;
    private javax.swing.JLabel JLabeltotal4;
    private javax.swing.JLabel JLabeltotal5;
    private javax.swing.JLabel JLabeltotal6;
    private javax.swing.JLabel JLabeltotal7;
    private javax.swing.JLabel JLabeltotal8;
    private javax.swing.JLabel JLabeltotal9;
    private javax.swing.JTextField JTextCola;
    private javax.swing.JLabel cajero1;
    private javax.swing.JLabel cajero10;
    private javax.swing.JLabel cajero11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton log;
    private javax.swing.JTextArea movimientos1;
    private javax.swing.JTextArea movimientos2;
    private javax.swing.JTextArea movimientos3;
    private javax.swing.JTextArea movimientos4;
    private javax.swing.JTextArea movimientos5;
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
