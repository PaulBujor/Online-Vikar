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

public class UpcomingWorkViewModel implements PropertyChangeListener {

    private WorkerModel model;
    private StringProperty username;
    private StringProperty jobTitle;
    private StringProperty employer;
    private DoubleProperty salary;
    private StringProperty startEndDates;
    private StringProperty location;
    private StringProperty description;
    private ObservableList<WorkTableData> list;
    private WorkTableData selectedJob;

    public UpcomingWorkViewModel(WorkerModel model)
    {
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

    private void createList() {
        list.clear();
        try {
            ArrayList<Job> jobs = model.getUpcomingJobs();
            for(Job job : jobs)
                list.add(new WorkTableData(job, model.getWorker()));
        } catch (Exception e) {
            //
        }
    }

    public ObservableList<WorkTableData> getList()
    {
        return list;
    }

    public void selectItem(WorkTableData data) {
        selectedJob = data;
        jobTitle.set(data.jobTitleProperty().get());
        employer.set(data.employerProperty().get());
        salary.set(data.salaryProperty().get());
        startEndDates.set(data.startTimeProperty().get() + " - " + data.endTimeProperty().get());
        location.set(data.locationProperty().get());
        description.set(data.getDescription());
    }

    public void reset() {
        jobTitle.set("");
        employer.set("");
        salary.set(0);
        startEndDates.set("");
        location.set("");
        description.set("");
        createList();
    }

    public void cancel()
    {
        try {
            model.cancelWorkerFromJob(model.getJobById(selectedJob.getJobId()));
        } catch (NullPointerException e) {
            //this would happen if no job is selected
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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

    public StringProperty usernameProperty() {
        return username;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(this::createList);
    }

}
