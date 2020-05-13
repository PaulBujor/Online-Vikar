package dk.grouptwo.view.employer;

import dk.grouptwo.utility.StringDoubleConverter;
import dk.grouptwo.utility.StringIntegerConverter;
import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.utility.WorkersTableData;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.WorkOfferViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

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
    private TextArea error;

    @FXML
    private TextArea workOfferWorkDescription;

    @FXML
    private TableView<WorkersTableData> workOfferTable;

    @FXML
    private TableColumn<WorkersTableData, String> workOfferFullNameColumn;

    @FXML
    private TableColumn<WorkersTableData, CheckBox> workOfferSelectColumn;

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

        workOfferDateOfBirth.textProperty().bindBidirectional();
        //todo above
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
        WorkersTableData workersTableData = workOfferTable.getSelectionModel().getSelectedItem();
        viewModel.se
    }
}
