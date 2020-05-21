package dk.grouptwo.utility;

import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;
import javafx.beans.property.*;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class WorkTableData {

    private int jobId;
    private StringProperty workerStatus;
    private StringProperty jobTitle;
    private StringProperty status;
    private StringProperty employer;
    private IntegerProperty numberOfWorkers;
    private DoubleProperty salary;
    private ObjectProperty<LocalDateTime> startTime;
    private ObjectProperty<LocalDateTime> endTime;
    private Address address;
    private String description;


    public WorkTableData(Job job) {
        jobTitle = new SimpleStringProperty(job.getJobTitle());
        status = new SimpleStringProperty(job.getStatus());
        employer = new SimpleStringProperty(job.getEmployer().getCompanyName());
        numberOfWorkers = new SimpleIntegerProperty(job.getSelectedWorkers().size());
        salary = new SimpleDoubleProperty(job.getSalary());
        startTime = new SimpleObjectProperty<LocalDateTime>(job.getShiftStart());
        endTime = new SimpleObjectProperty<LocalDateTime>(job.getShiftEnd());
        address = job.getLocation();
        jobId = job.getJobID();
        description = job.getDescription();
    }

    public WorkTableData(Job job, Worker worker) {
        this(job);
        workerStatus = new SimpleStringProperty(job.getSelectedWorkers().contains(worker) ? "Confirmed" : (job.getApplicants().contains(worker) ? "Applied" : "NULL"));
    }

    public void update(Job job) {
        jobTitle = new SimpleStringProperty(job.getJobTitle());
        status = new SimpleStringProperty(job.getStatus());
        employer = new SimpleStringProperty(job.getEmployer().getCompanyName());
        numberOfWorkers = new SimpleIntegerProperty(job.getSelectedWorkers().size());
        salary = new SimpleDoubleProperty(job.getSalary());
        startTime = new SimpleObjectProperty<LocalDateTime>(job.getShiftStart());
        endTime = new SimpleObjectProperty<LocalDateTime>(job.getShiftEnd());
        address = job.getLocation();
        jobId = job.getJobID();
        description = job.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public StringProperty jobTitleProperty() {
        return jobTitle;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty employerProperty() {
        return employer;
    }

    public IntegerProperty numberOfWorkersProperty() {
        return numberOfWorkers;
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public ObjectProperty<LocalDateTime> startTimeProperty() {
        return startTime;
    }

    public ObjectProperty<LocalDateTime> endTimeProperty() {
        return endTime;
    }

    public StringProperty locationProperty() {
        return new SimpleStringProperty(address.toString());
    }

    public StringProperty workerStatusProperty() {
        return workerStatus;
    }

    public Address getAddress() {
        return address;
    }

    public int getJobId() {
        return jobId;
    }

    public DoubleProperty workTimeProperty() {
        return new SimpleDoubleProperty(Math.floor(((double) ChronoUnit.MINUTES.between(startTimeProperty().get(), endTimeProperty().get())) / 60 * 100) / 100);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof WorkTableData))
            return false;
        WorkTableData other = (WorkTableData) obj;
        return jobId == other.jobId;
    }
}
