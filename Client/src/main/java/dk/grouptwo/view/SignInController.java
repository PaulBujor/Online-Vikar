package dk.grouptwo.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class SignInController {
    //todo no viewmodel, just reference to viewhandler to switch to correct controller
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, Region root) {
        this.viewHandler = viewHandler;
    }
    @FXML
    public void signInCreateAccountButtonPressed()
    {
    }
    @FXML
    public void singInEmployerButtonPressed()
    {
    }
    @FXML
    public void signInEmployeeButtonPressed()
    {
    }

    //todo button methods * 3

}
