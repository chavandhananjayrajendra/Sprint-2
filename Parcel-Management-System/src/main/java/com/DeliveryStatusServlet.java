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

@WebServlet("/DeliveryStatusServlet")
public class DeliveryStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingId = request.getParameter("bookingId");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/deliverydb;create=true", "app", "app");

            // Query to get the delivery status
            String query = "SELECT par_status FROM Bookings WHERE booking_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, bookingId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String status = rs.getString("par_status");
                out.println("<h2>Current Delivery Status for Booking ID " + bookingId + ": " + status + "</h2>");
            } else {
                out.println("<h2>No Booking found with ID " + bookingId + "</h2>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error occurred while retrieving status.</h2>");
        }
        out.println("<a href='deliveryStatus.jsp'>Back</a>");
    }
}
