package com.Servlet;

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


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeliveryStatusUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bookingId = request.getParameter("bookingId");
        String newStatus = request.getParameter("newStatus");
        StatusDetails statusDetails = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:C://Users//chava//MyDB;create=true");

            // Query to retrieve booking details based on booking ID
            String selectQuery = "SELECT B.id AS bookingId, U.name AS fullName, U.address, " +
                                 "B.rec_name AS recName, B.rec_address AS recAddress, " +
                                 "B.par_status AS parStatus " +
                                 "FROM Bookings B " +
                                 "JOIN Users U ON B.user_id = U.id " +
                                 "WHERE B.id = ?";

            pst = con.prepareStatement(selectQuery);
            pst.setString(1, bookingId);
            rs = pst.executeQuery();

            if (rs.next()) {
                // Retrieve current details
                statusDetails = new StatusDetails();
                statusDetails.setBookingId(rs.getLong("bookingId"));
                statusDetails.setFullName(rs.getString("fullName"));
                statusDetails.setAddress(rs.getString("address"));
                statusDetails.setRecName(rs.getString("recName"));
                statusDetails.setRecAddress(rs.getString("recAddress"));
                statusDetails.setParStatus(rs.getString("parStatus"));

                // Update the new status if provided
                if (newStatus != null && !newStatus.isEmpty()) {
                    String updateQuery = "UPDATE Bookings SET par_status = ? WHERE id = ?";
                    pst = con.prepareStatement(updateQuery);
                    pst.setString(1, newStatus);
                    pst.setString(2, bookingId);
                    pst.executeUpdate();

                    request.setAttribute("successMessage", "Delivery status updated successfully.");
                }
            } else {
                request.setAttribute("errorMessage", "No booking found for this booking ID.");
            }

            request.setAttribute("statusDetails", statusDetails);
            request.getRequestDispatcher("delivery_status.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while updating the delivery status.");
            request.getRequestDispatcher("deliveryStatusUpdate.jsp").forward(request, response);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
