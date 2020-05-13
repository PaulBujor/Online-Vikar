package dk.grouptwo.model;

import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.License;
import dk.grouptwo.model.objects.Worker;

import java.util.ArrayList;


public interface WorkerModel {

    void applyForJob(int jobID) throws Exception;

    void addLicense(License license) throws Exception;

    void deleteLicense(String licenseNumber) throws Exception;

    ArrayList<License> getLicenses();

    double getHoursWorkedThisMonth();

    ArrayList<Job> getWorkerJobHistory();

    ArrayList<Job> getJobs();

    ArrayList<Job> getUpcomingJobs() throws Exception;

    Job getJobById(int jobId);
}
