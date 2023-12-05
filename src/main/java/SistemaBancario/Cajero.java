package SistemaBancario;

//@author Héctor Benavente García

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.PrintWriter;

public class Cajero implements Runnable {

    private int id;
    private int dinero;
    private PrintWriter pw;
    private Cola cola;
    private Persona persona;
    private boolean ocupado = false;
    private Lock cerrojo = new ReentrantLock();
    private Condition insertar = cerrojo.newCondition();
    private Condition retirar = cerrojo.newCondition();
    
    public Cajero(int id, PrintWriter pw, Cola cola) {
        this.id = id;
        this.pw = pw;
        this.cola = cola;
        dinero = 50000;
    }
    
    public void insertar(int cantidad){
        cerrojo.lock();
        ocupado = true;
        cola.sacar();
        int nuevoDinero = dinero + cantidad;
        if (nuevoDinero>=100000) {
            try {
                insertar.await();
            } catch (InterruptedException e) {}
        }
        dinero = nuevoDinero;
        ocupado = false;
        cerrojo.unlock();
    }
    
    public void extraer(int cantidad){
        cerrojo.lock();
        dinero-=cantidad;
        cerrojo.unlock();
    }
    
    public boolean estaOcupado(){
        return ocupado;
    }

    @Override
    public void run() {
        try{
            do{
                persona = cola.sacar();
            } while(persona!=null);
        } catch(Exception e) {}
    }
}