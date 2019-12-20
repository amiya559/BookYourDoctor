package com.example.demo.Model;

public class PatientBookingDetails {

    private String currentOnlineUser,bookingDate,bookingTime,slot,registredDoctorName,patientName,patientGender,patientAge,patientPhoneNo,patientAddress,appointmentDate;
    private String key;

    public PatientBookingDetails() {

    }

    public PatientBookingDetails(String currentOnlineUser, String bookingDate, String bookingTime, String slot, String registredDoctorName, String patientName, String patientGender, String patientAge, String patientPhoneNo, String patientAddress, String appointmentDate) {
        this.currentOnlineUser = currentOnlineUser;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.slot = slot;
        this.registredDoctorName = registredDoctorName;
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientAge = patientAge;
        this.patientPhoneNo = patientPhoneNo;
        this.patientAddress = patientAddress;
        this.appointmentDate = appointmentDate;
    }

    public String getCurrentOnlineUser() {
        return currentOnlineUser;
    }

    public void setCurrentOnlineUser(String currentOnlineUser) {
        this.currentOnlineUser = currentOnlineUser;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
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

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
