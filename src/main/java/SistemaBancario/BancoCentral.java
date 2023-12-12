package SistemaBancario;

//@author Héctor Benavente García
//@author Jose Sánchez Nicolás

import javax.swing.JTextField;

public class BancoCentral {

    private static int dinero;
    private static JTextField total;
    
    public static void inicializar(JTextField total) {
        dinero = 250000;
        BancoCentral.total = total;
        total.setText(String.valueOf(dinero));
    }

    public static synchronized void insertar(int cantidad) {
        dinero+=cantidad;
        total.setText(String.valueOf(dinero));
    }

    public static synchronized int extraer(int cantidad) {
        if(dinero<cantidad){
            cantidad=0;
        }
        dinero-=cantidad;
        total.setText(String.valueOf(dinero));
        return cantidad;
    }
}