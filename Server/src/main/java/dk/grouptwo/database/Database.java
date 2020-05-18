package dk.grouptwo.database;

import dk.grouptwo.model.objects.*;
import org.apache.commons.dbutils.DbUtils;

import javax.print.DocFlavor;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database implements Persistence
{
  public Database()
  {

  }

  public void close(ResultSet rs, Statement statement, Connection connection)
  {
    if (rs != null)
    {
      DbUtils.closeQuietly(rs);
    }
    DbUtils.closeQuietly(statement);
    DbUtils.closeQuietly(connection);
  }

  @Override public synchronized void addJobToDB(Job job)
  {
    Connection conn = null;
    String SQL =
        "INSERT INTO job(jobtitle,description,salary,workersneeded,shiftstart,shiftend,status,cvr,address"
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    PreparedStatement posted = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      posted = conn.prepareStatement(SQL);
      posted.setString(1, job.getJobTitle());
      posted.setString(2, job.getDescription());
      posted.setDouble(3, job.getSalary());
      posted.setInt(4, job.getWorkersNeeded());
      posted.setTimestamp(5, Timestamp.valueOf(job.getShiftStart()));
      posted.setTimestamp(6, Timestamp.valueOf(job.getShiftEnd()));
      posted.setString(7, job.getStatus());
      posted.setString(8, job.getEmployer().getCVR());
      posted.setInt(9, insertAddress(job.getLocation()));
      posted.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, posted, conn);
    }
  }

  public Employer getEmployer(String cvr)
  {
    Employer tmpEmployer = new Employer(null, null, null, null, null);
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      String SQL = "SELECT * FROM employer WHERE cvr=?";
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, cvr);
      rs = pstm.executeQuery();

      while (rs.next())
      {
        process(rs, tmpEmployer);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstm, conn);
    }
    return tmpEmployer;

  }

  public int getJobID(Job job)
  {
    String SQL = "SELECT jobID FROM job WHERE jobtitle=? AND description=? AND salary=? AND workersneeded=? AND shiftstart=? AND shiftend=? AND status=? AND cvr=? AND address=?";
    int id = 0;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      preparedStatement = conn.prepareStatement(SQL);
      preparedStatement.setString(1, job.getJobTitle());
      preparedStatement.setString(2, job.getDescription());
      preparedStatement.setDouble(3, job.getSalary());
      preparedStatement.setInt(4, job.getWorkersNeeded());
      preparedStatement.setTimestamp(5, Timestamp.valueOf(job.getShiftStart()));
      preparedStatement.setTimestamp(6, Timestamp.valueOf(job.getShiftEnd()));
      preparedStatement.setString(7, job.getStatus());
      preparedStatement.setString(8, job.getEmployer().getCVR());
      preparedStatement.setInt(9, insertAddress(job.getLocation()));
      rs = preparedStatement.executeQuery();
      while (rs.next())
      {
        id = rs.getInt("jobID");
      }

    }

    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, preparedStatement, conn);
    }
    return id;
  }

  @Override public void removeJobFromDB(Job job)
  {
    String SQL = "DELETE FROM job WHERE jobID=?";
    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setInt(1, getJobID(job));
      pstm.executeUpdate();

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, pstm, conn);
    }
  }

  @Override public void applyForJob(Job job, Worker worker)

  {
    String SQL = "INSERT INTO applied (cvr,jobID)" + "VALUES(?,?)";
    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, worker.getCPR());
      pstm.setInt(2, getJobID(job));
      pstm.execute();

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, pstm, conn);
    }
  }

  @Override public void updateJob(Job job)
  {
    String SQL = "UPDATE job SET jobtitle=?, description=?, salary=?, workersneeded=?, shiftstart=?, shiftend=?, status=?, address=? WHERE jobID=? ";

    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, job.getJobTitle());
      pstm.setString(2, job.getDescription());
      pstm.setDouble(3, job.getSalary());
      pstm.setInt(4, job.getWorkersNeeded());
      pstm.setTimestamp(5, Timestamp.valueOf(job.getShiftStart()));
      pstm.setTimestamp(6, Timestamp.valueOf(job.getShiftEnd()));
      pstm.setString(7, job.getStatus());
      pstm.setInt(8, insertAddress(job.getLocation()));
      pstm.setInt(9, getJobID(job));
      pstm.executeUpdate();

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, pstm, conn);
    }
  }

  @Override public Employer loginEmployer(String CVR, String password)
      throws Exception
  {
    Employer tmpEmployer = new Employer(null, null, null, null, null);
    System.out.println(password);
    String SQL = "SELECT * FROM employer WHERE cvr=? AND password=?";
    ResultSet rs = null;
    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, CVR);
      pstm.setString(2, password);
      rs = pstm.executeQuery();
      System.out.println(rs);
      while (rs.next())
      {
        process(rs, tmpEmployer);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstm, conn);
    }
    System.out.println(tmpEmployer.getCVR());
    if (tmpEmployer.getCVR().equals(""))
    {
      throw new Exception("Account not found");
    }
    else
      return tmpEmployer;

  }

  @Override public Worker loginWorker(String CPR, String password)
      throws Exception
  {
    Worker tmpWorker = new Worker(null, null, null, null, null, null, null,
        null, null, null, null);

    String SQL = "SELECT * FROM worker WHERE cpr=? AND password=?";
    ResultSet rs = null;
    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, CPR);
      pstm.setString(2, password);
      rs = pstm.executeQuery();
      while (rs.next())
      {
        process(rs, tmpWorker);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstm, conn);
    }
    if (tmpWorker.getCPR() == null)
    {
      throw new Exception("Account not found");
    }
    else
      return tmpWorker;
  }

  @Override public void createEmployerAccount(Employer employer,
      String password) throws SQLException
  {

    String SQL =
        "INSERT INTO employer(cvr,password,companyname,email,phone,address)"

            + "VALUES(?,?,?,?,?,?)";
    PreparedStatement posted = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      posted = conn.prepareStatement(SQL);
      posted.setString(1, employer.getCVR());
      posted.setString(2, password);
      posted.setString(3, employer.getCompanyName());
      posted.setString(4, employer.getEmail());
      posted.setString(5, employer.getPhone());
      posted.setInt(6, insertAddress(employer.getAddress()));
      posted.executeUpdate();

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, posted, conn);
    }
  }

  @Override public void createWorkerAccount(Worker worker, String password)
  {

    String SQL =
        "INSERT INTO worker(cpr,password,firstname,lastname,taxcard,email,phone,languages,description,address,birthday,gender)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement posted = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      posted = conn.prepareStatement(SQL);
      posted.setString(1, worker.getCPR());
      posted.setString(2, password);
      posted.setString(3, worker.getFirstName());
      posted.setString(4, worker.getLastName());
      posted.setString(5, worker.getTaxCard());
      posted.setString(6, worker.getEmail());
      posted.setString(7, worker.getPhone());
      posted.setString(8, worker.getLanguages());
      posted.setString(9, worker.getDescription());
      posted.setInt(10, insertAddress(worker.getAddress()));
      posted.setDate(11, Date.valueOf(worker.getBirthday()));
      posted.setString(12, worker.getGender());
      posted.execute();

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, posted, conn);
    }
  }

  @Override public ArrayList<Job> getAllJobsFromDB()
  {
    ArrayList<Job> jobs = new ArrayList<>();
    String SQL = "SELECT * FROM job";
    ResultSet rs = null;
    Statement stmt = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(SQL);
      while (rs.next())
      {
        Job tmpJob = new Job(0, null, null, 0, 0, null, null, null, null, null);
        process(rs, tmpJob);
        jobs.add(tmpJob);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, stmt, conn);
    }
    return jobs;
  }

  @Override public ArrayList<Job> getAllJobHistoryWorkerFromDB(Worker worker)
  {

    ArrayList<Job> jobs = new ArrayList<>();
    String SQL = "SELECT * jobs INNER JOIN works ON jobs.jobID = works.jobID WHERE works.cpr=? AND job.status='completed'";
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, worker.getCPR());
      rs = pstmt.executeQuery();

      while (rs.next())
      {
        Job tmpJob = new Job(0, null, null, 0, 0, null, null, null, null, null);
        process(rs, tmpJob);
        jobs.add(tmpJob);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstmt, conn);
    }
    return jobs;
  }

  @Override public ArrayList<Job> getAllJobHistoryEmployerFromDB(
      Employer employer)
  {
    ArrayList<Job> jobs = new ArrayList<>();
    String SQL = "SELECT * FROM job WHERE cvr=? AND status='completed' OR status='cancelled'";
    ResultSet rs = null;
    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, employer.getCVR());
      rs = pstm.executeQuery();
      while (rs.next())
      {
        Job tmpJob = new Job(0, null, null, 0, 0, null, null, null, null, null);
        process(rs, tmpJob);
        jobs.add(tmpJob);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstm, conn);
    }
    return jobs;
  }

  @Override public ArrayList<Job> getUpcomingJobsWorkerFromDB(Worker worker)
  {

    ArrayList<Job> jobs = new ArrayList<>();
    String SQL = "SELECT * jobs INNER JOIN works ON jobs.jobID = works.jobID WHERE works.cpr=? AND job.status='pending'";

    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, worker.getCPR());
      rs = pstmt.executeQuery();

      while (rs.next())
      {
        Job tmpJob = new Job(0, null, null, 0, 0, null, null, null, null, null);
        process(rs, tmpJob);
        jobs.add(tmpJob);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstmt, conn);
    }
    return jobs;
  }

  @Override public ArrayList<Job> getCurrentEmployerJobs(Employer employer)
  {
    ArrayList<Job> jobs = new ArrayList<>();
    String SQL = "SELECT * FROM job WHERE cvr=? AND status='pending' OR status='created'";
    ResultSet rs = null;
    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, employer.getCVR());
      rs = pstm.executeQuery();
      while (rs.next())
      {
        Job tmpJob = new Job(0, null, null, 0, 0, null, null, null, null, null);
        process(rs, tmpJob);
        jobs.add(tmpJob);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstm, conn);
    }
    return jobs;
  }

  @Override public ArrayList<Worker> getAllAppliedWorkers(Job job)
  {
    ArrayList<Worker> workers = new ArrayList<>();

    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();

      //TODO might need to change SQL
      String SQL = "Select * FROM worker WHERE ID IN (SELECT cpr FROM applied WHERE jobID =? )";
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1, getJobID(job));
      rs = pstmt.executeQuery();
      while (rs.next())
      {
        //TODO subject to change
        Worker tmpWorker = new Worker(null, null, null, null, null, null, null,
            null, null, null, null);
        process(rs, tmpWorker);
        workers.add(tmpWorker);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstmt, conn);
    }
    return workers;
  }

  @Override public ArrayList<Worker> getAllAcceptedWorkers(Job job)
  {
    ArrayList<Worker> workers = new ArrayList<>();

    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();

      //TODO might need to change SQL
      String SQL = "Select * FROM worker WHERE cpr IN (SELECT cpr FROM works WHERE jobID =? )";
      pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1, getJobID(job));
      rs = pstmt.executeQuery();
      while (rs.next())
      {
        //TODO subject to change
        Worker tmpWorker = new Worker(null, null, null, null, null, null, null,
            null, null, null, null);
        process(rs, tmpWorker);
        workers.add(tmpWorker);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstmt, conn);
    }
    return workers;
  }

  @Override public void addLicense(License license, Worker worker)
  {
    String SQL =
        "INSERT INTO licence(cpr,licensenumber,typee,category,issuedate,expirydate"
            + "VALUE(?,?,?,?,?,?)";

    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, worker.getCPR());
      pstm.setString(2, license.getLicenseNumber());
      pstm.setString(3, license.getType());
      pstm.setString(4, license.getCategory());
      pstm.setDate(5, Date.valueOf(license.getIssueDate()));
      pstm.setDate(6, Date.valueOf(license.getExpiryDate()));
      pstm.execute();

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, pstm, conn);
    }
  }

  @Override public void removeLicense(License license)
  {
    String SQL = "DELETE FROM licence where licensenumber=?";
    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, license.getLicenseNumber());
      pstm.execute();

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, pstm, conn);
    }
  }

  @Override public int insertAddress(Address address)
  {
    if (!getAllAddress().contains(address))
    {
      String SQL =
          "INSERT INTO address(country,city,street,zip)" + "VALUES(?,?,?,?)";
      PreparedStatement posted = null;
      Connection conn = null;
      try
      {
        conn = DatabaseConnection.getInstance().connect();
        posted = conn.prepareStatement(SQL);
        posted.setString(1, address.getCountry());
        posted.setString(2, address.getCity());
        posted.setString(3, address.getStreet());
        posted.setString(4, address.getZip());
        posted.execute();

      }
      catch (SQLException e)
      {

        e.printStackTrace();
      }
      finally
      {
        close(null, posted, conn);
      }
    }
    String SQL = "SELECT addressID from address WHERE country=? AND city=? AND street=? AND zip=?"/*+"VALUES(?,?,?,?)"*/;
    int id = 0;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      preparedStatement = conn.prepareStatement(SQL);
      preparedStatement.setString(1, address.getCountry());
      preparedStatement.setString(2, address.getCity());
      preparedStatement.setString(3, address.getStreet());
      preparedStatement.setString(4, address.getZip());
      rs = preparedStatement.executeQuery();
      while (rs.next())
      {
        id = rs.getInt("addressID");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, preparedStatement, conn);
    }
    return id;
  }

  public Address getAddressByID(int id)
  {
    Address tmpAddress = new Address(null, null, null, null);
    String SQL = "SELECT * FROM address WHERE addressid=?";
    ResultSet rs = null;
    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setInt(1, id);
      rs = pstm.executeQuery();
      while (rs.next())
      {
        process(rs, tmpAddress);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstm, conn);
    }
    return tmpAddress;
  }

  public ArrayList<Address> getAllAddress()
  {
    ArrayList<Address> addresses = new ArrayList<>();
    ResultSet rs = null;
    Statement stmt = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      stmt = conn.createStatement();
      String SQL = "Select * FROM address";
      rs = stmt.executeQuery(SQL);

      while (rs.next())
      {
        Address tmpAddress = new Address(null,null,null,null);
        process(rs, tmpAddress);
        addresses.add(tmpAddress);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, stmt, conn);
    }
    System.out.println(addresses.toString());
    return addresses;
  }

  @Override public void cancelWorkerFromJob(Job job, Worker worker)
  {
    String SQL = "DELETE FROM works where cpr=? AND jobID=?";
    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, worker.getCPR());
      pstm.setInt(2, getJobID(job));
      pstm.execute();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, pstm, conn);
    }

    String SQL1 = "DELETE FROM applied where cpr=? AND jobID=?";
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL1);
      pstm.setString(1, worker.getCPR());
      pstm.setInt(2, getJobID(job));
      pstm.execute();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, pstm, conn);
    }
  }

  public boolean employerPasswordCheck(Employer employer, String password)
  {
    String SQL = "SELECT * FROM employer WHERE cvr=? AND password=?";
    Employer tmpEmployer = new Employer(null, null, null, null, null);
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, employer.getCVR());
      pstmt.setString(2, password);
      rs = pstmt.executeQuery();

      while (rs.next())
      {

        process(rs, tmpEmployer);
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstmt, conn);
    }
    if (tmpEmployer.getCVR() == null)
    {
      return false;
    }
    else
      return true;
  }

  public boolean workerPasswordCheck(Worker worker, String password)
  {
    String SQL = "SELECT * FROM worker WHERE cpr=? AND password=?";
    Worker tmpWorker = new Worker(null, null, null, null, null, null, null,
        null, null, null, null);

    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, worker.getCPR());
      pstmt.setString(2, password);
      rs = pstmt.executeQuery();

      while (rs.next())
      {
        process(rs, tmpWorker);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstmt, conn);
    }
    if (tmpWorker.getCPR() == null)
    {
      return false;
    }
    else
      return true;
  }

  @Override public void editEmployer(Employer employer, String password)
      throws Exception
  {
    if (employerPasswordCheck(employer, password))
    {

      PreparedStatement pstmt = null;
      Connection conn = null;
      try
      {
        String SQL = "UPDATE employer SET companyname=?, email=?, phone=?, address=? WHERE cvr=?";
        conn = DatabaseConnection.getInstance().connect();
        pstmt = conn.prepareStatement(SQL);
        pstmt.setString(1, employer.getCompanyName());
        pstmt.setString(2, employer.getEmail());
        pstmt.setString(3, employer.getPhone());
        pstmt.setInt(4, insertAddress(employer.getAddress()));
        pstmt.setString(5, employer.getCVR());
        pstmt.executeUpdate();
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      finally
      {
        close(null, pstmt, conn);
      }
    }
    else
      throw new Exception("Password does not match the current one.");
  }

  @Override public void editEmployer(Employer employer, String password,
      String newPassword) throws Exception
  {
    editEmployer(employer, password);
    PreparedStatement pstmt = null;
    Connection conn = null;
    try
    {

      String SQL = "UPDATE employer SET  password=? WHERE cvr=?";
      conn = DatabaseConnection.getInstance().connect();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, newPassword);
      pstmt.setString(2, employer.getCVR());
      pstmt.executeUpdate();

    }

    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, pstmt, conn);
    }

  }

  @Override public void editWorker(Worker worker, String password)
      throws Exception
  {
    if (workerPasswordCheck(worker, password))
    {

      PreparedStatement pstmt = null;
      Connection conn = null;
      try
      {
        String SQL = "UPDATE worker SET firstname=?, lastname=?, taxcard=?, gender=?, email=?, phone=?, languages=?, description=?, address=?, birthday=? WHERE cpr=? ";
        conn = DatabaseConnection.getInstance().connect();
        pstmt = conn.prepareStatement(SQL);
        pstmt.setString(1, worker.getFirstName());
        pstmt.setString(2, worker.getLastName());
        pstmt.setString(3, worker.getTaxCard());
        pstmt.setString(4, worker.getGender());
        pstmt.setString(5, worker.getEmail());
        pstmt.setString(6, worker.getPhone());
        pstmt.setString(7, worker.getLanguages());
        pstmt.setString(8, worker.getDescription());
        pstmt.setInt(9, insertAddress(worker.getAddress()));
        pstmt.setDate(10, Date.valueOf(worker.getBirthday()));
        pstmt.setString(11, worker.getCPR());
        pstmt.executeUpdate();
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      finally
      {
        close(null, pstmt, conn);
      }
    }
    else
      throw new Exception("Password does not match the current one.");
  }

  @Override public void editWorker(Worker worker, String password,
      String newPassword) throws Exception
  {
    editWorker(worker, password);
    PreparedStatement pstmt = null;
    Connection conn = null;
    try
    {

      String SQL = "UPDATE worker SET password=? WHERE cpr=? ";
      conn = DatabaseConnection.getInstance().connect();
      pstmt = conn.prepareStatement(SQL);
      pstmt.setString(1, newPassword);
      pstmt.setString(2, worker.getCPR());
      pstmt.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, pstmt, conn);
    }

  }

  @Override public void addSelectedWorker(Job job, Worker worker)
  {
    String SQL = "INSERT INTO works (cvr,jobID)" + "VALUES(?,?)";
    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, worker.getCPR());
      pstm.setInt(2, getJobID(job));
      pstm.execute();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, pstm, conn);
    }
  }

  @Override public void removeSelectedWorker(Job job, Worker worker)
  {
    String SQL = "DELETE from works where cpr=? AND jobID=?";
    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, worker.getCPR());
      pstm.setInt(2, getJobID(job));
      pstm.execute();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(null, pstm, conn);
    }
  }

  public ArrayList<License> getAllLicencesByCPR(String cpr)
  {
    ArrayList<License> licenses = new ArrayList<>();
    ResultSet rs = null;
    PreparedStatement pstm = null;
    Connection conn = null;
    try
    {
      String SQL = "SELECT * license where cpr=?";
      conn = DatabaseConnection.getInstance().connect();
      pstm = conn.prepareStatement(SQL);
      pstm.setString(1, cpr);
      rs = pstm.executeQuery();

      while (rs.next())
      {
        License tmpLicense = new License(null, null, null, null, null);
        process(rs, tmpLicense);
        licenses.add(tmpLicense);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      close(rs, pstm, conn);
    }
    return licenses;
  }

  private void process(ResultSet rs, Address address) throws SQLException
  {
    address.setCountry(rs.getString("country"));
    address.setCity(rs.getString("city"));
    address.setStreet(rs.getString("street"));
    address.setZip(rs.getString("zip"));

  }

  private void process(ResultSet rs, Worker worker) throws SQLException
  {
    worker.setCPR(rs.getString("cpr"));
    worker.setFirstName(rs.getString("firstname"));
    worker.setLastName(rs.getString("lastname"));
    worker.setTaxCard(rs.getString("taxcard"));
    worker.setEmail(rs.getString("email"));
    worker.setPhone(rs.getString("phone"));
    worker.setLanguages(rs.getString("languages"));
    worker.setDescription(rs.getString("description"));
    worker.setAddress(getAddressByID(rs.getInt("address")));
    worker.setLicenses(getAllLicencesByCPR(rs.getString("cpr")));
    worker.setGender(rs.getString("gender"));
    worker.setBirthday(rs.getDate("birthday").toLocalDate());
  }

  private void process(ResultSet rs, License license) throws SQLException
  {
    license.setLicenseNumber(rs.getString("licensenumber"));
    license.setType(rs.getString("typee"));
    license.setCategory(rs.getString("category"));
    license.setIssueDate(rs.getDate("issuedate").toLocalDate());
    license.setExpiryDate(rs.getDate("expirydate").toLocalDate());
  }

  private void process(ResultSet rs, Employer employer) throws SQLException
  {
    employer.setCVR(rs.getString("cvr"));
    employer.setCompanyName(rs.getString("companyname"));
    employer.setEmail(rs.getString("email"));
    employer.setPhone(rs.getString("phone"));
    employer.setAddress(getAddressByID(rs.getInt("address")));
  }

  private void process(ResultSet rs, Job job) throws SQLException
  {

    job.setJobID(rs.getInt("jobID"));
    job.setJobTitle(rs.getString("jobtitle"));
    job.setDescription(rs.getString("description"));
    job.setSalary(rs.getDouble("salary"));
    job.setWorkersNeeded(rs.getInt("workersneed"));
    job.setShiftStart(rs.getTimestamp("shiftstart").toLocalDateTime());
    job.setShiftEnd(rs.getTimestamp("shiftend").toLocalDateTime());
    job.setStatus(rs.getString("status"));
    job.setEmployer(getEmployer(rs.getString("cvr")));
    job.setLocation(getAddressByID(rs.getInt("address")));
  }

}
