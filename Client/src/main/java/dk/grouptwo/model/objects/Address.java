package dk.grouptwo.model.objects;

import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Address implements Serializable {
    private String country;
    private String city;
    private String street;
    private String zip;
    private static final long serialVersionUID = 6;

    public Address(String country, String city, String street, String zip) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zip = zip;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String toString() {
        return String.format("%s, %s %s, %s", street, city, zip, country);
    }
}
