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

@WebServlet("/DeliveryStatusUpdateServlet")
public class DeliveryStatusUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String updateBookingId = request.getParameter("updateBookingId");
        String deliveryStatus = request.getParameter("deliveryStatus");

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/deliverydb;create=true", "app", "app");

            // Update query to set the new delivery status
            String updateQuery = "UPDATE Bookings SET par_status = ? WHERE booking_id = ?";
            PreparedStatement pst = con.prepareStatement(updateQuery);
            pst.setString(1, deliveryStatus);
            pst.setString(2, updateBookingId);
            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                response.sendRedirect("deliveryStatus.jsp?success=1");
            } else {
                response.sendRedirect("deliveryStatus.jsp?success=0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("deliveryStatus.jsp?success=0");
        }
    }
}

