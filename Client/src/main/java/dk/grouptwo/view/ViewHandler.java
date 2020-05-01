package dk.grouptwo.view;

import dk.grouptwo.view.employer.*;
import dk.grouptwo.view.worker.*;
import dk.grouptwo.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler {
    private SignInController signInController;
    private CreateAccountController createAccountController;

    //worker controllers
    private CreateWorkerAccountController createWorkerAccountController;
    private FindWorkController findWorkController;
    private UpcomingWorkController upcomingWorkController;
    private WorkerProfileController workerProfileController;
    private WorkerWorkHistoryController workerWorkHistoryController;
    private CreateWorkOfferController createWorkOfferController;
    private SignInWorkerController signInWorkerController;

    //employer controllers
    private CreateEmployerAccountController createEmployerAccountController;
    private EmployerProfileController employerProfileController;
    private WorkController workController;
    private EmployerWorkHistoryController employerWorkHistoryController;
    private SignInEmployerController signInEmployerController;

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
                root = loadSignInView("sign_in.fxml");
                break;
            case "createAccount":
                root = loadCreateAccountView("create_account.fxml");
                break;
            case "createEmployer":
                root = loadCreateEmployerView("create_account_employer.fxml");
                break;
            case "createWorker":
                root = loadCreateWorkerView("create_account_employee.fxml");
                break;
            case "signInEmployer":
                root = loadSignInEmployerView("sign_in_employer.fxml");
                break;
            case "signInWorker":
                root = loadSignInWorkerView("sign_in_employee.fxml");
                break;
            case "findWork":
                root = loadFindWorkView("find_work.fxml");
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
        return signInController.getRoot();
    }

    private Region loadCreateAccountView(String fxmlFile) {
        Region root = null;
        if (createAccountController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/grouptwo/" + fxmlFile));
                root = loader.load();
                createAccountController = loader.getController();
                createAccountController.init(this, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return createAccountController.getRoot();
    }

    private Region loadCreateEmployerView(String fxmlFile) {
        Region root = null;
        if (createEmployerAccountController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/grouptwo/" + fxmlFile));
                root = loader.load();
                createEmployerAccountController = loader.getController();
                createEmployerAccountController.init(this, viewModelFactory.getCreateEmployerAccountViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            createEmployerAccountController.reset();
        }
        return createEmployerAccountController.getRoot();
    }

    private Region loadCreateWorkerView(String fxmlFile) {
        Region root = null;
        if (createWorkerAccountController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/grouptwo/" + fxmlFile));
                root = loader.load();
                createWorkerAccountController = loader.getController();
                createWorkerAccountController.init(this, viewModelFactory.getCreateWorkerAccountViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            createWorkerAccountController.reset();
        }
        return createWorkerAccountController.getRoot();
    }

    private Region loadSignInEmployerView(String fxmlFile) {
        Region root = null;
        if (signInEmployerController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/grouptwo/" + fxmlFile));
                root = loader.load();
                signInEmployerController = loader.getController();
                signInEmployerController.init(this, viewModelFactory.getSignInEmployerViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            signInEmployerController.reset();
        }
        return signInEmployerController.getRoot();
    }

    private Region loadSignInWorkerView(String fxmlFile) {
        Region root = null;
        if (signInWorkerController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/grouptwo/" + fxmlFile));
                root = loader.load();
                signInWorkerController = loader.getController();
                signInWorkerController.init(this, viewModelFactory.getSignInWorkerViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            signInWorkerController.reset();
        }
        return signInWorkerController.getRoot();
    }

    private Region loadFindWorkView(String fxmlFile) {
        Region root = null;
        if (findWorkController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/grouptwo/" + fxmlFile));
                root = loader.load();
                findWorkController = loader.getController();
                findWorkController.init(this, viewModelFactory.getSignInWorkerViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            findWorkController.reset();
        }
        return findWorkController.getRoot();
    }

    //todo loadviews
}
