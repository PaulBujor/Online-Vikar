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

        employerProfileCVR.textProperty().bindBidirectional(viewModel.CVRProperty());
        employerProfileCompany.textProperty().bindBidirectional(viewModel.companyProperty());
        employerProfileCompanyNameLabel.textProperty().bind(viewModel.companyProperty());
        employerProfileCity.textProperty().bindBidirectional(viewModel.cityProperty());
        employerProfilePostCode.textProperty().bindBidirectional(viewModel.postCodeProperty());
        employerProfileAddress.textProperty().bindBidirectional(viewModel.addressProperty());
        employerProfileMobilePhone.textProperty().bindBidirectional(viewModel.mobilePhoneProperty());
        employerProfileEmail.textProperty().bindBidirectional(viewModel.emailProperty());
        employerProfileCurrentPassword.textProperty().bindBidirectional(viewModel.currentPasswordProperty());
        employerProfileNewPassword.textProperty().bindBidirectional(viewModel.newPasswordProperty());
        employerProfileConfirmPassword.textProperty().bindBidirectional(viewModel.confirmPasswordProperty());
        employerProfileErrorLabel.textProperty().bindBidirectional(viewModel.errorProperty());
    }

    @FXML
    void employerProfileSaveButtonPressed() {
        viewModel.saveChangesEmployer();
    }

    public void reset() {
        viewModel.reset();
    }


}
