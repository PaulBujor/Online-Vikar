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

  private RemoteServer server;


  public Client(){

  }

  @Override public void addJob(Job job) throws RemoteException
  {
server.addJob(job,this);

    }

    @Override
    public void removeJob(Job job) throws RemoteException {
server.removeJob(job,this);
    }

  @Override public void updateJob(Job job) throws RemoteException
  {
server.updateJob(job,this);
  }

  @Override public void applyForAJob(Job job) throws RemoteException
  {
server.applyForJob(job,this);
  }

  @Override
    public Employer loginEmployer(String CVR, String password)
            throws RemoteException {
        return server.loginEmployer(CVR,password);
    }

    @Override
    public Worker loginWorker(String CPR, String password)
            throws RemoteException {
        return server.loginWorker(CPR,password);
    }

    @Override
    public void createEmployerAccount(Employer employer,
                                      String password) throws RemoteException {
server.createEmployerAccount(employer,password,this);
    }

    @Override
    public void createWorkerAccount(Worker worker, String password)
            throws RemoteException {
server.createWorkerAccount(worker,password,this);
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
        return server.editEmployer(employer,password);
    }

    @Override
    public Worker editWorker(Worker worker, String password)
            throws RemoteException {
        return server.editWorker(worker,password);
    }


}
