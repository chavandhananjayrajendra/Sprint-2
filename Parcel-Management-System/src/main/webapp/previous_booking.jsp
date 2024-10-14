<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Previous Bookings</title>
</head>
<body>
    <h2>Your Previous Bookings</h2>
    <table border="1">
        <tr>
            <th>Booking ID</th>
            <th>Receiver Name</th>
            <th>Date of Booking</th>
            <th>Status</th>
        </tr>
        <%
            // Assume connection and user session are already handled
            String userId = (String) session.getAttribute("userId");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/deliverydb;create=true", "app", "app");
            String query = "SELECT booking_id, rec_name, date_of_booking, par_status FROM Bookings WHERE user_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getString("booking_id") %></td>
            <td><%= rs.getString("rec_name") %></td>
            <td><%= rs.getDate("date_of_booking") %></td>
            <td><%= rs.getString("par_status") %></td>
        </tr>
        <%
            }
            con.close();
        %>
    </table>
    <a href="customermenu.jsp">Back to Menu</a>
</body>
</html>
