package dk.grouptwo.view.employer;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateWorkOfferController extends EmployerViewTabController
{
  @FXML
  private TextField createWorkOfferTitle;

  @FXML
  private TextField createWorkOfferTitleSalary;

  @FXML
  private TextField createWorkOfferStartHour;

  @FXML
  private TextField createWorkOfferStartMinutes;

  @FXML
  private DatePicker createWorkOfferStartDatePicker;

  @FXML
  private TextField createWorkOfferEndHour;

  @FXML
  private TextField createWorkOfferEndMinutes;

  @FXML
  private DatePicker createWorkOfferEndDate;

  @FXML
  private TextField createWorkOfferLocation;

  @FXML
  private TextArea createWorkOfferWorkDescription;

  @FXML
  void createWorkOfferCreateButtonPressed() {

  }

  @FXML
  void createWorkOfferHistoryButtonPressed() {

  }

  @FXML
  void createWorkOfferJobsButtonPressed() {

  }

  @FXML
  void createWorkOfferUsernameButtonPressed() {

  }
}
