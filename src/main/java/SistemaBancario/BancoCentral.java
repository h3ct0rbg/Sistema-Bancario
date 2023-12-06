package SistemaBancario;

//@author Héctor Benavente García

import java.io.PrintWriter;

public class BancoCentral {

    private int dinero;
    
    public BancoCentral(PrintWriter pw) {
        dinero = 250000;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
}