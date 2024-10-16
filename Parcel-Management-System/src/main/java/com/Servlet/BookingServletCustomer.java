package com.Servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BookingServletCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Session validation
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

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
        Timestamp parPickupTime = Timestamp.valueOf(request.getParameter("parPickupTime").replace("T", " ") + ":00");
        Timestamp parDropoffTime = Timestamp.valueOf(request.getParameter("parDropoffTime").replace("T", " ") + ":00");

        String username = (String) session.getAttribute("username");  // Sender's username stored in session

        Connection con = null;
        PreparedStatement pst = null;

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:C://Users//chava//MyDB;create=true");

            // Insert into Bookings table
            String query = "INSERT INTO Bookings (user_id, rec_name, rec_address, rec_pin, rec_mobile, par_weight_gram, par_contents_description, par_delivery_type, par_packing_preference, par_pickup_time, par_dropoff_time, par_service_cost, par_payment_time, par_status) " +
                           "VALUES ((SELECT id FROM Users WHERE username = ?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, 'Booked')";

            // Prepare statement to retrieve generated keys
            pst = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
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

            // Execute update
            pst.executeUpdate();

            // Retrieve the generated booking ID
            ResultSet generatedKeys = pst.getGeneratedKeys();
            long bookingId = -1; // Initialize booking ID variable
            if (generatedKeys.next()) {
                bookingId = generatedKeys.getLong(1); // Get the generated booking ID
            }

            // Set confirmation message and booking ID in the request
            request.setAttribute("confirmationMessage", "Your booking has been successfully confirmed!");
            request.setAttribute("bookingId", bookingId); // Pass the booking ID to the confirmation page

            // Forward to confirmation page
            request.getRequestDispatcher("bookingConfirmation.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

