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

    public void init(ViewHandler viewHandler, SignInWorkerViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
    }

    @FXML
    private TextField signInEmployerCPRTextField;

    @FXML
    private TextField signInEmployeePasswordTextField;

    @FXML
    private Label signInEmployeeErrorLabel;

    @FXML
    void backEmployeeButtonPressed(ActionEvent event) {
        viewHandler.openView("signin");
    }

    @FXML
    void logInEmployeeButtonPressed(ActionEvent event) {
        //TODO change to viewmodel.login, in viewmodel have booleanproperty connect, add listener and when it fires change window
        viewHandler.openView("findWork");
        viewModel.logInWorker();
    }

    public void reset() {
        //todo clear fields
    }

    public Region getRoot() {
        return root;
    }

}
