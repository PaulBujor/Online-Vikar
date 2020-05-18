package dk.grouptwo.database;

import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Employer;

import java.sql.SQLException;

public class testing
{
  public static void main(String[] args) throws SQLException
  {
    /*DatabaseConnection.getInstance().connect();*/
    EvenMoreTesting testing = new EvenMoreTesting();
    Address address = new Address("mars","8700");
    Address insertAddress = new Address("denmark","Horsens","bilka 92","8700");
    Address insertAddressNew = new Address("slowmark","horshit","samagon vej 92","DK-8700");
    Employer employer = new Employer("test@gmail.com","50505050",address,"518","blyat");
    System.out.println("Testing : ");
    /*testing.insertTest(employer,"gucci");*/
    System.out.println("tested...");
/*
    System.out.println(Integer.parseInt(insertAddress.getZip()));
    System.out.println(String.valueOf(insertAddress.getZip()));
    System.out.println("Testing adding address:");
    testing.insertAddressTest(insertAddress);
    System.out.println("Testing done..");*/
/*
    System.out.println("Testing if getting getting all addresses");
    testing.getAllAddresses();
    System.out.println("Test done");

    if(testing.getAllAddresses().contains(insertAddress)){
      System.out.println("true");
    }*/
 /*   testing.insertAddressTest(insertAddress);
    testing.insertAddressTest(insertAddress);*/

/*    System.out.println(testing.insertAddressTest(insertAddress));*/
   /* System.out.println(testing.getAllAddresses());*/

Database database = new Database();

  }
}
