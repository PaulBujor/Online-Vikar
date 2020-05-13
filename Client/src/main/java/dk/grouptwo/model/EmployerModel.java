package dk.grouptwo.model;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;


public interface EmployerModel {

    public void createWorkOffer(Job job) throws Exception;

    public void cancelWorkOffer(Job job) throws Exception;

    public void updateWorkOffer(Job job) throws Exception;

    public Job getJobById(int jobId);

    public Employer getEmployer();

    ArrayList<Job> getEmployerJobHistory() throws Exception;

    ArrayList<Job> getEmployerJobs();

    Worker getWorkerByJob(int jobID, String CPR);
}
