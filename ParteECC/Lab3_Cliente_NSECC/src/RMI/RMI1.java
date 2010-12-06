package RMI;

import Interfaces.LlamadaRemotaInterface;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI1 {

    static Registry registro;
    static int RMIPort;
    static String hostName;
    static String nombre;

    public static LlamadaRemotaInterface getLlamadaRemotaTerminal(LlamadaRemotaInterface interfaz) throws RemoteException, NotBoundException {
        java.security.AllPermission a = new java.security.AllPermission();
        System.setProperty("java.security.policy", "rmi.policy");

        //System.out.println(getHostName() + "++ "+getRMIPort());
        registro = LocateRegistry.getRegistry(getHostName(), getRMIPort());

        interfaz = (LlamadaRemotaInterface) getRegistro().lookup("LlamadaRemotaTerminal");
        return interfaz;
    }

    public static Registry iniciarRegistro()
            throws NotBoundException, MalformedURLException, RemoteException {

        registro = null;
        registro = startRegistry(getRMIPort());
        return getRegistro();
    }

    private static Registry startRegistry(int RMIPortNum)
            throws RemoteException {

        try {
            registro = LocateRegistry.getRegistry(RMIPortNum);
            registro.list();
        }
        catch (RemoteException e) {
            registro = LocateRegistry.createRegistry(RMIPortNum);
            registro.list();
        }
        return registro;
    }

    public static void setLlamadaRemotaTerminal(Registry registro)
            throws NotBoundException, MalformedURLException, RemoteException {

        LlamadaRemotaInterface llamada = new LlamadaRemotaTerminal();

        registro.rebind("LlamadaRemotaTerminal", llamada);
    }

    public static LlamadaRemotaInterface getLlamadaRemota(LlamadaRemotaInterface interfaz)
            throws RemoteException, NotBoundException {

        java.security.AllPermission a = new java.security.AllPermission();
        System.setProperty("java.security.policy", "rmi.policy");

        //System.out.println(getHostName() + "++ "+getRMIPort());
        registro = LocateRegistry.getRegistry(getHostName(), getRMIPort());

        interfaz = (LlamadaRemotaInterface) getRegistro().lookup("LlamadaRemota");
        return interfaz;

    }

    public static Registry getRegistro() {
        return registro;
    }

    public static void setRegistro(Registry aRegistro) {
        registro = aRegistro;
    }

    public static int getRMIPort() {
        return RMIPort;
    }

    public static void setRMIPort(int aRMIPort) {
        RMIPort = aRMIPort;
    }

    public static String getHostName() {
        return hostName;
    }

    public static void setHostName(String aHostName) {
        hostName = aHostName;
    }

    public static String getNombre() {
	return nombre;
    }

    public static void setNombre(String aNombre) {
	nombre = aNombre;
    }


}
