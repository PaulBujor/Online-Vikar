package dk.grouptwo.model;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;

import java.util.ArrayList;

public interface AccountManagement {

    public void registerAccountWorker(Worker worker, String password) throws Exception;

    public void logInWorker(String CPR, String password) throws Exception;

    public void registerAccountEmployer(Employer employer, String password) throws Exception;

    public void logInEmployer(String CVR, String password) throws Exception;

    public void editEmployer(Employer employer, String password) throws Exception;

    public void editEmployer(Employer employer, String password, String newPassword) throws Exception;

    public void editWorker(Worker worker, String password);

    public void logOutWorker();

    public void logOutEmployer();

    public ArrayList<Job> getJobHistory();

    public Employer getEmployer();

}
