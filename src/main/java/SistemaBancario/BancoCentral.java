package SistemaBancario;

import javax.swing.JTextField;

/**
 * Clase que representa el Banco Central en el sistema bancario.
 * 
 * @author Héctor Benavente García
 * @author Jose Sánchez Nicolás
 */
public class BancoCentral {

    private static int dinero; // Dinero actual del Banco Central
    private static JTextField total; // Campo de texto en la interfaz gráfica para mostrar el dinero

    /**
     * Inicializa el Banco Central con un dinero inicial y asigna el campo de texto
     * para mostrar el saldo.
     * 
     * @param total Campo de texto en la interfaz gráfica para mostrar el saldo.
     */
    public static void inicializar(JTextField total) {
        dinero = 250000; // Saldo inicial del Banco Central
        BancoCentral.total = total; // Asigna el campo de texto
        total.setText(String.valueOf(dinero)); // Muestra el saldo inicial en la interfaz gráfica
    }

    /**
     * Método sincronizado para insertar dinero en el Banco Central.
     * 
     * @param cantidad Cantidad de dinero a insertar.
     */
    public static synchronized void insertar(int cantidad) {
        dinero += cantidad; // Aumenta el dinero del Banco Central
        total.setText(String.valueOf(dinero)); // Actualiza el campo de texto en la interfaz gráfica
    }

    /**
     * Método sincronizado para extraer dinero del Banco Central.
     * 
     * @param cantidad Cantidad de dinero a extraer.
     * @return La cantidad real de dinero extraído, considerando el saldo disponible.
     */
    public static synchronized int extraer(int cantidad) {
        if (dinero < cantidad) {
            cantidad = 0; // Si el dinero no es suficiente, no se extrae dinero.
        } else {
            dinero -= cantidad; // Reduce el dinero del Banco Central
        }
        total.setText(String.valueOf(dinero)); // Actualiza el campo de texto en la interfaz gráfica
        return cantidad; // Retorna la cantidad de dinero extraído
    }
}