package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.utility.LicenseTableData;
import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.utility.WorkersTableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class EmployerWorkHistoryViewModel {

    private ModelManager model;
    private ObservableList<WorkTableData> listHistory;
    private ObservableList<WorkersTableData> listWorkers;

    public EmployerWorkHistoryViewModel () {
        listHistory = createListHistory();
        listWorkers = createListWorkers();
    }

    private ObservableList<WorkTableData> createListHistory()
    {
        ObservableList<LicenseTableData> list = FXCollections.observableArrayList();
        ArrayList<Job> jobs = model.getJobHistory();
        return null;

    }

    private ObservableList<WorkersTableData> createListWorkers()
    {
        return null;
    }

}
