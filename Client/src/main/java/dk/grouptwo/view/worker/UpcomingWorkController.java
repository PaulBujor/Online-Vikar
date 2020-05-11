package dk.grouptwo.view.worker;

import dk.grouptwo.utility.StringDoubleConverter;
import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.worker.UpcomingWorkViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.time.LocalDateTime;

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
    private TableView<WorkTableData> upcomingTable;

    @FXML
    private TableColumn<WorkTableData, String> upcomingJobTitleColumn;

    @FXML
    private TableColumn<WorkTableData, String> upcomingEmployerColumn;

    @FXML
    private TableColumn<WorkTableData, Number> upcomingSalaryColumn;

    @FXML
    private TableColumn<WorkTableData, LocalDateTime> upcomingStartColumn;

    @FXML
    private TableColumn<WorkTableData, LocalDateTime> upcomingEndColumn;

    @FXML
    private TableColumn<WorkTableData, Number> upcomingWorkTimeColumn;

    @FXML
    private TableColumn<WorkTableData, String> upcomingLocationColumn;

    @FXML
    private Button profileButton;

    public void init(ViewHandler viewHandler, UpcomingWorkViewModel viewModel, Region root){
        super.init(viewHandler, root);
        this.viewModel = viewModel;
        profileButton.setText(viewModel.usernameProperty().get());

        upcomingDetailsTitle.textProperty().bind(viewModel.jobTitleProperty());
        upcomingDetailsEmployer.textProperty().bind(viewModel.employerProperty());
        Bindings.bindBidirectional(upcomingDetailsSalary.textProperty(), viewModel.salaryProperty(), new StringDoubleConverter(0));
        upcomingDetailsStartEndDates.textProperty().bind(viewModel.startEndDatesProperty());
        upcomingDetailsLocation.textProperty().bind(viewModel.locationProperty());
        upcomingDetailsWorkDescription.textProperty().bind(viewModel.descriptionProperty());



        //TODO setCellValueFactory for table

    }

    @FXML
    void upcomingCancelButtonPressed() {

    }

}
