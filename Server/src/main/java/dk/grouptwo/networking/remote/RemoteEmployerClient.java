package dk.grouptwo.networking.remote;

import dk.grouptwo.model.objects.Job;

import java.io.Serializable;
import java.rmi.Remote;

public interface RemoteEmployerClient extends Remote, Serializable {
    public void updateJob(Job job);
}
