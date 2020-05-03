package dk.grouptwo.networking.remote;

import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteServer extends Remote
{
    //TODO need to test everything to see how it works, missing methods, maybe need some editing and so on..
    //This should register workers to get updates for job changes
    void registerClient(RemoteClient clientToRegister) throws RemoteException;

    //Adding and removing jobs
    //TODO might not need remoteClient
    void addJob(Job job, RemoteClient client) throws RemoteException;
    void removeJob(Job job, RemoteClient client) throws RemoteException;

    //Logins
    Employer loginEmployer(String CVR,String password) throws RemoteException;
    Worker loginWorker(String CPR, String password) throws RemoteException;

    //Edit profiles
    Employer editEmployer(Employer employer,String password) throws RemoteException;
    Worker editWorker(Worker worker,String password) throws RemoteException;

    //Apply and update job
    void applyForJob(Job job, RemoteClient client) throws RemoteException;
    void updateJob(Job job, RemoteClient client) throws RemoteException;

    //Creating accounts
    void createEmployerAccount(Employer employer,String password,RemoteClient client) throws RemoteException;
    void createWorkerAccount(Worker worker,String password, RemoteClient client) throws RemoteException;

    //Broadcast, client updates, dunno havent decided yet, need to test this myself
    ArrayList<Job> getAllJobsFromDB() throws RemoteException;
    ArrayList<Job> getAllJobHistoryWorkerFromDB() throws RemoteException;
    ArrayList<Job> getAllJobHistoryEmployerFromDB() throws RemoteException;
    ArrayList<Job> getUpcomingJobsWorkerFromDB() throws RemoteException;

    //Getting jobs of the employer
    ArrayList<Job> getEmployerJobs(Employer employer) throws RemoteException;

}
