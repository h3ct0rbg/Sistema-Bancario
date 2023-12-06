package SistemaBancario;

//@author Héctor Benavente García

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.PrintWriter;
import javax.swing.JTextField;

public class Cajero implements Runnable {

    private int id;
    private JTextField total;
    private JTextField operando;
    private int dinero;
    private Persona persona;
    
    public Cajero(int id, JTextField total, JTextField operando) {
        this.id = id;
        this.total = total;
        this.operando = operando;
        dinero = 50000;
    }
    
    public synchronized void insertar(int cantidad) throws InterruptedException{
        operando.setText(persona.getId()+"-I+"+persona.getDinero());
        Thread.sleep(2000);
        int nuevoDinero = dinero + cantidad;
        if (nuevoDinero>=100000) {
            nuevoDinero-=50000;
        }
        dinero = nuevoDinero;
        total.setText(String.valueOf(dinero));
    }
    
    public synchronized void extraer(int cantidad) throws InterruptedException{
        operando.setText(persona.getId()+"-E-"+persona.getDinero());
        Thread.sleep(1000);
        int nuevoDinero = dinero - cantidad;
        if (nuevoDinero<=0) {
            nuevoDinero+=50000;
        }
        dinero = nuevoDinero;
        total.setText(String.valueOf(dinero));
    }
    
    @Override
    public void run() {
        try{
            Thread.sleep(8000);
            while(!Cola.estaVacia()){
                persona = Cola.sacar();
                if(persona.getOperacion()){
                    insertar(persona.getDinero());
                }else{
                    extraer(persona.getDinero());
                }
                System.out.println(persona.getId());
            }
        } catch(Exception e) {}
    }
}