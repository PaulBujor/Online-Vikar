package dk.grouptwo.networking.remote;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.License;
import dk.grouptwo.model.objects.Worker;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public interface RemoteServer extends Remote {
    public void registerWorkerClient(RemoteWorkerClient client) throws RemoteException;

    public void registerEmployerClient(RemoteEmployerClient client, ArrayList<Job> jobs) throws RemoteException;

    //Logins
    Employer loginEmployer(String CVR, String password) throws RemoteException, NoSuchAlgorithmException;

    Worker loginWorker(String CPR, String password) throws RemoteException, NoSuchAlgorithmException;

    //Creating accounts
    void createEmployerAccount(Employer employer, String password) throws RemoteException, NoSuchAlgorithmException;

    void createWorkerAccount(Worker worker, String password) throws RemoteException, NoSuchAlgorithmException;

    //Edit profiles
    void editEmployer(Employer employer, String password) throws RemoteException, NoSuchAlgorithmException;

    void editEmployer(Employer employer, String password, String newPassword) throws RemoteException, NoSuchAlgorithmException;

    void editWorker(Worker worker, String password) throws RemoteException, NoSuchAlgorithmException;

    void editWorker(Worker worker, String password, String newPassword) throws RemoteException, NoSuchAlgorithmException;

    //Apply and update job
    void applyForJob(Job job, Worker worker) throws RemoteException;

    //client used for callback, might not be needed if exceptions are handled nicely
    void addJob(Job job, RemoteEmployerClient employerClient) throws RemoteException;

    void removeJob(Job job) throws RemoteException;

    void updateJob(Job job) throws RemoteException;

    //get jobs
    ArrayList<Job> getUpcomingJobs(Worker worker) throws RemoteException;

    ArrayList<Job> getWorkerJobHistory(Worker worker) throws RemoteException;

    ArrayList<Job> getEmployerJobHistory(Employer employer) throws RemoteException;

    void addLicense(License license, Worker worker) throws RemoteException;

    void removeLicense(License license) throws RemoteException;

    ArrayList<Job> getEmployerJobs(Employer employer) throws RemoteException;

    ArrayList<Job> getJobs() throws RemoteException;

    void cancelWorkerFromJob(Job job, Worker worker) throws RemoteException;
}
