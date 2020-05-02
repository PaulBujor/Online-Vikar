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
    public void logInWorker(String CPR, String password) {
        try {
            worker = client.loginWorker(CPR,password);
        }
        catch (RemoteException e)
        {
            throw new IllegalArgumentException("Wrong login or password.");
        }

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
    public void logInEmployer(String CVR, String password) {
        try {
            employer = client.loginEmployer(CVR, password);
        }
        catch (RemoteException e)
        {
            throw new IllegalArgumentException("Wrong login or password.");
        }
    }

    @Override
    public void editEmployer(Employer employer, String password) {

        try {
            client.editEmployer(employer, password);
        }
        catch (RemoteException e)
        {
            throw new IllegalArgumentException("Error saving edited data.");
        }
    }

    @Override
    public void editWorker(Worker worker, String password) {
        try {
            worker = client.editWorker(worker, password);
        }
        catch (RemoteException e)
        {
            throw new IllegalArgumentException("Error saving edited data.");
        }
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
    public void createWorkOffer(Job job)   {
        jobs.add(job);
        try {
            client.addJob(job);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelWorkOffer(Job job)   {
        jobs.remove(job);
        try {
            client.removeJob(job);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
