package dk.grouptwo.model;

import dk.grouptwo.model.objects.*;
import dk.grouptwo.networking.EmployerClient;
import dk.grouptwo.networking.Server;
import dk.grouptwo.networking.WorkerClient;
import dk.grouptwo.networking.remote.RemoteEmployerClient;
import dk.grouptwo.networking.remote.RemoteServer;
import dk.grouptwo.networking.remote.RemoteWorkerClient;
import dk.grouptwo.utility.PropertyChangeSubject;
import dk.grouptwo.utility.Validator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ModelManager implements AccountManagement, EmployerModel, WorkerModel, PropertyChangeListener {
    private PropertyChangeSupport property = new PropertyChangeSupport(this);
    private ArrayList<Job> jobs;
    private ArrayList<Job> workHistory;

    //worker
    private ArrayList<Job> upcomingJobs;
    private Worker worker;

    //employer
    private Employer employer;

    //network
    private String host = "localhost";
    private int port = 1099;
    private RemoteServer server = new Server(host, port);
    private RemoteWorkerClient workerClient;
    private RemoteEmployerClient employerClient;

    //todo observer pattern move from arrays based on udpate and fire udpate to viewmodel to update tables (simple remove and add)


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "updateJob":
                Job prevJob = getJobById(((Job) evt.getNewValue()).getJobID());
                Job newJob = (Job) evt.getNewValue();
                if (worker != null) {
                    if (newJob.getSelectedWorkers().contains(worker)) {
                        jobs.remove(prevJob);
                        if (newJob.getStatus().equals("cancelled") || newJob.getStatus().equals("completed")) {
                            if (!workHistory.contains(newJob)) {
                                workHistory.add(newJob);
                                property.firePropertyChange("moveToHistory", prevJob, newJob);
                            }
                        } else {
                            if (!upcomingJobs.contains(newJob)) {
                                upcomingJobs.add(newJob);
                                property.firePropertyChange("moveToUpcoming", prevJob, newJob);
                            }
                        }
                    } else if (prevJob.getSelectedWorkers().contains(worker) && !newJob.getSelectedWorkers().contains(worker)) {
                        upcomingJobs.remove(prevJob);
                        jobs.add(newJob);
                        property.firePropertyChange("workerCancelled", prevJob, newJob);
                        property.firePropertyChange("addJob", prevJob, newJob);
                    } else {
                        if (newJob.getStatus().equals("cancelled") || newJob.getStatus().equals("completed")) {
                            jobs.remove(prevJob);
                            property.firePropertyChange("removeFromJobs", prevJob, newJob);
                        }
                    }
                } else if (employer != null) {
                    if (newJob.getStatus().equals("completed")) {
                        jobs.remove(prevJob);
                        workHistory.add(newJob);
                        property.firePropertyChange("moveToHistory", prevJob, newJob);
                    }
                }
                break;
            case "addJob":
                jobs.add((Job) evt.getNewValue());
                property.firePropertyChange("addJob", 0, evt.getNewValue());
                break;
            case "removeJob": //could change to cancel todo
                jobs.remove(evt.getNewValue());
                property.firePropertyChange("removeJob", 0, evt.getNewValue());
                break;
        }
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);

    }

    @Override
    public void addListener(String eventID, PropertyChangeListener listener) {
        property.addPropertyChangeListener(eventID, listener);
    }

    @Override
    public void removeListener(String eventID, PropertyChangeListener listener) {
        property.removePropertyChangeListener(eventID, listener);
    }

    @Override
    public void registerAccountWorker(Worker worker, String password, String passwordConfirmation) throws Exception {
        try {
            if (Validator.createWorker(worker, password, passwordConfirmation))
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
            if (Validator.logInWorker(CPR, password)) {
                worker = server.loginWorker(CPR, password);
                workerClient = new WorkerClient();
                server.registerWorkerClient(workerClient);
                upcomingJobs = server.getUpcomingJobs(worker);
                workHistory = server.getWorkerJobHistory(worker);
            }
        } catch (RemoteException e) {
            throw new Exception("Account does not exist!");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted. For the safety of your account, you will not be logged in.");
        }
        setWorkerName(worker.getFirstName());
    }

    @Override
    public void registerAccountEmployer(Employer employer, String password, String passwordConfirmation) throws Exception {
        try {
            if (Validator.createEmployer(employer, password, passwordConfirmation))
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
            if (Validator.logInEmployer(CVR, password)) {
                employer = server.loginEmployer(CVR, password);
                jobs = server.getEmployerJobs(employer);
                employerClient = new EmployerClient();
                server.registerEmployerClient(employerClient, jobs);
                setEmployerName(employer.getCompanyName());
            }
        } catch (RemoteException e) {
            throw new Exception(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted. For the safety of your account, you will not be logged in.");
        }
    }

    @Override
    public void editEmployer(Employer employer, String password) throws Exception {
        try {
            if (Validator.updateEmployer(employer, password)) {
                server.editEmployer(employer, password);
                this.employer = employer;
            }
        } catch (RemoteException e) {
            throw new Exception(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted.");
        }
    }

    @Override
    public void editEmployer(Employer employer, String password, String newPassword, String newPasswordConfirm) throws Exception {
        try {
            if (Validator.updateEmployer(employer, password, newPassword, newPasswordConfirm)) {
                server.editEmployer(employer, password, newPassword);
                this.employer = employer;
            }
        } catch (RemoteException e) {
            throw new Exception(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could not be encrypted.");
        }
    }

    @Override
    public void editWorker(Worker worker, String password) throws Exception {
        try {
            if (Validator.updateWorker(worker, password)) {
                server.editWorker(worker, password);
                this.worker = worker;
            }
        } catch (RemoteException e) {
            throw new Exception(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Password could no be encrypted.");
        }
    }

    @Override
    public void editWorker(Worker worker, String password, String newPassword, String newPasswordConfirm) throws Exception {
        try {
            if (Validator.updateWorker(worker, password, newPassword, newPasswordConfirm)) {
                server.editWorker(worker, password, newPassword);
                this.worker = worker;
            }
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
    public ArrayList<Job> getWorkHistory() {
        return workHistory;
    }

    @Override
    public void createWorkOffer(Job job) throws Exception {
        try {
            if (Validator.createWork(job)) {
                server.addJob(job, employerClient);
                jobs.add(job);
            }
        } catch (RemoteException e) {
            throw new Exception("An error has occurred.");
        }
    }

    public void cancelWorkerFromJob(Job job) {
        server.cancelWorkerFromJob(job, worker);
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
            if (Validator.updateWork(job)) {
                server.updateJob(job);
            }
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
        return jobs;
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
        return jobs;
    }

    @Override
    public ArrayList<Job> getUpcomingJobs() throws Exception {
        return upcomingJobs;
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
