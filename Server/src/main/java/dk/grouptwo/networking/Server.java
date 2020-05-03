package dk.grouptwo.networking;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.networking.remote.RemoteClient;
import dk.grouptwo.networking.remote.RemoteServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements RemoteServer{
    private ArrayList<RemoteClient> clients;
    private Worker worker;
    private Employer employer;

    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this,0);
        //TODO sorry had to comment this out
        /*Naming.rebind("Job", this);*/
      /*  clients = new ArrayList<RemoteClient>();
        worker = null;
        employer = null;*/
    }

   /* public static String getIP() {
        try {
            URL aws = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    aws.openStream()));

            String ip = in.readLine();
            return ip;
        } catch (IOException e) {
            e.printStackTrace();
            return "Could not get IP";
        }
    }*/

    public void start() throws RemoteException, MalformedURLException {
        System.out.printf("Server IP: %s\n", "localhost"/*getIP()*/);
        UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind("Server", this); //permission error
    }

    @Override
    public void registerClient(RemoteClient clientToRegister) throws RemoteException {
        for (RemoteClient client : clients) {
            if(client.equals(employer)) {
                assert clientToRegister instanceof Employer;
                client.createEmployerAccount((Employer) clientToRegister); // password seems redundant
            }
            else if (client.equals(worker)){
                assert clientToRegister instanceof Worker;
                client.createWorkerAccount((Worker)clientToRegister);
            }
        }
    }

    @Override
    public void addJob(Job job, RemoteClient remoteClient) throws RemoteException {
        try {
            remoteClient = (RemoteClient) Naming.lookup("rmi://" + job + ":1099/Job");
            System.out.println(job + " added");
            clients.add(remoteClient);
            for (RemoteClient client : clients) {
                client.updateJob(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeJob(Job job, RemoteClient remoteClient) throws RemoteException {
        try {
            for (RemoteClient client : clients) {
                client.removeJob(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employer loginEmployer(String CVR, String password) throws RemoteException {
        try {
            for (RemoteClient client : clients) {
                client.loginEmployer(CVR,password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Worker loginWorker(String CPR, String password) throws RemoteException {
        return null;
    }

    @Override
    public Employer editEmployer(Employer employer, String password) throws RemoteException {
        return null;
    }

    @Override
    public Worker editWorker(Worker worker, String password) throws RemoteException {
        return null;
    }

    @Override
    public void applyForJob(Job job, RemoteClient client) throws RemoteException {

    }

    @Override
    public void updateJob(Job job, RemoteClient client) throws RemoteException {

    }

    @Override
    public void createEmployerAccount(Employer employer, String password, RemoteClient client) throws RemoteException {

    }

    @Override
    public void createWorkerAccount(Worker worker, String password, RemoteClient client) throws RemoteException {

    }

    @Override
    public ArrayList<Job> getAllJobsFromDB() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Job> getAllJobHistoryWorkerFromDB() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Job> getAllJobHistoryEmployerFromDB() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Job> getUpcomingJobsWorkerFromDB() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Job> getEmployerJobs(Employer employer) throws RemoteException {
        return null;
    }
}