package dk.grouptwo.database;

import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database implements Persistence
{
  public Database()
  {

  }

  @Override public synchronized void addJobToDB(Job job)
  {

  }

  @Override public void removeJobFromDB(Job job)
  {

  }

  @Override public Worker applyForJob(Worker worker)
  {
    return null;
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
      String password) throws SQLException
  {
    //TODO add adress to the mix
    String SQL =
        "INSERT INTO employer(cvr,password,companyname,email,phone)"

            + "VALUES(?,?,?,?,?)";
    try
    {
      Connection conn = DatabaseConnection.getInstance().connect();
      PreparedStatement posted = conn.prepareStatement(SQL);
      posted.setString(1, employer.getCVR());
      posted.setString(2, password);
      posted.setString(3, employer.getCompanyName());
      posted.setString(4, employer.getEmail());
      posted.setString(5, employer.getPhone());
    /*  posted.setString(6, "testing, should refer to actual address");*/
      posted.execute();
      posted.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
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

  @Override public void insertAddress(Address address)
  {
    String SQL = "INSERT INTO address(country,city,street,zip)" + "VALUES(?,?,?,?)";
    try{
      Connection conn = DatabaseConnection.getInstance().connect();
      PreparedStatement posted = conn.prepareStatement(SQL);
posted.setString(1,address.getCountry());
posted.setString(2,address.getCity());
posted.setString(3,address.getStreet());
posted.setInt(4, Integer.parseInt(address.getZip()));
posted.execute();
posted.close();
    }
    catch (SQLException e){
      e.printStackTrace();
    }
  }

  private void process(ResultSet rs, Address address) throws SQLException
  {
    address.setCountry(rs.getString("country"));
    address.setCity(rs.getString("city"));
    address.setStreet(rs.getString("street"));
    address.setZip(rs.getString("zip"));

  }
}
