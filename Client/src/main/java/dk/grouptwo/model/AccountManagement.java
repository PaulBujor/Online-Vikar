package dk.grouptwo.model;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Worker;

public interface AccountManagement {

    public void registerAccountWorker(Worker worker, String password);

    public void logInWorker(String CPR, String password) throws Exception;

    public void registerAccountEmployer(Employer employer, String password) throws Exception;

    public void logInEmployer(String CVR, String password) throws Exception;

    public void editEmployer(Employer employer, String password);

    public void editWorker(Worker worker, String password);

    public void logOutWorker();

    public void logOutEmployer();


}
