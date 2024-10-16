
// Booking class to hold booking details
 package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PreviousBookingServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        List<CustomerBooking> customerBookings = new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Establish a connection to the database
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:C://Users//chava//MyDB;create=true");

            // SQL query to fetch booking history for the current customer
            String query = "SELECT B.id, B.rec_name, B.rec_address, B.par_status, B.par_service_cost, B.par_payment_time " +
                           "FROM Bookings B " +
                           "JOIN Users U ON B.user_id = U.id " +
                           "WHERE U.username = ? " +
                           "ORDER BY B.par_payment_time DESC";

            pst = con.prepareStatement(query);
            pst.setString(1, username);
            rs = pst.executeQuery();

            while (rs.next()) {
                CustomerBooking customerBooking = new CustomerBooking();
                customerBooking.setBookingId(rs.getInt("id"));
                customerBooking.setReceiverName(rs.getString("rec_name"));
                customerBooking.setReceiverAddress(rs.getString("rec_address"));
                customerBooking.setStatus(rs.getString("par_status"));
                customerBooking.setServiceCost(rs.getBigDecimal("par_service_cost"));
                customerBooking.setPaymentTime(rs.getTimestamp("par_payment_time"));

                customerBookings.add(customerBooking);
            }

            // Set the customer booking data in the request attribute
            request.setAttribute("customerBookings", customerBookings);

            // Forward to the customer booking history JSP
            request.getRequestDispatcher("previous_booking.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving booking history.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
