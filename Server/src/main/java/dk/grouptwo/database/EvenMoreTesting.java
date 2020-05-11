package dk.grouptwo.database;

import dk.grouptwo.model.objects.Employer;

import java.sql.SQLException;

public class EvenMoreTesting
{

  private Persistence persistence= new Database();



  public void insertTest(Employer employer, String password) throws SQLException
  {
    persistence.createEmployerAccount(employer,password);
  }
}
