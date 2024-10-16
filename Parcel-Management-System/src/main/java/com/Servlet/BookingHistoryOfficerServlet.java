package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BookingHistoryOfficerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        ArrayList<OfficerHistory> officerHistoryList = new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:C://Users//chava//MyDB;create=true");

            // Format input date strings to handle time properly
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Timestamp startDate = null;
            Timestamp endDate = null;

            // Attempt to parse the date strings
            try {
                startDate = new Timestamp(dateFormat.parse(startDateStr).getTime());
                endDate = new Timestamp(dateFormat.parse(endDateStr).getTime());
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Invalid date format. Please use the correct format.");
                request.getRequestDispatcher("bookingHistoryOfficer.jsp").forward(request, response);
                return; // Exit if parsing fails
            }

            // Debugging - Check startDate and endDate
            System.out.println("Start Date: " + startDate);
            System.out.println("End Date: " + endDate);

            // Query to retrieve booking details for all users based on date range
            String query = "SELECT U.id AS customerId, B.id AS bookingId, B.par_payment_time AS bookingDate, " +
                           "B.rec_name AS recName, B.rec_address AS recAddress, B.par_service_cost AS amount, B.par_status AS status " +
                           "FROM Bookings B " +
                           "JOIN Users U ON B.user_id = U.id " +
                           "WHERE B.par_payment_time BETWEEN ? AND ? " +
                           "ORDER BY B.par_payment_time DESC";

            pst = con.prepareStatement(query);
            pst.setTimestamp(1, startDate);
            pst.setTimestamp(2, endDate);

            // Debugging - Query Execution
            rs = pst.executeQuery();
            System.out.println("Query executed successfully");

            // Fetch the results
            while (rs.next()) {
                OfficerHistory history = new OfficerHistory();
                history.setCustomerId(rs.getLong("customerId"));
                history.setBookingId(rs.getLong("bookingId"));
                history.setBookingDate(rs.getTimestamp("bookingDate"));
                history.setRecName(rs.getString("recName"));
                history.setRecAddress(rs.getString("recAddress"));
                history.setAmount(rs.getDouble("amount"));
                history.setStatus(rs.getString("status"));

                officerHistoryList.add(history);

                // Debugging - Print each booking data
                System.out.println("Booking Found: " + rs.getLong("bookingId"));
            }

            if (officerHistoryList.isEmpty()) {
                System.out.println("No data found");
                request.setAttribute("errorMessage", "No booking history found for the given date range.");
            } else {
                System.out.println("Data found, forwarding to JSP");
                request.setAttribute("officerHistoryList", officerHistoryList);
            }

            // Forward to JSP
            request.getRequestDispatcher("bookingHistoryOfficer.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while retrieving booking history.");
            request.getRequestDispatcher("bookingHistoryOfficer.jsp").forward(request, response);
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
