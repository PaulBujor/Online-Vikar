package dk.grouptwo.model;

import dk.grouptwo.model.objects.*;
import dk.grouptwo.networking.remote.RemoteClient;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelManager implements AccountManagement, EmployerModel, WorkerModel {


    private ArrayList<Job> jobs;
    private Worker worker;
    private Employer employer;
    private RemoteClient client;

    @Override
    public void registerAccountWorker(Worker worker, String password) {
        try {
            client.createWorkerAccount(worker, password);
        }
        catch (RemoteException e)
        {
            throw new IllegalArgumentException("Some kind of error");
        }

    }

    @Override
    public void logInWorker(String CPR, String password) throws RemoteException {
        worker = client.loginWorker(CPR,password);
    }

    @Override
    public void registerAccountEmployer(Employer employer, String password) {
        try {
            client.createEmployerAccount(employer, password);
        }
        catch (RemoteException e)
        {
            throw new IllegalArgumentException("Some kind of error");
        }
    }


    @Override
    public void logInEmployer(String CVR, String password) throws RemoteException {
        employer = client.loginEmployer(CVR, password);
    }

    @Override
    public void editEmployer(Employer employer, String password) throws RemoteException {
        employer = client.editEmployer(employer, password);
    }

    @Override
    public void editWorker(Worker worker, String password) throws RemoteException {
        worker = client.editWorker(worker, password);
    }

    @Override
    public void logOutWorker() {
        worker = null;

    }

    @Override
    public void logOutEmployer() {
        employer = null;
    }

    @Override
    public void createWorkOffer(Job job) throws RemoteException {
        jobs.add(job);
        client.addJob(job);
    }

    @Override
    public void cancelWorkOffer(Job job) throws RemoteException {
        jobs.remove(job);
        client.removeJob(job);
    }

    @Override
    public void updateWorkOffer(Job job) {

    }

    @Override
    public void applyForJob(Worker worker) {

    }

    @Override
    public void addLicense(License license) {
        worker.addLicense(license);
    }

    @Override
    public void deleteLicense(License license) {
        worker.removeLicense(license);
    }

}
