package com.bav.ordermanagementsystem.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bav.ordermanagementsystem.service.UserDetails;

@Entity
public class Client implements UserDetails {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String firstName;

    private String lastName;

    private String secondName;

    private String email;

    private Long phone;

    private String login;

    private String password;

    public Client(){}

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getRole() {
        return "client";
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getFullName() {
        return lastName + " " + firstName + " " + secondName;
    }

    @Override
    public String getAdditionalInformation() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
