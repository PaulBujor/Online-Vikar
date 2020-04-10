package dk.grouptwo;

import dk.grouptwo.model.Model;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Model model;

    @Override
    public void start(Stage stage) throws IOException {
        model = new Model() {
        };//todo
        new ViewModelFactory(model);
        new ViewHandler(viewModelFactory).start(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}