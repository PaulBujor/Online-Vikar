package main.java.dk.grouptwo.networking.remote;

import dk.grouptwo.model.objects.Job;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteClient extends Remote

{
    //Add remove jobs
    void addJob(Job job) throws RemoteException;
    void removeJob(Job job) throws RemoteException;

    //Update job
    void updateJob(Job job) throws RemoteException;

    //Apply for a job
    void applyForAJob(Job job) throws RemoteException;

    //Logins  **** If null don't load anything
    Employer loginEmployer(String CVR, String password) throws RemoteException;
    Worker loginWorker(String CPR, String password) throws RemoteException;

    //Creating accounts **** these methods should be available only if the passwords match in the gui and the critical fields are not empty
    void createEmployerAccount(Employer employer, String password) throws RemoteException;
    void createWorkerAccount(Worker worker, String password) throws RemoteException;

    //This should load every time the program starts, but then listeners will update gui when jobs are being added or removed
    ArrayList<Job> getAllJobs() throws RemoteException;

    // gucci ?
    ArrayList<Job> getAllJobHistoryWorker() throws RemoteException;
    ArrayList<Job> getAllJobHistoryEmployer() throws RemoteException;
    ArrayList<Job> getUpcomingJobsWorker() throws RemoteException;

    //Edit profiles these methods should be available only if the passwords match in the gui and the critical fields are not empty
    Employer editEmployer(Employer employer, String password) throws RemoteException;
    Worker editWorker(Worker worker, String password) throws RemoteException;
}

