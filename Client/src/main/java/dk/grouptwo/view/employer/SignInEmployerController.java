package dk.grouptwo.view.employer;

import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.SignInEmployerViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class SignInEmployerController {
    private ViewHandler viewHandler;
    private SignInEmployerViewModel viewModel;
    private Region root;

    @FXML
    private TextField signInEmployerCVRTextField;

    @FXML
    private PasswordField signInEmployerPasswordTextField;

    @FXML
    private Label signInEmployerErrorLabel;

    public void init(ViewHandler viewHandler, SignInEmployerViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        signInEmployerCVRTextField.textProperty().bindBidirectional(viewModel.CVRProperty());
        signInEmployerPasswordTextField.textProperty().bindBidirectional(viewModel.passwordProperty());
        signInEmployerErrorLabel.textProperty().bind(viewModel.errorProperty());
    }


    @FXML
    void backEmployerButtonPressed(ActionEvent event) {
        viewHandler.openView("signin");
    }

    @FXML
    void logInEmployerButtonPressed(ActionEvent event) {
        if (viewModel.logInEmployer())
            viewHandler.openView("employerWork");
    }

    public void reset() {
        viewModel.reset();
    }

    public Region getRoot() {
        return root;
    }

}
