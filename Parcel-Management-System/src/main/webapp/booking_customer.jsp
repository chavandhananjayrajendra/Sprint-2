<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Booking Service</title>
</head>
<body>
    <h2>New Booking - Customer</h2>
    <form action="BookingServletCustomer" method="post">
        <fieldset>
            <legend>Sender Information (Auto-filled)</legend>
            <label for="senderName">Your Name:</label>
            <input type="text" name="senderName" value="${sessionScope.username}" readonly><br>

            <label for="senderAddress">Your Address:</label>
            <textarea name="senderAddress" readonly>${sessionScope.address}</textarea><br>

            <label for="senderContact">Contact:</label>
            <input type="text" name="senderContact" value="${sessionScope.contact}" readonly><br>
        </fieldset>

        <fieldset>
            <legend>Receiver Information</legend>
            <label for="recName">Receiver Name:</label>
            <input type="text" name="recName" required><br>

            <label for="recAddress">Receiver Address:</label>
            <textarea name="recAddress" required></textarea><br>

            <label for="recPin">Receiver Pin:</label>
            <input type="text" name="recPin" required><br>

            <label for="recMobile">Receiver Mobile:</label>
            <input type="text" name="recMobile" required><br>
        </fieldset>

        <fieldset>
            <legend>Parcel Information</legend>
            <label for="parWeightGram">Parcel Weight (in grams):</label>
            <input type="number" name="parWeightGram" required><br>

            <label for="parContentsDescription">Contents Description:</label>
            <textarea name="parContentsDescription" required></textarea><br>

            <label for="parDeliveryType">Delivery Type:</label>
            <select name="parDeliveryType" required>
                <option value="Standard">Standard</option>
                <option value="Express">Express</option>
            </select><br>

            <label for="parPackingPreference">Packing Preference:</label>
            <input type="text" name="parPackingPreference" required><br>

            <label for="parPickupTime">Pickup Time:</label>
            <input type="datetime-local" name="parPickupTime" required><br>

            <label for="parDropoffTime">Dropoff Time:</label>
            <input type="datetime-local" name="parDropoffTime" required><br>
        </fieldset>

        <input type="submit" value="Book Now">
    </form>
</body>
</html>
