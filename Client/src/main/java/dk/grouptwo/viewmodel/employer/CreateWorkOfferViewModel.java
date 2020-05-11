package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.WorkerModel;
import javafx.beans.property.*;

import java.time.LocalDate;

public class CreateWorkOfferViewModel {
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

    public CreateWorkOfferViewModel(WorkerModel model) {
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

    public void create() {
        //todo
    }

    public void reset() {
        title.set("");
        salary.set(0);
        startHour.set(0);
        startMinutes.set(0);
        startDate.set(null);
        endHour.set(0);
        endMinutes.set(0);
        endDate.set(null);
        location.set("");
        description.set("");
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
