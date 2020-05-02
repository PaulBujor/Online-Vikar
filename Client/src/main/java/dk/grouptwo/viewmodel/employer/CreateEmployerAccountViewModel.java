package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.AccountManagement;
import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.utility.EmailValidator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreateEmployerAccountViewModel {

    private AccountManagement model;
    private StringProperty CVR;
    private StringProperty company;
    private StringProperty city;
    private StringProperty postCode;
    private StringProperty address;
    private StringProperty mobilePhone;
    private StringProperty email;
    private StringProperty password;
    private StringProperty confirmPassword;
    private StringProperty error;

    public CreateEmployerAccountViewModel(AccountManagement model) {
        this.model = model;
        CVR = new SimpleStringProperty("");
        company = new SimpleStringProperty("");
        city = new SimpleStringProperty("");
        postCode = new SimpleStringProperty("");
        address = new SimpleStringProperty("");
        mobilePhone = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        password = new SimpleStringProperty("");
        confirmPassword = new SimpleStringProperty("");
        error = new SimpleStringProperty("");
    }

    private boolean validData() {
        if (CVR.get().equals("") || company.get().equals("") || city.get().equals("") || postCode.get().equals("") || address.get().equals("") ||
                mobilePhone.get().equals("") || email.get().equals("") || password.get().equals("") || confirmPassword.get().equals("")) {
            error.set("All fields should be filled.");
            return false;
        } else if (!(password.get().equals(confirmPassword.get()))) {
            error.set("The passwords do not match.");
            return false;
        } else if (password.get().length() < 8) {
            error.set("The password should contain at least 8 characters.");
            return false;
        } else if (!(EmailValidator.emailCheck(email.get()))) {
            error.set("Wrong email format.");
            return false;
        }
        return true;
    }

    public boolean createEmployerAccount() {
        try {
            if (validData()) {
                model.registerAccountEmployer(new Employer(email.get(), mobilePhone.get(), new Address(city.get(), address.get(), postCode.get()), CVR.get(), company.get()), password.get());
                return true;
            }
            return false;
        } catch (Exception e) {
            error.set(e.getMessage());
            return false;
        }
    }

    public void reset() {
        CVR.set("");
        company.set("");
        city.set("");
        postCode.set("");
        address.set("");
        mobilePhone.set("");
        email.set("");
        password.set("");
        confirmPassword.set("");
        error.set("");
    }

    public StringProperty CVRProperty() {
        return CVR;
    }

    public StringProperty companyProperty() {
        return company;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty postCodeProperty() {
        return postCode;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty mobilePhoneProperty() {
        return mobilePhone;
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
