<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register</h2>
    <form action="RegistrationServlet" method="post">
        Name: <input type="text" name="name"><br>
        Email: <input type="email" name="email"><br>
        Mobile Number: <input type="text" name="mobile_number"><br>
        Address: <input type="text" name="address"><br>
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        Role: 
        <select name="role">
            <option value="customer">Customer</option>
            <option value="officer">Officer</option>
        </select><br>
        <input type="submit" value="Register">
    </form>
</body>
</html>
