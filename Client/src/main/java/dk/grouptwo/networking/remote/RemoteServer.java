package dk.grouptwo.networking.remote;

import dk.grouptwo.model.objects.Job;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServer extends Remote
{

  //This should register workers to get updates for job changes
  void registerClient(RemoteClient clientToRegister) throws RemoteException;

  void addJob(Job job) throws RemoteException;
  void removeJob(Job job) throws RemoteException;



}
