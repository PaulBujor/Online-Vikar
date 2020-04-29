package dk.grouptwo.model;

import dk.grouptwo.model.objects.Job;


public interface EmployerModel {

    public void createWorkOffer(Job job);

    public void cancelWorkOffer(Job job);

    public void updateWorkOffer(Job job);


}
