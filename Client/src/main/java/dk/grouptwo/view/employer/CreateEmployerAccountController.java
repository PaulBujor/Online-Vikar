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
    }

    @FXML
    public void createAccountEmployerBackButtonPressed(ActionEvent actionEvent) {
        viewHandler.openView("createAccount");
    }

    @FXML
    public void createAccountEmployerNextButtonPressed(ActionEvent actionEvent) {
        viewModel.createEmployerAccount();
    }

    public void reset() {
        //todo clear fields
    }

    public Region getRoot() {
        return root;
    }
}