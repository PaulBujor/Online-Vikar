package dk.grouptwo.model;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;


public interface EmployerModel {

    public void createWorkOffer(Job job);

    public void cancelWorkOffer(Job job);

    public void updateWorkOffer(Job job);

    public Job getJobById(int jobId);

    public Employer getEmployer();

    ArrayList<Job> getEmployerJobHistory();
}
