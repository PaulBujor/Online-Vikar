package dk.grouptwo.view.worker;

import java.io.IOException;

import dk.grouptwo.App;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.worker.CreateWorkerAccountViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class CreateWorkerAccountController {
    private ViewHandler viewHandler;
    private CreateWorkerAccountViewModel viewModel;
    private Region root;

    public void init(ViewHandler viewHandler, CreateWorkerAccountViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
    }

    @FXML
    private TextField createAccountEmployeeCPRNumber;
    @FXML
    private TextField createAccountEmployeeFirstName;
    @FXML
    private TextField createAccountEmployeeLastName;
    @FXML
    private DatePicker createAccountEmployeeDatePicker;
    @FXML
    private ComboBox createAccountEmployeeGender;
    @FXML
    private TextField createAccountEmployeeCity;
    @FXML
    private TextField createAccountEmployeePostCode;
    @FXML
    private TextField createAccountEmployeeMobilePhone;
    @FXML
    private ComboBox createAccountEmployeeTaxCard;
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
    public void createAccountEmployeeBackButtonPressed() {
        viewHandler.openView("createAccount");
    }

    @FXML
    public void createAccountEmployeeNextButtonPressed() {
    }

    public void reset() {
        //todo clear fields
    }

    public Region getRoot() {
        return root;
    }
}
