<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Track Package (Customer)</title>
</head>
<body>
    <h2>Track Package - Customer</h2>
    <form action="TrackingStatusCustomerServlet" method="post">
        <label for="bookingId">Booking ID:</label>
        <input type="text" name="bookingId" required><br>
        <input type="submit" value="Track">
    </form>
</body>
</html>
