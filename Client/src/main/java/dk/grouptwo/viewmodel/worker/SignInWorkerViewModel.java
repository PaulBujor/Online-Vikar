package dk.grouptwo.viewmodel.worker;

import dk.grouptwo.model.AccountManagement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SignInWorkerViewModel {

    private AccountManagement model;
    private StringProperty CPR;
    private StringProperty password;
    private StringProperty error;

    public SignInWorkerViewModel(AccountManagement model) {
        this.model = model;
        CPR = new SimpleStringProperty("");
        password = new SimpleStringProperty("");
        error = new SimpleStringProperty("");
    }

    public boolean logInWorker() {
        try {
            model.logInWorker(CPR.get(), password.get());
            return true;
        } catch (Exception e) {
            error.set(e.getMessage());
            return false;
        }
    }

    public StringProperty CPRProperty() {
        return CPR;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty errorProperty() {
        return error;
    }

    public void reset() {
        CPR.set("");
        password.set("");
        error.set("");
    }

}
