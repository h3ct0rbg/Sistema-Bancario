package SistemaBancario;

//@author Héctor Benavente García

import java.util.Random;

public class Persona implements Runnable {

    private String id;
    private boolean operacion;
    private int dinero;
    
    public Persona(String id) {
        this.id = id;
    }
    
    public String getId(){
        return id;
    }
    
    public int getDinero(){
        return dinero;
    }
    
    public boolean getOperacion(){
        return operacion;
    }
    
    @Override
    public void run() {
        try {
            Random rand = new Random();
            Thread.sleep(rand.nextInt(36000) + 5000); //Tiempo de llegada escalonada
            operacion = rand.nextBoolean();
            dinero = (rand.nextInt(6) + 5) * 1000;
           
            Cola.meter(this);

        } catch (InterruptedException ex) {}
    }
}