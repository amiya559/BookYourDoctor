package com.example.demo;

public class DoctorDetails {

    private String doctorName,specialization,gender,locations,days,price,description;
    private String doctorImage;

    public DoctorDetails(){

    }

    public DoctorDetails(String doctorName, String specialization, String gender, String locations, String days, String price, String description, String doctorImage) {
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.gender = gender;
        this.locations = locations;
        this.days = days;
        this.price = price;
        this.description = description;
        this.doctorImage = doctorImage;
    }


    public String getDoctorName() {
        return doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getGender() {
        return gender;
    }

    public String getLocations() {
        return locations;
    }

    public String getDays() {
        return days;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getDoctorImage() {
        return doctorImage;
    }
}
