<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Support</title>
</head>
<body>
    <h2>Contact Support</h2>
    <p>If you have any issues, please reach out to us!</p>
    <form action="sendSupportRequest.jsp" method="post">
        <label for="message">Your Message:</label><br>
        <textarea id="message" name="message" rows="5" cols="30"></textarea><br><br>
        <input type="submit" value="Send">
    </form>
    <a href="customermenu.jsp">Back to Menu</a>
</body>
</html>
