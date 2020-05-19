package dk.grouptwo.view.worker;

import dk.grouptwo.utility.StringDoubleConverter;
import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.worker.FindWorkViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.time.LocalDateTime;

public class FindWorkController extends WorkerViewTabController {
    private FindWorkViewModel viewModel;

    @FXML
    private Text findWorkDetailsTitle;

    @FXML
    private Text findWorkDetailsEmployer;

    @FXML
    private Text findWorkDetailsSalary;

    @FXML
    private Text findWorkDetailsStartEndDates;

    @FXML
    private Text findWorkDetailsLocation;

    @FXML
    private Text findWorkDetailsWorkDescription;

    @FXML
    private TableView<WorkTableData> findWorkTable;

    @FXML
    private TableColumn<WorkTableData, String> findWorkJobTitleColumn;

    @FXML
    private TableColumn<WorkTableData, String> findWorkEmployerColumn;

    @FXML
    private TableColumn<WorkTableData, Number> findWorkSalaryColumn;

    @FXML
    private TableColumn<WorkTableData, LocalDateTime> findWorkStartColumn;

    @FXML
    private TableColumn<WorkTableData, LocalDateTime> findWorkEndColumn;

    @FXML
    private TableColumn<WorkTableData, Number> findWorkWorkTimeColumn;

    @FXML
    private TableColumn<WorkTableData, String> findWorkLocationColumn;

    @FXML
    private Button profileButton;

    public void init(ViewHandler viewHandler, FindWorkViewModel viewModel, Region root) {
        super.init(viewHandler, root);
        this.viewModel = viewModel;

        profileButton.setText(viewModel.usernameProperty().get());

        findWorkDetailsTitle.textProperty().bind(viewModel.jobTitleProperty());
        findWorkDetailsEmployer.textProperty().bind(viewModel.employerProperty());
        Bindings.bindBidirectional(findWorkDetailsSalary.textProperty(), viewModel.salaryProperty(), new StringDoubleConverter(0));
        findWorkDetailsStartEndDates.textProperty().bind(viewModel.startEndDatesProperty());
        findWorkDetailsLocation.textProperty().bind(viewModel.locationProperty());
        findWorkDetailsWorkDescription.textProperty().bind(viewModel.descriptionProperty());

        findWorkJobTitleColumn.setCellValueFactory(cellData -> cellData.getValue().jobTitleProperty());
        findWorkEmployerColumn.setCellValueFactory(cellData -> cellData.getValue().employerProperty());
        findWorkSalaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty());
        findWorkStartColumn.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());
        findWorkEndColumn.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
        findWorkWorkTimeColumn.setCellValueFactory(cellData -> cellData.getValue().workTimeProperty());
        findWorkLocationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        findWorkTable.setItems(viewModel.getList());

        findWorkTable.onMouseClickedProperty().set(evt -> {
            try {
                viewModel.selectItem(findWorkTable.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                //
            }
        });
    }

    @FXML
    void findWorkApplyButtonPressed() {
        try {
            viewModel.apply(findWorkTable.getSelectionModel().getSelectedItem());
        } catch (NullPointerException e) {
            //
        }
    }

    public void reset() {
        viewModel.reset();
    }

}
