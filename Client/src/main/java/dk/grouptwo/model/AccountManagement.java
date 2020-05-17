package dk.grouptwo.model;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.License;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.utility.PropertyChangeSubject;

import java.util.ArrayList;

public interface AccountManagement extends PropertyChangeSubject {

    public void registerAccountWorker(Worker worker, String password, String passwordConfirmation) throws Exception;

    public void logInWorker(String CPR, String password) throws Exception;

    public void registerAccountEmployer(Employer employer, String password, String passwordConfirmation) throws Exception;

    public void logInEmployer(String CVR, String password) throws Exception;

    public void editEmployer(Employer employer, String password) throws Exception;

    public void editEmployer(Employer employer, String password, String newPassword, String newPasswordConfirm) throws Exception;

    public void editWorker(Worker worker, String password) throws Exception;

    public void editWorker(Worker worker, String password, String newPassword, String newPasswordConfirm) throws Exception;

    public void logOutWorker();

    public void logOutEmployer();

    public Employer getEmployer();

    ArrayList<License> getLicenses();

    Worker getWorker();

    void deleteLicense(String licenseNumber) throws Exception;

    void addLicense(License license) throws Exception;
}
