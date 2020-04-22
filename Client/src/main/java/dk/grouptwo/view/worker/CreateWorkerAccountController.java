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

  private TextField createAccountEmployeeCPRNumber;
  private TextField createAccountEmployeeFirstName;
  private TextField createAccountEmployeeLastName;
  private DatePicker createAccountEmployeeDatePicker;
  private ComboBox createAccountEmployeeGender;
  private TextField createAccountEmployeeCity;
  private TextField createAccountEmployeePostCode;
  private TextField createAccountEmployeeMobilePhone;
  private ComboBox createAccountEmployeeTaxCard;
  private TextField createAccountEmployeeLanguages;
  private TextArea createAccountEmployeeDescription;
  private TextField createAccountEmployeeEmail;
  private TextField createAccountEmployeePassword;
  private TextField createAccountEmployeeConfirmPassword;

  @FXML
    private void switchToSecondary() throws IOException {
        /*App.setRoot("secondary");*/
    }

  public void createAccountEmployeeBackButtonPressed()
  {
  }

  public void createAccountEmployeeNextButtonPressed()
  {
  }
}
