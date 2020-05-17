import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Worker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ModelManagerTest {

    private ModelManager model;
    Employer dummyEmployer = new Employer("employer@gmail.com", "11111111", new Address("Denmark", "Horsens", "Sundvej", "8700"), "111111", "Employer Company");
    Worker dummyWorker = new Worker("worker@gmail.com", "11223344", new Address("Denmark", "Horsens", "Sundvej", "8700"), "220220-2222", "John", "Doe", "A", "Danish, English", "I like to be a human-being", LocalDate.of(2020, 02, 22), "Other");


    @BeforeEach
    void setUp() {
        model = new ModelManager();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerAccountWorker() throws Exception {

        model.registerAccountWorker(dummyWorker, "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        assertEquals(dummyWorker, model.getWorker());
    }

    @Test
    void logInWorkerNotExisting() throws Exception {
        String errorMessage = "";
       try {
           model.logInWorker("000000-0000", "12345678");
       }
       catch (Exception e)
       {
           errorMessage = e.getMessage();
       }
        assertEquals("Account does not exist!", errorMessage);
    }

    @Test
    void registerAccountEmployer() throws Exception {

        model.registerAccountEmployer(dummyEmployer, "12345678","12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");
        assertEquals(dummyEmployer, model.getEmployer());
    }

    @Test
    void registerAccountEmployerExistingAccount() throws Exception {
        String errorMessage = "";
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        Employer anotherDummyEmployer = dummyEmployer;
        try {
            model.registerAccountEmployer(anotherDummyEmployer, "12345678", "12345678");
        }
        catch (Exception e)
        {
             errorMessage = e.getMessage();
        }
        assertEquals("Account could not be created!", errorMessage);
    }

    @Test
    void registerAccountEmployerNotMatchingPasswords() throws Exception {
        String errorMessage = "";

        try {
            model.registerAccountEmployer(dummyEmployer, "12345678", "123456789");
        }
        catch (Exception e)
        {
            errorMessage = e.getMessage();
        }
        assertEquals("The passwords do not match.", errorMessage);
    }

    @Test
    void registerAccountEmployerPasswordLength() throws Exception {
        String errorMessage = "";

        try {
            model.registerAccountEmployer(dummyEmployer, "1234567", "1234567");
        }
        catch (Exception e)
        {
            errorMessage = e.getMessage();
        }
        assertEquals("The password should contain at least 8 characters.", errorMessage);
    }

    @Test
    void registerAccountEmployerEmailFormat() throws Exception {
        String errorMessage = "";
        dummyEmployer.setEmail("employer@@gmail.com");
        try {
            model.registerAccountEmployer(dummyEmployer, "1234567", "1234567");
        }
        catch (Exception e)
        {
            errorMessage = e.getMessage();
        }
        assertEquals("Wrong email format.", errorMessage);
    }




    @Test
    void logInEmployer() throws Exception {

    }


    @Test
    void editEmployer() {
    }

    @Test
    void testEditEmployer() throws Exception {
        Employer dummyEmployerEdited = dummyEmployer;
        dummyEmployerEdited.setCompanyName("Another Employer Company");
        model.editEmployer(dummyEmployerEdited, "12345678");
        assertEquals(dummyEmployerEdited, model.getEmployer());
    }

    @Test
    void editWorker() {
    }

    @Test
    void testEditWorker() throws Exception {
        Worker dummyWorkerEdited = dummyWorker;
        dummyWorkerEdited.setFirstName("Kyle");
        model.editWorker(dummyWorkerEdited, "12345678");
        assertEquals(dummyWorkerEdited, model.getWorker());
    }

    @Test
    void getEmployer() {
    }

    @Test
    void getWorker() {
    }

    @Test
    void getJobById() {
    }

    @Test
    void getLicenseByNumber() {
    }

    @Test
    void getHoursWorkedThisMonth() {
    }

    @Test
    void logOutWorker() {
    }

    @Test
    void logOutEmployer() {
    }

    @Test
    void getWorkHistory() {
    }

    @Test
    void createWorkOffer() {
    }

    @Test
    void cancelWorkerFromJob() {
    }

    @Test
    void cancelWorkOffer() {
    }

    @Test
    void updateWorkOffer() {
    }

    @Test
    void applyForJob() {
    }

    @Test
    void addLicense() {
    }

    @Test
    void deleteLicense() {
    }

    @Test
    void getLicenses() {
    }

    @Test
    void getEmployerJobs() {
    }

    @Test
    void getWorkerByJob() {
    }

    @Test
    void getJobs() {
    }

    @Test
    void getUpcomingJobs() {
    }
}