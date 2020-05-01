package dk.grouptwo.view.employer;

import dk.grouptwo.view.ViewHandler;
import dk.grouptwo.viewmodel.employer.WorkViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class WorkController extends EmployerViewTabController {
  private WorkViewModel viewModel;

  @FXML
  private Text jobsDetailsTitle;

  @FXML
  private Text jobsDetailsEmployer;

  @FXML
  private Text jobsDetailsSalary;

  @FXML
  private Text jobsDetailsStartEndDates;

  @FXML
  private Text jobsDetailsLocation;

  @FXML
  private Text jobsDetailsWorkDescription;

  @FXML
  private TableView<?> jobsTable;

  @FXML
  private TableColumn<?, ?> jobsJobTitleColumn;

  @FXML
  private TableColumn<?, ?> jobsEmployerColumn;

  @FXML
  private TableColumn<?, ?> jobsSalaryColumn;

  @FXML
  private TableColumn<?, ?> jobsStartColumn;

  @FXML
  private TableColumn<?, ?> jobsWorkTimeColumn;

  @FXML
  private TableColumn<?, ?> jobsLocationColumn;

  public void init(ViewHandler viewHandler, WorkViewModel viewModel, Region root){
    super.init(viewHandler, root);
    this.viewModel = viewModel;
  }

  @FXML
  void jobsApplyButtonPressed() {

  }

  @FXML
  void jobsCreateWorkOfferButtonPressed() {

  }

  @FXML
  void jobsHistoryButtonPressed() {

  }

  @FXML
  void jobsJobsButtonPressed() {

  }

  @FXML
  void jobsUsernameButtonPressed() {

  }

}
