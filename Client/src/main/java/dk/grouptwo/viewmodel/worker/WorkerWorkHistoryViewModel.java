package dk.grouptwo.viewmodel.worker;

import dk.grouptwo.model.WorkerModel;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.utility.WorkTableData;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class WorkerWorkHistoryViewModel implements PropertyChangeListener {

    private WorkerModel model;
    private StringProperty username;
    private ObservableList<WorkTableData> list;
    private DoubleProperty hoursWorked;
    private StringProperty jobTitle;
    private StringProperty employer;
    private DoubleProperty salary;
    private StringProperty startEndDates;
    private StringProperty location;
    private StringProperty description;


    public WorkerWorkHistoryViewModel(WorkerModel model) {
        this.model = model;
        model.addListener(this);
        username = new SimpleStringProperty("");
        list = createList();
        hoursWorked = new SimpleDoubleProperty(model.getHoursWorkedThisMonth());
        jobTitle = new SimpleStringProperty("");
        employer = new SimpleStringProperty("");
        salary = new SimpleDoubleProperty(0);
        startEndDates = new SimpleStringProperty("");
        location = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
    }

    private ObservableList<WorkTableData> createList() {
        ObservableList<WorkTableData> list = FXCollections.observableArrayList();
        ArrayList<Job> jobs = model.getWorkHistory();
        for (Job job : jobs) {
            list.add(new WorkTableData(job));
        }
        return list;
    }

    public void selectJob(WorkTableData workTableData) {
        Job job = model.getJobById(workTableData.getJobId());
        jobTitle.set(job.getJobTitle());
        employer.set(job.getEmployer().getCompanyName());
        salary.setValue(job.getSalary());
        startEndDates.set(job.getShiftStart() + " - " + job.getShiftEnd());
        location.set(job.getLocation().toString());
        description.set(job.getDescription());
        //i like cock
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public ObservableList<WorkTableData> getList() {
        return list;
    }

    public DoubleProperty hoursWorkedProperty() {
        return hoursWorked;
    }

    public StringProperty jobTitleProperty() {
        return jobTitle;
    }

    public StringProperty employerProperty() {
        return employer;
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public StringProperty startEndDatesProperty() {
        return startEndDates;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("moveToHistory"))
            Platform.runLater(() -> {
                list.add(new WorkTableData((Job) evt.getNewValue()));
                hoursWorked.set(model.getHoursWorkedThisMonth());
            });
    }
}
