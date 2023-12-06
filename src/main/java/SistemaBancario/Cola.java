package SistemaBancario;

//@author Héctor Benavente García

import javax.swing.JTextField;
import java.util.LinkedList;

public class Cola {

    private static LinkedList<Persona> cola;
    private static JTextField tf;

    private Cola() {
        // Constructor privado para evitar instancias, ya que la clase es estática
    }

    public static void inicializar(JTextField textField) {
        cola = new LinkedList<>();
        tf = textField;
    }

    public static synchronized void meter(Persona persona) {
        cola.offer(persona);
        imprimir();
    }

    public static synchronized Persona sacar() {
        if (!cola.isEmpty()) {
            Persona persona = cola.poll();
            imprimir();
            return persona;
        }
        return null; // Devolver null si la cola está vacía
    }

    private static void imprimir() {
        StringBuilder contenido = new StringBuilder();

        for (Persona persona : cola) {
            contenido.append(persona.getId()).append(" - ");
        }

        tf.setText(contenido.toString());
    }

    public static boolean estaVacia() {
        return cola.isEmpty();
    }
}