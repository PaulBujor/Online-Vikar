package dk.grouptwo.utility;

import dk.grouptwo.model.objects.Worker;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorkersTableData {

    private StringProperty CPR;
    private StringProperty name;

    public WorkersTableData (Worker worker)
    {
        CPR = new SimpleStringProperty(worker.getCPR());
        name = new SimpleStringProperty(worker.getFirstName() + " " + worker.getLastName());
    }

    public StringProperty CPRProperty() {
        return CPR;
    }

    public StringProperty nameProperty() {
        return name;
    }
}
