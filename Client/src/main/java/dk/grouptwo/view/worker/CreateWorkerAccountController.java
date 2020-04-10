package dk.grouptwo.view.worker;

import java.io.IOException;

import dk.grouptwo.App;
import javafx.fxml.FXML;

public class CreateWorkerAccountController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
