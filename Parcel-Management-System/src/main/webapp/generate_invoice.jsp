<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Generate Invoice</title>
</head>
<body>
    <h2>Generate Invoice</h2>
    <form action="InvoiceServlet" method="post">
        <label for="bookingId">Booking ID:</label>
        <input type="text" name="bookingId" required><br>
        <input type="submit" value="Generate Invoice">
    </form>
</body>
</html>
