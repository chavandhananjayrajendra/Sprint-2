<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tracking Status</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS -->
</head>
<body>
    <h1>Tracking Status</h1>
    <form action="TrackingStatusOfficerServlet" method="post">
        <label for="trackingId">Enter Booking ID:</label>
        <input type="text" id="trackingId" name="trackingId" required>
        <input type="submit" value="Check Tracking Status">
    </form>

    <hr>
    <a href="officermenu.jsp">Back to Menu</a>
</body>
</html>
