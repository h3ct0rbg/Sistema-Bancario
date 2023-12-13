package SistemaBancario;

import javax.swing.JTextField;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase que simula una cola de personas en el sistema bancario.
 * 
 * Permite encolar y desencolar personas, utilizando un array para representar la cola.
 * Además, ofrece sincronización mediante cerrojos y condiciones.
 * 
 * @author Héctor Benavente García
 * @author Jose Sánchez Nicolás
 */
public class Cola {

    private static Persona[] cola; // Utilizamos un array para simular la cola
    private static int tamano;  // Tamaño máximo de la cola
    private static int ocupados; // Número de elementos ocupados en la cola
    private static Lock cerrojo = new ReentrantLock(); // Cerrojo para asegurar la exclusión mutua
    private static Condition meter; // Condición para encolar
    private static Condition sacar; // Condición para desencolar
    private static JTextField tf;
        
    /**
     * Inicializa la cola con un tamaño dado y un campo de texto para visualización.
     * 
     * @param size Tamaño máximo de la cola.
     * @param textField Campo de texto para visualizar el estado de la cola.
     */
    public static void inicializar(int size, JTextField textField) {
        tamano = size;
        cola = new Persona[tamano];
        ocupados = 0;
        tf = textField;
        meter = cerrojo.newCondition(); // Condición para encolar
        sacar = cerrojo.newCondition(); // Condición para desencolar
    }

    /**
     * Encola una persona en la cola.
     * 
     * @param p Persona a encolar.
     */
    public static void meter(Persona p) {
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
            sacar.signal(); // Notificar a quienes están esperando para desencolar
            imprimir();
        } finally {
            cerrojo.unlock();
        }
    }

    /**
     * Desencola una persona de la cola.
     * 
     * @return Persona desencolada.
     */
    public static Persona sacar() {
        try {
            cerrojo.lock();
            while (ocupados == 0) {
                try {
                    sacar.await(); // Esperar si la cola está vacía
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Persona datoRecibido = cola[0];
            // Mover los elementos restantes hacia la izquierda
            System.arraycopy(cola, 1, cola, 0, ocupados - 1);
            ocupados--;
            meter.signal(); // Notificar a quienes están esperando para encolar
            imprimir();
            return datoRecibido;
        } finally {
            cerrojo.unlock();
        }
    }

    /**
     * Imprime el estado actual de la cola en el campo de texto asociado.
     */
    private static void imprimir() {
        StringBuilder contenido = new StringBuilder();

        for (int i = 0; i < ocupados; i++) {
            contenido.append("  [ ").append(cola[i].getId()).append(" ]     ");
        }

        tf.setText(contenido.toString());
    }

    /**
     * Verifica si la cola está vacía.
     * 
     * @return true si la cola está vacía, false en caso contrario.
     */
    public static boolean estaVacia() {
        return ocupados == 0;
    }
}