package SistemaBancario;

//@author Héctor Benavente García
//@author Jose Sánchez Nicolás

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Operario implements Runnable{
    
    private int id;
    private JTextField op;
    private JTextArea movimientos;
    private Cajero cajero;
    private final Lock lock = new ReentrantLock();
    private final Condition condicionParado = lock.newCondition();
    private boolean parado = false;
    
    public Operario(int id, JTextField op, JTextArea movimientos) {
        this.id = id;
        this.op = op;
        this.movimientos = movimientos;
        parado = false;
    }

    public int getId() {
        return id;
    }
    
    public boolean isParado() {
        return parado;
    }
    
    public synchronized void retirar(int cantidad) throws InterruptedException{
        Thread.sleep(2000);
        int dinero = cajero.getDinero();
        cajero.setDinero(dinero-cantidad);
        cajero.start();
        BancoCentral.insertar(cantidad);
        movimientos.append("Operario"+id+"-C"+cajero.getId()+"+50.000"+"\n");
    }
    
    public synchronized void depositar(int cantidad) throws InterruptedException{
        Thread.sleep(3000);
         int cant = BancoCentral.extraer(cantidad);
         int dinero = cajero.getDinero();
         cajero.setDinero(dinero+cant);
         movimientos.append("Operario"+id+"-C"+cajero.getId()+"-50.000"+"\n");
         cajero.start();
    }
    
    public void parar() throws InterruptedException {
        lock.lock();
        try {
            parado = true;
        } finally {
            lock.unlock();
        }
    }

    public void reanudar() {
        lock.lock();
        try {
            parado = false;
            condicionParado.signal();
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public void run() {
        while(true){
            while(parado){
                try {
                    condicionParado.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Operario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            cajero = Solicitudes.sacar(this);
            if(cajero.getPersona().getOperacion()){
                try {
                    op.setText("Llevando 50.000 del Cajero"+cajero.getId()+" al Banco Central");
                    retirar(50000);
                    op.setText("");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Operario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    op.setText("Trayendo 50.000 del Banco Central al Cajero"+cajero.getId());
                    depositar(50000);
                    op.setText("");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Operario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}