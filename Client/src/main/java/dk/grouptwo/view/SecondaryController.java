package dk.grouptwo.view;

import java.io.IOException;

import dk.grouptwo.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}