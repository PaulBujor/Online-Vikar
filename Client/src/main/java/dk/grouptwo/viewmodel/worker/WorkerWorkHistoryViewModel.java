package dk.grouptwo.viewmodel.worker;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.Job;
import dk.grouptwo.utility.WorkTableData;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class WorkerWorkHistoryViewModel {

    private ModelManager model;
    private ObservableList<WorkTableData> list;
    private DoubleProperty hoursWorked;

    public WorkerWorkHistoryViewModel(ModelManager model)
    {
        this.model = model;
        list = createList();
        hoursWorked = new SimpleDoubleProperty(model.getHoursWorkedThisMonth());
    }

    private  ObservableList<WorkTableData> createList()
    {
        ObservableList<WorkTableData> list = FXCollections.observableArrayList();
        ArrayList<Job> jobs = model.getJobHistory();
        for (Job job: jobs)
        {
            list.add(new WorkTableData(job));
        }
        return list;
    }



}
