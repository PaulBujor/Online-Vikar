package dk.grouptwo.utility;

import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;

import java.util.ArrayList;

public class Validator {

    private static char[] emailToArray;
    private static ArrayList<Character> emailBeforeAtSign = new ArrayList<>();
    private static ArrayList<Character> emailAfterAtSign = new ArrayList<>();
    private static ArrayList<Character> domain = new ArrayList<>();
    private static ArrayList<Character> postDomain = new ArrayList<>();

    public static boolean emailCheck(String email) {
        int atCount = 0;
        int atPosition = 0;
        emailToArray = email.toCharArray();
        for (int i = 0; i < emailToArray.length; i++) {
            if (emailToArray[i] == '@')
                atCount++;
        }
        if (!(atCount == 1)) {
            return false;
        } else {

            for (int i = 0; i < emailToArray.length; i++) {
                if (emailToArray[i] == '@')
                    atPosition = i;
            }

            for (int i = 0; i < atPosition; i++) {
                emailBeforeAtSign.add(emailToArray[i]);
            }

            for (int i = atPosition + 1; i < emailToArray.length; i++) {
                emailAfterAtSign.add(emailToArray[i]);
            }

            return beforeAtSign() && afterAtSign();
        }

    }

    private static boolean beforeAtSign() {
        System.out.println(emailBeforeAtSign.toString());
        System.out.println(emailAfterAtSign.toString());
        if (emailBeforeAtSign.get(0) == '.' || emailBeforeAtSign.get(emailBeforeAtSign.size() - 1) == '.') {
            return false;
        } else {
            for (int i = 0; i < emailBeforeAtSign.size(); i++) {
                if (emailBeforeAtSign.get(i) == ' ')
                    return false;
            }
        }
        return true;
    }

    private static boolean afterAtSign() {

        int dotPosition = 0;
        for (int i = 0; i < emailAfterAtSign.size(); i++) {
            if (emailAfterAtSign.get(i) == '.') {
                if (i == emailAfterAtSign.size() - 1 || emailAfterAtSign.get(i - 1) == '-' || emailAfterAtSign.get(i - 1) == '.' || emailAfterAtSign.get(i + 1) == '-' || emailAfterAtSign.get(i + 1) == '.')
                    return false;
                dotPosition = i;
            }
        }

        for (int i = 0; i < dotPosition - 1; i++) {
            domain.add(emailAfterAtSign.get(i));
        }

        for (int i = dotPosition + 1; i < emailAfterAtSign.size(); i++) {
            postDomain.add(emailAfterAtSign.get(i));
        }

        for (int i = 0; i < domain.size(); i++) {
            if (!((domain.get(i) >= 48 && domain.get(i) <= 57) || (domain.get(i) >= 65 && domain.get(i) <= 90) || (domain.get(i) >= 97 && domain.get(i) <= 122) || (domain.get(i) == 46)))
                return false;
        }

        if (postDomain.size() < 2)
            return false;

        for (int i = 0; i < postDomain.size(); i++) {
            if (!((domain.get(i) >= 48 && domain.get(i) <= 57) || (domain.get(i) >= 65 && domain.get(i) <= 90) || (domain.get(i) >= 97 && domain.get(i) <= 122) || (domain.get(i) == 46)))
                return false;
        }

        return true;
    }

    public static boolean createWorker(Worker worker, String password, String passwordConfirmation) throws Exception {
        if (worker.getCPR().equals("") || worker.getFirstName().equals("") || worker.getLastName().equals("") || worker.getGender().equals("") || worker.getAddress().getCity().equals("") ||
                worker.getAddress().getZip().equals("") || worker.getAddress().getCountry().equals("") || worker.getAddress().getStreet().equals("") || worker.getPhone().equals("") || worker.getTaxCard().equals("") || worker.getLanguages().equals("") || worker.getEmail().equals("") ||
                password.equals("") || passwordConfirmation.equals("")) {
            throw new Exception("All fields should be filled.");
        } else if (!(password.equals(passwordConfirmation))) {
            throw new Exception("The passwords do not match.");
        } else if (password.length() < 8) {
            throw new Exception("The password should contain at least 8 characters.");
        } else if (!(emailCheck(worker.getEmail()))) {
            throw new Exception("Wrong email format.");
        }
        return true;
    }

