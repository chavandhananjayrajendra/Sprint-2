package com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet("/TrackingStatusServlet")
public class TrackingStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String bookingId = request.getParameter("bookingId");

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/deliverydb;create=true", "app", "app");
            String query = "SELECT * FROM Bookings WHERE booking_id = ? AND user_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, bookingId);
            pst.setString(2, (String) session.getAttribute("userId")); // Assuming userId is stored in session
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                request.setAttribute("bookingDetails", rs);
                request.getRequestDispatcher("tracking_status_result.jsp").forward(request, response);
            } else {
                response.sendRedirect("not_found.jsp"); // Redirect to a not found page
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to an error page
        }
    }
}
