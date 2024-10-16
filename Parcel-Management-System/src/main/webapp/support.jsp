<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Support</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .message {
            margin-bottom: 20px;
            padding: 10px;
            border-radius: 5px;
            color: white;
        }
        .success {
            background-color: green;
        }
        .error {
            background-color: red;
        }
        form {
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 5px;
            max-width: 500px;
        }
        input[type="text"], input[type="email"], textarea {
            width: 100%;
            padding: 10px;
            margin: 5px 0 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <h2>Contact Customer Support</h2>

    <% 
        String successMessage = request.getParameter("success");
        String errorMessage = request.getParameter("error");

        if (successMessage != null) {
    %>
        <div class="message success">
            <%= successMessage %>
        </div>
    <% 
        }
        if (errorMessage != null) {
    %>
        <div class="message error">
            <%= errorMessage %>
        </div>
    <% 
        }
    %>

    <form action="ContactSupportServlet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="message">Message:</label>
        <textarea id="message" name="message" rows="5" required></textarea>

        <input type="submit" value="Submit Inquiry">
    </form>
</body>
</html>
