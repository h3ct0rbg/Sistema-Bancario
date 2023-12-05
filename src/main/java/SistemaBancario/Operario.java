package SistemaBancario;

//@author Héctor Benavente García

import java.io.PrintWriter;

public class Operario implements Runnable{
    
    private String id;
    
    public Operario(String id, PrintWriter pw, Thread[] cajeros) {
        this.id = id;
    }
    
    @Override
    public void run() {
        System.out.println(id+": Listo");
    }
    
}