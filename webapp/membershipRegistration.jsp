<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Membership Registration</title>
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

    <h2>Membership Registration</h2>
    <form action="membership" method="post">
        <label for="membershipCode">Membership Code:</label>
        <input type="text" id="membershipCode" name="membershipCode" required><br><br>

        <label for="membershipStatus">Membership Status:</label>
        <select id="membershipStatus" name="membershipStatus">
            <option value="APPROVED">APPROVED</option>
            <option value="REJECTED">REJECTED</option>
            <option value="PENDING">PENDING</option>
        </select><br><br>

        <label for="registrationDate">Registration Date (yyyy-mm-dd):</label>
        <input type="text" id="registrationDate" name="registrationDate" required><br><br>

        <label for="expiringTime">Expiration Date (yyyy-mm-dd):</label>
        <input type="text" id="expiringTime" name="expiringTime" required><br><br>

        <label for="userId">User ID:</label>
        <input type="text" id="userId" name="userId" required><br><br>

        <label for="membershipTypeId">Membership Type ID:</label>
        <input type="text" id="membershipTypeId" name="membershipTypeId" required><br><br>

        <input type="submit" value="Register Membership">
    </form>
    </div>
    
     <script src="js/sidebar.js"></script>
    <script src="js/header.js"></script>
</body>
</html>
