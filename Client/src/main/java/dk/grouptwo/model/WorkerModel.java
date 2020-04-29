package dk.grouptwo.model;

import dk.grouptwo.model.objects.License;
import dk.grouptwo.model.objects.Worker;


import java.util.ArrayList;

public interface WorkerModel {

    public void applyForJob(Worker worker);

    public void addLicense(License license);

    public void deleteLicense(License license);


}
