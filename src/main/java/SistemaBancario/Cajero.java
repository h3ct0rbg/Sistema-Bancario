package SistemaBancario;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Clase que representa a un cajero en el sistema bancario.
 * Cada cajero opera en un hilo separado y realiza transacciones según las solicitudes de la cola.
 * 
 * @author Héctor Benavente García
 * @author Jose Sánchez Nicolás
 */
public class Cajero implements Runnable {

    private int id;
    private JTextField total;
    private JTextField operando;
    private JTextArea movimientos;
    private int dinero;
    private Persona persona;
    private final Lock lock = new ReentrantLock(); // Bloqueo para asegurar la exclusión mutua
    private final Condition condicionParado = lock.newCondition(); // Condición para pausar el cajero
    private boolean parado = false;
    
    /**
     * Constructor de la clase Cajero.
     * 
     * @param id Identificador único del cajero.
     * @param total Campo de texto que muestra el total de dinero en el cajero.
     * @param operando Campo de texto que muestra la operación actual del cajero.
     * @param movimientos Área de texto que registra los movimientos del cajero.
     */
    public Cajero(int id, JTextField total, JTextField operando, JTextArea movimientos) {
        this.id = id;
        this.total = total;
        this.operando = operando;
        this.movimientos = movimientos;
        dinero = 50000;
    }

    /**
     * Obtiene el identificador único del cajero.
     * 
     * @return Identificador del cajero.
     */
    public int getId() {
        return id;
    }
        
    /**
     * Obtiene la persona actualmente atendida por el cajero.
     * 
     * @return Persona actualmente atendida.
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * Obtiene la cantidad de dinero actual en el cajero.
     * 
     * @return Cantidad de dinero en el cajero.
     */
    public int getDinero() {
        return dinero;
    }
    
    /**
     * Verifica si el cajero está en estado pausado.
     * 
     * @return true si el cajero está pausado, false en caso contrario.
     */
    public boolean isParado() {
        return parado;
    }

    /**
     * Establece la cantidad de dinero en el cajero.
     * 
     * @param dinero Nueva cantidad de dinero en el cajero.
     */
    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    
    /**
     * Realiza la operación de insertar dinero en el cajero.
     * 
     * @param cantidad Cantidad de dinero a insertar.
     */
    public synchronized void insertar(int cantidad){
        try{
            operando.setText(persona.getId()+"-I+"+persona.getDinero());
            movimientos.append(persona.getId()+"-I+"+persona.getDinero()+"\n");
            FileWriter fileWriter = new FileWriter("evolucionCajeros.txt", true); // El parámetro true indica que se añadirá al final del archivo
            
            // Obtener la fecha actual
            Date fechaActual = new Date();

            // Formatear la fecha
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String fecha = formatoFecha.format(fechaActual);

            String mensajeLog = "["+fecha+"] - "+persona.getId()+"-I+"+persona.getDinero()+ " en el Cajero "+id +"\n";
            
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(mensajeLog);
            }
            
            Random rand = new Random();
            Thread.sleep(rand.nextInt(2000)+2000);
            dinero += cantidad;
            if (dinero>=100000) {
                Solicitudes.meter(this);
                while(dinero>=100000){
                    this.wait();
                }
            }
            total.setText(String.valueOf(dinero));
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Cajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Realiza la operación de extraer dinero del cajero.
     * 
     * @param cantidad Cantidad de dinero a extraer.
     */
    public synchronized void extraer(int cantidad){
        try{
            operando.setText(persona.getId()+"-E-"+persona.getDinero());
            movimientos.append(persona.getId()+"-E-"+persona.getDinero()+"\n");
            FileWriter fileWriter = new FileWriter("evolucionCajeros.txt", true); // El parámetro true indica que se añadirá al final del archivo
            
            // Obtener la fecha actual
            Date fechaActual = new Date();

            // Formatear la fecha
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String fecha = formatoFecha.format(fechaActual);

            String mensajeLog = "["+fecha+"] - "+persona.getId()+"-E-"+persona.getDinero()+ " en el Cajero "+id +"\n";
            
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(mensajeLog);
            }

            Random rand = new Random();
            Thread.sleep(rand.nextInt(2000)+2500);
            dinero -= cantidad;
            if (dinero<=0) {
                Solicitudes.meter(this);
                while(dinero<=0){
                    this.wait();
                }
            }
            total.setText(String.valueOf(dinero));
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Cajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Pausa la ejecución del cajero.
     * 
     * @throws InterruptedException Si se interrumpe la espera.
     */
    public void parar() throws InterruptedException {
        lock.lock();
        try {
            parado = true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Reanuda la ejecución del cajero.
     */
    public void reanudar() {
        lock.lock();
        try {
            parado = false;
            condicionParado.signal();
        } finally {
            lock.unlock();
        }
    }
        
    /**
     * Método para reanudar la ejecución del hilo.
     */
    public synchronized void avisar(){
        this.notify();
    }
    
    @Override
    public void run() {
        try {
            total.setText(String.valueOf(dinero));
            Thread.sleep(5000);
            while (!Cola.estaVacia()) {
                lock.lock(); // Adquirir el bloqueo antes de usar la condición
                try {
                    if (parado) {
                        condicionParado.await(); // Esperar si está pausado
                    }
                } finally {
                    lock.unlock(); // Liberar el bloqueo
                }

                persona = Cola.sacar();
                if (persona != null) {
                    if (persona.getOperacion()) {
                        insertar(persona.getDinero());
                    } else {
                        extraer(persona.getDinero());
                    }
                }
            }
        } catch (InterruptedException e) {
            // Manejo de la interrupción
        }
    }
}
