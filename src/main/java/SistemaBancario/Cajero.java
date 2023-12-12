package SistemaBancario;

//@author Héctor Benavente García
//@author Jose Sánchez Nicolás

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cajero implements Runnable {

    private int id;
    private JTextField total;
    private JTextField operando;
    private JTextArea movimientos;
    private int dinero;
    private Persona persona;
    private String archivo = "evolucionCajeros.txt";
    private String mensajeLog = "";
    
    public Cajero(int id, JTextField total, JTextField operando, JTextArea movimientos) {
        this.id = id;
        this.total = total;
        this.operando = operando;
        this.movimientos = movimientos;
        dinero = 50000;
    }

    public int getId() {
        return id;
    }
        
    public Persona getPersona() {
        return persona;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    
    public synchronized void insertar(int cantidad){
        try{
            operando.setText(persona.getId()+"-I+"+persona.getDinero());
            movimientos.append(persona.getId()+"-I+"+persona.getDinero()+"\n");
            mensajeLog = persona.getId()+"-I+"+persona.getDinero()+ " en el Cajero " + id +"\n";
            FileWriter fileWriter = new FileWriter(archivo, true); // El parámetro true indica que se añadirá al final del archivo
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(mensajeLog);
                bufferedWriter.newLine(); // Añadir nueva línea para el próximo registro
            }
            System.out.println(mensajeLog + "guardado en log");
            System.out.println("Registro guardado correctamente en el archivo.");
            
            Random rand = new Random();
            Thread.sleep(rand.nextInt(2000)+2000);
            dinero += cantidad;
            if (dinero>=100000) {
                Solicitudes.meter(this);
                while(dinero>=100000){
                    parar();
                }
            }
            total.setText(String.valueOf(dinero));
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Cajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void extraer(int cantidad){
        try{
            operando.setText(persona.getId()+"-E-"+persona.getDinero());
            movimientos.append(persona.getId()+"-E-"+persona.getDinero()+"\n");
            mensajeLog = persona.getId()+"-I+"+persona.getDinero()+ " en el Cajero " + id +"\n";
            FileWriter fileWriter = new FileWriter(archivo, true); // El parámetro true indica que se añadirá al final del archivo
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(mensajeLog);
                bufferedWriter.newLine(); // Añadir nueva línea para el próximo registro
            }
            System.out.println(mensajeLog + "guardado en log");
            System.out.println("Registro guardado correctamente en el archivo.");
            Random rand = new Random();
            Thread.sleep(rand.nextInt(2000)+2500);
            dinero -= cantidad;
            if (dinero<=0) {
                Solicitudes.meter(this);
                while(dinero<=0){
                    parar();
                }
            }
            total.setText(String.valueOf(dinero));
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Cajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void parar() throws InterruptedException{
        this.wait();
    }
    
    public synchronized void reanudar(){
        this.notify();
    }
    
    @Override
    public void run() {
        try{
            total.setText(String.valueOf(dinero));
            Thread.sleep(5000);
            while(!Cola.estaVacia()){
                persona = Cola.sacar();
                if(persona.getOperacion()){
                    insertar(persona.getDinero());
                }else{
                    extraer(persona.getDinero());
                }
                System.out.println(persona.getId());
            }
        } catch(InterruptedException e) {}
    }
}