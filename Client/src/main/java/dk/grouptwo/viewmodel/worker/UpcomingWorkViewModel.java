package dk.grouptwo.viewmodel.worker;

import dk.grouptwo.model.ModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UpcomingWorkViewModel {

    private ModelManager model;
    private StringProperty username;


    public UpcomingWorkViewModel(ModelManager model)
    {
        this.model = model;
        username = new SimpleStringProperty();


    }


    public StringProperty usernameProperty() {
        return username;
    }
}
