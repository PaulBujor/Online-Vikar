package dk.grouptwo.database;

import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Employer;

import java.sql.SQLException;

public class EvenMoreTesting
{

  private Persistence persistence= new Database();



  public void insertTest(Employer employer, String password) throws SQLException
  {
    persistence.createEmployerAccount(employer,password);
  }

  public void insertAddressTest(Address address) throws SQLException{
    persistence.insertAddress(address);
  }
}
