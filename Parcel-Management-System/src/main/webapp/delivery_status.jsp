<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Servlet.StatusDetails" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delivery Status Update</title>
</head>
<body>

<h2>Delivery Status Update</h2>

<!-- Form for entering Booking ID -->
<form action="DeliveryStatusUpdateServlet" method="post">
    <label for="bookingId">Booking ID:</label>
    <input type="text" id="bookingId" name="bookingId" required>
    <button type="submit">Fetch Details</button>
</form>

<%
    // Retrieve success or error messages if they exist
    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (successMessage != null && !successMessage.isEmpty()) {
%>
    <p style="color: green;"><%= successMessage %></p>
<%
    } 
    if (errorMessage != null && !errorMessage.isEmpty()) {
%>
    <p style="color: red;"><%= errorMessage %></p>
<%
    }

    // Retrieve the statusDetails from the request
    StatusDetails statusDetails = (StatusDetails) request.getAttribute("statusDetails");
    if (statusDetails != null) {
%>
    <h3>Booking Details:</h3>
    <form action="DeliveryStatusUpdateServlet" method="post">
        <input type="hidden" name="bookingId" value="<%= statusDetails.getBookingId() %>">

        <p>Full Name: <%= statusDetails.getFullName() %></p>
        <p>Address: <%= statusDetails.getAddress() %></p>
        <p>Receiver Name: <%= statusDetails.getRecName() %></p>
        <p>Receiver Address: <%= statusDetails.getRecAddress() %></p>
        <p>Current Status: <%= statusDetails.getParStatus() %></p>
        
        <label for="newStatus">Update Status:</label>
        <select id="newStatus" name="newStatus" required>
            <option value="">Select Status</option>
            <option value="Booked">Booked</option>
            <option value="In Transit">In Transit</option>
            <option value="Delivered">Delivered</option>
            <option value="Returned">Returned</option>
        </select>
        <button type="submit">Update Status</button>
    </form>
<%
    } else {
%>
    <p>No booking details found for the given Booking ID.</p>
<%
    }
%>

</body>
</html>
