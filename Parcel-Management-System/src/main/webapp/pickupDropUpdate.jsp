<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Pickup and Dropoff Times</title>
</head>
<body>
    <h2>Update Pickup and Dropoff Times</h2>

    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <c:if test="${not empty successMessage}">
        <p style="color: green;">${successMessage}</p>
    </c:if>

    <c:if test="${not empty bookingId}">
        <form action="UpdatePickupDropServlet" method="post">
            <input type="hidden" name="bookingId" value="${bookingId}" />

            <p>Booking ID: ${bookingId}</p>
            <p>Full Name: ${fullName}</p>
            <p>Address: ${address}</p>
            <p>Recipient Name: ${recName}</p>
            <p>Recipient Address: ${recAddress}</p>
            <p>Parcel Status: ${parStatus}</p>
            <p>Current Pickup Time: ${parPickupTime}</p>
            <p>Current Dropoff Time: ${parDropoffTime}</p>

            <label for="newPickupTime">New Pickup Time:</label>
            <input type="datetime-local" id="newPickupTime" name="newPickupTime" required /><br>

            <label for="newDropoffTime">New Dropoff Time:</label>
            <input type="datetime-local" id="newDropoffTime" name="newDropoffTime" required /><br>

            <input type="submit" value="Update Times" />
        </form>
    </c:if>

    <form action="PickupDropUpdateServlet" method="post">
        <label for="bookingId">Enter Booking ID:</label>
        <input type="text" id="bookingId" name="bookingId" required />
        <input type="submit" value="Fetch Details" />
    </form>
</body>
</html>
