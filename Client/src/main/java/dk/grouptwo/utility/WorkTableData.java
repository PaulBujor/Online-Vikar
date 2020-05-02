package dk.grouptwo.utility;

import dk.grouptwo.model.objects.Job;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WorkTableData {

    private StringProperty jobTitle;
    private StringProperty status;
    private StringProperty employer;
    private IntegerProperty numberOfWorkers;
    private DoubleProperty salary;
    private ObjectProperty<LocalDateTime> startTime;
    private ObjectProperty<LocalDateTime> endTime;
    private StringProperty location;

    public WorkTableData(Job job) {
        jobTitle = new SimpleStringProperty(job.getJobTitle());
        status = new SimpleStringProperty(job.getStatus());
        employer = new SimpleStringProperty(job.getEmployer().getCompanyName());
        numberOfWorkers = new SimpleIntegerProperty(job.getWorkers().size());
        salary = new SimpleDoubleProperty(job.getSalary());
        startTime = new SimpleObjectProperty<LocalDateTime>(job.getShiftStart());
        endTime = new SimpleObjectProperty<LocalDateTime>(job.getShiftEnd());
        location = new SimpleStringProperty(job.getLocation());
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
}
