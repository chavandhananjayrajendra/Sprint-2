<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f8ff;
            text-align: center;
        }
        .confirmation {
            margin-top: 50px;
            background-color: #e0f7fa;
            padding: 30px;
            border-radius: 10px;
            display: inline-block;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #00695c;
        }
        p {
            font-size: 18px;
            color: #004d40;
        }
        .details {
            margin-top: 20px;
            font-size: 18px;
            color: #004d40;
        }
        a {
            text-decoration: none;
            color: #004d40;
            font-weight: bold;
            margin-top: 20px;
            display: inline-block;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="confirmation">
        <h2>Booking Confirmed!</h2>
        <p>${confirmationMessage}</p>
        <p>Thank you for booking with us. Your parcel will be processed shortly.</p>

        <!-- Display username and booking ID details -->
        <div class="details">
           
            <p><strong>Booking ID:</strong> ${bookingId}</p>
        </div>

        <a href="officermenu.jsp">Go to Menu</a>
    </div>
</body>
</html></html>