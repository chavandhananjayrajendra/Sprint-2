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
import jakarta.servlet.http.HttpSession;



public class InvoiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String bookingId = request.getParameter("bookingId");
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Establishing a connection to the database
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:C://Users//chava//MyDB;create=true");

            // Query to retrieve booking details for the specific user and booking ID
            String query = "SELECT B.id, B.rec_name, B.rec_address, B.rec_pin, B.rec_mobile, " +
                           "B.par_weight_gram, B.par_contents_description, B.par_delivery_type, " +
                           "B.par_packing_preference, B.par_pickup_time, B.par_dropoff_time, " +
                           "B.par_service_cost, B.par_payment_time " +
                           "FROM Bookings B " +
                           "JOIN Users U ON B.user_id = U.id " +
                           "WHERE B.id = ? AND U.username = ?";

            pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(bookingId));
            pst.setString(2, (String) session.getAttribute("username"));

            rs = pst.executeQuery();

            if (rs.next()) {
                // Set the booking details as request attributes
                request.setAttribute("bookingId", rs.getInt("id"));
                request.setAttribute("recName", rs.getString("rec_name"));
                request.setAttribute("recAddress", rs.getString("rec_address"));
                request.setAttribute("recPin", rs.getString("rec_pin"));
                request.setAttribute("recMobile", rs.getString("rec_mobile"));
                request.setAttribute("parWeightGram", rs.getInt("par_weight_gram"));
                request.setAttribute("parContentsDescription", rs.getString("par_contents_description"));
                request.setAttribute("parDeliveryType", rs.getString("par_delivery_type"));
                request.setAttribute("parPackingPreference", rs.getString("par_packing_preference"));
                request.setAttribute("parPickupTime", rs.getTimestamp("par_pickup_time"));
                request.setAttribute("parDropoffTime", rs.getTimestamp("par_dropoff_time"));
                request.setAttribute("serviceCost", rs.getBigDecimal("par_service_cost"));
                request.setAttribute("paymentTime", rs.getTimestamp("par_payment_time"));

                // Forward to the invoice page
                request.getRequestDispatcher("invoice.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "No booking found with the given ID for this user.");
                request.getRequestDispatcher("invoice.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while retrieving the invoice.");
            request.getRequestDispatcher("invoice.jsp").forward(request, response);
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
