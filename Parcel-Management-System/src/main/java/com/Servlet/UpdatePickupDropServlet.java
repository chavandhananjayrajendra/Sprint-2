package com.Servlet;

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



public class UpdatePickupDropServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bookingId = request.getParameter("bookingId");
        String newPickupTime = request.getParameter("newPickupTime");
        String newDropoffTime = request.getParameter("newDropoffTime");

        Connection con = null;
        PreparedStatement pst = null;

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:C://Users//chava//MyDB;create=true");

            // Query to update the pickup and dropoff times for the specific booking ID
            String updateQuery = "UPDATE Bookings SET par_pickup_time = ?, par_dropoff_time = ? WHERE id = ?";

            pst = con.prepareStatement(updateQuery);
            pst.setTimestamp(1, Timestamp.valueOf(newPickupTime.replace("T", " ") + ":00"));
            pst.setTimestamp(2, Timestamp.valueOf(newDropoffTime.replace("T", " ") + ":00"));
            pst.setString(3, bookingId);

            int updated = pst.executeUpdate();

            if (updated > 0) {
                request.setAttribute("successMessage", "Pickup and Dropoff times updated successfully!");
            } else {
                request.setAttribute("errorMessage", "No booking found to update.");
            }

            // Forward to the JSP to show the result
            request.getRequestDispatcher("pickupDropUpdate.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while updating pickup and dropoff times.");
            request.getRequestDispatcher("pickupDropUpdate.jsp").forward(request, response);
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
