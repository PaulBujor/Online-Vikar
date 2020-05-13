package dk.grouptwo.model;

import dk.grouptwo.model.objects.*;
import dk.grouptwo.networking.Connection;
import dk.grouptwo.networking.LocalConnectionTest;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ModelManager implements AccountManagement, EmployerModel, WorkerModel {
    private ArrayList<Job> jobs;
    private ArrayList<Job> workHistory;
    private Worker worker;
    private Employer employer;
    private Connection connection = new LocalConnectionTest();

    @Override
    public void registerAccountWorker(Worker worker, String password) throws Exception {
        try {
            connection.createWorkerAccount(worker, password);
        } catch (RemoteException e) {
            throw new Exception("Account could not be created!");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted.");
        }
    }

    @Override
    public void logInWorker(String CPR, String password) throws Exception {
        try {
            worker = connection.loginWorker(CPR, password);
        } catch (RemoteException e) {
            throw new Exception("Account does not exist!");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted. For the safety of your account, you will not be logged in.");
        }
        setWorkerName(worker.getFirstName());
    }

    @Override
    public void registerAccountEmployer(Employer employer, String password) throws Exception {
        try {
            connection.createEmployerAccount(employer, password);
        } catch (RemoteException e) {
            throw new Exception("Account could not be created!");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted.");
        }
    }


    @Override
    public void logInEmployer(String CVR, String password) throws Exception {
        try {
            employer = connection.loginEmployer(CVR, password);
        } catch (RemoteException e) {
            throw new Exception("Account does not exist!");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted. For the safety of your account, you will not be logged in.");
        }
        setEmployerName(employer.getCompanyName());
    }

    @Override
    public void editEmployer(Employer employer, String password) throws Exception {
        try {
            connection.editEmployer(employer, password);
        } catch (RemoteException e) {
            throw new IllegalArgumentException("Error saving edited data.");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted.");
        }
    }

    @Override
    public void editEmployer(Employer employer, String password, String newPassword) throws Exception {
        try {
            connection.editEmployer(employer, password, newPassword);
        } catch (RemoteException e) {
            throw new IllegalArgumentException("Error saving edited data.");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted.");
        }
    }

    @Override
    public void editWorker(Worker worker, String password) throws Exception {
        try {
            worker = connection.editWorker(worker, password);
        } catch (RemoteException e) {
            throw new Exception("Error saving edited data.");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could no be encrypted.");
        }
    }

    @Override
    public void editWorker(Worker worker, String password, String newPassword) throws Exception {
        try {
            worker = connection.editWorker(worker, password, newPassword);
        } catch (RemoteException e) {
            throw new Exception("Error saving edited data.");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could no be encrypted.");
        }
    }

    public Employer getEmployer() {
        return employer;
    }

    public Worker getWorker()
    {
        return worker;
    }

    @Override
    public Job getJobById(int jobId) {
        for (Job job : jobs) {
            if (job.getJobID() == jobId)
                return job;
        }
        return null;
    }

    public License getLicenseByNumber(String number) {
        for (License license : worker.getLicenses()) {
            if (license.getLicenseNumber().equals(number))
                return license;
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
        return (double) minutes / 60;
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
    public ArrayList<Job> getWorkerJobHistory() {
        try {
            return connection.getWorkerJobHistory(worker);
        } catch (RemoteException e) {
            return null;
        }
    }

    public ArrayList<Job> getEmployerJobHistory() throws Exception {
        try {
            return connection.getEmployerWorkHistory(employer);
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public void createWorkOffer(Job job) throws Exception {
        jobs.add(job);
        try {
            connection.addJob(job);
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public void cancelWorkOffer(Job job) throws Exception {
        jobs.remove(job);
        try {
            connection.removeJob(job);
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public void updateWorkOffer(Job job) throws Exception {
        try {
            connection.updateJob(job);
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public void applyForJob(int jobID) throws Exception {
        try {
            connection.applyForAJob(getJobById(jobID), worker);
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public void addLicense(License license) throws Exception {
        try {
            connection.addLicense(license, worker);
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public void deleteLicense(String licenseNumber) throws Exception {
        try {
            connection.removeLicense(getLicenseByNumber(licenseNumber));
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public ArrayList<License> getLicenses() {
        return worker.getLicenses();
    }

    @Override
    public ArrayList<Job> getEmployerJobs() {
        return connection.getEmployerJobs(employer);
    }

    @Override
    public ArrayList<Job> getJobs() {
        return connection.getJobs();
    }

    @Override
    public ArrayList<Job> getUpcomingJobs() throws Exception {
        try {
            return connection.getUpcomingJobs(worker);
        } catch (RemoteException e) {
            throw new Exception("No jobs could be found");
        }
    }

    //static for username button
    private static String employerName;
    private static String workerName;

    public static String getEmployerName() {
        return employerName;
    }

    public static String getWorkerName() {
        return workerName;
    }

    public static void setEmployerName(String employerName) {
        ModelManager.employerName = employerName;
    }

    public static void setWorkerName(String workerName) {
        ModelManager.workerName = workerName;
    }
}
