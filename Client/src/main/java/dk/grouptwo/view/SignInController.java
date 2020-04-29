package dk.grouptwo.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class SignInController {
    //todo no viewmodel, just reference to viewhandler to switch to correct controller
    private ViewHandler viewHandler;
    private Region root;

    public void init(ViewHandler viewHandler, Region root) {
        this.viewHandler = viewHandler;
        this.root = root;
    }

    @FXML
    public void signInCreateAccountButtonPressed() {
        viewHandler.openView("createAccount");
    }

    @FXML
    public void singInEmployerButtonPressed() {
        viewHandler.openView("signInEmployer");
    }

    @FXML
    public void signInEmployeeButtonPressed() {
        viewHandler.openView("signInWorker");
    }

    public Region getRoot() {
        return root;
    }
}
