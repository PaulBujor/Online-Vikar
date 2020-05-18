package dk.grouptwo.view.employer;

import dk.grouptwo.utility.StringDoubleConverter;
import dk.grouptwo.utility.StringIntegerConverter;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.CreateWorkOfferViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class CreateWorkOfferController extends EmployerViewTabController {
    private CreateWorkOfferViewModel viewModel;

    @FXML
    private TextField createWorkOfferTitle;

    @FXML
    private TextField createWorkOfferTitleSalary;

    @FXML
    private TextField createWorkOfferStartHour;

    @FXML
    private TextField createWorkOfferStartMinutes;

    @FXML
    private DatePicker createWorkOfferStartDatePicker;

    @FXML
    private TextField createWorkOfferEndHour;

    @FXML
    private TextField createWorkOfferEndMinutes;

    @FXML
    private DatePicker createWorkOfferEndDate;

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
    private TextArea createWorkOfferWorkDescription;


    public void init(ViewHandler viewHandler, CreateWorkOfferViewModel viewModel, Region root) {
        super.init(viewHandler, root);
        this.viewModel = viewModel;

        createWorkOfferTitle.textProperty().bindBidirectional(viewModel.titleProperty());
        Bindings.bindBidirectional(createWorkOfferTitleSalary.textProperty(), viewModel.salaryProperty(), new StringDoubleConverter(0));
        Bindings.bindBidirectional(createWorkOfferStartHour.textProperty(), viewModel.startHourProperty(), new StringIntegerConverter(0));
        Bindings.bindBidirectional(createWorkOfferStartMinutes.textProperty(), viewModel.startMinutesProperty(), new StringIntegerConverter(0));
        createWorkOfferStartDatePicker.valueProperty().bindBidirectional(viewModel.startDateProperty());
        Bindings.bindBidirectional(createWorkOfferEndHour.textProperty(), viewModel.endHourProperty(), new StringIntegerConverter(0));
        Bindings.bindBidirectional(createWorkOfferEndMinutes.textProperty(), viewModel.endMinutesProperty(), new StringIntegerConverter(0));
        createWorkOfferEndDate.valueProperty().bindBidirectional(viewModel.endDateProperty());
        country.textProperty().bindBidirectional(viewModel.countryProperty());
        city.textProperty().bindBidirectional(viewModel.cityProperty());
        postCode.textProperty().bindBidirectional(viewModel.postCodeProperty());
        street.textProperty().bindBidirectional(viewModel.streetProperty());
        createWorkOfferWorkDescription.textProperty().bindBidirectional(viewModel.descriptionProperty());
        Bindings.bindBidirectional(workersNeeded.textProperty(), viewModel.workersNeededProperty(), new StringIntegerConverter(0));
        error.textProperty().bind(viewModel.errorProperty());
    }

    @FXML
    private void createWorkOffer() {
        if (viewModel.create())
            viewHandler.openView("employerWork");
    }

    public void reset() {
        viewModel.reset();
    }
}
