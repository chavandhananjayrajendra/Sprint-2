package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class PickupDropUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bookingId = request.getParameter("bookingId");
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:C://Users//chava//MyDB;create=true");

            // Query to retrieve booking details based on the booking ID
            String selectQuery = "SELECT B.id AS bookingId, U.name AS fullName, U.address, " +
                                 "B.rec_name AS recName, B.rec_address AS recAddress, " +
                                 "B.par_status AS parStatus, B.par_pickup_time AS parPickupTime, " +
                                 "B.par_dropoff_time AS parDropoffTime " +
                                 "FROM Bookings B " +
                                 "JOIN Users U ON B.user_id = U.id " +
                                 "WHERE B.id = ?";

            pst = con.prepareStatement(selectQuery);
            pst.setString(1, bookingId);
            rs = pst.executeQuery();

            if (rs.next()) {
                // Retrieve current details
                request.setAttribute("bookingId", rs.getLong("bookingId"));
                request.setAttribute("fullName", rs.getString("fullName"));
                request.setAttribute("address", rs.getString("address"));
                request.setAttribute("recName", rs.getString("recName"));
                request.setAttribute("recAddress", rs.getString("recAddress"));
                request.setAttribute("parStatus", rs.getString("parStatus"));
                request.setAttribute("parPickupTime", rs.getTimestamp("parPickupTime"));
                request.setAttribute("parDropoffTime", rs.getTimestamp("parDropoffTime"));

                // Forward the details to the JSP page
                request.getRequestDispatcher("pickupDropUpdate.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "No booking found for this booking ID.");
                request.getRequestDispatcher("pickupDropUpdate.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while retrieving booking details.");
            request.getRequestDispatcher("pickupDropUpdate.jsp").forward(request, response);
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
}
