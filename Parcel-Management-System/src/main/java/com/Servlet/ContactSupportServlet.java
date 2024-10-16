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

public class ContactSupportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        Connection con = null;
        PreparedStatement pst = null;

        try {
            // Establishing a connection to the database
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:C://Users//chava//MyDB;create=true");

            // SQL query to insert contact support data
            String query = "INSERT INTO ContactSupport (name, email, message) VALUES (?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, message);

            // Execute the query
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                response.sendRedirect("support.jsp?success=Your message has been sent.");
            } else {
                response.sendRedirect("support.jsp?error=Failed to send your message.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("contact_support.jsp?error=An error occurred while sending your message.");
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
