package dk.grouptwo.viewmodel.worker;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.utility.WorkTableData;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class WorkerWorkHistoryViewModel {

    private ModelManager model;
    private StringProperty username;
    private ObservableList<WorkTableData> list;
    private DoubleProperty hoursWorked;
    private StringProperty jobTitle;
    private StringProperty employer;
    private DoubleProperty salary;
    private StringProperty startEndDates;
    private StringProperty location;
    private StringProperty description;


    public WorkerWorkHistoryViewModel(ModelManager model)
    {
        this.model = model;
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

    private  ObservableList<WorkTableData> createList()
    {
        ObservableList<WorkTableData> list = FXCollections.observableArrayList();
        ArrayList<Job> jobs = model.getJobHistory();
        for (Job job: jobs)
        {
            list.add(new WorkTableData(job));
        }
        return list;
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
}
