package dk.grouptwo.view;

import java.io.IOException;

import dk.grouptwo.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
