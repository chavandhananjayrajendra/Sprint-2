<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.Servlet.Booking" %>
<html>
<head>
    <title>Track Status</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS file -->
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <h2>Booking Tracking Status</h2>
    <table>
        <tr>
            <th>Booking ID</th>
            <th>Receiver Name</th>
            <th>Receiver Address</th>
            <th>Status</th>
            <th>Payment Time</th>
        </tr>
        <%
            List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
            if (bookings != null && !bookings.isEmpty()) {
                for (Booking booking : bookings) {
        %>
        <tr>
            <td><%= booking.getId() %></td>
            <td><%= booking.getReceiverName() %></td>
            <td><%= booking.getReceiverAddress() %></td>
            <td><%= booking.getStatus() %></td>
            <td><%= booking.getPaymentTime() != null ? booking.getPaymentTime().toString() : "N/A" %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No booking details found.</td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <a href="booking_customer.jsp">Back to Booking</a> <!-- Link to go back to the booking page -->
</body>
</html>
