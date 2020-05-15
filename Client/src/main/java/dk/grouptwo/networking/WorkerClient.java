package dk.grouptwo.networking;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.networking.remote.RemoteWorkerClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WorkerClient implements RemoteWorkerClient {
    private ModelManager model;

    public WorkerClient(ModelManager model) throws RemoteException {
        this.model = model;
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public void addJob(Job job) {

    }

    @Override
    public void moveToUpcoming(Job job) {

    }

    @Override
    public void moveToHistory(Job job) {

    }
}
