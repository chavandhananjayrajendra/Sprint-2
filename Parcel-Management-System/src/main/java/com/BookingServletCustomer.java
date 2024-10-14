package com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class BookingServletCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get receiver details from customer input
        String recName = request.getParameter("recName");
        String recAddress = request.getParameter("recAddress");
        String recPin = request.getParameter("recPin");
        String recMobile = request.getParameter("recMobile");
        int parWeightGram = Integer.parseInt(request.getParameter("parWeightGram"));
        String parContentsDescription = request.getParameter("parContentsDescription");
        String parDeliveryType = request.getParameter("parDeliveryType");
        String parPackingPreference = request.getParameter("parPackingPreference");
        
        // Correctly parsing the pickup and dropoff times
        Timestamp parPickupTime = Timestamp.valueOf(request.getParameter("parPickupTime").replace("T", " "));
        Timestamp parDropoffTime = Timestamp.valueOf(request.getParameter("parDropoffTime").replace("T", " "));

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");  // Sender's username stored in session

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/deliverydb;create=true", "app", "app");

            // Insert into Bookings table
            String query = "INSERT INTO Bookings (user_id, rec_name, rec_address, rec_pin, rec_mobile, par_weight_gram, par_contents_description, par_delivery_type, par_packing_preference, par_pickup_time, par_dropoff_time, par_service_cost, par_payment_time, par_status) VALUES ((SELECT id FROM Users WHERE username = ?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, 'Booked')";
            
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, recName);
            pst.setString(3, recAddress);
            pst.setString(4, recPin);
            pst.setString(5, recMobile);
            pst.setInt(6, parWeightGram);
            pst.setString(7, parContentsDescription);
            pst.setString(8, parDeliveryType);
            pst.setString(9, parPackingPreference);
            pst.setTimestamp(10, parPickupTime);
            pst.setTimestamp(11, parDropoffTime);
            pst.setDouble(12, calculateServiceCost(parWeightGram, parDeliveryType));
            pst.executeUpdate();

            response.sendRedirect("customerMenu.jsp");
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

