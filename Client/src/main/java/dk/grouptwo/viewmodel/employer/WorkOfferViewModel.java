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
    private StringProperty country;
    private StringProperty postCode;
    private StringProperty city;
    private StringProperty street;
    private StringProperty description;
    private IntegerProperty workersNeeded;
    private StringProperty error;

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
        country = new SimpleStringProperty("");
        city = new SimpleStringProperty("");
        street = new SimpleStringProperty("");
        postCode = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        workersNeeded = new SimpleIntegerProperty(0);
        error = new SimpleStringProperty("");
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
        country.set(data.getAddress().getCountry());
        city.set(data.getAddress().getCity());
        postCode.set(data.getAddress().getZip());
        street.set(data.getAddress().getStreet());
        description.set(data.getDescription());
        workersNeeded.set(data.numberOfWorkersProperty().get());
        error.set("");
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

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty postCodeProperty() {
        return postCode;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty streetProperty() {
        return street;
    }

    public IntegerProperty workersNeededProperty() {
        return workersNeeded;
    }

    public StringProperty errorProperty() {
        return error;
    }
}
