package SistemaBancario;

//@author Héctor Benavente García

import java.io.PrintWriter;
import java.util.Random;

public class Persona implements Runnable {

    private String id;
    private PrintWriter pw;
    private Thread[] cajeros;
    private Cola cola;
    private int dinero;
    
    public Persona(String id, PrintWriter pw, Thread[] cajeros, Cola cola) {
        this.id = id;
        this.pw = pw;
        this.cajeros = cajeros;
        this.cola = cola;
    }
    
    public String getId(){
        return id;
    }
    
    public int getDinero(){
        return dinero;
    }
    
    @Override
    public void run() {
        try {
            Random rand = new Random();
            Thread.sleep(rand.nextInt(26000) + 5000); //Tiempo de llegada escalonada
            dinero = (rand.nextInt(6) + 5) * 1000;
            
            if(rand.nextBoolean()){
                cola.meter(this);
                cola.imprimir();
                System.out.println(id+": Insertar +"+dinero);
            }else{
                cola.meter(this);
                cola.imprimir();
                System.out.println(id+": Extraer -"+dinero);
            }
        } catch (InterruptedException ex) {}
    }
}