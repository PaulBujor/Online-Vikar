import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ModelManagerTest {

    private ModelManager model;
    Employer dummyEmployer = new Employer("employer@gmail.com", "11111111", new Address("Denmark", "Horsens", "Sundvej", "8700"), "111111", "Employer Company");
    Worker dummyWorker = new Worker("worker@gmail.com", "11223344", new Address("Denmark", "Horsens", "Sundvej", "8700"), "220220-2222", "John", "Doe", "A", "Danish, English", "I like to be a human-being", LocalDate.of(2020, 02, 22), "Other");
    Job dummyJobOne = new Job("Work1", "Work needed", 101, 1,
            LocalDateTime.of(2020, 7, 19, 6, 0, 0),
            LocalDateTime.of(2020, 7, 19, 14, 15, 0),
            new Address("Denmark", "Horsens", "Sundvej", "8700"), "pending",
            dummyEmployer);
    Job dummyJobTwo = new Job("Work2", "Work needed", 102, 2,
            LocalDateTime.of(2020, 8, 19, 6, 0, 0),
            LocalDateTime.of(2020, 8, 19, 14, 15, 0),
            new Address("Denmark", "Horsens", "Sundvej", "8700"), "pending",
            dummyEmployer);


    @BeforeEach
    void setUp() {
        model = new ModelManager();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerAccountWorker() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        assertEquals(dummyWorker, model.getWorker());
    }

    @Test
    void registerAccountWorkerExistingAccount() throws Exception {
        String errorMessage = "";
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        Worker anotherDummyWorker = dummyWorker;
        try {
            model.registerAccountWorker(anotherDummyWorker, "12345678", "12345678");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Account could not be created!", errorMessage);
    }

    @Test
    void registerAccountWorkerNotMatchingPasswords() throws Exception {
        String errorMessage = "";

        try {
            model.registerAccountWorker(dummyWorker, "12345678", "123456789");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The passwords do not match.", errorMessage);
    }

    @Test
    void registerAccountWorkerPasswordLength() throws Exception {
        String errorMessage = "";
        try {
            model.registerAccountWorker(dummyWorker, "1234567", "1234567");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The password should contain at least 8 characters.", errorMessage);
    }

    @Test
    void registerAccountWorkerEmailFormat() throws Exception {
        String errorMessage = "";
        dummyWorker.setEmail("worker@@gmail.com");
        try {
            model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Wrong email format.", errorMessage);
    }

    @Test
    void logInWorkerNotExisting() throws Exception {
        String errorMessage = "";
        try {
            model.logInWorker("000000-0000", "12345678");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Account does not exist!", errorMessage);
    }

    @Test
    void registerAccountEmployer() throws Exception {

        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
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
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Account could not be created!", errorMessage);
    }

    @Test
    void registerAccountEmployerNotMatchingPasswords() throws Exception {
        String errorMessage = "";

        try {
            model.registerAccountEmployer(dummyEmployer, "12345678", "123456789");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The passwords do not match.", errorMessage);
    }


    @Test
    void registerAccountEmployerPasswordLength() throws Exception {
        String errorMessage = "";

        try {
            model.registerAccountEmployer(dummyEmployer, "1234567", "1234567");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("The password should contain at least 8 characters.", errorMessage);
    }

    @Test
    void registerAccountEmployerEmailFormat() throws Exception {
        String errorMessage = "";
        dummyEmployer.setEmail("employer@@gmail.com");
        try {
            model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Wrong email format.", errorMessage);
    }


    @Test
    void logInEmployer() throws Exception {

    }


    @Test
    void editEmployer() throws Exception {
        Employer dummyEmployerEdited = dummyEmployer;
        model.registerAccountEmployer(dummyEmployerEdited, "12345678", "12345678");
        model.logInEmployer(dummyEmployerEdited.getCVR(), "12345678");
        dummyEmployerEdited.setCompanyName("Another Employer Company");
        model.editEmployer(dummyEmployerEdited, "12345678");
        assertEquals(dummyEmployerEdited, model.getEmployer());
    }

    @Test
    void editEmployerPassword() throws Exception {
        Employer dummyEmployerEdited = dummyEmployer;
        model.registerAccountEmployer(dummyEmployerEdited, "12345678", "12345678");
        model.logInEmployer(dummyEmployerEdited.getCVR(), "12345678");
        model.editEmployer(dummyEmployerEdited, "12345678", "87654321", "87654321");
        assertEquals(dummyEmployerEdited, model.getEmployer());
    }


    @Test
    void editEmployerWrongCurrentPassword() throws Exception {
        String errorMessage = "";
        Employer dummyEmployerEdited = dummyEmployer;
        model.registerAccountEmployer(dummyEmployerEdited, "12345678", "12345678");
        model.logInEmployer(dummyEmployerEdited.getCVR(), "12345678");
        try {
            model.editEmployer(dummyEmployerEdited, "123456789", "87654321", "87654321");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Password does not match the current one.", errorMessage);
    }

    @Test
    void editEmployerWrongPasswordConfirmation() throws Exception {
        String errorMessage = "";
        Employer dummyEmployerEdited = dummyEmployer;
        model.registerAccountEmployer(dummyEmployerEdited, "12345678", "12345678");
        model.logInEmployer(dummyEmployerEdited.getCVR(), "12345678");
        try {
            model.editEmployer(dummyEmployerEdited, "12345678", "87654321", "876543210");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Passwords do not match", errorMessage);
    }


    @Test
    void editWorker() throws Exception {
        Worker dummyWorkerEdited = dummyWorker;
        model.registerAccountWorker(dummyWorkerEdited, "12345678", "12345678");
        model.logInWorker(dummyWorkerEdited.getCPR(), "12345678");
        dummyWorkerEdited.setFirstName("Kyle");
        model.editWorker(dummyWorkerEdited, "12345678");
        assertEquals(dummyWorkerEdited, model.getWorker());
    }

    @Test
    void editWorkerPassword() throws Exception {
        Worker dummyWorkerEdited = dummyWorker;
        model.registerAccountWorker(dummyWorkerEdited, "12345678", "12345678");
        model.logInWorker(dummyWorkerEdited.getCPR(), "12345678");
        model.editWorker(dummyWorkerEdited, "12345678", "87654321", "87654321");
        assertEquals(dummyWorkerEdited, model.getWorker());
    }

    @Test
    void editWorkerWrongCurrentPassword() throws Exception {
        Worker dummyWorkerEdited = dummyWorker;
        model.registerAccountWorker(dummyWorkerEdited, "12345678", "12345678");
        model.logInWorker(dummyWorkerEdited.getCPR(), "12345678");
        model.editWorker(dummyWorkerEdited, "123456789", "87654321", "87654321");
        assertEquals(dummyWorkerEdited, model.getWorker());
    }

    @Test
    void editWorkerWrongPasswordConfirmation() throws Exception {
        Worker dummyWorkerEdited = dummyWorker;
        model.registerAccountWorker(dummyWorkerEdited, "12345678", "12345678");
        model.logInWorker(dummyWorkerEdited.getCPR(), "12345678");
        model.editWorker(dummyWorkerEdited, "12345678", "87654321", "876543210");
        assertEquals(dummyWorkerEdited, model.getWorker());
    }


    @Test
    void getEmployer() throws Exception {
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");
        assertEquals(dummyEmployer, model.getEmployer());
    }

    @Test
    void getWorker() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        assertEquals(dummyWorker, model.getWorker());
    }

    @Test
    void getJobByIdNull() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        model.createWorkOffer(dummyJobOne);
        assertNull(model.getJobById(-1));
    }

    @Test
    void getJobById() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        model.createWorkOffer(dummyJobOne);
        model.createWorkOffer(dummyJobTwo);
        assertEquals(dummyJobOne, model.getJobById(dummyJobOne.getJobID()));
    }

    @Test
    void getLicenseByNumber() throws Exception {
        License dummyLicense = new License("Forklift license", "Class 1 - Electric", "12345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        model.addLicense(dummyLicense);
        assertEquals(dummyLicense, model.getLicenseByNumber("12345678"));
    }

    @Test
    void getHoursWorkedThisMonth() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");
        model.createWorkOffer(dummyJobOne);
        dummyJobOne.addSelectedworker(dummyWorker);
        dummyJobOne.setStatus("completed");


    }

    @Test
    void logOutWorker() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.logOutWorker();
        assertNull(model.getWorker());
    }

    @Test
    void logOutEmployer() throws Exception {
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");
        model.logOutEmployer();
        assertNull(model.getEmployer());
    }

    @Test
    void getWorkHistoryZero() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        ArrayList<Job> dummyJobs = new ArrayList<>();
        dummyJobs.add(null);
        model.createWorkOffer(null);
        assertNull(model.getWorkHistory());
    }

    @Test
    void getWorkHistoryOne() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        ArrayList<Job> dummyJobs = new ArrayList<>();
        dummyJobs.add(dummyJobOne);
        model.createWorkOffer(dummyJobOne);
        assertEquals(dummyJobs, model.getWorkHistory());
    }

    @Test
    void getWorkHistoryMany() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        ArrayList<Job> dummyJobs = new ArrayList<>();
        dummyJobs.add(dummyJobOne);
        dummyJobs.add(dummyJobTwo);

        model.createWorkOffer(dummyJobOne);
        model.createWorkOffer(dummyJobTwo);


        assertEquals(dummyJobs, model.getWorkHistory());
    }

    @Test
    void createWorkOffer() throws Exception {
        Job job = new Job("Work", "Work needed", 100, 1,
                LocalDateTime.of(2020, 5, 19, 6, 0, 0),
                LocalDateTime.of(2020, 5, 19, 14, 15, 0),
                new Address("Denmark", "Horsens", "Sundvej", "8700"), "pending",
                dummyEmployer);
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");
        model.createWorkOffer(job);
    }

    @Test
    void createWorkOfferInvalidData() throws Exception {
        String errorMessage = "";
        dummyJobOne.setJobTitle("");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");
        try {
            model.createWorkOffer(dummyJobOne);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        assertEquals("All the fields must be filled", errorMessage);
    }

    @Test
    void cancelWorkerFromJob() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        model.createWorkOffer(dummyJobOne);
        model.createWorkOffer(dummyJobTwo);
        dummyJobOne.addApplicant(dummyWorker);
        dummyJobTwo.addApplicant(dummyWorker);
        model.updateWorkOffer(dummyJobOne);
        model.updateWorkOffer(dummyJobTwo);
        model.cancelWorkerFromJob(dummyJobOne);

        boolean firstCondition = !dummyJobOne.getApplicants().contains(dummyWorker);
        boolean secondCondition = dummyJobTwo.getApplicants().contains(dummyWorker);

        assertTrue(firstCondition && secondCondition);
    }

    @Test
    void cancelSelectedWorkerFromJob() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        model.createWorkOffer(dummyJobOne);
        model.createWorkOffer(dummyJobTwo);
        dummyJobOne.addSelectedworker(dummyWorker);
        dummyJobTwo.addSelectedworker(dummyWorker);
        model.updateWorkOffer(dummyJobOne);
        model.updateWorkOffer(dummyJobTwo);

        dummyJobOne.removeSelectedWorker(dummyWorker);
        model.updateWorkOffer(dummyJobOne);


        boolean firstCondition = !dummyJobOne.getSelectedWorkers().contains(dummyWorker);
        boolean secondCondition = dummyJobTwo.getSelectedWorkers().contains(dummyWorker);

        assertTrue(firstCondition && secondCondition);
    }

    @Test
    void cancelWorkOfferZero() throws Exception {
        String errorMessage = "";
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        try {
            model.cancelWorkOffer(dummyJobOne);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        assertEquals("An error has occurred.", errorMessage);


    }

    @Test
    void cancelWorkOfferOne() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        ArrayList<Job> dummyJobs = new ArrayList<>();
        dummyJobs.add(dummyJobOne);
        model.createWorkOffer(dummyJobOne);

        dummyJobs.remove(dummyJobOne);
        model.cancelWorkOffer(dummyJobOne);

        assertEquals(dummyJobs, model.getJobs());
    }

    @Test
    void cancelWorkOfferMany() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        ArrayList<Job> dummyJobs = new ArrayList<>();
        dummyJobs.add(dummyJobOne);
        dummyJobs.add(dummyJobTwo);
        model.createWorkOffer(dummyJobOne);
        model.createWorkOffer(dummyJobTwo);

        dummyJobs.remove(dummyJobOne);
        dummyJobs.remove(dummyJobTwo);
        model.cancelWorkOffer(dummyJobOne);
        model.cancelWorkOffer(dummyJobTwo);

        assertEquals(dummyJobs, model.getJobs());
    }

    @Test
    void updateWorkOfferNotFilledFields() throws Exception {
        String errorMessage = "";
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        model.createWorkOffer(dummyJobOne);
        dummyJobOne.setJobTitle("");
        try {
            model.updateWorkOffer(dummyJobOne);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("All the fields must be filled", errorMessage);
    }

    @Test
    void updateWorkOfferWrongDates() throws Exception {
        String errorMessage = "";
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        model.createWorkOffer(dummyJobOne);
        dummyJobOne.setShiftStart(LocalDateTime.of(2020, 7, 19, 14, 15, 0));
        dummyJobOne.setShiftEnd(LocalDateTime.of(2020, 7, 19, 6, 0, 0));
        try {
            model.updateWorkOffer(dummyJobOne);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Start time cannot be after end time", errorMessage);
    }

    @Test
    void updateWorkOffer() throws Exception {
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        model.createWorkOffer(dummyJobOne);
        model.updateWorkOffer(dummyJobTwo);

        ArrayList<Job> dummyJobs = new ArrayList<>();
        dummyJobs.add(dummyJobOne);
        assertEquals(dummyJobs, model.getJobs());
    }

    @Test
    void applyForJobZero() throws Exception {
        String errorMessage = "";
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        try {
            model.applyForJob(dummyJobOne.getJobID());
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        assertEquals("An error has occurred.", errorMessage);
    }

    @Test
    void applyForJobOne() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        ArrayList<Worker> dummyWorkers = new ArrayList<>();
        dummyWorkers.add(dummyWorker);

        model.createWorkOffer(dummyJobOne);
        model.applyForJob(dummyJobOne.getJobID());

        assertEquals(dummyWorkers, dummyJobOne.getApplicants());
    }

    @Test
    void applyForJobMany() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        model.createWorkOffer(dummyJobOne);
        model.createWorkOffer(dummyJobTwo);
        model.applyForJob(dummyJobOne.getJobID());
        model.applyForJob(dummyJobTwo.getJobID());
        assertEquals(2, dummyJobOne.getApplicants().size());
    }

    @Test
    void addLicenseZero() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.addLicense(null);
        assertNull(model.getLicenses());
    }

    @Test
    void addLicenseOne() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        ArrayList<License> dummyLicenses = new ArrayList<>();
        License dummyLicense = new License("Forklift license", "Class 1 - Electric", "12345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        model.addLicense(dummyLicense);
        assertEquals(dummyLicenses, model.getLicenses());
    }

    @Test
    void addLicenseMany() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        ArrayList<License> dummyLicenses = new ArrayList<>();
        License dummyLicense = new License("Forklift license", "Class 1 - Electric", "12345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        License anotherDummyLicense = new License("Forklift license", "Class 4 - Internal Combustion Engine", "012345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        dummyLicenses.add(dummyLicense);
        dummyLicenses.add(anotherDummyLicense);

        model.addLicense(dummyLicense);
        model.addLicense(anotherDummyLicense);
        assertEquals(dummyLicenses, model.getLicenses());
    }

    @Test
    void deleteLicenseOne() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");

        ArrayList<License> dummyLicenses = new ArrayList<>();
        License dummyLicense = new License("Forklift license", "Class 1 - Electric", "12345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        License anotherDummyLicense = new License("Forklift license", "Class 4 - Internal Combustion Engine", "012345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        dummyLicenses.add(dummyLicense);
        dummyLicenses.add(anotherDummyLicense);
        model.addLicense(dummyLicense);
        model.addLicense(anotherDummyLicense);

        model.deleteLicense(anotherDummyLicense.getLicenseNumber());
        dummyLicenses.remove(anotherDummyLicense);

        assertEquals(dummyLicenses, model.getLicenses());

    }

    @Test
    void deleteLicenseMany() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");

        ArrayList<License> dummyLicenses = new ArrayList<>();
        License dummyLicense = new License("Forklift license", "Class 1 - Electric", "12345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        License anotherDummyLicense = new License("Forklift license", "Class 4 - Internal Combustion Engine", "012345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        dummyLicenses.add(dummyLicense);
        dummyLicenses.add(anotherDummyLicense);
        model.addLicense(dummyLicense);
        model.addLicense(anotherDummyLicense);

        model.deleteLicense(anotherDummyLicense.getLicenseNumber());
        model.deleteLicense(dummyLicense.getLicenseNumber());
        dummyLicenses.remove(anotherDummyLicense);
        dummyLicenses.remove(dummyLicense);

        assertEquals(dummyLicenses, model.getLicenses());

    }

    @Test
    void deleteLicenseInvalidLicenseNumber() throws Exception {
        String errorMessage = "";
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");

        ArrayList<License> dummyLicenses = new ArrayList<>();
        License dummyLicense = new License("Forklift license", "Class 1 - Electric", "12345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        License anotherDummyLicense = new License("Forklift license", "Class 4 - Internal Combustion Engine", "012345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        dummyLicenses.add(dummyLicense);
        dummyLicenses.add(anotherDummyLicense);
        model.addLicense(dummyLicense);
        model.addLicense(anotherDummyLicense);

        try {
            model.deleteLicense("-1");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        assertEquals("An error has occurred.", errorMessage);
    }

    @Test
    void getLicensesZero() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");

        assertTrue(model.getLicenses().isEmpty());
    }

    @Test
    void getLicensesOne() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");

        ArrayList<License> dummyLicenses = new ArrayList<>();
        License dummyLicense = new License("Forklift license", "Class 1 - Electric", "12345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        dummyLicenses.add(dummyLicense);
        model.addLicense(dummyLicense);

        assertEquals(dummyLicenses, model.getLicenses());
    }

    @Test
    void getLicensesMany() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");

        ArrayList<License> dummyLicenses = new ArrayList<>();
        License dummyLicense = new License("Forklift license", "Class 1 - Electric", "12345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        License anotherDummyLicense = new License("Forklift license", "Class 4 - Internal Combustion Engine", "012345678", LocalDate.of(2020, 01, 01), LocalDate.of(2021, 01, 01));
        dummyLicenses.add(dummyLicense);
        dummyLicenses.add(anotherDummyLicense);
        model.addLicense(dummyLicense);
        model.addLicense(anotherDummyLicense);

        assertEquals(dummyLicenses, model.getLicenses());
    }

    @Test
    void getJobsZero() throws Exception {
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        assertTrue(model.getJobs().isEmpty());
    }

    @Test
    void getJobsOne() throws Exception {
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        ArrayList<Job> dummyJobs = new ArrayList<>();
        dummyJobs.add(dummyJobOne);
        model.createWorkOffer(dummyJobOne);

        assertEquals(dummyJobs, model.getJobs());
    }

    @Test
    void getJobsMany() throws Exception {
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        ArrayList<Job> dummyJobs = new ArrayList<>();
        dummyJobs.add(dummyJobOne);
        dummyJobs.add(dummyJobTwo);
        model.createWorkOffer(dummyJobOne);
        model.createWorkOffer(dummyJobTwo);

        assertEquals(dummyJobs, model.getJobs());
    }

    @Test
    void getUpcomingJobsZero() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");

        assertTrue(model.getUpcomingJobs().isEmpty());
    }

    @Test
    void getUpcomingJobsOne() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        ArrayList<Job> dummyJobs = new ArrayList<>();
        dummyJobs.add(dummyJobOne);
        model.createWorkOffer(dummyJobOne);
        dummyJobOne.addSelectedworker(dummyWorker);

        assertEquals(dummyJobs, model.getUpcomingJobs());
    }

    @Test
    void getUpcomingJobsMany() throws Exception {
        model.registerAccountWorker(dummyWorker, "12345678", "12345678");
        model.logInWorker(dummyWorker.getCPR(), "12345678");
        model.registerAccountEmployer(dummyEmployer, "12345678", "12345678");
        model.logInEmployer(dummyEmployer.getCVR(), "12345678");

        ArrayList<Job> dummyJobs = new ArrayList<>();
        dummyJobs.add(dummyJobOne);
        dummyJobs.add(dummyJobTwo);
        model.createWorkOffer(dummyJobOne);
        model.createWorkOffer(dummyJobTwo);
        dummyJobOne.addSelectedworker(dummyWorker);
        dummyJobTwo.addSelectedworker(dummyWorker);

        assertEquals(dummyJobs, model.getUpcomingJobs());
    }
}