package dk.grouptwo.view.employer;

import dk.grouptwo.utility.StringDoubleConverter;
import dk.grouptwo.utility.StringIntegerConverter;
import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.WorkOfferViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

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
    private TextField workOfferLocation;

    @FXML
    private TextArea workOfferWorkDescription;

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
        workOfferLocation.textProperty().bindBidirectional(viewModel.locationProperty());
        workOfferWorkDescription.textProperty().bindBidirectional(viewModel.locationProperty());
        //todo above
    }

    public void reset(WorkTableData data) {
        viewModel.reset(data);
    }

    @FXML
    void createWorkOfferSaveButtonPressed() {
        viewModel.save();
    }


}
