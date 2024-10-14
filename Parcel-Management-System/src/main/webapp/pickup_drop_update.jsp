<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Pickup and Drop</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS -->
</head>
<body>
    <h1>Update Pickup and Drop</h1>
    <form action="PickupDropUpdateServlet" method="post">
        <label for="bookingId">Enter Booking ID:</label>
        <input type="text" id="bookingId" name="bookingId" required>
        <input type="submit" value="Fetch Details">
    </form>

    <hr>
    <a href="officerMenu.jsp">Back to Menu</a>
</body>
</html>
