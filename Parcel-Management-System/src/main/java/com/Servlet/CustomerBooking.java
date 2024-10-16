package com.Servlet;


import java.math.BigDecimal;
import java.sql.Timestamp;

public class CustomerBooking {
    private int bookingId;
    private String receiverName;
    private String receiverAddress;
    private String status;
    private BigDecimal serviceCost;
    private Timestamp paymentTime;

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }

    public String getReceiverAddress() { return receiverAddress; }
    public void setReceiverAddress(String receiverAddress) { this.receiverAddress = receiverAddress; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getServiceCost() { return serviceCost; }
    public void setServiceCost(BigDecimal serviceCost) { this.serviceCost = serviceCost; }

    public Timestamp getPaymentTime() { return paymentTime; }
    public void setPaymentTime(Timestamp paymentTime) { this.paymentTime = paymentTime; }
}