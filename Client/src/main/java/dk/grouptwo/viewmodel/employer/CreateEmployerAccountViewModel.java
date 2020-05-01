package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Employer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


import java.rmi.RemoteException;

public class CreateEmployerAccountViewModel {

    private ModelManager model;
    private StringProperty CVR;
    private StringProperty company;
    private StringProperty city;
    private StringProperty postCode;
    private StringProperty address;
    private StringProperty mobilePhone;
    private StringProperty email;
    private StringProperty password;
    private StringProperty confirmPassword;
    private StringProperty errorLabel;
    private BooleanProperty accountCreated;

    public CreateEmployerAccountViewModel(ModelManager model) {
        this.model = model;
        CVR = new SimpleStringProperty();
        company = new SimpleStringProperty();
        city = new SimpleStringProperty();
        postCode = new SimpleStringProperty();
        address = new SimpleStringProperty();
        mobilePhone = new SimpleStringProperty();
        email = new SimpleStringProperty();
        password = new SimpleStringProperty();
        confirmPassword = new SimpleStringProperty();
        errorLabel = new SimpleStringProperty();
        accountCreated = new SimpleBooleanProperty();
    }

    public boolean createEmployerAccount ()
    {
        if (CVR.get().equals("") || company.get().equals("") || city.get().equals("") || postCode.get().equals("") || address.get().equals("") ||
        mobilePhone.get().equals("") || email.get().equals("") || password.get().equals("") || confirmPassword.get().equals(""))
        {
            errorLabel.set("All the fields should be filled.");
            return false;
        }
        else if (password.get().equals(confirmPassword.get()))
        {
            errorLabel.set("The passwords do not match.");
            return false;
        }
        // TODO check for email
        else
        {
            try {
                model.registerAccountEmployer(new Employer(email.get(),mobilePhone.get(),new Address(city.get(), address.get(), postCode.get()), CVR.get(), company.get()), password.get());
                return true;
            }
            catch (IllegalArgumentException e)
            {
                errorLabel.set(e.getMessage());
                return false;
            }
        }
    }

}
