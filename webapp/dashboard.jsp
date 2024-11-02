<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    String role = (String) session.getAttribute("role");

    if (role == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - AUCA Library System</title>
    <link rel="stylesheet" href="css/styles.css">
      <link rel="stylesheet" href="css/sidebar.css">
</head>
<body>
    <!-- Sidebar -->
    <div id="sidebar"></div>

    <!-- Main Content -->
    <div class="main-content">
        <!-- Header -->
        <header id="header"></header>

        <h1>Welcome to AUCA Library System Dashboard</h1>
        <p>Role: <%= role %></p>

        <% if ("Librarian".equals(role)) { %>
            <a href="BookManagement.jsp">Manage Books</a>
            <a href="MemberRequests.jsp">Approve Members</a>
        <% } else if ("HOD".equals(role) || "Dean".equals(role) || "Register".equals(role) || "Manager".equals(role)) { %>
            <p>View Only Access: No borrowing or book management privileges</p>
            <a href="ViewAllBooks.jsp">View All Books</a>
        <% } %>
    </div>

    <!-- Scripts -->
    <script src="js/sidebar.js"></script>
    <script src="js/header.js"></script>
</body>
</html>
