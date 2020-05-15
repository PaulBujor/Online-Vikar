package dk.grouptwo.networking;

import dk.grouptwo.database.Database;
import dk.grouptwo.database.Persistence;
import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.License;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.networking.remote.RemoteEmployerClient;
import dk.grouptwo.networking.remote.RemoteServer;
import dk.grouptwo.networking.remote.RemoteWorkerClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server implements RemoteServer {
    private ArrayList<RemoteWorkerClient> clients;
    private ArrayList<Job> jobs;
    private Persistence persistence;

    //used to save references to Employer which owns the Job
    private Map<Job, RemoteEmployerClient> jobMap = new HashMap<Job, RemoteEmployerClient>();

    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        clients = new ArrayList<RemoteWorkerClient>();
        jobs = new ArrayList<Job>();
        persistence = new Database();
    }

    @Override
    public void registerWorkerClient(RemoteWorkerClient client) throws RemoteException {
        clients.add(client);
    }

    public void registerEmployerClient(RemoteEmployerClient client, ArrayList<Job> jobs) throws RemoteException {
        for (Job job : jobs) {
            jobMap.put(job, client);
        }
        jobs.addAll(jobs);
    }

    @Override
    public void addJob(Job job, RemoteEmployerClient employerClient) throws RemoteException {
        persistence.addJobToDB(job);
        //todo job.setID with id added by database
        jobs.add(job);
        for (RemoteWorkerClient client : clients) {
            try {
                client.addJob(job);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeJob(Job job) throws RemoteException {
        jobs.remove(job);
        persistence.removeJobFromDB(job);
        for (RemoteWorkerClient client : clients) {
            try {
                client.removeJob(job);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Employer loginEmployer(String CVR, String password) throws RemoteException {
        Employer employer = null; //todo get employer CVR password
        if (employer != null)
            return employer;
        throw new RemoteException("Account could not be found!");
    }

    @Override
    public Worker loginWorker(String CPR, String password) throws RemoteException {
        Worker worker = null; //todo get worker CPR password
        if (worker != null)
            return worker;
        throw new RemoteException("Account could not be found!");
    }

    @Override
    public void applyForJob(Job job, Worker worker) throws RemoteException {
        RemoteEmployerClient client = jobMap.get(job);
        jobMap.remove(job);
        persistence.applyForJob(job, worker);
        job.addApplicant(worker);
        jobMap.put(job, client);
        client.updateJob(job);
    }

    @Override
    public void updateJob(Job job) throws RemoteException {
        persistence.updateJob(job);
        for (RemoteWorkerClient client : clients) {
            try {
                client.updateJob(job);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createEmployerAccount(Employer employer, String password) throws RemoteException {
        try {
            persistence.createEmployerAccount(employer, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createWorkerAccount(Worker worker, String password) throws RemoteException {
        try {
            persistence.createWorkerAccount(worker, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Job> getJobs() throws RemoteException {
        return persistence.getAllJobsFromDB();
    }

    @Override
    public ArrayList<Job> getWorkerJobHistory(Worker worker) throws RemoteException {
        return persistence.getAllJobHistoryWorkerFromDB(worker);
    }

    @Override
    public ArrayList<Job> getEmployerJobHistory(Employer employer) throws RemoteException {
        return persistence.getAllJobHistoryEmployerFromDB(employer);
    }

    @Override
    public ArrayList<Job> getUpcomingJobs(Worker worker) throws RemoteException {
        return persistence.getUpcomingJobsWorkerFromDB(worker);
    }

    @Override
    public ArrayList<Job> getEmployerJobs(Employer employer) throws RemoteException {
        return persistence.getCurrentEmployerJobs(employer);
    }


    //todo throw remote exception if an error occurs
    @Override
    public void editEmployer(Employer employer, String password) throws RemoteException {
        //todo
    }

    @Override
    public void editEmployer(Employer employer, String password, String newPassword) throws RemoteException, NoSuchAlgorithmException {
        //todo
    }

    @Override
    public void editWorker(Worker worker, String password) throws RemoteException {
        //todo
    }

    @Override
    public void editWorker(Worker worker, String password, String newPassword) throws RemoteException, NoSuchAlgorithmException {
        //todo
    }

    @Override
    public void addLicense(License license, Worker worker) throws RemoteException {
        //todo
    }

    @Override
    public void removeLicense(License license) throws RemoteException {
        //todo
    }
}