package SistemaBancario;

//@author Héctor Benavente García

import javax.swing.JTextField;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cola {

    private static Persona[] cola; // Utilizamos un array para simular la cola
    private static int tamano;  // Tamaño máximo de la cola
    private static int ocupados; // Número de elementos ocupados en la cola
    private static Lock cerrojo = new ReentrantLock();
    private static Condition meter = cerrojo.newCondition();
    private static Condition sacar = cerrojo.newCondition();
    private static JTextField tf;

    public static void inicializar(int size, JTextField textField) {
        tamano = size;
        cola = new Persona[tamano];
        tf = textField;
    }

    public static void meter(Persona p) {
        try {
            cerrojo.lock();
            while (ocupados == tamano) {
                try {
                    meter.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            cola[ocupados] = p;
            ocupados++;
            sacar.signal();
            imprimir();
        } finally {
            cerrojo.unlock();
        }
    }

    public static Persona sacar() {
        try {
            cerrojo.lock();
            while (ocupados == 0) {
                try {
                    sacar.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Persona datoRecibido = cola[0];
            // Mover los elementos restantes hacia la izquierda
            System.arraycopy(cola, 1, cola, 0, ocupados - 1);
            ocupados--;
            meter.signal();
            imprimir();
            return datoRecibido;
        } finally {
            cerrojo.unlock();
        }
    }

    private static void imprimir() {
        StringBuilder contenido = new StringBuilder();

        for (int i = 0; i < ocupados; i++) {
            contenido.append("  [ ").append(cola[i].getId()).append(" ]     ");
        }

        tf.setText(contenido.toString());
    }

    public static boolean estaVacia() {
        return ocupados == 0;
    }
}