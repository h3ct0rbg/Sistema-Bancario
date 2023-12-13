package SistemaBancario;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase que gestiona las solicitudes de cajeros en el sistema bancario.
 * Utiliza una cola de cajeros para manejar las solicitudes de operarios.
 * 
 * @author Héctor Benavente García
 * @author Jose Sánchez Nicolás
 */
public class Solicitudes {

    private static Cajero[] cola; // Utilizamos un array para simular la cola
    private static int tamano;  // Tamaño máximo de la cola
    private static int ocupados; // Número de elementos ocupados en la cola
    private static Lock cerrojo = new ReentrantLock(); // Bloqueo para asegurar la exclusión mutua
    private static Condition meter; // Condición para meter elementos
    private static Condition sacar; // Condición para sacar elementos

    /**
     * Inicializa la cola de solicitudes de cajeros.
     * 
     * @param size Tamaño máximo de la cola.
     */
    public static void inicializar(int size) {
        tamano = size;
        cola = new Cajero[tamano];
        ocupados = 0;
        meter = cerrojo.newCondition();
        sacar = cerrojo.newCondition();
    }

    /**
     * Agrega un cajero a la cola de solicitudes.
     * 
     * @param p Cajero a agregar a la cola.
     */
    public static void meter(Cajero p) {
        try {
            cerrojo.lock();
            while (ocupados == tamano) {
                try {
                    meter.await(); // Esperar si la cola está llena
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            cola[ocupados] = p;
            ocupados++;
            sacar.signal(); // Despertar al hilo que está esperando para sacar
        } finally {
            cerrojo.unlock();
        }
    }

    /**
     * Saca un cajero de la cola de solicitudes.
     * 
     * @param operario Operario que solicita el cajero.
     * @return Cajero retirado de la cola.
     */
    public static Cajero sacar(Operario operario) {
        try {
            cerrojo.lock();
            while (ocupados == 0 || operario.isParado()) {
                try {
                    sacar.await(); // Esperar si la cola está vacía o el operario está parado
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Cajero datoRecibido = cola[0];
            // Mover los elementos restantes hacia la izquierda
            System.arraycopy(cola, 1, cola, 0, ocupados - 1);
            ocupados--;
            meter.signal(); // Despertar al hilo que está esperando para meter
            return datoRecibido;
        } finally {
            cerrojo.unlock();
        }
    }
    
    /**
     * Avisa a los hilos que están esperando para sacar de la cola.
     */
    public static void avisar() {
        try {
            cerrojo.lock();
            sacar.signal();
        } finally {
            cerrojo.unlock();
        }
    }
    
    /**
     * Verifica si la cola de solicitudes está vacía.
     * 
     * @return true si la cola está vacía, false en caso contrario.
     */
    public static boolean estaVacia() {
        return ocupados == 0;
    }
}