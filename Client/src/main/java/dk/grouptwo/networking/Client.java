package dk.grouptwo.networking;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.networking.remote.RemoteClient;
import dk.grouptwo.networking.remote.RemoteServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

//maybe singleton
public class Client implements RemoteClient {

    @Override
    public void addJob(Job job) throws RemoteException {

    }

    @Override
    public void removeJob(Job job) throws RemoteException {

    }

    @Override
    public Employer loginEmployer(String CVR, String password)
            throws RemoteException {
        return null;
    }

    @Override
    public Worker loginWorker(String CPR, String password)
            throws RemoteException {
        return null;
    }

    @Override
    public void createEmployerAccount(Employer employer,
                                      String password) throws RemoteException {

    }

    @Override
    public void createWorkerAccount(Worker worker, String password)
            throws RemoteException {

    }

    @Override
    public ArrayList<Job> getAllJobs() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Job> getAllJobHistoryWorker()
            throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Job> getAllJobHistoryEmployer()
            throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Job> getUpcomingJobsWorker() throws RemoteException {
        return null;
    }

    @Override
    public Employer editEmployer(Employer employer, String password)
            throws RemoteException {
        return null;
    }

    @Override
    public Worker editWorker(Worker worker, String password)
            throws RemoteException {
        return null;
    }


}
