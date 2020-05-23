package dk.grouptwo;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        new ViewHandler(new ViewModelFactory(new ModelManager())).start(stage);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }

}