package dk.grouptwo.viewmodel.employer;

import dk.grouptwo.model.EmployerModel;
import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.utility.LicenseTableData;
import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.utility.WorkersTableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class EmployerWorkHistoryViewModel {
    private EmployerModel model;
    private ObservableList<WorkTableData> listHistory;
    private ObservableList<WorkersTableData> listWorkers;

    public EmployerWorkHistoryViewModel (EmployerModel model) {
        this.model = model;
        listHistory = createListHistory();
        listWorkers = createListWorkers();
    }

    private ObservableList<WorkTableData> createListHistory()
    {
        ObservableList<WorkTableData> list = FXCollections.observableArrayList();
        ArrayList<Job> jobs = model.getJobHistory();
        for (Job job: jobs)
        {
            list.add(new WorkTableData(job));
        }
        return list;
    }

    public ObservableList<WorkersTableData> createListWorkers()
    {
        return FXCollections.observableArrayList();
    }

    public void getWorkers(WorkTableData workTableData)
    {
        Job job = model.getJobById(workTableData.getJobId());
        listWorkers.removeAll();
        for (Worker worker : job.getSelectedWorkers())
        {
            listWorkers.add(new WorkersTableData(worker));
        }
    }
}
