package com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

@WebServlet("/PickupDropUpdateServlet")
public class PickupDropUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingId = request.getParameter("bookingId");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/deliverydb;create=true", "app", "app");

            // Query to get the booking details
            String query = "SELECT rec_name, rec_address, par_status, par_pickup_time, par_dropoff_time FROM Bookings WHERE booking_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, bookingId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String recName = rs.getString("rec_name");
                String recAddress = rs.getString("rec_address");
                String status = rs.getString("par_status");
                Timestamp pickupTime = rs.getTimestamp("par_pickup_time");
                Timestamp dropoffTime = rs.getTimestamp("par_dropoff_time");

                out.println("<h2>Booking Details for ID: " + bookingId + "</h2>");
                out.println("<p><strong>Recipient Name:</strong> " + recName + "</p>");
                out.println("<p><strong>Recipient Address:</strong> " + recAddress + "</p>");
                out.println("<p><strong>Status:</strong> " + status + "</p>");
                out.println("<p><strong>Current Pickup Time:</strong> <input type='datetime-local' name='newPickupTime' value='" + pickupTime + "'></p>");
                out.println("<p><strong>Current Dropoff Time:</strong> <input type='datetime-local' name='newDropoffTime' value='" + dropoffTime + "'></p>");
                out.println("<form action='UpdatePickupDropServlet' method='post'>");
                out.println("<input type='hidden' name='bookingId' value='" + bookingId + "'>");
                out.println("<input type='submit' value='Update Times'>");
                out.println("</form>");
            } else {
                out.println("<h2>No Booking found with ID " + bookingId + "</h2>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error occurred while retrieving booking details.</h2>");
        }
        out.println("<a href='pickupDropUpdate.jsp'>Back</a>");
    }
}
