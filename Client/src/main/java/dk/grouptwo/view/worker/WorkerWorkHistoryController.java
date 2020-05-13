package dk.grouptwo.view.worker;

import dk.grouptwo.utility.StringDoubleConverter;
import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.worker.WorkerWorkHistoryViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.time.LocalDateTime;

public class WorkerWorkHistoryController extends WorkerViewTabController {
    private WorkerWorkHistoryViewModel viewModel;

    @FXML
    private Text workHistoryDetailsTitle;

    @FXML
    private Text workHistoryDetailsEmployer;

    @FXML
    private Text workHistoryDetailsSalary;

    @FXML
    private Text workHistoryDetailsStartEndDates;

    @FXML
    private Text workHistoryDetailsLocation;

    @FXML
    private Text workHistoryDetailsWorkDescription;

    @FXML
    private TableView<WorkTableData> workHistoryTable;

    @FXML
    private TableColumn<WorkTableData, String> workHistoryJobTitleColumn;

    @FXML
    private TableColumn<WorkTableData, String> workHistoryStatusColumn;

    @FXML
    private TableColumn<WorkTableData, String> workHistoryEmployerColumn;

    @FXML
    private TableColumn<WorkTableData, Number> workHistorySalaryColumn;

    @FXML
    private TableColumn<WorkTableData, LocalDateTime> workHistoryStartColumn;

    @FXML
    private TableColumn<WorkTableData, LocalDateTime> workHistoryEndColumn;

    @FXML
    private TableColumn<WorkTableData, Number> workHistoryWorkTimeColumn;

    @FXML
    private TableColumn<WorkTableData, String> workHistoryLocationColumn;

    @FXML
    private Label workHistoryHoursLabel;

    @FXML
    private Button profileButton;

    public void init(ViewHandler viewHandler, WorkerWorkHistoryViewModel viewModel, Region root) {
        super.init(viewHandler, root);
        this.viewModel = viewModel;
        profileButton.setText(viewModel.usernameProperty().get());
        workHistoryHoursLabel.setText("" + viewModel.hoursWorkedProperty().get());
        workHistoryDetailsTitle.textProperty().bind(viewModel.jobTitleProperty());
        workHistoryDetailsEmployer.textProperty().bind(viewModel.employerProperty());
        Bindings.bindBidirectional(workHistoryDetailsSalary.textProperty(), viewModel.salaryProperty(), new StringDoubleConverter(0));
        workHistoryDetailsStartEndDates.textProperty().bind(viewModel.startEndDatesProperty());
        workHistoryDetailsLocation.textProperty().bind(viewModel.locationProperty());
        workHistoryDetailsWorkDescription.textProperty().bind(viewModel.descriptionProperty());

        workHistoryJobTitleColumn.setCellValueFactory(cellData -> cellData.getValue().jobTitleProperty());
        workHistoryStatusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        workHistoryEmployerColumn.setCellValueFactory(cellData -> cellData.getValue().employerProperty());
        workHistorySalaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty());
        workHistoryStartColumn.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());
        workHistoryEndColumn.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
        workHistoryWorkTimeColumn.setCellValueFactory(cellData -> cellData.getValue().workTimeProperty());
        workHistoryLocationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        workHistoryTable.setItems(viewModel.getList());


    }

    @FXML
    public void workerHistoryJobSelected()
    {
        try {
            WorkTableData workTableData = workHistoryTable.getSelectionModel().getSelectedItem();
            viewModel.selectJob(workTableData);
        }
        catch (Exception e)
        {
            //
        }


    }




}
