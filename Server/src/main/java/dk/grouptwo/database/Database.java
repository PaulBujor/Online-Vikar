package dk.grouptwo.database;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;

import java.util.ArrayList;

public class Database implements Persistence
{
  public Database(){

  }

  @Override public void addJobToDB(Job job)
  {

  }

  @Override public void removeJobFromDB(Job job)
  {

  }

  @Override public void applyForJob()
  {

  }

  @Override public void updateJob()
  {

  }

  @Override public Employer loginEmployer(String CVR, String password)
  {
    return null;
  }

  @Override public Worker loginWorker(String CPR, String password)
  {
    return null;
  }

  @Override public void createEmployerAccount(Employer employer,
      String password)
  {

  }

  @Override public void createWorkerAccount(Worker worker, String password)
  {

  }

  @Override public ArrayList<Job> getAllJobsFromDB()
  {
    return null;
  }

  @Override public ArrayList<Job> getAllJobHistoryWorkerFromDB(Worker worker)
  {
    return null;
  }

  @Override public ArrayList<Job> getAllJobHistoryEmployerFromDB(
      Employer employer)
  {
    return null;
  }

  @Override public ArrayList<Job> getUpcomingJobsWorkerFromDB(Worker worker)
  {
    return null;
  }

  @Override public ArrayList<Job> getCurrentEmployerJobs(Employer employer)
  {
    return null;
  }

  @Override public ArrayList<Worker> getAllAppliedWorkers()
  {
    return null;
  }
}
