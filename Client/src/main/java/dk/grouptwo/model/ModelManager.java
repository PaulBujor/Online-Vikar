package dk.grouptwo.model;

import dk.grouptwo.model.objects.*;
import dk.grouptwo.networking.Client;
import dk.grouptwo.networking.LocalClientTest;
import dk.grouptwo.networking.remote.RemoteClient;
import javafx.beans.property.DoubleProperty;

import java.rmi.RemoteException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ModelManager implements AccountManagement, EmployerModel, WorkerModel {


    private ArrayList<Job> jobs;
    private ArrayList<Job> workHistory;
    private Worker worker;
    private Employer employer;
    private Client client = new LocalClientTest();

    @Override
    public void registerAccountWorker(Worker worker, String password) {
        try {
            client.createWorkerAccount(worker, password);
        } catch (RemoteException e) {
            throw new IllegalArgumentException("Some kind of error");
        }

    }

    @Override
    public void logInWorker(String CPR, String password) throws Exception {
        try {
            worker = client.loginWorker(CPR, password);
        } catch (RemoteException e) {
            throw new Exception("Account does not exist!");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted. For the safety of your account, you will not be logged in.");
        }

    }

    @Override
    public void registerAccountEmployer(Employer employer, String password) throws Exception {
        try {
            client.createEmployerAccount(employer, password);
        } catch (RemoteException e) {
            throw new Exception("Account could not be created!");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted. For the safety of your account, you will not be logged in.");
        }
    }


    @Override
    public void logInEmployer(String CVR, String password) throws Exception {
        try {
            employer = client.loginEmployer(CVR, password);
        } catch (RemoteException e) {
            throw new Exception("Account does not exist!");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted. For the safety of your account, you will not be logged in.");
        }
    }

    @Override
    public void editEmployer(Employer employer, String password) {
        try {
            client.editEmployer(employer, password);
        } catch (RemoteException e) {
            throw new IllegalArgumentException("Error saving edited data.");
        }
    }

    @Override
    public void editWorker(Worker worker, String password) {
        try {
            worker = client.editWorker(worker, password);
        } catch (RemoteException e) {
            throw new InvalidParameterException("Error saving edited data.");
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
    public ArrayList<Job> getJobHistory() {
        return null;
        //TODO return not null
    }

    @Override
    public void createWorkOffer(Job job) {
        jobs.add(job);
        try {
            client.addJob(job);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelWorkOffer(Job job) {
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
    public void deleteLicense(String licenseNumber) {
        // worker.removeLicense(license);
    }

    @Override
    public ArrayList<License> getLicenses() {
        return worker.getLicenses();
    }

    @Override
    public Job getJobById(int jobId) {
        for (Job job : jobs) {
            if (job.getJobID() == jobId)
                return job;
        }
        return null;
    }

    @Override
    public double getHoursWorkedThisMonth() {
        LocalDateTime currentDate = LocalDateTime.now();
        int minutes = 0;

        for (Job job : workHistory) {
            if (job.getShiftStart().getMonth().equals(currentDate.getMonth())) {
                minutes += ChronoUnit.MINUTES.between(job.getShiftEnd(), job.getShiftStart());
            }
        }
        return (double) minutes/60;

    }

}
