package dk.grouptwo.view.employer;

import java.io.IOException;

import dk.grouptwo.App;
import javafx.fxml.FXML;

public class CreateEmployerAccountController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}