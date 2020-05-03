package dk.grouptwo.view.worker;

import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.worker.CreateWorkerAccountViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

public class CreateWorkerAccountController {
    private ViewHandler viewHandler;
    private CreateWorkerAccountViewModel viewModel;
    private Region root;

    @FXML
    private TextField createAccountEmployeeCPRNumber;
    @FXML
    private TextField createAccountEmployeeFirstName;
    @FXML
    private TextField createAccountEmployeeLastName;
    @FXML
    private DatePicker createAccountEmployeeDatePicker;
    @FXML
    private ComboBox<String> createAccountEmployeeGender;
    @FXML
    private TextField createAccountEmployeeCity;
    @FXML
    private TextField createAccountEmployeePostCode;
    @FXML
    private TextField createAccountEmployeeMobilePhone;
    @FXML
    private ComboBox<String> createAccountEmployeeTaxCard;
    @FXML
    private TextField createAccountEmployeeLanguages;
    @FXML
    private TextArea createAccountEmployeeDescription;
    @FXML
    private TextField createAccountEmployeeEmail;
    @FXML
    private TextField createAccountEmployeePassword;
    @FXML
    private TextField createAccountEmployeeConfirmPassword;
    @FXML
    private Label createAccountEmployeeErrorLabel;

    public void init(ViewHandler viewHandler, CreateWorkerAccountViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        createAccountEmployeeCPRNumber.textProperty().bindBidirectional(viewModel.CPRProperty());
        createAccountEmployeeFirstName.textProperty().bindBidirectional(viewModel.firstNameProperty());
        createAccountEmployeeLastName.textProperty().bindBidirectional(viewModel.lastNameProperty());
        createAccountEmployeeDatePicker.valueProperty().bindBidirectional(viewModel.birthdayProperty());
        createAccountEmployeeGender.valueProperty().bindBidirectional(viewModel.genderProperty());
        createAccountEmployeeCity.textProperty().bindBidirectional(viewModel.cityProperty());
        createAccountEmployeePostCode.textProperty().bindBidirectional(viewModel.postCodeProperty());
        createAccountEmployeeMobilePhone.textProperty().bindBidirectional(viewModel.mobilePhoneProperty());
        createAccountEmployeeTaxCard.valueProperty().bindBidirectional(viewModel.taxCardProperty());
        createAccountEmployeeLanguages.textProperty().bindBidirectional(viewModel.languagesProperty());
        createAccountEmployeeDescription.textProperty().bindBidirectional(viewModel.descriptionProperty());
        createAccountEmployeeEmail.textProperty().bindBidirectional(viewModel.emailProperty());
        createAccountEmployeeConfirmPassword.textProperty().bindBidirectional(viewModel.confirmPasswordProperty());
        createAccountEmployeePassword.textProperty().bindBidirectional(viewModel.passwordProperty());
        createAccountEmployeeErrorLabel.textProperty().bind(viewModel.errorProperty());
    }

    @FXML
    public void createAccountEmployeeBackButtonPressed() {
        viewHandler.openView("createAccount");
    }

    @FXML
    public void createAccountEmployeeNextButtonPressed() {
        if (viewModel.createWorkerAccount())
            viewHandler.openView("signInWorker");
    }

    public void reset() {
        viewModel.reset();
    }

    public Region getRoot() {
        return root;
    }
}
