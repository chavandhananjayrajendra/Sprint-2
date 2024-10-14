
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Officer Menu</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS -->
</head>
<body>
    <h1>Welcome to Officer Menu</h1>
    <hr>
    <ul>
        <li><a href="officermenu.jsp">Home</a></li>
        <li><a href="trackingStatus.jsp">Tracking</a></li>
        <li><a href="delivery_status.jsp">Delivery Status</a></li>
        <li><a href="pickup_drop_update.jsp">Pickup Scheduling</a></li>
        <li><a href="previous_booking.jsp">Previous Booking</a></li>
        <li><a href="logoutServlet">Logout</a></li>
    </ul>
</body>
</html>
