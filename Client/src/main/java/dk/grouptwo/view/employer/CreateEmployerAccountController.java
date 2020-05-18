package dk.grouptwo.view.employer;

import java.io.IOException;

import dk.grouptwo.App;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.CreateEmployerAccountViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class CreateEmployerAccountController {
    private ViewHandler viewHandler;
    private CreateEmployerAccountViewModel viewModel;
    private Region root;

    @FXML
    private TextField createAccountEmployerCVR;
    @FXML
    private TextField createAccountEmployerCompany;
    @FXML
    private TextField createAccountEmployerCountry;
    @FXML
    private TextField createAccountEmployerCity;
    @FXML
    private TextField createAccountEmployerPostCode;
    @FXML
    private TextField createAccountEmployerAddress;
    @FXML
    private TextField createAccountEmployerMobilePhone;
    @FXML
    private TextField createAccountEmployerEmail;
    @FXML
    private TextField createAccountEmployerPassword;
    @FXML
    private TextField createAccountEmployerConfirmPassword;
    @FXML
    private Label createAccountEmployerErrorLabel;

    public void init(ViewHandler viewHandler, CreateEmployerAccountViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        createAccountEmployerCVR.textProperty().bindBidirectional(viewModel.CVRProperty());
        createAccountEmployerCompany.textProperty().bindBidirectional(viewModel.companyProperty());
        createAccountEmployerCountry.textProperty().bindBidirectional(viewModel.countryProperty());
        createAccountEmployerCity.textProperty().bindBidirectional(viewModel.cityProperty());
        createAccountEmployerPostCode.textProperty().bindBidirectional(viewModel.postCodeProperty());
        createAccountEmployerAddress.textProperty().bindBidirectional(viewModel.addressProperty());
        createAccountEmployerMobilePhone.textProperty().bindBidirectional(viewModel.mobilePhoneProperty());
        createAccountEmployerEmail.textProperty().bindBidirectional(viewModel.emailProperty());
        createAccountEmployerPassword.textProperty().bindBidirectional(viewModel.passwordProperty());
        createAccountEmployerConfirmPassword.textProperty().bindBidirectional(viewModel.confirmPasswordProperty());
        createAccountEmployerErrorLabel.textProperty().bind(viewModel.errorProperty());
    }

    @FXML
    public void createAccountEmployerBackButtonPressed(ActionEvent actionEvent) {
        viewHandler.openView("createAccount");
    }

    @FXML
    public void createAccountEmployerNextButtonPressed(ActionEvent actionEvent) {
        if (viewModel.createEmployerAccount())
            viewHandler.openView("signInEmployer");
    }

    public void reset() {
        viewModel.reset();
    }

    public Region getRoot() {
        return root;
    }
}