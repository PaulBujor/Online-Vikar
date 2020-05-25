package dk.grouptwo.networking.remote;

import dk.grouptwo.model.objects.Job;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteWorkerClient extends Remote, Serializable {
    public void addJob(Job job) throws RemoteException;

    public void updateJob(Job job) throws RemoteException;
}

