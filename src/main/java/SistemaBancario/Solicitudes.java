package SistemaBancario;

//@author Héctor Benavente García
//@author Jose Sánchez Nicolás

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solicitudes {

    private static Cajero[] cola; // Utilizamos un array para simular la cola
    private static int tamano;  // Tamaño máximo de la cola
    private static int ocupados; // Número de elementos ocupados en la cola
    private static Lock cerrojo = new ReentrantLock();
    private static Condition meter = cerrojo.newCondition();
    private static Condition sacar = cerrojo.newCondition();

    public static void inicializar(int size) {
        tamano = size;
        cola = new Cajero[tamano];
        ocupados = 0;
    }

    public static void meter(Cajero p) {
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
        } finally {
            cerrojo.unlock();
        }
    }

    public static Cajero sacar(Operario operario) {
        try {
            cerrojo.lock();
            while (ocupados == 0 || operario.isParado()) {
                try {
                    sacar.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Cajero datoRecibido = cola[0];
            // Mover los elementos restantes hacia la izquierda
            System.arraycopy(cola, 1, cola, 0, ocupados - 1);
            ocupados--;
            meter.signal();
            return datoRecibido;
        } finally {
            cerrojo.unlock();
        }
    }
    
    public static void avisar() {
        try {
            cerrojo.lock();
            sacar.signal();
        } finally {
            cerrojo.unlock();
        }
    }
    
    public static boolean estaVacia() {
        return ocupados == 0;
    }
}