package com.daojpa.model;
import javax.persistence.*;

@Entity
@Table(name = "Customer")

public class Customer {
    @Id
    @Column(name = "Person_ID")
    private int id;

    @Column(name = "FirstName")
    private String firstName;

    public Customer() {

    }

    @Column(name = "LastName")
    private String secondName;

    public Customer(int id, String firstName, String secondName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
    }

    @Column(name = "EMail")
    private String email;

    @Column(name = "Phone")
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;

    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String toString(){
        return  "Person Id: " + getId()+ " First name: " + getFirstName()+ " Second Name: " + getSecondName()+ " Email: " + getEmail()+ " Phone: " + getPhone();
    }

}


