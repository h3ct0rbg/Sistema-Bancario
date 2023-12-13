package SistemaBancarioDistribuido;

import InterfacesGraficas.ModuloVisualizacion;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Héctor Benavente García
 * @author Jose Sánchez Nicolás
 */

public class InterfaceServidor extends UnicastRemoteObject implements InterfaceDistribuida{
    private final ModuloVisualizacion interfaces;
    
    public InterfaceServidor(ModuloVisualizacion modulo) throws RemoteException{
        this.interfaces = modulo;
    }
    
    @Override
    public String[] getTotales() throws RemoteException{
        return interfaces.getTotales();
    }
    
    @Override
    public String[] getOperandos() throws RemoteException{
        return interfaces.getOperandos();
    }
    
    @Override
    public String[] getOperarios() throws RemoteException{
        return interfaces.getOperarios();
    }
    
    @Override
    public String[] getBotones() throws RemoteException{
        return interfaces.getBotones();
    }

    @Override
    public void pausarOp1(java.awt.event.ActionEvent evt) throws RemoteException{
        interfaces.pausarOp1(evt);
    }

    @Override
    public void pausarOp2(java.awt.event.ActionEvent evt) throws RemoteException{
        interfaces.pausarOp2(evt);
    }

    @Override
    public void pausar(java.awt.event.ActionEvent evt) throws RemoteException{
        interfaces.pausar(evt);
    }
}