package dk.grouptwo.model;

import dk.grouptwo.model.objects.*;
import dk.grouptwo.networking.EmployerClient;
import dk.grouptwo.networking.Server;
import dk.grouptwo.networking.WorkerClient;
import dk.grouptwo.networking.remote.RemoteEmployerClient;
import dk.grouptwo.networking.remote.RemoteServer;
import dk.grouptwo.networking.remote.RemoteWorkerClient;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ModelManager implements AccountManagement, EmployerModel, WorkerModel {
    private ArrayList<Job> jobs;
    private ArrayList<Job> workHistory;
    private Worker worker;
    private Employer employer;
    private String host = "localhost";
    private int port = 1099;
    private RemoteServer server = new Server(host, port);
    private RemoteWorkerClient workerClient;
    private RemoteEmployerClient employerClient;

    @Override
    public void registerAccountWorker(Worker worker, String password) throws Exception {
        try {
            server.createWorkerAccount(worker, password);
        } catch (RemoteException e) {
            throw new Exception("Account could not be created!");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted.");
        }
    }

    @Override
    public void logInWorker(String CPR, String password) throws Exception {
        try {
            worker = server.loginWorker(CPR, password);
            workerClient = new WorkerClient(this);
            server.registerWorkerClient(workerClient);
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
            server.createEmployerAccount(employer, password);
        } catch (RemoteException e) {
            throw new Exception("Account could not be created!");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted.");
        }
    }


    @Override
    public void logInEmployer(String CVR, String password) throws Exception {
        try {
            employer = server.loginEmployer(CVR, password);
            jobs = server.getEmployerJobs(employer);
            employerClient = new EmployerClient(this);
            server.registerEmployerClient(employerClient, jobs);
            setEmployerName(employer.getCompanyName());
        } catch (RemoteException e) {
            throw new Exception(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted. For the safety of your account, you will not be logged in.");
        }
    }

    @Override
    public void editEmployer(Employer employer, String password) throws Exception {
        try {
            server.editEmployer(employer, password);
            this.employer = employer;
        } catch (RemoteException e) {
            throw new Exception(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted.");
        }
    }

    @Override
    public void editEmployer(Employer employer, String password, String newPassword) throws Exception {
        try {
            server.editEmployer(employer, password, newPassword);
            this.employer = employer;
        } catch (RemoteException e) {
            throw new Exception(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted.");
        }
    }

    @Override
    public void editWorker(Worker worker, String password) throws Exception {
        try {
            server.editWorker(worker, password);
            this.worker = worker;
        } catch (RemoteException e) {
            throw new Exception(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could no be encrypted.");
        }
    }

    @Override
    public void editWorker(Worker worker, String password, String newPassword) throws Exception {
        try {
            server.editWorker(worker, password, newPassword);
            this.worker = worker;
        } catch (RemoteException e) {
            throw new Exception(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could no be encrypted.");
        }
    }

    public Employer getEmployer() {
        return employer;
    }

    public Worker getWorker() {
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
            return server.getWorkerJobHistory(worker);
        } catch (RemoteException e) {
            return null;
        }
    }

    public ArrayList<Job> getEmployerJobHistory() throws Exception {
        try {
            return server.getEmployerJobHistory(employer);
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public void createWorkOffer(Job job) throws Exception {
        jobs.add(job);
        try {
            server.addJob(job);
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public void cancelWorkOffer(Job job) throws Exception {
        jobs.remove(job);
        try {
            server.removeJob(job);
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public void updateWorkOffer(Job job) throws Exception {
        try {
            server.updateJob(job);
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public void applyForJob(int jobID) throws Exception {
        try {
            server.applyForJob(getJobById(jobID), worker);
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public void addLicense(License license) throws Exception {
        try {
            server.addLicense(license, worker);
        } catch (RemoteException e) {
            throw new Exception("An error has occured.");
        }
    }

    @Override
    public void deleteLicense(String licenseNumber) throws Exception {
        try {
            server.removeLicense(getLicenseByNumber(licenseNumber));
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
        try {
            return server.getEmployerJobs(employer);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
            //todo
        }
    }

    @Override
    public Worker getWorkerByJob(int jobID, String CPR) {
        Job job = getJobById(jobID);
        for (Worker worker : job.getApplicants()) {
            if (worker.getCPR().equals(CPR))
                return worker;
        }
        return null;
    }

    @Override
    public ArrayList<Job> getJobs() {
        try {
            return server.getJobs();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
            //todo
        }
    }

    @Override
    public ArrayList<Job> getUpcomingJobs() throws Exception {
        try {
            return server.getUpcomingJobs(worker);
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
