package dk.grouptwo.model.objects;

import java.io.Serializable;

public class Address implements Serializable {
    private String country;
    private String city;
    private String street;
    private String zip;

    public Address(){}

    public Address(String country, String city, String street, String zip) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zip = zip;
    }

    public Address(String city, String street, String zip) {
        this.city = city;
        this.street = street;
        this.zip = zip;
    }

    public Address(String city, String zip) {
        this.city = city;
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

    public String toString(){
return country +" "+city+" "+ street+" "+zip;
    }

    public boolean equals(Object obj){
        if(!(obj instanceof Address)){
            return false;
        }
        Address other = (Address) obj;
        return other.country.equals(country) && other.city.equals(city) && other.street.equals(street) && other.zip.equals(zip);
    }
}
