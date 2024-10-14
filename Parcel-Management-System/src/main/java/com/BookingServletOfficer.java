package com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class BookingServletOfficer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get both sender and receiver details
        String senderName = request.getParameter("senderName");
        String senderAddress = request.getParameter("senderAddress");
        String senderContact = request.getParameter("senderContact");

        String recName = request.getParameter("recName");
        String recAddress = request.getParameter("recAddress");
        String recPin = request.getParameter("recPin");
        String recMobile = request.getParameter("recMobile");

        int parWeightGram = Integer.parseInt(request.getParameter("parWeightGram"));
        String parContentsDescription = request.getParameter("parContentsDescription");
        String parDeliveryType = request.getParameter("parDeliveryType");
        String parPackingPreference = request.getParameter("parPackingPreference");
        Timestamp parPickupTime = Timestamp.valueOf(request.getParameter("parPickupTime").replace("T", " "));
        Timestamp parDropoffTime = Timestamp.valueOf(request.getParameter("parDropoffTime").replace("T", " "));

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/deliverydb;create=true", "app", "app");

            // Insert into Bookings table
            String query = "INSERT INTO Bookings (sender_name, sender_address, sender_contact, rec_name, rec_address, rec_pin, rec_mobile, par_weight_gram, par_contents_description, par_delivery_type, par_packing_preference, par_pickup_time, par_dropoff_time, par_service_cost, par_payment_time, par_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, 'Booked')";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, senderName);
            pst.setString(2, senderAddress);
            pst.setString(3, senderContact);
            pst.setString(4, recName);
            pst.setString(5, recAddress);
            pst.setString(6, recPin);
            pst.setString(7, recMobile);
            pst.setInt(8, parWeightGram);
            pst.setString(9, parContentsDescription);
            pst.setString(10, parDeliveryType);
            pst.setString(11, parPackingPreference);
            pst.setTimestamp(12, parPickupTime);
            pst.setTimestamp(13, parDropoffTime);
            pst.setDouble(14, calculateServiceCost(parWeightGram, parDeliveryType));

            pst.executeUpdate();

            response.sendRedirect("officerMenu.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double calculateServiceCost(int weight, String deliveryType) {
        double cost = weight * 0.1;  // Base cost calculation
        if ("Express".equals(deliveryType)) {
            cost += 50;  // Additional charge for express delivery
        }
        return cost;
    }
}
