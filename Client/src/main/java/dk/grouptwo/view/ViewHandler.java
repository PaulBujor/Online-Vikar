package dk.grouptwo.view;

import dk.grouptwo.view.employer.*;
import dk.grouptwo.view.worker.FindWorkController;
import dk.grouptwo.view.worker.UpcomingWorkController;
import dk.grouptwo.view.worker.WorkerWorkHistoryController;
import dk.grouptwo.view.worker.WorkerProfileController;
import dk.grouptwo.viewmodel.ViewModelFactory;
import dk.grouptwo.viewmodel.worker.CreateWorkerAccountViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler {
    private SignInController signInController;
    private CreateAccountController createAccountController;

    //worker controllers
    private CreateWorkerAccountViewModel createWorkerAccountViewModel;
    private FindWorkController findWorkController;
    private UpcomingWorkController upcomingWorkController;
    private WorkerProfileController workerProfileController;
    private WorkerWorkHistoryController workerWorkHistoryController;
    private CreateWorkOfferController createWorkOfferController;

    //employer controllers
    private CreateEmployerAccountController createEmployerAccountController;
    private EmployerProfileController employerProfileController;
    private WorkController workController;
    private EmployerWorkHistoryController employerWorkHistoryController;

    private ViewModelFactory viewModelFactory;
    private Scene currentScene;
    private Stage primaryStage;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView("signin");
    }

    public void openView(String id) {
        Region root = null;
        switch (id) {
            case "signin":
                root = loadSignInView("sing_in.fxml");
                break;
        }
        currentScene.setRoot(root);
        String title = "VikarOnline";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.show();
    }

    public void closeView() {
        primaryStage.close();
    }

    private Region loadSignInView(String fxmlFile) {
        Region root = null;
        if (signInController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/grouptwo/" + fxmlFile));
                root = loader.load();
                signInController = loader.getController();
                signInController.init(this, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null; //signInController.getRoot();
    }

    //todo loadviews
}
