package dk.grouptwo.model;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.utility.PropertyChangeSubject;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;


public interface EmployerModel extends PropertyChangeSubject {

    public void createWorkOffer(Job job) throws Exception;

    public void cancelWorkOffer(Job job) throws Exception;

    public void updateWorkOffer(Job job) throws Exception;

    public Job getJobById(int jobId);

    public Employer getEmployer();

    ArrayList<Job> getWorkHistory() throws Exception;

    ArrayList<Job> getEmployerJobs() throws Exception;

    Worker getWorkerByJob(int jobID, String CPR);
}
