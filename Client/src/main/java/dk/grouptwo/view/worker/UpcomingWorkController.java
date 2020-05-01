package dk.grouptwo.view.worker;

import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.worker.UpcomingWorkViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class UpcomingWorkController extends WorkerViewTabController {
    private UpcomingWorkViewModel viewModel;

    @FXML
    private Text upcomingDetailsTitle;

    @FXML
    private Text upcomingDetailsEmployer;

    @FXML
    private Text upcomingDetailsSalary;

    @FXML
    private Text upcomingDetailsStartEndDates;

    @FXML
    private Text upcomingDetailsLocation;

    @FXML
    private Text upcomingDetailsWorkDescription;

    @FXML
    private TableView<?> upcomingTable;

    @FXML
    private TableColumn<?, ?> upcomingJobTitleColumn;

    @FXML
    private TableColumn<?, ?> upcomingEmployerColumn;

    @FXML
    private TableColumn<?, ?> upcomingSalaryColumn;

    @FXML
    private TableColumn<?, ?> upcomingStartColumn;

    @FXML
    private TableColumn<?, ?> upcomingWorkTimeColumn;

    @FXML
    private TableColumn<?, ?> upcomingLocationColumn;

    public void init(ViewHandler viewHandler, UpcomingWorkViewModel viewModel, Region root){
        super.init(viewHandler, root);
        this.viewModel = viewModel;
    }

    @FXML
    void upcomingCancelButtonPressed() {

    }

}
