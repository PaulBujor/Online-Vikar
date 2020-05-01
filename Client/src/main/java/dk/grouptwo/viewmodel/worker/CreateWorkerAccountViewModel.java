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
        CPR = new SimpleStringProperty();
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        birthday = new SimpleObjectProperty<>();
        gender = new SimpleStringProperty();
        city = new SimpleStringProperty();
        postCode = new SimpleStringProperty();
        mobilePhone = new SimpleStringProperty();
        taxCard = new SimpleStringProperty();
        languages = new SimpleStringProperty();
        description = new SimpleStringProperty();
        email = new SimpleStringProperty();
        password = new SimpleStringProperty();
        confirmPassword = new SimpleStringProperty();
        error = new SimpleStringProperty();
    }

    public boolean createWorkerAccount() {
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
        } else {
            try {
                model.registerAccountWorker(new Worker(email.get(), mobilePhone.get(), new Address(city.get(), postCode.get()), CPR.get(),
                        firstName.get(), lastName.get(), taxCard.get(), languages.get(), description.get()), password.get());
                return true;
            }
            catch (IllegalArgumentException e) {
                error.set(e.getMessage());
                return false;
            }
        }
    }

}
