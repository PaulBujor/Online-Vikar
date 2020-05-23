package dk.grouptwo.model.objects;

import java.time.LocalDate;
import java.util.ArrayList;

public class Worker extends Account {
    private String CPR;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String gender;
    private String taxCard;
    private String languages;
    private String description;
    private ArrayList<License> licenses;
    private static final long serialVersionUID = 3;

    public Worker(String email, String phone, Address address, String CPR, String firstName, String lastName, String taxCard, String languages, String description, LocalDate birthday, String gender) {
        super(email, phone, address);
        this.CPR = CPR;
        this.firstName = firstName;
        this.lastName = lastName;
        this.taxCard = taxCard;
        this.languages = languages;
        this.description = description;
        licenses = new ArrayList<License>();
        this.birthday = birthday;
        this.gender = gender;
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

    public void addLicense(License license)
    {
        licenses.add(license);
    }

    public void removeLicense(License license)
    {
        licenses.remove(license);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public String toString() {
        return getCPR();
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Worker))
            return false;
        Worker other = (Worker) obj;
        return CPR.equals(other.CPR);
    }
}
