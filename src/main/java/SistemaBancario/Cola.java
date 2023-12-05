package SistemaBancario;

//@author Héctor Benavente García

import javax.swing.JTextField;
import java.util.LinkedList;

public class Cola {

    private LinkedList<Persona> cola;
    private JTextField tf;

    public Cola(JTextField tf) {
        cola = new LinkedList<>();
        this.tf = tf;
    }

    public synchronized void meter(Persona persona) {
        cola.offer(persona);
        imprimir();
    }

    public synchronized Persona sacar() {
        if (!cola.isEmpty()) {
            Persona persona = cola.poll();
            imprimir();
            return persona;
        }
        return null; // Devolver null si la cola está vacía
    }

    public void imprimir() {
        StringBuilder contenido = new StringBuilder();

        for (Persona persona : cola) {
            contenido.append(persona.getId()).append(" - ");
        }

        tf.setText(contenido.toString());
    }
}