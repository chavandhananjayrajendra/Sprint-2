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


public class TrackingStatusCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String bookingId = request.getParameter("bookingId");
        List<Booking> bookings = new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Establishing a connection to the database
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:C:\\Users\\chava\\MyDB;create=true");

            // Query to retrieve booking details for the specific user and booking ID
            String query = "SELECT B.id, B.par_payment_time, B.rec_name, B.rec_address, B.par_status, " +
                           "U.id AS user_id " +
                           "FROM Bookings B " +
                           "JOIN Users U ON B.user_id = U.id " +
                           "WHERE B.id = ? AND U.username = ?";

            pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(bookingId));
            pst.setString(2, (String) session.getAttribute("username"));

            rs = pst.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setReceiverName(rs.getString("rec_name"));
                booking.setReceiverAddress(rs.getString("rec_address"));
                booking.setStatus(rs.getString("par_status"));
                booking.setPaymentTime(rs.getTimestamp("par_payment_time"));

                bookings.add(booking);
            }

            // Setting the booking details in the request scope
            request.setAttribute("bookings", bookings);

            // Forwarding to the tracking status page
            request.getRequestDispatcher("trackdetails.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
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

