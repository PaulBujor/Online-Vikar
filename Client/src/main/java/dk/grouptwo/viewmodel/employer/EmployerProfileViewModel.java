package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.utility.EmailValidator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployerProfileViewModel {

    private ModelManager model;
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
        if (CVR.get().equals("") || company.get().equals("") || city.get().equals("") || postCode.get().equals("") || address.get().equals("") ||
                mobilePhone.get().equals("") || email.get().equals("")) {
            error.set("All fields should be filled.");
            return false;
        } else if (!(newPassword.get().equals(confirmPassword.get()))) {
            error.set("The passwords do not match.");
            return false;
        } else if (newPassword.get().length() < 8) {
            error.set("The password should contain at least 8 characters.");
            return false;
        } else if (!(EmailValidator.emailCheck(email.get()))) {
            error.set("Wrong email format.");
            return false;
        }
        if(currentPassword.equals("")) {
            error.set("Please enter your current password to save changes");
            return false;
        }
        return true;
    }

    public boolean saveChangesEmployer() {
        try {
            if(validData()) {
                //model
                return true;
            }
            return false;
        } catch (Exception e) {
            error.set(e.getMessage());
            return false;
        }
    }

}
