package dk.grouptwo.networking.remote;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public interface RemoteClient extends Remote {
    void _addJob(Job job) throws RemoteException;

    void _removeJob(Job job) throws RemoteException;

    void _updateJob(Job job) throws RemoteException;
}
