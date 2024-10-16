package com.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login") // Ensure you have a URL mapping for the servlet
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate input (basic validation, can be enhanced)
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            response.sendRedirect("login.jsp?error=Please+enter+username+and+password");
            return;
        }

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Database connection
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:C://Users//chava//MyDB;create=true");

            // Query to verify the user
            String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            rs = pst.executeQuery();

            // Check if user exists
            if (rs.next()) {
                // Create a session for the user
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("address", rs.getString("address")); // Assuming you have an 'address' field
                session.setAttribute("name", rs.getString("name")); // Assuming you have an 'address' field

                session.setAttribute("mobile_number", rs.getString("mobile_number")); // Assuming you have a 'contact' field
                
                // Check if the user is an officer
                if ("Dhanpra23".equals(username) && "Dj26062002".equals(password)) {
                    response.sendRedirect("officermenu.jsp");
                } else {
                    response.sendRedirect("customermenu.jsp");
                }
            } else {
                response.sendRedirect("login.jsp?error=Invalid+Credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=An+error+occurred");
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
