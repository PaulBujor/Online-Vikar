package dk.grouptwo.utility;

import dk.grouptwo.model.objects.License;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class LicenseTableData {

    private StringProperty licenseTitle;
    private StringProperty licenseCategory;
    private StringProperty licenseNumber;
    private ObjectProperty<LocalDate> licenseIssueDate;
    private ObjectProperty<LocalDate> licenseExpiryDate;

    public LicenseTableData(License license) {
        this.licenseTitle = new SimpleStringProperty(license.getType());
        this.licenseCategory = new SimpleStringProperty(license.getCategory());
        this.licenseNumber = new SimpleStringProperty(license.getLicenseNumber());
        this.licenseIssueDate = new SimpleObjectProperty<LocalDate>(license.getIssueDate());
        this.licenseExpiryDate = new SimpleObjectProperty<LocalDate>(license.getExpiryDate());
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
}
