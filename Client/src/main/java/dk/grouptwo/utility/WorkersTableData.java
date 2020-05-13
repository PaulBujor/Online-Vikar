package dk.grouptwo.utility;

import dk.grouptwo.model.objects.Worker;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorkersTableData {

    private StringProperty CPR;
    private StringProperty name;
    private BooleanProperty selectedForWork;

    public WorkersTableData (Worker worker)
    {
        CPR = new SimpleStringProperty(worker.getCPR());
        name = new SimpleStringProperty(worker.getFirstName() + " " + worker.getLastName());
        selectedForWork = new SimpleBooleanProperty(false);
    }

    public StringProperty CPRProperty() {
        return CPR;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public BooleanProperty selectedForWorkProperty() {
        return selectedForWork;
    }
}
