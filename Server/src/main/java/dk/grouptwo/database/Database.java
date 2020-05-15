package dk.grouptwo.database;

import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;

import java.sql.*;
import java.util.ArrayList;

public class Database implements Persistence
{
  public Database()
  {

  }

  @Override public synchronized void addJobToDB(Job job)
  {
    String SQL =
        "INSERT INTO job(jobtitle,description,salary,workersneeded,shiftstart,shiftend,status,cvr,address"
            + "VALUES(?,?,?,?,?,?,?,?,?)";
   try{
     Connection conn= DatabaseConnection.getInstance().connect();
     PreparedStatement posted = conn.prepareStatement(SQL);
     posted.setString(1,job.getJobTitle());
     posted.setString(2,job.getDescription());
     posted.setDouble(3,job.getSalary());
     posted.setInt(4,job.getWorkersNeeded());
     posted.setTimestamp(5,Timestamp.valueOf(job.getShiftStart()));
     posted.setTimestamp(6,Timestamp.valueOf(job.getShiftEnd()));
     posted.setString(7,job.getStatus());
     posted.setString(8,job.getEmployer().getCVR());
     posted.setInt(9,insertAddress(job.getLocation()));

   }
   catch (SQLException e ){
     e.printStackTrace();
   }
  }
public Employer getEmployer(String cvr){
    Employer tmpEmployer = new Employer(null,null,null,null,null);
    try{
      Connection conn = DatabaseConnection.getInstance().connect();
      String SQL = "SELECT * FROM employer WHERE cvr=?";
      PreparedStatement pstm = conn.prepareStatement(SQL);
      pstm.setString(1,cvr);
      ResultSet rs = pstm.executeQuery();

      while(rs.next()){
        process(rs,tmpEmployer);
      }

    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return tmpEmployer;
}

public Employer getEmployer(Job job){
  Employer tmpEmployer = new Employer(null,null,null,getJobCVR(job),null);
    try{
      Connection conn= DatabaseConnection.getInstance().connect();
      String SQL = "SELECT * FROM employer WHERE cvr=?";
      PreparedStatement pstm =conn.prepareStatement(SQL);
      pstm.setString(1,getJobCVR(job));
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){

        process(rs,tmpEmployer);
      }
    }
    catch (SQLException e ){
      e.printStackTrace();
    }
    return tmpEmployer;
}
  public String getJobCVR(Job job){
    String SQL = "Select cvr FROM job WHERE jobID=?";
    String tmp = "";
    try{
      Connection conn = DatabaseConnection.getInstance().connect();
      PreparedStatement pstm = conn.prepareStatement(SQL);
      pstm.setInt(1,getJobID(job));
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        tmp = rs.getString("cvr");
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return tmp;
  }


  public int getJobID(Job job){
    String SQL = "SELECT jobID FROM job WHERE jobtitle=? AND description=? AND salary=? AND workersneeded=? AND shiftstart=? AND shiftend=? AND status=? AND cvr=? AND address=?";
    int id = 0;
    try
    {
      Connection conn = DatabaseConnection.getInstance().connect();
      PreparedStatement preparedStatement = conn.prepareStatement(SQL);
      preparedStatement.setString(1,job.getJobTitle() );
      preparedStatement.setString(2, job.getDescription());
      preparedStatement.setDouble(3, job.getSalary());
      preparedStatement.setInt(4, job.getWorkersNeeded());
      preparedStatement.setTimestamp(5, Timestamp.valueOf(job.getShiftStart()));
      preparedStatement.setTimestamp(6, Timestamp.valueOf(job.getShiftEnd()));
      preparedStatement.setString(7, job.getStatus());
      preparedStatement.setString(8, job.getEmployer().getCVR());
      preparedStatement.setInt(9,insertAddress(job.getLocation()));
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next())
      {
        id = rs.getInt("jobID");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return id;
  }


  @Override public void removeJobFromDB(Job job)
  {
String SQL = "DELETE FROM job WHERE jobID=?";
    try{
      Connection conn = DatabaseConnection.getInstance().connect();
      PreparedStatement pstm= conn.prepareStatement(SQL);
      pstm.setInt(1,getJobID(job));
      pstm.executeUpdate();
    }
    catch (SQLException e){
      e.printStackTrace();
    }
  }




  @Override public void applyForJob(Job job, Worker worker)

  {
 String SQL = "INSERT INTO applied (cvr,jobID)" + "VALUES(?,?)";
 try{
   Connection conn = DatabaseConnection.getInstance().connect();
   PreparedStatement pstm= conn.prepareStatement(SQL);
   pstm.setString(1,worker.getCPR());
   pstm.setInt(1,getJobID(job));
 }
 catch (SQLException e){
   e.printStackTrace();
 }
  }

  @Override public void updateJob(Job job)
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

    String SQL = "INSERT INTO employer(cvr,password,companyname,email,phone,address)"

        + "VALUES(?,?,?,?,?,?)";
    try
    {
      Connection conn = DatabaseConnection.getInstance().connect();
      PreparedStatement posted = conn.prepareStatement(SQL);
      posted.setString(1, employer.getCVR());
      posted.setString(2, password);
      posted.setString(3, employer.getCompanyName());
      posted.setString(4, employer.getEmail());
      posted.setString(5, employer.getPhone());
      posted.setInt(6, insertAddress(employer.getAddress()));
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

    String SQL =
        "INSERT INTO worker(cpr,password,firstname,lastname,taxcard,email,phone,languages,description,address)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?)";
    try
    {
      Connection conn = DatabaseConnection.getInstance().connect();
      PreparedStatement posted = conn.prepareStatement(SQL);
      posted.setString(1, worker.getCPR());
      posted.setString(2, password);
      posted.setString(3, worker.getFirstName());
      posted.setString(4, worker.getLastName());
      posted.setString(5, worker.getTaxCard());
      posted.setString(6, worker.getEmail());
      posted.setString(7, worker.getPhone());
      posted.setString(8, worker.getLanguages());
      posted.setString(9, worker.getDescription());
      posted.setInt(10,insertAddress(worker.getAddress()));
      posted.execute();
      posted.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public ArrayList<Job> getAllJobsFromDB()
  {
    ArrayList<Job> jobs = new ArrayList<>();
    String SQL = "SELECT * FROM job";
    try{
      Connection conn = DatabaseConnection.getInstance().connect();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(SQL);
      while(rs.next()){
        Job tmpJob = new Job(0,null,null,0,0,null,null,null,null,null);
        process(rs,tmpJob);
        jobs.add(tmpJob);
      }
    }
    catch (SQLException e ){
      e.printStackTrace();
    }
    return jobs;
}

  @Override public ArrayList<Job> getAllJobHistoryWorkerFromDB(Worker worker)
  {
    return null;
  }

  @Override public ArrayList<Job> getAllJobHistoryEmployerFromDB(
      Employer employer)
  {
   ArrayList<Job> jobs = new ArrayList<>();
   String SQL = "SELECT * FROM job WHERE cvr=? AND status='finished' OR status='cancelled'";
   try{
     Connection conn = DatabaseConnection.getInstance().connect();
    PreparedStatement pstm = conn.prepareStatement(SQL);
    pstm.setString(1,employer.getCVR());
  ResultSet rs = pstm.executeQuery();
     while(rs.next()){
       Job tmpJob = new Job(0,null,null,0,0,null,null,null,null,null);
       process(rs,tmpJob);
       jobs.add(tmpJob);
     }
   }
   catch (SQLException e ){
     e.printStackTrace();
   }
   return  jobs;
  }

  @Override public ArrayList<Job> getUpcomingJobsWorkerFromDB(Worker worker)
  {
    return null;
  }

  @Override public ArrayList<Job> getCurrentEmployerJobs(Employer employer)
  {
    ArrayList<Job> jobs = new ArrayList<>();
    String SQL = "SELECT * FROM job WHERE cvr=? AND status='pending'";
    try{
      Connection conn = DatabaseConnection.getInstance().connect();
      PreparedStatement pstm = conn.prepareStatement(SQL);
      pstm.setString(1,employer.getCVR());
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        Job tmpJob = new Job(0,null,null,0,0,null,null,null,null,null);
        process(rs,tmpJob);
        jobs.add(tmpJob);
      }
    }
    catch (SQLException e ){
      e.printStackTrace();
    }
    return  jobs;
  }


  @Override public ArrayList<Worker> getAllAppliedWorkers(Job job)
  {
    ArrayList<Worker> workers = new ArrayList<>();

    try
    {
      Connection conn = DatabaseConnection.getInstance().connect();

      //TODO might need to change SQL
      String SQL = "Select * FROM worker WHERE ID IN (SELECT cpr FROM works WHERE jobID =? )";
      PreparedStatement pstmt = conn.prepareStatement(SQL);
      pstmt.setInt(1, getJobID(job));
      ResultSet rs = pstmt.executeQuery();
      while (rs.next())
      {
        //TODO subject to change
        Worker tmpWorker = new Worker(null, null, null, null, null, null, null,
            null, null);
        process(rs, tmpWorker);
        workers.add(tmpWorker);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return workers;
  }

  @Override public int insertAddress(Address address)
  {
    if (!getAllAddress().contains(address))
    {
      String SQL =
          "INSERT INTO address(country,city,street,zip)" + "VALUES(?,?,?,?)";
      try
      {
        Connection conn = DatabaseConnection.getInstance().connect();
        PreparedStatement posted = conn.prepareStatement(SQL);
        posted.setString(1, address.getCountry());
        posted.setString(2, address.getCity());
        posted.setString(3, address.getStreet());
        posted.setString(4, address.getZip());
        posted.execute();
        posted.close();
      }
      catch (SQLException e)
      {

        e.printStackTrace();
      }
    }
    String SQL = "SELECT addressID from address WHERE country=? AND city=? AND street=? AND zip=?"/*+"VALUES(?,?,?,?)"*/;
    int id = 0;
    try
    {
      Connection conn = DatabaseConnection.getInstance().connect();
      PreparedStatement preparedStatement = conn.prepareStatement(SQL);
      preparedStatement.setString(1, address.getCountry());
      preparedStatement.setString(2, address.getCity());
      preparedStatement.setString(3, address.getStreet());
      preparedStatement.setString(4, address.getZip());
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next())
      {
        id = rs.getInt("addressID");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return id;
  }
  public Address getAddressByID(int id){
    Address tmpAddress = new Address(null,null,null,null);
    String SQL ="SELECT * FROM address WHERE addressid=?";
    try{
      Connection conn = DatabaseConnection.getInstance().connect();
      PreparedStatement pstm = conn.prepareStatement(SQL);
      pstm.setInt(1,id);
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        process(rs,tmpAddress);
      }
    }
    catch (SQLException e ){
      e.printStackTrace();
    }
    return tmpAddress;
  }

  public int getAddressValueFromJob(Job job){
    int id=0;
    String SQL ="SELECT address FROM job where jobID=?";
    try{
      Connection conn = DatabaseConnection.getInstance().connect();
     PreparedStatement pstm = conn.prepareStatement(SQL);
     pstm.setInt(1,getJobID(job));
     ResultSet rs = pstm.executeQuery();
     while(rs.next()){
       id= rs.getInt("address");
     }
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return id;
  }
  public int getAddressValueFromEmployer(Employer employer){
    int id = 0;
    String SQL ="SELECT address FROM employer where cvr=?";
    try{
      Connection conn = DatabaseConnection.getInstance().connect();
      PreparedStatement pstm = conn.prepareStatement(SQL);
      pstm.setString(1,employer.getCVR());
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        id = rs.getInt("address");
      }
    }
    catch (SQLException e ){
      e.printStackTrace();
    }
    return id;
  }

  public  int getAddressValueFromWorker(Worker worker){
    int id = 0;
    String SQL = "SELECT address FROM worker where cpr=?";
    try{
      Connection conn = DatabaseConnection.getInstance().connect();
      PreparedStatement pstm= conn.prepareStatement(SQL);
      pstm.setString(1,worker.getCPR());
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        id = rs.getInt("address");
      }
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return id;
  }


  public ArrayList<Address> getAllAddress()
  {
    ArrayList<Address> addresses = new ArrayList<>();
    try
    {
      Connection conn = DatabaseConnection.getInstance().connect();
      Statement stmt = conn.createStatement();
      String SQL = "Select * FROM address";
      ResultSet rs = stmt.executeQuery(SQL);

      while (rs.next())
      {
        Address tmpAddress = new Address();
        process(rs, tmpAddress);
        addresses.add(tmpAddress);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    System.out.println(addresses.toString());
    return addresses;
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
    worker.setFirstName(rs.getString("firstname"));
    worker.setLastName(rs.getString("lastname"));
    worker.setTaxCard(rs.getString("taxcard"));
    worker.setEmail(rs.getString("email"));
    worker.setPhone(rs.getString("phone"));
    worker.setLanguages(rs.getString("languages"));
    worker.setDescription(rs.getString("description"));
    worker.setAddress(getAddressByID(rs.getInt("address")));

  }

  private void process(ResultSet rs, Employer employer) throws SQLException
  {
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
