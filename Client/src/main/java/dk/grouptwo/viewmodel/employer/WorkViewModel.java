package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.EmployerModel;
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

public class WorkViewModel implements PropertyChangeListener {
    private EmployerModel model;
    private ObservableList<WorkTableData> list;
    private StringProperty jobTitle;
    private StringProperty employer;
    private DoubleProperty salary;
    private StringProperty startEndDates;
    private StringProperty location;
    private StringProperty description;
    private WorkTableData selectedItem;
    private StringProperty error;

    public WorkViewModel(EmployerModel model) {
        this.model = model;
        model.addListener(this);
        jobTitle = new SimpleStringProperty("");
        employer = new SimpleStringProperty("");
        salary = new SimpleDoubleProperty(0);
        startEndDates = new SimpleStringProperty("");
        location = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        error = new SimpleStringProperty("");
        list = createList();
    }

    private ObservableList<WorkTableData> createList()
    {
        ObservableList<WorkTableData> list = FXCollections.observableArrayList();
        try {
            ArrayList<Job> jobs = model.getEmployerJobs();
            for (Job job : jobs)
                list.add(new WorkTableData(job));
        } catch (Exception e) {
            //
        }
        return list;
    }

    public void selectItem(WorkTableData item) {
        jobTitle.set(item.jobTitleProperty().get());
        employer.set(item.employerProperty().get());
        salary.set(item.salaryProperty().get());
        startEndDates.set(item.startTimeProperty().get() + " - " + item.endTimeProperty().get());
        location.set(item.locationProperty().get());
        description.set(item.getDescription());
        selectedItem = item;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("moveToHistory"))
            Platform.runLater(() -> list.remove(new WorkTableData((Job) evt.getOldValue())));
    }

    public WorkTableData getSelectedWorkOffer() {
        return selectedItem;
    }

    public void reset() {
        jobTitle.set("");
        employer.set("");
        salary.set(0);
        startEndDates.set("");
        location.set("");
        description.set("");

    }

    public EmployerModel getModel() {
        return model;
    }

    public ObservableList<WorkTableData> getList() {
        return list;
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
