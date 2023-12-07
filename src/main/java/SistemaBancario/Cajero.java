package SistemaBancario;

//@author Héctor Benavente García

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cajero implements Runnable {

    private int id;
    private JTextField total;
    private JTextField operando;
    private JTextArea movimientos;
    private int dinero;
    private Persona persona;
    private Thread[] operarios;
    private String archivo = "evolucionCajeros.txt";
    private String mensajeLog = "";
    
    public Cajero(int id, Thread[] operarios, JTextField total, JTextField operando, JTextArea movimientos) {
        this.id = id;
        this.operarios = operarios;
        this.total = total;
        this.operando = operando;
        this.movimientos = movimientos;
        dinero = 50000;
    }
    
    public synchronized void insertar(int cantidad) throws InterruptedException{
        operando.setText(persona.getId()+"-I+"+persona.getDinero());
        movimientos.append(persona.getId()+"-I+"+persona.getDinero()+"\n");
        mensajeLog = persona.getId()+"-I+"+persona.getDinero()+ " en el Cajero " + id +"\n";
        try{
            FileWriter fileWriter = new FileWriter(archivo, true); // El parámetro true indica que se añadirá al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(mensajeLog);
            bufferedWriter.newLine(); // Añadir nueva línea para el próximo registro
            bufferedWriter.close();
            System.out.println(mensajeLog + "guardado en log");
            System.out.println("Registro guardado correctamente en el archivo.");
        }catch (IOException e){
            System.err.println("Error al escribir en el log: " + e.getMessage());
        }
        Random rand = new Random();
        Thread.sleep(rand.nextInt(2000)+2000);
        int nuevoDinero = dinero + cantidad;
        if (nuevoDinero>=100000) {
            nuevoDinero-=50000;
        }
        dinero = nuevoDinero;
        total.setText(String.valueOf(dinero));
    }
    
    public synchronized void extraer(int cantidad) throws InterruptedException{
        operando.setText(persona.getId()+"-E-"+persona.getDinero());
        movimientos.append(persona.getId()+"-E-"+persona.getDinero()+"\n");
        mensajeLog = persona.getId()+"-I+"+persona.getDinero()+ " en el Cajero " + id +"\n";
        try{
            FileWriter fileWriter = new FileWriter(archivo, true); // El parámetro true indica que se añadirá al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(mensajeLog);
            bufferedWriter.newLine(); // Añadir nueva línea para el próximo registro
            bufferedWriter.close();
            System.out.println(mensajeLog + "guardado en log");
            System.out.println("Registro guardado correctamente en el archivo.");
        }catch (IOException e){
            System.err.println("Error al escribir en el log: " + e.getMessage());
        }
        Random rand = new Random();
        Thread.sleep(rand.nextInt(2000)+2500);
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