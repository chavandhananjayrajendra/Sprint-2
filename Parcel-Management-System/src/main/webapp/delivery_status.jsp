<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delivery Status</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS -->
</head>
<body>
    <h1>Delivery Status</h1>
    <form action="DeliveryStatusServlet" method="post">
        <label for="bookingId">Enter Booking ID:</label>
        <input type="text" id="bookingId" name="bookingId" required>
        <input type="submit" value="Check Status">
    </form>

    <hr>
    <h2>Update Delivery Status</h2>
    <form action="DeliveryStatusUpdateServlet" method="post">
        <label for="updateBookingId">Booking ID:</label>
        <input type="text" id="updateBookingId" name="updateBookingId" required>

        <label for="deliveryStatus">Select Status:</label>
        <select id="deliveryStatus" name="deliveryStatus">
            <option value="Booked">Booked</option>
            <option value="In Transit">In Transit</option>
            <option value="Delivered">Delivered</option>
            <option value="Returned">Returned</option>
        </select>
        <input type="submit" value="Update Status">
    </form>

    <hr>
    <a href="officerMenu.jsp">Back to Menu</a>
</body>
</html>
