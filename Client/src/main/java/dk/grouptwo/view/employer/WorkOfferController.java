package dk.grouptwo.view.employer;

import dk.grouptwo.utility.StringDoubleConverter;
import dk.grouptwo.utility.StringIntegerConverter;
import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.utility.WorkersTableData;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.WorkOfferViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.util.Optional;

public class WorkOfferController extends EmployerViewTabController {
    private WorkOfferViewModel viewModel;

    @FXML
    private TextField workOfferTitle;

    @FXML
    private TextField workOfferSalary;

    @FXML
    private TextField workOfferStartHour;

    @FXML
    private TextField workOfferStartMinutes;

    @FXML
    private DatePicker workOfferStartDatePicker;

    @FXML
    private TextField workOfferEndHour;

    @FXML
    private TextField workOfferEndMinutes;

    @FXML
    private DatePicker workOfferEndDate;

    @FXML
    private TextField workersNeeded;

    @FXML
    private TextField country;

    @FXML
    private TextField city;

    @FXML
    private TextField postCode;

    @FXML
    private TextField street;

    @FXML
    private Label error;

    @FXML
    private TextArea workOfferWorkDescription;

    @FXML
    private TableView<WorkersTableData> workOfferTable;

    @FXML
    private TableColumn<WorkersTableData, String> workOfferFullNameColumn;

    @FXML
    private TableColumn<WorkersTableData, Boolean> workOfferSelectColumn;

    @FXML
    private Text workOfferDateOfBirth;

    @FXML
    private Text workOfferGender;

    @FXML
    private Text workOfferLanguages;

    @FXML
    private Text workOfferWorkerDescription;

    @FXML
    private Text workOfferLicenses;


    //TODO add select worker table and functionality

    public void init(ViewHandler viewHandler, WorkOfferViewModel viewModel, Region root) {
        super.init(viewHandler, root);
        this.viewModel = viewModel;

        workOfferTitle.textProperty().bindBidirectional(viewModel.titleProperty());
        Bindings.bindBidirectional(workOfferSalary.textProperty(), viewModel.salaryProperty(), new StringDoubleConverter(0));
        Bindings.bindBidirectional(workOfferStartHour.textProperty(), viewModel.startHourProperty(), new StringIntegerConverter(0));
        Bindings.bindBidirectional(workOfferStartMinutes.textProperty(), viewModel.startMinutesProperty(), new StringIntegerConverter(0));
        workOfferStartDatePicker.valueProperty().bindBidirectional(viewModel.startDateProperty());
        Bindings.bindBidirectional(workOfferEndHour.textProperty(), viewModel.endHourProperty(), new StringIntegerConverter(0));
        Bindings.bindBidirectional(workOfferEndMinutes.textProperty(), viewModel.endMinutesProperty(), new StringIntegerConverter(0));
        workOfferEndDate.valueProperty().bindBidirectional(viewModel.endDateProperty());
        country.textProperty().bindBidirectional(viewModel.countryProperty());
        city.textProperty().bindBidirectional(viewModel.cityProperty());
        postCode.textProperty().bindBidirectional(viewModel.postCodeProperty());
        street.textProperty().bindBidirectional(viewModel.streetProperty());
        workOfferWorkDescription.textProperty().bindBidirectional(viewModel.descriptionProperty());
        Bindings.bindBidirectional(workersNeeded.textProperty(), viewModel.workersNeededProperty(), new StringIntegerConverter(0));
        error.textProperty().bind(viewModel.errorProperty());

        workOfferDateOfBirth.textProperty().bind(viewModel.workerDateOfBirthProperty());
        workOfferGender.textProperty().bind(viewModel.workerGenderProperty());
        workOfferLanguages.textProperty().bind(viewModel.workerLanguagesProperty());
        workOfferWorkerDescription.textProperty().bind(viewModel.workerDescriptionProperty());
        workOfferLicenses.textProperty().bind(viewModel.workerLicensesProperty());

        workOfferFullNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        workOfferSelectColumn.setCellValueFactory(cellData -> cellData.getValue().selectedForWorkProperty());
        workOfferSelectColumn.setCellFactory(x -> new CheckBoxTableCell<>());
        //todo needs testing
        workOfferTable.setItems(viewModel.getList());
    }

    public void reset(WorkTableData data) {
        viewModel.reset(data);
    }

    @FXML
    void createWorkOfferSaveButtonPressed() {
        if (viewModel.save())
            viewHandler.openView("employerWork");
    }

    @FXML
    void workOfferWorkerSelected() {
        try {
            WorkersTableData workersTableData = workOfferTable.getSelectionModel().getSelectedItem();
            viewModel.selectWorker(workersTableData);
        } catch (NullPointerException e)
        {
            //
        }

    }

    //https://code.makery.ch/blog/javafx-dialogs-official/
    @FXML
    void cancelWork() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Work Confirmation");
        alert.setHeaderText("You are about to cancel a work offer");
        alert.setContentText("Are you sure you want to cancel this work offer?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            viewModel.cancel();
            viewHandler.openView("employerWork");
        }
    }
}
