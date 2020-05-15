package dk.grouptwo.networking.remote;

import dk.grouptwo.model.objects.Job;

import java.rmi.Remote;

public interface RemoteEmployerClient extends Remote {
    public void updateJob(Job job);
}
