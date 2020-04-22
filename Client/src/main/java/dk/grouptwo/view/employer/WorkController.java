package dk.grouptwo.view.employer;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class WorkController extends EmployerViewTabController {

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
