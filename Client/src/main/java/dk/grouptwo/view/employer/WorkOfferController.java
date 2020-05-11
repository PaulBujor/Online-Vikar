package dk.grouptwo.view.employer;

import dk.grouptwo.utility.WorkTableData;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.WorkOfferViewModel;
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
    private TextField workOfferTitleSalary;

    @FXML
    private TextField workOfferStartHour;

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
    private TextField createWorkOfferLocation;

    @FXML
    private TextArea createWorkOfferWorkDescription;

    public void init(ViewHandler viewHandler, WorkOfferViewModel viewModel, Region root) {
        super.init(viewHandler, root);
        this.viewModel = viewModel;


    }

    public void reset(WorkTableData data) {
        viewModel.reset(data);
    }

    @FXML
    void createWorkOfferSaveButtonPressed() {

    }


}
