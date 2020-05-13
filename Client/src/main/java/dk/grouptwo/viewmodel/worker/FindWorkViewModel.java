package dk.grouptwo.viewmodel.worker;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.WorkerModel;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.utility.WorkTableData;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class FindWorkViewModel {

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
        username = new SimpleStringProperty();
        jobTitle = new SimpleStringProperty("");
        employer = new SimpleStringProperty("");
        salary = new SimpleDoubleProperty(0);
        startEndDates = new SimpleStringProperty("");
        location = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        list = createList();
    }

    public ObservableList<WorkTableData> createList() {
        ObservableList<WorkTableData> list = FXCollections.observableArrayList();
        try {
            ArrayList<Job> jobs = model.getJobs();
            for(Job job : jobs)
                list.add(new WorkTableData(job));
        } catch (Exception e) {
            //
        }
        return list;
    }

    public void apply(WorkTableData data) {
        model.applyForJob(data.getJobId());
    }

    public ObservableList<WorkTableData> getList()
    {
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

}
