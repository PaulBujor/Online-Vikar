package dk.grouptwo.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class CreateAccountController {
    private ViewHandler viewHandler;
    private Region root;

    public void init(ViewHandler viewHandler, Region root) {
        this.viewHandler = viewHandler;
        this.root = root;
    }

    @FXML
    public void createAccountEmployerButtonPressed() {
        viewHandler.openView("createEmployer");
    }

    @FXML
    public void createAccountEmployeeButtonPressed() {
        viewHandler.openView("createWorker");
    }

    @FXML
    public void createAccountSingInButtonPressed() {
        viewHandler.openView("signin");
    }

    public Region getRoot() {
        return root;
    }
}
