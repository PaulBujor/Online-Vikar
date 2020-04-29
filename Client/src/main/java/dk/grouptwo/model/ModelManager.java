package dk.grouptwo.model;

import dk.grouptwo.model.objects.*;
import dk.grouptwo.networking.remote.RemoteClient;

import java.util.ArrayList;

public class ModelManager implements AccountManagement, EmployerModel, WorkerModel {


    private ArrayList<Job> jobs;
    private Worker worker;
    private RemoteClient client;

    @Override
    public void registerAccountWorker(Worker worker, String password) {

    }

    @Override
    public void logInWorker(String CPR, String password) {

    }

    @Override
    public void registerAccountEmployer(Employer employer, String password) {

    }


    @Override
    public void logInEmployer(String CVR, String password) {

    }

    @Override
    public void createWorkOffer(Job job) {

    }

    @Override
    public void cancelWorkOffer(Job job) {

    }

    @Override
    public void updateWorkOffer(Job job) {

    }

    @Override
    public void applyForJob(Worker worker) {

    }

    @Override
    public void addLicense(License license) {

    }

    @Override
    public void deleteLicense(License license) {

    }
}
