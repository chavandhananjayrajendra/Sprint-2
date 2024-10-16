<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Invoice</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .invoice-container {
            width: 60%;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .invoice-header {
            text-align: center;
        }
        .invoice-details {
            margin-top: 20px;
        }
        .invoice-details label {
            font-weight: bold;
        }
        .invoice-details div {
            margin-bottom: 10px;
        }
        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>

    <div class="invoice-container">
        <div class="invoice-header">
            <h1>Invoice</h1>
            <p>Thank you for booking with us!</p>
        </div>

        <%
            // If there's an error message, display it
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <div class="error-message">
                <%= errorMessage %>
            </div>
        <%
            } else {
        %>

        <!-- Booking Details -->
        <div class="invoice-details">
            <div>
                <label>Booking ID:</label>
                <%= request.getAttribute("bookingId") %>
            </div>
            <div>
                <label>Receiver Name:</label>
                <%= request.getAttribute("recName") %>
            </div>
            <div>
                <label>Receiver Address:</label>
                <%= request.getAttribute("recAddress") %>
            </div>
            <div>
                <label>Receiver Pin Code:</label>
                <%= request.getAttribute("recPin") %>
            </div>
            <div>
                <label>Receiver Mobile:</label>
                <%= request.getAttribute("recMobile") %>
            </div>
            <div>
                <label>Parcel Weight (grams):</label>
                <%= request.getAttribute("parWeightGram") %>
            </div>
            <div>
                <label>Parcel Contents Description:</label>
                <%= request.getAttribute("parContentsDescription") %>
            </div>
            <div>
                <label>Delivery Type:</label>
                <%= request.getAttribute("parDeliveryType") %>
            </div>
            <div>
                <label>Packing Preference:</label>
                <%= request.getAttribute("parPackingPreference") %>
            </div>
            <div>
                <label>Pickup Time:</label>
                <%= request.getAttribute("parPickupTime") %>
            </div>
            <div>
                <label>Dropoff Time:</label>
                <%= request.getAttribute("parDropoffTime") %>
            </div>
            <div>
                <label>Service Cost:</label>
                ₹<%= request.getAttribute("serviceCost") %>
            </div>
            <div>
                <label>Payment Time:</label>
                <%= request.getAttribute("paymentTime") %>
            </div>
        </div>

        <div class="invoice-footer" style="margin-top: 20px; text-align: center;">
            <button onclick="window.print()">Print Invoice</button>
        </div>
        
        <% } %>
    </div>

</body>
</html>
