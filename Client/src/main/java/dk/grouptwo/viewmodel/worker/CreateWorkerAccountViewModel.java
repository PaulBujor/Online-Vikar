package dk.grouptwo.viewmodel.worker;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.utility.EmailValidator;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class CreateWorkerAccountViewModel {

    private ModelManager model;
    private StringProperty CPR;
    private StringProperty firstName;
    private StringProperty lastName;
    private ObjectProperty<LocalDate> birthday;
    private StringProperty gender;
    private StringProperty country;
    private StringProperty street;
    private StringProperty city;
    private StringProperty postCode;
    private StringProperty mobilePhone;
    private StringProperty taxCard;
    private StringProperty languages;
    private StringProperty description;
    private StringProperty email;
    private StringProperty password;
    private StringProperty confirmPassword;
    private StringProperty error;

    public CreateWorkerAccountViewModel(ModelManager model) {
        this.model = model;
        CPR = new SimpleStringProperty("");
        firstName = new SimpleStringProperty("");
        lastName = new SimpleStringProperty("");
        birthday = new SimpleObjectProperty<>(null);
        gender = new SimpleStringProperty("Gender");
        city = new SimpleStringProperty("");
        postCode = new SimpleStringProperty("");
        country = new SimpleStringProperty("");
        street = new SimpleStringProperty("");
        mobilePhone = new SimpleStringProperty("");
        taxCard = new SimpleStringProperty("Tax card");
        languages = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        password = new SimpleStringProperty("");
        confirmPassword = new SimpleStringProperty("");
        error = new SimpleStringProperty("");
    }

    private boolean dataValid() {
        if (CPR.get().equals("") || firstName.get().equals("") || lastName.get().equals("") || gender.get().equals("") || city.get().equals("") ||
                postCode.get().equals("") || mobilePhone.get().equals("") || taxCard.get().equals("") || languages.get().equals("") || description.get().equals("") || email.get().equals("") ||
                password.get().equals("") || confirmPassword.get().equals("")) {
            error.set("All fields should be filled.");
            return false;
        } else if (!(password.get().equals(confirmPassword.get()))) {
            error.set("The passwords do not match.");
            return false;
        } else if (!(EmailValidator.emailCheck(email.get()))) {
            error.set("Wrong email format.");
            return false;
        }
        return true;
    }

    public boolean createWorkerAccount() {
        try {
            if (dataValid()) {
                model.registerAccountWorker(new Worker(email.get(), mobilePhone.get(), new Address(country.get(), city.get(), street.get(), postCode.get()), CPR.get(),
                        firstName.get(), lastName.get(), taxCard.get(), languages.get(), description.get(), birthday.get(), gender.get()), password.get());
                return true;
            }
            return false;
        } catch (Exception e) {
            error.set(e.getMessage());
            return false;
        }
    }

    public void reset() {
        CPR.set("");
        firstName.set("");
        lastName.set("");
        birthday.set(null);
        gender.set("Gender");
        city.set("");
        postCode.set("");
        mobilePhone.set("");
        taxCard.set("Tax card");
        languages.set("");
        description.set("");
        email.set("");
        password.set("");
        confirmPassword.set("");
        error.set("");
    }

    public StringProperty CPRProperty() {
        return CPR;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty streetProperty() {
        return street;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty postCodeProperty() {
        return postCode;
    }

    public StringProperty mobilePhoneProperty() {
        return mobilePhone;
    }

    public StringProperty taxCardProperty() {
        return taxCard;
    }

    public StringProperty languagesProperty() {
        return languages;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty confirmPasswordProperty() {
        return confirmPassword;
    }

    public StringProperty errorProperty() {
        return error;
    }
}
