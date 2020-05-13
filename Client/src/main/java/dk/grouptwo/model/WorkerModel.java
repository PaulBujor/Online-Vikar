package dk.grouptwo.model;

import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.License;
import dk.grouptwo.model.objects.Worker;

import java.util.ArrayList;


public interface WorkerModel {

    void applyForJob(Worker worker);

    void addLicense(License license);

    void deleteLicense(String licenseNumber);

    ArrayList<License> getLicenses();

    double getHoursWorkedThisMonth();

    ArrayList<Job> getWorkerJobHistory();

}
