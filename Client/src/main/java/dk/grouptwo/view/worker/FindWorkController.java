package dk.grouptwo.view.worker;

import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.worker.FindWorkViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class FindWorkController extends WorkerViewTabController {
    private ViewHandler viewHandler;
    private FindWorkViewModel viewModel;
    private Region root;

    @FXML
    private Text findWorkDetailsTitle;

    @FXML
    private Text findWorkDetailsEmployer;

    @FXML
    private Text findWorkDetailsSalary;

    @FXML
    private Text findWorkDetailsStartEndDates;

    @FXML
    private Text findWorkDetailsLocation;

    @FXML
    private Text findWorkDetailsWorkDescription;

    @FXML
    private TableView<?> findWorkTable;

    @FXML
    private TableColumn<?, ?> findWorkJobTitleColumn;

    @FXML
    private TableColumn<?, ?> findWorkEmployerColumn;

    @FXML
    private TableColumn<?, ?> findWorkSalaryColumn;

    @FXML
    private TableColumn<?, ?> findWorkStartColumn;

    @FXML
    private TableColumn<?, ?> findWorkWorkTimeColumn;

    @FXML
    private TableColumn<?, ?> findWorkLocationColumn;

    public void init(ViewHandler viewHandler, FindWorkViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
    }

    @FXML
    void findWorkApplyButtonPressed() {

    }

    @FXML
    void findWorkFindWorkButtonPressed() {

    }

    @FXML
    void findWorkHistoryButtonPressed() {

    }

    @FXML
    void findWorkUpcomingButtonPressed() {

    }

    @FXML
    void findWorkUsernameButtonPressed() {

    }

    public void reset()
    {

    }
}
