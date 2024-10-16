<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.Servlet.OfficerHistory" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Officer Booking History</title>
</head>
<body>

<h2>Booking History</h2>

<!-- Form for entering date range -->
<form action="BookingHistoryOfficerServlet" method="post">
    <label for="startDate">Start Date:</label>
    <input type="datetime-local" id="startDate" name="startDate" required>
    
    <label for="endDate">End Date:</label>
    <input type="datetime-local" id="endDate" name="endDate" required>

    <button type="submit">View Booking History</button>
</form>

<%
    // Retrieve the error message if exists
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null && !errorMessage.isEmpty()) {
%>
    <p style="color: red;"><%= errorMessage %></p>
<%
    }

    // Retrieve the officerHistoryList from the request
    ArrayList<OfficerHistory> officerHistoryList = (ArrayList<OfficerHistory>) request.getAttribute("officerHistoryList");
    if (officerHistoryList != null && !officerHistoryList.isEmpty()) {
%>
    <table border="1">
        <thead>
            <tr>
                <th>Customer ID</th>
                <th>Booking ID</th>
                <th>Booking Date</th>
                <th>Receiver Name</th>
                <th>Delivered Address</th>
                <th>Amount</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the officerHistoryList -->
            <%
                for (OfficerHistory history : officerHistoryList) {
            %>
                <tr>
                    <td><%= history.getCustomerId() %></td>
                    <td><%= history.getBookingId() %></td>
                    <td><%= history.getBookingDate() != null ? history.getBookingDate().toString() : "N/A" %></td>
                    <td><%= history.getRecName() %></td>
                    <td><%= history.getRecAddress() %></td>
                    <td><%= history.getAmount() %></td>
                    <td><%= history.getStatus() %></td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
<%
    } else {
%>
    <p>No booking history found for the given date range.</p>
<%
    }
%>

</body>
</html>