    public static boolean updateWorker(Worker worker, String password) throws Exception {
        if (worker.getCPR().equals("") || worker.getFirstName().equals("") || worker.getLastName().equals("") || worker.getGender().equals("") || worker.getAddress().getCity().equals("") ||
                worker.getAddress().getZip().equals("") || worker.getAddress().getCountry().equals("") || worker.getAddress().getStreet().equals("") || worker.getPhone().equals("") || worker.getTaxCard().equals("") || worker.getLanguages().equals("") || worker.getEmail().equals("")) {
            throw new Exception("All fields should be filled.");
        } else if (!(emailCheck(worker.getEmail()))) {
            throw new Exception("Wrong email format.");
        }
        if (password.isEmpty())
            throw new Exception("Enter your password to save changes");
        return true;
    }

    public static boolean updateWorker(Worker worker, String password, String newPassword, String newPasswordConfirm) throws Exception {
        if (updateWorker(worker, password)) {
            if (!newPassword.equals(newPasswordConfirm))
                throw new Exception("Passwords do not match");
            else if (newPassword.length() > 0 && newPassword.length() < 8)
                throw new Exception("New password must contain at least 8 characters");
        } else {
            return false;
        }
        return true;
    }

    public static boolean logInWorker(String CPR, String password) throws Exception {
        if (CPR.isEmpty() || password.isEmpty())
            throw new Exception("Login fields cannot be empty");
        return true;
    }

    public static boolean createEmployer(Employer employer, String password, String passwordConfirmation) throws Exception {
        if (employer.getCVR().equals("") || employer.getCompanyName().equals("") || employer.getAddress().getCity().equals("") || employer.getAddress().getZip().equals("") || employer.getAddress().getStreet().equals("") ||
                employer.getAddress().getCountry().equals("") || employer.getPhone().equals("") || employer.getEmail().equals("") || password.equals("") || passwordConfirmation.equals("")) {
            throw new Exception("All fields should be filled.");
        } else if (!(password.equals(passwordConfirmation))) {
            throw new Exception("The passwords do not match.");
        } else if (password.length() < 8) {
            throw new Exception("The password should contain at least 8 characters.");
        } else if (!(emailCheck(employer.getEmail()))) {
            throw new Exception("Wrong email format.");
        }
        return true;
    }

    public static boolean updateEmployer(Employer employer, String password) throws Exception {
        if (employer.getCVR().equals("") || employer.getCompanyName().equals("") || employer.getAddress().getCity().equals("") || employer.getAddress().getZip().equals("") || employer.getAddress().getStreet().equals("") ||
                employer.getAddress().getCountry().equals("") || employer.getPhone().equals("") || employer.getEmail().equals("")) {
            throw new Exception("All fields should be filled.");
        } else if (!(emailCheck(employer.getEmail()))) {
            throw new Exception("Wrong email format.");
        }
        if (password.isEmpty())
            throw new Exception("Enter your password to save changes");
        return true;
    }

    public static boolean updateEmployer(Employer employer, String password, String newPassword, String newPasswordConfirm) throws Exception {
        if (updateEmployer(employer, password)) {
            if (!newPassword.equals(newPasswordConfirm))
                throw new Exception("Passwords do not match");
            else if (newPassword.length() > 0 && newPassword.length() < 8)
                throw new Exception("New password must contain at least 8 characters");
        } else {
            return false;
        }
        return true;
    }

    public static boolean logInEmployer(String CVR, String password) throws Exception {
        if (CVR.isEmpty() || password.isEmpty())
            throw new Exception("Login fields cannot be empty");
        return true;
    }

    public static boolean createWork(Job job) throws Exception {
        if (job.getJobTitle().isEmpty() || job.getDescription().isEmpty() || job.getSalary() == 0 || job.getWorkersNeeded() == 0 || job.getShiftStart() == null || job.getShiftEnd() == null || job.getStatus().isEmpty() || job.getLocation() == null)
            throw new Exception("All the fields must be filled");
        if (job.getShiftStart().isAfter(job.getShiftEnd()))
            throw new Exception("Start time cannot be after end time");
        return true;
    }

    public static boolean updateWork(Job job) throws Exception {
        if (job.getJobTitle().isEmpty() || job.getDescription().isEmpty() || job.getSalary() == 0 || job.getWorkersNeeded() == 0 || job.getShiftStart() == null || job.getShiftEnd() == null || job.getStatus().isEmpty() || job.getLocation() == null)
            throw new Exception("All the fields must be filled");
        if (job.getShiftStart().isAfter(job.getShiftEnd()))
            throw new Exception("Start time cannot be after end time");
        return true;
    }
}
