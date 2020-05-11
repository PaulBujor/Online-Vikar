package dk.grouptwo.utility;

import dk.grouptwo.model.objects.Job;
import javafx.beans.property.*;

import java.time.LocalDateTime;

public class WorkTableData {

    private int jobId;
    private StringProperty jobTitle;
    private StringProperty status;
    private StringProperty employer;
    private IntegerProperty numberOfWorkers;
    private DoubleProperty salary;
    private ObjectProperty<LocalDateTime> startTime;
    private ObjectProperty<LocalDateTime> endTime;
    private StringProperty location;
    private String description;


    public WorkTableData(Job job) {
        jobTitle = new SimpleStringProperty(job.getJobTitle());
        status = new SimpleStringProperty(job.getStatus());
        employer = new SimpleStringProperty(job.getEmployer().getCompanyName());
        numberOfWorkers = new SimpleIntegerProperty(job.getSelectedWorkers().size());
        salary = new SimpleDoubleProperty(job.getSalary());
        startTime = new SimpleObjectProperty<LocalDateTime>(job.getShiftStart());
        endTime = new SimpleObjectProperty<LocalDateTime>(job.getShiftEnd());
        location = new SimpleStringProperty(job.getLocation());
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
        return location;
    }

    public int getJobId() {
        return jobId;
    }
}
