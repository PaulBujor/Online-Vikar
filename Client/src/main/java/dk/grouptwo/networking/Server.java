package dk.grouptwo.networking;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.License;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.networking.remote.RemoteEmployerClient;
import dk.grouptwo.networking.remote.RemoteServer;
import dk.grouptwo.networking.remote.RemoteWorkerClient;
import dk.grouptwo.utility.Encryptor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

//maybe singleton
public class Server implements RemoteServer {
    private RemoteServer server;
    private boolean connected;

//    public Server() {
//        connected = false;
//    }

    public Server(String host, int port) {
        try {
            Registry registry = null;
            try {
                registry = LocateRegistry.getRegistry(host, port);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
          /*  UnicastRemoteObject.exportObject(this, 0);*/
            server = (RemoteServer) registry.lookup("Server");
            connected = true;
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void registerWorkerClient(RemoteWorkerClient client) throws RemoteException {
        server.registerWorkerClient(client);
    }

    @Override
    public void registerEmployerClient(RemoteEmployerClient client, ArrayList<Job> jobs) throws RemoteException {
        server.registerEmployerClient(client, jobs);
    }

    public boolean isConnected() {
        return connected;
    }

    public Employer loginEmployer(String CVR, String password)
            throws RemoteException, NoSuchAlgorithmException {
        return server.loginEmployer(CVR, Encryptor.encrypt(password));
    }

    public Worker loginWorker(String CPR, String password)
            throws RemoteException, NoSuchAlgorithmException {
        return server.loginWorker(CPR, Encryptor.encrypt(password));
    }

    public void createEmployerAccount(Employer employer,
                                      String password) throws RemoteException, NoSuchAlgorithmException {
        server.createEmployerAccount(employer, Encryptor.encrypt(password));
    }

    public void createWorkerAccount(Worker worker, String password)
            throws RemoteException, NoSuchAlgorithmException {
        server.createWorkerAccount(worker, Encryptor.encrypt(password));
    }

    public void editEmployer(Employer employer, String password)
            throws RemoteException, NoSuchAlgorithmException {
        server.editEmployer(employer, Encryptor.encrypt(password));
    }

    public void editEmployer(Employer employer, String password, String newPassword)
            throws RemoteException, NoSuchAlgorithmException {
        server.editEmployer(employer, Encryptor.encrypt(password), Encryptor.encrypt(newPassword));
    }

    public void editWorker(Worker worker, String password)
            throws RemoteException, NoSuchAlgorithmException {
        server.editWorker(worker, Encryptor.encrypt(password));
    }

    public void editWorker(Worker worker, String password, String newPassword)
            throws RemoteException, NoSuchAlgorithmException {
        server.editWorker(worker, Encryptor.encrypt(password), Encryptor.encrypt(newPassword));
    }

    public void addJob(Job job, RemoteEmployerClient employerClient) throws RemoteException {
        server.addJob(job, employerClient);
    }

    public void cancelJob(Job job) throws RemoteException {
        server.cancelJob(job);
    }

    public void updateJob(Job job) throws RemoteException {
        server.updateJob(job);
    }

    public void applyForJob(Job job, Worker worker) throws RemoteException {
        server.applyForJob(job, worker);
    }

    public ArrayList<Job> getUpcomingJobs(Worker worker) throws RemoteException {
        return server.getUpcomingJobs(worker);
    }

    public ArrayList<Job> getWorkerJobHistory(Worker worker) throws RemoteException {
        return server.getWorkerJobHistory(worker);
    }

    public ArrayList<Job> getEmployerJobHistory(Employer employer) throws RemoteException {
        return server.getEmployerJobHistory(employer);
    }

    @Override
    public void cancelWorkerFromJob(Job job, Worker worker) throws  RemoteException{
        server.cancelWorkerFromJob(job, worker);
    }

    public void addLicense(License license, Worker worker) throws RemoteException {
        server.addLicense(license, worker);
    }

    public void removeLicense(License license) throws RemoteException {
        server.removeLicense(license);
    }

    public ArrayList<Job> getEmployerJobs(Employer employer) throws RemoteException {
        return server.getEmployerJobs(employer);
    }

    public ArrayList<Job> getJobs() throws RemoteException {
        return server.getJobs();
    }


}
