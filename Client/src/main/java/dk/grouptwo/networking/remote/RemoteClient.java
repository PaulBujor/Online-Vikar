package dk.grouptwo.networking.remote;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public interface RemoteClient extends Remote {
    //Add remove jobs
    void addJob(Job job) throws RemoteException;

    void removeJob(Job job) throws RemoteException;

    //Update job
    void updateJob(Job job) throws RemoteException;

    //Apply for a job
    void applyForAJob(Job job) throws RemoteException;

    //This should load every time the program starts, but then listeners will update gui when jobs are being added or removed
    ArrayList<Job> getAllJobs() throws RemoteException;

    // gucci ?
    ArrayList<Job> getAllJobHistoryWorker() throws RemoteException;

    ArrayList<Job> getAllJobHistoryEmployer() throws RemoteException;

    ArrayList<Job> getUpcomingJobsWorker() throws RemoteException;

    Worker editWorker(Worker worker, String password) throws RemoteException, NoSuchAlgorithmException;


}
