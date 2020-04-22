package dk.grouptwo.model.objects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Job implements Serializable {
    private int jobID;
    private String jobTitle;
    private String description;
    private String category;
    private double salary;
    private int workersNeeded;
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private String status;

    public Job(int jobID, String jobTitle, String description, String category, double salary, int workersNeeded, LocalDateTime shiftStart, LocalDateTime shiftEnd, String status) {
        this.jobID = jobID;
        this.jobTitle = jobTitle;
        this.description = description;
        this.category = category;
        this.salary = salary;
        this.workersNeeded = workersNeeded;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
        this.status = status;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
