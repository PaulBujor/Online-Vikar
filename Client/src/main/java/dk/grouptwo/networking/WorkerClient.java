package dk.grouptwo.networking;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.networking.remote.RemoteWorkerClient;
import dk.grouptwo.utility.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WorkerClient implements RemoteWorkerClient, PropertyChangeSubject {
    private PropertyChangeSupport property;

    public WorkerClient() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        property = new PropertyChangeSupport(this);
    }

    @Override
    public void addJob(Job job) {
        property.firePropertyChange("addJob", 0, job);
    }

    @Override
    public void updateJob(Job job) {
        property.firePropertyChange("updateJob", 0, job);
    }

    @Override
    public void removeJob(Job job) {
        property.firePropertyChange("removeJob", 0, job);
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);

    }

    @Override
    public void addListener(String eventID, PropertyChangeListener listener) {
        property.addPropertyChangeListener(eventID, listener);
    }

    @Override
    public void removeListener(String eventID, PropertyChangeListener listener) {
        property.removePropertyChangeListener(eventID, listener);
    }
}
