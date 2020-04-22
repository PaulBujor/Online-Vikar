package dk.grouptwo.view.worker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class WorkerWorkHistoryController extends WorkerViewTabController {
  @FXML
  private Text workHistoryDetailsTitle;

  @FXML
  private Text workHistoryDetailsEmployer;

  @FXML
  private Text workHistoryDetailsSalary;

  @FXML
  private Text workHistoryDetailsStartEndDates;

  @FXML
  private Text workHistoryDetailsLocation;

  @FXML
  private Text workHistoryDetailsWorkDescription;

  @FXML
  private TableView<?> workHistoryTable;

  @FXML
  private TableColumn<?, ?> workHistoryJobTitleColumn;

  @FXML
  private TableColumn<?, ?> workHistoryStatusColumn;

  @FXML
  private TableColumn<?, ?> workHistoryEmployerColumn;

  @FXML
  private TableColumn<?, ?> workHistorySalaryColumn;

  @FXML
  private TableColumn<?, ?> workHistoryStartColumn;

  @FXML
  private TableColumn<?, ?> workHistoryWorkTimeColumn;

  @FXML
  private TableColumn<?, ?> workHistoryLocationColumn;

  @FXML
  private Label workHistoryHoursLabel;

  @FXML
  void workHistoryFindWorkButtonPressed() {

  }

  @FXML
  void workHistoryHistoryButtonPressed() {

  }

  @FXML
  void workHistoryUpcomingButtonPressed() {

  }

  @FXML
  void workHistoryUsernameButtonPressed() {

  }
}
