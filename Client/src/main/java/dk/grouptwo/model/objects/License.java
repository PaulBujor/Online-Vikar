package dk.grouptwo.model.objects;

import java.io.Serializable;
import java.time.LocalDate;

public class License implements Serializable {
    private String type;
    private String category;
    private String licenseNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private static final long serialVersionUID = 5;

    public License(String type, String category, String licenseNumber, LocalDate issueDate, LocalDate expiryDate) {
        this.type = type;
        this.category = category;
        this.licenseNumber = licenseNumber;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String toString()
    {
        return getType();
    }
}
