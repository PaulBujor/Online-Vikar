package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.ModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SignInEmployerViewModel {

    private ModelManager model;
    private StringProperty CVR;
    private StringProperty password;

    private SignInEmployerViewModel(ModelManager model) {
        this.model = model;
        CVR = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }

    public boolean logInEmployer()
    {
        return !CVR.get().equals("") && !password.get().equals("");
    }


}
