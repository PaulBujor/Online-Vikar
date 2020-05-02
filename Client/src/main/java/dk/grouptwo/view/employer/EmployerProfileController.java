package dk.grouptwo.view.employer;

import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.EmployerProfileViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class EmployerProfileController extends EmployerViewTabController {
    private EmployerProfileViewModel viewModel;

    @FXML
    private TextField employerProfileCVR;

    @FXML
    private TextField employerProfileCompany;

    @FXML
    private TextField employerProfileCity;

    @FXML
    private TextField employerProfilePostCode;

    @FXML
    private TextField employerProfileAddress;

    @FXML
    private TextField employerProfileMobilePhone;

    @FXML
    private TextField employerProfileEmail;

    @FXML
    private TextField employerProfileCurrentPassword;

    @FXML
    private TextField employerProfileNewPassword;

    @FXML
    private TextField employerProfileConfirmPassword;

    @FXML
    private Label employerProfileErrorLabel;

    @FXML
    private Label employerProfileCompanyNameLabel;

    public void init(ViewHandler viewHandler, EmployerProfileViewModel viewModel, Region root) {
        super.init(viewHandler, root);
        this.viewModel = viewModel;
    }

    @FXML
    void employerProfileSaveButtonPressed() {
        viewModel.saveChangesEmployer();
    }


}
