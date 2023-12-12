package SistemaBancario;

//@author Héctor Benavente García
//@author Jose Sánchez Nicolás

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Operario implements Runnable{
    
    private int id;
    private JTextField op;
    private JTextArea movimientos;
    private Cajero cajero;
    private final Lock lock = new ReentrantLock();
    private final Condition condicionParado = lock.newCondition();
    private boolean parado = false;
    
    public Operario(int id, JTextField op, JTextArea movimientos) {
        this.id = id;
        this.op = op;
        this.movimientos = movimientos;
        parado = false;
    }

    public int getId() {
        return id;
    }
    
    public boolean isParado() {
        return parado;
    }
    
    public synchronized void retirar(int cantidad) throws InterruptedException, IOException{
        FileWriter fileWriter = new FileWriter("evolucionCajeros.txt", true); // El parámetro true indica que se añadirá al final del archivo
            
        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Formatear la fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha = formatoFecha.format(fechaActual);

        String mensajeLog = "["+fecha+"] - "+"Operario"+id+"-C"+cajero.getId()+"+"+cantidad+"\n";

        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(mensajeLog);
        }
        
        Thread.sleep(2000);
        int dinero = cajero.getDinero();
        cajero.setDinero(dinero-cantidad);
        cajero.start();
        BancoCentral.insertar(cantidad);
        movimientos.append("Operario"+id+"-C"+cajero.getId()+"+50.000"+"\n");
    }
    
    public synchronized void depositar(int cantidad) throws InterruptedException, IOException{
        FileWriter fileWriter = new FileWriter("evolucionCajeros.txt", true); // El parámetro true indica que se añadirá al final del archivo
            
        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Formatear la fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha = formatoFecha.format(fechaActual);

        String mensajeLog = "["+fecha+"] - "+"Operario"+id+"-C"+cajero.getId()+"-"+cantidad+"\n";

        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(mensajeLog);
        }
        
        Thread.sleep(3000);
         int cant = BancoCentral.extraer(cantidad);
         int dinero = cajero.getDinero();
         cajero.setDinero(dinero+cant);
         movimientos.append("Operario"+id+"-C"+cajero.getId()+"-50.000"+"\n");
         cajero.start();
    }
    
    public void parar() throws InterruptedException {
        lock.lock();
        try {
            parado = true;
        } finally {
            lock.unlock();
        }
    }

    public void reanudar() {
        lock.lock();
        try {
            parado = false;
            condicionParado.signal();
            Solicitudes.avisar();
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public void run() {
        while(true){
            try{
            while(parado){
                condicionParado.await();
            }
            cajero = Solicitudes.sacar(this);
            if(cajero.getPersona().getOperacion()){
                    op.setText("Llevando 50.000 del Cajero"+cajero.getId()+" al Banco Central");
                    retirar(50000);
                    op.setText("");
            }else{
                    op.setText("Trayendo 50.000 del Banco Central al Cajero"+cajero.getId());
                    depositar(50000);
                    op.setText("");
            }
            }catch (InterruptedException | IOException ex) {
                Logger.getLogger(Operario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}