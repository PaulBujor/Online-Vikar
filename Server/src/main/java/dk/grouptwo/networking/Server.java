package dk.grouptwo.networking;

import dk.grouptwo.database.Database;
import dk.grouptwo.database.Persistence;
import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.License;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.networking.remote.RemoteEmployerClient;
import dk.grouptwo.networking.remote.RemoteServer;
import dk.grouptwo.networking.remote.RemoteWorkerClient;

import java.lang.reflect.Array;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Server implements RemoteServer {
    private ArrayList<RemoteWorkerClient> clients;
    private ArrayList<Job> jobs;
    private Persistence persistence;

    //used to save references to Employer which owns the Job
    private Map<Job, RemoteEmployerClient> jobMap = new HashMap<Job, RemoteEmployerClient>();

    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        clients = new ArrayList<RemoteWorkerClient>();
        jobs = new ArrayList<Job>();
        persistence = new Database();
    }

    @Override
    public void registerWorkerClient(RemoteWorkerClient client) throws RemoteException {
        clients.add(client);
    }

    public void registerEmployerClient(RemoteEmployerClient client, ArrayList<Job> jobs) throws RemoteException {
        new Thread(() -> {
            for (Job job : jobs) {
                jobMap.put(job, client);
            }
            jobs.addAll(jobs);
        }).start();
    }

    @Override
    public void addJob(Job job, RemoteEmployerClient employerClient) throws RemoteException {
        new Thread(() -> {
            persistence.addJobToDB(job);
            job.setJobID(persistence.getJobID(job));
            jobs.add(job);
            jobMap.put(job, employerClient);
            for (RemoteWorkerClient client : clients) {
                try {
                    client.addJob(job);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void removeJob(Job job) throws RemoteException {
        new Thread(() -> {
            jobs.remove(job);
            persistence.removeJobFromDB(job);
            for (RemoteWorkerClient client : clients) {
                try {
                    client.removeJob(job);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public Employer loginEmployer(String CVR, String password) throws RemoteException {

        Employer employer = null;
        try {
            employer = persistence.loginEmployer(CVR, password);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
        return employer;
    }

    @Override
    public Worker loginWorker(String CPR, String password) throws RemoteException {
        Worker worker = null;
        try {
            worker = persistence.loginWorker(CPR, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException(e.getMessage());
        }
        return worker;
    }

    @Override
    public void applyForJob(Job job, Worker worker) throws RemoteException {
        new Thread(() -> {
            RemoteEmployerClient client = jobMap.remove(job);
            persistence.applyForJob(job, worker);
            job.addApplicant(worker);
            jobMap.put(job, client);
            try {
                client.updateJob(job);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            for (RemoteWorkerClient callback : clients) {
                try {
                    callback.updateJob(job);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void cancelWorkerFromJob(Job job, Worker worker) {
        new Thread(() -> {
            RemoteEmployerClient client = jobMap.get(job);
            jobMap.remove(job);
            persistence.cancelWorkerFromJob(job, worker);
            job.removeWorker(worker);
            job.removeApplicant(worker);
            jobMap.put(job, client);
            try {
                client.updateJob(job);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            for (RemoteWorkerClient callback : clients) {
                try {
                    callback.updateJob(job);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void updateJob(Job job) throws RemoteException {
        new Thread(() -> {
            RemoteEmployerClient employer = jobMap.get(job);
            jobMap.remove(job);
            jobMap.put(job, employer);
            persistence.updateJob(job);

            //update in database
            ArrayList<Worker> selectedWorkers = persistence.getAllAcceptedWorkers(job);
            for (Worker worker : job.getSelectedWorkers()) {
                if (!selectedWorkers.contains(worker)) {
                    persistence.addSelectedWorker(job, worker);
                }
            }
            for (Worker worker : selectedWorkers) {
                if (!job.getSelectedWorkers().contains(worker)) {
                    persistence.removeSelectedWorker(job, worker);
                }
            }

            for (RemoteWorkerClient client : clients) {
                try {
                    client.updateJob(job);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void createEmployerAccount(Employer employer, String password) throws RemoteException {
        try {
            persistence.createEmployerAccount(employer, password);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void createWorkerAccount(Worker worker, String password) throws RemoteException {
        try {
            persistence.createWorkerAccount(worker, password);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Job> getJobs() throws RemoteException {
        return persistence.getAllJobsFromDB();
    }

    @Override
    public ArrayList<Job> getWorkerJobHistory(Worker worker) throws RemoteException {
        return persistence.getAllJobHistoryWorkerFromDB(worker);
    }

    @Override
    public ArrayList<Job> getEmployerJobHistory(Employer employer) throws RemoteException {
        return persistence.getAllJobHistoryEmployerFromDB(employer);
    }

    @Override
    public ArrayList<Job> getUpcomingJobs(Worker worker) throws RemoteException {
        return persistence.getUpcomingJobsWorkerFromDB(worker);
    }

    @Override
    public ArrayList<Job> getEmployerJobs(Employer employer) throws RemoteException {
        return persistence.getCurrentEmployerJobs(employer);
    }

    @Override
    public void editEmployer(Employer employer, String password) throws RemoteException {
        try {
            persistence.editEmployer(employer, password);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void editEmployer(Employer employer, String password, String newPassword) throws RemoteException, NoSuchAlgorithmException {
        try {
            persistence.editEmployer(employer, password, newPassword);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void editWorker(Worker worker, String password) throws RemoteException {
        try {
            persistence.editWorker(worker, password);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void editWorker(Worker worker, String password, String newPassword) throws RemoteException, NoSuchAlgorithmException {
        try {
            persistence.editWorker(worker, password, newPassword);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void addLicense(License license, Worker worker) throws RemoteException {
        try {
            persistence.addLicense(license, worker);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void removeLicense(License license) throws RemoteException {
        try {
            persistence.removeLicense(license);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }
}