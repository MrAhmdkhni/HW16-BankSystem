package entity;

import base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends BaseEntity {

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(name = "national_code", length = 10, nullable = false, unique = true)
    private String nationalCode;

    @Column(name = "mobile_number", length = 11, nullable = false, unique = true)
    private String mobileNumber;

    @Column(unique = true)
    private String username;
    private String password;

    public Person(String firstname, String lastname, String nationalCode, String mobileNumber, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalCode = nationalCode;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.password = password;
    }

    public Person() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
