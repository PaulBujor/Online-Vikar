package dk.grouptwo.view.employer;

import dk.grouptwo.utility.StringDoubleConverter;
import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.WorkViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.time.LocalDateTime;

public class WorkController extends EmployerViewTabController {
    private WorkViewModel viewModel;

    @FXML
    private Text jobsDetailsTitle;

    @FXML
    private Text jobsDetailsEmployer;

    @FXML
    private Text jobsDetailsSalary;

    @FXML
    private Text jobsDetailsStartEndDates;

    @FXML
    private Text jobsDetailsLocation;

    @FXML
    private Text jobsDetailsWorkDescription;

    @FXML
    private TableView<WorkTableData> jobsTable;

    @FXML
    private TableColumn<WorkTableData, String> jobsJobTitleColumn;

    @FXML
    private TableColumn<WorkTableData, String> jobsEmployerColumn;

    @FXML
    private TableColumn<WorkTableData, Number> jobsSalaryColumn;

    @FXML
    private TableColumn<WorkTableData, LocalDateTime> jobsStartColumn;

    @FXML
    private TableColumn<WorkTableData, LocalDateTime> jobsEndColumn;

    @FXML
    private TableColumn<WorkTableData, String> jobsLocationColumn;

    public void init(ViewHandler viewHandler, WorkViewModel viewModel, Region root) {
        super.init(viewHandler, root);
        this.viewModel = viewModel;

        jobsDetailsTitle.textProperty().bind(viewModel.jobTitleProperty());
        jobsDetailsEmployer.textProperty().bind(viewModel.employerProperty());
        Bindings.bindBidirectional(jobsDetailsSalary.textProperty(), viewModel.salaryProperty(), new StringDoubleConverter(0));
        jobsDetailsStartEndDates.textProperty().bind(viewModel.startEndDatesProperty());
        jobsDetailsLocation.textProperty().bind(viewModel.locationProperty());
        jobsDetailsWorkDescription.textProperty().bind(viewModel.descriptionProperty());

        jobsJobTitleColumn.setCellValueFactory(cellData -> cellData.getValue().jobTitleProperty());
        jobsEmployerColumn.setCellValueFactory(cellData -> cellData.getValue().employerProperty());
        jobsSalaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty());
        jobsStartColumn.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());
        jobsEndColumn.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
        jobsLocationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        jobsTable.setItems(viewModel.getList());

        jobsTable.onMouseClickedProperty().set(evt -> {
            try {
                viewModel.selectItem(jobsTable.getSelectionModel().getSelectedItem());
            } catch (NullPointerException e) {
                //this should happen when mouse is clicked outside of any items
            }
        });
    }

    @FXML
    void openOffer() {
        viewHandler.openView("workOffer");
    }

    @FXML
    void createWorkOffer() {

    }

    public void reset() {
        viewModel.reset();
    }

}
