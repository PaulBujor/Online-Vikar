package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.AccountManagement;
import dk.grouptwo.model.ModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.NoSuchElementException;

public class SignInEmployerViewModel {

    private AccountManagement model;
    private StringProperty CVR;
    private StringProperty password;
    private StringProperty error;

    public SignInEmployerViewModel(AccountManagement model) {
        this.model = model;
        CVR = new SimpleStringProperty("");
        password = new SimpleStringProperty("");
        error = new SimpleStringProperty("");
    }

    public boolean logInEmployer() {
        try {
            model.logInEmployer(CVR.get(), password.get());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            error.set(e.getMessage());
            return false;
        }
    }

    public StringProperty CVRProperty() {
        return CVR;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty errorProperty() {
        return error;
    }

    public void reset() {
        CVR.set("");
        password.set("");
        error.set("");
    }
}
