package dk.grouptwo.model.objects;

import java.io.Serializable;

public abstract class Account implements Serializable {
    private String email;
    private String phone;
    private Address address;
    private static final long serialVersionUID = 1;

    public Account(String email, String phone, Address address) {
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
