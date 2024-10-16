package com.Servlet;

public class StatusDetails {
    private long bookingId;
    private String fullName;
    private String address;
    private String recName;
    private String recAddress;
    private String parStatus;

    // Getters and Setters
    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getParStatus() {
        return parStatus;
    }

    public void setParStatus(String parStatus) {
        this.parStatus = parStatus;
    }
}
