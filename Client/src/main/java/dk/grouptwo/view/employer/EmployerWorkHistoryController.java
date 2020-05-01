package dk.grouptwo.view.employer;

import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.EmployerWorkHistoryViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

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
    private TableView<?> employerHistoryEmployeesTable;

    @FXML
    private TableColumn<?, ?> employerHistoryEmployeesTableCPRColumn;

    @FXML
    private TableColumn<?, ?> employerHistoryEmployeesTableNameColumn;

    @FXML
    private TableView<?> employerHistoryMainTable;

    @FXML
    private TableColumn<?, ?> employerHistoryJobTitleColumn;

    @FXML
    private TableColumn<?, ?> employerHistoryStatusColumn;

    @FXML
    private TableColumn<?, ?> employerHistoryNoEmployesColumn;

    @FXML
    private TableColumn<?, ?> employerHistorySalaryColumn;

    @FXML
    private TableColumn<?, ?> employerHistoryStartColumn;

    @FXML
    private TableColumn<?, ?> employerHistoryWorkTimeColumn;

    @FXML
    private TableColumn<?, ?> employerHistoryLocationColumn;

    //TODO Create Table row classes and connect them with table columns

    public void init(ViewHandler viewHandler, EmployerWorkHistoryViewModel viewModel, Region root) {
        super.init(viewHandler, root);
        this.viewModel = viewModel;
    }
}
