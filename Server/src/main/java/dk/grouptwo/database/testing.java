package dk.grouptwo.database;

import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Employer;

import java.sql.SQLException;

public class testing
{
  public static void main(String[] args) throws Exception
  {
  /*  *//*DatabaseConnection.getInstance().connect();*//*
    EvenMoreTesting testing = new EvenMoreTesting();
    Address address = new Address("mars","8700");
    Address insertAddress = new Address("denmark","Horsens","bilka 92","8700");
    Address insertAddressNew = new Address("slowmark","horshit","samagon vej 92","DK-8700");
    Employer employer = new Employer("test@gmail.com","50505050",address,"518","blyat");
    System.out.println("Testing : ");
    *//*testing.insertTest(employer,"gucci");*//*
    System.out.println("tested...");*/
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
/*   *//* System.out.println(testing.getAllAddresses());*//*
Employer tmpEmployer = new Employer("blet@lol.dk","123",address,"asdasdas","reitan");
Database database = new Database();
database.createEmployerAccount(tmpEmployer,"666666");*/
    Database database = new Database();
    database.loginEmployer("asdpnapsdon","cf1aa821ddabafa6a16212eba3805fbcff92c6fc981d43967e1fca8657f8571c");
  }
}
