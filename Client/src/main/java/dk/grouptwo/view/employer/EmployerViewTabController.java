package dk.grouptwo.view.employer;

import dk.grouptwo.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

/**
 * this is package-private
 * This class is for the tab switcher on the left of the employer client
 * use extends EmployerViewTabController for employer client app controllers
 * (Profile, Work and History Controllers)
 */
class EmployerViewTabController {
    private ViewHandler viewHandler;
    private Region root;

    public void init(ViewHandler viewHandler, Region root){
        this.viewHandler = viewHandler;
        this.root = root;
    }

    @FXML
    public void switchToProfile() {
        viewHandler.openView("employerProfile");
    }

    @FXML
    public void switchToWork() {
        viewHandler.openView("employerWork");
    }

    @FXML
    public void switchToHistory() {
        viewHandler.openView("employerHistory");
    }

    public void reset() {

    }

    public Region getRoot() {
        return root;
    }
}
