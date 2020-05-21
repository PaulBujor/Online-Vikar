package dk.grouptwo.view.worker;

import dk.grouptwo.utility.LicenseTableData;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.worker.WorkerProfileViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.time.LocalDate;

public class WorkerProfileController extends WorkerViewTabController {
    private WorkerProfileViewModel viewModel;

    @FXML
    private TableView<LicenseTableData> workerProfileLicenseTable;

    @FXML
    private TableColumn<LicenseTableData, String> workerProfileLicenseColumn;

    @FXML
    private TableColumn<LicenseTableData, String> workerProfileCategoryColumn;

    @FXML
    private TableColumn<LicenseTableData, String> workerProfileLicenseNumberColumn;

    @FXML
    private TableColumn<LicenseTableData, LocalDate> workerProfileIssueDateColumn;

    @FXML
    private TableColumn<LicenseTableData, LocalDate> workerProfileIssueExpiryColumn;

    @FXML
    private TextField workerProfileCPR;

    @FXML
    private TextField workerProfileFirstName;

    @FXML
    private TextField workerProfileLastName;

    @FXML
    private DatePicker workerProfileDatePicker;

    @FXML
    private ComboBox<String> workerProfileGender;

    @FXML
    private TextField workerProfileCity;

    @FXML
    private TextField workerProfilePostCode;

    @FXML
    private TextField workerProfileMobilePhone;

    @FXML
    private ComboBox<String> workerProfileTaxCard;

    @FXML
    private TextField workerProfileLanguages;

    @FXML
    private TextArea workerProfileDescription;

    @FXML
    private TextField workerProfileEmail;

    @FXML
    private TextField workerProfileCurrentPassword;

    @FXML
    private TextField workerProfileNewPassword;

    @FXML
    private TextField workerProfileConfirmPassword;

    @FXML
    private TextField workerProfileLicense;

    @FXML
    private TextField workerProfileCategory;

    @FXML
    private TextField workerProfileLicenseNumber;

    @FXML
    private DatePicker workerProfileIssueDate;

    @FXML
    private DatePicker workerProfileExpiryDate;

    @FXML
    private Label workerProfileErrorLabel;

    @FXML
    private Label workerProfileNameLabel;

    @FXML
    private Button profileButton;

    @FXML
    private TextField workerProfileCountry;

    @FXML
    private TextField workerProfileStreet;

    public void init(ViewHandler viewHandler, WorkerProfileViewModel viewModel, Region root) {
        super.init(viewHandler, root);
        this.viewModel = viewModel;
        workerProfileNameLabel.setText(viewModel.usernameProperty().get());

        workerProfileCPR.textProperty().bind(viewModel.CPRProperty());
        workerProfileFirstName.textProperty().bindBidirectional(viewModel.firstNameProperty());
        workerProfileLastName.textProperty().bindBidirectional(viewModel.lastNameProperty());
        workerProfileDatePicker.valueProperty().bindBidirectional(viewModel.birthdayProperty());
        workerProfileGender.valueProperty().bindBidirectional(viewModel.genderProperty());
        workerProfileCountry.textProperty().bindBidirectional(viewModel.countryProperty());
        workerProfileStreet.textProperty().bindBidirectional(viewModel.streetProperty());
        workerProfileCity.textProperty().bindBidirectional(viewModel.cityProperty());
        workerProfilePostCode.textProperty().bindBidirectional(viewModel.postCodeProperty());
        workerProfileMobilePhone.textProperty().bindBidirectional(viewModel.mobilePhoneProperty());
        workerProfileTaxCard.valueProperty().bindBidirectional(viewModel.taxCardProperty());
        workerProfileLanguages.textProperty().bindBidirectional(viewModel.languagesProperty());
        workerProfileDescription.textProperty().bindBidirectional(viewModel.descriptionProperty());
        workerProfileEmail.textProperty().bindBidirectional(viewModel.emailProperty());
        workerProfileCurrentPassword.textProperty().bindBidirectional(viewModel.currentPasswordProperty());
        workerProfileNewPassword.textProperty().bindBidirectional(viewModel.newPasswordProperty());
        workerProfileConfirmPassword.textProperty().bindBidirectional(viewModel.confirmPasswordProperty());
        workerProfileErrorLabel.textProperty().bind(viewModel.errorProperty());
        workerProfileLicense.textProperty().bindBidirectional(viewModel.licenseTitleProperty());
        workerProfileCategory.textProperty().bindBidirectional(viewModel.licenseCategoryProperty());
        workerProfileLicenseNumber.textProperty().bindBidirectional(viewModel.licenseNumberProperty());
        workerProfileIssueDate.valueProperty().bindBidirectional(viewModel.licenseIssueDateProperty());
        workerProfileExpiryDate.valueProperty().bindBidirectional(viewModel.licenseExpiryDateProperty());

        workerProfileLicenseColumn.setCellValueFactory(cellData -> cellData.getValue().licenseTitleProperty());
        workerProfileCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().licenseCategoryProperty());
        workerProfileLicenseNumberColumn.setCellValueFactory(cellData -> cellData.getValue().licenseNumberProperty());
        workerProfileIssueDateColumn.setCellValueFactory(cellData -> cellData.getValue().licenseIssueDateProperty());
        workerProfileIssueExpiryColumn.setCellValueFactory(cellData -> cellData.getValue().licenseExpiryDateProperty());
        workerProfileLicenseTable.setItems(viewModel.getList());
    }

    @FXML
    void workerProfileAddButtonPressed() {
        viewModel.addLicense();
    }

    @FXML
    void workerProfileRemoveButtonPressed() {
        try {
            viewModel.removeLicense(workerProfileLicenseTable.getSelectionModel().getSelectedItem());
        } catch (NullPointerException e) {
            //
        }
    }

    @FXML
    void workerProfileSaveButtonPressed() {
        viewModel.saveChangesWorker();
    }

    public void reset() {
        viewModel.reset();
    }
}
