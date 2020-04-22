package dk.grouptwo.view.employer;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class EmployerWorkHistoryController extends EmployerViewTabController {

  @FXML
  private Text employerHistoryDetailsTitle;

  @FXML
  private Text employerHistoryDetailsSalary;

  @FXML
  private Text employerHistoryDetailsStartEndDates;

  @FXML
  private Text employerHistoryDetailsLocation;

  @FXML
  private Text employerHistoryDetailsWorkDescription;

  @FXML
  private TableView<?> employerHistoryEmployeesTable;

  @FXML
  private TableColumn<?, ?> employerHistoryEmployeesTableCPRColumn;

  @FXML
  private TableColumn<?, ?> employerHistoryEmployeesTableNameColumn;

  @FXML
  private TableView<?> employerHistoryMainTable;

  @FXML
  private TableColumn<?, ?> employerHistoryJobTitleColumn;

  @FXML
  private TableColumn<?, ?> employerHistoryStatusColumn;

  @FXML
  private TableColumn<?, ?> employerHistoryNoEmployesColumn;

  @FXML
  private TableColumn<?, ?> employerHistorySalaryColumn;

  @FXML
  private TableColumn<?, ?> employerHistoryStartColumn;

  @FXML
  private TableColumn<?, ?> employerHistoryWorkTimeColumn;

  @FXML
  private TableColumn<?, ?> employerHistoryLocationColumn;

  //TODO Create Table row classes and connect them with table columns

  @FXML
  void employerHistoryHistoryButtonPressed() {

  }

  @FXML
  void employerHistoryJobsButtonPressed() {

  }

  @FXML
  void employerHistoryUsernameButtonPressed() {

  }
}
