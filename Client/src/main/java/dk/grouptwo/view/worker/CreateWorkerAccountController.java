package dk.grouptwo.view.worker;

import java.io.IOException;

import dk.grouptwo.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateWorkerAccountController {
  @FXML
  private TextField createAccountEmployeeCPRNumber;
  @FXML
  private TextField createAccountEmployeeFirstName;
  @FXML
  private TextField createAccountEmployeeLastName;
  @FXML
  private DatePicker createAccountEmployeeDatePicker;
  @FXML
  private ComboBox createAccountEmployeeGender;
  @FXML
  private TextField createAccountEmployeeCity;
  @FXML
  private TextField createAccountEmployeePostCode;
  @FXML
  private TextField createAccountEmployeeMobilePhone;
  @FXML
  private ComboBox createAccountEmployeeTaxCard;
  @FXML
  private TextField createAccountEmployeeLanguages;
  @FXML
  private TextArea createAccountEmployeeDescription;
  @FXML
  private TextField createAccountEmployeeEmail;
  @FXML
  private TextField createAccountEmployeePassword;
  @FXML
  private TextField createAccountEmployeeConfirmPassword;

  @FXML
  public void createAccountEmployeeBackButtonPressed()
  {
  }
  @FXML
  public void createAccountEmployeeNextButtonPressed()
  {
  }
}
