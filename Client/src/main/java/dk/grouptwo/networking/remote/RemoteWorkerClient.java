package dk.grouptwo.networking.remote;

import dk.grouptwo.model.objects.Job;

import java.io.Serializable;
import java.rmi.Remote;

public interface RemoteWorkerClient extends Remote, Serializable {
    public void addJob(Job job);

    public void updateJob(Job job);

    public void removeJob(Job job);
}

