package dk.grouptwo.view.employer;

import dk.grouptwo.utility.StringDoubleConverter;
import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.utility.WorkersTableData;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.EmployerWorkHistoryViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.time.LocalDateTime;

public class EmployerWorkHistoryController extends EmployerViewTabController {
    private EmployerWorkHistoryViewModel viewModel;

    @FXML
    private Text employerHistoryDetailsTitle;

    @FXML
    private Text employerHistoryDetailsSalary;

    @FXML
    private Text employerHistoryDetailsStartEndDates;

    @FXML
    private Text employerHistoryDetailsLocation;

    @FXML
    private Text employerHistoryDetailsWorkDescription;

    @FXML
    private TableView<WorkersTableData> employerHistoryEmployeesTable;

    @FXML
    private TableColumn<WorkersTableData, String> employerHistoryEmployeesTableCPRColumn;

    @FXML
    private TableColumn<WorkersTableData, String> employerHistoryEmployeesTableNameColumn;

    @FXML
    private TableView<WorkTableData> employerHistoryMainTable;

    @FXML
    private TableColumn<WorkTableData, String> employerHistoryJobTitleColumn;

    @FXML
    private TableColumn<WorkTableData, String> employerHistoryStatusColumn;

    @FXML
    private TableColumn<WorkTableData, Number> employerHistoryNoEmployeesColumn;

    @FXML
    private TableColumn<WorkTableData, Number> employerHistorySalaryColumn;

    @FXML
    private TableColumn<WorkTableData, LocalDateTime> employerHistoryStartColumn;

    @FXML
    private TableColumn<WorkTableData, LocalDateTime> employerHistoryEndColumn;

    @FXML
    private TableColumn<WorkTableData, String> employerHistoryLocationColumn;

    public void init(ViewHandler viewHandler, EmployerWorkHistoryViewModel viewModel, Region root) {
        super.init(viewHandler, root);
        this.viewModel = viewModel;

        employerHistoryDetailsTitle.textProperty().bind(viewModel.jobTitleProperty());
        Bindings.bindBidirectional(employerHistoryDetailsSalary.textProperty(), viewModel.salaryProperty(), new StringDoubleConverter(0));
        employerHistoryDetailsStartEndDates.textProperty().bind(viewModel.datesProperty());
        employerHistoryDetailsLocation.textProperty().bind(viewModel.locationProperty());
        employerHistoryDetailsWorkDescription.textProperty().bind(viewModel.descriptionProperty());

        employerHistoryJobTitleColumn.setCellValueFactory(cellData -> cellData.getValue().jobTitleProperty());
        employerHistoryStatusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        employerHistoryNoEmployeesColumn.setCellValueFactory(cellData -> cellData.getValue().numberOfWorkersProperty());
        employerHistorySalaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty());
        employerHistoryStartColumn.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());
        employerHistoryEndColumn.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
        employerHistoryLocationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        employerHistoryMainTable.setItems(viewModel.getListHistory());

        employerHistoryEmployeesTableCPRColumn.setCellValueFactory(cellData -> cellData.getValue().CPRProperty());
        employerHistoryEmployeesTableNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        employerHistoryEmployeesTable.setItems(viewModel.getListWorkers());
    }

    @FXML
    public void employerHistoryJobSelected() {
        try {
            WorkTableData workTableData = employerHistoryMainTable.getSelectionModel().getSelectedItem();
            viewModel.selectJob(workTableData);
        } catch (NullPointerException e) {
            //
        }
    }

    public void reset() {
        viewModel.reset();
    }
}
