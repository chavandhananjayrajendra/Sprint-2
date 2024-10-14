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
import java.sql.ResultSet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InvoiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String bookingId = request.getParameter("bookingId");

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/deliverydb;create=true", "app", "app");

            String query = "SELECT * FROM Bookings WHERE booking_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, bookingId);

            ResultSet rs = pst.executeQuery();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (rs.next()) {
                out.println("<h2>Invoice Details</h2>");
                out.println("Booking ID: " + rs.getString("booking_id") + "<br>");
                out.println("Receiver Name: " + rs.getString("rec_name") + "<br>");
                out.println("Receiver Address: " + rs.getString("rec_address") + "<br>");
                out.println("Parcel Weight: " + rs.getInt("par_weight_gram") + " grams<br>");
                out.println("Service Cost: " + rs.getDouble("par_service_cost") + "<br>");
                out.println("Payment Time: " + rs.getTimestamp("par_payment_time") + "<br>");
            } else {
                out.println("<h3>Booking ID not found.</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
