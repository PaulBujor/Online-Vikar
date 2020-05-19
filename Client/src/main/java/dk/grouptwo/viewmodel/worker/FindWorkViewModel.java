package dk.grouptwo.viewmodel.worker;

import dk.grouptwo.model.ModelManager;
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

public class FindWorkViewModel implements PropertyChangeListener {

    private WorkerModel model;
    private StringProperty username;
    private StringProperty jobTitle;
    private StringProperty employer;
    private DoubleProperty salary;
    private StringProperty startEndDates;
    private StringProperty location;
    private StringProperty description;
    private ObservableList<WorkTableData> list;

    public FindWorkViewModel(WorkerModel model) {
        this.model = model;
        model.addListener(this);
        username = new SimpleStringProperty();
        jobTitle = new SimpleStringProperty("");
        employer = new SimpleStringProperty("");
        salary = new SimpleDoubleProperty(0);
        startEndDates = new SimpleStringProperty("");
        location = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        list = FXCollections.observableArrayList();
    }

    public ObservableList<WorkTableData> createList() {
        list.clear();
        try {
            ArrayList<Job> jobs = model.getJobs();
            for (Job job : jobs)
                list.add(new WorkTableData(job));
        } catch (Exception e) {
            //
        }
        return list;
    }

    public void apply(WorkTableData data) {
        try {
            model.applyForJob(data.getJobId());
        } catch (Exception e) {
//            error.set(e.getMessage());
        }
    }

    public void selectItem(WorkTableData data) {
        jobTitle.set(data.jobTitleProperty().get());
        employer.set(data.employerProperty().get());
        salary.set(data.salaryProperty().get());
        startEndDatesProperty().set(data.startTimeProperty().get() + " - " + data.endTimeProperty().get());
        location.set(data.locationProperty().get());
        description.set(data.getDescription());
    }

    public void reset() {
        createList();
    }

    public ObservableList<WorkTableData> getList() {
        return list;
    }

    public StringProperty usernameProperty() {
        return username;
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
        if (evt.getPropertyName().equals("addJob"))
            Platform.runLater(() -> list.add(new WorkTableData((Job) evt.getNewValue())));
        else if (evt.getPropertyName().equals("moveToUpcoming"))
            Platform.runLater(() -> list.remove(new WorkTableData((Job) evt.getOldValue())));
    }

}
