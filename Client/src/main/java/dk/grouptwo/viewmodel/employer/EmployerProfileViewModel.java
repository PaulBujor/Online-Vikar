package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.AccountManagement;
import dk.grouptwo.model.EmployerModel;
import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.utility.EmailValidator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployerProfileViewModel {

    private AccountManagement model;
    private StringProperty CVR;
    private StringProperty company;
    private StringProperty country;
    private StringProperty city;
    private StringProperty postCode;
    private StringProperty address;
    private StringProperty mobilePhone;
    private StringProperty email;
    private StringProperty currentPassword;
    private StringProperty newPassword;
    private StringProperty confirmPassword;
    private StringProperty error;


    public EmployerProfileViewModel(ModelManager model) {
        this.model = model;
        CVR = new SimpleStringProperty("");
        company = new SimpleStringProperty("");
        country = new SimpleStringProperty("");
        city = new SimpleStringProperty("");
        postCode = new SimpleStringProperty("");
        address = new SimpleStringProperty("");
        mobilePhone = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        currentPassword = new SimpleStringProperty("");
        newPassword = new SimpleStringProperty("");
        confirmPassword = new SimpleStringProperty("");
        error = new SimpleStringProperty("");
    }

    private boolean validData() {
        if (CVR.get().equals("") || company.get().equals("") || country.get().equals("") || city.get().equals("") || postCode.get().equals("") || address.get().equals("") ||
                mobilePhone.get().equals("") || email.get().equals("")) {
            error.set("All fields should be filled.");
            return false;
        } else if (!(newPassword.get().equals(confirmPassword.get()))) {
            error.set("The passwords do not match.");
            return false;
        } else if (newPassword.get().length() < 8 && newPassword.get().length() > 0) {
            error.set("The password should contain at least 8 characters.");
            return false;
        } else if (!(EmailValidator.emailCheck(email.get()))) {
            error.set("Wrong email format.");
            return false;
        }
        if (currentPassword.get().equals("")) {
            error.set("Please enter your current password to save changes");
            return false;
        }
        error.set("");
        return true;
    }

    public void reset() {
        Employer employer = model.getEmployer();
        CVR.set(employer.getCVR());
        company.set(employer.getCompanyName());
        country.set(employer.getAddress().getCountry());
        city.set(employer.getAddress().getCity());
        postCode.set(employer.getAddress().getZip());
        address.set(employer.getAddress().getStreet());
        mobilePhone.set(employer.getPhone());
        email.set(employer.getEmail());
        error.set("");
    }

    public boolean saveChangesEmployer() {
        try {
            if (validData()) {
                Employer employer = new Employer(email.get(), mobilePhone.get(), new Address(country.get(), city.get(), address.get(), postCode.get()), CVR.get(), company.get());
                if (newPassword.get().equals(""))
                    model.editEmployer(employer, currentPassword.get());
                else
                    model.editEmployer(employer, currentPassword.get(), newPassword.get());
                return true;
            }
            return false;
        } catch (Exception e) {
            error.set(e.getMessage());
            return false;
        }
    }

    public AccountManagement getModel() {
        return model;
    }

    public StringProperty CVRProperty() {
        return CVR;
    }

    public StringProperty companyProperty() {
        return company;
    }

    public StringProperty countryProperty() {
        return country;
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

    public StringProperty currentPasswordProperty() {
        return currentPassword;
    }

    public StringProperty newPasswordProperty() {
        return newPassword;
    }

    public StringProperty confirmPasswordProperty() {
        return confirmPassword;
    }

    public StringProperty errorProperty() {
        return error;
    }
}
