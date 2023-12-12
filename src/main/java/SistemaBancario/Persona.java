package SistemaBancario;

import java.util.Random;

/**
 * Clase que representa a una persona en el sistema bancario.
 * 
 * Cada persona tiene un identificador, realiza una operación (depósito o retiro)
 * y tiene una cantidad de dinero asociada.
 * 
 * @author Héctor Benavente García
 * @author Jose Sánchez Nicolás
 */
public class Persona implements Runnable {

    private String id;        // Identificador de la persona
    private boolean operacion; // Tipo de operación: true para depósito, false para retiro
    private int dinero;        // Cantidad de dinero asociada a la persona

    /**
     * Constructor de la clase Persona.
     * 
     * @param id Identificador único de la persona.
     */
    public Persona(String id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador de la persona.
     * 
     * @return Identificador de la persona.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene la cantidad de dinero asociada a la persona.
     * 
     * @return Cantidad de dinero asociada a la persona.
     */
    public int getDinero() {
        return dinero;
    }

    /**
     * Obtiene el tipo de operación que realizará la persona.
     * 
     * @return true si la operación es depósito, false si es retiro.
     */
    public boolean getOperacion() {
        return operacion;
    }

    /**
     * Implementación del método run de la interfaz Runnable.
     * 
     * La persona espera un tiempo aleatorio antes de realizar una operación
     * (depósito o retiro) con una cantidad aleatoria de dinero.
     */
    @Override
    public void run() {
        try {
            Random rand = new Random();
            Thread.sleep(rand.nextInt(58000) + 2000); // Tiempo de llegada escalonada
            operacion = rand.nextBoolean();
            dinero = (rand.nextInt(6) + 5) * 1000;

            Cola.meter(this); // Agregar la persona a la cola

        } catch (InterruptedException ex) {
            // Manejo de la interrupción
        }
    }
}