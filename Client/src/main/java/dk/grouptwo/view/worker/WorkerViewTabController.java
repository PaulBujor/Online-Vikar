package dk.grouptwo.view.worker;

import dk.grouptwo.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

/**
 * this is package-private
 * This class is for the tab switcher on the left of the worker client
 * use extends WorkerViewTabController for worker client app controllers
 * (Profile, Find Work, Upcoming and History Controllers)
 */
class WorkerViewTabController {
    private ViewHandler viewHandler;
    private Region root;

    public void init(ViewHandler viewHandler, Region root){
        this.viewHandler = viewHandler;
        this.root = root;
    }

    @FXML
    public void switchToProfile() {
        viewHandler.openView("workerProfile");
    }

    @FXML
    public void switchToFindWork() {
        viewHandler.openView("findWork");
    }

    @FXML
    public void switchToUpcoming() {
        viewHandler.openView("upcomingWork");
    }

    @FXML
    public void switchToHistory() {
        viewHandler.openView("workerHistory");
    }

    //todo log out

    public void reset() {

    }

    public Region getRoot() {
        return root;
    }
}
