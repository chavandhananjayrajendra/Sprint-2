package com.Servlet;

import java.sql.Timestamp;

public class Booking {
	    private int id;
	    private String receiverName;
	    private String receiverAddress;
	    private String status;
	    private Timestamp paymentTime;

	    // Getters and Setters
	    public int getId() { return id; }
	    public void setId(int id) { this.id = id; }

	    public String getReceiverName() { return receiverName; }
	    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }

	    public String getReceiverAddress() { return receiverAddress; }
	    public void setReceiverAddress(String receiverAddress) { this.receiverAddress = receiverAddress; }

	    public String getStatus() { return status; }
	    public void setStatus(String status) { this.status = status; }

	    public Timestamp getPaymentTime() { return paymentTime; }
	    public void setPaymentTime(Timestamp paymentTime) { this.paymentTime = paymentTime; }
	}

