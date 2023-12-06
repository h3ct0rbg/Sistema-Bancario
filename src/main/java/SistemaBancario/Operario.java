package SistemaBancario;

//@author Héctor Benavente García

public class Operario implements Runnable{
    
    private String id;
    private boolean ocupado;
    
    public Operario(String id, Thread[] cajeros) {
        this.id = id;
        ocupado = false;
    }
    
    public void enviar(int cantidad){
        
    }
    
    public int traer(){
        return 0; 
    }
    
    @Override
    public void run() {
        while(true){
            
        }
    }
    
}