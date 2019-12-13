package com.example.demo.Model;

public class PatientBookingDetails {

    private String slot,registredDoctorName,patientName,patientGender,patientAge,patientPhoneNo,patientAddress;

    public PatientBookingDetails() {

    }

    public PatientBookingDetails(String slot, String registredDoctorName, String patientName, String patientGender, String patientAge, String patientPhoneNo, String patientAddress) {
        this.slot = slot;
        this.registredDoctorName = registredDoctorName;
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientAge = patientAge;
        this.patientPhoneNo = patientPhoneNo;
        this.patientAddress = patientAddress;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getRegistredDoctorName() {
        return registredDoctorName;
    }

    public void setRegistredDoctorName(String registredDoctorName) {
        this.registredDoctorName = registredDoctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientPhoneNo() {
        return patientPhoneNo;
    }

    public void setPatientPhoneNo(String patientPhoneNo) {
        this.patientPhoneNo = patientPhoneNo;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }
}
