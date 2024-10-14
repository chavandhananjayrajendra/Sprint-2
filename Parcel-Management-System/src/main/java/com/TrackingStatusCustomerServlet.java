package com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class TrackingStatusCustomerServlet
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TrackingStatusCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bookingId = request.getParameter("bookingId");

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/deliverydb;create=true", "app", "app");

            String query = "SELECT * FROM Bookings WHERE booking_id = ? AND user_id = (SELECT id FROM Users WHERE username = ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, bookingId);
            pst.setString(2, (String) request.getSession().getAttribute("username"));

            ResultSet rs = pst.executeQuery();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (rs.next()) {
                out.println("<h2>Package Status</h2>");
                out.println("Booking ID: " + rs.getString("booking_id") + "<br>");
                out.println("Receiver Name: " + rs.getString("rec_name") + "<br>");
                out.println("Status: " + rs.getString("par_status") + "<br>");
            } else {
                out.println("<h3>Booking ID not found or you don't have access to this booking.</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
