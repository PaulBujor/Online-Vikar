package dk.grouptwo.networking;

import dk.grouptwo.database.Persistence;
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
    private ArrayList<Job> jobs;
    private Worker worker;
    private Employer employer;
    private Persistence db;

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
            jobs.add(job);
            db.addJobToDB(job);
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
    public void removeJob(Job job, RemoteClient client) throws RemoteException {
        try {
            jobs.remove(job);
            db.removeJobFromDB(job);
            client.removeJob(job);
            System.out.println(job + " removed");
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
        try {
            for (RemoteClient client : clients) {
                client.loginWorker(CPR,password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employer editEmployer(Employer employer, String password) throws RemoteException {
        try {
            for (RemoteClient client : clients) {
                client.editEmployer(employer,password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employer;
    }

    @Override
    public Worker editWorker(Worker worker, String password) throws RemoteException {
        try {
            for (RemoteClient client : clients) {
                client.editWorker(worker,password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public void applyForJob(Job job, Worker worker) throws RemoteException {
        try {
            for (RemoteClient client : clients) {
                db.applyForJob();
                client.applyForAJob(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateJob(Job job) throws RemoteException {
        try {
            for (RemoteClient client : clients) {
                db.updateJob();
                client.updateJob(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createEmployerAccount(Employer employer, String password, RemoteClient client) throws RemoteException {
        try {
            client.createEmployerAccount(employer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createWorkerAccount(Worker worker, String password, RemoteClient client) throws RemoteException {
        try {
            client.createWorkerAccount(worker);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Job> getAllJobsFromDB() throws RemoteException {
        return db.getAllJobsFromDB();
    }

    @Override
    public ArrayList<Job> getAllJobHistoryWorkerFromDB() throws RemoteException {
        return db.getAllJobHistoryWorkerFromDB(worker);
    }

    @Override
    public ArrayList<Job> getAllJobHistoryEmployerFromDB() throws RemoteException {
        return db.getAllJobHistoryEmployerFromDB(employer);
    }

    @Override
    public ArrayList<Job> getUpcomingJobsWorkerFromDB() throws RemoteException {
        return db.getUpcomingJobsWorkerFromDB(worker);
    }

    @Override
    public ArrayList<Job> getEmployerJobs(Employer employer) throws RemoteException {
        return db.getCurrentEmployerJobs(employer);
    }
}