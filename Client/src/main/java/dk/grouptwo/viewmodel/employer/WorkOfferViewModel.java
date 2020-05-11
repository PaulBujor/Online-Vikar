package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.WorkerModel;
import dk.grouptwo.utility.WorkTableData;
import javafx.beans.property.*;

import java.time.LocalDate;

public class WorkOfferViewModel {
    WorkerModel model;
    private StringProperty title;
    private DoubleProperty salary;
    private IntegerProperty startHour;
    private IntegerProperty startMinutes;
    private ObjectProperty<LocalDate> startDate;
    private IntegerProperty endHour;
    private IntegerProperty endMinutes;
    private ObjectProperty<LocalDate> endDate;
    private StringProperty location;
    private StringProperty description;

    public WorkOfferViewModel(WorkerModel model) {
        this.model = model;
        title = new SimpleStringProperty("");
        salary = new SimpleDoubleProperty(0);
        startHour = new SimpleIntegerProperty(0);
        startMinutes = new SimpleIntegerProperty(0);
        startDate = new SimpleObjectProperty<>(null);
        endHour = new SimpleIntegerProperty(0);
        endMinutes = new SimpleIntegerProperty(0);
        endDate = new SimpleObjectProperty<>(null);
        location = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
    }

    public void save() {
        //todo
    }

    public void reset(WorkTableData data) {
        title.set(data.jobTitleProperty().get());
        salary.set(data.salaryProperty().get());
        startHour.set(data.startTimeProperty().get().getHour());
        startMinutes.set(data.startTimeProperty().get().getMinute());
        startDate.set(data.startTimeProperty().get().toLocalDate());
        endHour.set(data.endTimeProperty().get().getHour());
        endMinutes.set(data.endTimeProperty().get().getMinute());
        endDate.set(data.endTimeProperty().get().toLocalDate());
        location.set(data.locationProperty().get());
        description.set(data.getDescription());
    }

    public StringProperty titleProperty() {
        return title;
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public IntegerProperty startHourProperty() {
        return startHour;
    }

    public IntegerProperty startMinutesProperty() {
        return startMinutes;
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public IntegerProperty endHourProperty() {
        return endHour;
    }

    public IntegerProperty endMinutesProperty() {
        return endMinutes;
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public StringProperty descriptionProperty() {
        return description;
    }
}
