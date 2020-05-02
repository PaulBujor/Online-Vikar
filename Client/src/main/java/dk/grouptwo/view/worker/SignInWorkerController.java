package dk.grouptwo.view.worker;

import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.worker.SignInWorkerViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class SignInWorkerController {
    private ViewHandler viewHandler;
    private SignInWorkerViewModel viewModel;
    private Region root;

    @FXML
    private TextField signInEmployerCPRTextField;

    @FXML
    private TextField signInEmployeePasswordTextField;

    @FXML
    private Label signInEmployeeErrorLabel;

    public void init(ViewHandler viewHandler, SignInWorkerViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        signInEmployerCPRTextField.textProperty().bindBidirectional(viewModel.CPRProperty());
        signInEmployeePasswordTextField.textProperty().bindBidirectional(viewModel.passwordProperty());
        signInEmployeeErrorLabel.textProperty().bind(viewModel.errorProperty());
    }

    @FXML
    void backEmployeeButtonPressed(ActionEvent event) {
        viewHandler.openView("signin");
    }

    @FXML
    void logInEmployeeButtonPressed(ActionEvent event) {
        if(viewModel.logInWorker())
            viewHandler.openView("findWork");
    }

    public void reset() {
        viewModel.reset();
    }

    public Region getRoot() {
        return root;
    }

}
