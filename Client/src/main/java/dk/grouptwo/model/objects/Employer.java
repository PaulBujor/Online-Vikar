package dk.grouptwo.model.objects;

import java.io.Serializable;

public class Employer extends Account {
    private String CVR;
    private String companyName;

    public Employer(String email, String phone, Address address, String CVR, String companyName) {
        super(email, phone, address);
        this.CVR = CVR;
        this.companyName = companyName;
    }

    public String getCVR() {
        return CVR;
    }

    public void setCVR(String CVR) {
        this.CVR = CVR;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
