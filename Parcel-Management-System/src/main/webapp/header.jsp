<%@ page session="false" %> 
<% 
    HttpSession session = request.getSession(false);
    String username = (session != null) ? (String) session.getAttribute("username") : null;
%>
<div style="text-align: right; padding: 10px; background-color: #f8f9fa; border-bottom: 1px solid #ddd;">
    <% if (username != null) { %>
        <span>Welcome, <strong><%= username %></strong>!</span>
        <a href="logoutServlet" style="margin-left: 20px; text-decoration: none; background-color: #dc3545; color: white; padding: 5px 10px; border-radius: 5px;">Logout</a>
    <% } else { %>
        <a href="login.jsp" style="text-decoration: none; background-color: #007bff; color: white; padding: 5px 10px; border-radius: 5px;">Login</a>
    <% } %>
</div>