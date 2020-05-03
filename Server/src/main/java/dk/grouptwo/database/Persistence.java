package dk.grouptwo.database;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;

import java.util.ArrayList;

public interface Persistence
{
 //Adding removing, applying for jobs*******
  void addJobToDB(Job job);
  void removeJobFromDB(Job job);

  // maybe Worker instead of void
  Worker applyForJob(Worker worker);
  void updateJob();

 //Login checks
 Employer loginEmployer(String CVR,String password);
 Worker loginWorker(String CPR,String password);

 //Creating accounts**** //TODO needs testing
  void createEmployerAccount(Employer employer, String password);
  void createWorkerAccount(Worker worker,String password);

  ArrayList<Job> getAllJobsFromDB();

  ArrayList<Job> getAllJobHistoryWorkerFromDB(Worker worker);
  ArrayList<Job> getAllJobHistoryEmployerFromDB(Employer employer);
  ArrayList<Job> getUpcomingJobsWorkerFromDB(Worker worker);
  ArrayList<Job> getCurrentEmployerJobs(Employer employer);

  ArrayList<Worker> getAllAppliedWorkers();

}
