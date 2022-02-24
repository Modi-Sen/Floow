package com.java.Floow.technicalchallenge.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class DriverBean {

    private int driverId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    public DriverBean(){}

    public DriverBean(int driverId, String firstName, String lastName, String dateOfBirth, LocalDate creationDate) {
        this.driverId = driverId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.creationDate = creationDate;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "DriverBean{" +
                "driverId=" + driverId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", creationDate=" + creationDate +
                '}';
    }
}
