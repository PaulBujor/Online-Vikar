package dk.grouptwo.model.objects;

import java.util.ArrayList;

public class Worker extends Account {
    private String CPR;
    private String firstName;
    private String lastName;
    private String taxCard;
    private String languages;
    private String description;
    private ArrayList<License> licenses;

    public Worker(String email, String phone, Address address, String CPR, String firstName, String lastName, String taxCard, String languages, String description) {
        super(email, phone, address);
        this.CPR = CPR;
        this.firstName = firstName;
        this.lastName = lastName;
        this.taxCard = taxCard;
        this.languages = languages;
        this.description = description;
        licenses = new ArrayList<License>();
    }

    public String getCPR() {
        return CPR;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTaxCard() {
        return taxCard;
    }

    public void setTaxCard(String taxCard) {
        this.taxCard = taxCard;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(ArrayList<License> licenses) {
        this.licenses = licenses;
    }
}
