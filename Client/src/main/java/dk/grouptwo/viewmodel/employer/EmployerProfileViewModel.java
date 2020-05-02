package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.ModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployerProfileViewModel {

    private ModelManager model;
    private StringProperty company;
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
        company = new SimpleStringProperty();
        city = new SimpleStringProperty();
        postCode = new SimpleStringProperty();
        address = new SimpleStringProperty();
        mobilePhone = new SimpleStringProperty();
        email = new SimpleStringProperty();
        currentPassword = new SimpleStringProperty();
        newPassword = new SimpleStringProperty();
        confirmPassword = new SimpleStringProperty();
        error = new SimpleStringProperty();
    }

    public boolean saveChangesEmployer() {
        if (!(newPassword.get().equals(confirmPassword.get())))
        {
            error.set("The passwords do not match.");
            return false;
        }
        else if (newPassword.get().length() < 8) {
            error.set("The password should contain at least 8 characters.");
            return false;
        }

        return true;
    }

}
