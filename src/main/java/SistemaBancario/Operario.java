package SistemaBancario;

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

/**
 * Clase que representa a un operario en el sistema bancario.
 * Realiza operaciones de retiro y depósito en los cajeros automáticos.
 * 
 * @author Héctor Benavente García
 * @author Jose Sánchez Nicolás
 */
public class Operario implements Runnable {

    private int id; // Identificador único del operario
    private JTextField op; // Campo de texto para mostrar las operaciones del operario
    private JTextArea movimientos; // Área de texto para mostrar los movimientos realizados
    private Cajero cajero; // Cajero automático con el que el operario interactúa
    private final Lock lock = new ReentrantLock(); // Bloqueo para asegurar la exclusión mutua
    private final Condition condicionParado = lock.newCondition(); // Condición de parado
    private boolean parado = false; // Indica si el operario está parado o no

    /**
     * Constructor de la clase Operario.
     * 
     * @param id Identificador único del operario.
     * @param op Campo de texto para mostrar las operaciones del operario.
     * @param movimientos Área de texto para mostrar los movimientos realizados.
     */
    public Operario(int id, JTextField op, JTextArea movimientos) {
        this.id = id;
        this.op = op;
        this.movimientos = movimientos;
        parado = false;
    }

    /**
     * Obtiene el identificador del operario.
     * 
     * @return Identificador único del operario.
     */
    public int getId() {
        return id;
    }

    /**
     * Verifica si el operario está parado.
     * 
     * @return true si el operario está parado, false en caso contrario.
     */
    public boolean isParado() {
        return parado;
    }

    /**
     * Realiza la operación de retiro de dinero del cajero automático.
     * 
     * @param cantidad Cantidad de dinero a retirar.
     * @throws InterruptedException Si la operación es interrumpida.
     * @throws IOException Si hay un error de entrada/salida al escribir en el archivo de log.
     */
    public synchronized void retirar(int cantidad) throws InterruptedException, IOException {
        FileWriter fileWriter = new FileWriter("evolucionCajeros.txt", true); // El parámetro true indica que se añadirá al final del archivo

        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Formatear la fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha = formatoFecha.format(fechaActual);

        String mensajeLog = "[" + fecha + "] - " + "Operario" + id + "-C" + cajero.getId() + "+" + cantidad + "\n";

        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(mensajeLog);
        }

        Thread.sleep(2000);
        int dinero = cajero.getDinero();
        cajero.setDinero(dinero - cantidad);
        cajero.avisar();
        BancoCentral.insertar(cantidad);
        movimientos.append("Operario" + id + "-C" + cajero.getId() + "+50.000" + "\n");
    }

    /**
     * Realiza la operación de depósito de dinero en el cajero automático.
     * 
     * @param cantidad Cantidad de dinero a depositar.
     * @throws InterruptedException Si la operación es interrumpida.
     * @throws IOException Si hay un error de entrada/salida al escribir en el archivo de log.
     */
    public synchronized void depositar(int cantidad) throws InterruptedException, IOException {
        FileWriter fileWriter = new FileWriter("evolucionCajeros.txt", true); // El parámetro true indica que se añadirá al final del archivo

        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Formatear la fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha = formatoFecha.format(fechaActual);

        String mensajeLog = "[" + fecha + "] - " + "Operario" + id + "-C" + cajero.getId() + "-" + cantidad + "\n";

        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(mensajeLog);
        }

        Thread.sleep(3000);
        int cant = BancoCentral.extraer(cantidad);
        int dinero = cajero.getDinero();
        cajero.setDinero(dinero + cant);
        movimientos.append("Operario" + id + "-C" + cajero.getId() + "-50.000" + "\n");
        cajero.avisar();
    }

    /**
     * Pausa la ejecución del operario.
     * 
     * @throws InterruptedException Si la operación es interrumpida.
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
     * Reanuda la ejecución del operario y avisa a las solicitudes para procesar cajeros.
     */
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

    /**
     * Implementación del hilo de ejecución del operario.
     * Realiza operaciones continuas mientras el operario está activo.
     */
    @Override
    public void run() {
        while (true) {
            try {
                lock.lock(); // Adquirir el bloqueo antes de usar la condición
                while (parado) {
                    condicionParado.await();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Operario.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                lock.unlock(); // Liberar el bloqueo
            }
                cajero = Solicitudes.sacar(this);
                if (cajero.getPersona().getOperacion()) {
                try {
                    op.setText("Llevando 50.000 del Cajero" + cajero.getId() + " al Banco Central");
                    retirar(50000);
                    op.setText("");
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(Operario.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else {
                try {
                    op.setText("Trayendo 50.000 del Banco Central al Cajero" + cajero.getId());
                    depositar(50000);
                    op.setText("");
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(Operario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}