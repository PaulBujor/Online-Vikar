package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.EmployerModel;
import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Job;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CreateWorkOfferViewModel {
    private EmployerModel model;
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

    public CreateWorkOfferViewModel(EmployerModel model) {
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

    public boolean create() {
        try {
            model.createWorkOffer(new Job(title.get(), description.get(), salary.get(), workersNeeded.get(), LocalDateTime.of(startDate.get(), LocalTime.of(startHour.get(), startMinutes.get())),
                    LocalDateTime.of(endDate.get(), LocalTime.of(endHour.get(), endMinutes.get())), new Address(country.get(), city.get(), street.get(), postCode.get()), "pending", model.getEmployer()));
            return true;
        } catch (Exception e) {
            error.set(e.getMessage());
            return false;
        }
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
        country.set("");
        city.set("");
        postCode.set("");
        street.set("");
        description.set("");
        workersNeeded.set(0);
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
