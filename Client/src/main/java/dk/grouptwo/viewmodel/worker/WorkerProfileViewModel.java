package dk.grouptwo.viewmodel.worker;

import dk.grouptwo.model.AccountManagement;
import dk.grouptwo.model.objects.Address;
import dk.grouptwo.model.objects.License;
import dk.grouptwo.model.objects.Worker;
import dk.grouptwo.utility.Validator;
import dk.grouptwo.utility.LicenseTableData;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class WorkerProfileViewModel {

    private AccountManagement model;
    private StringProperty username;
    private StringProperty CPR;
    private StringProperty firstName;
    private StringProperty lastName;
    private ObjectProperty<LocalDate> birthday;
    private StringProperty gender;
    private StringProperty country;
    private StringProperty city;
    private StringProperty street;
    private StringProperty postCode;
    private StringProperty mobilePhone;
    private StringProperty taxCard;
    private StringProperty languages;
    private StringProperty description;
    private StringProperty email;
    private StringProperty currentPassword;
    private StringProperty newPassword;
    private StringProperty confirmPassword;
    private StringProperty licenseTitle;
    private StringProperty licenseCategory;
    private StringProperty licenseNumber;
    private ObjectProperty<LocalDate> licenseIssueDate;
    private ObjectProperty<LocalDate> licenseExpiryDate;
    private StringProperty error;
    private ObservableList<LicenseTableData> list;

    public WorkerProfileViewModel(AccountManagement model) {
        this.model = model;
        username = new SimpleStringProperty("");
        CPR = new SimpleStringProperty("");
        firstName = new SimpleStringProperty("");
        lastName = new SimpleStringProperty("");
        birthday = new SimpleObjectProperty<>(null);
        gender = new SimpleStringProperty("Gender");
        country = new SimpleStringProperty("");
        street = new SimpleStringProperty("");
        city = new SimpleStringProperty("");
        postCode = new SimpleStringProperty("");
        mobilePhone = new SimpleStringProperty("");
        taxCard = new SimpleStringProperty("");
        languages = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        currentPassword = new SimpleStringProperty("");
        newPassword = new SimpleStringProperty("");
        confirmPassword = new SimpleStringProperty("");
        this.licenseTitle = new SimpleStringProperty("");
        this.licenseCategory = new SimpleStringProperty("");
        this.licenseNumber = new SimpleStringProperty("");
        this.licenseIssueDate = new SimpleObjectProperty<LocalDate>(null);
        this.licenseExpiryDate = new SimpleObjectProperty<LocalDate>(null);
        error = new SimpleStringProperty("");
        list = createList();
    }

    private boolean dataValid() {
        if (CPR.get().equals("") || firstName.get().equals("") || lastName.get().equals("") || gender.get().equals("") || city.get().equals("") ||
                postCode.get().equals("") || mobilePhone.get().equals("") || taxCard.get().equals("") || languages.get().equals("") || description.get().equals("") || email.get().equals("") ||
                currentPassword.get().equals("")) {
            error.set("All fields should be filled.");
            return false;
        } else if (!(newPassword.get().equals(confirmPassword.get()))) {
            error.set("The passwords do not match.");
            return false;
        } else if (!(Validator.emailCheck(email.get()))) {
            error.set("Wrong email format.");
            return false;
        }
        if (newPassword.get().length() > 0 && newPassword.get().length() < 8) {
            error.set("New password must be at least 8 characters long");
            return false;
        }
        return true;
    }

    public boolean saveChangesWorker() {
        try {
            Worker worker = new Worker(email.get(), mobilePhone.get(), new Address(country.get(), city.get(), street.get(), postCode.get()), CPR.get(), firstName.get(), lastName.get(), taxCard.get(), languages.get(), description.get(), birthday.get(), gender.get());
            if (newPassword.get().equals(""))
                model.editWorker(worker, currentPassword.get());
            else
                model.editWorker(worker, currentPassword.get(), newPassword.get(), confirmPassword.get());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addLicense() {
        if (!(licenseTitle.get().equals("") || licenseCategory.get().equals("") || licenseNumber.get().equals("") || licenseIssueDate.get().toString().equals("") || licenseExpiryDate.get().toString().equals(""))) {
            try {
                License license = new License(licenseTitle.get(), licenseCategory.get(), licenseNumber.get(), licenseIssueDate.get(), licenseExpiryDate.get());
                model.addLicense(license);
                Platform.runLater(() -> {
                    list.add(new LicenseTableData(license));
                });
            } catch (Exception e) {
                error.set(e.getMessage());
            }
        } else {
            error.set("All the fields for adding a license must be filled.");
        }
    }

    public void removeLicense(LicenseTableData licenseTableData) {
        try {
            model.deleteLicense(licenseTableData.licenseNumberProperty().get());
            Platform.runLater(() -> list.remove(licenseTableData));
        } catch (Exception e) {
            //
        }
    }

    private ObservableList<LicenseTableData> createList() {
        ObservableList<LicenseTableData> list = FXCollections.observableArrayList();

        ArrayList<License> licenses = new ArrayList<>(model.getLicenses());

        for (License license : licenses) {
            list.add(new LicenseTableData(license));
        }
        return list;

    }

    public void reset() {
        Worker worker = model.getWorker();
        CPR.set(worker.getCPR());
        firstName.set(worker.getFirstName());
        lastName.set(worker.getLastName());
        birthday.set(worker.getBirthday());
        gender.set(worker.getGender());
        country.set(worker.getAddress().getCountry());
        city.set(worker.getAddress().getCity());
        postCode.set(worker.getAddress().getZip());
        street.set(worker.getAddress().getStreet());
        mobilePhone.set(worker.getPhone());
        email.set(worker.getEmail());
        taxCard.set(worker.getTaxCard());
        languages.set(worker.getLanguages());
        description.set(worker.getDescription());
        currentPassword.set("");
        newPassword.set("");
        confirmPassword.set("");
        licenseTitle.set("");
        licenseNumber.set("");
        licenseCategory.set("");
        licenseIssueDate.set(null);
        licenseExpiryDate.set(null);
        error.set("");
    }


    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty CPRProperty() {
        return CPR;
    }

    public ObservableList<LicenseTableData> getList() {
        return list;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty streetProperty() {
        return street;
    }

    public StringProperty postCodeProperty() {
        return postCode;
    }

    public StringProperty mobilePhoneProperty() {
        return mobilePhone;
    }

    public StringProperty taxCardProperty() {
        return taxCard;
    }

    public StringProperty languagesProperty() {
        return languages;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty currentPasswordProperty() {
        return currentPassword;
    }

    public StringProperty newPasswordProperty() {
        return newPassword;
    }

    public StringProperty confirmPasswordProperty() {
        return confirmPassword;
    }

    public StringProperty licenseTitleProperty() {
        return licenseTitle;
    }

    public StringProperty licenseCategoryProperty() {
        return licenseCategory;
    }

    public StringProperty licenseNumberProperty() {
        return licenseNumber;
    }

    public ObjectProperty<LocalDate> licenseIssueDateProperty() {
        return licenseIssueDate;
    }

    public ObjectProperty<LocalDate> licenseExpiryDateProperty() {
        return licenseExpiryDate;
    }

    public StringProperty errorProperty() {
        return error;
    }
}
