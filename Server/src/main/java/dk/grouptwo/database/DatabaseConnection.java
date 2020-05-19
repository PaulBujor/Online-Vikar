package dk.grouptwo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DatabaseConnection
{

  private Connection connection;
  private String driver = "org.postgresql.Driver";
  private String url = "jdbc:postgresql://localhost:5432/sep2db?currentSchema=sep2db";
  private String user = "postgres";
  private String password = "gt-s5570";
  private static DatabaseConnection instance;
  private static Lock locker = new ReentrantLock();

  private DatabaseConnection()
  {
    this.connection = null;
  }

  public static DatabaseConnection getInstance()
  {
    if (instance == null)
    {
      synchronized (locker)
      {
        if (instance == null)
          instance = new DatabaseConnection();
      }
    }
    return instance;
  }

  public synchronized Connection connect()
  {
    try
    {
      connection = DriverManager.getConnection(url, user, password);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      connection = null;
    }
    return connection;
  }

}
