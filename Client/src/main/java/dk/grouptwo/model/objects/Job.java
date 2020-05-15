package dk.grouptwo.model.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Job implements Serializable {
    private int jobID;
    private String jobTitle;
    private String description;
    private double salary;
    private int workersNeeded;
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private String status;
    private Address location;
    private Employer employer;


    private ArrayList<Worker> selectedWorkers;
    private ArrayList<Worker> applicants;

    public Job(String jobTitle, String description, double salary, int workersNeeded, LocalDateTime shiftStart, LocalDateTime shiftEnd, Address location, String status, Employer employer) {
        this.jobTitle = jobTitle;
        this.description = description;
        this.salary = salary;
        this.workersNeeded = workersNeeded;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
        this.status = status;
        this.location = location;
        this.employer = employer;
        selectedWorkers = new ArrayList<Worker>();
        applicants = new ArrayList<Worker>();
    }

    public Job(int jobID, String jobTitle, String description, double salary, int workersNeeded, LocalDateTime shiftStart, LocalDateTime shiftEnd, String status, Address location, Employer employer) {
        this(jobTitle, description, salary, workersNeeded, shiftStart, shiftEnd, location, status, employer);
        this.jobID = jobID;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWorkersNeeded() {
        return workersNeeded;
    }

    public void setWorkersNeeded(int workersNeeded) {
        this.workersNeeded = workersNeeded;
    }

    public LocalDateTime getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(LocalDateTime shiftStart) {
        this.shiftStart = shiftStart;
    }

    public LocalDateTime getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(LocalDateTime shiftEnd) {
        this.shiftEnd = shiftEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public ArrayList<Worker> getSelectedWorkers() {
        return selectedWorkers;
    }

    public void addWorker(Worker worker) {
        selectedWorkers.add(worker);
    }

    public void removeWorker(Worker worker) {
        selectedWorkers.remove(worker);
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public ArrayList<Worker> getApplicants() {
        return applicants;
    }

    public void addApplicant(Worker worker) {
        if (!applicants.contains(worker))
            applicants.add(worker);
    }

    public void setApplicants(ArrayList<Worker> applicants) {
        this.applicants = applicants;
    }

    public void setSelectedWorkers(ArrayList<Worker> selectedWorkers) {
        this.selectedWorkers = selectedWorkers;
    }
}
