package com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

@WebServlet("/UpdatePickupDropServlet")
public class UpdatePickupDropServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingId = request.getParameter("bookingId");
        Timestamp newPickupTime = Timestamp.valueOf(request.getParameter("newPickupTime").replace("T", " "));
        Timestamp newDropoffTime = Timestamp.valueOf(request.getParameter("newDropoffTime").replace("T", " "));

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/deliverydb;create=true", "app", "app");

            // Update query
            String query = "UPDATE Bookings SET par_pickup_time = ?, par_dropoff_time = ? WHERE booking_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setTimestamp(1, newPickupTime);
            pst.setTimestamp(2, newDropoffTime);
            pst.setString(3, bookingId);
            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                response.sendRedirect("pickupDropUpdate.jsp?success=1"); // Redirect on success
            } else {
                response.sendRedirect("pickupDropUpdate.jsp?success=0"); // Redirect on failure
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("pickupDropUpdate.jsp?success=0"); // Redirect on error
        }
    }
}
