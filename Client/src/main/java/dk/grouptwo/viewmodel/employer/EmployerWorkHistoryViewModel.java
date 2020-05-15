package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.EmployerModel;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.utility.WorkersTableData;
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

public class EmployerWorkHistoryViewModel implements PropertyChangeListener {
    private EmployerModel model;
    private ObservableList<WorkTableData> listHistory;
    private ObservableList<WorkersTableData> listWorkers;

    private StringProperty jobTitle;
    private DoubleProperty salary;
    private StringProperty dates;
    private StringProperty location;
    private StringProperty description;


    public EmployerWorkHistoryViewModel(EmployerModel model) {
        this.model = model;
        model.addListener(this);
        listHistory = createListHistory();
        listWorkers = FXCollections.observableArrayList();

        jobTitle = new SimpleStringProperty("");
        salary = new SimpleDoubleProperty(0);
        dates = new SimpleStringProperty("");
        location = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
    }

    private ObservableList<WorkTableData> createListHistory() {
        ObservableList<WorkTableData> list = FXCollections.observableArrayList();
        try {
            ArrayList<Job> jobs = model.getWorkHistory();
            for (Job job : jobs)
                list.add(new WorkTableData(job));
        } catch (Exception e) {
            //
        }
        return list;
    }

    public void selectJob(WorkTableData workTableData) {
        Job job = model.getJobById(workTableData.getJobId());
        jobTitle.set(job.getJobTitle());
        salary.set(job.getSalary());
        dates.set(job.getShiftStart() + " - " + job.getShiftEnd());
        location.set(job.getLocation().toString());
        description.set(job.getDescription());
        listWorkers.removeAll();
        for (Worker worker : job.getSelectedWorkers()) {
            listWorkers.add(new WorkersTableData(worker));
        }
    }

    public void reset() {
        listWorkers.removeAll();
        jobTitle.set("");
        salary.set(0);
        dates.set("");
        location.set("");
        description.set("");
    }

    public ObservableList<WorkTableData> getListHistory() {
        return listHistory;
    }

    public ObservableList<WorkersTableData> getListWorkers() {
        return listWorkers;
    }

    public StringProperty jobTitleProperty() {
        return jobTitle;
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public StringProperty datesProperty() {
        return dates;
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
            Platform.runLater(() -> listHistory.add(new WorkTableData((Job) evt.getNewValue())));
    }
}
