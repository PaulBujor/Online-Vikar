package dk.grouptwo.database;

import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Employer;

import java.sql.SQLException;
import java.util.ArrayList;

public class EvenMoreTesting
{

  private Persistence persistence= new Database();



  public void insertTest(Employer employer, String password) throws SQLException
  {
    try {
      persistence.createEmployerAccount(employer,password);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public int insertAddressTest(Address address) throws SQLException{
    return persistence.insertAddress(address);
  }
  public ArrayList<Address> getAllAddresses() throws SQLException{
     return persistence.getAllAddress();
  }
}
