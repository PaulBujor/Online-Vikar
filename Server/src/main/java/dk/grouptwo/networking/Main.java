package dk.grouptwo.networking;

import dk.grouptwo.networking.remote.RemoteServer;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;


public class Main {
    public static void main(String[] args) throws RemoteException,
        AlreadyBoundException
    {
        RemoteServer server = new Server();
        Registry registry = LocateRegistry.createRegistry(1099);

        registry.bind("Server",server);
        System.out.println("Server started..");
    }
}