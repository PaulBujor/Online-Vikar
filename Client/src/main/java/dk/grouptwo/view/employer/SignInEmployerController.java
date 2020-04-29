package dk.grouptwo.view.employer;

import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.SignInEmployerViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class SignInEmployerController {
    private ViewHandler viewHandler;
    private SignInEmployerViewModel signInEmployerViewModel;
    private Region root;

    public void init(ViewHandler viewHandler, SignInEmployerViewModel signInEmployerViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.signInEmployerViewModel = signInEmployerViewModel;
        this.root = root;
    }

    @FXML
    private TextField signInEmployerCVRTextField;

    @FXML
    private TextField signInEmployerPasswordTextField;

    @FXML
    private Label signInEmployerErrorLabel;

    @FXML
    void backEmployerButtonPressed(ActionEvent event) {
        viewHandler.openView("signin");
    }

    @FXML
    void logInEmployerButtonPressed(ActionEvent event) {

    }

    public void reset() {
        //todo clear fields
    }

    public Region getRoot() {
        return root;
    }

}
