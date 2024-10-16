package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class TrackingStatusOfficerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bookingId = request.getParameter("bookingId");
        ArrayList<TrackingInfo> trackingInfoList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:C://Users//chava//MyDB;create=true");

            // Query to retrieve tracking details based on booking ID
            String query = "SELECT B.id AS bookingId, U.name AS fullName, U.address, " +
                           "B.rec_name AS recName, B.rec_address AS recAddress, " +
                           "B.par_status AS parStatus, B.par_payment_time AS dateOfBooking " +
                           "FROM Bookings B " +
                           "JOIN Users U ON B.user_id = U.id " +
                           "WHERE B.id = ?";

            pst = con.prepareStatement(query);
            pst.setString(1, bookingId);
            rs = pst.executeQuery();

            while (rs.next()) {
                TrackingInfo info = new TrackingInfo();
                info.setBookingId(rs.getLong("bookingId"));
                info.setFullName(rs.getString("fullName"));
                info.setAddress(rs.getString("address"));
                info.setRecName(rs.getString("recName"));
                info.setRecAddress(rs.getString("recAddress"));
                info.setDateOfBooking(rs.getTimestamp("dateOfBooking"));
                info.setParStatus(rs.getString("parStatus"));
                trackingInfoList.add(info);
            }

            if (trackingInfoList.isEmpty()) {
                request.setAttribute("errorMessage", "No tracking information found for this booking ID.");
            } else {
                request.setAttribute("trackingInfoList", trackingInfoList);
            }

            // Forward to the JSP to display the results
            request.getRequestDispatcher("trackdetailsofficer.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while retrieving tracking details.");
            request.getRequestDispatcher("trackdetailsofficer.jsp").forward(request, response);
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
