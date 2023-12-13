package SistemaBancarioDistribuido;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Héctor Benavente García
 * @author Jose Sánchez Nicolás
 */

public interface InterfaceDistribuida extends Remote {
        
    public String[] getTotales() throws RemoteException;
    
    public String[] getOperandos() throws RemoteException;
    
    public String[] getOperarios() throws RemoteException;
    
    public String[] getBotones() throws RemoteException;
    
    public void pausarOp1(java.awt.event.ActionEvent evt) throws RemoteException;

    public void pausarOp2(java.awt.event.ActionEvent evt) throws RemoteException;

    public void pausar(java.awt.event.ActionEvent evt) throws RemoteException;

}