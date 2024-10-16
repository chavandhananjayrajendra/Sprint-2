package com.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.IOException;

public class SupportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String issue = request.getParameter("issue");
        
        // Mock sending support email (replace with actual email service integration)
        System.out.println("Support request from " + name + " (" + email + ")");
        System.out.println("Issue: " + issue);

        // After handling, redirect to a confirmation or thank you page
        response.sendRedirect("supportThankYou.jsp");
    }
}
