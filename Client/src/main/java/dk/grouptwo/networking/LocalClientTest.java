package dk.grouptwo.networking;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.utility.Encryptor;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

public class LocalClientTest extends Client {
    @Override
    public Employer loginEmployer(String CVR, String password)
            throws RemoteException, NoSuchAlgorithmException {
        return new Employer(null, null, null, CVR, null);
    }

    @Override
    public Worker loginWorker(String CPR, String password)
            throws RemoteException, NoSuchAlgorithmException {
        return new Worker(null, null, null, CPR, null, null, null, null, null);
    }

    public void createEmployerAccount(Employer employer,
                                      String password) throws RemoteException, NoSuchAlgorithmException {
        System.out.println("account created");
    }
}
