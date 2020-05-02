package dk.grouptwo.viewmodel.worker;

import dk.grouptwo.model.ModelManager;
import dk.grouptwo.model.objects.License;
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

    private ModelManager model;
    private StringProperty firstName;
    private StringProperty lastName;
    private ObjectProperty<LocalDate> birthday;
    private StringProperty gender;
    private StringProperty city;
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

    public WorkerProfileViewModel(ModelManager model) {
        this.model = model;
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        birthday = new SimpleObjectProperty<>();
        gender = new SimpleStringProperty();
        city = new SimpleStringProperty();
        postCode = new SimpleStringProperty();
        mobilePhone = new SimpleStringProperty();
        taxCard = new SimpleStringProperty();
        languages = new SimpleStringProperty();
        description = new SimpleStringProperty();
        email = new SimpleStringProperty();
        currentPassword = new SimpleStringProperty();
        newPassword = new SimpleStringProperty();
        confirmPassword = new SimpleStringProperty();
        this.licenseTitle = new SimpleStringProperty();
        this.licenseCategory = new SimpleStringProperty();
        this.licenseNumber = new SimpleStringProperty();
        this.licenseIssueDate = new SimpleObjectProperty<LocalDate>();
        this.licenseExpiryDate = new SimpleObjectProperty<LocalDate>();
        error = new SimpleStringProperty();
        list = createList();

    }

    public boolean saveChangesWorker() {


    }

    public void addLicense() {
        License license = new License(licenseTitle.get(), licenseCategory.get(), licenseNumber.get(), licenseIssueDate.get(), licenseExpiryDate.get());
        if (!(licenseTitle.get().equals("") || licenseCategory.get().equals("") || licenseNumber.get().equals("") || licenseIssueDate.get().toString().equals("") || licenseExpiryDate.get().toString().equals(""))) {
            try {
                Platform.runLater(() -> {
                    list.add(new LicenseTableData(license));
                });
                model.addLicense(license);
                //TODO check exception if license exists
            } catch (Exception e) {
                error.set(e.getMessage());
            }
        } else {
            error.set("All the fields for adding a license must be filled.");
        }
    }

    public void removeLicense(LicenseTableData licenseTableData) {
        //Controller getSelectionItem
        model.deleteLicense(licenseTableData.licenseNumberProperty().get());

    }

    private ObservableList<LicenseTableData> createList() {
        ObservableList<LicenseTableData> list = FXCollections.observableArrayList();

        ArrayList<License> licenses = new ArrayList<>(model.getLicenses());

        for (License license : licenses) {
            list.add(new LicenseTableData(license));
        }
        return list;

    }

    public ObservableList<LicenseTableData> getList() {
        return list;
    }


}
