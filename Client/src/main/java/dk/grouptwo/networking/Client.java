package dk.grouptwo.networking;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.networking.remote.RemoteClient;
import dk.grouptwo.networking.remote.RemoteServer;
import dk.grouptwo.utility.Encryptor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

//maybe singleton
public class Client implements RemoteClient {

    private RemoteServer server;
    private boolean connected;


    public Client() {
        connected = false;
    }

    public void StartClient(String host, int port) {
        try {
            Registry registry = null;
            try {
                registry = LocateRegistry.getRegistry(host, port);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            UnicastRemoteObject.exportObject(this, 0);
            server = (RemoteServer) registry.lookup("Server");

            server.registerClient(this);
            connected = true;
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connected;
    }

    @Override
    public void addJob(Job job) throws RemoteException {
        server.addJob(job, this);

    }

    @Override
    public void removeJob(Job job) throws RemoteException {
        server.removeJob(job, this);
    }

    @Override
    public void updateJob(Job job) throws RemoteException {
        server.updateJob(job, this);
    }

    @Override
    public void applyForAJob(Job job) throws RemoteException {
        server.applyForJob(job, this);
    }

    @Override
    public Employer loginEmployer(String CVR, String password)
            throws RemoteException, NoSuchAlgorithmException {
        return server.loginEmployer(CVR, Encryptor.toHexString(Encryptor.getSHA(password)));
    }

    @Override
    public Worker loginWorker(String CPR, String password)
            throws RemoteException, NoSuchAlgorithmException {
        return server.loginWorker(CPR, Encryptor.toHexString(Encryptor.getSHA(password)));
    }

    @Override
    public void createEmployerAccount(Employer employer,
                                      String password) throws RemoteException {
        server.createEmployerAccount(employer, password, this);
    }

    @Override
    public void createWorkerAccount(Worker worker, String password)
            throws RemoteException {
        server.createWorkerAccount(worker, password, this);
    }

    @Override
    public ArrayList<Job> getAllJobs() throws RemoteException {
        return server.getAllJobsFromDB();
    }

    @Override
    public ArrayList<Job> getAllJobHistoryWorker()
            throws RemoteException {
        return server.getAllJobHistoryWorkerFromDB();
    }

    @Override
    public ArrayList<Job> getAllJobHistoryEmployer()
            throws RemoteException {
        return server.getAllJobHistoryEmployerFromDB();
    }

    @Override
    public ArrayList<Job> getUpcomingJobsWorker() throws RemoteException {
        return server.getUpcomingJobsWorkerFromDB();
    }

    @Override
    public Employer editEmployer(Employer employer, String password)
            throws RemoteException {
        return server.editEmployer(employer, password);
    }

    @Override
    public Worker editWorker(Worker worker, String password)
            throws RemoteException {
        return server.editWorker(worker, password);
    }


}
