package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.Employer;
import dk.grouptwo.utility.EmailValidator;
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
    private StringProperty error;
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
        error = new SimpleStringProperty();
        accountCreated = new SimpleBooleanProperty();
    }

    public boolean createEmployerAccount ()
    {
        if (CVR.get().equals("") || company.get().equals("") || city.get().equals("") || postCode.get().equals("") || address.get().equals("") ||
        mobilePhone.get().equals("") || email.get().equals("") || password.get().equals("") || confirmPassword.get().equals(""))
        {
            error.set("All fields should be filled.");
            return false;
        }
        else if (!(password.get().equals(confirmPassword.get())))
        {
            error.set("The passwords do not match.");
            return false;
        }
        else if (password.get().length() < 8)
        {
            error.set("The password should contain at least 8 characters.");
        }
        else if (!(EmailValidator.emailCheck(email.get())))
        {
            error.set("Wrong email format.");
            return false;
        }
        else
        {
            try {
                model.registerAccountEmployer(new Employer(email.get(),mobilePhone.get(),new Address(city.get(), address.get(), postCode.get()), CVR.get(), company.get()), password.get());
                return true;
            }
            catch (IllegalArgumentException e)
            {
                error.set(e.getMessage());
                return false;
            }
        }
        return true;
    }

}
