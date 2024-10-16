<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.Servlet.TrackingInfo" %>
<html>
<head>
    <title>Tracking Details</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Include your CSS file here -->
</head>
<body>

<div class="container">
    <h1>Tracking Details</h1>

    <% 
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <div class="alert alert-danger">
            <%= errorMessage %>
        </div>
    <%
        } else {
            List<TrackingInfo> trackingInfoList = (List<TrackingInfo>) request.getAttribute("trackingInfoList");
            if (trackingInfoList != null && !trackingInfoList.isEmpty()) {
    %>
        <table>
            <tr>
                <th>Booking ID</th>
                <th>Full Name</th>
                <th>Address</th>
                <th>Receiver Name</th>
                <th>Receiver Address</th>
                <th>Date of Booking</th>
                <th>Parcel Status</th>
            </tr>
            <% for (TrackingInfo info : trackingInfoList) { %>
                <tr>
                    <td><%= info.getBookingId() %></td>
                    <td><%= info.getFullName() %></td>
                    <td><%= info.getAddress() %></td>
                    <td><%= info.getRecName() %></td>
                    <td><%= info.getRecAddress() %></td>
                    <td><%= info.getDateOfBooking() != null ? info.getDateOfBooking().toString() : "N/A" %></td>
                    <td><%= info.getParStatus() %></td>
                </tr>
            <% } %>
        </table>
    <%
            } else {
    %>
        <p>No tracking information available.</p>
    <%
            }
        }
    %>

    <br>
    <a href="trackingStatusofficer.jsp">Back to Tracking</a>
</div>

</body>
</html>
