package dk.grouptwo.viewmodel.worker;

import dk.grouptwo.model.ModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SignInWorkerViewModel {

    private ModelManager model;
    private StringProperty CPR;
    private StringProperty password;

    private SignInWorkerViewModel (ModelManager model) {
        this.model = model;
        CPR = new SimpleStringProperty();
        password = new SimpleStringProperty();


    }

    public boolean logInWorker()
    {
        return !CPR.get().equals("") && !password.get().equals("");
    }

}
